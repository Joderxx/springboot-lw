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
    var temp = "<div class=\"row panel panel-info text-center\">\n" +
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
    var temp = "<div class=\"row panel panel-info text-center\">\n" +
        "                <div class=\"panel-heading\">头部信息</div>\n" +
        "                <div class=\"panel-body\">\n" +
                             "<div id='headers'></div>"+
        "                    <div class=\"row\">\n" +
        "                        <form class=\"form-horizontal\">\n" +
        "                            <div class=\"btn-group btn-group-xs\">\n" +
        "                                <button type=\"button\" class=\"btn btn-success\" onclick='addHeaderInfo(this)'>新增头部</button>\n" +
        "                            </div>\n" +
        "                        </form>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>";
    return temp;
}

function headerInfoTemp(index) {
    var temp = "<div id='headerInfo_"+index+"' class=\"row\">\n" +
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
        "                                <button type=\"button\" class=\"btn btn-danger btn-xs\" onclick='delHeaderInfo(\"headerInfo_"+index+"\")' >\n" +
        "                                    <span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>\n" +
        "                                </button>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "\n" +
        "                        </div>"
    return temp;
}

function conditionTemp() {
    var temp = "       <div class=\"row panel panel-info text-center\">\n" +
        "                <div class=\"panel-heading\">条件</div>\n" +
        "                <div class=\"panel-body\">\n" +
        "                    <div id=\"childConditionBody\">\n" +
        "\n" +
        "                    </div>\n" +
        "                    <div class=\"row \">\n" +
        "                        <form class=\"form-horizontal\">\n" +
        "                            <div class=\"btn-group btn-group-sm\">\n" +
        "                                <button type=\"button\" class=\"btn btn-success \">新增条件</button>\n" +
        "                            </div>\n" +
        "\n" +
        "                        </form>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>";
    return temp;
}

function childConditionTemp(index) {
    var temp = "<div class=\"row panel panel-info text-center\">\n" +
        "           <div class=\"panel-heading\">" +
        "               <input type=\"text\" name=\"attr\" placeholder=\"字段"+index+"\">" +
        "           </div>\n" +
        "           <div class=\"panel-body\">\n" +
        "           </div>" +
        "           <div class=\"row \">" +
        "               <form class=\"form-horizontal\">\n" +
        "                   <div class=\"btn-group btn-group-xs\">\n" +
        "                        <button type=\"button\" class=\"btn btn-success \">新增条件</button>\n" +
        "                    </div>\n" +
        "                </form>\n" +
        "           </div>"
    "       </div>";

    return temp;
}