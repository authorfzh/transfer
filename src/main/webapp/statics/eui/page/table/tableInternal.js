/**
 * 初始化 Layui Table 的封装
 * @author ...
 */
(function () {
	/**
	 * 参数的信息：  
		 * elemId 表格id
		 * url 地址
		 * params 默认条件
		 * method  请求类型
		 * page 是否分页 false为不展示
		 * limit 展示的页数
		 * totalRow 是否有统计
		 * self  筛选
	 * */
    var LayuiTable = function (params) {
	    this.externalView=externalView; //外部的js
    	this.elemId=params.id; 
    	this.url=params.url;
    	this.params=params.params;
        this.height = 665;    
        this.page=params.page!=null?params.page:true;
        this.method=params.method!=null?params.method:"post";
        this.limit=params.limit!=null?params.limit:"10";
        this.totalRow=params.totalRow!=null?params.totalRow:false;
        this.self=params.self!=null?params.self:false;
    };

    LayuiTable.prototype = {
        /**
         * 初始化
         */
        init: function () {
        	//获取参数
        	 var elem=this.elemId;
        	 var url=this.url;
        	 var params=this.params;
        	 var method=this.method;
        	 var clos=externalView.returnColsJson();
        	 var toolbar=externalView.returnToolbarHtml();
        	 var limit=this.limit;
        	 var page=this.page;
        	 var totalRow=this.totalRow;
        	 var self=this.self;
        	 var pageNow =0;
             var dataLength =0;
        	layui.use('table', function(){
        		  var table = layui.table;
        		  table.render({
        		    elem: "#"+elem+""
        		    ,url:url
        		    ,where:params
        		    ,method:method
        		    ,toolbar: toolbar
        		    ,title: '用户数据表'
        		    ,cols:clos 
        		    ,page: page
        		    ,self:self
        		    ,limit: limit //显示的数量
        		    ,totalRow: totalRow
        		    ,done: function(res, curr, count){
        		    	pageNow = curr,
        		    	dataLength = res.data.length;
        		    	if(toolbar=="" && page==false ){
        		    		$(".table_foot").remove();
        		    	}else if(count!=0){
        		    		$(".table_foot").show();
        		    	}
        		      }
        		  });
        		  //头工具栏事件
        		  table.on('toolbar('+elem+')', function(obj){
        		    var checkStatus = table.checkStatus(obj.config.id);
        		    var data = checkStatus.data;
        		    var dataJsonStr=JSON.stringify(data);
        		    //传输外部方法
        		    externalView.toolbarClick(obj.event,data);
        		    /*layer.alert(JSON.stringify(data));*/
        		   /* switch(obj.event){
        		      case 'getCheckData':
        		        var data = checkStatus.data;
        		        layer.alert(JSON.stringify(data));
        		      break;
        		      case 'getCheckLength':
        		        var data = checkStatus.data;
        		        layer.msg('选中了：'+ data.length + ' 个');
        		      break;
        		      case 'isAll':
        		        layer.msg(checkStatus.isAll ? '全选': '未全选');
        		      break;
        		    };*/
        		  });
        		  
        		  //监听行单击事件（单击事件为：rowDouble）
        		  table.on('row('+elem+')', function(obj){
        		    var data = obj.data;
        		    externalView.rowClick(data);
        		    /* layer.alert(JSON.stringify(data), {
        		      title: '当前行数据：'
        		    }); */
        		    //标注选中样式
        		    obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        		  });
        		  
        		  //表列操作事件
        		  table.on('tool('+elem+')', function(obj){
        			var event=obj.event;
        		    var data = obj.data;
        		    externalView.toolClick(event,data);
        		  });
        		  //搜索条件初始化
        		  $(".submitClick").click(function(){
        			  var params=externalView.returnParams();
        			  if(page==true){
        				  table.reload('test', {
          					page: {curr: 1},
  		                    where: params
  		                });	
         			 }else{
         				 table.reload('test', {
  		                    where: params
  	                  });	
         			 }
        			
        	     });
        		  //表格初始化
        		  $("#test").click(function(){
        			  var  pageSize=pageNow;
        			  var params=externalView.returnParams();
        			  if(dataLength == 1){
        				  pageSize=pageNow >1 ? (pageNow - 1) : 1 ;
        			  }
        			 if(page==true){
        				 table.reload('test', {
 	      				  	page: {curr: pageSize},
 		                    where: params
 	                  });	
        			 }else{
        				 table.reload('test', {
 		                    where: params
 	                  });	
        			 }
    				 
        			  
        	     });
        		 
        	 });
        	
        	//阻止冒泡
        	$(document).on("click",".layui-table-body table.layui-table tbody tr a", function (e) {
        		//  event.stopPropagation();//不会打印1，但是页面会跳转；
        		var trobj=$(this).parents("tr");
        	    var index = trobj.attr('data-index');
        		var tableBox= $(this).parents('.layui-table-box');
        		 if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length>0) {
           	        tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
           	    } else {
           	        tableDiv = tableBox.find(".layui-table-body.layui-table-main");
           	    }
           	    var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox");
           	    var checkDiv=checkCell.find("div.layui-form-checkbox");
           	    var isCheckDiv=checkDiv.hasClass("layui-form-checked");
           	    if(checkDiv.length>0 && isCheckDiv==false){
           	    	checkDiv.click();
           	    }
        		return false; 
        		
        	})
        	//行点击事件
        	$(document).on("click",".layui-table-body table.layui-table tbody tr", function () {
        	 var index = $(this).attr('data-index');
        	    var tableBox = $(this).parents('.layui-table-box');
        	    if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length>0) {
        	        tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
        	    } else {
        	        tableDiv = tableBox.find(".layui-table-body.layui-table-main");
        	    }
        	    var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
        	    if (checkCell.length>0) {
        	        checkCell.click();
        	    }
        	    var radioCheckCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-radio div.layui-form-radio I");
        	    if (radioCheckCell.length>0) {
        	    	radioCheckCell.click();
        	    }
        	});

        	$(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
        	    e.stopPropagation();
        	});
        	$(document).on("click", "td div.laytable-cell-radio div.layui-form-radio", function (e) {
        	    e.stopPropagation();
        	});
        }
    };
    window.LayuiTable = LayuiTable;
}());



