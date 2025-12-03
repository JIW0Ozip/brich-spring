package com.ktdsuniversity.edu.domain.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.blog.controller.SearchBlogController;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.service.ChatService;
import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
@RequestMapping("/{auth}/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	private static final Logger log = LoggerFactory.getLogger(ChatController.class);

	/**
	 * 광고주 - 캠페인 목록 페이지
	 */
	@GetMapping("/campaigns")
	public String viewCampaignListpage(@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			Model model) {
		String usrId = loginUser.getUsrId();

		model.addAttribute("usrId", usrId);

		return "chat/campaignlist";
	}

	/**
	 * 채팅방 목록 페이지
	 */
	@GetMapping("/rooms")
	public String viewChatRoomListPage(@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			Model model, @RequestParam(required = false) String cmpnId) {
		String usrId = loginUser.getUsrId();
		Map<String, String> parameter = new HashMap<>();
		parameter.put("usrId", usrId);
		parameter.put("cmpnId", cmpnId);
		List<String> allChtRmId = this.chatService.readAllChtRmIdByUsrIdOrCmpnId(parameter);
		log.info(cmpnId);
		model.addAttribute("usrId", usrId);
		model.addAttribute("cmpnId", cmpnId);
		model.addAttribute("allChtRmId", allChtRmId);
		return "chat/chatRoomList";
	}

	//TODO 현재 로딩될때 상대방 메시지가 없으면 상대방 아이디를 못받아오는 이슈가 있음(신고를 못함)
	//     따라서 VO를 새로 만들어서 상대방 아이디를 포함해서 반환해줄 필요가 있음
	/**
	 * 채팅방 페이지
	 */
	@GetMapping("/room/{chtRmId}")
	public String viewChatRoomPage(@PathVariable String chtRmId,
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser, Model model) {
		String usrId = loginUser.getUsrId();
		String auth = loginUser.getAutr();
		CampaignVO campaignVO = this.chatService.readCampaignByChtRmId(chtRmId);
		model.addAttribute("chtRmId", chtRmId);
		model.addAttribute("usrId", usrId);
		model.addAttribute("auth", auth);
		model.addAttribute("campaign", campaignVO);

		return "chat/chatRoom";
	}

	/**
	 * 채팅방 목록 전체 조회 (페이징)
	 */
	@GetMapping("/rooms/all")
	@ResponseBody
	public AjaxResponse getAllChatRoomList(
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			@RequestParam(required = false) String cmpnId, @RequestParam(defaultValue = "0") int pageNo) {

		String usrId = loginUser.getUsrId();
		String auth = loginUser.getAutr();
		log.info("{} auth", auth);

		SearchChatVO searchChatVO = new SearchChatVO();
		searchChatVO.setUsrId(usrId);
		searchChatVO.setAuth(auth);
		searchChatVO.setCmpnId(cmpnId);
		searchChatVO.setPageNo(pageNo);

		SearchChatVO result = chatService.readAllChatRoomList(searchChatVO);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);
		return ajaxResponse;
	}

	/**
	 * 채팅방 목록 안읽은 것만 조회 (페이징)
	 */
	@GetMapping("/rooms/unread")
	@ResponseBody
	public AjaxResponse getUnreadChatRoomList(
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			@RequestParam(required = false) String cmpnId, @RequestParam(defaultValue = "0") int pageNo) {
		String usrId = loginUser.getUsrId();
		String auth = loginUser.getAutr();

		SearchChatVO searchChatVO = new SearchChatVO();
		searchChatVO.setUsrId(usrId);
		searchChatVO.setAuth(auth);
		searchChatVO.setCmpnId(cmpnId);
		searchChatVO.setPageNo(pageNo);

		SearchChatVO result = chatService.readUnreadChatRoomList(searchChatVO);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 광고주용 채팅방 캠페인 목록 전체 (페이징)
	 */
	@GetMapping("/campaigns/all")
	@ResponseBody
	public AjaxResponse getAllCampaignList(
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			@RequestParam(defaultValue = "0") int pageNo) {
		String usrId = loginUser.getUsrId();

		SearchChatVO searchChatVO = new SearchChatVO();
		searchChatVO.setUsrId(usrId);
		searchChatVO.setPageNo(pageNo);

		SearchChatVO result = chatService.readAllCampaignList(searchChatVO);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 광고주용 채팅방 캠페인 목록 종료 (페이징)
	 */
	@GetMapping("/campaigns/ended")
	@ResponseBody
	public AjaxResponse getEndedCampaignList(
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			@RequestParam(defaultValue = "0") int pageNo) {
		String usrId = loginUser.getUsrId();

		SearchChatVO searchChatVO = new SearchChatVO();
		searchChatVO.setUsrId(usrId);
		searchChatVO.setPageNo(pageNo);

		SearchChatVO result = chatService.readEndedCampaignList(searchChatVO);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 광고주용 채팅방 캠페인 목록 진행중 (페이징)
	 */
	@GetMapping("/campaigns/ongoing")
	@ResponseBody
	public AjaxResponse getOngoingCampaignList(
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser,
			@RequestParam(defaultValue = "0") int pageNo) {
		String usrId = loginUser.getUsrId();

		SearchChatVO searchChatVO = new SearchChatVO();
		searchChatVO.setUsrId(usrId);
		searchChatVO.setPageNo(pageNo);

		SearchChatVO result = chatService.readOngoingCampaignList(searchChatVO);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 채팅방 나가기
	 */
	@PostMapping("/room/leave")
	@ResponseBody
	public AjaxResponse leaveChatRoom(@RequestParam String chtRmId,
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser) {
		String usrId = loginUser.getUsrId();

		AjaxResponse ajaxResponse = new AjaxResponse();

		ChatParticipantVO participant = new ChatParticipantVO();
		participant.setChtRmId(chtRmId);
		participant.setUsrId(usrId);
		participant.setMttr(usrId);
		log.info("participant: {}", participant);

		boolean success = chatService.leaveChatRoom(participant);

		Map<String, Object> result = new HashMap<>();
		result.put("success", success);
		result.put("message", success ? "채팅방을 나갔습니다." : "채팅방 나가기에 실패했습니다.");

		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 채팅방 입장 시 메시지 목록 조회 (페이징)
	 */
	@GetMapping("/room/{chtRmId}/messages")
	@ResponseBody
	public AjaxResponse getChatMessages(@PathVariable String chtRmId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser) {

		String usrId = loginUser.getUsrId();

		Map<String, Object> result = chatService.readChatMessageListPaged(chtRmId, usrId, page, size);

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * 메시지 전송 파일이 있는 경우
	 */
	@PostMapping("/message/send")
	@ResponseBody
	public AjaxResponse sendMessage(RequestChatMessageVO requestChatMessageVO,
			@SessionAttribute(name = "__LOGIN_USER__", required = false) UserVO loginUser) {
		log.info("위 호출");
		String usrId = loginUser.getUsrId();

		AjaxResponse ajaxResponse = new AjaxResponse();

		ChatMessageVO savedMessage = chatService.sendMessage(requestChatMessageVO);

		// WebSocket으로 다른 참가자에게 메시지 전송
		messagingTemplate.convertAndSend("/topic/chat/" + requestChatMessageVO.getChtRmId(), savedMessage);

		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", savedMessage);

		ajaxResponse.setBody(result);

		return ajaxResponse;
	}

	/**
	 * WebSocket 메시지 핸들러 - 채팅 메시지 전송
	 */
	@MessageMapping("/chat/send")
	public void handleChatMessage(@Payload RequestChatMessageVO requestChatMessageVO) {
		// 메시지 저장 (파일 없이)
		ChatMessageVO savedMessage = chatService.sendMessage(requestChatMessageVO);

		// TODO JS Stomp Client 사용해 테스트, Postman 등으로는 테스트 어려움
		// 채팅방의 모든 참가자에게 메시지 전송
		messagingTemplate.convertAndSend("/topic/chat/" + requestChatMessageVO.getChtRmId(), savedMessage);
	}

	/**
	 * WebSocket 메시지 핸들러 - 읽음 처리
	 */
	@MessageMapping("/chat/read")
	public void handleReadMessage(@Payload Map<String, String> payload) {
		String chtRmId = payload.get("chtRmId");
		String usrId = payload.get("usrId");

		chatService.updateMessagesAsRead(chtRmId, usrId);

		// 읽음 처리 알림을 채팅방 참가자에게 전송
		messagingTemplate.convertAndSend("/topic/chat/" + chtRmId + "/read", payload);
	}
	
	
}
