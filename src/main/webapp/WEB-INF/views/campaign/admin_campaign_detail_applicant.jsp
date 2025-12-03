<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="scripts">
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type="text/javascript" src="/js/campaign/admin_campaign_detail.js"></script>
       <script type="text/javascript" src="/js/common/paginator.js"></script>
</c:set>
    
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
   <jsp:param name='css' value="
       <link type='text/css' rel='stylesheet' href='/css/brich.css' />
   " />
   <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

    <div class="campaign-wrapper">
        <jsp:include page="/WEB-INF/views/campaign/admin_campaign_detail_tab.jsp">
           <jsp:param value="${applicantList.campaignInfo.cmpnId}" name="cmpnId" />
           <jsp:param value="${applicantList.campaignInfo.cmpnTitle}" name="cmpnTitle" />
           <jsp:param value="${applicantList.campaignInfo.sttsCd}" name="sttsCd" />
        </jsp:include>
        <div class="campaign-list-wrapper">
            <div class="campaign-title">
                <div>캠페인 신청자</div>
                <span class="enddate">~${applicantList.campaignInfo.rcrtEndDt}</span>
                <!-- <span class="adopt-count">채택 인원: ${applicantList.adoptCount}</span> -->
            </div>
            
            <div class="id-search">
               <input type="text" placeholder="ID를 입력하세요." />
               <img src="/img/Search.png" />
            </div>
            
            <div class="list-header grid-list-header list-applicant">
                <div>신뢰도
                    <img class="sort asc" data-sort-type="PNLT_CNT" src="/img/arrow-bottom.png" />
                </div>
                <div>블로거 정보
                </div>
                <div>전체 방문자 수
                    <img class="sort desc" data-sort-type="TTL_VSTR_CNT" src="/img/arrow-bottom.png" />
                </div>
                <div>평균 방문자 수
                    <img class="sort desc" data-sort-type="AVRG_VSTR_CNT" src="/img/arrow-bottom.png" />
                </div>
                <div>이웃 수
                    <img class="sort desc" data-sort-type="BLG_NGHBR_CNT" src="/img/arrow-bottom.png" />
                </div>
                <div>스크랩 수
                    <img class="sort desc" data-sort-type="SCRP_CNT" src="/img/arrow-bottom.png" />
                </div>
                <div>채택 여부
                    <span class="total-adpt-yn"></span>
                    <img class="sort desc" data-sort-type="ADPT_YN" src="/img/arrow-bottom.png" />
                </div>
            </div>
            
            <c:forEach items="${applicantList.applicantList}" var="applicant">
                <div class="grid-list-header list-applicant applicant">
                    <div>
                        <c:choose>
                            <c:when test="${applicant.userInfo.pnltCnt eq 0}">
                                <img src="/img/pnt_0.png" />
                            </c:when>
                            <c:when test="${applicant.userInfo.pnltCnt eq 1}">
                                <img src="/img/pnt_1.png" />
                            </c:when>
                            <c:otherwise>
                               <img src="/img/pnt_2.png" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <div class="user">
                        <div class="logId" data-cmpn-apply-id="${applicant.cmpnPstAdptId}">
                            ${applicant.userInfo.logId}
                            <button type="button" 
                                    name="blog-detail-info"  
                                    data-blog-id="${applicant.userInfo.usrId}" 
                                    data-blog-name="${applicant.userInfo.logId}">!</button>
                        </div>
                        <div><a href="${applicant.userInfo.blgAddrs}" target="_blank">${applicant.userInfo.blgAddrs}</a></div>
                    </div>
                    
                    <div>${applicant.userInfo.avrgVstrCnt}</div>
                    <div>${applicant.userInfo.blgNghbrCnt}</div>
                    <div>${applicant.userInfo.scrpCnt}</div>
                    <div>${applicant.userInfo.ttlVstrCnt}</div>
                    
                    <div class="adpt-yn">
                        <div>${applicant.adptYn}</div>
                    </div>
                    
                </div>
            </c:forEach>
            
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
        </div>
    </div>
    
<jsp:include page="/WEB-INF/views/layout/footer.jsp" />