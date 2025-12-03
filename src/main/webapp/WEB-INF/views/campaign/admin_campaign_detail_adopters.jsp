<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="scripts">
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type="text/javascript" src="/js/campaign/admin_campaign_detail.js"></script>
       <script type="text/javascript" src="/js/campaign/admin_campaign_detail_adopters.js"></script>
       <script type="text/javascript" src="/js/common/paginator.js"></script>
</c:set>

<link type='text/css' rel='stylesheet' href='/css/admin/admin_return_reason_modal.css'/>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
   <jsp:param name='css' value="
       <link type='text/css' rel='stylesheet' href='/css/brich.css' />
   " />
   <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

<style>
    button {
        cursor: pointer;
    }
    
    .list-applicant {
        grid-template-columns: 160px auto repeat(3, 200px);
    }
    
    .button_50_30 {
        width: 80px;
        height: 30px;
        
        box-sizing: border-box;
        margin: 0 auto;
        font-size: 0.75rem;
        font-weight: normal;
        
        border: 0;
        border-radius: 5px;
        vertical-align: middle;
    }
    
    .table-top-item {
        display: flex;
        box-sizing: border-box;
    }
    
    .posting-approve-adopter-count {
        font-size: 1rem;
        font-weight: normal;
        
        box-sizing: border-box;
        margin-top: 20px;
    }
    
</style>

    <div class="campaign-wrapper">
        <jsp:include page="/WEB-INF/views/campaign/admin_campaign_detail_tab.jsp">
           <jsp:param value="${adopterList.campaignInfo.cmpnId}" name="cmpnId" />
           <jsp:param value="${adopterList.campaignInfo.cmpnTitle}" name="cmpnTitle" />
           <jsp:param value="${adopterList.campaignInfo.sttsCd}" name="sttsCd" />
        </jsp:include>
        <div class="campaign-list-wrapper">
            <div class="campaign-title">
                <div>캠페인 채택자</div>
                <span class="enddate">~${adopterList.campaignInfo.rcrtEndDt}</span>
            </div>
            
            <div class="table-top-item">
            
            <span class="posting-approve-adopter-count">
                총 ${adopterList.adopterCount}명 중 ${adopterList.postApproveCount}명 완료
            </span>
            
            <div class="id-search">
               <input type="text" placeholder="ID를 입력하세요."/>
               <img src="/img/Search.png" />
            </div>
            
            </div>
            
            <div class="list-header grid-list-header list-applicant">
                <div>
                    아이디
                </div>
                <div>포스팅 URL
                    <!-- <img class="sort desc" data-sort-type="PST_URL" src="/img/arrow-bottom.png" /> -->
                </div>
                <div>진행 상태
                    <!-- <img class="sort desc" data-sort-type="PST_STTS_CD" src="/img/arrow-bottom.png" /> -->
                </div>
                <div>반려 이력
                    <!-- <img class="sort desc" data-sort-type="POST_RETN_RSN" src="/img/arrow-bottom.png" /> -->
                </div>
                <div>재제출 이력
                    <!-- <img class="sort desc" data-sort-type="POST_SUBMIT_CHG_CN" src="/img/arrow-bottom.png" /> -->
                </div>
            </div>
            
            <c:forEach items="${adopterList.adopterList}" var="adopterList">
                <div class="grid-list-header list-applicant applicant adopters">
                    <div class="user">
                        <div class="blgUsrId" data-usr-id="${adopterList.blgUsrId}"></div>
                        <div class="logId" data-post-id="${adopterList.cmpnPstAdptId}">${adopterList.logId}</div>
                    </div>
                    <div>
                        <a href="${adopterList.pstUrl}" target="_blank">${adopterList.pstUrl}</a>
                    </div>
                    <div>
                        ${adopterList.pstSttsCdNm}
                    </div>
                    
                    <!-- 블로거 반려 사유 버튼 / 재제출 사유 버튼 -->
                    <div>
                        <c:choose>
                            <c:when test="${adopterList.postRtrnYn eq 'Y'}">
                                <button type="button" id="postReturnRsnBtn" class="button_50_30 unadopted postReturnRsnBtn">자세히 보기</button>
                            </c:when>
                            
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <div>
                        <c:choose>
                            <c:when test="${adopterList.postSubmitChgYn eq 'Y'}">
                                <button type="button" id="postReSubmitCnBtn" class="button_50_30 unadopted postReSubmitCnBtn">자세히 보기</button>
                            </c:when>
                            
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
            
            <jsp:include page="/WEB-INF/views/layout/paginator.jsp">
                <jsp:param value="${search.listSize}" name="listSize"/>
                <jsp:param value="${search.havePrevPageGroup}" name="havePrevPageGroup"/>
                <jsp:param value="${search.prevGroupStartPageNo}" name="prevGroupStartPageNo"/>
                <jsp:param value="${search.groupStartPageNo}" name="groupStartPageNo"/>
                <jsp:param value="${search.groupEndPageNo}" name="groupEndPageNo"/>
                <jsp:param value="${search.pageNo}" name="pageNo"/>
                <jsp:param value="${search.haveNextPageGroup}" name="haveNextPageGroup"/>
                <jsp:param value="${search.nextGroupStartPageNo}" name="nextGroupStartPageNo"/>
                <jsp:param value="${search.pageCount}" name="pageCount"/>
            </jsp:include>
        </div>
    </div>
    
    <!-- 반려 사유/재제출 내용 모달 -->
    <div id="adopters-modal" style="display: none;">
        <div id="modal-container">
	        <div id="modal-title-area">
	            <span id="modal-title">logId의 반려 사유/재제출 내용</span>
	        </div>
	        
	        <div id="modal-content-area">
	            
	        </div>
	        
	        <div id="modal-button-area">
	            <button type="button" id="modal-close-btn">닫기</button>
	        </div>
        </div>
    </div>
    
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />