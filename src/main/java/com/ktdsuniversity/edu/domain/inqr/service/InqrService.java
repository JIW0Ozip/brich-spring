package com.ktdsuniversity.edu.domain.inqr.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.inqr.vo.InqrSearchVO;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrVO;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.domain.inqr.vo.response.ResponseInqrVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface InqrService {

	public List<CommonCodeVO> readInqrCategory();

	public boolean createNewInqr(RequestInqrCreateVO requestInqrCreateVO);

	public List<InqrVO> selectMyInqrListWithPaging(InqrSearchVO inqrSearchVO);

	public ResponseInqrVO readInqrDetailByInqrId(String inqrId);

}