package com.ktdsuniversity.edu.domain.inqr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.inqr.vo.AdminAnsrRegistVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrDetailVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrListVO;

public interface AdminInqrService {

	List<AdminInqrListVO> readAdminInqrList();

	AdminInqrDetailVO readAdminInqrDetailByInqrId(String inqrId);

	boolean updateInqrToAnswer(AdminAnsrRegistVO ansrInfo, List<MultipartFile> files);

}
