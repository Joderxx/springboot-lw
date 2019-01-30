$(function () {
    $("#span-tips-hide").tooltip('show');
    $("#span-tips-show").tooltip('hide');

    $("#span-tips-hide").on('click',function () {
        $("#operator-panel").hide(500);
        setTimeout(function () {
            $("#span-tips-hide").tooltip('hide');
            $("#span-tips-show").tooltip('show');
        },500)

    });



    $("#span-tips-show").on('click',function () {
        $("#operator-panel").show(500);
        setTimeout(function () {
            $("#span-tips-hide").tooltip('show');
            $("#span-tips-show").tooltip('hide');
        },500);

    });

});