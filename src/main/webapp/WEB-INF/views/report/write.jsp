<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
	<script type='text/javascript' src='/js/chat/chat.js'></script>
	<script type='text/javascript' src='/js/report/report.js'></script>
	<script type='text/javascript' src='/js/common/validate.js'></script>
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
<div class="chat-wrapper">
	<div class="header-title">신고</div>
	<div class="chat-main">
		<div class="content-box">
			<div class="report-input">
				<label class="report-title">신고 제목 <span class="require-mark">*</span></label>
				<input class="report-input-tag ttl" id="rptTitle" name="rptTitle">
			</div>

			<div class="report-input">
				<label class="report-title">신고 대상 <span class="require-mark">
						*</span></label> <input class="report-input-tag" id="rptedUsrId"
					name="rptedUsrId" value="${reportInfo.targetName}" disabled>
				<input type="hidden" id="rptedUsrIdHidden" name="rptedUsrId"
					value="${reportInfo.targetId}">
			</div>

			<div class="report-input">
				<label class="report-title">신고 사유 <span class="require-mark">*</span></label>
				<select id="rptRsn" name="rptRsn">
					<option value="">선택</option>
					<c:forEach items="${reportInfo.rptCategory}" var="category">
						<option value="${category.cdId}">${category.cdNm}</option>
					</c:forEach>
				</select>
			</div>

			<div class="report-input">
				<label class="report-title">신고 내용 <span class="require-mark">*</span></label>
				<textarea class="report-input-tag text" id="rptCn" name="rptCn"></textarea>
			</div>

			<div class="report-input">
				<label class="report-title">자료 첨부 </label> <input type="file"
					id="reportFiles" class="fileInput" name="file" multiple>
				<div class="fileList"></div>
			</div>
			<div class="btn-flex">
				<div class="btn-back">돌아가기</div>
				<div class="btn-report">신고하기</div>
			</div>
		</div>

	</div>
</div>
</div>
</div>
</body>
</html>