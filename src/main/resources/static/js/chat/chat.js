// 현재 페이지 상태를 저장하는 변수
var currentFilter = 'all'; // 'all', 'ongoing', 'ended'
var currentPage = 0;

$().ready(function() {
    
    loadCampaignList('all', 0);
        
    // 전체 버튼 클릭
    $(".progress.all").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'all';
        currentPage = 0;
        loadCampaignList('all', 0);
    });

    $(".progress.all").trigger("click");

    
    // 진행중 버튼 클릭
    $(".progress.on").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'ongoing';
        currentPage = 0;
        loadCampaignList('ongoing', 0);
    });

    // 종료된 버튼 클릭
    $(".progress.end").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'ended';
        currentPage = 0;
        loadCampaignList('ended', 0);
    });

    // 캠페인 아이템 클릭
    $(document).on("click", ".campaign-content-item", function() {
        window.location.href = "/adv/chat/rooms?cmpnId=" 
                             + $(this).data("campaign-id");
    });

    // 페이지 번호 클릭 이벤트 (동적 생성된 요소)
    $(document).on("click", ".page-item", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadCampaignList(currentFilter, pageNo);
    });

    // 이전 그룹 버튼 클릭
    $(document).on("click", ".page-prev-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadCampaignList(currentFilter, pageNo);
    });

    // 다음 그룹 버튼 클릭
    $(document).on("click", ".page-next-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadCampaignList(currentFilter, pageNo);
    });
});

// 필터 버튼 스타일 업데이트
function updateFilterButtons($clickedButton) {
    console.log($clickedButton);
    $(".progress.all, .progress.on, .progress.end").css({
        "border-bottom": "0"
    });
  
    $clickedButton.css({
        "border-bottom": "3px solid #000"
    });
}

// 캠페인 목록 로드 함수
function loadCampaignList(filter, pageNo) {
    var url = "";
    
    switch(filter) {
        case 'all':
            url = "/adv/chat/campaigns/all";
            break;
        case 'ongoing':
            url = "/adv/chat/campaigns/ongoing";
            break;
        case 'ended':
            url = "/adv/chat/campaigns/ended";
            break;
    }
    
    $.get(url + "?pageNo=" + pageNo, function(response) {
        var result = response.body;
        var items = result.campaignList;
        
        // 디버깅 로그
        console.log("=== 캠페인 페이지 정보 ===");
        console.log("현재 페이지:", result.pageNo);
        console.log("페이지 크기:", result.listSize);
        console.log("총 페이지 수:", result.pageCount);
        console.log("데이터 개수:", items ? items.length : 0);
        console.log("그룹 시작:", result.groupStartPageNo);
        console.log("그룹 끝:", result.groupEndPageNo);
        console.log("전체 result:", result);
        console.log("========================");
        
        // 기존 목록 초기화
        $(".content-list").children().remove();
        
        // 캠페인 아이템 렌더링
        if (items && items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-campaign-list").html();

                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignid#", item.cmpnId)
                    .replace("#campaign#", item.cmpnId);

                // 상태 라벨 처리
                var status = item.sttsCd === "2009" ? "종료" : "";
                template = template.replace("#campaignstatus#", status);

                var listItem = $(template);

                // 상태가 비어있으면 해당 요소만 제거
                if (status === "") {
                    listItem.find(".campaign-status").remove();
                }

                $(".content-list").append(listItem);
            }
        } else {
            $(".content-list").append("<div class='no-data'>캠페인이 없습니다.</div>");
        }
        
        // 페이지네이션 렌더링
        renderPagination(result);
    });
}

function renderPagination(pageInfo) {
    var paginationHtml = "";
    
    // 페이지가 없으면 페이징 숨김
    if (!pageInfo || pageInfo.pageCount === 0) {
        $(".page-list").html("");
        return;
    }
    
    // 실제 마지막 페이지 인덱스 계산 (0부터 시작하므로 pageCount - 1)
    var lastPageIndex = pageInfo.pageCount - 1;
    
    // 그룹의 실제 끝 페이지 (lastPageIndex와 groupEndPageNo 중 작은 값)
    var actualGroupEndPage = Math.min(pageInfo.groupEndPageNo, lastPageIndex);
    
    console.log("페이징 디버그 - pageCount:", pageInfo.pageCount, 
                "lastPageIndex:", lastPageIndex,
                "groupStartPageNo:", pageInfo.groupStartPageNo,
                "groupEndPageNo:", pageInfo.groupEndPageNo,
                "actualGroupEndPage:", actualGroupEndPage);
    
    // 이전 그룹 버튼
    if (pageInfo.havePrevPageGroup) {
        paginationHtml += '<span class="page-prev-group" data-page="' + 
                         pageInfo.prevGroupStartPageNo + '">&lt;&lt;</span> ';
    }
    
    // 페이지 번호들 (실제 존재하는 페이지만)
    for (var i = pageInfo.groupStartPageNo; i <= actualGroupEndPage; i++) {
        if (i === pageInfo.pageNo) {
            paginationHtml += '<span class="page-item active" data-page="' + i + '">' + 
                             (i + 1) + '</span> ';
        } else {
            paginationHtml += '<span class="page-item" data-page="' + i + '">' + 
                             (i + 1) + '</span> ';
        }
    }
    
    // 다음 그룹 버튼
    if (pageInfo.haveNextPageGroup) {
        paginationHtml += '<span class="page-next-group" data-page="' + 
                         pageInfo.nextGroupStartPageNo + '">&gt;&gt;</span>';
    }
    
    $(".page-list").html(paginationHtml);
}