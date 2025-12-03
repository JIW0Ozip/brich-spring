package com.ktdsuniversity.edu.domain.file.service;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

public interface FileService {

	public FileVO readFileVO(RequestDownloadVO requestDownloadVO);

}