package com.ktdsuniversity.edu.domain.chat.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatRoomFindVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

public interface ChatDao {
	// 채팅방 목록 조회 (페이징)
    public int selectUserChatRoomsCount(SearchChatVO searchChatVO);
    
    public List<ResponseChatRoomInfoVO> selectUserChatRooms(SearchChatVO searchChatVO);
    
    public int selectCampaignChatRoomsCount(SearchChatVO searchChatVO);
    
    public List<ResponseChatRoomInfoVO> selectCampaignChatRooms(SearchChatVO searchChatVO);
    
    // 캠페인 목록 조회 (페이징)
    public int selectAllCampaignListCount(SearchChatVO searchChatVO);
    
    public List<ResponseChatCampaignListVO> selectAllCampaignList(SearchChatVO searchChatVO);
    
    public int selectEndedCampaignListCount(SearchChatVO searchChatVO);
    
    public List<ResponseChatCampaignListVO> selectEndedCampaignList(SearchChatVO searchChatVO);
    
    public int selectOngoingCampaignListCount(SearchChatVO searchChatVO);
    
    public List<ResponseChatCampaignListVO> selectOngoingCampaignList(SearchChatVO searchChatVO);
    
    // 채팅방 나가기
    public int updateChatRoomLeave(ChatParticipantVO participant);
    
    // 사용자 이름 조회
    public String selectUserName(String usrId);

    //채팅방에 해당하는 캠페인 정보 조회
	public CampaignVO selectCampaignByChtRmId(String chtRmId);

	public String selectTargetUsrIdByChtRmIdAndUsrId(Map<String, String> chtRoomInfo);

	public List<String> selectChtRmIdListByCmpnId(Map<String, String> parameter);

	public List<String> selectChtRmIdListByUsrId(Map<String, String> parameter);
}
