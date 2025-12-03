<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
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
            <form class="user-regist-form">
                    <div class="input-flex">
                        <label for="name" class="require">이름</label>   
                        <input type="text" id="name" name="nm" placeholder="이름을 입력해주세요" value="${registData.nm}"/>
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
                    <div class="email-check-timer"></div>
                    <div class="email-confirm-message"></div>
                    <button type="button" class="find-btn">아이디 찾기</button>
                </form>
        </div>
    </div>
</body>
</html>