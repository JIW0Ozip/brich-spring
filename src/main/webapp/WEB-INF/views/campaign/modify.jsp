<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="scripts">
    <script type="text/javascript" src="/js/campaign/write.js"></script>
    <script type="text/javascript" src="/js/common/validate.js"></script>
    <script type="text/javascript" src="/js/common/areamodal.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

<div class="campaign-wrapper">
    <div class="campaign-content-wrapper">
	    <div class="campaign-title">캠페인 만들기</div>
	        <form:form modelAttribute="RequestCreateCmpnVO"
	                   method="post"
	                   action="/adv/campaign/modify/${campaign.cmpnId}"
	                   enctype="multipart/form-data"
	                   id="campaign-submit">
	        <div class="write-content">
	            <div class="campaign-sub-title require-after">캠페인 제목</div>
		        <input type="text" class="text-input require-input" name="cmpnTitle"  value="${campaign.cmpnTitle}" />
		        
		        <div class="campaign-sub-title require-after">카테고리</div>
		        <div>
		            <c:forEach items="${common.categoryList}" var="category">
		                <c:choose>
                            <c:when test="${campaign.ctgCd eq category.cdId}">
			                  <input type="radio" name="ctgCd" value="${category.cdId}" checked />${category.cdNm}
                            </c:when>
			                <c:otherwise>
			                  <input type="radio" name="ctgCd" value="${category.cdId}" />${category.cdNm}
			                </c:otherwise>
		                </c:choose>
		            </c:forEach>
		        </div>
		        
		        <div class="area">
		            <div>
		                <span class="campaign-sub-title">지역</span>
		                <button type="button">선택</button>
		            </div>
		            <div class="area-list"></div>
		            <div class="hidden-area-list"></div>
		        </div>
		        
		        <div class="address">
		            <span class="campaign-sub-title">주소</span>
		            <input type="radio" name="address-check" id="address-check" />
		            <label for="address-check">입력</label>
		            <input type="radio" name="address-check" id="address-uncheck" checked />
		            <label for="address-uncheck">입력 안함</label>
		            <br>
		            <input type="text" placeholder="도로명 주소" name="roadAddress" class="text-input" readonly disabled="disabled" />
		            <input type="text" placeholder="상세 주소" name="detailAddress" class="text-input" disabled="disabled" />
		        </div>
		
		        <div>
		            <span class="campaign-sub-title">이미지</span>
		            <input type="file" name="file" />
		        </div>
		        
		        <div class="require-after">
		            <span class="campaign-sub-title">모집 인원</span>
		            <input type="number" name="rcrtPrsnn" class="require-input" value="${campaign.rcrtPrsnn}"
		              data-person-price="${common.personPrice}" />
		            <span>예상 인원 가격 : <span class="rcrtPrsnn-price">0</span>원</span>
		        </div>
		        
		        <div class="campaign-sub-title require-after">설명</div>
		        <textarea class="text-input write-input require-input" name="cmpnCn">${campaign.cmpnCn}</textarea>
		        <div class="require-after">
		            <span class="campaign-sub-title">제공</span>
		            <span>금액</span>
		            <input type="number" name="offrPrc" class="require-input" value="${campaign.offrPrc}" />
		            <span>원</span>
		        </div>
		        <textarea class="text-input write-input require-input" name="offrCn">${campaign.offrCn}</textarea>
		        <div class="campaign-sub-title require-after">미션</div>
		        <textarea class="text-input write-input require-input" name="pstMssn">${campaign.pstMssn}</textarea>
		        <div class="campaign-sub-title require-after">해시태그</div>
		        <textarea class="text-input write-input require-input" name="hstg">${campaign.hstg}</textarea>
		        <div class="campaign-sub-title require-after">안내사항</div>
		        <textarea class="text-input write-input require-input" name="ntfcn">${campaign.ntfcn}</textarea>
		        
		        <div class="create-button-list grid-item">
	                <%--<button type="button" class="">미리보기</button>--%>
	                <button type="button" class="temporary-button">임시저장</button>
	                <button type="button" class="cancel-button">취소</button>
	                <button type="button" class="submit-button auto-active">신청</button>
	            </div>
		   </div>
		</form:form>
	</div>
</div>

<c:set var="doCityList" value="${common.doAndCityList}" scope="request" />
<jsp:include page="/WEB-INF/views/layout/areamodal.jsp" />

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />