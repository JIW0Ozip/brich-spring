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
<div class=chat-wrapper>
	<div class="header-title">신고</div>
	<div class="chat-main">
		<div class="content-box">
			<div class="report-input">
				<label class="report-title">신고 제목</label> <input
					class="report-input-tag ttl view-detail" id="rptTitle"
					name="rptTitle" value="${report.rptTitle}" disabled>
			</div>

			<div class="report-input">
				<label class="report-title">신고 대상</label>
				<c:choose>
					<c:when test="${sessionScope.__LOGIN_USER__.autr eq 1004}">
						<input class="report-input-tag view-detail" id="rptedUsrId"
							name="rptedUsrId" value="${report.rptedUsrNm}" disabled>
					</c:when>
					<c:otherwise>
						<input class="report-input-tag view-detail" id="rptedUsrId"
							name="rptedUsrId" value="${report.rptedUsrCmpny}" disabled>
					</c:otherwise>
				</c:choose>
				<input type="hidden" id="rptedUsrIdHidden" name="rptedUsrId"
					value="${report.rptedUsrId}">
			</div>

			<div class="report-input">
				<label class="report-title">신고 사유</label> <select id="rptRsn"
					name="rptRsn" disabled>
					<option value="">${report.rptRsnNm}</option>
				</select>
			</div>

			<div class="report-input">
				<label class="report-title">신고 내용</label>
				<textarea class="report-input-tag text view-detail" id="rptCn"
					name="rptCn" disabled>${report.rptCn}</textarea>
			</div>

			<div class="report-input">
				<label class="report-title">첨부한 자료 </label>
				<c:choose>
					<c:when test="${report.fileList ne null}">
						<c:forEach items="${report.fileList}" var="fileItem">
							<a class="report-file"
								href="/file/${report.rptId}/${fileItem.flGrpId}/${fileItem.flId}">${fileItem.flNm}</a>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="no-report-file">없음</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="btn-flex">
				<div class="btn-back">신고 내역으로</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>