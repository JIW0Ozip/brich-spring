$(document).ready(function() {
    var here = location.pathname.replace(/^\/+|\/+$/g, '').split('/');
    var auth = $(".wrapper").data("menu-auth");
    //관리자 캠페인 관리에서 campaigndetail 봤을 때와
    //캠페인 탭에서 campaigndatail 봤을 때를 구분해주기 위한 변수
    var prevUrl = document.referrer;
    console.log(here);
    if (here.includes('submittedmycampaign')
        || here.includes('campaignongoing')
        || here.includes('closedcampaign')
        || here.includes('favcampaign')) {
        $(".my-sub-menu").removeClass('display-none');
    }

    $(".my-campaign").click(function() {
        $(this).find($(".my-sub-menu")).slideToggle('fast');
        $('.caret-up').toggleClass('display-none');
        $('.caret-down').toggleClass('display-none');
    })

    $('common-menu').removeClass('menu-selected');
     
    $(".search-input").on("keyup", function(e) {
        if(e.keyCode === 13) {
            window.location.href = "/campaignmain?searchKeyword=" + $(this).val() + "&sortBy=latest"
        } 
    });

    
    if (here.includes('campaignmain') || here.includes('campaigndetail')) {
        $('.menu-campaignmain-selected').addClass('menu-selected')
    }
    //캠페인 관리에서 campaigndetail을 클릭한 경우
    if (here.includes('campaigndetail') && auth === 1001 && prevUrl.includes('admin')) {
        $('.menu-campaignmain-selected').removeClass('menu-selected');
        $('.menu-admin-campaign-selected').addClass('menu-selected');
    }
    
    if (here.includes('submittedmycampaign')) {
        $('.menu-my-submitted-campaign-selected').addClass('menu-selected')
    }
    if (here.includes('campaignongoing')) {
        $('.menu-my-ongoing-campaign-selected').addClass('menu-selected')
    }

    if (here.includes('blog') && here.includes('manage')) {
        console.log("Asdsad")
        $('.menu-my-blog-selected').addClass('menu-selected');
    }

    if (here.includes('closedcampaign')) {
        $('.menu-my-closed-campaign-selected').addClass('menu-selected')
    }
    if (here.includes('favcampaign')) {
        $('.menu-my-fav-campaign-selected').addClass('menu-selected')
    }
    if (here.includes('campaign') && here.includes('write')) {
        $('.menu-my-campaign-write').addClass('menu-selected');
    }

    if (here.includes('campaign') && here.includes('list')) {
        $('.menu-my-campaign-list').addClass('menu-selected');
    }

    if (here.includes("chat")) {
        $('.menu-message-selected').addClass('menu-selected');
    }

    if (here.includes("help")) {
        $('.menu-help-selected').addClass('menu-selected');
    }

    if (here.includes("account")) {
        $('.menu-account-selected').addClass('menu-selected');
    }

    if (here[0].includes('report')) {
        $('.menu-report-selected').addClass('menu-selected');
    }

    if (here[0].includes("admin") && here[1].includes("user")) {
        $('.menu-admin-user-selected').addClass('menu-selected');
    }
    if (here[0].includes("admin") && here[1].includes("inqr")) {
        $('.menu-admin-inqr-selected').addClass('menu-selected');
    }
    if (here[0].includes("admin") && here[1].includes("report")) {
        $('.menu-admin-report-selected').addClass('menu-selected');
    }
    if (here[0].includes("admin") && here[1].includes("campaign")) {
        $('.menu-admin-campaign-selected').addClass('menu-selected');
    }
    if (here[0].includes("admin") && here[1].includes("category")) {
        $('.menu-admin-category-selected').addClass('menu-selected');
    }
    if (here.includes('dashboard')) {
        $('.menu-dashboard-selected').addClass('menu-selected');
    }
})