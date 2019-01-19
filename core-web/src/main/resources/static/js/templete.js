function paramTemp() {
    var temp = "<div class=\"row panel panel-info text-center\">\n" +
        "                <div class=\"panel-heading\">参数信息</div>\n" +
        "                <div class=\"panel-body\">\n" +
        "                    <div id=\"parameterInfo\" >\n" +

        "                    </div>\n" +
        "\n" +
        "                </div>\n" +
        "            </div>";
    return temp;
}

function paramInfoTemp(index,key,value) {
    var temp = "<div class=\"row\">\n" +
        "                            <div class=\"row panel col-sm-offset-1 col-sm-10\">\n" +
        "                                <div class=\"col-sm-3 \">\n" +
        "                                    <input type=\"text\" class=\"form-control\" id=\"paramKey_"+index+"\" placeholder=\"参数\" value='"+key+"' style='height: 25px' readonly>\n" +
        "                                </div>\n" +
        "                                <div class=\"col-sm-1 \">\n" +
        "                                    =\n" +
        "                                </div>\n" +
        "                                <div class=\"col-sm-6 \">\n" +
        "                                    <input type=\"text\" class=\"form-control\" id=\"paramValue_"+index+"\" placeholder=\"参数值\" style='height: 25px' value='"+value+"' readonly>\n" +
        "                                </div>" +
        "                                <div >\n" +
        "                                   <button type=\"button\" class=\"btn btn-danger btn-xs\" value='2' onclick='delParam(this)'>\n" +
        "                                       <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                   </button>\n" +
        "                               </div>"+
        "                            </div>\n" +
        "\n" +
        "                        </div>";
    return temp;
}

function proxyTemp() {
    var temp = "<div class=\"row panel panel-info \">\n" +
        "                <div class=\"panel-heading\">代理</div>\n" +
        "                <div class=\"panel-body\">\n" +
        "                    <div class=\"row\">\n" +
        "                        <form class=\"form-horizontal\">\n" +
        "                            <div class=\"form-group\">\n" +
        "                                <label for=\"host\" class=\"col-sm-1 control-label\">Host</label>\n" +
        "                                <div class=\"col-sm-10\">\n" +
        "                                    <input type=\"text\" class=\"form-control\" id=\"host\" placeholder=\"代理主机IP(例：127.0.0.1)\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class=\"form-group\">\n" +
        "                                <label for=\"port\" class=\"col-sm-1 control-label\">Port</label>\n" +
        "                                <div class=\"col-sm-3\">\n" +
        "                                    <input type=\"text\" class=\"form-control\" id=\"port\" placeholder=\"代理主机端口(例：8080)\">\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </form>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";
    return temp;
}

function headerTemp() {
    var temp = "<div class=\"row panel panel-info \">\n" +
        "                <div class=\"panel-heading\">头部信息" +
        "                            <div class=\"btn-group btn-group-xs\">\n" +
        "                                <button type=\"button\" class=\"btn btn-success\" onclick='addHeaderInfo(this)'>新增头部</button>\n" +
        "                            </div>\n" +
        "                  </div>\n" +
        "                <div class=\"panel-body\">\n" +
                             "<div id='headers'></div>"+
        "                    <div class=\"row\">\n" +
        "                        <form class=\"form-horizontal\">\n" +
        "                        </form>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";
    return temp;
}

function headerInfoTemp(index) {
    var temp = "<div id='headerInfo-"+index+"' class=\"row\">\n" +
        "                        <div class=\"row panel col-sm-offset-1 col-sm-10\">\n" +
        "                            <div class=\"col-sm-3 \">\n" +
        "                                <input type=\"text\" class=\"form-control\" id=\"headerKey_"+index+"\" placeholder=\"头部键(例：Cookie)\" style='height: 25px'>\n" +
        "                            </div>\n" +
        "                            <div class=\"col-sm-1 \">\n" +
        "                                =\n" +
        "                            </div>\n" +
        "                            <div class=\"col-sm-6 \">\n" +
        "                                <input type=\"text\" class=\"form-control\" id=\"headerValue_"+index+"\" placeholder=\"头部值(例：value123)\" style='height: 25px'>\n" +
        "                            </div>\n" +
        "                            <div >\n" +
        "                                <button type=\"button\" class=\"btn btn-danger btn-xs\" onclick='delHeaderInfo("+index+")' >\n" +
        "                                    <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                </button>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "\n" +
        "                        </div>"
    return temp;
}

function conditionTemp() {
    var temp = "<div class=\"row col-sm-12\">\n" +
        "            <div class=\"row panel panel-info \">\n" +
        "                <div class=\"panel-heading\">" +
        "                   条件" +
        "<span class='glyphicon glyphicon-question-sign' title='父级条件是字段的共有条件，字段数量代表结果有多少属性'></span>"+
        "</div>\n" +
        "                <div class=\"panel-body\">\n" +
        "                    <div class=\"row\">\n" +
        "                        <div class=\"col-lg-12  panel panel-success text-left\">\n" +
        "                            <div class=\"panel-heading\">\n" +
        "                                父级条件\n" +
        "\n" +
        "                                    <button type=\"button\" class=\"btn btn-success btn-sm  \" onclick='addCommonCondition()'>新增条件</button>\n" +
        "                            </div>\n" +
        "                            <div class=\"panel-body\">\n" +
        "                                <div class=\"common-condition-body\">\n" +
        "                                </div>\n" +

        "                            </div>\n" +
        "                        </div>\n" +
        "                        <div class=\"col-lg-12  panel panel-success text-left\">\n" +
        "                            <div class=\"panel-heading\">\n" +
        "                                结果字段\n" +
        "                                    <button type=\"button\" class=\"btn btn-success btn-sm \" onclick='addFiled()'>新增字段</button>\n" +
        "                            </div>\n" +
        "                            <div class=\"panel-body\">\n" +
        "                                <div class=\"field-condition-body\">\n" +
        "                                </div>\n" +
        "\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";
    return temp;
}

function commonConditionTemp(index) {
    var temp = "<div class=\"row\" id='common-"+index+"'>\n" +
        "                                        <div class=\"row\" >\n" +
        "                                            <div class=\"col-sm-11\">\n" +
        "                                                <form class=\"form-horizontal\">\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">过滤类型</label>\n" +
        "                                                        <div class=\"col-sm-9\">\n" +
        "                                                            <select class=\"form-control\" >\n" +
        "                                                                <option value=\"ID\">根据id过滤</option>\n" +
        "                                                                <option value=\"CLASS\">根据class样式过滤</option>\n" +
        "                                                                <option value=\"TAG\">根据tag标签过滤</option>\n" +
        "                                                                <option value=\"REGEX\">根据正则表达式过滤</option>\n" +
        "                                                                <option value=\"XPATH\">根据Xpath过滤</option>\n" +
        "                                                            </select>\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">条件</label>\n" +
        "                                                        <div class=\"col-sm-9\">\n" +
        "                                                            <input type=\"text\" class=\"form-control\" placeholder=\"条件\">\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">选取条件</label>\n" +
        "                                                        <div class=\"col-sm-1\">\n" +
        "                                                            <input type=\"radio\" checked name=\"select\" placeholder=\"条件\" value=\"text\" onclick=\"hide('common-"+index+"')\">文本\n" +
        "                                                        </div>\n" +
        "                                                        <div class=\"col-sm-1\">\n" +
        "                                                            <input type=\"radio\" name=\"select\" placeholder=\"条件\" value=\"attr\" onclick=\"show('common-"+index+"')\">属性\n" +
        "                                                        </div>\n" +
        "                                                        <div class=\"hidden\" >\n" +
        "                                                            <input type=\"text\" placeholder=\"属性(例：name)\">\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "\n" +
        "                                                </form>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"col-sm-1\">\n" +
        "                                                <div class=\"form-group-sm\">\n" +
        "                                                    <div class=\"col-sm-offset-5 col-sm-1\">\n" +
        "                                                        <button type=\"button\" class=\"btn btn-danger btn-xs\" onclick=\"removeCommonCondition("+index+")\">\n" +
        "                                                            <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                                        </button>\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                        <div class=\"row\">\n" +
        "                                            <hr>\n" +
        "                                        </div>\n" +
        "\n" +
        "\n" +
        "                                    </div>";

    return temp;
}

function filedTemp(index) {
    var temp = "<div class=\"row\" id='field-"+index+"'>\n" +
        "                                        <div class=\"row panel panel-info text-center\">\n" +
        "                                            <div class=\"panel-heading\">\n" +
        "                                                <input placeholder='字段"+index+"'>" +
        "                                       <button type=\"button\" class=\"btn btn-danger btn-xs\" onclick='removeFiled("+index+")'>\n" +
        "                                                    <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                                </button>"+
        "                                            </div>\n" +
        "                                            <div class=\"panel-body\">\n" +
        "<div class=\"row\">\n" +
        "                                                    <div class=\"filed-condition-"+index+"\">\n" +
        "\n" +
        "                                                    </div>\n" +
        "                                                </div>\n" +
        "\n" +
        "                                                <div class=\"row\">\n" +
        "                                                    <div class=\"btn-group btn-group-sm\">\n" +
        "                                                        <button type=\"button\" class=\"btn btn-success \" onclick='addFiledCondition("+index+")'>新增条件</button>\n" +
        "                                                    </div>\n" +
        "                                                </div>"+
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                    </div>";
    return temp;
}

function fieldConditionTemp(index1,index2) {
    var temp = "<div class=\"row\" id='field-"+index1+"-condition-"+index2+"'>\n" +
        "                                        <div class=\"row\" >\n" +
        "                                            <div class=\"col-sm-11\">\n" +
        "                                                <form class=\"form-horizontal\">\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">过滤类型</label>\n" +
        "                                                        <div class=\"col-sm-9\">\n" +
        "                                                            <select class=\"form-control\" >\n" +
        "                                                                <option value=\"ID\">根据id过滤</option>\n" +
        "                                                                <option value=\"CLASS\">根据class样式过滤</option>\n" +
        "                                                                <option value=\"TAG\">根据tag标签过滤</option>\n" +
        "                                                                <option value=\"REGEX\">根据正则表达式过滤</option>\n" +
        "                                                                <option value=\"XPATH\">根据Xpath过滤</option>\n" +
        "                                                            </select>\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">条件</label>\n" +
        "                                                        <div class=\"col-sm-9\">\n" +
        "                                                            <input type=\"text\" class=\"form-control\" placeholder=\"条件\">\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "                                                    <div class=\"form-group\">\n" +
        "                                                        <label class=\"col-sm-2 control-label\">选取条件</label>\n" +
        "                                                        <div class=\"col-sm-1\">\n" +
        "                                                            <input type=\"radio\" checked name=\"select\" placeholder=\"条件\" value=\"text\" onclick=\"hide('field-"+index1+"-condition-"+index2+"')\">文本\n" +
        "                                                        </div>\n" +
        "                                                        <div class=\"col-sm-1\">\n" +
        "                                                            <input type=\"radio\" name=\"select\" placeholder=\"条件\" value=\"attr\" onclick=\"show('field-"+index1+"-condition-"+index2+"')\">属性\n" +
        "                                                        </div>\n" +
        "                                                        <div class=\"hidden\" >\n" +
        "                                                            <input type=\"text\" placeholder=\"属性(例：name)\">\n" +
        "                                                        </div>\n" +
        "                                                    </div>\n" +
        "\n" +
        "                                                </form>\n" +
        "                                            </div>\n" +
        "                                            <div class=\"col-sm-1\">\n" +
        "                                                <div class=\"form-group-sm\">\n" +
        "                                                    <button type=\"button\" class=\"btn btn-danger btn-xs\" onclick=\"removeFiledCondition("+index1+","+index2+")\">\n" +
        "                                                            <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                                        </button>\n" +
        "                                                </div>\n" +
        "                                            </div>\n" +
        "                                        </div>\n" +
        "                                        <div class=\"row\">\n" +
        "                                            <hr>\n" +
        "                                        </div>\n" +
        "\n" +
        "\n" +
        "                                    </div>";

    return temp;
}