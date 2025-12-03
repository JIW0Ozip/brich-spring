<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 관리 - 목록</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/user/admin_user_list.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_list.css"/>
	</head>
	<body>
	   <jsp:include page="/WEB-INF/views/layout/menu.jsp">
	       <jsp:param name='css' value="
                <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
	   </jsp:include>
	   <div class="wrapper">
           <div class="admin-title">회원 관리</div>
              <input type="hidden" id="current-tab" value="${currentTab}"/>
           
            <div class="tab">
                <label for="all-user">전체</label>
                <input type="radio" id="all" data-tab-value="all" name="admin-user-tab" class="tab-item"
                    <c:if test="${currentTab == 'all' || empty currentTab}">checked="checked"</c:if>
                />
        
                <label for="blogger">블로거</label>
                <input type="radio" id="blogger" data-tab-value="blogger" name="admin-user-tab" class="tab-item"
                    <c:if test="${currentTab == 'blogger'}">checked="checked"</c:if>
                />
                <label for="advertiser">광고주</label>
                <input type="radio" id="advertiser" data-tab-value="advertiser" name="admin-user-tab" class="tab-item"
                    <c:if test="${currentTab == 'advertiser'}">checked="checked"</c:if>
            />
            </div>
           <div class="container">
           
               <div id="all-tbl" class="list-tbl grid-11">
                   <!-- Header Row -->
                   <div class="grid-header list-row">
                       <div class="cell">아이디</div>
                       <div class="cell">이름</div>
                       <div class="cell">진행 중인 캠페인</div>
                       <div class="cell">총 진행한 캠페인</div>
                       <div class="cell">가입 승인</div>
                       <div class="cell">블로그 주소</div>
                       <div class="cell">최근 블로그 인증일</div>
                       <div class="cell">구독 만료일</div>
                       <div class="cell">최근 로그인</div>
                       <div class="cell">징계 횟수</div>
                       <div class="cell">가입 일시</div>
                   </div>
                   
                   <c:choose>
                       <c:when test="${not empty userList}">
                           <c:forEach items="${userList}" var="items">
                               <div class="list-row">
                                   <div class="cell">
                                       <a href="/admin/user_detail/${items.usrId}">${items.logId}</a>
                                   </div>
                                   <div class="cell">${items.nm}</div>
                                   <div class="cell">${items.cmpnPrgrssCnt}</div>
                                   <div class="cell">${items.cmpnAllCnt}</div>
                                   
                                   <div class="cell">${items.registAcpt}</div>
                                   
                                   <div class="cell">${empty items.blgAddrs ? '-' : items.blgAddrs}</div>
                                   <div class="cell">${empty items.rcntBlgCrtfctnDt ? '-' : items.rcntBlgCrtfctnDt}</div>
                                   <div class="cell">${empty items.sbscrptnExprsDt ? '-' : items.sbscrptnExprsDt}</div>
                                   
                                   <div class="cell">${empty items.rcntLgnScsDt ? '-' : items.rcntLgnScsDt}</div>
                                   <div class="cell">${items.pnltCnt}</div>
                                   <div class="cell">${items.crtDt}</div>
                               </div>
                           </c:forEach>
                       </c:when>
                   </c:choose>    
               </div>
               
               <div id="blogger-tbl" class="list-tbl grid-10">
                   <!-- Header Row -->
                   <div class="grid-header list-row">
                       <div class="cell">아이디</div>
                       <div class="cell">이름</div>
                       <div class="cell">블로그 주소</div>
                       <div class="cell">최근 블로그 인증일</div>
                       <div class="cell">구독 만료일</div>
                       <div class="cell">진행 중인 캠페인</div>
                       <div class="cell">총 진행한 캠페인</div>
                       <div class="cell">최근 로그인</div>
                       <div class="cell">징계 횟수</div>
                       <div class="cell">가입 일시</div>
                   </div>
                   
                   <c:choose>
                       <c:when test="${not empty userList}">
                           <c:forEach items="${userList}" var="items">
                           <c:if test="${items.autr == '1002' || items.autr == '1003'}">
                                <div class="list-row">
                                    <div class="cell"><a href="/admin/user_detail/${items.usrId}">${items.logId}</a></div>
                                    <div class="cell">${items.nm}</div>
                                    <div class="cell">${empty items.blgAddrs ? '-' : items.blgAddrs}</div>
                                    <div class="cell">${empty items.rcntBlgCrtfctnDt ? '미인증' : items.rcntBlgCrtfctnDt}</div>
                                    <div class="cell">${empty items.sbscrptnExprsDt ? '-' : items.sbscrptnExprsDt}</div>
                                    <div class="cell">${items.cmpnPrgrssCnt}</div>
                                    <div class="cell">${items.cmpnAllCnt}</div>
                                    <div class="cell">${items.rcntLgnScsDt}</div>
                                    <div class="cell">${items.pnltCnt}</div>
                                    <div class="cell">${items.crtDt}</div>
                                </div>
                            </c:if>
                            </c:forEach>
                       </c:when>
                   </c:choose>
               </div>
               
               <div id="advertiser-tbl" class="list-tbl grid-8">
                   <!-- Header Row -->
                   <div class="grid-header list-row">
                       <div class="cell">아이디</div>
                       <div class="cell">이름</div>
                       <div class="cell">가입 승인</div>
                       <div class="cell">진행 중인 캠페인</div>
                       <div class="cell">총 진행한 캠페인</div>
                       <div class="cell">최근 로그인</div>
                       <div class="cell">징계 횟수</div>
                       <div class="cell">가입 일시</div>
                   </div>
                   
                   <c:choose>
                       <c:when test="${not empty userList}">
                           <c:forEach items="${userList}" var="items">
                           <c:if test="${items.autr == '1004' || items.autr == '1007'}">
                                <div class="list-row">
                                    <div class="cell"><a href="/admin/user_detail/${items.usrId}">${items.logId}</a></div>
                                    <div class="cell">${items.nm}</div>
                                    <div class="cell">${items.registAcpt}</div>
                                    <div class="cell">${items.cmpnPrgrssCnt}</div>
                                    <div class="cell">${items.cmpnAllCnt}</div>
                                    <div class="cell">${items.rcntLgnScsDt}</div>
                                    <div class="cell">${items.pnltCnt}</div>
                                    <div class="cell">${items.crtDt}</div>
                                </div>
                           </c:if>
                           </c:forEach>
                       </c:when>
                   </c:choose>
               </div>
               
           </div>
       </div>
	</body>
</html>