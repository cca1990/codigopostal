var prevTerm=new Date().getTime();
var findBySimilar = function(term, page){
    $('#search-button').toggleClass('active');
    $.get("/api/postalCodes/search/findSimilar?term="+term+"&page="+page, function(data, status){
        populateTable(data);
        if(data.page.size > data.page.totalElements){ //remove pagination
            $('.pagination-holder').html('');
        }else if(term!=prevTerm && (data.page.size < data.page.totalElements)){ //just if we need pagination or refresh pagination
            $('.pagination-holder').html('');
            $('.pagination-holder').html('<ul class="pagination-sm"></ul>');
            $('.pagination-sm').twbsPagination({
                totalPages: data.page.totalPages,
                visiblePages: 5,
                first:'<<', prev:'<', next:'>', last:'>>',
                onPageClick: function (event, page) {
                    findBySimilar(term, page-1);
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
            findBySimilar($('#search-input').val(), 0);
        }
    });
    $('#search-button').click(function(e){findBySimilar($('#search-input').val(), 0);});
    findBySimilar('',1);
});