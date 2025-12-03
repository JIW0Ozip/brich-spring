package com.ktdsuniversity.edu.domain.file.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

@Repository
public class FileDaoImpl extends SqlSessionDaoSupport implements FileDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.file.dao.impl.FileDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertFile(FileVO result) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertFile", result);
	}

	@Override
	public int updateDownloadCount(RequestDownloadVO requestDownloadVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateDownloadCount", requestDownloadVO);
	}

	@Override
	public FileVO selectFileVO(RequestDownloadVO requestDownloadVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectFileVO", requestDownloadVO);
	}

	@Override
	public int updateFilesAsDelete(Map<String, Object> deleteParamMap) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateFilesAsDelete", deleteParamMap);
	}

	@Override
	public List<FileVO> selectFilesByGroupId(String attchGrpId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectFilesByGroupId", attchGrpId);
	}

}