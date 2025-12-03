/**
 * 
 */
$().ready(function() {
    var category = $("input[name='category']");
    var sortBy = $("select[name='sortBy']");
    var searchKeyword = $("input[name='searchKeyword']");
    var searchButton = $(".search-button");
    var hereCategory = new URLSearchParams(window.location.search).get('category') || '';
    var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/');    
    
    if (here.includes('/admin/campaign-list')) {
      $('.campaign-status').removeClass('display-none');
    }
    
    $(".category-seleted-box-all").removeClass('visibility-hidden');
    
    $('.category-seleted-box').each(function () {
        
        console.log("hereCategory: " + hereCategory);
        
      if($(this).data('category-menu') === hereCategory){
        
        if( ($(this).data('category-menu') && hereCategory) === "all") {
            $(".category-seleted-box-all").removeClass('visibility-hidden');
            return;
        }
        
         $(".category-seleted-box-"+hereCategory).removeClass('visibility-hidden');
         $(".category-seleted-box-all").addClass('visibility-hidden');
       }
    });
    
    category.on("change", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam ;
        window.location.href = window.location.pathname + searchParam;
    });
    sortBy.on("change", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam ;
        window.location.href = window.location.pathname + searchParam;
    });
    searchKeyword.on("keyup", function(event) {
        if (event.keyCode === 13) {
            var searchParam = $(".search-section").serialize();
            searchParam = "?" + searchParam ;
            window.location.href = window.location.pathname + searchParam;
        }
    });
    searchButton.on("click", function() {
        var searchParam = $(".search-section").serialize();
        searchParam = "?" + searchParam 
        window.location.href = window.location.pathname + searchParam;
    });

    $(document).on("click", ".campaign-main-block", function () {
        const cmpnId = $(this).data("cmpn-id");
        console.log("cmpnId: " + cmpnId);
        window.location.href = "/admin/campaign-detail/" + cmpnId;
    });
    
    $(".status--draft").on("click",function() {
        $(".submit-modal-form").removeClass("display-none");
    })
    
    $(".status--rejected").on("click",function(){
        $(".re-submit-modal-form").removeClass("display-none");
    })
    
    
    //--------------------------------
    let pageNo = 0;
    let isLoading = false;
    let hasMore = true;

    $(window).on("scroll", function () {
        if (!isLoading && hasMore && $(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            isLoading = true;
            pageNo++;
    
            const params = $(".search-section").serialize() + "&pageNo=" + pageNo + "&listSize=16";
    
            $.get("/admin/campaign-list", params, function (html) {
                const newContent = $(html).find(".campaign-main-list-area").html();
    
                if ($.trim(newContent) === "") {
                    hasMore = false;
                } else {
                    $(".campaign-main-list-area").append(newContent);
                }
              isLoading = false;
            });
        }
    });
    
});
