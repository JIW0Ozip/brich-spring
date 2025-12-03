<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

                <div class="right-bar-top">
                    <div class="right-bor-top-wrapper">
                        <ul class="right-bar-detail-table">
                            <c:if test="${not empty detail.rcrtStrtDt}">
                                <li>
                                    <label for="">캠페인 신청기간</label>
                                    <div>${detail.rcrtStrtDt} ~ ${detail.pstEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.rcrtEndDt}">
                                <li>
                                    <label for="">선정자 발표일</label>
                                    <div>${detail.rcrtEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.cmpnEndDt}">
                                <li>
                                    <label for="">캠페인 종료일</label>
                                    <div>${detail.cmpnEndDt}</div>
                                </li>
                            </c:if>
                            <c:if test="${not empty detail.rcrtPrsnn}">
                                <li>
                                    <label for="">모집 인원</label>
                                    <div>${detail.rcrtPrsnn}명</div>
                                </li>
                                <li>
                                    <label for="">신청 인원</label>
                                    <div>${detail.adptCnt}명</div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- TODO -->
                <%-- <div class="right-bar-bottom"> 
                 <h3>📊 참여자 블로그 지수 비교</h3>
                            <canvas id="campaignIndexChart"></canvas>

                            <script>
                              const labels = [
                                <c:forEach items="${indexStats}" var="row" varStatus="st">
                                  "${row.statDt}"<c:if test="${!st.last}">,</c:if>
                                </c:forEach>
                              ];
                              
                              alert("블로그 지수" + labels);

                              const minData = [
                                <c:forEach items="${indexStats}" var="row" varStatus="st">
                                  ${row.minIndx}<c:if test="${!st.last}">,</c:if>
                                </c:forEach>
                              ];
                              
                              alert("블로그 지수" + minData);

                              const avgData = [
                                <c:forEach items="${indexStats}" var="row" varStatus="st">
                                  ${row.avgIndx}<c:if test="${!st.last}">,</c:if>
                                </c:forEach>
                              ];
                              
                              alert("블로그 지수" + minData);

                              const maxData = [
                                <c:forEach items="${indexStats}" var="row" varStatus="st">
                                  ${row.maxIndx}<c:if test="${!st.last}">,</c:if>
                                </c:forEach>
                              ];
                              
                              alert("블로그 지수" + maxData);

                              new Chart(document.getElementById("campaignIndexChart"), {
                                type: "line",
                                data: {
                                  labels,
                                  datasets: [
                                    { label: "최소 지수", data: minData, borderColor: "#C2C2C2", tension: 0.3 },
                                    { label: "평균 지수", data: avgData, borderColor: "#7B61FF", borderWidth: 2, tension: 0.4 },
                                    { label: "최대 지수", data: maxData, borderColor: "#00B8A9", tension: 0.3 }}
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
                            </script></div> --%>
                  
                <c:if test="${param.sttsCd eq 2003}">
                     <div class="deny-reason">
                         <div class="font-red">반려 사유</div>
                         <div class="rtrnRsn">: ${detail.rtrnRsn}</div>
                     </div>
                </c:if>
