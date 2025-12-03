package com.ktdsuniversity.edu.domain.inqr.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.inqr.dao.InqrDao;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrSearchVO;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrVO;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.domain.inqr.vo.response.ResponseInqrVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class InqrDaoImpl extends SqlSessionDaoSupport implements InqrDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.inqr.dao.impl.InqrDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectInqrCategory() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectInqrCategory");
	}

	@Override
	public int insertInqr(RequestInqrCreateVO requestInqrCreateVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertInqr", requestInqrCreateVO);
	}

	@Override
	public int selectMyInqrCount(InqrSearchVO inqrSearchVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectMyInqrCount", inqrSearchVO);
	}

	@Override
	public List<InqrVO> selectMyInqrListWithPaging(InqrSearchVO inqrSearchVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectMyInqrListWithPaging", inqrSearchVO);
	}

	@Override
	public ResponseInqrVO selectInqrDetailByInqrId(String inqrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectInqrDetailByInqrId", inqrId);
	}


}