package com.ktdsuniversity.edu.domain.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.service.FileService;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public FileVO readFileVO(RequestDownloadVO requestDownloadVO) {
		// 파일 다운로드 횟수 증가
		int updateCount = this.fileDao.updateDownloadCount(requestDownloadVO);

		if (updateCount == 0) {

			throw new IllegalArgumentException("존재하지 않는 파일입니다.");
		}
	
		return this.fileDao.selectFileVO(requestDownloadVO);
	}

}