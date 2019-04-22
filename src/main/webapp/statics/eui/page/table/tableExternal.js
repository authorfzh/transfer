/**
 * 创建外部对象
 * */

var ExternalView=function(){
};
var externalView=new ExternalView();
var json;
/**
 * 外部向内部提供的参数方法
 * */

/**
 * 属性信息
 * ColsJson 表头列的json
 * 
 * */
function  categoryIdBtn(res){
	var value =res.categoryId;
	return "测试";
}
function  operateBtn(res){
	var index =res.LAY_TABLE_INDEX;
	if(index==0){
		return "<a  style='color: #279af2;cursor: hand;' lay-event='down'>下移</>";
	}else{
		return "<a  style='color: #279af2;cursor: hand;' lay-event='down'>下移</>" +
				"<a style='color: #279af2;cursor: hand;margin-left: 10px;' lay-event='up'>上移</>";
	}
}
/**
 * cloms 列的类型 具体请参考layui https://www.layui.com/doc/modules/table.html#initSort
 * field:属性名
 * title:标题名
 * type:类型 checkbox（复选框列），numbers（序号列）   普通列不需设置
 * fixed:定位  left（固定在左）、right（固定在右）
 * align:对其方式  left（默认）、center（居中）、right（居右）
 * templet:扩展方法 得到的参数为row对象  
 * */
ExternalView.prototype.returnColsJson = function() {
	 var cloms=[[  {"type": "checkbox", "align": 'left',"width":"40"}
			  ,{"field":"itemId", "title": "常用语标识", "sort": "true" , "align": 'left'}
			  ,{"field":"content" , "title": "常用语内容" , "align": 'left','width':'300'}
			  ,{"field":"categoryId" , "title": "常用分类名称" ,"templet":categoryIdBtn , "align": 'left'}
			  ,{"field":"customerId" , "title": "所属客服" , "align": 'left'}
		      ,{"title": "操作","templet":operateBtn , "align": 'left'}
		    ]];
	var json= JSON.stringify(cloms);
	// console.log(json)
	return cloms;
};

ExternalView.prototype.returnToolbarHtml = function() {
	var toolbarHtml='';
	  toolbarHtml='<div class="layui-btn-container">'+
				    '<button id="btn2" class="btn btn btn_default" lay-event="del">删除</button>'+
				  '</div>';
	return toolbarHtml;
};
//请求ajax方法

//点击行的事件
ExternalView.prototype.rowClick = function(obj) {
	console.log("单击行点击==== ");
	console.log(obj);
	
};
//操作栏的点击事件
ExternalView.prototype.toolClick = function(event,obj) {
	if("up"==event){
		upItem(obj.itemId,obj.categoryId);
	}else if("down"==event){
		downItem(obj.itemId,obj.categoryId);
	}
	console.log("操作栏点击====  event:"+event);
	console.log(obj);
};
//尾部按钮事件
ExternalView.prototype.toolbarClick = function(event,obj) {
	if("edit" ==event){
		if(obj.length==0){
			toastr.warning("请选择需要修改的行！");
		}else if(obj.length>1){
			toastr.warning("请选择一行进行操作！");
		}else if(obj.length ==1){
			var itemId =obj[0].itemId;
			var categoryId =obj[0].categoryId;
			var title =obj[0].title;
			var content =obj[0].content;
			document.getElementById("upItemId").value=itemId;
			/*document.getElementById("upCategoryId").value=categoryId;*/
			$("#upCategoryId").val([""+categoryId+""]).trigger('change');
			document.getElementById("upTitle").value=title;
			document.getElementById("upContent").value=content;
			$('#myModal2').modal('show');
		}
	}else if("del"==event){
		if(obj.length==0){
			toastr.warning("请选择需要删除的行！");
		}else if(obj.length>1){
			toastr.warning("请选择一行进行操作！");
		}else if(obj.length ==1){
			$('#delModal').modal();
			document.getElementById("modaldelId").value=obj[0].itemId;
		}
	}else if("load"==event){
		 try{
		      var elemIF = document.createElement("iframe");
		      elemIF.src = basePath+"/Service/CommonWord/downloadCrm";
		      elemIF.style.display = "none";
		      document.body.appendChild(elemIF);
		    }catch(e){

		    }
	}
};
//搜索条件的查询参数
ExternalView.prototype.returnParams = function(event,obj) {
	var value=$("#selVal").val().replace(/\s+/g,"");;
	var cId =customerId;
	var filterParams = " and userId = '"+cId+"' ";
	if(value != null && value.length > 0) filterParams += " and content like '%"+value+"%'";
	var params={
            'filterParams':filterParams,
        };
	return params
};

$(function(){
	//getQueryAllCategory();
	/**
	 * 初始化的搜索条件参数
	 * */

	/**
	 * 参数的信息：  
		 * id 表格id
		 * url 地址
		 * params 默认条件
		 * method  请求类型
		 * page 是否分页 false为不展示
		 * limit 展示的页数
		 * totalRow 是否有统计
		 * self  是否添加筛选
	 * */
	/*"url":getContextPath()+"/Service/CommonWord/PageCommonWordPersonal",*/
	var params={};
	var paramsJson=
	            {
	                "id":"test",
	                "url":"./table.js",
	                "params":params,
	                "method":"post",
	                "page":true,
	                "limit":20,
	                "totalRow":false,
	                "self":false
	            }
	        
	var table = new LayuiTable(paramsJson);
	table.init();
	/*var table = new LayuiTable("test", ""+getContextPath()+"/Service/CommonWord/PageCommonWordPersonal",params, "post",false,20);*/
});


//获取项目路径
function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}