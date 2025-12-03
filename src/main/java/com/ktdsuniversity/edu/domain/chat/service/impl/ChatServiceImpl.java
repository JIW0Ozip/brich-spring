package com.ktdsuniversity.edu.domain.chat.service.impl;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.dao.ChatDao;
import com.ktdsuniversity.edu.domain.chat.dao.ChatMessageRepository;
import com.ktdsuniversity.edu.domain.chat.service.ChatService;
import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.global.util.SessionUtil;
import com.ktdsuniversity.edu.global.util.TimeFormatUtil;

@Service
public class ChatServiceImpl implements ChatService {

	private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Autowired
	private MultipartFileHandler multipartFileHandler;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private FileGroupDao fileGroupDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public SearchChatVO readAllChatRoomList(SearchChatVO searchChatVO) {
	    String auth = searchChatVO.getAuth();

	    // 1. 총 개수 조회
	    int totalCount = 0;
	    List<ResponseChatRoomInfoVO> chatRooms = null;

	    if (!auth.equals("1004")) {
	        totalCount = chatDao.selectUserChatRoomsCount(searchChatVO);
	        chatRooms = chatDao.selectUserChatRooms(searchChatVO);
	        log.info("{}", System.currentTimeMillis());
	    } else {
	        totalCount = chatDao.selectCampaignChatRoomsCount(searchChatVO);
	        chatRooms = chatDao.selectCampaignChatRooms(searchChatVO);
	    }

	    searchChatVO.setPageCount(totalCount);
	    
	    if (chatRooms == null || chatRooms.isEmpty()) {
	        searchChatVO.setChatRoomList(chatRooms);
	        return searchChatVO;
	    }

	    // 2. 채팅방 ID 목록 추출
	    List<String> chtRmIds = chatRooms.stream()
	            .map(ResponseChatRoomInfoVO::getChtRmId)
	            .collect(Collectors.toList());

	    // 3. 최신 메시지 일괄 조회
	    Aggregation aggLatest = Aggregation.newAggregation(
	        Aggregation.match(Criteria.where("CHT_RM_ID").in(chtRmIds).and("DLT_YN").is("N")),
	        Aggregation.sort(Sort.Direction.DESC, "CRT_DT"),
	        Aggregation.group("CHT_RM_ID")
	            .first("$$ROOT").as("message"),
	        Aggregation.replaceRoot("message")
	    );
	    
	    List<ChatMessageVO> lastMessages = mongoTemplate
	        .aggregate(aggLatest, "CHT_MSG", ChatMessageVO.class)
	        .getMappedResults();
	    
	    Map<String, ChatMessageVO> messageMap = lastMessages.stream()
	            .collect(Collectors.toMap(ChatMessageVO::getChtRmId, msg -> msg));
	    
	    log.info("조회된 채팅방 수: {}, 최신 메시지 수: {}", chtRmIds.size(), messageMap.size());

	    // 4. 안읽은 메시지 수 일괄 조회 
	    Aggregation aggUnread = Aggregation.newAggregation(
	        Aggregation.match(Criteria.where("CHT_RM_ID").in(chtRmIds)
	            .and("DLT_YN").is("N")
	            .and("RD_YN").is("N")
	            .and("USR_ID").ne(searchChatVO.getUsrId())),
	        Aggregation.group("CHT_RM_ID").count().as("count")
	    );
	    
	    List<org.bson.Document> unreadResults = mongoTemplate
	        .aggregate(aggUnread, "CHT_MSG", org.bson.Document.class)
	        .getMappedResults();
	    
	    Map<String, Long> unreadCountMap = new HashMap<>();
	    for (org.bson.Document doc : unreadResults) {
	        String roomId = doc.getString("_id");
	        Integer count = doc.getInteger("count", 0);
	        unreadCountMap.put(roomId, count.longValue());
	    }

	    // 5. 데이터 매칭
	    for (ResponseChatRoomInfoVO chatRoom : chatRooms) {
	        ChatMessageVO lastMessage = messageMap.get(chatRoom.getChtRmId());
	        if (lastMessage != null) {
	            chatRoom.setLastMsgCn(lastMessage.getMsgCn());
	            chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
	            chatRoom.setLastMsgCrtDt(TimeFormatUtil.format(lastMessage.getCrtDt()));
	            chatRoom.setCrtDt(lastMessage.getCrtDt());
	        }

	        chatRoom.setUnreadCnt(unreadCountMap.getOrDefault(chatRoom.getChtRmId(), 0L).intValue());
	        log.info("chatroomCrtDt {}", chatRoom.getCrtDt());
	    }

	    // 6. 최근 메시지 온 순서대로 정렬
	    chatRooms.sort(
	        Comparator.comparing(ResponseChatRoomInfoVO::getCrtDt, 
	            Comparator.nullsFirst(Comparator.naturalOrder()))
	            .reversed());

	    searchChatVO.setChatRoomList(chatRooms);
	    return searchChatVO;
	}

	@Override
	public SearchChatVO readUnreadChatRoomList(SearchChatVO searchChatVO) {
	    String auth = searchChatVO.getAuth();

	    // 1. 전체 채팅방 조회 (페이징 없이)
	    SearchChatVO tempSearch = new SearchChatVO();
	    tempSearch.setUsrId(searchChatVO.getUsrId());
	    tempSearch.setAuth(searchChatVO.getAuth());
	    tempSearch.setCmpnId(searchChatVO.getCmpnId());
	    tempSearch.setPageNo(0);
	    tempSearch.setListSize(Integer.MAX_VALUE);

	    List<ResponseChatRoomInfoVO> allChatRooms = null;
	    
	    if (!auth.equals("1004")) {
	        allChatRooms = chatDao.selectUserChatRooms(tempSearch);
	    } else {
	        allChatRooms = chatDao.selectCampaignChatRooms(tempSearch);
	    }

	    if (allChatRooms == null || allChatRooms.isEmpty()) {
	        searchChatVO.setChatRoomList(new ArrayList<>());
	        searchChatVO.setPageCount(0);
	        return searchChatVO;
	    }

	    // 2. 채팅방 ID 목록 추출
	    List<String> chtRmIds = allChatRooms.stream()
	            .map(ResponseChatRoomInfoVO::getChtRmId)
	            .collect(Collectors.toList());

	    // 3. 최신 메시지 일괄 조회
	    Aggregation aggLatest = Aggregation.newAggregation(
	        Aggregation.match(Criteria.where("CHT_RM_ID").in(chtRmIds).and("DLT_YN").is("N")),
	        Aggregation.sort(Sort.Direction.DESC, "CRT_DT"),
	        Aggregation.group("CHT_RM_ID")
	            .first("$$ROOT").as("message"),
	        Aggregation.replaceRoot("message")
	    );
	    
	    List<ChatMessageVO> lastMessages = mongoTemplate
	        .aggregate(aggLatest, "CHT_MSG", ChatMessageVO.class)
	        .getMappedResults();
	    
	    Map<String, ChatMessageVO> messageMap = lastMessages.stream()
	            .collect(Collectors.toMap(ChatMessageVO::getChtRmId, msg -> msg));

	    // 4. 안읽은 메시지 수 일괄 조회
	    Aggregation aggUnread = Aggregation.newAggregation(
	        Aggregation.match(Criteria.where("CHT_RM_ID").in(chtRmIds)
	            .and("DLT_YN").is("N")
	            .and("RD_YN").is("N")
	            .and("USR_ID").ne(searchChatVO.getUsrId())),
	        Aggregation.group("CHT_RM_ID").count().as("count")
	    );
	    
	    List<org.bson.Document> unreadResults = mongoTemplate
	        .aggregate(aggUnread, "CHT_MSG", org.bson.Document.class)
	        .getMappedResults();
	    
	    Map<String, Long> unreadCountMap = new HashMap<>();
	    for (org.bson.Document doc : unreadResults) {
	        String roomId = doc.getString("_id");
	        Integer count = doc.getInteger("count", 0);
	        unreadCountMap.put(roomId, count.longValue());
	    }

	    // 5. 데이터 매칭 및 필터링
	    List<ResponseChatRoomInfoVO> unreadRooms = new ArrayList<>();
	    
	    for (ResponseChatRoomInfoVO chatRoom : allChatRooms) {
	        // 안읽은 메시지 수 확인
	        int unreadCnt = unreadCountMap.getOrDefault(chatRoom.getChtRmId(), 0L).intValue();
	        
	        // 안읽은 메시지가 없으면 스킵
	        if (unreadCnt == 0) {
	            continue;
	        }
	        
	        chatRoom.setUnreadCnt(unreadCnt);
	        
	        // 최신 메시지 설정
	        ChatMessageVO lastMessage = messageMap.get(chatRoom.getChtRmId());
	        if (lastMessage != null) {
	            chatRoom.setLastMsgCn(lastMessage.getMsgCn());
	            chatRoom.setLastMsgUsrId(lastMessage.getUsrId());
	            chatRoom.setLastMsgCrtDt(TimeFormatUtil.format(lastMessage.getCrtDt()));
	            chatRoom.setCrtDt(lastMessage.getCrtDt());
	        }
	        
	        unreadRooms.add(chatRoom);
	    }

	    // 6. 최근 메시지 순 정렬
	    unreadRooms.sort(
	        Comparator.comparing(ResponseChatRoomInfoVO::getCrtDt, 
	            Comparator.nullsLast(Comparator.naturalOrder()))
	            .reversed());

	    // 7. 메모리 페이징
	    int startIndex = searchChatVO.getPageNo() * searchChatVO.getListSize();
	    int endIndex = Math.min(startIndex + searchChatVO.getListSize(), unreadRooms.size());

	    List<ResponseChatRoomInfoVO> pagedRooms = new ArrayList<>();
	    if (startIndex < unreadRooms.size()) {
	        pagedRooms = unreadRooms.subList(startIndex, endIndex);
	    }

	    searchChatVO.setChatRoomList(pagedRooms);
	    searchChatVO.setPageCount(unreadRooms.size());

	    return searchChatVO;
	}

	@Override
	public SearchChatVO readAllCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectAllCampaignListCount(searchChatVO);

		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectAllCampaignList(searchChatVO);

		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);

		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);

		return searchChatVO;
	}

	@Override
	public SearchChatVO readEndedCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectEndedCampaignListCount(searchChatVO);

		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectEndedCampaignList(searchChatVO);

		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);

		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);

		return searchChatVO;
	}

	@Override
	public SearchChatVO readOngoingCampaignList(SearchChatVO searchChatVO) {
		// 1. 총 개수 조회
		int totalCount = chatDao.selectOngoingCampaignListCount(searchChatVO);

		// 2. 목록 조회
		List<ResponseChatCampaignListVO> campaigns = chatDao.selectOngoingCampaignList(searchChatVO);

		// 3. 페이지 정보 설정
		searchChatVO.setPageCount(totalCount);

		// 4. 결과 목록을 SearchChatVO에 설정
		searchChatVO.setCampaignList(campaigns);

		return searchChatVO;
	}

	@Transactional
	@Override
	public boolean leaveChatRoom(ChatParticipantVO participant) {
		int result = chatDao.updateChatRoomLeave(participant);
		return result > 0;
	}

	@Transactional
	@Override
	public Map<String, Object> readChatMessageListPaged(String chtRmId, String usrId, int page, int size) {
		// 페이징 설정 (최신순 내림차순)
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "crtDt"));

		// 메시지 조회
		Slice<ChatMessageVO> messageSlice = chatMessageRepository.findByChtRmIdAndDltYnOrderByCrtDtDesc(chtRmId, "N",
				pageable);

		// Reverse되게 하기 위해서 List로
		List<ChatMessageVO> messages = new ArrayList<>(messageSlice.getContent());

		// 파일이 있다면 파일 정보 추가
		for (ChatMessageVO message : messages) {
			if (message.getAttchGrpId() != null && !message.getAttchGrpId().isEmpty()) {
				List<FileVO> files = fileDao.selectFilesByGroupId(message.getAttchGrpId());
				message.setFileList(files);
			}
		}

		Collections.reverse(messages);

		// 첫 페이지일 때만 읽음 처리
		if (page == 0) {
			updateMessagesAsRead(chtRmId, usrId);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("messages", messages);
		result.put("hasNext", messageSlice.hasNext());

		return result;
	}

	@Transactional
	@Override
	public ChatMessageVO sendMessage(RequestChatMessageVO requestChatMessageVO) {

		ChatMessageVO message = new ChatMessageVO();

		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestChatMessageVO.getFiles());

		if (uploadResult != null && uploadResult.size() > 0) {
			// 1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size() : 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);

			// 2.File Insert
			for (FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			// 게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestChatMessageVO.setAttchGrpId(fileGroupVO.getFlGrpId());
			message.setAttchGrpId(requestChatMessageVO.getAttchGrpId());
			message.setFileList(uploadResult);
		}

		// 메시지 ID 생성
		message.setChtMsgId(UUID.randomUUID().toString());
		message.setChtRmId(requestChatMessageVO.getChtRmId());
		message.setMsgCn(requestChatMessageVO.getMsgCn());
		message.setUsrId(requestChatMessageVO.getUsrId());
		message.setCrtr(requestChatMessageVO.getUsrId());
		message.setMttr(requestChatMessageVO.getUsrId());
		message.setUsrNm(requestChatMessageVO.getNm());
		message.setCmpny(requestChatMessageVO.getCmpny());
		String now = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		message.setCrtDt(now);
		message.setUpdtDt(now);
		message.setRdYn("N");
		message.setDltYn("N");

		// MongoDB에 메시지 저장
		ChatMessageVO savedMessage = chatMessageRepository.save(message);

		// 사용자 이름 추가
		String userName = chatDao.selectUserName(requestChatMessageVO.getUsrId());
		savedMessage.setUsrNm(userName);

		// 보낸 메시지에 파일도 있으면
		if (uploadResult != null && uploadResult.size() > 0) {
			savedMessage.setFileList(uploadResult);
		}
		return savedMessage;
	}

	@Transactional
	@Override
	public void updateMessagesAsRead(String chtRmId, String usrId) {
		// MongoDB에서 안읽은 메시지 조회
		List<ChatMessageVO> unreadMessages = chatMessageRepository.findUnreadMessages(chtRmId, usrId);
		String now = DateTimeFormatter.ISO_INSTANT.format(Instant.now());

		//Bulk operation으로 N+1해소
		// 1번의 조회 - n개의 데이터 
		// n번만큼 업데이트 - n번 
		// 업데이트를 한번의 실행으로 끝냄
		if (unreadMessages.isEmpty()) {
			return;
		}
		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, ChatMessageVO.class);

		for (ChatMessageVO msg : unreadMessages) {
			Query query = new Query(Criteria.where("_id").is(msg.getChtMsgId()));
			Update update = new Update().set("rdYn", "Y").set("updtDt", now);
			bulkOps.updateOne(query, update);
		}

		bulkOps.execute();
	}

	@Override
	public CampaignVO readCampaignByChtRmId(String chtRmId) {

		String currentUsrId = SessionUtil.getLoginObject().getUsrId();
		
		Map<String, String> chtRoomInfo = new HashMap<>();
		chtRoomInfo.put("chtRmId", chtRmId);
		chtRoomInfo.put("currentUsrId", currentUsrId);
		
		CampaignVO campaignVO = this.chatDao.selectCampaignByChtRmId(chtRmId);
		String targetUsrId = this.chatDao.selectTargetUsrIdByChtRmIdAndUsrId(chtRoomInfo);
		log.info("targetUsrId{}" , targetUsrId);
		if (campaignVO == null) {
			throw new IllegalArgumentException(chtRmId + "에 해당하는 캠페인이 없습니다.");
		}
		campaignVO.setUsrId(targetUsrId);

		return campaignVO;
	}

	@Override
	public List<String> readAllChtRmIdByUsrIdOrCmpnId(Map<String, String> parameter) {
		List<String> chtRmIdList = null;
		//캠페인 아이디가 있으면 캠페인에 대한 채팅방 목록 아이디를 가져온다.
		if(parameter.get("cmpnId") != null) {
			chtRmIdList = this.chatDao.selectChtRmIdListByCmpnId(parameter);
		}
		else {
			chtRmIdList = this.chatDao.selectChtRmIdListByUsrId(parameter);
		}
		return chtRmIdList;
	}


}