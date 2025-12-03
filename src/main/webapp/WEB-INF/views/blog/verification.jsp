<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    	<link type='text/css' rel='stylesheet' href='/css/blog/verification.css' />
    	
       	
   	" />
   	<jsp:param name="scripts" value="
       	<script type='text/javascript' src='/js/blog/verification.js'></script>
	" />    
</jsp:include>
		
		<div class="verification-container">
			<div class="title">블로그 인증</div>
			<div class="verification-content">
			블로그 등록이 필요합니다.
			</div>
			<div class="verification-content">
			블로그 등록을 진행해주세요.
			</div>
			<button id="verify-btn"> 등록하기</button>
			<div id="verify-modal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h3>블로그 인증</h3>
	
						<p>인증 코드를 생성하고 블로그 소개글에 추가하세요.</p>
						<button class="btn" id="generate-code">인증 코드 생성</button>
						<div id="verification-code" style="margin:10px 0;font-size:1.3em;"></div>
					<span>블로그 주소: <input id="blog-url" type="text"></span>
					<p>아래 버튼을 눌러 블로그 인증을 진행하세요.</p>
					<button id="run-verification" class="btn-green"
					data-user-id="${sessionScope.__LOGIN_USER__.usrId}">인증 시작</button>
					
					<div id="verify-result" class="result"></div>
				</div>
			</div>
			
		</div>
	</body>
</html>