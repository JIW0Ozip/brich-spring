<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
     <jsp:param name='accountCss' value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
    <jsp:param name="accountJs"
        value="
        <script type='text/javascript' src='/js/account/account.js'></script>
    " />
</jsp:include>

<fmt:parseDate value="${subInfo.expireDate}" 
               pattern="yyyy.MM.dd" var="exprsDt" />
<jsp:useBean id="now" class="java.util.Date" />
<div class="account-wrapper">
    <div class="campaign-tab">
        <div class="account-menu-box" data-auth="${sessionScope.__LOGIN_USER__.autr}">
            <div class="account-top-menu account-info">계정 정보</div>
            <div class="account-top-menu reset-password">비밀번호 재설정</div>
            <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004}">
                <div class="account-top-menu sub-info point">구독 정보</div>
            </c:if>
        </div>
    </div>
    <c:choose>
        <c:when test="${sessionScope.__LOGIN_USER__.autr eq 1003 or exprsDt lt now}">
            <div class="no-sub-wrapper">
                <div class="sub-alert">현재 이용중인 이용권이 없습니다.</div>
                <a href="/blgr/pay/subscribe"><div class="go-sub-page">이용권 구매로 이동</div></a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="sub-wrapper">
                <div class="current-sub">현재 이용중인 이용권</div>
                <div class="sub-info-box">
                    <div class="">블로그 관리 ${subInfo.cdNm} 이용권</div>
                   <!--  보여줄 데이터는 상태랑 만료일 매칭 시킬 필요가 있음 
                    만료일이 지났음에도 구독중 상태라서 보이는 현상있음  -->
                    <div class="sub-info-bottom">
                        <div class="expire-date">만료일 : ${subInfo.expireDate}</div>
                        <a href="/blgr/pay/subscribe"> <div class="go-expand-page">연장하기</div> </a> 
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    
 </div>
</div>
</div>
</body>
</html>