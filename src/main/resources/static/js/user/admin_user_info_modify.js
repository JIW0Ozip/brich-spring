/**
 * 
 */
$().ready(function() {
    
    // 로그인 된 관리자의 USR_ID 값 가져오기
    var sessionUserId = $("#login_usrId").val();
    
    // 수정할 회원의 usrId, autr 값 가져오기
    var usrId = $("#usrId").val();
    var autr = $("#autr").val();
    
    
    
    
    
    var MAX_CHECK_COUNT = 3;
    
    /* 카테고리 선택 개수 제한 */
    $(".category-checkbox").on("change", function() {
        
        // 체크된 checkbox의 개수 가져오기
        var checkedCount = $(".category-checkbox:checked").length;
        
        if(checkedCount > MAX_CHECK_COUNT) {
            $(this).prop("checked", false);
            
            alert("카테고리는 최대 " + MAX_CHECK_COUNT + "개까지 선택 가능합니다.");
        }
    });
    
    
    /*
    /* 블로거 - 블로그 수동 인증 
    var inputBlogAddr = $("input[name='blgAddr']");
    var activeBtn = $(".blog-crtfctn-active-btn");
    $(".crtctn-cancel-btn").hide();
    $(".crtctn-save-btn").hide();

    // 기존 블로그 주소 값 저장해 놓기
    var currentBlogAddr = inputBlogAddr.val();
        
    /* 블로그 수동 인증 (모달 제외) 
    $(activeBtn).on("click", function() {
        
        // 1. readonly 풀기
        alert("새 블로그 주소를 입력해 주세요.");
        $(inputBlogAddr).prop("readonly", false);
        
        // 2. 기존 버튼은 hide
        //    취소/인증 완료 버튼 show, 칸은 비워두기 + focus
        activeBtn.hide();
        $(".crtctn-cancel-btn").show();
        $(".crtctn-save-btn").show();
        inputBlogAddr.val("");
        inputBlogAddr.focus();
    });
    
    // 3-1. 취소 클릭 시 버튼/readonly/value 원래대로 되돌리기
    $(".crtctn-cancel-btn").on("click", function() {
        $(inputBlogAddr).prop("readonly", true);
        
        inputBlogAddr.val(currentBlogAddr);
        
        activeBtn.show();
        $(".crtctn-cancel-btn").hide();
        $(".crtctn-save-btn").hide();
    });
    
    var isBlogAddressChange = false;
    
    // 3-2. 인증 완료 클릭 시 로그인 된 관리자 ID와 결과를 보여주며 값 업데이트
    // 값 업데이트 시 블로그 주소, 최근 인증일, 수정일 총 세 가지 정보 업데이트 
    $(".crtctn-save-btn").on("click", function() {
        
        // 새로 입력한 값 가져오기
        var newBlogAddr = inputBlogAddr.val();
        
        // 이전 블로그 인증 일시 가져오기
        var befBlgCrtfctnDt = $("#rcntBlgCrtfctnDt").val();
        
        // 비어있으면...
        if (newBlogAddr.trim() === "") {
            
            if(!confirm("블로그 주소가 비어있습니다.\n이대로 진행하시겠습니까?")) {
                return false;
            }
            else {
                // 예
            }
        }
        else if(currentBlogAddr === newBlogAddr) {
            
            alert("이전과 같은 값으로 변경할 수 없습니다.\n다시 입력해 주세요.");
            
            inputBlogAddr.val("");
            inputBlogAddr.focus();
            
            return false;
        }
        
        isBlogAddressChange = true;
        
        // Ajax로 보낼 객체 생성
        var requestData = {
            usrId: usrId, 
            isBlogAddressChange: isBlogAddressChange,
            blgAddrs: newBlogAddr,
            befAddrs: currentBlogAddr, 
            befBlgCrtfctnDt: befBlgCrtfctnDt,
            adminId: sessionUserId
        };
        
        $.ajax({
            url: "/admin/blog_passivity_certify/" + usrId, 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(requestData), 
            
            success: function(response) {
                alert("블로그 수동 인증 완료" + "\n수동 인증 처리자: " + sessionUserId + "\n새 블로그 주소: " + newBlogAddr);
                
                // 복원
                $(inputBlogAddr).prop("readonly", true);
                
                // 버튼 복원
                activeBtn.show();
                $(".crtctn-cancel-btn").hide();
                $(".crtctn-save-btn").hide();
                
                // 4. 페이지 새로고침
                location.reload();
            },
        });
    });
    */
    
    
    
    
    /* 첨부 파일(사업자 등록증 관련) */
    var FILE_CONTAINER = $("#existFileList"); // 현재 파일들을 담고 있는 div
    
    $(FILE_CONTAINER).off("click", ".file-remove-btn");
    
    /* 첨부 파일 삭제 (기존 파일, 새로 추가된 파일은 안에서 분기 처리) */
    $(FILE_CONTAINER).on("click", ".file-remove-btn", function() {
        
        var clickedBtn = $(this);
        
        // 클릭한 버튼의 data가 file-id일 경우 (기존 파일인 경우)
        if(clickedBtn.data("file-id")) {
            
            // 삭제될 파일의 data를 통해 flId 값 가져오기
            var deleteFileId = $(this).data("file-id");
            
            // 두 번 뜨는데 일단 넣음 (해결하긴 함)
            if(!confirm("기존 파일을 삭제하시겠습니까?")) {
                return false;
            }
            
            // 기존 파일 요소 제거 (<span> 태그)
            $(this).closest(".file-item").remove();

            // 삭제할 파일의 flId를 담고 있는 input 태그 또한 제거
            $("input[name='existFileIds'][value='"+ deleteFileId +"']").remove();

            // 삭제할 flId를 hidden 값으로 추가 (서버에 보낼 때 필요)
            $(FILE_CONTAINER).append("<input type='hidden' name='deleteFileIds' value='" + deleteFileId + "'/>");
            
            return false;
        }
        // 클릭한 버튼의 data가 input-id일 경우 (새로 추가된 파일인 경우)
        else if(clickedBtn.data("input-id")) {
            
            // 삭제할 파일의 임의로 설정했던 data 값 가져오기
            var inputIdToRemove = $(this).data("input-id");
            
            // 화면에 보여주는 동적 태그(fileDisplay) 삭제
            $(this).closest(".file-item").remove();
            
            // 동적 태그(newInput) 또한 삭제
            $("#" + inputIdToRemove).remove();
            
            return false;
        }
    });
    
    var newFileInputCount = 0;  // 새로 첨부되는 파일에 ID를 부여하기 위한 카운터
    
    $("#addNewFileBtn").off("click");
    
    // 새 첨부 파일 추가
    $("#addNewFileBtn").one("click", function() {
        createNewFileInput();
        
        $("#addNewFileBtn").on("click", function() {
            createNewFileInput();
        });
    });
    
    function createNewFileInput() {
        
        // 새 첨부 파일 카운트 증가
        newFileInputCount++;
        
        // input 태그 id 지정
        var inputId = "fileInput_" + newFileInputCount;
        
        // 새로 추가되는 파일의 동적 태그 생성
        var newInput = 
            $("<input type='file' name='file' class='dynamic-file-input modify-values'/>")
            .attr("id", inputId)
            .on("change", fileSelectedEvent);    // 파일이 선택될 경우 이벤트 발생
        
        // 새로 추가되는 파일 영역(div, display none)에 동적 태그(input) 추가
        $("#fileInputList").append(newInput);
        
        newInput.css("display", "block");
        
        // 어차피 첨부 파일 추가 버튼을 눌러서 발생되는 함수이기 때문에, 
        // 강제로 click 이벤트 발생시키게 함
        newInput.click();
        
        newInput.css("display", "none");
    }
    
    /* 파일이 선택된 경우 */
    // change 이벤트를 발생시킨 input의 요소를 event에 담아 가져옴
    function fileSelectedEvent(event) {
        
        // event 객체에서 파일 정보를 담아옴
        var file = event.target.files[0];
        
        // 현재 이벤트를 발생시킨 선택된 input 태그
        var currentInput = $(this);
        
        /* 사용자가 파일을 선택하지 않고 취소했을 경우 */
        if(!file) {                   // 선택된 파일이 없다면
            currentInput.remove();    // newInput으로 만들었던 input 태그 삭제
            newFileInputCount--;      // 새로 추가되는 첨부 파일 카운트 값 -1 (1, 4, 5, 6... 이런 식으로 들어가는 거 방지)
            return;
        }
        
        /* 사용자가 파일을 선택했을 경우 */
        
        // 선택된 파일의 이름 가져오기
        var fileName = file.name;
        
        // 동적 태그(input)에 등록된 id를 가져옴 
        var inputId = currentInput.attr("id");
        
        // 추가된 파일의 이름을 <span> .file-item의 형식과 동일하게 
        // 화면에 보여줄 동적 태그 생성
        var fileDisplay = $("<span id='fileDisplay_" + inputId + "' class='file-item'>" 
                            + "[" + fileName + "]"
                            + "<button type='button' class='file-remove-btn' data-input-id='" + inputId + "'>X</button>"
                            + "</span>");
        
        // 새로 등록되는 첨부 파일 영역에 위에 만든 동적 태그 집어넣기
        $("#newAddedFileList").append(fileDisplay);
    }
    
    
    
    
    
    $(".save-btn").off("click");
    /* 회원 정보 수정 */
    $(".save-btn").on("click", function(e) {
        
        e.preventDefault(); 
        e.stopPropagation();
                
        var $btn = $(this);
        $btn.prop('disabled', true);
        
        var formData = new FormData();
        
        // 정보 수정하는 관리자 ID
        formData.append("adminId", sessionUserId);
        
        // 회원 테이블 공용 정보
        formData.append("usrId", usrId);
        formData.append("logId", $("#logId").val());
        formData.append("eml", $("#eml").val());
        formData.append("nm", $("#nm").val());
        formData.append("autr", autr);
        formData.append("cmpny", $("#cmpny").val());
        formData.append("flGrpId", $("#flGrpId").val());
        
        // 회원 정보 수정 테이블 정보: 수정 사유
        formData.append("updtRsn", $("#updtRsn").val());
        
        // 블로거 카테고리 정보 (체크된 것만)
        $(".category-checkbox:checked").each(function() {
            formData.append("usrBlgCtg", $(this).val());
        });
        
        // 광고주 사업자 등록증 파일 정보
        
        // 기존 파일 중 유지할 데이터 
        $("input[name='existFileIds']").each(function() {
            formData.append("existFileIds", $(this).val());    // flId
        });
        
        // 기존 파일 중 삭제될 데이터 (hidden 타입으로 생성해 둔 동적 태그)
        $("input[name='deleteFileIds']").each(function() {
            formData.append("deleteFileIds", $(this).val());    // flId
        });
        
        // 새로 첨부된 파일 (동적 태그)
        $(FILE_CONTAINER).find(".dynamic-file-input").each(function() {
            
            // 새로 첨부된 파일이 있다면,
            if(this.files && this.files.length > 0) {
                // 추가되는 파일의 name을 newFile로 통일
                formData.append("file", this.files[0]);
            }
        });
        
        $.ajax({
            url: "/admin/user_modify/" + usrId, 
            method: "POST", 
            data: formData, 
            enctype: "multipart/form-data", 
            processData: false, 
            contentType: false, 
            complete: function() {
                // 요청 완료 시 버튼 다시 활성화 (페이지 이동 직전에만)
                // location.href 있으니까 여기서 비활성화 해제 로직 제거
            },
            success: function(response) {
                alert("회원 정보 수정 완료: " + response);
                location.href = "/admin/user_detail/" + usrId;
            }, 
            error: function() {
                $btn.prop('disabled', false); // 에러 발생 시 재활성화
            }
        });
        return false;
    });
    
    
});