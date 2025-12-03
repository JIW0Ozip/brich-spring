<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <jsp:include page="/WEB-INF/views/session/session.jsp"/>
    <c:choose>
    <c:when test="${sessionScope.__LOGIN_USER__.autr eq '1001'}">
        관리자
    </c:when>
    <c:when test="${sessionScope.__LOGIN_USER__.autr eq '1002' or sessionScope.__LOGIN_USER__.autr eq '1003'}">
        블로거
    </c:when>
    <c:when test="${sessionScope.__LOGIN_USER__.autr eq '1004'}">
        광고주
    </c:when>
</c:choose>
    <a href="/${auth}/account/subscription-info">구독정보</a>
    <a href="/logout">로그아웃</a>

</body>
</html>