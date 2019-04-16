/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {}
};

/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
UserInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
UserInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
UserInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
UserInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('account').set('sex').set('password').set('avatar')
        .set('email').set('name').set('birthday').set('rePassword').set('deptid').set('phone');
};

/**
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    
    var account=EASYLIAO.checkRequired2(this.userInfoData.account,"账号不能为空!");
    
    var name=EASYLIAO.checkRequired2(this.userInfoData.name,"姓名不能为空!");
    var deptid=EASYLIAO.checkRequired2(this.userInfoData.deptid,"部门不能为空!");
    
    if(account==false || name==false || deptid==false ){
    	return;
    }

    if (!this.validatePwd()) {
        EASYLIAO.error("两次密码输入不一致");
        return;
    }

    //提交信息
    var ajax = new $ax(EASYLIAO.ctxPath + "/mgr/add", function (data) {
        EASYLIAO.success("添加成功!");
        window.parent.MgrUser.table.refresh();
        UserInfoDlg.close();
    }, function (data) {
        EASYLIAO.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {
debugger;
    this.clearData();
    this.collectData();
    var account=EASYLIAO.checkRequired2(this.userInfoData.account,"账号不能为空!");
    
    var name=EASYLIAO.checkRequired2(this.userInfoData.name,"姓名不能为空!");
    var deptid=EASYLIAO.checkRequired2(this.userInfoData.deptid,"部门不能为空!");
    
    if(account==false || name==false || deptid==false ){
    	return;
    }
    var c = null;
    //提交信息
    var ajax = new $ax(EASYLIAO.ctxPath + "/mgr/edit", function (data) {
    	c = 0;
        EASYLIAO.success("修改成功!");
        if(window.parent.MgrUser != undefined){
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }
    }, function (data) {
        EASYLIAO.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
    if(c==0){
    	window.parent.location.href = window.parent.location.href;
    }
};

/**
 * 修改密码
 */
UserInfoDlg.chPwd = function () {
    var ajax = new $ax(EASYLIAO.ctxPath + "/mgr/changePwd", function (data) {
        EASYLIAO.success("修改成功!");
    }, function (data) {
        EASYLIAO.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("oldPwd");
    ajax.set("newPwd");
    ajax.set("rePwd");
    ajax.start();

};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        UserInfoDlg.hideDeptSelectTree();
    }
}

$(function () {
    var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(UserInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    //初始化性别选项
    $("#sex").val($("#sexValue").val());

    // 初始化头像上传
    debugger;
    var avatarUp = new $WebUpload("avatar");
    avatarUp.init();
});
