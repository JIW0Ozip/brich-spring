<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="scripts">
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type="text/javascript" src="/js/campaign/campaignmain.js"></script>
       <script type="text/javascript" src="/js/common/paginator.js"></script>
       <script type="text/javascript" src="/js/common/validate.js"></script>
	   <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</c:set>

<c:set var="css">
       <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
       <link type='text/css' rel='stylesheet' href='/css/brich.css' />
</c:set>

<jsp:include page="/WEB-INF/views/layout/menu.jsp">
    <jsp:param name='css' value="${css}" />
        
    <jsp:param name="scripts" value="${scripts}" />
</jsp:include>

        <div class="main">
          <c:if test="${userInfo.usrId eq detail.usrId}">
            <jsp:include page="/WEB-INF/views/campaign/campaignTab.jsp">
                <jsp:param value="${detail.cmpnId}" name="cmpnId"></jsp:param>
                <jsp:param value="${detail.cmpnTitle}" name="cmpnTitle"></jsp:param>
                <jsp:param value="${detail.sttsCd}" name="sttsCd"></jsp:param>
            </jsp:include>
          </c:if>
          <div class="campaign-detail-wrapper">
	          <div class="cmpn-top-area">
	            <div class="cmpn-title flex-space-between">
					<div class="cmpn-title-content">
						<c:if test="${not empty detail.parentArea}">[ ${detail.parentArea} ]</c:if> 
						 ${detail.cmpnTitle }
						<c:if test="${sessionScope.__LOGIN_USER__.autr eq 1004
                                and detail.usrId eq sessionScope.__LOGIN_USER__.usrId}">
							<c:choose>
					            <c:when test="${detail.sttsCdNm eq '승인' || detail.sttsCdNm eq '모집중'
					                            || detail.sttsCdNm eq '선정중' || detail.sttsCdNm eq '진행중'}">
					                <span class="font-green">${detail.sttsCdNm}</span>
					            </c:when>
					            
					            <c:when test="${detail.sttsCdNm eq '시작대기' || detail.sttsCdNm eq '검토중' || detail.sttsCdNm eq '임시저장'}">
					                <span class="font-brown">${detail.sttsCdNm}</span>
					            </c:when>
					            
					            <c:when test="${detail.sttsCdNm eq '반려'}">
					                <span class="font-red">${detail.sttsCdNm}</span>
					            </c:when>
					            
					            <c:when test="${detail.sttsCdNm eq '종료'}">
					                <span class="font-gray">${detail.sttsCdNm}</span>
					            </c:when>
					        </c:choose>
				        </c:if>
					</div>
				    <c:if test="${not empty sessionScope.__LOGIN_USER__ }" > 
				        <c:if test="${(sessionScope.__LOGIN_USER__.autr == 1002 
                              or sessionScope.__LOGIN_USER__.autr == 1003)}">
						<div class="cmpn-title-love">
				            <c:if test="${not empty sessionScope.__LOGIN_USER__ }" > 
				                <c:set var="love" value="${detail.favYn eq 'Y'}" />
				                <div class="campaign-fav campaign-fav1 love-${detail.favYn}"
				                     data-usr-id="${sessionScope.__LOGIN_USER__.usrId}"
				                     data-cmpn-id="${detail.cmpnId}"> 
				                <div class="love-on ${love ? '' : 'display-none'}"></div>
				                <div class="love-off ${love ? 'display-none' : ''}"></div>
				              </div>
				            </c:if>							
    					</div>
					   </c:if>
					</c:if>
				</div>
	            <div class="cmpn-title-offrCn">${detail.offrCn}</div>
	            <div class ="cmpn-title-person flex-space-between">
	              <div>신청자 ${detail.adptCnt } / 모집인원 ${detail.rcrtPrsnn }</div>
	              <c:if test="${(sessionScope.__LOGIN_USER__.autr == 1002 
                              or sessionScope.__LOGIN_USER__.autr == 1003)}">
		              <c:if test="${not empty detail.pstSttsCd}">
			              <div class ="pst-stts-cd-${detail.pstSttsCd}">${detail.pstSttsCdNm}</div>
		              </c:if>
	              </c:if>
	            </div>
	          </div>
	          <div class="cmpn-bottom-area">
	            <div class="cmpn-content-area">
					<ul class="campaign-detail-table">
					  <li>
						<c:if test="${not empty detail.fileVoList}">
						  <div class="cmpn-images">
						    <c:forEach var="f" items="${detail.fileVoList}" varStatus="st">
						      <div class="cmpn-image">
						        <div class="path">
						        </div>
						           <img class="cmpnjij" src="/file/1234/${detail.flGrpId}/${f.flId}"/>
						      </div>
						    </c:forEach>
						  </div>
						</c:if>
					  </li>
					  <li>
	                    <label for="">설명</label>
	                    <pre>${detail.cmpnCn }</pre>				  
	                  </li>
					  <li>
	                    <label for="">제공</label>
	                    <pre>${detail.offrCn }</pre>				  
	                  </li>
					  <li>
	                    <label for="">미션</label>
	                    <pre>${detail.pstMssn }</pre>				  
	                  </li>
	                  <li>
	                    <label for="">해시 태그</label>
	                    <pre>${detail.hstg }</pre>
	                  </li>
	                  <li>
	                    <label for="">안내 사항</label>
	                    <pre>${detail.ntfcn }</pre>
	                  </li>
	                  <c:if test="${not empty detail.addrs}">
		                  <li>
		                    <label for="">위치</label>
		                    <div>${detail.addrs }</div>
		                  </li>
		                  <li>
		                    <label>지도</label>
		                    <div id="map" style="width:500px;height:400px;" data-location="${detail.addrs }"></div>
		                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=75d2ae10ae5482a8868edf8364c88dad&libraries=services"></script>
		                    <script type="text/javascript" src="/js/common/map.js"></script>
		                  </li>
	                  </c:if>
					</ul>
 
                    
                    
	            </div>
	            <div class="cmpn-rightbar-area">
	              <div class="right-bar">
	              
	              <!-- 오른쪽 창 -->
					<jsp:include page="/WEB-INF/views/campaign/campaigndetailrightbar.jsp">
					    <jsp:param name="scripts" value="
					        <script type='text/javascript' src='/js/campaign/campaignmain.js'></script>
					    " />
					    <jsp:param value="${detail.usrId}" name="usrId" />
					    <jsp:param value="${detail.sttsCd}" name="sttsCd" />
					    <jsp:param value="${detail.rtrnRsn}" name="rtrnRsn" />
	                </jsp:include>
	              <!-- 오른쪽 창 -->
	              
	              </div>
	            </div>
	          </div>
          </div>
        </div>
        <form class="submit-modal-form display-none">
	    	<div class = "submit-modal-area" data-cmpn-id= "${detail.cmpnId}">
	            <div class="submit-modal">
	            	<div class="submit-modal-title height-center">포스팅 입력</div>
	            	<div class="submit-modal-field middle-center">
	            		<label class="submit-modal-label" for="post-title">포스팅 제목</label>
	            		<input class="submit-modal-input" id="post-title" type="text" name="postTitle">
	            	</div> 
				    <div class="submit-modal-field middle-center">
				    	<label class="submit-modal-label" for="post-url">포스팅 주소</label>
				    	<input class="submit-modal-input" id="post-url" type="url" placeholder="https://" name="postUrl">
				    </div>
					<div class="submit-modal-actions flex-row">
					   <div class="submit-modal-btn submit-modal-btn-close middle-center">닫기</div>
					   <div class="submit-modal-btn submit-modal-btn-ok middle-center" >제출</div>
					</div>
	            </div>
	    	</div>
	    </form>
	    
<form class="re-submit-modal-form display-none" enctype="multipart/form-data">
  <div class="submit-modal-area" data-cmpn-id="${detail.cmpnId}">
    <div class="submit-modal">
      <div class="submit-modal-title height-center">포스팅 재 제출</div>
      <div class="submit-modal-field middle-center re-submit-area">
        <label class="submit-modal-label" for="re-post-cn">수정된 내용</label>
        <textarea class="submit-modal-input re-submit-cn" id="re-post-cn" name="postSubmitChgCn"></textarea>
      </div>
      <div class="submit-modal-field middle-center">
        <label class="submit-modal-label" for="re-post-title">포스팅 제목</label>
        <input class="submit-modal-input" id="re-post-title" type="text" name="postTitle">
      </div>
      <div class="submit-modal-field middle-center">
        <label class="submit-modal-label" for="re-post-url">포스팅 주소</label>
        <input class="submit-modal-input" id="re-post-url" type="url" placeholder="https://" name="postUrl">
      </div>
      <div class="submit-modal-field middle-center">
        <label class="submit-modal-label" for="re-post-file">파일추가</label>
        <div class="submit-modal-file">
          <input type="file" id="file-input" name="file" multiple style="display:none;">
          <img class="file-insert" id="re-post-file" src="/img/file.png" alt="파일 추가">
        </div>
      </div>
      <div class="submit-modal-actions flex-row">
        <div class="re-submit-modal-btn submit-modal-btn-close middle-center">닫기</div>
        <div class="submit-modal-btn re-submit-modal-btn-ok middle-center">제출</div>
      </div>
    </div>
  </div>
</form>

	    
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  

