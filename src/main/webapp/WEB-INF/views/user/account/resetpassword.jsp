<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='css'
		value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
	<jsp:param name='accountCss'
		value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
	<jsp:param name="accountJs"
		value="
        <script type='text/javascript' src='/js/account/account.js'></script>
    " />
</jsp:include>


<div class="account-wrapper">
	<div class="campaign-tab">
		<div class="account-menu-box"
			data-auth="${sessionScope.__LOGIN_USER__.autr}">
			<div class="account-top-menu account-info">계정 정보</div>
			<div class="account-top-menu reset-password point">비밀번호 재설정</div>
			<c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004}">
				<div class="account-top-menu sub-info">구독 정보</div>
			</c:if>
		</div>
	</div>
	<div class="account-info-wrapper">
		<div class="account-info-container">
			<div class="account-info-item">
				<div class="item-title space">현재 비밀번호</div>
				<input type="password" class="item-content current-pswrd" placeholder="현재 비밀번호를 입력해주세요.">
			</div>
			<div class="account-info-item">
                <div class="item-title space">새 비밀번호</div>
                <input type="password" class="item-content new-pswrd" placeholder="새로운 비밀번호를 입력해주세요.">
            </div>
            <div class="account-info-item">
                <div class="item-title space">새 비밀번호 확인</div>
                <input type="password" class="item-content new-pswrd-confirm" placeholder="새로운 비밀번호를 다시 입력해주세요.">
            </div>
			<div class="reset-password-confirm">확인</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>