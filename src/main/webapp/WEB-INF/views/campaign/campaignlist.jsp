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
        <c:if test="${(sessionScope.__LOGIN_USER__.autr == 1002 
            or sessionScope.__LOGIN_USER__.autr == 1003)}">
         <c:if test="${not empty sessionScope.__LOGIN_USER__ }" > 
              <c:set var="love" value="${campaignList.favYn eq 'Y'}" />
              <div
                class="campaign-fav love-${campaignList.favYn}"
                data-usr-id="${sessionScope.__LOGIN_USER__.usrId}"
                data-cmpn-id="${campaignList.cmpnId}"> 
                  <div class="love-on ${love ? '' : 'display-none'}"></div>
                  <div class="love-off ${love ? 'display-none' : ''}"></div>
              </div>
           </c:if>
          </c:if>
        </div>
        <div class="campaign-one-title padding-10px">
          <c:if test="${not empty campaignList.parentArea}">
            [ ${campaignList.parentArea} ]
          </c:if>
          ${campaignList.cmpnTitle}
        </div>

        <div class="campaign-one-offrcn padding-10px">
          ${campaignList.offrCn}
        </div>

        <div class="campaign-one-adptcnt padding-10px flex-row flex-space-between">
          <c:choose>
            <c:when test="${campaignList.sttsCd == 2009}">
                <div></div>
            </c:when>
            <c:when test="${campaignList.sttsCd != 2007}">
                <div>신청자 ${campaignList.adptCnt} / 모집인원 ${campaignList.rcrtPrsnn}</div>
            </c:when>
            <c:when test="${campaignList.sttsCd == 2007}">
                <div></div>
            </c:when>
          </c:choose>

          <div class="campaign-now-status-${campaignList.sttsCd} campaign-status campaign-pst-status-${campaignList.pstSttsCd}">
			<c:choose>
			  <c:when test="${campaignList.sttsCd == 2005}">모집중</c:when>
			  <c:when test="${campaignList.sttsCd == 2006}">선정중</c:when>
			  <c:when test="${campaignList.sttsCd == 2007}">
			    <c:choose>
			      <c:when test="${campaignList.pstSttsCd == 6001}">진행중</c:when>
			      <c:when test="${campaignList.pstSttsCd == 6002}">검토중</c:when>
			      <c:when test="${campaignList.pstSttsCd == 6003}">반려됨</c:when>
			      <c:when test="${campaignList.pstSttsCd == 6004}">승인됨</c:when>
			    </c:choose>
			  </c:when>
			  <c:when test="${campaignList.sttsCd == 2009}">마감됨</c:when>
			</c:choose>
          </div>
        </div>
        
        
      </div>
    </c:forEach>
  </div>
  