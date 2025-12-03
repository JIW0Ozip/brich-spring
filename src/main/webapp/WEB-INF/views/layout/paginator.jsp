<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<ul class="paginator" data-search-form-class=".board-search-form">
    <c:if test="${param.havePrevPageGroup}">
       <li>
            <a data-page-no="0">
                처음
            </a>
        </li>
        <li>
            <a data-page-no = "${param.prevGroupStartPageNo}">
                이전
            </a>
        </li>
    </c:if>
    <c:forEach begin="${param.groupStartPageNo}"
               end="${param.groupEndPageNo}"
               step="1"
               var="page">
    <li class="${param.pageNo eq page ? "active" : ""}">
        <a data-page-no="${page}">
            ${page + 1}
        </a>
    </li>
    </c:forEach>
    <c:if test="${param.haveNextPageGroup}">
        <li>
            <a data-page-no="${param.nextGroupStartPageNo}">
                다음
            </a>
        </li>
        <li>
            <a data-page-no="${param.pageCount-1}">
                마지막
            </a>
        </li>
    </c:if>
</ul>