<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<c:set var="scripts">
    <script type="text/javascript" src="/js/common/paginator.js"></script>
    <script type="text/javascript" src="/js/common/validate.js"></script>
    <script type="text/javascript" src="/js/campaign/list.js"></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

<div class="campaign-wrapper">
    <c:if test="${campaignList.isDeny eq true}">
        <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp">
           <jsp:param value="${campaignList.campaignInfo.cmpnId}" name="cmpnId" />
           <jsp:param value="${campaignList.campaignInfo.cmpnTitle}" name="cmpnTitle" />
           <jsp:param value="${campaignList.campaignInfo.sttsCd}" name="sttsCd" />
        </jsp:include>
    </c:if>
    <div class="campaign-content-wrapper">
        <c:choose>
	        <c:when test="${campaignList.isDeny eq true}">
	            <div class="campaign-title">반려 기록</div>
	        </c:when>
	        <c:otherwise>
	            <div class="campaign-title">내 캠페인</div>
	            <div class="search-container">
            <div class="id-search">
               <input type="text" placeholder="검색어를 입력하세요." />
               <img src="/img/Search.png" />
            </div>
            <div>
               <!-- <button type="button" name="filter" class="button_50_30">Filter</button>  -->
               <select name="filter" id="campaign-filter" class="button_50_30">
                   <option value="">전체 보기</option>
                   <option value="2001">검토중</option>
                   <option value="2002">승인됨</option>
                   <option value="2003">반려</option>
                   <option value="2004">시작 대기</option>
                   <option value="2005">모집중</option>
                   <option value="2006">선정중</option>
                   <option value="2007">진행중</option>
                   <option value="2008">임시 저장</option>
                   <option value="2009">종료</option>
                   
               </select>
            </div>
        </div>
	        </c:otherwise>
        </c:choose>
	        
        
        <c:choose>
            <c:when test="${empty campaignList.responseCampaignList}">
                <div class="empty-space">작성한 캠페인이 없습니다!</div>
            </c:when>
            <c:otherwise>
                <div class="campaign-list-content">
		            <c:forEach items="${campaignList.responseCampaignList}" var="campaign">
		                <jsp:include page="/WEB-INF/views/campaign/campaignBlock.jsp">
		                    <jsp:param value="${campaign.cmpnId}" name="cmpnId"/>
		                    <jsp:param value="${campaign.cmpnTitle}" name="cmpnTitle"/>
		                    <jsp:param value="${campaign.sttsCdNm}" name="cmpnCdNm"/>
		                    <jsp:param value="${campaign.cnfmDt}" name="cnfmDt"/>
		                    <jsp:param value="${campaign.rcrtEndDt}" name="rcrtEndDt"/>
		                    <jsp:param value="${campaign.cmpnEndDt}" name="cmpnEndDt"/>
		                    <jsp:param value="${campaign.rcrtStrtDt}" name="rcrtStrtDt"/>
		                    <jsp:param value="${campaign.usrId}" name="usrId"/>
		                    <jsp:param value="${campaign.attchGrpId}" name="flGrpId"/>
		                    <jsp:param value="${campaign.fileVoList[0].flId}" name="flId"/>
		                </jsp:include>
		            </c:forEach>
		        </div>
            </c:otherwise>
        </c:choose>
        
            
            <c:choose>
                <c:when test="${campaignList.isDeny eq false}">
		            <jsp:include page="/WEB-INF/views/layout/paginator.jsp">
		                <jsp:param value="${search.listSize}" name="listSize"/>
		                <jsp:param value="${search.havePrevPageGroup}" name="havePrevPageGroup"/>
		                <jsp:param value="${search.prevGroupStartPageNo}" name="prevGroupStartPageNo"/>
		                <jsp:param value="${search.groupStartPageNo}" name="groupStartPageNo"/>
		                <jsp:param value="${search.groupEndPageNo}" name="groupEndPageNo"/>
		                <jsp:param value="${search.pageNo}" name="pageNo"/>
		                <jsp:param value="${search.haveNextPageGroup}" name="haveNextPageGroup"/>
		                <jsp:param value="${search.nextGroupStartPageNo}" name="nextGroupStartPageNo"/>
		                <jsp:param value="${search.pageCount}" name="pageCount"/>
		            </jsp:include>
		        <a id="create-campaign" href="/adv/campaign/write">
		            <div>+</div>
		        </a>
            </c:when>
        </c:choose>
    </div>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />