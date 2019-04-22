<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/static/common/cssAndScript.jsp"%>
<link href="<%=path%>/static/plugins/layui-v2.4.5/layui/css/layui.css" type="text/css" rel="stylesheet">
<script type="text/javascript" language="Javascript" src="<%=path%>/static/plugins/layui-v2.4.5/layui/layui.js"></script>
 <script type="text/javascript" language="Javascript" src="<%=path%>/jsp/table/tableExternal.js"></script>
<script type="text/javascript" language="Javascript" src="<%=path%>/jsp/table/tableInternal.js"></script> 
<!-- 提示语  -->
<link rel="stylesheet" href="<%=path%>/static/plugins/toastr/toastr.css" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/static/plugins/toastr/toastr.js"></script>
<!-- 时间 -->
<link  href="<%=path%>/static/plugins/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">
<script src="<%=path%>/static/plugins/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/static/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 时间段插件   -->
<link rel="stylesheet" type="text/css"  href="<%=path%>/static/plugins/daterangepicker/css/daterangepicker.css" />
<script type="text/javascript" src="<%=path%>/static/plugins/daterangepicker/js/moment.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/plugins/daterangepicker/js/daterangepicker.js"></script>

 <!-- jedate时间点插件 -->
<link  href="<%=path%>/static/plugins/jedate/jedate.css" rel="stylesheet">
<script src="<%=path%>/static/plugins/jedate/jedate.js"></script>
<!-- 校验正则  -->
<script type="text/javascript" src="<%=path%>/static/js/validation.js"></script>
<!-- 数据json -->
<script type="text/javascript" src="./searchJson.js"></script>
<!-- 外部js  -->
<script src="<%=path%>/jsp/table/searchExternalCustomer.js"></script>
<script src="<%=path%>/jsp/table/searchInternal.js"></script>

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
					        <h5 class="console-title  id="total">高级查询表格</h5>
					     <div class="heardBtn">
							<button type="button" class="btn btn_primary" onclick="showAdd()"><img class="img_style"   src="<%=path %>/static/img/add.png"/>新增</button>
						</div>
					    </div>
					    <!-- 标线  -->
					    <div class="border_style"></div>
					    <!-- 搜索条件 -->
					     <div class="searchContent">
						    <div class="row" >
								<div class="col-md-12">
									<!--  模态框  -->
									<div id="dialogDiv">
									</div>
									<!-- 搜索条件栏 -->
								 	<div id="searchDiv" >
								 	
								 	</div>
								 </div>
							</div>
						</div>		 
				   </div>  
                </div>
	         </div>
	     </div>           
	</div>
  <div class="page_content">
       <table class="layui-hide" id="test" lay-filter="test"></table>
 </div>
	
</div>	
</body>
</html>