var headerFlag = true; //是否添加头部
var proxyFlag = true;

var  paramNum = 0;
function updateParameter(input) {
    var param = document.getElementById("parameter");
    var url = document.getElementById("url").value;
    if ($.trim(url)==''){
        param.innerHTML = '';
    } else {
        var i = url.indexOf("?");
        if (i == -1){
            param.innerHTML = '';
        }else {
            param.innerHTML = paramTemp();
            var s = url.substring(i+1);
            var arr = s.split("&")
            for (var j=0;j<arr.length;j++){

                if (arr[j].indexOf("=")!=-1){
                    var sp = arr[j].split("=")
                    if (sp.length==2){
                        addParam1(sp[0],sp[1])
                    } else {
                        addParam1(sp[0],'')
                    }

                } else {
                    addParam1(arr[j],'')
                }

            }
        }
    }
}

function addParam1(key,value) {

    var paramInfo = document.getElementById("parameterInfo");
    if (key==null){
        paramInfo.innerHTML += paramInfoTemp(paramNum,'','')
    } else if (value==null) {
        paramInfo.innerHTML += paramInfoTemp(paramNum,key,'')
    }else {
        paramInfo.innerHTML += paramInfoTemp(paramNum,key,value)
    }
}

function delParam(btn) {
    var key = btn.parentNode.parentNode.childNodes[1].childNodes[1].getAttribute("value");
    var url = document.getElementById("url").value;
    var i = url.indexOf("?");
    var sub = url.substring(0,i);
    var s = url.substring(i+1);
    var arr = s.split("&")
    if (arr.length>1){
        sub += "?";
        for (var j=0;j<arr.length;j++){
            if (arr[j].indexOf("=")!=-1){
                var sp = arr[j].split("=")
                if (sp[0]==key)continue;
                sub += sp[0]+"="+sp[1]+"&";

            } else {
                sub += arr[j]+"&";
            }

        }
        document.getElementById("url").value= sub.substring(0,sub.length-1);
    }else {

        document.getElementById("url").value= sub;
    }
    btn.parentNode.parentNode.parentNode.removeChild(btn.parentNode.parentNode)
}

function addProxy(btn) {
    var proxy = document.getElementById("proxy");
    if (proxyFlag){
        proxy.innerHTML = proxyTemp();
        btn.innerHTML = '删除代理';
        btn.className = "btn btn-danger"
        proxyFlag = false;
    } else {
        btn.innerHTML = '添加代理'
        btn.className = "btn btn-primary"
        proxy.innerHTML = '';
        proxyFlag = true;
    }
}

function addHeaderForm(btn) {
    var header = document.getElementById("header");
    if (headerFlag){
        header.innerHTML= headerTemp();
        btn.innerHTML = '删除头部信息';
        btn.className = "btn btn-danger"
        headerFlag = false;
    } else {
        header.innerHTML= '';
        btn.className = "btn btn-info"
        btn.innerHTML = '添加头部信息';
        headerFlag = true;
    }
}

var headerIndex = 0;
var headArr = [];

function addHeaderInfo(btn) {
    $("#headers").append(headerInfoTemp(headerIndex));
    headArr[headerIndex] = 'headerInfo-'+headerIndex;
    headerIndex++;

}

function delHeaderInfo(index) {
    $("#headerInfo-"+index).remove();
    headArr[index] = ''
}


function addCondition(btn) {
    var condition = document.getElementById("conditionBody");
    if (headerFlag){
        condition.innerHTML= conditionTemp();
        btn.innerHTML = '删除条件';
        btn.className = "btn btn-danger"
        headerFlag = false;
    } else {
        condition.innerHTML= '';
        btn.className = "btn btn-primary"
        btn.innerHTML = '添加条件';
        headerFlag = true;
    }

}

function removeCondition(s) {
    $("#"+s).remove()
}

var commonIndex = 0;
var commonArr = [];

function addCommonCondition(btn) {
    $(".common-condition-body").append(commonConditionTemp(commonIndex));
    commonArr[commonIndex] = 'common-'+commonIndex;
    commonIndex++;
}

function removeCommonCondition(index) {
    commonArr[index] = '';
    $("#common-"+index).remove();
}

var fieldIndex = 0;
var fieldArr = [];
var fieldConditionIndex = 0;
var fieldConditionArr = [];

function addFiled(btn) {
    $(".field-condition-body").append(filedTemp(fieldIndex));
    fieldArr[fieldIndex] = 'field-'+commonIndex;
    fieldConditionArr[fieldIndex] = [];
    fieldIndex++;
}

function removeFiled(index) {
    fieldArr[index] = '';
    fieldConditionArr[index] = [];
    $("#field-"+index).remove();
}

function addFiledCondition(index) {

    $(".filed-condition-"+index).append(fieldConditionTemp(index,fieldConditionIndex))
    fieldConditionArr[index][fieldConditionIndex] = 'field-'+index+"-condition-"+fieldConditionIndex;
    fieldConditionIndex++;
}

function removeFiledCondition(index1,index2) {
    fieldConditionArr[index1][index2] = '';
    $("#field-"+index1+"-condition-"+index2).remove();
}

function hide(s) {
    $("#"+s+" div:nth-child(1) div.col-sm-10 form div:nth-child(3) div.show").attr('class','hidden')
}

function show(s) {
    $("#"+s+" div:nth-child(1) div.col-sm-10 form div:nth-child(3) div.hidden").attr('class','show')
}


