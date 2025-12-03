<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>신고 관리 - 리스트</title>
	 <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
	 <link type="text/css" rel="stylesheet" href="/css/admin/admin_list.css"/>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
       </jsp:include>
	
	    <div class="wrapper">
            <div class="admin-title">신고 관리</div>
            
            <div class="container">
                <div class="list-tbl grid-6">
                    
                    <div class="list-row grid-header">
                        <div class="cell">신고 회원</div>
                        <div class="cell">신고 제목</div>
                        <div class="cell">카테고리</div>
                        <div class="cell">신고 일시</div>
                        <div class="cell">처리 여부</div>
                        <div class="cell">처리 일시</div>
                    </div>
                    
                    <c:choose>
                    <c:when test="${not empty reportList}">
                    <c:forEach items="${reportList}" var="reportInfo">
                        <div class="list-row">
                            <div class="cell">${reportInfo.logId}</div>
                            <div class="cell">
                                <a href="/admin/report_detail/${reportInfo.rptId}">
                                    <c:if test="${reportInfo.rptFlGrpId != null and 
                                                  reportInfo.rptFlGrpId != ''}">
                                        &#128196;
                                    </c:if>
                                        ${reportInfo.rptTitle}
                                </a>
                            </div>
                            <div class="cell">${reportInfo.cdNm}</div>
                            <div class="cell">${reportInfo.crtDt}</div>
                            <div class="cell">${reportInfo.prcnYn}</div>
                            <div class="cell">${reportInfo.prcnDt}</div>
                        </div>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="list-row" style="grid-template-columns: 1fr;">
                            <div class="cell no-data" style="padding: 20px; grid-column: 1 / -1;">
                                등록된 신고 내역이 존재하지 않습니다.
                            </div>
                        </div>
                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
	</body>
</html>