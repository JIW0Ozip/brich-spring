<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="simple-paginator" data-search-form-class=".board-search-form">
  
        <button class="btn-prev" data-page-no="${param.pageNo - 1}"><img src="/img/chevron_left.png"></button>



        <button class="btn-next" data-page-no="${param.pageNo + 1}"><img src="/img/chevron_right.png"></button>

</div>



