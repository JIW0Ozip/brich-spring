var auth = null;
var startUrl = null;
var currentPswrd = null;
var newPswrd = null;
var newPswrdConfirm = null;
$().ready(function() {
    
    //권한에 따른 경로 설정
    auth = $(".account-menu-box").data("auth");
    console.log(auth);
    if(auth === 1004) {
        startUrl = "/adv/account";
    }
    else if (auth === 1002 || auth === 1003) {
        startUrl = "/blgr/account";
    }
    
    //계정 관리 페이지
    $(".account-info").on("click", function() {
        window.location.href = startUrl + "/info";
    });

    //비밀번호 재설정 페이지
    $(".reset-password").on("click", function() {
        window.location.href = startUrl + "/reset-password";
    });
    
    //구독 정보 페이지
    $(".sub-info").on("click", function() {
        window.location.href = startUrl + "/subscription-info";
    });
    
    //새로운 비밀번호로 수정
    $(".reset-password-confirm").on("click", function() {
        currentPswrd = $(".current-pswrd").val();
        newPswrd = $(".new-pswrd").val();
        newPswrdConfirm = $(".new-pswrd-confirm").val();
        
        console.log(currentPswrd, newPswrd, newPswrdConfirm);
        
        
        $.post(startUrl
             + "/reset-password"
            ,{currentPswrd : currentPswrd
                , newPswrd : newPswrd
                , newPswrdConfirm : newPswrdConfirm}
            , function(response){
             console.log(response);
            if(!response.error) {
                alert(response.body.result);
                window.location.reload();
            }
        });
    });
    
    // 지역 재설정 모달 열기
    $(".area-reset").on("click", function() {
        $(".modal").css("display", "flex");
        makeCheckedList(".checked-cities");
    });

    //카테고리 재설정 버튼 
    $(".category-reset").on("click", function() {
        $(".category-checkbox-wrapper").toggle(); // 체크박스 영역 토글
        
        // 기존 카테고리 체크 처리
        const userCategories = $(this).data("user-categories");
        
        if (userCategories) {
            // 일단 모든 체크박스 해제
            $("input[name='category-check']").prop("checked", false);
            
            // 쉼표로 구분된 문자열을 배열로 변환
            const categoryIds = userCategories.toString().split(",");
            
            // 기존 카테고리들 체크
            categoryIds.forEach(function(categoryId) {
                $("#cat-" + categoryId.trim()).prop("checked", true);
            });
        }
    });

    // 모달 닫기
    $(".modal-close").on("click", function() {
        $(".modal").css("display", "none");
    });
  
    
    // 모달 선택 버튼 
    $(".modal-submit").on("click", function() {
        $(".modal").css("display", "none");
        updateAreaPreview();  // 선택 버튼 누를 때 미리보기 업데이트
    });
    
    //일단 공통 코드 건드리기 애매하니까 account js에 작성함 
    //기존에는 adv로 시작하는데 블로거라 가져오지 못하는 상황
    $("input[name=do-city]").on("click", function() {
            var id = $(this).attr("id");
            
            $.get(startUrl + "/info/" + id, function(response) {
                $(".city-gu-gun").empty();
                if(response.body) {
                    city = response.body;
                    for(i = 0; i < city.length; i++) {
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
                            if(checkedList.includes(cityName)) {
                                // 리스트 특정 값 제거 방법 : 삭제해야하는 value와 안겹친 경우만 array로 다시 반환
                                checkedList = checkedList.filter((e) => e.name !== cityName);
                            }
                            else if (checkedList.length === arCnt) {
                                alert(arCnt + "개를 선택하셨습니다.");
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
        
        // 계정 정보 수정 확인
        $(".modify-confirm").on("click", function() {
            // 지역 코드 수집
            const areaCodes = checkedList.map(item => item.code);
            
            // 카테고리 코드 수집
            const categoryCodes = [];
            $("input[name='category-check']:checked").each(function() {
                categoryCodes.push($(this).val());
            });
            
            console.log("지역 코드:", areaCodes);
            console.log("카테고리 코드:", categoryCodes);
            
            $.post(startUrl + "/info/update", 
                { 
                    area: areaCodes,
                    cdIdList: categoryCodes
                },
                function(response) {
                    if(!response.error) {
                        alert(response.body);
                        window.location.reload();
                    } else {
                        alert("수정 실패");
                    }
                }
            );
        });
        
        // 지역 미리보기 업데이트 함수
        function updateAreaPreview() {
            $(".area-preview-list").empty(); 
            checkedList.forEach(function(area) {
               const areaItem = $("<div></div>");
               areaItem.addClass("select-list-item");
               areaItem.text(area.name);
               $(".area-preview-list").append(areaItem);
           });
        }
});