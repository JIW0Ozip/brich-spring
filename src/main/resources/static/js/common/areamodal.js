function makeCheckedList(insertClassName) {
    $(insertClassName).empty();
    for(i = 0; i < checkedList.length; i++) {
        cityBlock = $("<div></div>");
        cityBlock.addClass("check-city");
        
        cityName = $("<div></div>")
        cityName.text(checkedList[i].name);  
        
        deleteCity = $("<div>x</div>");
        deleteCity.addClass("delete-city");
        deleteCity.on("click", function() {
            city = $(this).closest(".check-city").children("div:first-child").text();
            checkedList = checkedList.filter((e) => e.name !== city);
            if ($(this).closest(".check-city").parents().hasClass("area-list")) {
                beforeChecked = JSON.parse(JSON.stringify(checkedList));
            }
            $(this).closest(".check-city").remove();
        });
        
        cityBlock.append(cityName);
        cityBlock.append(deleteCity);
        
        $(insertClassName).append(cityBlock);
    }
}

var checkedList = [];
var beforeChecked = [];

$().ready(function() {
    $("input[name=do-city]").on("click", function() {
        var id = $(this).attr("id");
        beforeChecked = JSON.parse(JSON.stringify(checkedList));
        
        $.get("/adv/campaign/write/" + id, function(response) {
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
                        if(checkedList.some(item => item.name === cityName)) {
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
        
    $(".area").children("div").find("button").on("click", function() {
        $(".modal").css("display", "flex");
        makeCheckedList(".checked-cities");
    });
    
    $(".area-close").on("click", function() {
        checkedList = JSON.parse(JSON.stringify(beforeChecked));
        makeCheckedList(".checked-cities");
    });
        
    $(".modal-submit").on("click", function() {
        $(".modal").css("display", "none");
        makeCheckedList(".area-list");
        // 리스트 복사 - 문자로 만들고 다시 JSON parse
        beforeChecked = JSON.parse(JSON.stringify(checkedList));
    });
});