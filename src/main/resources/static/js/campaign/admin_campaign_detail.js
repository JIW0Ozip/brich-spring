/**
 * 
 */
$().ready(function() {
    
    var adminId = $("#adminId").val();
    var cmpnId = $("#cmpnId").val();
    
    /* 캠페인 승인 버튼 */
    var approveBtn = $(".approve-btn");
    
    $(approveBtn).on("click", function() {
        
        if(!confirm("해당 캠페인을 승인 처리하시겠습니까?")) {
            return;
        }
        else {
            
            var dataToSend = {
                adminId : adminId, 
                cmpnId : cmpnId
            };
            
            $.ajax({
                url: "/admin/campaign-approve", 
                method: "POST", 
                contentType: "application/json", 
                data: JSON.stringify(dataToSend), 
                
                success: function(response) {
                    alert("해당 캠페인 승인 처리를 완료했습니다." + 
                        "\n승인한 캠페인: " + cmpnId + 
                        "\n처리한 관리자: " + adminId);
                        
                    location.reload();
                }, 
                error: function(xhr) {
                    var errorMessage = "HTTP status: " + xhr.status + ": " + (xhr.responseText || "서버 응답 없음");
                                       alert("error\n" + errorMessage + "\n콘솔에서 정보 확인 필요");
                                       console.error("Ajax Error: " + xhr);
                }
            });
        }
    });
    
    /* 캠페인 반려 데이터 */
    var rejectModal = $("#rejectModal");
    var rejectModalTitle = $(".reject-modal-title");
    var rejectModalInput = $(".reject-modal-input");
    var rejectModalCloseBtn = $(".reject-modal-close-btn");
    var rejectModalNextBtn = $(".reject-modal-next-btn");
    var rejectModalBackBtn = $(".reject-modal-back-btn");
    var rejectModalSaveBtn = $(".reject-modal-save-btn");
    
    var rejectBtnAreaOne = $(".modal-btn-area-one");
    var rejectBtnAreaTwo = $(".modal-btn-area-two");
    
    /* (반려) 반려 버튼 클릭 시 모달 열기 */
    $(".reject-btn").on("click", function() {
        openModalAction("REJECT");
    });
    
    /* (반려) 모달 닫기 버튼 클릭 시 창 닫기 */
    $(rejectModalCloseBtn).on("click", function() {
        closeModalAction("REJECT");
    });

    /* (반려) 바깥 배경 클릭 시 모달 창 닫기 */
    rejectModal.on("click", function(event) {
        // 클릭한 배경의 id 비교
        if(event.target.id === "rejectModal") {
            closeRejectModal();
        }
    });
    
    // 모달 닫기 액션
    var closeModalAction = function(type) {
        switch(type) {
            case "REJECT": 
                          closeRejectModal();
                  default:
                          return;
        }
    };
    
    /* (반려) 모달 닫기 */
    var closeRejectModal = function() {
        rejectModal.hide();
        rejectModalTitle.text("반려 사유를 작성해 주세요.");
        rejectModalInput.val("");
    };
    
    // 모달 열기 액션
    var openModalAction = function(type) {
        switch(type) {
            case "REJECT": 
                          openRejectModal();
                  default:
                          return;
        }
    };
    
    /* (반려) 모달 열기 */
    var openRejectModal = function() {
        rejectModal.show();
        rejectModalInput.focus();
        rejectBtnAreaTwo.hide();
    }
    
    /* (반려) 모달 1차 확인 버튼 클릭 시 */
    $(rejectModalNextBtn).on("click", function() {
        
        if(rejectModalInput.val().trim() === "") {
            alert("사유는 빈 칸, 혹은 공백으로 제출할 수 없습니다.");
            return;
        }
        
        rejectBtnAreaOne.hide();
        rejectBtnAreaTwo.show();
        
        rejectModalTitle.text("아래와 같은 사유로 정말 반려하시겠습니까?");
        rejectModalInput.prop("readonly", true);
    });
    
    /* (반려) 모달 2차 다시 작성 버튼 클릭 시 */
    $(rejectModalBackBtn).on("click", function() {
        
        rejectBtnAreaTwo.hide();
        rejectBtnAreaOne.show();
        
        rejectModalTitle.text("반려 사유를 작성해 주세요.");
        rejectModalInput.focus();
        rejectModalInput.prop("readonly", false);
    });
    
    /* (반려) 모달 2차 확인 버튼 클릭 시 Ajax 전송 */
    $(rejectModalSaveBtn).on("click", function() {
        
        var rtrnRsn = rejectModalInput.val();
        console.log("반려 사유: " + rtrnRsn);
        
        // 1차에서 막긴 했는데, 혹시나 해서 다시 막아둠
        if(rtrnRsn.trim() === "") {
            alert("사유는 빈 칸, 혹은 공백으로 제출할 수 없습니다.");
            return;
        }
        
        rejectModalSaveBtn.prop("disabled", true).text("반려 사유 제출 중...");
        
        var dataToSend = {
            adminId : adminId, 
            cmpnId : cmpnId, 
            rtrnRsn : rtrnRsn
        };
        
        $.ajax({
            url: "/admin/campaign-reject", 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(dataToSend), 
            
            success: function(response) {
                alert("해당 캠페인 반려 처리를 완료했습니다." + 
                    "\n반려한 캠페인: " + cmpnId + 
                    "\n처리한 관리자: " + adminId);
                
                rejectModal.hide();
                rejectModalTitle.text("반려 사유를 작성해 주세요.");
                rejectModalInput.val("");
                rejectModalInput.prop("readonly", false);
                rejectModalSaveBtn.text("확인");
                rejectModalSaveBtn.prop("disabled", false);
                
                location.reload();
            }, 
            error: function(xhr) {
                var errorMessage = "HTTP status: " + xhr.status + ": " + (xhr.responseText || "서버 응답 없음");
                                   alert("error\n" + errorMessage + "\n콘솔에서 정보 확인 필요");
                                   console.error("Ajax Error: " + xhr);
            }, 
            complete: function() {
                rejectModalSaveBtn.text("확인");
                rejectModalSaveBtn.prop("disabled", false);
            }
        });
    });
    
    /* 탭 이동 */
    url = window.location.href;
    
    if (url.includes("/admin/campaign-detail")) {
        $("#campaign-detail").attr("checked", "true");
    }
    else if (url.includes("/admin/campaign-applicant")) {
        $("#campaign-applicant").attr("checked", "true");
    }
    else if (url.includes("/admin/campaign-adopters")) {
        $("#campaign-adopters").attr("checked", "true");
    }
    
    $(".campaign-tab").children("input").on("click", function() {
        cmd = $(this).attr("id");
        console.log(cmd);
        cmpnId = $("#campaign-detail").data("cmpn-id");
        console.log(cmpnId);
        url = "";
        if (cmd === "campaign-detail") {
            url = "/admin/campaign-detail/" + cmpnId;
        }
        else if (cmd === "campaign-applicant") {
            url = "/admin/campaign-applicant/" + cmpnId;
        }
        else if (cmd === "campaign-adopters") {
            url = "/admin/campaign-adopters/" + cmpnId;
        }
        
        window.location.href = url;
    });
    
    /* 탭 메뉴 페이지에서 정렬(sort) */
    /* 어차피 이 파일 무조건 import 해야 돼서... 따로 안 쓰고 여기로 옮김 */
    var urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get("sortCol") !== null) {
            column = urlParams.get("sortCol");
            order = urlParams.get("order");
            
            if (order === 'asc') {
                $(`[data-sort-type="${column}"]`).addClass("desc").removeClass("asc");
            }
            else if (order === 'desc') {
                $(`[data-sort-type="${column}"]`).addClass("asc").removeClass("desc");
            }
        }

    /* 졍럴 */
    $(".sort").on("click", function() {
        order = $(this).attr("class").includes("asc") ? "asc" : "desc";
        col = $(this).data("sort-type");
        
        if (window.location.search === "") {
            url = window.location.pathname + "?order=" + order
                                           + "&sortCol=" + col;
         }
         else {
             url = new URL(window.location.href);
             searchParam = url.searchParams;
             searchParam.set("order", order);
             searchParam.set("sortCol", col);
             url = url.toString();
         }
        
        window.location.href = url;
    });
    
    /* 신청자의 블로그 지수 */
    $("button[name=blog-detail-info]").on("click", function() {
        var userId = $(this).data("blog-id");
        var userName = $(this).data("blog-name");
        var url = "/adv/blog-info/" + userId + "?userName=" + userName;
        var name = "유저 정보";
        var option = "width=900, height=500, left=300, top=300"
        
        window.open(url, name, option);
    });
    
});
