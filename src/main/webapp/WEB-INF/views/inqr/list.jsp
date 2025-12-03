<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
    <script type='text/javascript' src='/js/chat/chat.js'></script>
    <script type='text/javascript' src='/js/report/report.js'></script>
    <script type='text/javascript' src='/js/common/validate.js'></script>
    <script type='text/javascript' src='/js/common/paginator.js'></script>
    <script type='text/javascript' src='/js/help/help.js'></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='chatCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
    <jsp:param name='helpCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/help/help.css' />
    " />
     <jsp:param name='accountCss' value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
    <jsp:param name='reportCss'
        value="
        <link type='text/css' rel='stylesheet' href='/css/report/report.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>
<div class="report-list-wrapper">
    <div class="header-title">HELP</div>
    <div class="campaign-tab">
        <div class="account-menu-box help">
            <div class="account-top-menu inqr-list point">1대1 문의 내역</div>
            <div class="account-top-menu inqr-write">1대1 문의하기</div>
            <div class="account-top-menu faq-list">자주 찾는 도움말</div>
        </div>
    </div>
    <div class="list-head inqr">
        <div class="f1 inqr">접수일</div>
        <div class="f2 inqr">문의 제목</div>
        <div class="f3 inqr">답변 여부</div>
        <div class="f4 inqr">문의 내용 보기</div>
    </div>
    
    <c:choose>
        <c:when test="${empty inqrList}">
            <div class="list-item white">
                <div style="text-align: center; padding: 20px; width: 100%;">
                   문의 내역이 없습니다.
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${inqrList}" var="inqr">
                <div class="list-item white inqr">
                    <div class="f1 inqr">${inqr.crtDt}</div>
                    <div class="f2 inqr">${inqr.inqrTitle}</div>
                    <div class="f3 inqr">
                        <c:choose>
                            <c:when test="${inqr.ansrUsrId ne null}">
                                <div class="complete">완료</div>
                            </c:when>
                            <c:otherwise>
                                <div class="not-complete">미완료</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                   <div class="f4 inqr detail" data-inqr-id="${inqr.inqrId}"><span>내용 보기</span></div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    
    <c:if test="${not empty inqrList and inqrSearchVO.pageCount > 1}">
        <div class="paginator-wrapper">
            <jsp:include page="/WEB-INF/views/layout/paginator.jsp">
                <jsp:param name="pageNo" value="${inqrSearchVO.pageNo}" />
                <jsp:param name="pageCount" value="${inqrSearchVO.pageCount}" />
                <jsp:param name="pageCountInGroup" value="${inqrSearchVO.pageCountInGroup}" />
                <jsp:param name="groupCount" value="${inqrSearchVO.groupCount}" />
                <jsp:param name="groupNo" value="${inqrSearchVO.groupNo}" />
                <jsp:param name="groupStartPageNo" value="${inqrSearchVO.groupStartPageNo}" />
                <jsp:param name="groupEndPageNo" value="${inqrSearchVO.groupEndPageNo}" />
                <jsp:param name="haveNextPageGroup" value="${inqrSearchVO.haveNextPageGroup}" />
                <jsp:param name="havePrevPageGroup" value="${inqrSearchVO.havePrevPageGroup}" />
                <jsp:param name="nextGroupStartPageNo" value="${inqrSearchVO.nextGroupStartPageNo}" />
                <jsp:param name="prevGroupStartPageNo" value="${inqrSearchVO.prevGroupStartPageNo}" />
            </jsp:include>
        </div>
    </c:if>
</div>
</body>
</html>