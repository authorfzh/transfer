/**
 * 初始化通知详情对话框
 */
var NoticeInfoDlg = {
    noticeInfoData : {}
};

/**
 * 清除数据
 */
NoticeInfoDlg.clearData = function() {
    this.noticeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NoticeInfoDlg.set = function(key, val) {
    this.noticeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NoticeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NoticeInfoDlg.close = function() {
    parent.layer.close(window.parent.Notice.layerIndex);
}

/**
 * 收集数据
 */
NoticeInfoDlg.collectData = function() {
    this.set('id').set('title').set('content');
}

/**
 * 提交添加
 */
NoticeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(EASYLIAO.ctxPath + "/notice/add", function(data){
        EASYLIAO.success("添加成功!");
        window.parent.Notice.table.refresh();
        NoticeInfoDlg.close();
    },function(data){
        EASYLIAO.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NoticeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(EASYLIAO.ctxPath + "/notice/update", function(data){
        EASYLIAO.success("修改成功!");
        window.parent.Notice.table.refresh();
        NoticeInfoDlg.close();
    },function(data){
        EASYLIAO.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

$(function() {

});
