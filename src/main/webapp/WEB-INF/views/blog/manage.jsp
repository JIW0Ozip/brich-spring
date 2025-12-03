<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css'/>
		<link rel='stylesheet' type='text/css'
	      	href='https://cdn.datatables.net/responsive/2.5.0/css/responsive.dataTables.min.css'/>
       	<link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
		<link type='text/css' rel='stylesheet' href='/css/blog/manage.css' />
		
       	
   	" />
   	<jsp:param name="scripts" value="
		<script src='https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js'></script>
		<script src='https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js'></script>
       	<script type='text/javascript' src='/js/blog/manage.js'></script>
	" />    
</jsp:include>
		<div class="manage-container">
			<div class="header-title">포스팅 관리</div>
			<div class="table-container">
				
				
				<table class="table table-bordered table-hover dt-responsive">
				        
				        <thead>
				          <tr>
				            <th>제출일</th>
				            <th>제목</th>
				            <th>캠페인 제목</th>
				            <th>상태</th>
				            <th>반려사유</th>
				          </tr>
				        </thead>
					<tbody>
					<c:choose>
						<c:when test="${not empty list}">
							<c:forEach var="post" items="${list}">
							  <tr >
							    <td class="center">${post.pstSbmtDt}</td>
							    <td><a href="${post.pstUrl}" target="_blank">${post.pstTitle}</a></td>
							    <td>${post.cmpnTitle}</td>
								<td class="center">
								  <c:choose>
								    <c:when test="${post.pstSttsCd eq '6001'}">
								      <span class="status status-pending">제출 전</span>
								    </c:when>
								    <c:when test="${post.pstSttsCd eq '6002'}">
								      <span class="status status-review">검토중</span>
								    </c:when>
								    <c:when test="${post.pstSttsCd eq '6003'}">
								      <span class="status status-rejected">반려</span>
								    </c:when>
								    <c:when test="${post.pstSttsCd eq '6004'}">
								      <span class="status status-approved">승인</span>
								    </c:when>
								    <c:otherwise>
								      <span class="status status-unknown">-</span>
								    </c:otherwise>
								  </c:choose>
								</td>
	
							    <td class ="center">
							      <c:choose>
							        <c:when test="${post.hasReturn}">
							          <button class="btn-reason" data-id="${post.cmpnPstAdptId}">반려 내용 보기</button>
							        </c:when>
							        <c:otherwise>-</c:otherwise>
							      </c:choose>
							    </td>
							  </tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5" class="no-data">표시할 캠페인 없음</td>
							</tr>
						</c:otherwise>
					</c:choose>
					</tbody>
		      	</table>
			</div>
			<div id="reason-modal" class="modal">
			  <div class="modal-content">
			    
			    <h3>반려사유<span class="close right-align">&times;</span></h3>
			    <div id="reason-detail"></div>
			  </div>
			</div>
		</div>
	</body>
</html>