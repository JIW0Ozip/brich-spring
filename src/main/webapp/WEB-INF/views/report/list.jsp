<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
    <script type='text/javascript' src='/js/chat/chat.js'></script>
    <script type='text/javascript' src='/js/report/report.js'></script>
    <script type='text/javascript' src='/js/common/validate.js'></script>
    <script type='text/javascript' src='/js/common/paginator.js'></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='chatCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
    <jsp:param name='reportCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/report/report.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>
<div class="report-list-wrapper">
    <div class="header-title">신고</div>

    <div class="list-head">
        <div class="f1">접수일</div>
        <div class="f2">신고 제목</div>
        <div class="f3">신고 대상</div>
        <div class="f4">처리 여부</div>
        <div class="f5">신고 내용 보기</div>    
    </div>
    
    <c:choose>
        <c:when test="${empty reportList}">
            <div class="list-item white">
                <div style="text-align: center; padding: 20px; width: 100%;">
                    신고 내역이 없습니다.
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${reportList}" var="report">
                <div class="list-item white">
                    <div class="f1">${report.crtDt}</div>
                    <div class="f2">${report.rptTitle}</div>
                    <div class="f3">
                        <c:choose>
                            <c:when test="${sessionScope.__LOGIN_USER__.autr eq 1004}">
                                ${report.nm}
                            </c:when>
                            <c:otherwise>
                                ${report.cmpny}
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="f4">
                        <c:choose>
                            <c:when test="${report.prcnYn eq 'Y'}">
                                <div class="complete">완료</div>
                            </c:when>
                            <c:otherwise>
                                <div class="not-complete">미완료</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="f5 detail" data-report-id="${report.rptId}"><span>내용 보기</span></div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
   
    <c:if test="${not empty reportList and reportSearchVO.pageCount > 1}">
        <div class="paginator-wrapper">
            <jsp:include page="/WEB-INF/views/layout/paginator.jsp">
                <jsp:param name="pageNo" value="${reportSearchVO.pageNo}" />
                <jsp:param name="pageCount" value="${reportSearchVO.pageCount}" />
                <jsp:param name="pageCountInGroup" value="${reportSearchVO.pageCountInGroup}" />
                <jsp:param name="groupCount" value="${reportSearchVO.groupCount}" />
                <jsp:param name="groupNo" value="${reportSearchVO.groupNo}" />
                <jsp:param name="groupStartPageNo" value="${reportSearchVO.groupStartPageNo}" />
                <jsp:param name="groupEndPageNo" value="${reportSearchVO.groupEndPageNo}" />
                <jsp:param name="haveNextPageGroup" value="${reportSearchVO.haveNextPageGroup}" />
                <jsp:param name="havePrevPageGroup" value="${reportSearchVO.havePrevPageGroup}" />
                <jsp:param name="nextGroupStartPageNo" value="${reportSearchVO.nextGroupStartPageNo}" />
                <jsp:param name="prevGroupStartPageNo" value="${reportSearchVO.prevGroupStartPageNo}" />
            </jsp:include>
        </div>
    </c:if>
 
</div>
</body>
</html>
