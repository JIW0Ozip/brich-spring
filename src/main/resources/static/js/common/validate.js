$().ready(function() {

    $("label.require").next().on("keyup", function() {
        var value = $(this).val();
        $(this).next(".validate-require").remove();
        if (value === "") {
            if($(this).attr("class") === "logId"){
                $(".duplicate-id-check").remove();
            }
            $(this).after($("<span class='validate-require'>필수 입력입니다!</span>"));
            $(this).next(".validate-require").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
    });
    
    $("input.require-input").on("keyup", function() {
        var value = $(this).val();
        if (value === "") {
            $(this).addClass("require-empty");
        }
        else {
            $(this).removeClass("require-empty");
        }
        submitAutoActive();
    });
    
    $("textarea.require-input").on("keyup", function() {
        var value = $(this).val();
        if (value === "") {
            $(this).addClass("require-empty");
        }
        else {
            $(this).removeClass("require-empty");
        }
        submitAutoActive();
    });

    $("#password-confirm").on("keyup", function() {
        $(this).next(".validate-password-confirm").remove();
        if ($(this).closest(".input-flex").children("#password").val() !== $(this).val()) {
            $(this).after($("<span class='validate-password-confirm'>비밀번호가 일치하지 않습니다.</span>"));
            $(this).next(".validate-password-confirm").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
        else {
            $(this).after($("<span class='validate-password-confirm'>비밀번호가 일치합니다.</span>"));
            $(this).next(".validate-password-confirm").css({
                "color": "#FF0000",   // 빨강
                "font-size": "14px"   // 14px
            });
        }
    });
    
    $("#password").on("keyup", function() {
            $(this).next().next(".validate-password-confirm").remove();
            if($("#password-confirm").val() !== "") {
                if ($(this).closest(".input-flex").children("#password-confirm").val() !== $(this).val()) {
                    $(this).next().after($("<span class='validate-password-confirm'>비밀번호가 일치하지 않습니다.</span>"));
                    $(this).next().next(".validate-password-confirm").css({
                        "color": "#FF0000",   // 빨강
                        "font-size": "14px"   // 14px
                    });
                }
                else {
                    $(this).next().after($("<span class='validate-password-confirm'>비밀번호가 일치합니다.</span>"));
                    $(this).next().next(".validate-password-confirm").css({
                        "color": "#FF0000",   // 빨강
                        "font-size": "14px"   // 14px
                    });
                }
            }
        });
    
    $(".duplicate-id").parent().on("click", function() {
        var url = $(".logId").val();
        var btn = $(this);
        $.get("/duplicate-id/check?logId=" + url, function(response) {
            btn.closest(".right-flex").next(".duplicate-id-check").remove();
            console.log(response.body);
            if (response.body === 1) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>이미 존재하는 아이디입니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",   // 빨강
                    "font-size": "14px"   // 14px
                });
            }
            else if (response.body === 0) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>사용 가능한 아이디입니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",   // 빨강
                    "font-size": "14px"   // 14px
                });
            }
            else if (response.body === 2) {
                btn.closest(".right-flex").after("<span class='duplicate-id-check'>아이디는 8~16자까지 가능하며 영문자와 숫자를 혼합해야합니다.</span>");
                btn.closest(".right-flex").next(".duplicate-id-check").css({
                    "color": "#FF0000",  
                    "font-size": "14px"   
                });
            }
        });
    });
    
    $(".modal-close").on("click", function() {
        $(".modal").css("display", "none");
        $(".deny-container").css("display", "none");
        $(".post-url").children("a").remove();
        $(".button-list").empty();
    });
    
    $(".id-search").children("img").on("click", function() {
             keyword = $(this).closest(".id-search").children("input[type=text]").val();
             url = "";
             if (window.location.search === "") {
                url = window.location.pathname + "?searchKeyword=" + keyword;
             }
             else {
                 url = new URL(window.location.href);
                 searchParam = url.searchParams;
                 searchParam.set("searchKeyword", keyword);
                 searchParam.set("pageNo", 0);
                 url = url.toString();
             }
             window.location.href = url;
        });
        
    $(".campaign-block").on("click", function() {
        cmpnId = $(this).data("cmpn-id");
        if ($(this).children(".font-brown").text() === "임시저장") {
            window.location.href = "/adv/campaign/modify?cmpnId=" + cmpnId + "&sttsCd=2008";
        }
        
        else {
            window.location.href = "/campaigndetail/" + cmpnId;
        }
    });
    
    // campaignTab js
    url = window.location.href;
    if (url.includes("detail")) {
        $("#campaign-name").attr("checked", "true");
    }
    else if (url.includes("applicant")) {
        $("#campaign-applicant").attr("checked", "true");
    }
    else if (url.includes("adopt")) {
        $("#campaign-adopt").attr("checked", "true");
    }
    else if (url.includes("deny-history")) {
        $("#campaign-return-hist").attr("checked", "true");
    }
    
    $(".campaign-tab").children("input").on("click", function() {
        cmd = $(this).attr("id");
        cmpnId = $("#campaign-name").data("cmpn-id");
        url = "";
        if (cmd === "campaign-name") {
            url = "/campaigndetail/" + cmpnId;
        }
        else if (cmd === "campaign-adopt") {
            url = "/adv/campaign/adopt/" + cmpnId;
        }
        else if (cmd === "campaign-applicant") {
            url = "/adv/campaign/applicant/" + cmpnId;
        }
        else if (cmd === "campaign-return-hist") {
            url = "/adv/campaign/deny-history/" + cmpnId;
        }
        
        window.location.href = url;
    });
});

function submitAutoActive() {
    $("input.require-input").each(function() {
        if ($(this).val() === "") {
            $(this).addClass("require-empty");
        }
    });
    
    $("textarea.require-input").each(function() {
        if($(this).val() === "") {
            $(this).addClass("require-empty");
        }
    });
    var emptyLength = $(".require-empty").length;
    if (emptyLength > 0) {
        $(".auto-active").attr("disabled", "disabled");
    }
    else {
        $(".auto-active").removeAttr("disabled");
    }
}