<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
<script type="text/javascript" src="/js/common/validate.js"></script>
</head>
<body>
<div class="regist-wrapper find-wrapper">
        <div class="find-header">
            Brich
        </div>
        <span class="email-not-check">${checked}</span>
        <div class="find-id-main regist-main">
            <form method="post" action="/reset/password" class="user-regist-form reset">
                    <div class="right-flex">
                        <div class="input-flex"> 
                            <label for="id" class="require">아이디</label>
                            <input type="text" id="id" name="logId" class="logId" placeholder="아이디를 입력해주세요"/>
                        </div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex">
                            <label for="email" class="require" >이메일</label>
                            <input type="text" id="email" name="eml" value="${registData.eml}" placeholder="이메일을 입력해주세요"/>
                        </div>
                        <div class="regist-side-btn email-send find"><div>인증 번호</div></div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex">
                           <label for="email-confirm">인증번호 입력</label>
                           <input type="text" id="email-confirm" placeholder="인증번호를 입력하세요"/>
                        </div>
                        <div class="regist-side-btn email-verify find"><div>인증 확인</div></div>   
                    </div>    
                    <span class="email-check-timer"></span>
                    <span class="email-confirm-message"></span>
                    <div class="input-flex">
                        <label for="password" class="require">새비밀번호</label>
                        <input type="password" id="password" name="pswrd" placeholder="8~16자리 비밀번호 입력" maxlength="16"/>
                        <input type="password" id="password-confirm" name="pswrdConfirm" placeholder="비밀번호 확인"/> 
                    </div>
                    <button type="button" class="do-reset-btn">비밀번호 재설정</button>
                </form>
        </div>
    </div>
</body>
</html>