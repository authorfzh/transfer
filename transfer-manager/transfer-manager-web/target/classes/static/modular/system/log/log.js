/**
 * 日志管理初始化
 */
var OptLog = {
    id: "OptLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OptLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '日志类型', field: 'logtype', align: 'center', valign: 'middle', sortable: true},
        {title: '日志名称', field: 'logname', align: 'center', valign: 'middle', sortable: true},
        // {title: '用户名称', field: 'userName', align: 'center', valign: 'middle', sortable: true, sortName: 'userid'},
        {title: '用户名称', field: 'userName', align: 'center', valign: 'middle'},
        {title: '类名', field: 'classname', align: 'center', valign: 'middle', sortable: true},
        {title: '方法名', field: 'method', align: 'center', valign: 'middle', sortable: true},
        {title: '时间', field: 'createtime', align: 'center', valign: 'middle', sortable: true},
        {title: '具体消息', field: 'message', align: 'center', valign: 'middle', sortable: true}];
};

/**
 * 检查是否选中
 */
OptLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        EASYLIAO.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OptLog.seItem = selected[0];
        return true;
    }
};

/**
 * 查看日志详情
 */
OptLog.detail = function () {
    if (this.check()) {
        var ajax = new $ax(EASYLIAO.ctxPath + "/log/detail/" + this.seItem.id, function (data) {
            EASYLIAO.infoDetail("日志详情", data.regularMessage);
        }, function (data) {
            EASYLIAO.error("获取详情失败!");
        });
        ajax.start();
    }
};


/**
 * 清空日志
 */
OptLog.delLog = function () {
    EASYLIAO.confirm("是否清空所有日志?",function(){
        var ajax = EASYLIAO.baseAjax("/log/delLog","清空日志");
        ajax.start();
        OptLog.table.refresh();
    });
}

/**
 * 查询日志列表
 */
OptLog.search = function () {
    var queryData = {};

    queryData['logName'] = $("#logName").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['logType'] = $("#logType").val();

    OptLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OptLog.initColumn();
    var table = new BSTable(OptLog.id, "/log/list", defaultColunms);
    table.setPaginationType("server");
    OptLog.table = table.init();
});
