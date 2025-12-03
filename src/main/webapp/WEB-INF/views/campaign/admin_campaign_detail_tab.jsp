<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-tab">
        <input type="radio" name="campaign-tab" id="campaign-detail" data-cmpn-id="${param.cmpnId}">
        <label class="campaign-tab-detail" for="campaign-detail">캠페인</label>
        
        <c:if test="${not empty param.sttsCd && 
                       (param.sttsCd eq '2005' || 
                        param.sttsCd eq '2006' || 
                        param.sttsCd eq '2007' || 
                        param.sttsCd eq '2009')}" >
            <input type="radio" name="campaign-tab" id="campaign-applicant">
            <label for="campaign-applicant">신청자</label>
        </c:if>
        
        <c:if test="${not empty param.sttsCd && 
                       (param.sttsCd eq '2007' || 
                       param.sttsCd eq '2009')}">
            <input type="radio" name="campaign-tab" id="campaign-adopters">
            <label for="campaign-adopters">채택자</label>
        </c:if>
</div>