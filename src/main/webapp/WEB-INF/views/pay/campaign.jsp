<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="scripts">
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type="text/javascript" src="/js/campaign/campaignmain.js"></script>
       <script type="text/javascript" src="/js/common/pay.js"></script>
</c:set>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
  <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
  <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

<div class="main flex-column side-padding">
    <div class="px36blue campaign-title-area height-center">캠페인 결제하기</div>
    <div class="flex-column flex-cente box-shadow">
    <form id="payForm">
    	<div class="campaign-payment-block" id="campaign-payment-block" data-cmpn-id="${detail.cmpnId }">
    		<div class="campaign-payment-title">상품정보</div>
    		<div class="campaign-payment-content">${detail.cmpnTitle }</div>
    	</div>
    	<div class="campaign-payment-block">
    		<div class="campaign-payment-title">모집일 선택</div>     		
				<div class="campaign-payment-content flex-row">
				  <div>
				    <div>모집 시작일</div>
				    <input type="date" id="startDate" name="rcrtStrtDt" required />
				  </div>
				
				  <div>
				    <div>구매 기간(일)</div>
				    <input id="buyDays" name="prgrssDrtn" type="text" inputmode="numeric" pattern="[0-9]*" autocomplete="off" />
				  </div>
				
				  <div>
				    <div>모집 마감일</div>
				    <input type="date" id="endDate" name="rcrtEndDt" readonly/>
				  </div>
				
				  <div>
				    <div>캠페인 시작일</div>
				    <input type="date" id="campStartDate" readonly/>
				  </div>
				
				  <div>
				    <div>캠페인 마감일</div>
				    <input type="date" id="pstEndDt" name="pstEndDt" />
				  </div>
				</div>
    	</div>
    	<div class="campaign-payment-block">
    		<div class="campaign-payment-title">인원 수</div>
    		<div class="campaign-payment-content payPerson" data-person="${detail.rcrtPrsnn}">${detail.rcrtPrsnn} 명</div>
    	</div>
    	<div> 총 모집일과 인원 수를 계산하여 총 결제 금액이 결정됩니다 </div>
    	<div class="campaign-payment-block">
    		<div class="campaign-payment-title">요금 상세</div>
    		<div class="campaign-payment-content payPrice" data-rcrtprsnn="${detail.personPrice}" data-dayprice="${detail.dayPrice}">
    			(${detail.rcrtPrsnn} 명 x ${detail.personPrice }원) + ( <span id="payDay"></span>일 x ${detail.dayPrice }원) =  <span class="total-pay"></span>원 
    		</div>
    	</div>
    	<div class="campaign-payment-block">
    		<div class="campaign-payment-title">최종 금액</div>
    		<div class="campaign-payment-content"><span class="total-pay end-pay"></span> 원</div>
    	</div>
    	<input type="hidden" name="totalPay" id="totalPayHidden" readonly>
    </form>
    </div>
	<div class ="payment-bottom flex-row">
		<div class="payment-bottom-title">최종 금액 <span class="total-pay end-pay"></span> 원</div>
		<a href="/adv/pay/{cdId}"><div class="payment-bottom-button middle-center">결제하기</div></a>
	</div>    
</div>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
