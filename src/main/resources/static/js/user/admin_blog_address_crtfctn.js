/**
 * 블로그 주소 수동 인증 모달 관련 스크립트
 * 모달 HTML 구조: admin_blogger_detail.jsp에 포함됨
 */
$().ready(function() {
    
    var blogAddressInput = $("#blogAddress");
    var blogCrtfctnBtn = $(".blog-crtfctn-active-btn");
    var blogAddressModal = $("#blog-address-modal");
    var modalCloseBtn = $("#modal-close-btn");
    var modalSaveBtn = $("#modal-save-btn");

    var usrId = $("#usrId").val();
    var sessionUserId = $("#login_usrId").val();
    
    var befBlgCrtfctnDt = $("#rcntBlgCrtfctnDt").text().trim();
    
    function getCurrentBlogAddress() {
        var currentAddr = blogCrtfctnBtn.siblings("input[name='blgAddr']").val();
        return currentAddr || "";
    }

    blogCrtfctnBtn.on("click", function() {
        
        var currentBlogAddr = getCurrentBlogAddress();
        blogAddressInput.val(currentBlogAddr);
        
        blogAddressModal.show();
        blogAddressInput.focus();
    });

    function closeBlogAddressModal() {
        blogAddressInput.val("");
        blogAddressModal.hide();
    }
    
    modalCloseBtn.on("click", closeBlogAddressModal);

    blogAddressModal.on("click", function(event) {
        if (event.target.id === "blog-address-modal") {
            closeBlogAddressModal();
        }
    });

    modalSaveBtn.on("click", function() {
        var newBlogAddr = blogAddressInput.val().trim();
        var currentBlogAddr = getCurrentBlogAddress();
        
        // 비어있으면...
        if (newBlogAddr === "") {
            
            if(!confirm("블로그 주소가 비어있습니다.\n이대로 진행하시겠습니까?")) {
                return false;
            }
            // '예'를 누르면 다음으로
        }
        else if (currentBlogAddr === newBlogAddr) {
            
            alert("이전과 같은 값으로 변경할 수 없습니다.\n다시 입력해 주세요.");
            
            blogAddressInput.focus();
            
            return false;
        }
        
        if(!confirm("정말 블로그 주소 수동 인증을 진행하시겠습니까?")) {
            return false;
        }

        var isBlogAddressChange = true;

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
                
                closeBlogAddressModal(); 

                location.reload();
            },
            error: function(xhr, status, error) {
                alert("블로그 수동 인증에 실패했습니다. 관리자에게 문의하세요.");
                console.error("AJAX Error:", status, error);
            }
        });
    });
});