/**
 * 
 */
$().ready(function() {
    
    var adminId = $("#adminId").val();
    var saveBtn = $(".save-btn");
    var revertBtn = $(".revert-btn");
    
    var targetCdId;
    var targetCdNm;
    var targetSrt;
    var currentModalType;    // 현재 어떤 모달 사용 중인지
    
    var categoryModal = $("#categoryModal");
    categoryModal.hide();    // 모달 틀 가리기
    
    var modalTitle = $("#title");    // 모달 제목 내용
    var modalCloseBtn = $(".modal-close-btn");    // 모달 닫기 버튼
    var modalSaveBtn = $(".modal-save-btn");    // 모달 확인 버튼
    
    var isOrderChanged = false;    // 카테고리 순서 변경이 되었는가?
    var originalOrderList = [];    // 초기 카테고리ID(cdId)의  순서를 저장할 배열
    
    /* 저장 버튼 비활성화 (노출 순서) */
    saveBtn.hide()
           .prop("disabled", true)
           .removeClass("active");
                  
    /* 되돌리기 버튼 비활성화 (노출 순서) */
    revertBtn.hide()
             .prop("disabled", true);
    
    /* 카테고리 추가 */
    var addCategoryButton = $(".add-btn");    // 카테고리 추가 버튼
    var addCategoryArea = $("#addCategoryArea");    // 카테고리 추가 영역
    var newCategoryName = $("#newCategoryName");    // 추가시킬 카테고리 이름 input
    $(addCategoryButton).on("click", function() {
        modalAction("ADD", null);
    });
    
    /* 카테고리 분할 */
    var divCategoryButton = $(".div-btn");    // 카테고리 분할 버튼
    var divCategoryArea = $("#divCategoryArea");    // 카테고리 분할 영역
    $(divCategoryButton).on("click", function() {
        modalAction("DIV", $(this));
    });
    
    /* 카테고리 병합 */
    var mergeCategoryButton = $(".merge-btn");    // 카테고리 병합 버튼
    var mergeCategoryArea = $("#mergeCategoryArea");    // 카테고리 분할 영역
    $(mergeCategoryButton).on("click", function() {
        modalAction("MERGE", $(this));
    });
    
    /* 카테고리 순위 변동 시 순위 텍스트 변경 */
    var updateVisualSrt = function() {
        
        var items = $(".container").find(".category-item");
        
        items.each(function(index) {
            var srtSpan = $(this).find(".category-srt span");
            srtSpan.text("순위: " + (index + 1));
        });
        
    };
    
    /* 카테고리 순서가 기존 순서와 같은지 체크 */
    var orderChengedCheck = function() {
        
        var currentOrderList = $(".container").find(".category-item").map(function() {
            
            // cdId 값 추출하여 배열에 저장
            return $(this).find("input[type='hidden']").data("parent-cd-id");
        }).get();
        
        if(currentOrderList.length !== originalOrderList.length) {
            return true;
        }
        
        for(var i = 0; i < currentOrderList.length; i++) {
            
            // 하나라도 기존 순서와 다르면 return
            if(currentOrderList[i] !== originalOrderList[i]) {
                return true;
            }
        }
        return false;
    };
    
    /* 카테고리 순서 변경 액션 + 버튼 활성화 */
    var clickToMoveCategoryItem = function(currentItem, direction) {
        
        // 이동할 대상 찾기 (.category-item)
        var targetItem = (direction === "UP")
                       ? currentItem.prev(".category-item")
                       : currentItem.next(".category-item");
        
        // 더 이상 움직일 수 없는 경우, 
        if(targetItem.length === 0) {
            alert("더 이상 순서를 변경할 수 없습니다.");
            return;
        }
        
        if(direction === "UP") {
            currentItem.insertBefore(targetItem);
        }
        else {
            currentItem.insertAfter(targetItem);
        }
        
        // 시각적 순위 업데이트
        updateVisualSrt();
        
        // 카테고리 순서가 바뀌었는지 체크 (boolean)
        var orderHasChanged = orderChengedCheck();
        
        // 변경 상태 표시 및 저장/되돌리기 버튼 활성화/표시
        if(orderHasChanged) {    // 순서가 바뀌었다면, 
            isOrderChanged = true;
            
            saveBtn.show().prop("disabled", false).addClass("active");
            revertBtn.show().prop("disabled", false);
            
            console.log(saveBtn);
        }
        else {    // 순서가 기존과 동일하다면, 
            isOrderChanged = false;
            saveBtn.hide().prop("disabled", true).removeClass("active");
            revertBtn.hide().prop("disabled", true);
        }
        
    };
    
    /* 카테고리 순서 변경 (저장 버튼 눌러서 적용) */
    $(".up-btn, .down-btn").on("click", function() {
        
        var buttonType = $(this);
        var currentItem = $(buttonType).closest(".category-item");
        var direction = buttonType.hasClass("up-btn") ? "UP" : "DOWN";
        
        // .category-item을 찾을 수 없을 경우, 
        if(currentItem === 0) {
            alert("이동시킬 카테고리 데이터를 찾을 수 없습니다.");
            return;
        }
        
        clickToMoveCategoryItem(currentItem, direction);
    });
    
    /* 저장 버튼 클릭 시 */
    saveBtn.on("click", function() {
        saveOrderBySrt();
    });
    
    /* 되돌리기 버튼 클릭 시 */
    revertBtn.on("click", function() {
        revertOrderBySrt();
    });
    
    /* 기존 카테고리 순서대로 카테고리 ID 저장 */
    var saveOriginalOrder = function() {
        originalOrderList = $(".container").find(".category-item").map(function() {
            return $(this).find("input[type='hidden']").data("parent-cd-id");
        }).get();
        
        // console.log("초기 카테고리 순서: " + originalOrderList);
    };
    
    /* 되돌리기 - 기존 카테고리 순서대로 UI 초기화 */
    var revertOrderBySrt = function() {
        
        // 카테고리 순서가 기존 순서와 같다면(방지용), 
        if(!isOrderChanged) {
            return;
        }
        
        // 방지턱
        if(!confirm("카테고리를 기존 순서대로 되돌립니다.")) {
            return;
        }
        else {
            // 기존 카테고리를 돌면서 정보를 모두 map(객체)에 저장해 둠.
            // 그리고 초기에 저장해 두었던 ID 값/순서에 맞춰서 데이터를 붙여서 순서를 되돌림.
            var itemMap = {};
            $(".container").find(".category-item").each(function() {
                
                var item = $(this);    // 순회 중인 카테고리 하나의 틀 (.category-item)
                var cdId = item.find("input[type='hidden']").data("parent-cd-id");
                itemMap[cdId] = item;    // Key(cdId) : Value(.category-item)
                item.detach();    // 순서 변경 이벤트(스티커) 유지 목적으로 detach() 사용.
            });
            
            // 원래 순서(originalOrderList)대로 되돌리기
            $.each(originalOrderList, function(index, cdId) {
                if(itemMap[cdId]) {
                    $(".category-append-area").append(itemMap[cdId]);
                }
            });
            
            // 시각적 순위 업데이트
            // 해당 함수를 호출하지 않으면 순서는 돌아가지만, 
            // 마지막으로 위치했던... 바뀌기 전의 순위 값이 뜨게 됨.
            updateVisualSrt();
            
            // 상태 및 UI 초기화
            isOrderChanged = false;
            saveBtn.hide().prop("disabled", true).removeClass("active");
            revertBtn.hide().prop("disabled", true);
            
            alert("되돌리기 완료");
        }
    };
    
    /* 순서 저장 (Ajax 전송) */
    var saveOrderBySrt = function() {
        
        // 순서가 기존과 같다면, (방지용)
        // 원래 기존과 같을 경우 버튼은 뜨지 않지만...
        if(!isOrderChanged) {
            alert("변경사항이 없습니다."); 
            return;
        }
        
        // 방지턱
        if(!confirm("카테고리 노출 순서를 변경하시겠습니까?")) {
            return;
        }
        else {
            saveBtn.prop("disabled", true).text("순서 변경 요청 중...");
            
            // 최종 순서대로 cdId 값을 가져와 List에 할당
            var orderedCdIdList = $(".container").find(".category-item").map(function() {
                return $(this).find("input[type='hidden']").data("parent-cd-id");
            }).get();
            
            if(orderedCdIdList.length === 0) {
                alert("저장할 카테고리 목록이 비어있습니다.");
                saveBtn.prop("disabled", false).addClass("active");
                return;
            }
            
            var dataToSend = {
                adminId : adminId, 
                orderedCdIdList : orderedCdIdList
            };
            
            $.ajax({
               url: "/admin/category-manage/change-order", 
               method: "POST", 
               contentType: "application/json", 
               data: JSON.stringify(dataToSend), 
               
               success: function(response) {
                   alert("카테고리 노출 순서 변경 사항을 저장하였습니다.");
                   
                   isOrderChanged = false;
                   saveBtn.hide();
                   revertBtn.hide();
                   saveBtn.prop("disabled", true);
                   location.reload();
               }, 
               error: function(xhr) {
                   var errorMessage = "HTTP status: " + xhr.status + ": " + (xhr.responseText || "서버 응답 없음");
                   alert("error\n" + errorMessage + "\n콘솔에서 정보 확인 필요");
                   console.error("Ajax Error: " + xhr);
               }, 
               complete: function() {
                   saveBtn.prop("disabled", false).removeClass("active");
               }
            });
        }
        
    }
    
    /* 모달 닫기 버튼 클릭 시 창 닫기 */
    modalCloseBtn.on("click", function() {
        closeModal();
    });
    
    /* 바깥 배경 클릭 시 모달 창 닫기 */
    categoryModal.on("click", function(event) {
        // 클릭한 배경의 id 비교
        if(event.target.id === "categoryModal") {
            closeModal();
        }
    });
    
    /* 모달 확인 버튼 클릭 시 (기능 타입에 맞는 함수 호출) */
    modalSaveBtn.on("click", function() {
        switch(currentModalType) {
            case "ADD": 
                        addCategory();
                        break;
            case "DIV": 
                        divCategory();
                        break;
            case "MERGE": 
                        mergeCategory();
                        break;
                default: 
                        alert("모달/기능 타입 식별 불가");
        }
    });
    
    /* 모달 열기 전, select 태그 내 option 값 채우기 */
    // type: 기능 종류
    // currentCdId: 선택된 기능에 맞는 카테고리 ID (cdId)
    var loadCategoryList = function(type, currentCdId) {
        
        var selectedList = (type === "DIV") 
                         ? $("[name='divActiveCategoryList']") 
                         : $("[name='mergeActiveCategoryList']");
        
        // 기존 옵션 초기화 (잔여물 제거 느낌...)
        selectedList.empty().append("<option value=''>데이터 불러오는 중...</option>");
        
        var url = (type === "DIV") 
                ? "/admin/category-manage/modal-list/children?parentCdId=" + currentCdId
                : "/admin/category-manage/modal-list/parent?excludeCdId=" + currentCdId;
        
        $.ajax({
            url: url, 
            method: "GET", 
            dataType: "json", 
            
            success: function(categoryList) {
                
                selectedList.empty();
                
                var defaultOptionText = (type === "DIV")
                                      ? "하위 카테고리 선택"
                                      : "상위 카테고리 선택";
                
                selectedList.append("<option value='' selected disabled>" + defaultOptionText + "</option>")
                
                if(categoryList && categoryList.length > 0) {
                    $.each(categoryList, function(index, category) {
                        
                        // 병합의 경우 자신의 이름은 제외하고 출력한다.
                        if(type === "MERGE" && category.cdId === currentCdId) {
                            return true;
                        }
                        selectedList.append("<option name='categoryOption' value='" + category.cdId + "'>" 
                                                + category.cdNm + 
                                            "</option>");
                    });
                }
                else {
                    selectedList.append("<option value='' disabled>사용 가능 카테고리 없음</option>");
                }
            } 
            // error 생략
        });
    };
    
    /* 클릭된 버튼 종류에 따라 활성화 시킬 모달 선택 */
    var modalAction = function(type, button) {
        
        // 입력만 받으면 되는 거라 바로 모달 열고 return
        if(type === "ADD") {
            targetCdId = "";
            targetCdNm = "";
            targetSrt = ""; 
            openModal(type, null);
            return;
        }
        
        // 분할or병합일 경우 해당 버튼의 부모 쪽에서 데이터 추출
        var infoDiv = button.closest(".category-info");
        
        var hiddenInput = infoDiv.find("input[type='hidden'][data-parent-cd-id]").first();
        
        var data = {};
        data.cdId = hiddenInput.data("parentCdId");
        data.cdNm = infoDiv.find(".category-parent").text();
        var srtText = infoDiv.find(".category-srt span").text().trim();
        data.srt = srtText.replace("순위:", "").trim();
        
        if(!data.cdId || !data.cdNm || !data.srt) {
            alert("카테고리 정보를 읽어올 수 없음.");
            return;
        }
        
        targetCdId = data.cdId;
        targetCdNm = data.cdNm;
        targetSrt = data.srt;
        
        // 해당 카테고리 정보를 가지고 가서 기능에 맞는 모달 열기
        openModal(type, data);
    };
    
    /* 모달 열기 */
    var openModal = function(type, data) {
        
        addCategoryArea.hide();
        divCategoryArea.hide();
        mergeCategoryArea.hide();
        
        currentModalType = type;
        
        switch(type) {
            
            case "ADD": 
                        modalTitle.text("카테고리 추가");
                        addCategoryArea.show();
                        newCategoryName.val("").focus();
                        break;
            case "DIV": 
                        modalTitle.text("카테고리 분할");
                        divCategoryArea.children(".targetCategoryName").text(data.cdNm);
                        loadCategoryList("DIV", data.cdId);
                        divCategoryArea.show();
                        break;
            case "MERGE": 
                        modalTitle.text("카테고리 병합");
                        mergeCategoryArea.children(".targetCategoryName").text(data.cdNm);
                        loadCategoryList("MERGE", data.cdId);
                        mergeCategoryArea.show();
                        break;
                default: 
                        alert("모달 타입 식별 불가: " + type);
                        return;
        }
        categoryModal.show();
    };
    
    /* 모달 닫기 */
    var closeModal = function() {
        categoryModal.hide();
        currentModalType = "";
        targetCdId = "";
        targetCdNm = "";
        targetSrt = "";
        modalTitle.text("");
        newCategoryName.val("");
        
        $("[name='divActiveCategoryList']")
         .empty()
         .append('<option value="" disabled>하위 카테고리 선택</option>')
         .prop('multiple', false); 
         
        $("[name='mergeActiveCategoryList']")
         .empty()
         .append('<option value="" disabled>상위 카테고리 선택</option>');
         
        modalSaveBtn.prop("disabled", false);
    };
    
    /* 카테고리 추가 (Ajax 전송) */
    var addCategory = function() {
        
        var categoryName = newCategoryName.val().trim();
        
        if(categoryName === "") {
            alert("추가할 카테고리 이름을 입력해 주세요.");
            newCategoryName.val("");
            newCategoryName.focus();
            return;
        }
        modalSaveBtn.prop("disabled", true);
        
        var dataToSend = {
            adminId : adminId, 
            cdNm : categoryName
        }
        
        $.ajax({
            url: "/admin/category-manage/add", 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(dataToSend), 
            
            success: function(response) {
                alert(categoryName + " 카테고리 추가를 완료하였습니다.");
                location.reload();
            }, 
            complete: function() {
                modalSaveBtn.prop("disabled", false);
            }
        });
    };
    
    /* 카테고리 분할 (Ajax 전송) */
    var divCategory = function() {
        
        var selectedOptionCdId = $("option[name='categoryOption']:selected").val();
        var selectedOptionCdNm = $("option[name='categoryOption']:selected").text();
        
        if(!selectedOptionCdId) {
            alert("분할할 카테고리를 선택해 주세요.");
            return;
        }
        
        modalSaveBtn.prop("disabled", true).text("분할 요청 중...");
        
        var dataToSend = {
            adminId : adminId, 
            cdId : selectedOptionCdId
        };
        
        $.ajax({
            url: "/admin/category-manage/div", 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(dataToSend), 
            
            success: function(response) {
                alert(targetCdNm + "에서 " + selectedOptionCdNm + " 분할을 완료하였습니다.");
                location.reload();
            }, 
            complete: function() {
                modalSaveBtn.prop("disabled", false);
            }
        });
        
    };
    
    /* 카테고리 병합 (Ajax 전송) */
    var mergeCategory = function() {
        
        var selectedOptionCdId = $("option[name='categoryOption']:selected").val();
        var selectedOptionCdNm = $("option[name='categoryOption']:selected").text();
        
        if(!selectedOptionCdId) {
            alert("병합할 카테고리를 선택해 주세요.");
            return;
        }
        
        modalSaveBtn.prop("disabled", true).text("병합 요청 중...");
        
        var dataToSend = {
            adminId : adminId, 
            prntCdId: selectedOptionCdId, 
            srt: targetSrt, 
            cdId : targetCdId
        };
        
        $.ajax({
            url: "/admin/category-manage/merge", 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(dataToSend), 
            
            success: function(response) {
                alert(targetCdNm + "을(를) " + selectedOptionCdNm + "에 병합을 완료하였습니다.");
                location.reload();
            }, 
            complete: function() {
                modalSaveBtn.prop("disabled", false);
            }
        });
    };
    
    // 페이지 로드 시 기존 카테고리 순서 저장 (최초 1회 실행)
    saveOriginalOrder();
    
    // 페이지 로드 시 순위 텍스트 업데이트 (카테고리 순서에 맞게 SRT 값 재계산 후 표시)
    updateVisualSrt();
    
});