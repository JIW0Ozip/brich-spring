$().ready(function() {
    url = new URL(window.location.href);
    searchParam = url.searchParams;
    sortValue = searchParam.get("sortBy") 
    if (sortValue) {
        $("#campaign-filter").val(sortValue).prop("selected", true);
    }
    
    $("#campaign-filter").on("change", function() {
        sortValue = $(this).val();
        
        if (window.location.search === "") {
            url = window.location.pathname + "?sortBy=" +  sortValue;
         }
         else {
             url = new URL(window.location.href);
             searchParam = url.searchParams;
             searchParam.set("sortBy", sortValue);
             url = url.toString();
         }
        
        window.location.href = url;
    });
});