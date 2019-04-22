

/**
 * 创建外部对象
 * */

var ExternalView=function(){
};
var externalView=new ExternalView();
/**
 * 属性信息
 * heardJson 头部菜单json
 * menuJson 左侧主菜单json
 * */
ExternalView.prototype.returnHeardJson = function() {
	return null;
};

ExternalView.prototype.returnMenuJson = function() {
	return menuJson;
};

/**
 * 点击事件
 * */
//头部点击事件	
ExternalView.prototype.headMenuClick = function(item) {
	//转换成json
	var json= JSON.parse(item);
	//url路径+jsp
	var url=getContextPath()+json.url;
	//ifream跳转
	$.fn.iframeUrl("myiframe",url);
};	
//一级菜单的点击事件
ExternalView.prototype.openBar = function(title,item,data_type) {
	var json= JSON.parse(item);
	//加载事件
	$.fn.left_menu_tree(title,json.treeJson);
	var url =json.treeJson[0].url;
	//验证是否为初始化, true 为初始化 false为反显操作
	if(data_type=="true"){
		url =getContextPath()+"/Service"+url;
		$.fn.iframeUrl("myiframe",url);
		$("#treeview li:first").css("background-color","white");
		
	}
};	


//二级菜单点击事件
ExternalView.prototype.multiMenuClick = function(item) {
	var url=getContextPath()+item.url;
	$.fn.iframeUrl("myiframe",url);
};	
//获取项目路径
function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}