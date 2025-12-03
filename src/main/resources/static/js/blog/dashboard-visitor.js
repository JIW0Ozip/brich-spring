$().ready(function () {
    const modal = $("#total-visitor-modal");
    // 모달 열기
    $("#daily-visitor-detail").on("click", function () {
        const usrId = $(this).data("user-id");

        $.ajax({
            url: `/api/blog/index/${usrId}/detail`,
            method: "GET",
            success: function (data) {
                if (!data || data.length === 0) {
                    thead.html("");
                    tbody.html("<tr><td>데이터가 없습니다.</td></tr>");
                    modal.fadeIn(200);
                    return;
                }

                // 헤더 생성
                let headerHtml = "<tr><th>지표</th>";
                dates.forEach(date => {
                    headerHtml += `<th>${date}</th>`;
                });
                headerHtml += "</tr>";
                thead.html(headerHtml);

                // 본문 생성
                let bodyHtml = "";
                metrics.forEach(metric => {
                    bodyHtml += `<tr><td>${metric.label}</td>`;
                    data.forEach(day => {
                        const value = day[metric.key] ?? "-";
                        bodyHtml += `<td>${value}</td>`;
                    });
                    bodyHtml += "</tr>";
                });
                tbody.html(bodyHtml);

                modal.fadeIn(200);
            },
            error: function (err) {
                console.error("데이터 로드 실패:", err);
                alert("데이터를 불러오는 중 오류가 발생했습니다.");
            }
        });
    });

    // 닫기 버튼
    $(".close").on("click", function() {
        modal.fadeOut(200);
    });

    // 배경 클릭 시 닫기
    $(window).on("click", function(e) {
        if ($(e.target).is(modal)) modal.fadeOut(200);
    });

    var url = new URL(window.location.href);
    var searchParam = url.searchParams;
    var userName = searchParam.get("userName");
    $(".user-name-space").text(userName);

})