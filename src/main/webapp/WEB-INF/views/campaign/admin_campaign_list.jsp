<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>캠페인 관리 - 목록</title>
		<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
		<script type='text/javascript' src='/js/campaign/admin_campaign_list.js'></script>
	</head>
	<body>
	    <jsp:include page="/WEB-INF/views/layout/menu.jsp">
           <jsp:param name='css' value="
                <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
            " />
        </jsp:include>
        
        <div class="main flex-column side-padding">
            <div class="px36blue campaign-title-area height-center">캠페인 관리</div>
            
            <form class="search-section">
                <div class="category-area flex-row text-align">
                
                    <label class="category-radio">
                    <input type="radio" name="category" value="all"/>
                    <span class="text-align height-center flex-center campaign-hover-blue">
	                    <div></div>
	                    <div>전체</div>
	                    <div class="category-seleted-box-all visibility-hidden category-seleted-box" 
	                         data-category-menu="all"></div>
                    </span>
                    </label>
                    
	                <c:forEach items="${category}" var="category" begin="1">
	                    <label class="category-radio">
	                    <input type="radio" name="category" value="${category.cdNm}"
	                        ${category.cdId eq search.category ? 'checked' : ''} />
	                    <span class="text-align height-center flex-center campaign-hover-blue">
	                        <div></div>
	                        <div>${category.cdNm}</div>
	                        <div class="category-seleted-box-${category.cdNm} visibility-hidden category-seleted-box" 
	                             data-category-menu="${category.cdNm}"></div>
	                    </span>
	                    </label>
	                </c:forEach>
	                
			    </div>
			    
                <div class="search-area flex-row">
                    <input type="text" name="searchKeyword" value="${search.searchKeyword}" 
			               class="search-input" placeholder="Search"/>
                    <select class="sortBy" name="sortBy">
				        <option value="latest"   ${search.sortBy eq "latest"   ? "selected" : ""}>최신순</option>
				        <option value="under-review"   ${search.sortBy eq "under-review"   ? "selected" : ""}>검토중</option>
				        <option value="cnfm"   ${search.sortBy eq "cnfm"   ? "selected" : ""}>승인</option>
				        <option value="rtrn"   ${search.sortBy eq "rtrn"   ? "selected" : ""}>반려</option>
				        <option value="start-waiting"   ${search.sortBy eq "start-waiting"   ? "selected" : ""}>시작 대기</option>
				        <option value="recruiting"   ${search.sortBy eq "recruiting"   ? "selected" : ""}>모집 중</option>
				        <option value="choice"   ${search.sortBy eq "choice"   ? "selected" : ""}>선정 중</option>
				        <option value="start"   ${search.sortBy eq "start"   ? "selected" : ""}>진행 중</option>
				        <option value="end"   ${search.sortBy eq "end"   ? "selected" : ""}>종료</option>
			        </select>
			    </div>
		    </form>
		    
		    <!-- 캠페인 리스트 -->
            <jsp:include page="/WEB-INF/views/campaign/admin_campaign_list_item.jsp"/>
        </div>
        
        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>