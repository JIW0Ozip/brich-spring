$().ready(function() {
    var currentUrl = window.location.href;
    $(".role-box").on("click", function() {
        var role = $(this).data("role")
        window.location.href = "/terms/" + role;
    });

    $("#check-all").on("click", function() {
        if ($(this).is(':checked')) {
            $("#personal").prop('checked', true);
            $("#term").prop('checked', true);
        }
        else {
            $("#personal").prop('checked', false);
            $("#term").prop('checked', false);
        }
    });
    $("input[type='checkbox'][name='check']").on("change", function() {
        var length = $("input[type='checkbox'][name='check']:not(:checked)").length;
        if (length === 0) {
            $("#check-all").prop("checked", true);
        } else {
            $("#check-all").prop("checked", false);
        }
    });
    $("#fileInput").on("change", function() {
        var files = this.files;
        var $fileList = $("#fileList");
        $fileList.empty(); // 초기화

        $.each(files, function(index, file) {
            $fileList.append("<div>" + file.name + "</div>");
        });
    });
    // 지역 추가 버튼 클릭
    $(".add-area-btn").on("click", function() {
        $(".modal").css("display", "flex");
        makeCheckedList(".checked-cities");
    });

    // 모달 선택 버튼
    $(".modal-submit").on("click", function() {
        $(".modal").css("display", "none");
        updateSelectedAreaList();
    });

    // 모달 닫기 버튼
    $(".modal-close").on("click", function() {
        $(".modal").css("display", "none");
    });

    // 시/도 클릭 시 하위 시/군/구 가져오기 
    $("input[name=do-city]").on("click", function() {
        var id = $(this).attr("id");

        $.get("/regist/area/" + id, function(response) {
            console.log("sdasdsda")
            console.log(response);
            $(".city-gu-gun").empty();
            if (response.body) {
                city = response.body;
                for (i = 0; i < city.length; i++) {
                    cityBox = $("<input>");
                    cityBox.attr("type", "checkbox");
                    cityBox.attr("name", "city-gu-gun");
                    cityBox.attr("id", city[i].cdNm);
                    cityBox.data("city-code", city[i].cdId);
                    cityBox.on("click", function() {
                        doName = $("input[name=do-city]:checked").data("do-name");
                        cityName = doName + " " + $(this).attr("id");
                        cityCode = $(this).data("city-code");

                        var arCnt = 3;
                        if (checkedList.some(e => e.name === cityName)) {
                            checkedList = checkedList.filter((e) => e.name !== cityName);
                        }
                        else if (checkedList.length === arCnt) {
                            alert(arCnt + "개를 선택하셨습니다.");
                            $(this).prop("checked", false);
                        }
                        else {
                            cityInfo = {
                                name: cityName,
                                code: cityCode
                            };
                            checkedList.push(cityInfo);
                        }

                        makeCheckedList(".checked-cities");
                    });

                    cityLabel = $("<label></label>");
                    cityLabel.attr("for", city[i].cdNm);
                    cityLabel.text(city[i].cdNm);

                    $(".city-gu-gun").append(cityBox);
                    $(".city-gu-gun").append(cityLabel);
                }
            }
        });
    });


    // 선택된 지역 목록 업데이트
    function updateSelectedAreaList() {
        $(".selected-area-list").empty();

        checkedList.forEach(function(area) {
            const areaItem = $("<div></div>");
            areaItem.addClass("select-list-item");
            areaItem.text(area.name);
            $(".selected-area-list").append(areaItem);
        });
    }

    // 회원가입 버튼 클릭 시 (기존 코드 수정)
    $(".regist-btn").on("click", function() {
        // 선택된 지역을 hidden input으로 추가
        $(".hidden-area-list").empty();

        checkedList.forEach(function(area) {
            const hidden = $("<input />");
            hidden.attr("type", "hidden");
            hidden.attr("name", "areaList");
            hidden.attr("value", area.code);
            $(".hidden-area-list").append(hidden);
        });

        $(".user-regist-form").submit();
    });
    $(".next-btn").on("click", function() {
        var role = $(this).data("role");
        if ($("#personal").is(':checked') && $("#term").is(':checked')) {
            console.log($("#personal").val())
            window.location.href = "/regist/" + role;
        }
        else {
            alert("모두 동의해야합니다!!!");
        }
    });

    $(".email-send").on("click", function() {
        var email = $("#email").val();
        var timerHtml = $(".email-check-timer");
        var duplicate = null;
        $.get("/email/duplicate/" + email, function(response) {
            if (response.body === false && currentUrl.includes("regist")) {
                duplicate = true;
                timerHtml.text("이미 등록된 이메일입니다.");
                return;
            }

            if (email !== "" && /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/g.test(email)) {
                var totalSeconds = 180; //3분
                var timerInterval;
                function startTimer() {
                    timerInterval = setInterval(function() {
                        var minutes = Math.floor(totalSeconds / 60);
                        var seconds = totalSeconds % 60;

                        // 2자리 숫자 포맷
                        var display =
                            (minutes < 10 ? "0" + minutes : minutes) + ":" +
                            (seconds < 10 ? "0" + seconds : seconds);

                        timerHtml.text(display);

                        totalSeconds--;

                        if (totalSeconds < 0) {
                            clearInterval(timerInterval);
                            timerHtml.text("시간 만료");
                        }
                    }, 1000);
                }
                // 타이머 시작
                startTimer();
            }
            else {
                timerHtml.text("적절하지 않은 이메일입니다.")
            }
        });
        $.post("/email/send?email=" + email, function() { });
    });

    $(".email-verify").on("click", function() {
        var email = $("#email").val();
        var code = $("#email-confirm").val();
        $.post("/email/verify?email=" + email + "&code=" + code, function(response) {
            console.log(response);
            var isSuccess = response.body.success;
            var message = response.body.message;
            if (isSuccess === true) {
                $(".email-check-timer").remove();
                $(".email-confirm-message").text("인증완료");
                $("#email-confirm").val("OK");
                $("#email-confirm").closest(".right-flex").hide();
            }
            else {
                $(".email-confirm-message").text(message);
            }
        });
    });

    $(".find-btn").on("click", function() {
        var name = $("#name").val();
        if ($("#name").val() === ""
            || $("#email").val() === ""
            || $("email-confirm").val() === "") {
            alert("모든 값을 입력하세요!");
        } else {
            window.location.href = "/find/id/" + ((name !== "") ? name : "non");
        }
    });

    $(".reset-password-btn").on("click", function() {
        window.location.href = "/reset/password"
    });

    $(".go-login-btn").on("click", function() {
        window.location.href = "/login";
    });

    $(".do-reset-btn").on("click", function() {
        if ($("#id").val() === ""
            || $("#email").val() === ""
            || $("#email-confirm").val() === ""
            || $("#password").val() === ""
            || $("#password-confirm").val() === "") {
            alert("모든 값을 입력해주세요!");
        }
        else if ($("#email-confirm").val() === "OK"
            && $("#password").val() === $("#password-confirm").val()) {
            alert("비밀번호가 재설정 되었습니다.")
            $(".user-regist-form").submit();
        }
    });
});
