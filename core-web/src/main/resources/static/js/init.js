var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh',                                         //设置语言
            uploadUrl: uploadUrl,                                   //上传的地址
            allowedFileExtensions: ['jpg'],    //接收的文件后缀
            showUpload: true,                                       //是否显示上传按钮
            showCaption: false,                                     //是否显示标题
            browseClass: "btn btn-primary",                         //按钮样式
            maxFileCount: 1,                                       //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            maxFileSize: 3072,
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            uploadExtraData:function (previewId, index) {           //传参
                var data = {
                    "reportGroupId": $('#lbl_groupId').html(),      //此处自定义传参
                };
                return data;
            }
        });

        //导入文件上传完成之后的事件
        $("#reportFile").on("fileuploaded", function (event, data, previewId, index) {
            //报告table刷新
            var account = document.getElementById("account").value;
            var time = document.getElementById("time").value;
            var tid = document.getElementById("tid").value;
            var sign = document.getElementById("sign").value;
            location.href="/user/main?account="+account+"&time="+time+"&sign="+sign+"&tid="+tid;
        });
    }
    return oFile;
};

var oFileInput = new FileInput();
var account = document.getElementById("account").value;
var loginHost = document.getElementById("loginHost").value;
var time = document.getElementById("time").value;
var sign = document.getElementById("sign").value;
//参数1:控件id、参数2:上传地址
oFileInput.Init("reportFile", loginHost+"/user/avatar?account="+account+"&time="+time+"&sign="+sign);

$(function () {
    $("#operator-panel").attr("style","position: absolute;float: left;\n" +
        "                 min-height: "+($(window).innerHeight()*0.95)+"px;min-width: 90px");
    $("#span-div").attr("style","height: "+($(window).innerHeight()*0.4)+"px")
    $("#operator-body").attr("style","background-color: white;position: relative;padding-top: 5px;  " +
        "overflow-x:auto;height: "+($(window).innerHeight()*0.95)+"px;width: "+$("#operator-panel").width()+"px");

    $("#add-btn").click(function () {
        var data = {
            account:$("#account").val(),
            time: $("#time").val(),
            tid: $("#tid").val(),
            sign: $("#sign").val(),
            templateName: $("#create-template-name").val(),
            description: $("#create-template-description").val()
        }
        $.ajax({
            type: 'post',
            async: false,
            url: "/user/templates/create",
            data: data,
            success: function (data) {
                $("body").html(data)
                window.location.reload()
            }
        })
    });

    $("#userCenter").mouseover(function () {
        $("#userCenter").attr("class","dropdown open")
    }).mouseout(function () {
        $("#userCenter").attr("class","dropdown")
    });
});

