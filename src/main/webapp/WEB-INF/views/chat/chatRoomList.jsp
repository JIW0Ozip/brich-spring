<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='chatCss'
		value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
        <link type='text/css' rel='stylesheet' href='/css/blog/verification.css' />
    " />
	<jsp:param name="chatroomListscripts"
		value="
        <script type='text/javascript' src='/js/chat/chatRoomList.js'></script>
    " />
	<jsp:param name="scripts"
		value="
        <script type='text/javascript' src='/js/blog/verification.js'></script>
        <script type='text/javascript' src='/js/common/socketconnect.js'></script>
    " />
    <jsp:param name="sockjs"
        value='<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.6.1/dist/sockjs.min.js"></script>' />
    <jsp:param name="stompjs"
        value='<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>' />
    

</jsp:include>
<div class="chat-wrapper">
	<div class="header-title">메세지</div>
	<div class="chat-main" data-cmpn="${cmpnId}">
		<div class="content-box"
			data-auth="${sessionScope.__LOGIN_USER__.autr}"
			data-chtid-list="${allChtRmId}">
			<c:if test="${sessionScope.__LOGIN_USER__.autr eq 1004 }">
	           <img src="/img/arrow-left.png" class="arrow-back"/>
	        </c:if>
			<div class="content-title">채팅방 목록</div>
			<div class="progress-check">
				<div class="progress readall">전체</div>
				<div class="progress not">안읽음</div>
			</div>
			<div class="content-list"></div>
			<div class="page-list"></div>
		</div>
	</div>
</div>
<div class="modal">
    <div class="modal-content-leave" data-cht-rm="">
        <div class="leave-check">
            <div class="m1">채팅방에서 나가시겠습니까?</div>
            <div class="m2">채팅방을 되돌릴 수 없습니다.</div>
            <div class="modal-btn-box">
                <div class="cancel-btn">취소</div>
                <div class="confirm-btn">나가기</div>
            </div>
        </div>
    </div>
</div>

</div>
</div>
<template id="chat-room-list">
	<div class="chatroom-content-item" 
	     data-chat-room="#chatroomid#"
	     data-target-id="#targetid#">
		<div class="chatroom-info">
			<div class="chatroom-campaign-title">
				<div class="campaign-title camp">#campaigntitle#</div>
				<div class="campaign-title bloger-name">#blogername#</div>
				<div class="campaign-status">#campaignstatus#</div>
			</div>
			<div class="last-chat-message">#lastmessage#</div>
		</div>
		<div class="chatroom-extra-info">
			<div class="chat-latest-info">
				<div class="latest-time">#latesttime#</div>
				<div class="unread-count">#unreadcount#</div>
			</div>
			<img src="/img/more-horizontal.png" class="chat-leave-btn">
			<div class="report-btn">신고하기</div>
			<div class="leave-chat-btn">채팅방 나가기</div>
		</div>
	</div>
</template>
</body>
</html>
