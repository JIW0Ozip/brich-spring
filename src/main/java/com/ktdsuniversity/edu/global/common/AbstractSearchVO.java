package com.ktdsuniversity.edu.global.common;

/**
 * 페이지네이션과 검색을 담당할 공통 VO
 * 페이지네이션이 필요한 VO에서 상속으로 사용
 */
public abstract class AbstractSearchVO {

	/**
	 * 검색할 페이지 번호
	 * 0 부터 시작
	 */
	private int pageNo;
	
	/**
	 * 한 페이지에 보여줄 항목의 수
	 */
	private int listSize;
	
	/**
	 * 생성될 페이지의 개수
	 * 올림(항목의 수 / listSize)
	 */
	private int pageCount;
	
	//// Page Pagination 시작
	/**
	 * 한 페이지 그룹에 노출시킬 페이지의 개수
	 */
	private int pageCountInGroup;
	
	/**
	 * 페이지 그룹의 총 개수
	 * 올림(페이지 수 / pageCountInGroup)
	 */
	private int groupCount;
	
	/**
	 * 현재 페이지의 그룹 번호
	 * 버림(pageNo / pageCountInGroup)
	 */
	private int groupNo;
	
	/**
	 * 현재 페이지 그룹의 시작 페이지 번호
	 * groupNo * pageCountInGroup
	 */
	private int groupStartPageNo;
	
	/**
	 * 현재 페이지 그룹의 마지막 페이지 번호
	 * 1. (groupNo + 1) * pageCountInGroup - 1
	 * 2. groupStartPageNo + pageCountInGroup - 1
	 */
	private int groupEndPageNo;
	
	/**
	 * 현재 페이지 그룹의 다음 그룹이 있는지 여부
	 * groupNo + 1 < groupCount
	 */
	private boolean haveNextPageGroup;
	
	/**
	 * 현재 페이지 그룹의 이전 그룹이 있는지 여부
	 * groupNo > 0
	 */
	private boolean havePrevPageGroup;
	
	/**
	 * 다음 그룹의 시작 페이지 번호
	 * groupEndPageNo + 1
	 */
	private int nextGroupStartPageNo;
	
	/**
	 * 이전 그룹의 시작 페이지 번호
	 * groupStartPageNo - pageCountInGroup
	 */
	private int prevGroupStartPageNo;
	
	//// Page Pagination 끝
	
	public AbstractSearchVO() {
		this.listSize = 10;
		this.pageCountInGroup = 10;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getListSize() {
		return this.listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	/**
	 * 생성할 총 페이지의 개수를 계산
	 * @param listCount : 조회된 게시글의 총 수
	 */
	public void setPageCount(int listCount) {
		this.pageCount = (int) Math.ceil((double) listCount / this.listSize);
		
		// 그룹 개수 계산
		this.groupCount = (int) Math.ceil((double) this.pageCount / this.pageCountInGroup);
		
		// 현재 페이지의 그룹 계산
		this.groupNo = this.pageNo / this.pageCountInGroup;
		
		// 현재 그룹의 시작 페이지 번호 계산
		this.groupStartPageNo = this.groupNo * this.pageCountInGroup;
		
		// 현재 그룹의 마지막 페이지 번호 계산
		this.groupEndPageNo = (this.groupNo + 1) * this.pageCountInGroup - 1;
		
		// 현재 그룹의 마지막 페이지 보정
		if (this.pageCount <= this.groupEndPageNo) {
			this.groupEndPageNo = this.pageCount - 1;
		}
		if (this.groupEndPageNo < 0) {
			this.groupEndPageNo = 0;
		}
		
		// 현재 그룹에서 다음 그룹이 있는지 확인
		this.haveNextPageGroup = this.groupNo + 1 < this.groupCount;
		
		// 현재 그룹에서 이전 그룹이 있는지 확인
		this.havePrevPageGroup = this.groupNo > 0;
		
		// 다음 그룹으로 이동하기 위한 다음 그룹의 시작 페이지 번호
		this.nextGroupStartPageNo = this.groupEndPageNo + 1;
		
		// 이전 그룹으로 이동하기 위한 이전 그룹의 시작 페이지 번호
		this.prevGroupStartPageNo = this.groupStartPageNo - this.pageCountInGroup;
	}

	public int getPageCountInGroup() {
		return this.pageCountInGroup;
	}

	public void setPageCountInGroup(int pageCountInGroup) {
		this.pageCountInGroup = pageCountInGroup;
	}

	public int getGroupCount() {
		return this.groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupStartPageNo() {
		return this.groupStartPageNo;
	}

	public void setGroupStartPageNo(int groupStartPageNo) {
		this.groupStartPageNo = groupStartPageNo;
	}

	public int getGroupEndPageNo() {
		return this.groupEndPageNo;
	}

	public void setGroupEndPageNo(int groupEndPageNo) {
		this.groupEndPageNo = groupEndPageNo;
	}

	public boolean isHaveNextPageGroup() {
		return this.haveNextPageGroup;
	}

	public void setHaveNextPageGroup(boolean haveNextPageGroup) {
		this.haveNextPageGroup = haveNextPageGroup;
	}

	public boolean isHavePrevPageGroup() {
		return this.havePrevPageGroup;
	}

	public void setHavePrevPageGroup(boolean havePrevPageGroup) {
		this.havePrevPageGroup = havePrevPageGroup;
	}

	public int getNextGroupStartPageNo() {
		return this.nextGroupStartPageNo;
	}

	public void setNextGroupStartPageNo(int nextGroupStartPageNo) {
		this.nextGroupStartPageNo = nextGroupStartPageNo;
	}

	public int getPrevGroupStartPageNo() {
		return this.prevGroupStartPageNo;
	}

	public void setPrevGroupStartPageNo(int prevGroupStartPageNo) {
		this.prevGroupStartPageNo = prevGroupStartPageNo;
	}

	@Override
	public String toString() {
		return "AbstractSearchVO [pageNo=" + pageNo + ", listSize=" + listSize + ", pageCount=" + pageCount
				+ ", pageCountInGroup=" + pageCountInGroup + ", groupCount=" + groupCount + ", groupNo=" + groupNo
				+ ", groupStartPageNo=" + groupStartPageNo + ", groupEndPageNo=" + groupEndPageNo
				+ ", haveNextPageGroup=" + haveNextPageGroup + ", havePrevPageGroup=" + havePrevPageGroup
				+ ", nextGroupStartPageNo=" + nextGroupStartPageNo + ", prevGroupStartPageNo=" + prevGroupStartPageNo
				+ "]";
	}
	
}
