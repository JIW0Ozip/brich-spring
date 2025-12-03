/**
 * 회원 리스트 탭 제어
 */

$().ready(function() {
    
    $("input[name='admin-user-tab']").on("change", function() {
       
        var selectedTab = $(this).attr("id"); 
        var baseUrl = window.location.pathname;
        
        window.location.href = baseUrl + "?tab=" + selectedTab;
    });
    
    var activeTab = $("input[name='admin-user-tab']:checked").attr("id");
    
    var currentTable;
    
    if (activeTab === 'all') {
        currentTable = 'all-tbl';
    }
    else {
        currentTable = activeTab + "-tbl";
    }
    
    $(".list-tbl").hide();
    $("#" + currentTable).show();
    
});