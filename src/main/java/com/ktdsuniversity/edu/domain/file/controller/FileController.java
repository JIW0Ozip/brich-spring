package com.ktdsuniversity.edu.domain.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.file.service.FileService;
import com.ktdsuniversity.edu.domain.file.util.ResourceUtil;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;



@Controller
public class FileController {

    @Autowired
    private FileService fileService;
    
    @GetMapping("/file/{id}/{flGrpId}/{flId}")
    public ResponseEntity<Resource> doDownloadAction(
    		@PathVariable String id,
    	    @PathVariable String flGrpId,
    	    @PathVariable String flId) {
  
    	RequestDownloadVO requestDownloadVO = new RequestDownloadVO();
    	requestDownloadVO.setId(id);
    	requestDownloadVO.setFlGrpId(flGrpId);
    	requestDownloadVO.setFlId(flId);
    	
    	FileVO downloadFile = this.fileService.readFileVO(requestDownloadVO);
    	
    	String filename = downloadFile.getFlNm();
    	String filePath = downloadFile.getFlPth();
    	String mimeType = downloadFile.getFlTyp();
    	
    	//Download 시작
    	File file = new File(filePath);
    	
    	return ResourceUtil.getResource(file, filename, mimeType);
    }

}