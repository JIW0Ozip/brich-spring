<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>회원 관리 - 상세 정보</title>
        <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
        <script type="text/javascript" src="/js/user/admin_user_penalty_process.js"></script>
        <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
            <script type="text/javascript" src="/js/user/admin_advertiser_regist_process.js"></script>
        </c:if>
        <link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
        <link type="text/css" rel="stylesheet" href="/css/admin/admin_blog_crtfctn_modal.css"/>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
                <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
       
       <div class="wrapper">
           <div class="admin-title">회원 상세</div>
           <div class="container">
              <input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
              <input type="hidden" id="usrId" value="${userInfo.usrId}"/>
              <input type="hidden" id="autr" value="${userInfo.autr}"/>
              <c:if test="${classType eq 'AdminBloggerDetailVO'}">
                  <input type="hidden" id="rcntBlgCrtfctnDt" value="${userInfo.rcntBlgCrtfctnDt}"/>
              </c:if>
              
              <c:choose>
                <c:when test="${not empty userInfo}">
                <div class="detail-info">
                   
                   <div class="detail-row">
                      <div class="detail-key">아이디</div>
                      <div class="detail-value">${userInfo.logId}</div>
                   </div>
                   
                   <div class="detail-row">
                      <div class="detail-key">이메일</div>
                      <div class="detail-value">${userInfo.eml}</div>
                   </div>
                   
                   <div class="detail-row">
                      <div class="detail-key">이름</div>
                      <div class="detail-value">${userInfo.nm}</div>
                   </div>
                   
                   <c:if test="${classType eq 'AdminAdvertiserDetailVO'}">
                   <div class="detail-row">
                      <div class="detail-key">상호명</div>
                      <div class="detail-value">
                          <c:choose>
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
                </div>
                </c:when>
              </c:choose>
              
              <div class="btn-group">
                  
                  <c:if test="${(classType eq 'AdminBloggerDetailVO') || 
                                (classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1004')}">
                                
                      <!-- 경고/정지 라디오 버튼 및 처리 버튼 -->
                      <div class="penalty-options">
                          <label for="warning">경고</label>
                          <input type="radio" id="warning" name="penalty-option" class="option_item" value="warning"/>
                          
                          <label for="ban">정지</label>
                          <input type="radio" id="ban" name="penalty-option" class="option_item" value="ban"/>
                          
                          <button class="penalty-btn control-btn save-btn">처리</button>
                      </div>
                      
                      <a href="/admin/user_modify/${usrId}">
                          <button class="control-btn modify-btn">수정</button>
                      </a>
                  </c:if>
              
                  <c:if test="${classType eq 'AdminAdvertiserDetailVO' && userInfo.autr eq '1007'}">
                      <button class="regist-yn-group-btn control-btn reject-btn" data-action="reject">가입 반려</button>
                      <button class="regist-yn-group-btn control-btn approve-btn" data-action="approve">가입 승인</button>
                  </c:if>
              </div>
           </div>
       </div>
    </body>
</html>