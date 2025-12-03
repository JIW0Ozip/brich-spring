/**
 * 
 */
$().ready(function() {
    // alert("!");
    
    var adminId = $("#login_usrId").val();
    var inqrId = $("#inqrId").val();
    
    var fileCount = 0;
    var addNewFileBtn = $("#addNewFileBtn");
    
    // 파일 첨부
    $(addNewFileBtn).on("click", function() {
        
        var fileInputList = $("#fileInputList");
        var newAddedFileList = $("#newAddedFileList");
        
        fileCount++;
        var fileInputId = "ansrFileInput_" + fileCount;
        var fileSpanId = "ansrFileSpan_" + fileCount;
        
        var fileInput = $("<input type='file' name='newAnsrFiles'/>")
                         .attr("id", fileInputId).hide();
                         
        fileInput.trigger("click");
                                 
        var fileItemSpan = $("<span class='file-item'></span>")
                            .attr("id", fileSpanId);
        
        $(fileInputList).append(fileInput);
        $(newAddedFileList).append(fileItemSpan);
        
        $(fileInput).on("change", function() {
            
            var removeBtn = $("<button type='button' class='file-remove-btn'>X</button>");
            
            var files = $(this).get(0).files;
            
            if(files.length > 0) {
                $(fileItemSpan).append("&#128196;" + files[0].name);
                $(fileItemSpan).append(removeBtn);
                
                $(removeBtn).on("click", function() {
                    
                    $(fileInput).remove();
                    $(fileItemSpan).remove();
                });
                
            }
            else {
                $(fileInput).remove();
                $(fileItemSpan).remove();
                fileCount--;
            }
        });
    });    // 파일 첨부 끝
    
    
    // 답변 등록
    $(".ansr-save-btn").on("click", function() {
        
        var ansrCn = $("#ansr_input").val();
        
        if(ansrCn.trim() === "") {
            
            alert("답변 내용을 입력해 주세요.");
            ansrCn.focus();
            return false;
        }
        
        if(!confirm("답변을 등록하시겠습니까?")) {
            return false;
        }
        else {
            
            var formData = new FormData();
            
            formData.append("ansrUsrId", adminId);
            formData.append("inqrId", inqrId);
            formData.append("ansrCn", ansrCn);
            
            $(fileInputList).find("input[name='newAnsrFiles']").each(function() {
                
                if(this.files && this.files.length > 0) {
                    formData.append("files", this.files[0]);
                }
            });
            
            $.ajax({
                url: "/admin/ansr_regist_process/" + inqrId, 
                method: "POST", 
                data: formData, 
                processData: false, 
                contentType: false, 
                
                success: function(response) {
                    alert("답변 등록 완료\n답변 등록 관리자: " + adminId);
                    location.reload();
                }, 
                error: function() {
                    alert("답변 등록 실패\n: Ajax error");
                }
            });
        }
        
    });
    
});