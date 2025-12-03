<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
  <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
  <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />
</jsp:include>

<div class="main flex-column side-padding">
    <div class="px36blue campaign-title-area height-center">블로그 관리 서비스 이용권</div>
    <div class="flex-row subscribe-area flex-center">
    
        <div class="flex-column subscribe-box">
            <div class="product-title">1개월 이용권</div>
            <div class="product-content">블로그 관리 및 캠페인 신청의 모든 기능을 이용할 수 있어요!</div>
            <div class="product-price-area flex-column">
                <div class="product-price">
                    ₩ <fmt:formatNumber value="${payInfoList[0].value1}" pattern="#,##0" />
                    / <span class="pay-day"> ${payInfoList[0].cdNm}  </span>
                </div>
            </div>
            <a href="/blgr/pay/${payInfoList[0].cdId}">
	            <div class="product-submit-area middle-center" data-product-price="${payInfoList[0].value1}">
	                <div class="product-submit middle-center">구매하기</div>            
	            </div>
            </a>
        </div>
        
        <div class="flex-column subscribe-box ">
            <c:set var="month1"  value="${payInfoList[0].value1}" />
            <c:set var="month12" value="${payInfoList[1].value1 /12}" />
            <c:set var="discount"  value="${(month1-month12)/month1*100 }" />           
            <div class="product-title">12개월 이용권</div>
            <div class="product-content">
                1년 이용권 구매 시 월
                <span class="product-price">
                    <fmt:formatNumber value="${month12}" pattern="#,##0" />
                </span> 원!
            </div>
            <div class="product-price-area flex-column">
                <div class="product-price">
                    <span class="italic line-through">
                        ₩ <fmt:formatNumber value="${payInfoList[0].value1*12}" pattern="#,##0" />
                    </span>
                    / <span class="pay-day italic"> ${payInfoList[1].cdNm} </span>
                    <span class="discount-percent">
                        약 <fmt:formatNumber value="${discount}" pattern="#,##0" />% 할인!!
                    </span>
                </div>
                <div class="product-price">
                    ₩ <fmt:formatNumber value="${payInfoList[1].value1}" pattern="#,##0" />
                    / <span class="pay-day"> ${payInfoList[1].cdNm} </span>
                </div>
                <div class="product-price">
                  ₩ <fmt:formatNumber value="${month12}" pattern="#,##0" />
                  / <span class="pay-day"> ${payInfoList[0].cdNm} </span> 
                </div>
            </div>
            <a href="/blgr/pay/${payInfoList[1].cdId}">
	            <div class="product-submit-area middle-center" data-product-price="${payInfoList[1].value1}">
	                <div class="product-submit middle-center">구매하기</div>            
	            </div>
            </a>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
