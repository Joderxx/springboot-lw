$(function () {
    $("#operator-panel").attr("style","position: absolute;float: left;\n" +
        "                 min-height: "+($(window).innerHeight()*0.95)+"px");
    $("#span-div").attr("style","height: "+($(window).innerHeight()*0.4)+"px")
    $("#operator-body").attr("style","background-color: white;position: relative;padding-top: 5px;  " +
        "overflow-x:auto;height: "+($(window).innerHeight()*0.95)+"px;width: "+$("#operator-panel").width()+"px")
})