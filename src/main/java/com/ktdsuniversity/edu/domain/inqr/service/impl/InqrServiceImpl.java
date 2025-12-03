package com.ktdsuniversity.edu.domain.inqr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.inqr.dao.InqrDao;
import com.ktdsuniversity.edu.domain.inqr.service.InqrService;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrSearchVO;
import com.ktdsuniversity.edu.domain.inqr.vo.InqrVO;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.domain.inqr.vo.response.ResponseInqrVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseMyReportInfoVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class InqrServiceImpl implements InqrService {

	@Autowired
	private MultipartFileHandler multipartFileHandler;

	@Autowired
	private InqrDao inqrDao;

	@Autowired
	private FileGroupDao fileGroupDao;

	@Autowired
	private FileDao fileDao;

	@Override
	public List<CommonCodeVO> readInqrCategory() {
		return this.inqrDao.selectInqrCategory();
	}

	@Transactional
	@Override
	public boolean createNewInqr(RequestInqrCreateVO requestInqrCreateVO) {

		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestInqrCreateVO.getFile());

		if (uploadResult != null && uploadResult.size() > 0) {
			// 1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size() : 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);

			// 2.File Insert

			for (FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			// 게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestInqrCreateVO.setInqrFlGrpId(fileGroupVO.getFlGrpId());
		}

		int insertResult = this.inqrDao.insertInqr(requestInqrCreateVO);

		return insertResult > 0;
	}

	/**
	 * 문의글 목록 조회(페이징)
	 */
	@Override
	public List<InqrVO> selectMyInqrListWithPaging(InqrSearchVO inqrSearchVO) {
		// 1. 문의 글 건수 조회
		int inqrCount = this.inqrDao.selectMyInqrCount(inqrSearchVO);
		// 2. 페이지 정보 설정
		inqrSearchVO.setPageCount(inqrCount);

		if (inqrCount == 0) {
			return new ArrayList<>();
		}
		// 3. 문의글 목록 조회
		List<InqrVO> inqrList = this.inqrDao.selectMyInqrListWithPaging(inqrSearchVO);

		return inqrList;
	}

	/**
	 * 문의글 상세조회, 파일 포함
	 */
	@Override
	public ResponseInqrVO readInqrDetailByInqrId(String inqrId) {

		ResponseInqrVO inqrDetail = this.inqrDao.selectInqrDetailByInqrId(inqrId);

		//문의글 첨부파일 존재 여부 확인
		if (inqrDetail.getInqrFlGrpId() != null && !inqrDetail.getInqrFlGrpId().isEmpty()) {
			List<FileVO> files = fileDao.selectFilesByGroupId(inqrDetail.getInqrFlGrpId());
			inqrDetail.setMyFile(files);
		}
		//답변글 첨부파일 존재 여부 확인
		if (inqrDetail.getAnsrFlGrpId() != null && !inqrDetail.getAnsrFlGrpId().isEmpty()) {
			List<FileVO> files = fileDao.selectFilesByGroupId(inqrDetail.getAnsrFlGrpId());
			inqrDetail.setAnswerFile(files);
		}
		return inqrDetail;
	}

}