/**
 * 회원 상세 정보 페이지에서 경고/정지 처리
 */
$().ready(function() {
    
    var adminId = $("#login_usrId").val();
    var usrId = $("#usrId").val();
    
    $(".penalty-btn").on("click", function() {
        
        var selectedpenalty = $("input[name='penalty-option']:checked").val();
        var penaltyKeyword;
        
        if(!selectedpenalty) {
            alert("처리 유형을 선택해 주세요.");
            return false;
        }
        
        if(selectedpenalty === "warning") {
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
            usrId: usrId, 
            penaltyOption: selectedpenalty, 
            penaltyKeyword: penaltyKeyword
        };
        
        $.ajax({
            url: "/admin/user_penalty_process/" + usrId, 
            method: "POST", 
            contentType: "application/json", 
            data: JSON.stringify(requestData), 
            
            success: function(response) {
                alert("해당 사용자를 " + penaltyKeyword + " 처리했습니다." + 
                      "\n처리한 관리자: " + adminId);
                      
                location.href = "/admin/user_list";
            }
       });
    });
    
});