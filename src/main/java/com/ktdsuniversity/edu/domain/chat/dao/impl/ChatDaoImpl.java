package com.ktdsuniversity.edu.domain.chat.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.chat.dao.ChatDao;
import com.ktdsuniversity.edu.domain.chat.vo.ChatParticipantVO;
import com.ktdsuniversity.edu.domain.chat.vo.ChatRoomVO;
import com.ktdsuniversity.edu.domain.chat.vo.SearchChatVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatRoomFindVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatCampaignListVO;
import com.ktdsuniversity.edu.domain.chat.vo.response.ResponseChatRoomInfoVO;

@Repository
public class ChatDaoImpl extends SqlSessionDaoSupport implements ChatDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.chat.dao.impl.ChatDaoImpl.";

	
    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


    @Override
    public int selectUserChatRoomsCount(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserChatRoomsCount", searchChatVO);
    }

    @Override
    public List<ResponseChatRoomInfoVO> selectUserChatRooms(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserChatRooms", searchChatVO);
    }

    @Override
    public int selectCampaignChatRoomsCount(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignChatRoomsCount", searchChatVO);
    }

    @Override
    public List<ResponseChatRoomInfoVO> selectCampaignChatRooms(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignChatRooms", searchChatVO);
    }

    @Override
    public int selectAllCampaignListCount(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAllCampaignListCount", searchChatVO);
    }

    @Override
    public List<ResponseChatCampaignListVO> selectAllCampaignList(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectAllCampaignList", searchChatVO);
    }

    @Override
    public int selectEndedCampaignListCount(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectEndedCampaignListCount", searchChatVO);
    }

    @Override
    public List<ResponseChatCampaignListVO> selectEndedCampaignList(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectEndedCampaignList", searchChatVO);
    }

    @Override
    public int selectOngoingCampaignListCount(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectOngoingCampaignListCount", searchChatVO);
    }

    @Override
    public List<ResponseChatCampaignListVO> selectOngoingCampaignList(SearchChatVO searchChatVO) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectOngoingCampaignList", searchChatVO);
    }

    @Override
    public int updateChatRoomLeave(ChatParticipantVO participant) {
        return super.getSqlSession().update(this.NAME_SPACE + "updateChatRoomLeave", participant);
    }

    @Override
    public String selectUserName(String usrId) {
        return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserName", usrId);
    }


	@Override
	public CampaignVO selectCampaignByChtRmId(String chtRmId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignByChtRmId", chtRmId);
	}


	@Override
	public String selectTargetUsrIdByChtRmIdAndUsrId(Map<String, String> chtRoomInfo) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectTargetUsrIdByChtRmIdAndUsrId", chtRoomInfo);
	}


	@Override
	public List<String> selectChtRmIdListByCmpnId(Map<String, String> parameter) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectChtRmIdListByCmpnId", parameter);
	}


	@Override
	public List<String> selectChtRmIdListByUsrId(Map<String, String> parameter) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectChtRmIdListByUsrId", parameter);
	}
    
}
