<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="scripts">
    <script type="text/javascript" src="/js/common/areamodal.js"></script>
</c:set>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/brich.css' />
    " />
     <jsp:param name='accountCss' value="
        <link type='text/css' rel='stylesheet' href='/css/account/account.css' />
    " />
    <jsp:param name="accountJs"
        value="
        <script type='text/javascript' src='/js/account/account.js'></script>
    " />
     <jsp:param name="scripts" value="${scripts}" />
    
</jsp:include>


<div class="account-wrapper">
    <div class="campaign-tab">
        <div class="account-menu-box" data-auth="${sessionScope.__LOGIN_USER__.autr}">
            <div class="account-top-menu account-info point">계정 정보</div>
            <div class="account-top-menu reset-password">비밀번호 재설정</div>
            <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004}">
                <div class="account-top-menu sub-info">구독 정보</div>
            </c:if>
        </div>
    </div>
    <div class="account-info-wrapper">
        <div class="account-info-container">
            <div class="account-info-item">
                <div class="item-title space">이름</div>
                <input class="item-content" value="${userInfo.userVO.nm}" disabled>
            </div>
              <div class="account-info-item">
                <div class="item-title space">아이디</div>
                <input class="item-content" value="${userInfo.userVO.logId}" disabled>
            </div>
              <div class="account-info-item">
                <div class="item-title space">이메일</div>
                <input class="item-content" value="${userInfo.userVO.eml }" disabled>
            </div>
             <c:if test="${sessionScope.__LOGIN_USER__.autr eq 1004 }">
                <div class="account-info-item">
                    <div class="item-title space p">상호명</div>
                    <input class="item-content" value="${userInfo.userVO.cmpny}" disabled>
                </div>
            </c:if>
            <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004 }">
                <div class="account-select-item">
				    <div class="category-box">
				        <div class="item-title space">카테고리</div>
				<!--         카테고리 사용여부 y/n 제대로 정리가 안된듯 
				        기존 코드에는 n인데 블로거 카테고리에는 있어서 나옴 -->  
				        <div class="select-list">
				            <c:forEach items="${userInfo.categoryList}" var="category">
							    <div class="select-list-item">${category.cdNm}</div>
							</c:forEach>
				        </div>
				        
				    </div>
				    <div class="category-reset" 
				         data-user-categories="<c:forEach items='${userInfo.categoryList}' var='cat' varStatus='status'>${cat.cdId}<c:if test='${!status.last}'>,</c:if></c:forEach>">
				        카테고리 재설정
				    </div>
				</div>
  
			    <div class="category-checkbox-wrapper" style="display: none;">
			        <div class="category-checkbox-list">
			            <c:forEach items="${common.categoryList}" var="item" begin="1">
						    <div class="category-checkbox-item">
						        <input type="checkbox"
						               name="category-check"
						               id="cat-${item.cdId}"
						               data-category-code="${item.cdId}"
						               value="${item.cdId}" />
						        <label for="cat-${item.cdId}">${item.cdNm}</label>
						    </div>
						</c:forEach>
			        </div>
			    </div>
            </c:if>
             <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004 }">
                <div class="account-select-item">
                    <div class="area-box">
                        <div class="item-title space p">지역</div>
                        <div class="select-list area-preview-list">
                            <c:forEach items="${userInfo.areaList}" var="area">
                                <div class="select-list-item">${area.cdNm}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="area-reset">지역 재설정</div>
                </div>
            </c:if>
            <c:if test="${sessionScope.__LOGIN_USER__.autr ne 1004}">
                <div class="modify-confirm">확인</div>
            </c:if>
         </div>
     </div>
 </div>

</div>
</div>
<c:set var="doCityList" value="${common.doAndCityList}" scope="request" />
 <jsp:include page="/WEB-INF/views/layout/areamodal.jsp" />

</body>
</html>