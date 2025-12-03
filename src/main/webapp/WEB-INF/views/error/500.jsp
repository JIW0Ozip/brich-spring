<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
	   <title>Insert title here</title>
	   <link type="text/css" rel="stylesheet" href="/css/hello-spring.css" />
	</head>
	<body>
	    <div class="Wrapper">
	       <h1 class="page-title">에러가 발생했습니다.</h1>
	       ${errorMessage}
	       <div>잠시 후 다시 시도해주세요.</div>
	    </div>
	</body>
</html>