<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

                  <div class="right-bar-top">
                    <div class="right-bor-top-wrapper">
                        <ul class="right-bar-detail-table">
                            <c:if test="${not empty detail.rcrtStrtDt}">
		                          <li>
		                            <label for="">캠페인 신청기간</label>
		                            <div>${detail.rcrtStrtDt } ~ ${detail.rcrtEndDt }</div>
		                          </li>
	                          </c:if>
	                          <%-- <li>
		                            <label for="">선정자 발표일</label>
		                            <div>몇일할껀지??</div>
		                          </li> --%>
	                           <c:if test="${not empty detail.cmpnEndDt}">
		                          <li>
		                            <label for="">캠페인 종료일</label>
		                            <div>${detail.cmpnEndDt }</div>
		                          </li>
	                          </c:if>
	                          <c:if test="${not empty detail.rcrtPrsnn}">
		                          <li>
		                            <label for="">신청</label>
		                            <div>${detail.adptCnt } </div>
		                          </li>
		                          <li>
		                            <label for="">모집인원</label>
		                            <div> ${detail.rcrtPrsnn }</div>
		                          </li>
	                          </c:if>
                        </ul>
                    </div>
                  </div>
                  
                  <!-- 신청 버튼 영역 -->
                  <c:if test="${sessionScope.__LOGIN_USER__ ne null 
			               and (sessionScope.__LOGIN_USER__.autr == 1002 
			                or  sessionScope.__LOGIN_USER__.autr == 1003)
			               and detail.pstSttsCd eq null
			                }">
                        <div class="right-bar-bottom"> 							<h3>📊 참여자 블로그 지수 비교</h3>
							<canvas id="campaignIndexChart"></canvas>

							<script>
							  const labels = [
							    <c:forEach items="${indexStats}" var="row" varStatus="st">
							      "${row.statDt}"<c:if test="${!st.last}">,</c:if>
							    </c:forEach>
							  ];

							  const minData = [
							    <c:forEach items="${indexStats}" var="row" varStatus="st">
							      ${row.minIndx}<c:if test="${!st.last}">,</c:if>
							    </c:forEach>
							  ];

							  const avgData = [
							    <c:forEach items="${indexStats}" var="row" varStatus="st">
							      ${row.avgIndx}<c:if test="${!st.last}">,</c:if>
							    </c:forEach>
							  ];

							  const maxData = [
							    <c:forEach items="${indexStats}" var="row" varStatus="st">
							      ${row.maxIndx}<c:if test="${!st.last}">,</c:if>
							    </c:forEach>
							  ];

							  const myIndex = ${myIndex};

							  new Chart(document.getElementById("campaignIndexChart"), {
							    type: "line",
							    data: {
							      labels,
							      datasets: [
							        { label: "최소 지수", data: minData, borderColor: "#C2C2C2", tension: 0.3 },
							        { label: "평균 지수", data: avgData, borderColor: "#7B61FF", borderWidth: 2, tension: 0.4 },
							        { label: "최대 지수", data: maxData, borderColor: "#00B8A9", tension: 0.3 },
							        { label: "내 지수", data: Array(labels.length).fill(myIndex), borderColor: "#FF6B6B", borderDash: [5,5], borderWidth: 2 }
							      ]
							    },
							    options: {
							      responsive: true,
							      plugins: {
							        legend: { position: "bottom" },
							        tooltip: { mode: "index", intersect: false }
							      },
							      scales: {
							        y: {
							          beginAtZero: false,
							          title: { display: true, text: "블로그 지수" }
							        }
							      }
							    }
							  });
							</script></div>
			            <c:set var="isApplied" value="${detail.adptYn eq 'N'}" />
                        <div class="right-bar-bottom apply-cancel-blg middle-center ${isApplied ? 'display-none' : ''}" data-campaign-id="${detail.cmpnId}">
                        신청취소
                        </div>                        
                        <div class="right-bar-bottom apply-blg middle-center ${isApplied ? '' : 'display-none'}" data-campaign-id="${detail.cmpnId}">
                        신청하기
                        </div>
                  </c:if>
                  <c:if test="${sessionScope.__LOGIN_USER__ eq null}" >
                        <a class="right-bar-bottom required-login middle-center" href="/login">로그인 후 이용해주세요</a>
                  </c:if>
                  <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                           and (sessionScope.__LOGIN_USER__.autr == 1002 
                            or  sessionScope.__LOGIN_USER__.autr == 1003)
                           and detail.pstSttsCd ne null
                            }">
                            <!-- 제출전 -->
                            <c:if test="${detail.pstSttsCd == 6001}" >
		                        <div class="right-bar-bottom middle-center cursor-pointer status--draft status--" data-campaign-id="${detail.cmpnId}">
		                          포스팅 제출하기
		                        </div>
                            </c:if>                            
                            <!-- 검토중 -->
                            <c:if test="${detail.pstSttsCd == 6002}" >
		                        <div class="right-bar-bottom middle-center status--review status--" data-campaign-id="${detail.cmpnId}">
		                          검토중
		                        </div>
                            </c:if>      
                            
                            <!-- 반려됨 -->                      
                            <c:if test="${detail.pstSttsCd == 6003}" >
                            	<c:if test="${not empty returnReason}">
								  <div class="return-reason-box">
								  	<div class="return-reason-title">반려사유</div>
								  	<div> ${returnReason} </div> 
								  </div>
								</c:if>
		                        <div class="right-bar-bottom middle-center status--rejected cursor-pointer status--" data-campaign-id="${detail.cmpnId}">
		                          다시 제출하기
		                        </div>
                            </c:if>      
                            
                            <!-- 승인됨 -->                      
                            <c:if test="${detail.pstSttsCd == 6004}" >
                            <c:if test="${detail.sttsCd != 2009}" >
		                        <div class="right-bar-bottom middle-center status--approved status--" data-campaign-id="${detail.cmpnId}">
		                          승인 완료
		                        </div>
                            </c:if>
                            </c:if>
                            
                            <!-- 종료됨 -->                      
                            <c:if test="${detail.sttsCd == 2009}" >
		                        <div class="right-bar-bottom middle-center status--end status--" data-campaign-id="${detail.cmpnId}">
		                          종료됨
		                        </div>
                            </c:if>
                  </c:if>
                  
                  <c:if test="${sessionScope.__LOGIN_USER__.autr eq 1004
                                and param.usrId eq sessionScope.__LOGIN_USER__.usrId}">
	                   <c:if test="${param.sttsCd eq 2002}">
	                       <div class="middle-center">
			                   <a href="/adv/pay/campaign/${detail.cmpnId}">
			                     <button type="button" class="button_200_30 button-payment">결제</button>
			                   </a>
			               </div>
	                  </c:if>
	                  <c:if test="${param.sttsCd eq 2003}">
	                       <div class="deny-reason">
	                           <div class="font-red">반려 사유</div>
	                           <div>${param.rtrnRsn}</div>
	                       </div>
	                       <div class="middle-center">
                               <button type="button" class="button_200_30 button-campaign-modify">수정</button>
                           </div>
	                  </c:if>
	                  
	                  <%--
	                  <c:if test="${param.sttsCd eq 2004}">
	                       <div class="middle-center">
                               <button type="button" class="button_200_30 button-report">취소</button>
                           </div>
	                  </c:if>--%>
                  </c:if>