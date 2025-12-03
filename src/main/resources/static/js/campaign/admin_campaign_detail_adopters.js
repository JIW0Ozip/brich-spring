/**
 * 
 */
$().ready(function() {
    
    var postReturnBtn = $(".postReturnRsnBtn");
    var postReSubmitBtn = $(".postReSubmitCnBtn");
    
    var adopterModal = $("#adopters-modal");
    var modalTitle = $("#modal-title");
    var modalContentArea = $("#modal-content-area");
    var modalCloseBtn = $("#modal-close-btn");

    /* 반려 사유 모달 */
    $(postReturnBtn).on("click", function() {
        openModal("RETURN", $(this));
    });
    
    /* 재제출 내용 모달 */
    $(postReSubmitBtn).on("click", function() {
        openModal("RESUBMIT", $(this));
    });
    
    /* 버튼 종류에 맞게 데이터 가져와 모달 열기 */
    var openModal = function(type, button) {
        currentModalType = type;
        
        var row = button.closest(".adopters");
        var postId = row.find(".logId").data("post-id");
        var blgUsrId = row.find(".blgUsrId").data("usr-id");
        var logId = row.find(".logId").text().trim();
        
        console.log("postId: " + postId + "\nlogId: " + logId + "\nblgUsrId: " + blgUsrId);
        
        modalTitle.text("데이터 불러오는 중...");
        modalContentArea.empty().append("<div class='modal-content-box'>불러올 데이터 구분 중...</div>");
        
        var url = "";
        
        switch(type) {
            case "RETURN": 
                          modalTitle.text(logId + "의 반려 사유");
                          url = "/admin/campaign-adopter/modal-list/returnReason";
                          break;
            case "RESUBMIT": 
                          modalTitle.text(logId + "의 재제출 내용");
                          url = "/admin/campaign-adopter/modal-list/postReSubmitContent";
                          break;
                  default: 
                          alert("모달 타입 식별 불가");
                          return;
        }
        
        adopterModal.show();
        
        $.ajax({
            url: url, 
            method: "GET", 
            dataType: "json", 
            data: {
                "postId" : postId
            }, 
            
            success: function(contentList) { // contentList: List<ContentVO>
                
                // console.log("리스트: " + contentList);
                            
                var historyHtml = "";
                
                if(contentList && contentList.length > 0) {
                    $.each(contentList, function(index, content) {
                        
                        var contentText = "";
                        
                        if(url.includes("returnReason")) {
                            contentText = content.postRtrnRsn;
                        }
                        else {
                            contentText = content.postSubmitChgCn; 
                        }
                        
                        historyHtml += "<div class='modal-content-block'>"; 
                        historyHtml += "<div class='modal-content-box'>" + contentText + "</div><div class='crt-dt'>" + content.crtDt + "</div>";
                        
                        if(content.fileList && content.fileList.length > 0) { 
                            
                            var fileAreaHtml = "<div class='file-area'><div class='file-text'>첨부 파일</div>";
                            
                            $.each(content.fileList, function(fileIndex, file) { 
                                
                                fileAreaHtml += "<a href='/file/" + blgUsrId + "/" + file.flGrpId + "/" + file.flId + " class='file-link'>&#128196;" 
                                                + file.flNm + 
                                                "</a>";
                            });
                            
                            fileAreaHtml += "</div>";
                            
                            historyHtml += fileAreaHtml;
                        }
                        historyHtml +=  "</div>";
                    }); // contentList.each
                    
                    modalContentArea.html(historyHtml);
                    
                } else {
                    modalContentArea.html("<div class='modal-content-box''>이력이 존재하지 않습니다.</div>");
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error:", status, error, xhr.responseText);
                modalContentArea.html("<div class='modal-content-box'>데이터 로드 중 오류가 발생했습니다.</div>");
            }
        });
    }
    
    /* 모달 닫기 버튼 */
    modalCloseBtn.on("click", function() {
        modalTitle.text("");
        modalContentArea.empty();
        adopterModal.hide();
    });
    
    /* 배경 눌러 모달 닫기 */
    adopterModal.on("click", function(event) {
        // 클릭한 배경의 id 비교
        if(event.target.id === "adopters-modal") {
            modalTitle.text("");
            modalContentArea.empty();
            adopterModal.hide();
        }
    });
    
});