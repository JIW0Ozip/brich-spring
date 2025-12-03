$().ready(function() {
    // 신고하기 이전 페이지로 이동
    $(".btn-back").on("click", function() {
        window.history.back();
    });
    

       // 신고 내용 보기 버튼 클릭
    $(".f5.detail").on("click", function() {
        var reportId = $(this).data("report-id");
        window.location.href = "/report/view/" + reportId;
    });
    
    $(".fileInput").on("change", function() {
           var files = this.files;
           var fileList = $(".fileList");
           fileList.empty(); // 초기화

           $.each(files, function(index, file) {
               fileList.append("<div style='font-size: 10px'>" + file.name + "</div>");
           });
       });
    
    // 신고하기
    $(".btn-report").on("click", function(){
        // 필수 입력 검증
        var rptTitle = $("#rptTitle").val().trim();
        var rptRsn = $("#rptRsn").val();
        var rptCn = $("#rptCn").val().trim();
        
        if(!rptTitle) {
            alert("신고 제목을 입력해주세요.");
            $("#rptTitle").focus();
            return;
        }
        
        if(!rptRsn) {
            alert("신고 사유를 선택해주세요.");
            $("#rptRsn").focus();
            return;
        }
        
        if(!rptCn) {
            alert("신고 내용을 입력해주세요.");
            $("#rptCn").focus();
            return;
        }
        
        // FormData 객체 생성 (파일 업로드용)
        var formData = new FormData();
        formData.append("rptTitle", rptTitle);
        formData.append("rptedUsrId", $("#rptedUsrIdHidden").val());
        formData.append("rptRsn", rptRsn);
        formData.append("rptCn", rptCn);
        
        // 파일 추가
        var files = $("#reportFiles")[0].files;
        if(files.length > 0) {
            for(var i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
            }
        }
        
        // AJAX POST 요청
        $.ajax({
            url: "/report/write",
            type: "POST",
            data: formData,
            processData: false, 
            contentType: false, 
            success: function(response) {
                alert("신고가 접수되었습니다.");
                window.location.href = "/report/list";
            },
            error: function(error) {
                alert("신고 접수에 실패했습니다. 다시 시도해주세요.");
                console.error("Error:", error);
            }
        });
    });
});