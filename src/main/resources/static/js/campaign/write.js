$().ready(function() {
    submitAutoActive();
    
    $("input[name=address-check]").on("change", function() {
        roadAddr = $("input[name=roadAddress]");
        detailAddr = $("input[name=detailAddress]");
        
        if ($(this).attr("id") === "address-check") {
            roadAddr.removeAttr("disabled");
            detailAddr.removeAttr("disabled");
        }
        else if ($(this).attr("id") === "address-uncheck") {
            roadAddr.val("");
            detailAddr.val("");
            roadAddr.attr("disabled", "disabled");
            detailAddr.attr("disabled", "disabled");
        }
    });
    
    $("input[name=roadAddress]").on("click", function() {
        $(this).val("");
        that = $(this);
        new daum.Postcode({
            oncomplete: function(data) {
                roadAddress = data.roadAddress ? data.roadAddress : data.autoRoadAddress;
                that.val(roadAddress);
            }
        }).open();
    });
    
    
    var price = parseInt($("input[name=rcrtPrsnn]").data("person-price"));
    $(".rcrtPrsnn-price").text($("input[name=rcrtPrsnn]").val() * price);
    
    $("input[name=rcrtPrsnn]").on("keyup", function() {
        var price = parseInt($(this).data("person-price"));
        $(".rcrtPrsnn-price").text($(this).val() * price);
    });
    
    $(".cancel-button").on("click",function() {
        window.location.href = "/adv/campaign/list";
    });
    
    $(".submit-button").on("click", function() {
        if($("input[name=ctgCd]:checked").length === 0) {
            alert("카테고리를 선택하세요.");
        }
        else if (parseInt($("input[name=rcrtPrsnn]").val(), 10) === 0) {
            alert("모집 인원은 0명일 수 없습니다.");
        }
        
        else {
            for(i = 0; i < checkedList.length; i++) {
                hidden = $("<input />");
                hidden.attr("type", "hidden");
                hidden.attr("name", "area");
                hidden.attr("value", checkedList[i].code);
                $(".hidden-area-list").append(hidden);
            }
            
            var urlParams = new URLSearchParams(window.location.search);
            var sttsCd = urlParams.get("sttsCd");
            if (sttsCd === "2008" || sttsCd === "2003") {
                var prevCmpnId = urlParams.get("cmpnId");
                $("form").attr("action", "/adv/campaign/modify?prevCmpnId=" + prevCmpnId + "&sttsCd=" + sttsCd);
            }
            else {
                $("form").attr("action", "/adv/campaign/write");
            }
            
            $("#campaign-submit").submit();
        }
    });
    
    // 임시저장
    $(".temporary-button").on("click", function() {
        var title = $("input[name=cmpnTitle]");
        if (title.val() === '') { title.val("임시저장"); }
        if ($("input[name=ctgCd]:checked").length === 0) {
            $("input[name=ctgCd]").attr("checked", "checked");
        }
        $("textarea.require-input").each(function() {
            if ($(this).text() === "") {
                $(this).text("임시저장 빈 칸");
            }
        });
        
        var urlParams = new URLSearchParams(window.location.search);
        var sttsCd = urlParams.get("sttsCd");
        if (sttsCd === "2008" || sttsCd === "2003") {
            var prevCmpnId = urlParams.get("cmpnId");
            $("form").attr("action", "/adv/campaign/temporary?prevCmpnId=" + prevCmpnId + "&sttsCd=" + sttsCd);
        }
        else {
            $("form").attr("action", "/adv/campaign/temporary");
        }
        $("#campaign-submit").submit();
    });
    
    if ($("input[type=number]").val() === "") {
        $("input[type=number]").val(0);
    }
    
    $("input[type=number]").on("keyup", function() {
        if ($(this).val() < 0) {
            $(this).val(0);
        }
        if ($(this).val() === "0") {
            $(this).val("");
        }
    })
    .on("focus", function() {
        if ($(this).val() === "0") {
                $(this).val("");
                $(this).addClass("require-empty");
            }
    })
    .on("blur", function() {
        if($(this).val() === "") {
            $(this).val(0);
            $(this).removeClass("require-empty");
        }
    })
    .on("change", function() {
        $(this).trigger("keyup");
    });
    
    $("input[name=rcrtPrsnn]").on("keyup", function() {
        if ($(this).val() > 1000) {
            $(this).val(999);
        }
    });
    
    $("input[name=offrPrc]").on("keyup", function() {
        if ($(this).val() > 100000000) {
            $(this).val(99999999);
        }
    });
});