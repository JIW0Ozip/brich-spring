/**
 * 회원 관리 - 회원 상세 (광고주) 가입 승인/반려 처리
 */
$().ready(function() {
    
    $(".regist-yn-group-btn").on("click", function() {
        
        var action = $(this).data("action");
        var usrId = $("#usrId").val();
        
        var updateStatusCode;
        var actionText;
        
        // 모달 띄우기
        // 모달 닫기
        // 모달 바깥쪽 클릭 시 닫기
        // 모달 확인 버튼
        
        if(action === "approve") {
            updateStatusCode = "1004";
            actionText = "승인";
        }
        else if(action === "reject") {
            updateStatusCode = "1008";
            actionText = "반려";
        }
        else {
            // error
        }
        
        // 모달에서 확인 버튼 클릭 시 실행
        var requestData = {usrId : usrId, autr : updateStatusCode};
        
        $.ajax({
            url: "/admin/advertiser_regist_process", 
            method: "POST", 
            contentType: "application/json",
            data: JSON.stringify(requestData),
            
            success: function(response) {
                var isSuccess = response.body;
                
                if(isSuccess) {
                    var message = actionText + "처리 완료"
                    
                    if(actionText === "승인") {
                        alert(message);
                        
                        window.location.reload();
                    } else {
                        // 반려의 경우 리스트 페이지로 이동
                        message += ", 목록으로 돌아갑니다.";
                        alert(message);
                        
                        window.location.href = "/admin/user_list";
                    }
                }
                else {
                    alert("처리 요청에 실패했습니다.")
                }
            }, 
            
            error: function(xhr) {
                // 아직 안 함
            }
            
        });
        
    });
    
});