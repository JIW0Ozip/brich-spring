package com.ktdsuniversity.edu.domain.file.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

public interface FileDao {

	public int insertFile(FileVO result);

	public int updateDownloadCount(RequestDownloadVO requestDownloadVO);

	public FileVO selectFileVO(RequestDownloadVO requestDownloadVO);

	public int updateFilesAsDelete(Map<String, Object> deleteParamMap);

	public List<FileVO> selectFilesByGroupId(String attchGrpId);

}