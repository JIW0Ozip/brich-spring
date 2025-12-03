var categoryCode = null;
var url = null;
var searchString = null;
var startIndex = null;
var startSearchIndex = null;
var searchResult = null;
$().ready(function() {
    url = window.location.href;
    searchString = "category=";
    startIndex = url.indexOf(searchString);
    console.log(startIndex)
    //queryString 없을 경우 제외
    if(startIndex !== -1) {
        startSearchIndex = startIndex + searchString.length;
        searchResult = url.substring(startSearchIndex);
    }
    
    //문의 목록으로 이동
    $(".inqr-list").on("click", function() {
        window.location.href = "/help/inqr/list";
    });

    // 문의 내용 보기 버튼 클릭
    $(".f4.detail").on("click", function() {
        var inqrId = $(this).data("inqr-id");
        window.location.href = "/help/inqr/view/" + inqrId;
    });
    //문의 작성으로 이동
    $(".inqr-write").on("click", function() {
        window.location.href = "/help/inqr/write";
    });
    //도움말 목록으로 이동
    $(".faq-list").on("click", function() {
        window.location.href = "/help/faq";
    });

    // FAQ 아코디언 기능
    $(".faq-title").on("click", function() {
        console.log("클릭됨!");
        var faqItem = $(this).parent();
        var faqCn = $(this).next(".faq-cn");

        // 한 번에 하나만 열리게 하려면
        if (!faqItem.hasClass("active")) {
            $(".faq-item").removeClass("active");
            $(".faq-cn").slideUp(50);

            faqItem.addClass("active");
            faqCn.slideDown(50);
        } else {
            faqItem.removeClass("active");
            faqCn.slideUp(50);
        }
    });
    
    $(".category-item").on("click", function() {
        categoryCode = $(this).data("category-id");
        window.location.href = "/help/faq?category=" + categoryCode;
    });
    
    //선택한 카테고리 포커스
    $(".category-item").each(function() {
        if (searchResult === $(this).data("category-id") 
            || (searchResult === null && $(this).data("category-id") === "")) {
            $(this).addClass("current-url");
        }
    });
    

    // 문의하기
    $(".btn-submit").on("click", function() {
        // 필수 입력 검증
        var inqrTitle = $("#inqrTitle").val().trim();
        var inqrCtg = $("#inqrCtg").val();
        var inqrCn = $("#inqrCn").val().trim();

        if (!inqrTitle) {
            alert("문의 제목을 입력해주세요.");
            $("#inqrTitle").focus();
            return;
        }

        if (!inqrCtg) {
            alert("문의 유형을 선택해주세요.");
            $("#inqrCtg").focus();
            return;
        }

        if (!inqrCn) {
            alert("문의 내용을 입력해주세요.");
            $("#inqrCn").focus();
            return;
        }

        

        // FormData 객체 생성 (파일 업로드용)
        var formData = new FormData();
        formData.append("inqrTitle", inqrTitle);
        formData.append("inqrUsrId", $("#InqrUsrIdHidden").val());
        formData.append("inqrCtg", inqrCtg);
        formData.append("inqrCn", inqrCn);

        // 파일 추가
        var files = $("#inqrFiles")[0].files;
        if (files.length > 0) {
            for (var i = 0; i < files.length; i++) {
                formData.append("file", files[i]);
            }
        }

        // AJAX POST 요청
        $.ajax({
            url: "/help/inqr/write",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                alert(response.body);
                window.location.href = "/help/inqr/list";
            },
            error: function(error) {
                alert(response.body);
                console.error("Error:", error);
            }
        });
    });
});