<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
  <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
  <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />
</jsp:include>

<div class="main flex-column side-padding">
  <div class="px36blue campaign-title-area height-center">캠페인</div>

  <form class="search-section">
    <div class="category-area flex-row text-align">
      <c:forEach items="${category}" var="category">
        <label class="category-radio ">
          <input
            type="radio"
            name="category"
            value="${category.cdNm}"
            ${category.cdId eq search.category ? 'checked' : ''}
          />
          <span class="text-align height-center flex-center campaign-hover-blue">
            <div></div>
            <div>${category.cdNm}</div>
            <div
              class="category-seleted-box-${category.cdNm} visibility-hidden category-seleted-box"
              data-category-menu="${category.cdNm}"
            ></div>
          </span>
        </label>
      </c:forEach>
    </div>

    <div class="search-area flex-row">
      <input
        class="search-input"
        type="text"
        name="searchKeyword"
        value="${search.searchKeyword}"
        placeholder="Search"
      />
      <select class="sortBy" name="sortBy">
        <option value="latest"   ${search.sortBy eq "latest"   ? "selected" : ""}>최신순</option>
        <option value="deadline" ${search.sortBy eq "deadline" ? "selected" : ""}>마감임박순</option>
        <option value="popular"  ${search.sortBy eq "popular"  ? "selected" : ""}>인기순</option>
      </select>
    </div>
  </form>

  <!-- 캠페인 리스트 -->
<jsp:include page="/WEB-INF/views/campaign/campaignlist.jsp"/>
  <!-- 캠페인 리스트 -->
  
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
