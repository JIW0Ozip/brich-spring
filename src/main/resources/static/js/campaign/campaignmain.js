$().ready(function() {
    var category = $("input[name='category']");
    var sortBy = $("select[name='sortBy']");
    var searchKeyword = $("input[name='searchKeyword']");
    var searchButton = $(".search-button");
    var campaignFav = $(".campaign-fav");
    var hereCategory = new URLSearchParams(window.location.search).get('category') || '';
    var campaignMainBlock = $(".campaign-main-block");
    var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/');    
    var applyBlg = $(".apply-blg");
    var applyCancelBlg = $(".apply-cancel-blg");
    var submitModalBtnOk = $(".submit-modal-btn-ok");
    var reSubmitModalBtnOk = $(".re-submit-modal-btn-ok");
	
    if (here.includes('campaignmain')) {
      $('.campaign-status').removeClass('display-none');
    }
    if (here.includes('closedcampaign')) {
      $('.campaign-fav').addClass('display-none');
    }
    
    $('.category-seleted-box').each(function () {
      if($(this).data('category-menu') === hereCategory){
         $(".category-seleted-box-"+hereCategory).removeClass('visibility-hidden');
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
    applyBlg.on("click", function(){
        var campaignId = $(".apply-blg").data("campaign-id");
        $.post("/blgr/apply/" + campaignId ,function() {
            alert("신청완료!");
            location.reload();
            $(".apply-blg").toggleClass('display-none');
            $(".apply-cancel-blg").toggleClass('display-none');
        })
    })
    applyCancelBlg.on("click", function(){
        var campaignId = $(".apply-cancel-blg").data("campaign-id");
        $.post("/blgr/apply/" + campaignId ,function() {
            alert("취소완료!");
            location.reload();
            $(".apply-cancel-blg").toggleClass('display-none');
            $(".apply-blg").toggleClass('display-none');
        })
    })

	$(document).on("click", ".campaign-main-block", function () {
	    const cmpnId = $(this).data("cmpn-id");
	    window.location.href = "/campaigndetail/" + cmpnId;
	});

	$(document).on("click", ".campaign-fav", function (e) {
	    e.stopPropagation(); 
	    const cmpnId = $(this).data("cmpn-id");
	    const $this = $(this);

	    $.ajax({
	        url: `/blgr/love/` + cmpnId,
	        type: "POST",
	        success: function () {
	            $this.find(".love-on, .love-off").toggleClass("display-none");
	        }
	    });
	});
	
	
	
	
	
	submitModalBtnOk.on("click", function() {
		var campaignId = $(".submit-modal-area").data("cmpn-id");
		
		var postData = {
		     postTitle: $("#post-title").val(),
		     postUrl: $("#post-url").val()
		 };
		$.post("/blgr/pstsubmit/" + campaignId , postData, function() {
		    alert("제출완료!");
			$(".submit-modal-form").addClass("display-none");
			location.reload();
		})
	})
	
    
    /* */
    // 파일 아이콘 클릭 시 파일 선택 창 열기
    reSubmitModalBtnOk.on('click', function (e) {
      e.preventDefault();

      const campaignId = $('.submit-modal-area').data('cmpn-id');
      const form = $('.re-submit-modal-form')[0];
      const fd = new FormData(form);
      const url = '/blgr/repstsubmit/' + campaignId;
      $.ajax({
        url: url,
        type: 'POST',
        data: fd,
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
          alert('수정제출완료!');
          $('.re-submit-modal-form').addClass('display-none');
          location.reload();
        },
        error: function (xhr) {
          console.error(xhr.responseText || xhr.statusText);
          alert('제출 실패: ' + xhr.status);
        }
      });
    });
    
    
    /* */
    
    
	$(".status--draft").on("click",function() {
		$(".submit-modal-form").removeClass("display-none");
	})
	
	$(".status--rejected").on("click",function(){
		$(".re-submit-modal-form").removeClass("display-none");
	})
	
	$(".submit-modal-btn-close").on("click",function() {
		$(".submit-modal-form").addClass("display-none");
		$(".re-submit-modal-form").addClass("display-none");
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

	    $.get("/campaignmain", params, function (html) {
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
	
    $(".button-campaign-modify").on("click", function() {
        cmpnId = $("input[name=campaign-tab]").data("cmpn-id");
        window.location.href = "/adv/campaign/modify?cmpnId=" + cmpnId + "&sttsCd=2003";
    });
    $(".file-insert").off("click").on("click", function() {
        $("#file-input").click();
    });
    
    
});
