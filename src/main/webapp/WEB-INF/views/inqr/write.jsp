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
	<jsp:param name='accountCss'
		value="
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
			<div class="account-top-menu inqr-list">1대1 문의 내역</div>
			<div class="account-top-menu inqr-write point">1대1 문의하기</div>
			<div class="account-top-menu faq-list">자주 찾는 도움말</div>
		</div>
	</div>
	<div class="chat-main inqr-page">
		<div class="content-box inqr-page">
			<div class="report-input">
				<label class="report-title inqr-page">문의 제목 <span
					class="require-mark">*</span></label> <input class="report-input-tag ttl"
					id="inqrTitle" name="rptTitle">
			</div>
			<input type="hidden" id="InqrUsrIdHidden" name="rptedUsrId"
				value="${sessionScope.__LOGIN_USER__.usrId}">
			<div class="report-input">
				<label class="report-title">문의 유형 <span class="require-mark">*</span></label>
				<select id="inqrCtg" name="rptRsn">
					<option value="">선택</option>
					<c:forEach items="${category}" var="category">
						<option value="${category.cdId}">${category.cdNm}</option>
					</c:forEach>
				</select>
			</div>

			<div class="report-input">
				<label class="report-title">문의 내용 <span class="require-mark">*</span></label>
				<textarea class="report-input-tag text" id="inqrCn" name="rptCn"></textarea>
			</div>

			<div class="report-input">
				<label class="report-title">자료 첨부 </label> <input type="file"
					id="inqrFiles" class="fileInput" name="file" multiple>
				<div class="fileList"></div>
			</div>
			<div class="btn-flex">
				<div class="btn-submit">제출하기</div>
			</div>
		</div>

	</div>
</div>
</body>
</html>