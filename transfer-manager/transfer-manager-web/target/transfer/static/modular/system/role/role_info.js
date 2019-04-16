/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
	roleInfoData : {},
	deptZtree : null,
	pNameZtree : null
};

/**
 * 清除数据
 */
RolInfoDlg.clearData = function() {
	this.roleInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function(key, val) {
	this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
	return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function() {
	parent.layer.close(window.parent.Role.layerIndex);
}

/**
 * 点击部门input框时
 * 
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickDept = function(e, treeId, treeNode) {
	$("#deptName").attr("value", RolInfoDlg.deptZtree.getSelectedVal());
	$("#deptid").attr("value", treeNode.id);
}

/**
 * 点击父级菜单input框时
 * 
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function(e, treeId, treeNode) {
	$("#pName").attr("value", RolInfoDlg.pNameZtree.getSelectedVal());
	$("#pid").attr("value", treeNode.id);
}

/**
 * 显示部门选择的树
 * 
 * @returns
 */
RolInfoDlg.showDeptSelectTree = function() {
	EASYLIAO.showInputTree("deptName","deptContent");
}

/**
 * 显示父级菜单的树
 * 
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function() {
	EASYLIAO.showInputTree("pName","pNameContent");
}

/**
 * 收集数据
 */
RolInfoDlg.collectData = function() {
	this.set('id').set('name').set('pid').set('deptid').set('tips').set('num');
}

/**
 * 提交添加用户
 */
RolInfoDlg.addSubmit = function() {
	
	this.clearData();
	this.collectData();
	
	debugger;
	
	var name=EASYLIAO.checkRequired2(this.roleInfoData.name,"角色名称不能为空!");
    var pid=EASYLIAO.checkRequired2(this.roleInfoData.pid,"上级名称不能为空!");
    var deptid=EASYLIAO.checkRequired2(this.roleInfoData.deptid,"部门名称不能为空!");
    
    if(name==false || deptid==false || pid==false ){
    	return;
    }
	
	//提交信息
	var ajax = new $ax(EASYLIAO.ctxPath + "/role/add", function(data){
		EASYLIAO.success("添加成功!");
		window.parent.Role.table.refresh();
		RolInfoDlg.close();
	},function(data){
		EASYLIAO.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.roleInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
RolInfoDlg.editSubmit = function() {
	
	
	this.clearData();
	this.collectData();
	
	var name=EASYLIAO.checkRequired2(this.roleInfoData.name,"角色名称不能为空!");
    var pid=EASYLIAO.checkRequired2(this.roleInfoData.pid,"上级名称不能为空!");
    var deptid=EASYLIAO.checkRequired2(this.roleInfoData.deptid,"部门名称不能为空!");
    
    if(name==false || deptid==false || pid==false ){
    	return;
    }
	
	//提交信息
	var ajax = new $ax(EASYLIAO.ctxPath + "/role/edit", function(data){
		EASYLIAO.success("修改成功!");
		window.parent.Role.table.refresh();
		RolInfoDlg.close();
	},function(data){
		EASYLIAO.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.roleInfoData);
	ajax.start();
}

$(function() {
	var deptTree = new $ZTree("deptTree", "/dept/tree");
	deptTree.bindOnClick(RolInfoDlg.onClickDept);
	deptTree.init();
	RolInfoDlg.deptZtree = deptTree;
	
	var pNameTree = new $ZTree("pNameTree", "/role/roleTreeList");
	pNameTree.bindOnClick(RolInfoDlg.onClickPName);
	pNameTree.init();
	RolInfoDlg.pNameZtree = pNameTree;
});
