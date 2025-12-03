/**
 * 
 */
$().ready(function() {
    
    var adminId = $("#adminId").val();
    var rptId = $("#rptId").val();
    var rptedUsrId = $("#rptedUsrId").val();
    
    // 신고 처리 (기각/경고/정지)
    $(".penalty-btn").on("click", function() {
        
        var selectedpenalty = $("input[name='penalty-option']:checked").val();
        var penaltyKeyword;
        
        if(!selectedpenalty) {
            alert("처리 유형을 선택해 주세요.");
            return false;
        }
        
        if(!confirm("정말 처리하시겠습니까?")) {
            return false;
        }
        else {
            if(selectedpenalty === "dismiss") {
                penaltyKeyword = "기각";
            }
            else if(selectedpenalty === "warning") {
                penaltyKeyword = "경고";
            }
            else if(selectedpenalty === "ban") {
                penaltyKeyword = "영구 정지";
                
                if(!confirm("영구 정지는 되돌릴 수 없습니다.\n계속 진행하시겠습니까?")) {
                    return false;
                }
            }
            
            var requestData = {
                adminId: adminId,
                rptId: rptId, 
                rptedUsrId: rptedUsrId,
                penaltyOption: selectedpenalty, 
                penaltyKeyword: penaltyKeyword
            };
            
            $.ajax({
                url: "/admin/report_penalty_process/" + rptId, 
                method: "POST", 
                contentType: "application/json", 
                data: JSON.stringify(requestData), 
                
                success: function(response) {
                    alert("해당 신고 건은 " + penaltyKeyword + " 처리되었습니다." + 
                          "\n처리한 관리자: " + adminId);
                          
                    location.reload();
                }
            });
        }
        
    });
    
});