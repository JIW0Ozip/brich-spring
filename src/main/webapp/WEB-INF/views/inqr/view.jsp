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
    
    <div class="chat-main inqr-page">
        <div class="content-box inqr-page">
            <div class="report-input">
                <label class="report-title inqr-page">문의 제목 <span
                    class="require-mark ctg">${inqr.inqrCtgNm}</span></label> 
                <input class="report-input-tag ttl" id="inqrTitle" name="rptTitle" value="${inqr.inqrTitle}" disabled>
            </div>
            
            <div class="report-input">
                <label class="report-title">문의 내용</label>
                <textarea class="report-input-tag answer text" id="inqrCn" name="rptCn" disabled>${inqr.inqrCn}</textarea>
            </div>

            <div class="report-input">
                <label class="report-title">첨부한 자료</label>
                <c:choose>
                    <c:when test="${inqr.myFile ne null}">
                        <c:forEach items="${inqr.myFile}" var="fileItem">
                            <a class="report-file" href="/file/${sessionScope.__LOGIN_USER__.usrId}/${fileItem.flGrpId}/${fileItem.flId}">${fileItem.flNm}</a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-report-file">없음</div>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <hr class="seperate">
            
            <div class="report-input">
                <label class="report-title">답변 내용</label>
                <c:choose>
                    <c:when test="${inqr.ansrCn ne null}">
                        <textarea class="report-input-tag answer text" id="ansrCn" name="ansrCn" disabled>${inqr.ansrCn}</textarea>
                    </c:when>
                    <c:otherwise>
                        <textarea class="report-input-tag answer text" id="ansrCn" name="ansrCn" disabled>답변이 없습니다.</textarea>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <c:if test="${inqr.ansrCn ne null}">
                <div class="report-input">
                    <label class="report-title">답변 첨부 자료</label>
                    <c:choose>
                        <c:when test="${inqr.answerFile ne null}">
                            <c:forEach items="${inqr.answerFile}" var="fileItem">
                                <a class="report-file" href="/file/${sessionScope.__LOGIN_USER__.usrId}/${fileItem.flGrpId}/${fileItem.flId}">${fileItem.flNm}</a>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="no-report-file">없음</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
            
            <div class="btn-flex">
                <div class="btn-back">문의 내역으로</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>