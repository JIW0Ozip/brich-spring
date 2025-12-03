$().ready(function() {
    var paginatorSearchForm = $(".paginator").data("search-form-class");
    
    $(paginatorSearchForm).find(".search-button").on("click", function() {
        // form 안에 있는 select, input을 queryStringParameter로 만든다.
        var searchParam = $(this).closest(paginatorSearchForm)
                                 .serialize();  // serialize() : queryStringParameter로 만들어줌
                                 
        var listSize = $(".paginator").find(".page-list-size").val();
        searchParam = "?" + searchParam + "&pageNo=0&listSize=" + listSize;

        window.location.href = window.location.pathname + searchParam;
    })
    
    $(".paginator").find("a").attr("href", "javascript:void(0);")   // 클릭했을 때 아무 동작도 안함
                             .on("click", function() {
                                
                                 var pageNo = $(this).data("page-no");
                                 var listSize = 10;
                                 
                                 if (window.location.pathname === "/adv/campaign/list") {
                                    listSize = 8;
                                 }
                                 
                                 url = "";
                                 if (window.location.search === "") {
                                    url = window.location.pathname + "?pageNo=" + pageNo + "&listSize=" + listSize;
                                 }
                                 else {
                                     url = new URL(window.location.href);
                                     searchParam = url.searchParams;
                                     searchParam.set("pageNo", pageNo);
                                     searchParam.set("listSize", listSize);
                                     url = url.toString();
                                 }
                                 window.location.href = url;
                             });
    
    $(".paginator").find(".page-list-size").on("change", function() {    // change : selected가 변경될 때 발생하는 이벤트
        var searchFormClass = $(this).closest(".paginator").data("search-form-class");
        var searchParam = $(searchFormClass).serialize();
        window.location.href = window.location.pathname
                               + "?" + searchParam 
                               + "&pageNo=0&listSize=" + $(this).val();
    });
});