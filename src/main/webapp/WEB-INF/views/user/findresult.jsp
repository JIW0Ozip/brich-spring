<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
</head>
<body>
<div class="regist-wrapper find-wrapper">
        <div class="find-header">
            Brich
        </div>
        <div class="find-id-main regist-main result-main">
            <form class="user-regist-form">
                    <div class="result-id">
                       아이디는 ${findedId} 입니다.
                    </div>
                    
                    <button type="button" class="reset-password-btn">비밀번호 재설정</button>
                    <button type="button" class="go-login-btn">로그인으로</button>
                    
                </form>
        </div>
    </div>
</body>
</html>