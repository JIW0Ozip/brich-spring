<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>신고 관리 - 상세 정보</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type="text/javascript" src="/js/report/admin_report_penalty_process.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_detail.css"/>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
        </jsp:include>
        
        <div class="wrapper">
            <div class="admin-title">신고 상세</div>
            <div class="container">
            <input type="hidden" id="adminId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
            <input type="hidden" id="rptId" value="${reportInfo.rptId}"/>
            <input type="hidden" id="rptrUsrId" value="${reportInfo.rptrUsrId}"/>
            <input type="hidden" id="rptedUsrId" value="${reportInfo.rptedUsrId}"/>
            
            <div class="detail-info">
                
                <div class="detail-row">
                    <div class="detail-key">신고한 회원</div>
                    <div class="detail-value">${reportInfo.rptrLogId}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">신고 대상</div>
                    <div class="detail-value">${reportInfo.rptedLogId}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">신고 제목</div>
                    <div class="detail-value">${reportInfo.rptTitle}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">신고 일시</div>
                    <div class="detail-value">${reportInfo.crtDt}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">신고 사유</div>
                    <div class="detail-value">${reportInfo.cdNm}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">신고 내용</div>
                    <div class="detail-value">${reportInfo.rptCn}</div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">첨부 파일</div>
                    <div class="detail-value">
                        <c:set var="hasValidReportFile" value="false"/>
                        <c:if test="${not empty reportInfo.fileList}">
                            <c:forEach items="${reportInfo.fileList}" var="checkReportFileList">
                                <c:if test="${not empty checkReportFileList.flId}">
                                    <c:set var="hasValidReportFile" value="true"/>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        
                        <c:choose>
                            <c:when test="${hasValidReportFile}">
                            <div class="file-list-container">
                                <c:forEach items="${reportInfo.fileList}" var="reportFileList">
                                    <c:if test="${not empty reportFileList.flId}">
                                        <a href="/file/${reportInfo.rptrUsrId}/${reportInfo.rptFlGrpId}/${reportFileList.flId}">
                                            <span class="file-item">&#128196; ${reportFileList.flNm}</span>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                            </c:when>
                            
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                
                <div class="detail-row">
                    <div class="detail-key">처리 여부</div>
                    <div class="detail-value">${reportInfo.prcnYn}</div>
                </div>
                
                <c:if test="${reportInfo.prcnYn eq 'Y'}">
                <div class="detail-row last-row">
                    <div class="detail-key">처리 일시</div>
                    <div class="detail-value">${reportInfo.prcnDt}</div>
                </div>
                </c:if>
                
                <c:if test="${reportInfo.prcnYn eq 'N'}">
                <div class="detail-row last-row">
                    <div class="detail-key">처리 내용</div>
                    <div class="detail-value">미처리</div>
                </div>
                </c:if>

            </div> <!-- Grid 컨테이너 끝 -->
                
                <c:if test="${reportInfo.prcnYn eq 'N'}">
                <div class="btn-group">
                    <div class="penalty-options">
                        <label for="dismiss">기각</label>
                        <input type="radio" id="dismiss" name="penalty-option" class="option_item" value="dismiss"/>
                        
                        <label for="warning">경고</label>
                        <input type="radio" id="warning" name="penalty-option" class="option_item" value="warning"/>
                        
                        <label for="ban">정지</label>
                        <input type="radio" id="ban" name="penalty-option" class="option_item" value="ban"/>
                    </div>
                    
                    <button type="button" class="control-btn save-btn penalty-btn">처리</button>
                </div>
                </c:if>
            </div>
        </div>
	</body>
</html>