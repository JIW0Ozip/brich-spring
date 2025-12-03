<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
        <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
    " />
    <jsp:param name="scripts" value="
        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
    " />
</jsp:include>
        <div class="main">
          <div class="campaign-detail-wrapper">
          
          <!-- 내 캠페인 프로필 -->
			<jsp:include page="/WEB-INF/views/campaign/campaignmy.jsp">
			   <jsp:param name="campaignMyName" value="신청한 캠페인"/>
			</jsp:include>
          <!-- 내 캠페인 프로필-->
          
          
          
           <!-- 캠페인 리스트 -->
			<jsp:include page="/WEB-INF/views/campaign/campaignlist.jsp"/>
           <!-- 캠페인 리스트 -->
           
            </div>
          </div>
        </div>
        
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  


