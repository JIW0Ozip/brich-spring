<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 정보 수정</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
                <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
	    <div class="wrapper">
           <div class="admin-title">회원 정보 수정</div>
           
           <div class="container">
               <input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
               <input type="hidden" id="usrId" value="${userInfo.usrId}"/>
               <input type="hidden" id="autr" value="${userInfo.autr}"/>
               <input type="hidden" id="flGrpId" value="${userInfo.flGrpId}"/>
           
              <div class="detail-info">
                <c:choose>
                  <c:when test="${not empty userInfo}">
                    
                    <div class="detail-row">
                       <div class="detail-key">아이디</div>
                       <div class="detail-value">
                         <input type="text" id="logId" name="logId" class="modify-values" value="${userInfo.logId}"/>
                       </div>
                    </div>
                    
                    <div class="detail-row">
                       <div class="detail-key">이메일</div>
                       <div class="detail-value">
                           <input type="text" id="eml" name="eml" class="modify-values" value="${userInfo.eml}"/>
                       </div>
                    </div>
                    
                    <div class="detail-row">
                       <div class="detail-key">이름</div>
                       <div class="detail-value">
                           <input type="text" id="nm" name="nm" class="modify-values" value="${userInfo.nm}"/>
                       </div>
                    </div>
                    
                    <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                    <div class="detail-row">
                       <div class="detail-key">상호명</div>
                       <div class="detail-value">
                           <c:choose>
                               <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
                                   <input type="text" id="cmpny" name="cmpny" class="modify-values" value="${userInfo.cmpny}" />
                               </c:when>
                               
                               <c:when test="${not empty userInfo.cmpny}">
                                   ${userInfo.cmpny}
                               </c:when>
                               
                               <c:otherwise>
                                   -
                               </c:otherwise>
                           </c:choose>
                       </div>
                    </div>
                    </c:if>
                    
                    <!-- include (AdminBloggerDetailVO / AdminAdvertiserDetailVO) -->
                    <c:if test="${classType eq 'AdminBloggerDetailVO'}">
                        <jsp:include page="../user/admin_blogger_detail.jsp"></jsp:include>
                    </c:if>
                     
                    <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                        <jsp:include page="../user/admin_advertiser_detail.jsp"></jsp:include>
                    </c:if>
                    
                    <div class="detail-row section-separator-top">
                       <div class="detail-key">최근 로그인</div>
                       <div class="detail-value">${userInfo.rcntLgnScsDt}</div>
                    </div>
                    
                    <div class="detail-row">
                       <div class="detail-key">징계 횟수</div>
                       <div class="detail-value">${userInfo.pnltCnt}</div>
                    </div>
                    
                    <div class="detail-row">
                       <div class="detail-key">가입 일시</div>
                       <div class="detail-value">${userInfo.crtDt}</div>
                    </div>
                    
                    <div class="detail-row last-row">
                       <div class="detail-key">수정 사유</div>
                       <div class="detail-value">
                           <input type="text" id="updtRsn" name="cmpny" class="modify-values"/>
                       </div>
                    </div>
                  </c:when>
                </c:choose>
              </div>
              
              <div class="btn-group">
                  <a href="/admin/user_detail/${userInfo.usrId}">
                      <button type="button" class="control-btn cancel-btn modify-btn">취소</button>
                  </a>
                  <button type="button" class="control-btn save-btn">완료</button>
              </div>
           </div>
       </div>
	</body>
</html>