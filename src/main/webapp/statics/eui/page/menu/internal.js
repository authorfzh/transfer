
//界面初始化方法：
$(function(){
	/**
	 *内部方法初始化
	 * */
	menuView.append();
	/**
	 * 事件方法的初始化
	 * */
	menuView.init();
});

var MenuView=function(){
};
var menuView=new MenuView();
/**
 * 初始化添加html内容
 * */
MenuView.prototype.append = function() {
	 this.addHeard();
	 this.addMenu();
};
/**
 * 初始化事件方法
 * */
MenuView.prototype.init = function() {
	 this.mouse();
	 this.click();
	 this.WindowPX();
};
MenuView.prototype.addHeard = function() {
	//添加头部所需静态
	$.fn.append_head();
	//加载头部菜单
	/*var heardJson = externalView.getHeardJson();
	$.fn.head_menu(heardJson);*/
};
MenuView.prototype.addMenu = function() {
	//添加左侧主菜单所需静态
	$.fn.append_leftMenu();
	//加载左侧主菜单
	var menuJson = externalView.returnMenuJson();
	$.fn.left_menu(menuJson);
};

/**
 * 鼠标移动事件
 * */
MenuView.prototype.mouse = function() {
	/**
	 *鼠标及滑动的js效果 
	 * */
	//系统操作栏-表头移动事件
	$(".showBanner").mouseenter(function (){ //打开
		$(this).addClass("open");
	}).mouseleave(function (){
		$(this).removeClass("open");
	});
	$(".BannerHidden").mouseout(function (){  
		$(this).removeClass("open");
	}); 
	//左侧菜单栏鼠标移动事件
	$(".main-menu").mouseenter(function (){  
		$(".main-menu").css("width","180px");
		$("nav.main-menu .nav-text").css("visibility","visible");
	}).mouseleave(function (){
		 $(".main-menu").css("width","54px");
	     $("nav.main-menu .nav-text").css("visibility","hidden");
	});
	//左侧菜单栏伸缩按钮鼠标移动事件
	$(".opera-pic").mouseenter(function (){  
		var className =$("i.opera-pic")[0].className;
		if(className.indexOf("abutton_collapse")!=-1){
			$(".opera-pic").removeClass("abutton_collapse");
			$(".opera-pic").addClass("abutton_bigcollapse");
		}else if(className.indexOf("abutton_expand")!=-1){
			$(".opera-pic").removeClass("abutton_expand");
			$(".opera-pic").addClass("abutton_bigexpand");
		}
	}).mouseleave(function (){
		var className =$("i.opera-pic")[0].className;
		if(className.indexOf("abutton_bigcollapse")!=-1){
			$(".opera-pic").removeClass("abutton_bigcollapse");
			$(".opera-pic").addClass("abutton_collapse");
		}else if(className.indexOf("abutton_bigexpand")!=-1){
			$(".opera-pic").removeClass("abutton_bigexpand");
			$(".opera-pic").addClass("abutton_expand");
		}
	});
};
/**
 * 点击事件
 * */
MenuView.prototype.click = function() {
	//菜单栏点击的 颜色加载与事件
	$("#opera-coll").click(function(){
		var close= $("#opera-coll").attr("class");
		//点击遮蔽二级层
		if(close==undefined || close==""){
			$(".sidebar-collapse").hide();
			$("#opera-coll").css({"left":"45px","border-radius":"30px 0 0 30px"}); 
			$("#opera-coll").addClass("closed");
			$(".navbar-side").css({"height":"","position":""});
			$(".page-wrapper").css("left","54px");
			$(".opera-pic").removeClass("abutton_bigcollapse");
			$(".opera-pic").addClass("abutton_expand");
		}else{
			//点开事件
			$(".sidebar-collapse").show();
			$(".navbar-side").css({"height":"100%","position":"fixed"});
			$("#opera-coll").css({"left":"205px","border-radius":" 30px 0 0 30px"}); 
			$("#opera-coll").removeClass("closed");
			$(".page-wrapper").css("left","225px");
			$(".opera-pic").addClass("abutton_collapse");
			$(".opera-pic").removeClass("abutton_bigexpand");
		}
		menuView.WindowPX();
	});
	//点击颜色选中事件
	$(".has-subnav").click(function(){
		//清空所有选中的样式
		$(".main-menu ul li").removeClass('active');
		//添加选中样式
		$(this).addClass('active');
	});
};
/**
 * 初始化页面的窗口大小
 * */
MenuView.prototype.WindowPX = function() {
	var ifm= document.getElementById("myiframe");
	ifm.height=$(".page-wrapper").css('height');
	ifm.width=$(".page-wrapper").css('width') ;
};

//封装方法
(function ($) {
	$.fn.extend({
		/*添加头部html*/
		append_head:function(){
			//项目路径
			var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var path = pathName.substr(0,index+1);
		    //渲染页面
			var html='<img class="logo" src="./statics/eui/image/logo.png"/><ul  class="nav navbar-top-links navbar-right operate"></ul>';
			$(".header").append(html);
		},
		/*添加二级菜单栏html*/
		append_leftMenu:function(){
			var html='<!-- 展开关闭二级的图标 -->'+
			'<div id="opera-coll" class="closed" style="display:none;">'+
				'<i class="opera-pic fa abutton_collapse fa-lg " style="margin-top: -5px;"></i>'+
			'</div>'+
		    '<!-- 二级页面的详情  -->'+
		    '<div class="sidebar-collapse" style="display:none;">'+
		    	 '<!-- 二级页面头部 -->'+
		    	 '<div class="brand"></div>'+
		    	 '<!-- 二级页面内容  -->'+
		  		 '<div id="treeview">'+
		  		 '</div>'+
		    '</div>'
		 $(".navbar-side").append(html);   
		},
		/*封装头部菜单*/
		head_menu:function(operate_json){
			//外层是操作栏
			$.each(operate_json, function(i, n){
				var html=" <li class=\"dropdown showBanner\" >";
				var nodes=n.nodes;
				//判断类型为系统 还是消息 （0 系统 ，1 消息）
				if(n.type=="0"){
					html+=" <a class=\"dropdown-toggle count-info\" data-toggle=\"dropdown\" >"+n.text+"</a>";
				}else if(n.type=="1"){
					html+=" <a class=\"dropdown-toggle count-info\" data-toggle=\"dropdown\"><i class=\"fa fa-bell\"></i>  <span class=\"label label-primary\">"+n.messageNum+"</span></a>";
				}
				html+=" <ul  class=\"dropdown-menu dropdown-alerts BannerHidden\" >";
				//操作的详情内容
				$.each(nodes, function(i, n1){
					if(n.type=="0"){
						html+=" <li><a href=\"javascript:headMenuClick('"+encodeURIComponent(JSON.stringify(n1))+"')\"> "+n1.text+"</a></li><li class=\"divider\"></li>";
					}else if(n.type=="1"){
						html+=" <li><a href=\"javascript:headMenuClick('"+encodeURIComponent(JSON.stringify(n1))+"')\"><span> "+n1.content+"</span><br><span>2018-09-04 11:11:00</span></a></li><li class=\"divider\"></li>";
					}
	
				});
				//追加信息	
				$(".operate").append(html);
				$(".operate").append("</ul></li>");
			});
		},
		/*左侧一级菜单栏*/
		left_menu:function(menuJson){
			var html="<ul style=\"margin-top: 0px;\" >";
			$.each(menuJson, function(i, n){
				var li_class="";
				//默认首页加载样式
				if(n.text=="首页"){
					li_class="has-subnav active";
				}else{
					li_class="has-subnav";
				}
				var icon=n.icon; //图标样式
				//转义json字符串 封装所需hmtl
				html+="<li class='"+li_class+"'>"+
				"<span  data_type=\"true\" id='"+n.id+"' params='"+JSON.stringify(n)+"' onclick=openBar('"+n.text+"','"+n.id+"')>"+
				"<i class='"+icon+"'></i>"+
				"<span class=\"nav-text\">"+n.text+"</span>"+
				"</span>"+
				"</li>";
				
				
			});
			html+="</ul>";
			//追加信息	
			$("#left_menu").empty();	
			$("#left_menu").append(html);
		},
		/*一级菜单点击事件 初始化二级*/
		left_menu_tree:function(title,json){
			//大窗口关闭
			 $(".main-menu").css("width","54px");
		     $("nav.main-menu .nav-text").css("visibility","hidden");
			//模拟点击事件
		     $(".sidebar-collapse").show();
		 	 $(".navbar-side").css({"position":"fixed"});
		 	 $("#opera-coll").css({"left":"205px","border-radius":" 30px 0 0 30px"}); 
		 	 $("#opera-coll").removeClass("closed");
		 	 $(".page-wrapper").css("left","225px");
		 	 $(".opera-pic").addClass("abutton_collapse");
		 	 $(".opera-pic").removeClass("abutton_expand");
			//打开伸展操作图标
			$("#opera-coll").show();
			//加载二级菜单树
			$('#treeview').treeview({
				data: json,
				onNodeSelected:function(event, data){
					var nodeId=data.nodeId;
				
					//data为树的字段信息 -操作事件
					var text=data.text;
					//验证url
					if(data.url!=null && data.url!=undefined){
						//加载页面
						setTimeout(function(){
							$('#treeview').treeview('disableNode', [ nodeId, { silent:true } ]);
							multiMenuClick(data);
							$("#myiframe").ready(function() {
									$('#treeview').treeview('enableNode', [ nodeId, { silent:true } ]);
									$(".treeview").find("li").eq(nodeId).addClass("node-selected");
									$(".treeview").find("li").eq(nodeId).css({"color":"#333333","background-color":"white"}) ;
									
							}); 
							
						});
						
					}
				}
			}); 
			//默认节点关闭
			$('#treeview').treeview('collapseAll',[ { silent: true } ]); 
			//修改二级页的标头
			$(".brand").html(title);  
			//内容div的窗口初始化
			menuView.WindowPX();
		},
		//ifreame 加载页面方式
		iframeUrl:function(id,url){
			//console.log("iframe的跳转url地址"+url);
			$("#"+id+"").attr("src",url);
		}
	});
})(window.jQuery);
//头部菜单点击事件
function headMenuClick(item){
	externalView.headMenuClick(item);
}
//一级菜单的点击事件
function openBar(title,id){
	var data_type=$("#"+id+"").attr("data_type");
	//var json= JSON.parse(decodeURI(item));
	var params=$("#"+id+"").attr("params");
	var json=  JSON.parse(params);
	var url=getContextPath()+json.url;
	if(json.url!=undefined){
		 $(".main-menu").css("width","54px");
	     $("nav.main-menu .nav-text").css("visibility","hidden");
		//初始化窗口
		$(".sidebar-collapse").hide();
		$("#opera-coll").css({"left":"35px","border-radius":"30px 0 0 30px"}); 
		$("#opera-coll").addClass("closed");
		$(".navbar-side").css({"height":"","position":""});
		$(".page-wrapper").css("left","54px");
		$(".opera-pic").removeClass("abutton_bigcollapse");
		$(".opera-pic").addClass("abutton_expand");
		//内容div的窗口初始化
		menuView.WindowPX();
		$.fn.iframeUrl("myiframe",url);
	}else{
		//var keywords =decodeURI(item);
		externalView.openBar(title,params,data_type);
	}
	
}
//二级菜单点击
function multiMenuClick(item){
  externalView.multiMenuClick(item);
}
//修改二级展开关闭
function itemOnclick(target){
	//找到当前节点id
	var nodeid = $(target).attr('data-nodeid');
	var tree = $('#treeview');
	//获取当前节点对象
	var node = tree.treeview('getNode', nodeid);
	if(node.state.expanded){ 
	    //处于展开状态则折叠
	    tree.treeview('collapseNode',[  node.nodeId, { silent: true } ]);  
	} else {
		//当为根节点时 可关闭
		if(node.parentId==undefined){
			//默认关闭所有树
			$('#treeview').treeview('collapseAll', { silent: true });
		}
	    //展开当前树
	    tree.treeview('expandNode', [  node.nodeId, { silent: true } ]);
	};
};
//反显根据id渲染

function reverseMenu(pId,treeId){
	//初始化 设置类型不加载二级菜单的第一个页面
	$("#"+pId+"").attr("data_type","false");
	//一级菜单点击事件
	$("#"+pId+"").click();
	//初始化一级菜单选中样式
	$(".has-subnav").removeClass("active");
	$("#"+pId+"").parent().addClass("active");
	//初始化二级菜单选中样式
	$("#"+treeId+"").click();
	//$("#"+treeId+"").addClass("node-selected");
	//$("#"+treeId+"").css({"color":"#333333","background-color":"white"}) ;
	
}

//加载首页
$(function(){
	//var url ="/console/Service/HomePage/WelcomePage";
	 $(".main-menu").css("width","54px");
     $("nav.main-menu .nav-text").css("visibility","hidden");
	//初始化窗口
	$(".sidebar-collapse").hide();
	$("#opera-coll").css({"left":"45px","border-radius":"30px 0 0 30px"}); 
	$("#opera-coll").addClass("closed");
	$(".navbar-side").css({"height":"","position":""});
	$(".page-wrapper").css("left","2.8%");
	$(".opera-pic").removeClass("abutton_bigcollapse");
	$(".opera-pic").addClass("abutton_expand");
	//内容div的窗口初始化
	//menuView.WindowPX();
	//$.fn.iframeUrl("myiframe",url);
})
