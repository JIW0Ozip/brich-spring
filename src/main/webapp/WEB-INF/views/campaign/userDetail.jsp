<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
	<head>
	   <meta charset="UTF-8" />
	   <link type='text/css' rel='stylesheet' href='/css/campaignmain.css' />
	   <link type='text/css' rel='stylesheet' href='/css/blog/dashboard.css' />
       <link type='text/css' rel='stylesheet' href='/css/brich.css' />
       <link typr='text/css' rel='stylesheet' href='/css/paginator-simple.css'/>
       <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
       <script type='text/javascript' src='/js/blog/dashboard.js'></script>
	   
       <title>Brich</title>
	</head>
	<body>
		<div class="user-detail-container">
		        <div class="blog-info-section">
		            <div class="section-header">
		                <div class="dashboard-title"><span class="user-name-space"></span> 블로그 지수</div>
		                <button id="blog-index-detail" data-user-id="${user}" class="dashboard-detail-btn">자세히 보기</button>
		            </div>
		            <div id="blog-index-modal" class="modal">
		                <div class="modal-content">
		                    <span class="close">&times;</span>
		                    <h3>블로그 상세 통계</h3>
		                    <div class="table-container">
		                        <table id="blog-detail-table" border="1">
		                            <thead></thead>
		                            <tbody></tbody>
		                        </table>
		                    </div>
		
		                </div>
		            </div>
		            <div>현재 블로그 지수: <span class="blog-index">${currentIndex}</span></div>
		            <canvas id="blogIndexChart"></canvas>
		        </div>
		
		        <div class="blog-info-section">
		            <div class="section-header">
		                <div class="dashboard-title"><span class="user-name-space"></span> 의 블로그 방문자 수</div>
		                <%--<button id="daily-visitor-detail" class="dashboard-detail-btn">자세히 보기</button>--%>
		            </div>
            <div id="total-visitor">총 방문자 수   <span>${totalVisitor}</span></div>
            <span class="sub-info">▲ 2.1% 지난 주 대비</span>
		            <canvas id="dailyVisitorChart"></canvas>
		        </div>
		
		        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
		
		        <script>
		            // 방문자 수 그래프
		
		            // 전체 데이터 (14일)
		            const allLabels = [
		                <c:forEach items="${dailyVisitorsResult}" var="row" varStatus="st">
		                "${row.vstDt}"<c:if test="${!st.last}">,</c:if>
		                </c:forEach>
		            ];
		
		            const allCounts = [
		                <c:forEach items="${dailyVisitorsResult}" var="row" varStatus="st">
		                ${row.vstrCnt}<c:if test="${!st.last}">,</c:if>
		                </c:forEach>
		            ];
		
		            // 지난 주 (앞 7개)
		            const lastWeekLabels = allLabels.slice(0, 7);
		            const lastWeekCounts = allCounts.slice(0, 7);
		
		            // 최근 1주 (뒤 7개)
		            const recentWeekLabels = allLabels.slice(7, 14);
		            const recentWeekCounts = allCounts.slice(7, 14);
		
		            // X축은 최근 1주의 날짜 기준 (둘 다 같은 길이니까)
		            const ctxVisitor = document.getElementById('dailyVisitorChart').getContext('2d');
		            const visitorChart = new Chart(ctxVisitor, {
		                type: 'bar',
		                data: {
		                    labels: recentWeekLabels,  // X축 라벨
		                    datasets: [
		                        {
		                            label: '지난 주 방문자 수',
		                            data: lastWeekCounts,
		                            backgroundColor: 'lightgray',  // 회색
		                            borderColor: 'white',
		                            borderWidth: 1
		                        },
		                        {
		                            label: '최근 1주 방문자 수',
		                            data: recentWeekCounts,
		                            backgroundColor: '#00C7E2',   // 파란색
		                            borderColor: 'white',
		                            borderWidth: 1
		                        }
		                    ]
		                },
		                options: {
		                    responsive: true,
		                    scales: {
		                        x: {
		                            grid: { display: false }
		                        },
		                        y: {
		                            beginAtZero: true,
		                            ticks: {
		                                display: false
		                            }
		                        }
		                    },
		                    legend: { // 범례
		                        position: 'bottom',
		                        labels: {
		                            usePointStyle: true,
		                            boxWidth: 10,
		                            padding: 10
		                        }
		                    }
		                }
		            });
		
		            // 블로그 지수
		
		            const labels = [
		                <c:forEach items="${index}" var="row" varStatus="st">
		                    "${row.statDt}"<c:if test="${!st.last}">,</c:if>
		                </c:forEach>
		            ];
		
		            const avg5d = [
		                <c:forEach items="${index}" var="row" varStatus="st">
		                    ${row.indxValAvg5d}<c:if test="${!st.last}">,</c:if>
		                </c:forEach>
		            ];
		
		            const ovll = [
		                <c:forEach items="${index}" var="row" varStatus="st">
		                    ${row.ovllBlgIndx}<c:if test="${!st.last}">,</c:if>
		                </c:forEach>
		            ];
		
		            const ctx = document.getElementById('blogIndexChart').getContext('2d');
		            const chart = new Chart(ctx, {
		                type: 'line',
		                data: {
		                    labels: labels,
		                    datasets: [
		                        {
		                            label: '5일 평균 지수',
		                            data: avg5d,
		                            borderColor: '#7B61FF',
		                            backgroundColor: 'rgba(123, 97, 255, 0.1)',
		                            tension: 0.4,
		                            // fill: true,
		                            // pointRadius: 3,
		                            // pointHoverRadius: 5,
		                            borderWidth: 2
		                        },
		                        {
		                            label: '전체 평균 지수',
		                            data: ovll,
		                            borderColor: '#00B8A9',
		                            backgroundColor: 'rgba(0, 184, 169, 0.1)',
		                            tension: 0.4,
		                            // fill: true,
		                            // pointRadius: 3,
		                            // pointHoverRadius: 5,
		                            borderWidth: 2
		                        }
		                    ]
		                },
		                options: {
		                    responsive: true,
		                    plugins: {
		                        legend: {
		                            position: 'top',
		                            labels: {
		                                color: '#333',
		                                font: { size: 13 }
		                            }
		                        },
		                        tooltip: {
		                            mode: 'index',
		                            intersect: false
		                        }
		                    },
		                    scales: {
		                        // x: {
		                        //  ticks: { color: '#555' },
		                        //  grid: { color: 'rgba(0,0,0,0.05)' }
		                        // },
		                        // y: {
		                        //  beginAtZero: false,
		                        //  ticks: { color: '#555' },
		                        //  grid: { color: 'rgba(0,0,0,0.05)' }
		                        // }
		                        x: {
		                            grid: { display: false }
		                        },
		                        y: {
		                            beginAtZero: false,
		                            ticks: {
		                                display: false
		                            }
		                        }
		                    }
		                }
		            });
		        </script>
		    </div>
	</body>
</html>
