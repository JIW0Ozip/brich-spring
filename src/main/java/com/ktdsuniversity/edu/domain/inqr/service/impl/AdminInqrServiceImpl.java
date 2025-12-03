package com.ktdsuniversity.edu.domain.inqr.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.inqr.dao.AdminInqrDao;
import com.ktdsuniversity.edu.domain.inqr.service.AdminInqrService;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminAnsrRegistVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrDetailVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrListVO;

@Service
public class AdminInqrServiceImpl implements AdminInqrService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminInqrServiceImpl.class);
	
	@Autowired
	private AdminInqrDao adminInqrDao;
	
	@Autowired
	private FileGroupDao fileGroupDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private MultipartFileHandler multipartFileHandler;

	@Override
	public List<AdminInqrListVO> readAdminInqrList() {
		return this.adminInqrDao.selectAdminInqrList();
	}

	@Override
	public AdminInqrDetailVO readAdminInqrDetailByInqrId(String inqrId) {
		return this.adminInqrDao.selectAdminInqrDetailByInqrId(inqrId);
	}

	@Transactional
	@Override
	public boolean updateInqrToAnswer(AdminAnsrRegistVO ansrInfo, List<MultipartFile> files) {
		
		if(files != null && !files.isEmpty()) {
			
			List<FileVO> insertedFiles = this.multipartFileHandler.upload(files);
			
			FileGroupVO fileGroupVo = new FileGroupVO();
			this.fileGroupDao.insertFileGroup(fileGroupVo);
			
			for(FileVO fileVO : insertedFiles) {
				fileVO.setFlGrpId(fileGroupVo.getFlGrpId());
				int insertCount = this.fileDao.insertFile(fileVO);
			}
			
			ansrInfo.setAnsrFlGrpId(fileGroupVo.getFlGrpId());
		}
		return this.adminInqrDao.updateInqrToAnswer(ansrInfo) > 0;
	}
	
}
