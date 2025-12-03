<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/common/login.js"></script>
<script type="text/javascript" src="/js/common/validate.js"></script>
</head>
<body class="login-body">
    <div class="login-wrapper">
	    <div class="login-area">
		    <form method="post" id="login">
		        <div class="login-input-box">
		            <img src="/img/user.png"/>
			        <label for="logId" class="require-login"></label>
			        <input type="text" id="logId" class="login-input" name="logId" placeholder="USERNAME">
		        </div>
		        <div class="login-input-box">
		            <img src="/img/lock.png"/>
			        <label for="password" class="require-login"></label>
			        <input type="password" class="login-input"  id="password" name="pswrd" placeholder="PASSWORD">
		        </div>
		        <div class="login-error-message">${errorMessage}</div>
		         <button type="button" class="login-button">SIGN IN</button>
		    </form>
		    <a href="/find/id" class="login-page-btn">로그인 정보를 잊으셨나요?</a>
		    <button class="signup-btn"><a href="/choose-role" class="login-button">SIGN UP</a></button>
		    <a href="/campaignmain" class="login-page-btn">비회원으로 둘러보기</a>
        </div>
    </div>
</body>
</html>