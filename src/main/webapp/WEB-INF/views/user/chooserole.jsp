<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 유형 선택</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
</head>
<body>
    <div class="choose-wrapper">
        <div class="choose-message">회원가입 유형 선택</div>
        <div class="choose-box">
            <div class="role-box" data-role="blogger">
                <img src="img/blogger.png" class="role-image"/>
                <div class="role">블로거 (개인)</div>
                <ul>
                    <li>내 블로그 순위 관리</li>
                    <li>진행 중인 캠페인 추천</li>
                    <li>광고주와 손쉬운 연락</li>
                    <li>오늘의 블로그 추천 키워드</li>
                </ul>
                <div class="go-regist">가입하기</div>
            </div>
            <div class="role-box" data-role="advertiser">
                <img src="img/advertiser.png" class="role-image"/>
                <div class="role">광고주 (업체)</div>
                <ul>
                    <li>체계적인 광고 진행</li>
                    <li>내 가게/제품 홍보</li>
                    <li>블로거와의 직접적인 연락</li>
                    <li>내 광고 관련 블로그 포스팅</li>
                </ul>
                <div class="go-regist">가입하기</div>
            </div>
        </div>
    </div>
    
</body>
</html>