<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="campaign-main-list-area">
<c:forEach items="${campaignList}" var="campaignList">
    <div class="campaign-main-block" data-cmpn-id="${campaignList.cmpnId}">
        <div class="campaign-thumbnail">
	        <c:if test="${not empty campaignList.fileVoList[0].flPth}" > 
	            <img class = "cmpn-image" src=" /file/1234/${campaignList.flGrpId}/${campaignList.fileVoList[0].flId}"/> 
	        </c:if>
	        <c:if test="${empty campaignList.fileVoList[0].flPth}" > 
	           <img class = "cmpn-image" src=" /img/logo.png"/>
	        </c:if>
        </div>
        <div class="campaign-one-title padding-10px">
	        <c:if test="${not empty campaignList.parentArea}">
	            [${campaignList.parentArea}]
	        </c:if>
	        ${campaignList.cmpnTitle}
        </div>

      <div class="campaign-one-offrcn padding-10px">
        ${campaignList.offrCn}
      </div>

      <div class="campaign-one-adptcnt padding-10px flex-row flex-space-between">
          <c:if test="${campaignList.sttsCd != '2001' && campaignList.sttsCd != '2002' 
                       && campaignList.sttsCd != '2003' && campaignList.sttsCd != '2004'
                       && campaignList.sttsCd != '2008'}">
          <div>신청 ${campaignList.adptCnt} / ${campaignList.rcrtPrsnn}</div>
          </c:if>
          <div class="campaign-now-status-${campaignList.sttsCd} campaign-status">
              <c:choose>
	              <c:when test="${campaignList.sttsCd == 2001}">검토 중</c:when>
	              <c:when test="${campaignList.sttsCd == 2002}">승인됨</c:when>
	              <c:when test="${campaignList.sttsCd == 2003}">반려됨</c:when>
	              <c:when test="${campaignList.sttsCd == 2004}">시작 대기</c:when>
	              <c:when test="${campaignList.sttsCd == 2005}">모집 중</c:when>
	              <c:when test="${campaignList.sttsCd == 2006}">선정 중</c:when>
	              <c:when test="${campaignList.sttsCd == 2007}">진행 중</c:when>
                  <c:when test="${campaignList.sttsCd == 2009}">종료</c:when>
              </c:choose>
          </div>
      </div>
    </div>
</c:forEach>
</div>