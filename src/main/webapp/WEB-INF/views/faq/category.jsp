<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="faq-category">
        <div class="category-item" data-category-id="">전체</div>
        <c:forEach items="${faqList.codeList}" var="code">
            <div class="category-item" data-category-id="${code.cdId}">${code.cdNm}</div>
        </c:forEach>
</div>