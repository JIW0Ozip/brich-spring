<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='chatCss'
		value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
    " />
	<jsp:param name="chatscripts"
		value="
        <script type='text/javascript' src='/js/chat/chat.js'></script>
    " />
</jsp:include>
<div class="chat-wrapper">
	<div class="header-title">메세지</div>
	<div class="chat-main">
		<div class="content-box">
			<div class="content-title">캠페인 목록</div>
			<div class="progress-check">
				<div class="progress all">전체</div>
				<div class="progress on">진행중</div>
				<div class="progress end">종료된</div>
			</div>
			<div class="content-list"></div>
			<div class="page-list">1 2 3 4 5</div>
		</div>
	</div>
</div>
</div>
</div>
<template id="chat-campaign-list">
	<div class="campaign-content-item" data-campaign-id="#campaign#">
		<div class="campaign-item">
			<div class="campaign-title-c">#campaigntitle#</div>
			<div class="campaign-status end">#campaignstatus#</div>
		</div>
		<!--추후 이동할 엔드포인트 삽입-->
		<a href="/campaigndetail/#campaignid#" class="campaign-link">캠페인으로</a>
	</div>
</template>
</body>
</html>