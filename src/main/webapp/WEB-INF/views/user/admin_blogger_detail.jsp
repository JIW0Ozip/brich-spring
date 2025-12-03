<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
<script type="text/javascript" src="/js/user/admin_blog_address_crtfctn.js"></script>

<input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
<input type="hidden" id="usrId" value="${userInfo.usrId}"/>
<input type="hidden" id="autr" value="${userInfo.autr}"/>
<input type="hidden" id="flGrpId" value="${userInfo.flGrpId}"/>

<div class="detail-row">
    <div class="detail-key">진행 중인 캠페인 수</div>
    <div class="detail-value">${userInfo.cmpnProgressCnt}</div>
</div>

<div class="detail-row campaign-list-row">
    <div class="detail-key">진행 중인 캠페인</div>
    <div class="detail-value">
        <c:choose>
            <c:when test="${not empty userInfo.cmpnProgressList}">
                <c:forEach items="${userInfo.cmpnProgressList}" var="campaginInfo">
                    <a href="/admin/campaign-detail/${campaginInfo.cmpnId}">[${campaginInfo.cmpnTitle}]</a>
                </c:forEach>
            </c:when>
            
            <c:otherwise>
              -
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">완료한 캠페인 수</div>
    <div class="detail-value">${userInfo.cmpnCompletedCnt}</div>
</div>

<div class="detail-row campaign-list-row">
    <div class="detail-key">완료한 캠페인</div>
    <div class="detail-value">
       <c:choose>
           <c:when test="${not empty userInfo.cmpnCompletedList}">
                <c:forEach items="${userInfo.cmpnCompletedList}" var="campaginInfo">
                    <a href="/admin/campaign-detail/${campaginInfo.cmpnId}">[${campaginInfo.cmpnTitle}]</a>
                </c:forEach>
           </c:when>
           
           <c:otherwise>
              -
           </c:otherwise>
       </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">구독 만료일</div>
    <div class="detail-value">
        <c:choose>
           <c:when test="${not empty userInfo.sbscrptnExprsDt}">
               ${userInfo.sbscrptnExprsDt}
           </c:when>
           
           <c:otherwise>
              -
           </c:otherwise>
       </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">블로그 주소</div>
    <div class="detail-value blog-address-area">
        <c:choose>
            <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
                <input type="text" class="modify-values" name="blgAddr" value="${userInfo.blgAddrs}"/>
            </c:when>
            
            <c:otherwise>
                <input type="text" class="modify-values" name="blgAddr" value="${userInfo.blgAddrs}" readonly="readonly"/>
                <button type="button" class="blog-crtfctn-active-btn">수동 인증</button>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">최근 블로그 인증일</div>
    <div class="detail-value" id="rcntBlgCrtfctnDt">${empty userInfo.rcntBlgCrtfctnDt ? '미인증' : userInfo.rcntBlgCrtfctnDt}</div>
</div>

<div class="detail-row">
    <div class="detail-key">활동 지역</div>
    <div class="detail-value">
       <c:choose>
           <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
               <!-- 활동 지역 수정 영역 (복잡한 로직은 JS에서 처리된다고 가정) -->
               <span class="placeholder-text">[수정 로직 필요]</span>
           </c:when>
           
           <c:when test="${not empty userInfo.usrAr}">
                <c:forEach items="${userInfo.usrAr}" var="bloggerInfo">
                    [${bloggerInfo.cdNm}]
                </c:forEach>
           </c:when>
           
           <c:otherwise>
              -
           </c:otherwise>
       </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">블로그 카테고리</div>
    <div class="detail-value">
       <c:choose>
           <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
               <c:forEach items="${BlogcategoryList}" var="categoryInfo">
                   <input type="checkbox" class="modify-values category-checkbox" name="blogCategory" 
                          value="${categoryInfo.cdId}" 
                       <c:if test="${userInfo.checkedBlgCtg.contains(categoryInfo.cdId)}">
                           checked="checked"
                       </c:if>
                   />
                   <label class="category-label">${categoryInfo.cdNm}</label>
               </c:forEach>
           </c:when>
           
           <c:when test="${not empty userInfo.usrBlgCtg}">
                <c:forEach items="${userInfo.usrBlgCtg}" var="bloggerInfo">
                    [${bloggerInfo.cdNm}]
                </c:forEach>
           </c:when>
           
           <c:otherwise>
              -
           </c:otherwise>
       </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">평균 방문자 수</div>
    <div class="detail-value">${userInfo.avrgVstrCnt}</div>
</div>

<div class="detail-row">
    <div class="detail-key">블로그 이웃 수</div>
    <div class="detail-value">${userInfo.blgNghbrCnt}</div>
</div>

<div class="detail-row last-row">
    <div class="detail-key">스크랩 수</div>
    <div class="detail-value">${userInfo.scrpCnt}</div>
</div>

<!-- 블로그 수동 인증 모달 -->
<div id="blog-address-modal" style="display: none;">
    <div id="modal-content-area">
        <div id="modal-title-area">
            <span id="modal-title">블로그 인증 처리</span>
        </div>
        
        <div id="modal-user-id-area">블로거 아이디
            <div id="modal-user-id">${userInfo.logId}</div>
        </div>
        
        <div id="modal-blog-address-area">
            블로그 주소
            <input type="text" id="blogAddress" placeholder="수동 인증할 블로그 주소 입력"/>
        </div>
        
        <div id="modal-admin-id-area">
            <span id="modal-admin-id-title">처리할 관리자</span>
            <div id="modal-admin-id">${sessionScope.__LOGIN_USER__.usrId}</div>
        </div>
        
        <div id="modal-btn-area">
            <button type="button" id="modal-close-btn">닫기</button>
            <button type="button" id="modal-save-btn">확인</button>
        </div>
    </div>
</div>