<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
   <%
	String path = request.getContextPath();
%> 
<%@ include file="/static/common/cssAndScript.jsp"%>
<link href="<%=path%>/static/plugins/layui-v2.4.5/layui/css/layui.css" type="text/css" rel="stylesheet">
<script type="text/javascript" language="Javascript" src="<%=path%>/static/plugins/layui-v2.4.5/layui/layui.js"></script>

<script type="text/javascript">
	var toolbarHtml='';
	      toolbarHtml='<div class="layui-btn-container">'+
	    				  '<button type="button" class="btn btn_primary"  id="btn-expand" lay-event="getCheckData"><img class="img_style"   src="<%=path %>/static/img/add.png"/>新增</button>'+
				  '</div>';
	var params = {
				
				};
	//操作按钮信息					  
	function operatefun(){
		var btnHtml='<div class="layui-btn-container">'+
						'<a class="operateBtn" lay-event="edit">修改</a>'+
						'<a class="operateBtn" lay-event="del" style="margin-left: 10px;">删除</a>'+
					'</div>';
   			return 	btnHtml;		 
	}
	
	//表格初始化	  
	$(function(){
	   layui.config({
             base: '<%=path%>/static/plugins/module/'
       }).extend({
            treetable: 'treetable-lay/treetable'
       }).use(['layer', 'table', 'treetable'], function () {
	        var $ = layui.jquery;
	        var table = layui.table;
	        var layer = layui.layer;
	        var treetable = layui.treetable;
	        // 渲染表格
	        var renderTable = function () {
	            treetable.render({
	                treeColIndex: 1,
	                treeSpid: -1,
	                treeIdName: 'id',
	                treePidName: 'pid',
	                treeDefaultClose: true,
	                treeLinkage: false,
	                toolbar:toolbarHtml,
	                elem: '#table1',
	                url : "<%=path%>/static/json/treeTable.js",
	                where : params,
	                page: false,
	                cols: [[
	                    {type: 'checkbox', align: 'left',width:'40'},
	                    {field: 'categoryName', title: '名称'},
	                    {field: 'categoryMemo', title: '描述'},
	                    {templet: operatefun, title: '操作'}
	                ]],
	                done: function () {
	                		$(".toolbarIdDiv").empty();
        		    		$(".toolbarIdDiv").append($(".layui-table-tool"));
        		    		//清空属性
        		    		$(".layui-table-tool").css("min-height","0px");
        		    		$(".layui-table-tool").css("line-height","0px");
        		    		$(".layui-table-tool").css("padding","0px");
        		    		$(".layui-table-tool").css("width","80px");
        		    		$(".layui-table-tool").css("float","right");
	                   	    layer.closeAll('loading');
	                }
	            });
	        };
	
	        renderTable();
	        $('#btn-expand').click(function () {
	            treetable.expandAll('#table1');
	        });
	
	        $('#btn-fold').click(function () {
	            treetable.foldAll('#table1');
	        });
	
	        $('#btn-refresh').click(function () {
	           renderTable();
	        });
			//头工具栏事件
		  table.on('toolbar(table1)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id);
		    if("getCheckData"==obj.event){
		    	if(checkStatus.data.length==0){
		    		$("#parentId").val("0");
					$("#parentName").val("顶级分类");
					$("#categoryName").val("");
					$("#categoryMemo").val("");
					$('#addModal').modal();
		    	}else if(checkStatus.data.length==1){
		    		$("#parentId").val(checkStatus.data[0].id);
					$("#parentName").val(checkStatus.data[0].categoryName);
					$("#categoryName").val("");
					$("#categoryMemo").val("");
					$('#addModal').modal();
		    	}else{
		    		toastr.warning("请选择单个上级分类！");
		    	}
		    }
		   /*  switch(obj.event){
		      case 'getCheckData':
		        var data = checkStatus.data;
		        layer.alert(JSON.stringify(data));
		      break;
		    }; */
		  });
	        //监听工具条
	        table.on('tool(table1)', function (obj) {
	            var data = obj.data;
	            var layEvent = obj.event;
	            if (layEvent === 'del') {
	            	$('#delModal').modal();
	            	$("#modaldelId").val(obj.data.categoryId);
	            } else if (layEvent === 'edit') {
	            	var parentId =obj.data.parentId;
	            	if(parentId=="0"){
	            		$("#parentIdEdit").val("0");
	            		$("#parentNameEdit").val("顶级分类");
	            	}else{
	            		$.ajax({
				        type: "Post",
				        url: basePath + "/Service/ChatInfo/QuerySummaryCategory" ,
				        data: {
				        	"categoryId" : parentId,
				        },
				        dataType: "json",
				        success: function (result) {
				        	var categoryId =result.message.categoryId;
				        	var categoryName =result.message.categoryName;
				        	$("#parentIdEdit").val(categoryId);
	            			$("#parentNameEdit").val(categoryName);
					        }
						});
	            	}
					$("#categoryId").val(obj.data.categoryId);
					$("#categoryNameEdit").val(obj.data.categoryName);
					$("#categoryMemoEdit").val(obj.data.categoryMemo);
	               if(obj.parentId==-1){
						$("#parentNameEdit").val("顶级分类");
					}
					$('#editModal').modal();
	            }
	        });
        
        	//选中行事件
          /*    table.on('row(table1)', function(obj){
	      		    var data = obj.data;
	      		    obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
      		  });
      		   */
        
    });
    });
			
		</script>	
</head>	
<body>
<!-- 总盒子 -->
<div class="page_body">
	<!-- 头部和内容的盒子 -->
	<div class="page_header">
		 <div class="wrapper wrapper-content animated fadeInRight">
	       <div class="row">
                <div class="col-lg-12">
               	  <div class="ibox">
               	  		<!-- 表头 -->
						<div  class="ibox-title">
					        <h5 class="console-title" style="cursor: pointer;" id="total">树表格</h5>
						     <div class="heardBtn toolbarIdDiv">
						      <!--   <a onclick="showAddModal()" class="abutton_add" style="margin-left: -15px;float:right;">新增</a> -->
								<!-- <a onclick="showEditModal()" class="abutton_edit" style="float:right;">编辑</a>
								<a onclick="showDelModal()" class="abutton_modalDelete" style="float:right;">删除</a> -->
							</div>
					    </div>
					    <!-- 标线  -->
					    <div class="border_style"></div>
					    <!-- 搜索条件 -->
				   </div>  
                </div>
	         </div>
	     </div>           
	</div>
  <div class="page_content">
  			<button style="display: none" class="layui-btn" id="btn-refresh">刷新表格</button>
      	  <table id="table1" class="layui-table" lay-filter="table1"></table>
 </div>
	
</div>	

		
	</body>
</html>