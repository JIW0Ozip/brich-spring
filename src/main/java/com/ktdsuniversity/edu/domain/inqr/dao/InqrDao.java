package com.ktdsuniversity.edu.domain.inqr.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.inqr.vo.InqrSearchVO;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrVO;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.domain.inqr.vo.response.ResponseInqrVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface InqrDao {

	public List<CommonCodeVO> selectInqrCategory();

	public int insertInqr(RequestInqrCreateVO requestInqrCreateVO);

	public int selectMyInqrCount(InqrSearchVO inqrSearchVO);

	public List<InqrVO> selectMyInqrListWithPaging(InqrSearchVO inqrSearchVO);

	public ResponseInqrVO selectInqrDetailByInqrId(String inqrId);

}