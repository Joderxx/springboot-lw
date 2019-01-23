function isEmpty(s) {
    return s==null||s.length==0
}
function findCondition(s) {
    var ts = $("#"+s)
    var condition = ts.find("select[name='select-condition']").val();
    var conditionText = ts.find("input[name='select-condition-text']").val();
    var select = ts.find("input[name='select']:checked").val();
    var attrText = ts.find("input[name='attr-text']").val();
    var error = '';
    if (isEmpty(condition)){
        error += '没选择条件类型<br>';
    }
    if (isEmpty(conditionText)){
        error += '没填写条件<br>';
    }
    if (isEmpty(select)){
        error+= '没选择结果<br>';
    }
    if (select=='attr'&&isEmpty(attrText)){
        error += '没填写属性名';
    }
    if (error.length>0){
        Swal.fire(error)
    }
    return {
        condition: condition,
        conditionText: conditionText,
        select: select,
        attrText: attrText
    }
}

function findHeader(s) {
    var ts = $("#"+s);
    var key = ts.find("input[name='header-key']").val();
    var value = ts.find("input[name='header-value']").val()
    return {
        key: key,
        value: value
    }
}

function findHeaders() {
    var headerInfo = [];
    var num = 0;
    $.each(headArr,function () {
        if (this!=''){
            headerInfo[num++] = findHeader(this)
        }
    })
    return headerInfo
}

function findProxy() {
    var host = $("#host").val();
    var port = $("#port").val();
    return {
        host: host,
        port: port
    }
}

function findParendCondition(){
    var common = [];
    var num = 0;
    $.each(commonArr,function () {
        if (this!=''){
            common[num++] = findCondition(this)
        }
    })
    return common
}

function findFieldCondition(){
    var conditions = [];
    var ci = 0;
    $.each(fieldArr,function (i,e) {
        if (e!=''){
            var condition = [];
            var cj = 0;
            var fieldName = $("#"+e).find("input[name='filed-name']").val();
            $.each(fieldConditionArr[i],function () {
                if (this!=''){
                    condition[cj++] = findCondition(this)
                }
            })
            conditions[ci++] = {
                fieldName: fieldName,
                condition: condition
            }
        }
    })
    return conditions
}

$(function () {
   $("#send").click(function () {
       var url = $("#url").val();
       if (isEmpty(url)){
           Swal.fire('没有填写url')
       }
       var method = $("#method").val();
       var common = findParendCondition();
       var field = findFieldCondition();
       var header = findHeaders();
       var proxy = findProxy();
       var request = {
           url: url,
           method: method,
           header: header,
           proxy:proxy,
           common: common,
           field: field
       }

       console.log(request)
   });

   $("#publish-btn").click(function () {
       $("#publish-btn").attr('value',$("#publish-btn").val()=='false');
       $("#publish-text").text($("#publish-text").text()=='公开'?'未公开':'公开');
       var data = {
           account:$("#account").val(),
           time: $("#time").val(),
           tid: $("#tid").val(),
           sign: $("#sign").val(),
           type: $("#publish-btn").val(),
       }
       $.ajax({
           type: 'post',
           async: false,
           url: "/user/templates/publish",
           data: data,
           success: function (data) {
               if (data.state==0){
                   $("#publish-btn").attr('value',$("#publish-btn").val()=='false');
                   $("#publish-text").text($("#publish-text").text()=='公开'?'未公开':'公开');
               }
           }
       })
   })
});