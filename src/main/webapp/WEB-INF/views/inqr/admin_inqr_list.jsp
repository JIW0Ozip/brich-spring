<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의 관리 - 목록</title>
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
            <div class="admin-title">문의 관리</div>
            
            <div class="container">
                <!-- 💡 HTML 테이블 구조를 Grid 기반 div 구조로 변경: grid-6은 6개의 컬럼을 정의 -->
                <div class="list-tbl grid-6">
                    
                    <!-- Header Row -->
                    <div class="list-row grid-header">
                        <div class="cell">문의 회원</div>
                        <div class="cell">문의 제목</div>
                        <div class="cell">카테고리</div>
                        <div class="cell">문의 일시</div>
                        <div class="cell">답변 여부</div>
                        <div class="cell">답변 등록 일시</div>
                    </div>
                   
                    <!-- Body Rows -->
                    <c:choose>
                        <c:when test="${not empty inqrList}">
                            <c:forEach items="${inqrList}" var="inqrInfo">
                                <div class="list-row">
                                    <div class="cell">${inqrInfo.logId}</div>
                                    <div class="cell">
                                        <a href="/admin/inqr_detail/${inqrInfo.inqrId}">
                                            <c:if test="${not empty inqrInfo.inqrFlGrpId}">&#128196;</c:if>
                                            ${inqrInfo.inqrTitle}
                                        </a>
                                    </div>
                                    <div class="cell">${inqrInfo.cdNm}</div>
                                    <div class="cell">${inqrInfo.crtDt}</div>
                                    <div class="cell">${inqrInfo.ansrYn}</div>
                                    <div class="cell">${inqrInfo.ansrDt}</div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <!-- No Data Row (6개 컬럼에 걸쳐 표시) -->
                            <div class="list-row" style="grid-template-columns: 1fr;">
                                <div class="cell no-data" style="padding: 20px; grid-column: 1 / -1;">
                                    등록된 문의가 존재하지 않습니다.
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>