<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-block" data-cmpn-id="${param.cmpnId}">
    <c:choose>
        <c:when test="${not empty param.flId}">
            <img class="cmpnjij" src="/file/${param.usrId}/${param.flGrpId}/${param.flId}" />
        </c:when>
        <c:otherwise>
            <img src="/img/logo.png" />
        </c:otherwise>
    </c:choose>
    <div class="flex-grow-1">
        <div>${param.cmpnTitle}</div>
        <c:choose>
            <c:when test="${param.cmpnCdNm eq '승인'}">
		        <div class="enddate">결제 마감일 : ${param.cnfmDt}</div>
		        </div>
		        <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '모집중'}">
		        <div class="enddate">모집 마감일 : ${param.rcrtEndDt}</div>
		        </div>
		        <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '선정중'}">
                <div class="enddate">캠페인 시작일 : ${param.rcrtStrtDt}</div>
	            </div>
	            <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '진행중'}">
                <div class="enddate">캠페인 종료일 : ${param.cmpnEndDt}</div>
                </div>
                <div class="font-green">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '시작대기' || param.cmpnCdNm eq '검토중'}">
                <div class="enddate">캠페인 시작일 : ${param.rcrtStrtDt}</div>
                </div>
                <div class="font-brown">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '임시저장'}">
                </div>
                <div class="font-brown">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '반려'}">
                </div>
                <div class="font-red">${param.cmpnCdNm}</div>
            </c:when>
            
            <c:when test="${param.cmpnCdNm eq '종료'}">
                <div class="enddate">캠페인 종료일 : ${param.cmpnEndDt}</div>
                </div>
                <div>${param.cmpnCdNm}</div>
            </c:when>
        </c:choose>
</div>