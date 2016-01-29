var LAST_PAGE = 7299;
var FIRST_PAGE = 1;
var TOTAL_RECORDS = 145962;

var firstPage; var nextPage; var lastPage; var prevPage;
var prevTerm = '';

var updatePageLinks = function(data){
    TOTAL_RECORDS = data.page.totalElements;
    LAST_PAGE = data.page.totalPages-1;
};

var showPostalCodesByPage = function(page){
    $('#search-button').toggleClass('active');
    $.get("/api/postalCodes?page="+page, function(data, status){
        populateTable(data);
        updatePageLinks(data);
    });
};
var showPostalCodesBySimilarTerm = function(term, page){
    $('#search-button').toggleClass('active');
    $.get("/api/postalCodes/search/findSimilar?term="+term+"&page="+(page-1), function(data, status){
        populateTable(data);
        updatePageLinks(data);
        //some tweaks for this plugin
        if(data.page.size > data.page.totalElements){ //we do not need pagination
            $('.pagination-holder').html('');
        }else if(prevTerm != term){ //just if we need pagination
            $('.pagination-holder').html('');
            $('.pagination-holder').html('<ul class="pagination-sm"></ul>');
            $('.pagination-sm').twbsPagination({
                totalPages: LAST_PAGE,
                visiblePages: 5,
                first:'<<', prev:'<', next:'>', last:'>>',
                onPageClick: function (event, page) {
                    showPostalCodesBySimilarTerm(term, page);
                }
             });
        }
        prevTerm = term;
    });
}

var populateTable = function(data){
    var table = $("#codigos-postales tbody");
    var html = '';
    $.each(data._embedded.postalCodes, function(idx, elem){
        html+="<tr><td>"+elem.codigoPostal+"</td><td>"+elem.colonia+"</td><td>"+elem.municipio+"</td><td>"+elem.estado+"</td></tr>";
    });
    table.html(html);
    var foundElements = $('#found-elements');
    if(data.page.totalElements<data.page.size)
        foundElements.html("<h5>"+data.page.totalElements+" ["+$('#search-input').val()+"]</h5>");
    else
        foundElements.html("<h5>"+data.page.size+" / "+data.page.totalElements+" ["+$('#search-input').val()+"]</h5>");
    $('#search-button').toggleClass('active');
}

$(document).ready(function(){
    $('#search-div').show();
    $('#loading-div').hide();
    $("input[type='text']").on("click", function () {
       $(this).select();
    });
    $('#search-input').keyup(function(e){
        if(e.keyCode == 13){
            showPostalCodesBySimilarTerm($('#search-input').val(), 1);
        }
    });
    $('#search-button').click(function(e){showPostalCodesBySimilarTerm($('#search-input').val(), 1);});
});