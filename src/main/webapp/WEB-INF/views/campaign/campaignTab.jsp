<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-tab">
    <input type="radio" name="campaign-tab" id="campaign-name" data-cmpn-id="${param.cmpnId}">
    <label class="campaign-tab-name" for="campaign-name">${param.cmpnTitle}</label>
    <c:choose>
     <c:when test="${param.sttsCd eq '2007' || param.sttsCd eq '2008' || param.sttsCd eq '2009'}" >
        <input type="radio" name="campaign-tab" id="campaign-adopt">
        <label for="campaign-adopt">채택</label>
     </c:when>
     <c:otherwise>
        <input type="radio" name="campaign-tab" id="campaign-applicant">
        <label for="campaign-applicant">신청자</label>
     </c:otherwise>
    </c:choose>
    <input type="radio" name="campaign-tab" id="campaign-return-hist">
    <label for="campaign-return-hist">반려 기록</label>
</div>