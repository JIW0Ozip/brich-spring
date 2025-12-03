<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
	<jsp:param name='chatCss'
		value="
        <link type='text/css' rel='stylesheet' href='/css/chat/chat.css' />
        <link type='text/css' rel='stylesheet' href='/css/blog/verification.css' />
        
    " />
    
	<jsp:param name='chatroomscripts'
		value="
        <script type='text/javascript' src='/js/chat/chatRoom.js'></script>
    " />
    <jsp:param name="chatroomListscripts"
        value="
        <script type='text/javascript' src='/js/chat/chatRoomList.js'></script>
    " />
    <jsp:param name="scripts"
        value="
        <script type='text/javascript' src='/js/blog/verification.js'></script>
    " />
	<jsp:param name="sockjs"
		value='<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.6.1/dist/sockjs.min.js"></script>' />
	<jsp:param name="stompjs"
		value='<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>' />
</jsp:include>
<div class="chat-wrapper">
	<div class="header-title">메세지</div>
	<div class="chat-main" data-chtrm-id="${chtRmId}">
		<div class="content-box"
			data-auth="${sessionScope.__LOGIN_USER__.autr}"
			data-usr-id="${sessionScope.__LOGIN_USER__.usrId}"
			data-nm = "${sessionScope.__LOGIN_USER__.nm}"
			data-cmpny = "${sessionScope.__LOGIN_USER__.cmpny}"
			data-target-Id="${campaign.usrId}">
           <img src="/img/arrow-left.png" class="arrow-back"/>
			<div class="content-title">
				<div class="content-title-text"
				     data-cmpn-id="${campaign.cmpnId}">${campaign.cmpnTitle}</div>
				<div class="chatroom-extra">
					<img src="/img/more-horizontal.png" class="chat-leave-btn-rm">
					<div class="report-btn-rm">신고하기</div>
					<div class="leave-chat-btn-rm">채팅방 나가기</div>
				</div>
			</div>
			<div class="message-container">
			</div>
			<div class="chat-message-input">
				<textarea class="chat-text-input"></textarea>
				 <input type="file" id="file-input" multiple style="display: none;">
	            <img class="file-insert" src="/img/file.png">
				<button class="chat-send-btn" type="button">전송</button>
			</div>
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
</body>
</html>
