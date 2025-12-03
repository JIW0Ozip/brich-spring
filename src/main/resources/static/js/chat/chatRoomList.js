// 현재 페이지 상태를 저장하는 변수
var currentFilter = 'all'; // 'all', 'unread'
var currentPage = 0;
var auth = 0;
var cmpnId = "";
$().ready(function() {
    auth = $(".content-box").data("auth");
    cmpnId = $(".chat-main").data("cmpn");
    chtRmId = $(this).closest(".chatroom-content-item").data("chat-room");

    console.log("auth:", auth);
    console.log("cmpnId:", cmpnId);

    // 초기 로드: 전체 채팅방 목록 첫 페이지 조회
    loadChatRoomList('all', 0);

    updateFilterButtons("");

    // 전체 버튼 클릭
    $(".progress.readall").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'all';
        currentPage = 0;
        loadChatRoomList('all', 0);
    });

    // 안읽음 버튼 클릭
    $(".progress.not").on("click", function() {
        updateFilterButtons($(this));
        currentFilter = 'unread';
        currentPage = 0;
        loadChatRoomList('unread', 0);
    });
    
    $(".arrow-back").on("click", function() {
        window.location = document.referrer;
    });

    // 페이지 번호 클릭 이벤트 (동적 생성된 요소)
    $(document).on("click", ".page-item", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });

    // 이전 그룹 버튼 클릭
    $(document).on("click", ".page-prev-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });

    // 다음 그룹 버튼 클릭 
    $(document).on("click", ".page-next-group", function() {
        var pageNo = $(this).data("page");
        currentPage = pageNo;
        loadChatRoomList(currentFilter, pageNo);
    });

    // 신고, 나가기 버튼 영역 클릭시 숨김 방지
    $(document).on("click", ".report-btn, .leave-chat-btn, .leave-chat-btn-rm", function(event) {
        event.stopPropagation();
    });

    // 신고하기/나가기 메뉴 열기
    $(document).on("click", ".chat-leave-btn", function(event) {
        var chtRmId = $(this).closest(".chatroom-content-item").data("chat-room");
        console.log(chtRmId);
        event.stopPropagation(); // 문서 클릭 이벤트 막기
        $(".modal-content-leave").data("cht-rm", chtRmId);
        console.log(chtRmId);
        var parent = $(this).closest(".chatroom-content-item");
        parent.find(".report-btn, .leave-chat-btn").show();
    });
    
    $(document).on("click", ".report-btn", function() {
        console.log("ddd");
        window.location.href = "/report/write/" + $(this).closest(".chatroom-content-item").data("target-id");
    })

    // 메뉴 외부 클릭시 메뉴 숨김
    $(document).on("click", function() {
        $(".report-btn, .leave-chat-btn").hide();
        $(".modal").fadeOut(200);
    });

    //채팅방 나가기 로직 
    //채팅방 나가기 버튼 누르면 모달 띄움
    $(document).on("click", ".leave-chat-btn, .leave-chat-btn-rm", function() {
        $(".modal").fadeIn(200);
    });
    //모달 닫기
    $(document).on("click", ".cancel-btn", function() {
        $(".modal").fadeOut(200);
    });
    //채팅방 나가기 
    $(document).on("click", ".confirm-btn", function() {
        var chtRmId = 
            $(this).closest(".modal-content-leave").data("cht-rm") !== ""
            ? $(this).closest(".modal-content-leave").data("cht-rm")
            : $(".chat-main").data("chtrm-id");
        console.log(chtRmId);
        var startUrl = auth === 1004 ? '/adv' : '/blgr';
        $.post(startUrl + "/chat/room/leave?chtRmId=" + chtRmId, function(response) {
            if (response.error === null) {
                alert(response.body.message);
            }
            //채팅방 목록에서 나간 경우
            if($(".chat-main").data("chtrm-id") === undefined) {
                window.location.reload(true);
            }
            //채팅방에서 방을 나간 경우
            else {
                window.location = document.referrer;
                
            }
        });
    });




    //실제 메시지를 나누는 채팅방 들어가기 
    $(document).on("click", ".chatroom-content-item", function() {
        var chtRmId = $(this).closest(".chatroom-content-item").data("chat-room");
        var startUrl = auth === 1004 ? '/adv' : '/blgr';
        window.location.href = startUrl + "/chat/room/" + chtRmId;
    });
});

// 필터 버튼 스타일 업데이트
function updateFilterButtons($clickedButton) {
    $(".progress.readall, .progress.not").css({
        "border-bottom": "0"
    });
    if ($clickedButton === "") {
        $(".progress.readall").css({
            "border-bottom": "3px solid #000"
        })
        return;
    }
    $clickedButton.css({
        "border-bottom": "3px solid #000"
    });

}

function loadChatRoomList(filter, pageNo) {
    var url = "";
    var params = "pageNo=" + pageNo;

    if (auth === 1004) {
        params += "&cmpnId=" + cmpnId;
    }

    if (filter === 'all') {
        url = (auth === 1004 ? "/adv" : "/blgr") + "/chat/rooms/all";
    } else if (filter === 'unread') {
        url = (auth === 1004 ? "/adv" : "/blgr") + "/chat/rooms/unread";
    }

    $.get(url + "?" + params, function(response) {
        var result = response.body;
        var items = result.chatRoomList;
        
        // HTML 문자열로 먼저 다 만들기
        var htmlString = "";

        if (items && items.length > 0) {
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                var template = $("#chat-room-list").html();
               
                // 메시지 처리
                var lastMsgCrtDt = item.lastMsgCrtDt || ""
                var lastMsg = item.lastMsgCn || "";
                if (lastMsg === "" && lastMsgCrtDt === "") {
                    lastMsg = item.nm + " 님과 채팅을 시작해보세요!";
                }
                
                if (lastMsg === "") {
                    lastMsg = "파일 메시지 입니다.";
                } else if (lastMsg.length >= 25) {
                    lastMsg = lastMsg.substr(0, 20) + "...";
                }

                // template 채우기
                template = template.replace("#campaigntitle#", item.cmpnTitle)
                    .replace("#campaignstatus#", item.cdNm)
                    .replace("#lastmessage#", lastMsg)
                    .replace("#latesttime#", item.lastMsgCrtDt || "")
                    .replace("#unreadcount#", item.unreadCnt || 0)
                    .replace("#chatroomid#", item.chtRmId)
                    .replace("#targetid#", item.targetId);
                
                if (auth === 1004) {
                    template = template.replace("#blogername#", item.nm || "");
                }

                // jQuery 객체로 변환해서 처리
                var $listItem = $(template);

                // 권한에 따라 제거
                if (auth === 1004) {
                    $listItem.find(".campaign-status").remove();
                    $listItem.find(".campaign-title.camp").remove();
                } else {
                    $listItem.find(".campaign-title.bloger-name").remove();
                }

                // 안읽은 개수 0이면 제거
                if (item.unreadCnt === 0) {
                    $listItem.find(".unread-count").remove();
                }

                // 상태 스타일
                if (item.cdNm === '종료') {
                    $listItem.find(".campaign-status").addClass("end");
                } else if (item.cdNm === '진행중') {
                    $listItem.find(".campaign-status").addClass("ongoing");
                } else if (item.cdNm === '선정중') {
                    $listItem.find(".campaign-status").addClass("selecting");
                }

                // HTML 문자열로 누적
                htmlString += $listItem[0].outerHTML;
            }
        } else {
            htmlString = "<div class='no-data'>채팅방이 없습니다.</div>";
        }

        // 한 번에 교체! (reflow 1번만 발생)
        $(".content-list").html(htmlString);

        // 페이지네이션 렌더링
        renderPagination(result);
    });
}

// 페이지네이션 렌더링 함수
function renderPagination(pageInfo) {
    var paginationHtml = "";

    // 이전 그룹 버튼
    if (pageInfo.havePrevPageGroup) {
        paginationHtml += '<span class="page-prev-group" data-page="' +
            pageInfo.prevGroupStartPageNo + '">&lt;&lt;</span> ';
    }

    // 페이지 번호들
    for (var i = pageInfo.groupStartPageNo; i <= pageInfo.groupEndPageNo; i++) {
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