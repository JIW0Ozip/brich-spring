<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>카테고리 관리</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
        <script type="text/javascript" src="/js/campaign/admin_category_manage.js"></script>
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_category.css" />
		<link type="text/css" rel="stylesheet" href="/css/admin/admin_category_modal.css" />
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
                <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
        </jsp:include>
	    <div class="wrapper">
	        <div class="title">카테고리 관리</div>
	        
	        <div class="container">
	        <input type="hidden" id="adminId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
	        
	            <div class="category-add">
	                <button type="button" class="add-btn pointer-cursor">+ 카테고리 추가</button>
	            </div>
	            
	            <div class="category-append-area">
					<c:forEach items="${campaignCategoryList}" var="parentCtg">
					<c:if test="${parentCtg.level eq 1}">
					    <div class="category-item">
					        <div class="category-up-down">
					            <div class="up-btn">▲</div>
					            <div class="down-btn">▼</div>
					        </div>
					        
					        <div class="category-info">
					            <input type="hidden" data-parent-cd-id="${parentCtg.cdId}"/>
							    <span class="category-parent">${parentCtg.cdNm}</span>
						    
							    <c:forEach items="${campaignCategoryList}" var="childCtg">
							    <c:if test="${parentCtg.cdId eq childCtg.prntCdId}">
							        <span class="category-child">&nbsp;| ${childCtg.cdNm}</span>
							    </c:if>
							    </c:forEach>
						    
							    <c:if test="${parentCtg.useYn eq 'Y' and parentCtg.srt ne 999}">
								    <div class="category-btn-group">
								        <button type="button" class="div-btn pointer-cursor">분할</button>
								        <button type="button" class="merge-btn pointer-cursor">병합</button>
								    </div>
							    </c:if>
							    <div class="category-srt">
						            <span>순위: ${parentCtg.srt}</span>
						        </div>
					        </div>
					    </div>
					</c:if>
					</c:forEach>
	            </div>
	            
	            <div class="btn-group">
		            <button type="button" class="revert-btn" style="display: none;">되돌리기</button>
		            <button type="button" class="save-btn" style="display: none;">순서 저장</button>
	            </div>
	        </div>
	    </div>
	</body>
	
	<div id="categoryModal" class="modal" style="display: none;">
		<div id="modalContentWrapper">
		    <div id="titleArea">
		        <h3 id="title">모달 제목</h3>
		    </div>
		    
		    <div id="addCategoryArea">
		        <span>카테고리 </span>
		        <input type="text" id="newCategoryName" required placeholder="추가할 카테고리 이름 입력" />
		        <span>을(를) 추가하시겠습니까?</span>
		    </div>
		    
		    <div id="divCategoryArea">
		        <span class="targetCategoryName"></span>
		        <span>에서 </span>
		        <select name="divActiveCategoryList">
		        </select>
		        <span>을(를) 분할하여 노출시키시겠습니까?</span>
		    </div>
		    
		    <div id="mergeCategoryArea">
		        <span class="targetCategoryName"></span>
		        <span>을(를) </span>
		        <select name="mergeActiveCategoryList">
		        </select>
		        <span> 하위로 병합하시겠습니까?</span>
		    </div>
		    
		    <div id="buttonArea" class="modal">
		        <button type="button" class="modal-close-btn">닫기</button>
		        <button type="button" class="modal-save-btn">확인</button>
		    </div>
		</div>
	</div>
</html>