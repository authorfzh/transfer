//初始化方法：
	//内部方法的对象
	var SearchInternal=function(){
	};
	var searchInternal=new SearchInternal();
$(function(){
	/**
	 * 初始化添加html内容
	 * */
	SearchInternal.prototype.append = function() {
		//绑定静态
		$.fn.appendSearch();
		$.fn.appendDialog();
		$.fn.appendCommonSerch();
		//数据绑定
		this.addDwLabel();
		this.addAdvanced();
		this.confSerch();
		this.addComSerch();
		//添加搜索条件的额外按钮
		this.addOperateBtn();
	};
	/**
	 * 初始化事件方法
	 * */
	SearchInternal.prototype.init = function() {
		 //插件资源与点击初始
		 this.areaTimepicker();
		 this.click();
	};

	SearchInternal.prototype.addDwLabel = function() {
		$.fn.addDwLabel(labelJson);
	};
	SearchInternal.prototype.addAdvanced = function() {
		$.fn.addAdvanced(criteriaJson);
	};
	SearchInternal.prototype.confSerch = function() {
		$.fn.confSerch(confJson);
	};
	SearchInternal.prototype.addComSerch = function() {
		$.fn.addComSerch(definiteJson);
	};
	SearchInternal.prototype.addOperateBtn = function() {
		$.fn.addOperateBtn();
	};
	
	/**时间区域控件*/
	SearchInternal.prototype.areaTimepicker = function() {
		if(criteriaJson!="" && criteriaJson!=undefined){
			$.each(criteriaJson, function(i, n){
				switch (n.type) {
				case "3":
					//时间控件    
					 $("#"+n.name+"").datetimepicker({
						format : "yyyy-mm-dd hh:ii:ss",
						language : "zh-CN",
						todayHighlight : true,
						todayBtn : true,
						autoclose: true
					}); 
					break;
				case "4":
					$("#"+n.name+"").daterangepicker({
				        format: 'YYYY-MM-DD',
				     /*   startDate: moment().subtract(29, 'days'),*/
				        endDate: moment(),
				       /*  minDate: '01/01/2012',
				        maxDate: '12/31/2015', */
				        dateLimit: { days: 60 },
				        showDropdowns: true,
				        showWeekNumbers: true,
				        timePicker: false,
				        timePickerIncrement: 1,
				        timePicker12Hour: true,
				        ranges: {
				            '上七天': [moment().subtract(6, 'days'), moment()],
				            '上一个月': [moment().subtract(29, 'days'), moment()],
				            '本月': [moment().startOf('month'), moment().endOf('month')]
				        },
				        opens: 'right',
				        drops: 'down',
				        buttonClasses: ['btn', 'btn-sm'],
				        applyClass: 'btn-primary',
				        cancelClass: 'btn-default',
				        separator: '-',
				        locale: {
				        	format: 'YYYY-MM-DD',
				        	separator: ';',
				            applyLabel: '确定',
				            cancelLabel: '关闭',
				            fromLabel: '开始',
				            toLabel: '结束',
				            customRangeLabel: '选择区域',
				            daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
				            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
				            firstDay: 1
				        }
				    }, function(start, end, label) {
				    	 if(!this.startDate){
				             this.element.val('');
				         }else{
				             this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
				         }
				    });
					break;
				case "5":
					 //时间控件    
					   jeDate("#"+n.name+"",{
					      //  minDate:"01:02:08",              //最小日期  设置时间段
					      //  maxDate:"15:25:35",              //最大日期
					        format: "hh:mm:ss"
					    });
					break;
				default:
					break;
				}
			});
		}
	};
	/**
	 * 点击事件
	 * */
	SearchInternal.prototype.click = function() {
		//未添加列的单个复选框点击事件
	 	$("[name='unAdded']").off("click").on("click",function(){
	 		//根据长度判断赋予全选的状态
	 		$("#unAddedId").prop("checked",$(":checkbox[name='unAdded']").length==$(":checkbox[name='unAdded']:checked").length);
	 	});
	 	//未添加列的全选复选框点击事件
	 	$("#unAddedId").off("click").on("click",function(){
	 		var checkbox=$("#unAddedId").is(":checked"); //判断是否选中
	 		if(checkbox){
				$("[name='unAdded']").prop("checked",true);//选中则全选操作
			}else{
				$("[name='unAdded']").prop("checked",false);
			}
			 		
	 	});
	 	//未添加列的单个复选框点击事件
	 	$("[name='added']").off("click").on("click",function(){
	 		//根据长度判断赋予全选的状态
	 		$("#addedId").prop("checked",$(":checkbox[name='added']").length==$(":checkbox[name='added']:checked").length);
	 	});
	 	//未添加的搜索栏
	 	$("#addedId").off("click").on("click",function(){
	 		var checkbox=$("#addedId").is(":checked"); //判断是否选中
	 		if(checkbox){
				$("[name='added']").prop("checked",true);//选中则全选操作
			}else{
				$("[name='added']").prop("checked",false);
			}
			 		
	 	});
	 	//时间转化方法
	 	Date.prototype.Format = function (fmt) { 
	 	    var o = {
	 	        "M+": this.getMonth() + 1, //月份 
	 	        "d+": this.getDate(), //日 
	 	        "H+": this.getHours(), //小时 
	 	        "m+": this.getMinutes(), //分 
	 	        "s+": this.getSeconds(), //秒 
	 	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	 	        "S": this.getMilliseconds() //毫秒 
	 	    };
	 	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	 	    for (var k in o)
	 	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	 	    return fmt;
	 	};
	 	//普通搜索点击事件
		$("#commonId").off("click").on("click",function(){
			$("#advancedId").show();
			$("#commonId").hide();
			$("#searchId").hide();
		});
		//高级搜索点击事件
		$("#advancedId").off("click").on("click",function(){
			$("#advancedId").hide();
			$("#commonId").show();
			$("#searchId").show();
		});
		
		
		//普通搜索下拉框点击事件
		$(".definiteClick").off("click").on("click",function(){
			var json= JSON.parse($(this).attr("params"));
			$.fn.showSelect(json.text,json.name,json.id);
		});
		//反显标签
		$(".labelClick").off("click").on("click",function(){
			//清除搜索表单内容
			$.fn.resetAll();
			var params=$(this).attr("params");
			$.fn.showLabel(params);
			$('#openBtnDiv').trigger("click");
		});
		$("#openBtnDiv").off("click").on("click",function(){
			$("#detail_serch").show();
			$(".ibox-tags").show();
			$("#openBtnDiv").hide();
			$("#closeBtnDiv").show();
		})
		$("#closeBtnDiv").off("click").on("click",function(){
			$("#detail_serch").hide();
			$(".ibox-tags").hide();
			$("#openBtnDiv").show();
			$("#closeBtnDiv").hide();
		})
		//重置普通搜索
		$(".generalReset").off("click").on("click",function(){
			$.fn.generalResetAll();
		});
		
		//重置
		$(".resetAllClick").off("click").on("click",function(){
			$.fn.resetAll();
		});
		//保存标签方法
		$(".saveLabelClick").off("click").on("click",function(){
			$.fn.saveLabel();
		});
		//取消保存标签方法
		$(".closeSaveLabelClick").off("click").on("click",function(){
			$.fn.closeSaveLabel();
		});
		//点击标签事件
		$(".windowTakeClick").off("click").on("click",function(){
			$.fn.windowTake(criteriaJson);
		});
		//筛选搜索条件选择事件
		$(".chooseConfSearchClick").off("click").on("click",function(){
			$.fn.chooseConfSearch(confJson);
		});
		//筛选重置
		$(".confSerchClick").off("click").on("click",function(){
			$.fn.confSerch(confJson);
		});
		
		//筛选向右移动
		$(".moveRightClick").off("click").on("click",function(){
			$.fn.moveRight();
		});
		//筛选向左移动
		$(".moveLeftClick").off("click").on("click",function(){
			$.fn.moveLeft();
		});
		//移动方法
		$("#addtr").off("click").on("click",".moveTrClick",function(){
			var oper=$(this).attr("params");
			$.fn.moveTr($(this),oper);
			});
		//校验方法 根据类型区别
		$(".validate").blur(function(){
			var validateType=$(this).attr("validateType");
			$.fn.Validation($(this),validateType);
		});
		
		
		//外部方法
		$(".extendClick").off("click").on("click",function(){
			searchExternalView.extendClick();
		});
		$(".advanceedSerchClick").off("click").on("click",function(){
			if(criteriaJson!="" && criteriaJson!=undefined){
				//初始化
				search_json=[];
				/**保存json*/
				 //得到搜索条件的json
			 	$.each(criteriaJson, function(i, n){
			 		var text=n.text;
			 		var type=n.type;
			 		var id=n.name;
			 		var value="";
			 		//获取目前的值
			 		//判断获取值
			 		switch (n.type) {
					case "2":
						value= $("#"+id+" option:selected",$("#detail_serch")).val();
						break;
					default:
						value=$("#"+id+"",$("#detail_serch")).val();
						break;
					}
			 		if(value!="" && value!=undefined){
			 			search_json.push({
			 		        "text":n.text,
			 		        "type":n.type,
			 		        "name":n.name,
			 		        "value":value
			 		    });
			 		}
				 	
			 	}); 
				searchExternalView.advanceedSerch(search_json);
			}
			
		});
		
		//具体条件搜索
		$(".definiteSerchClick").off("click").on("click",function(){
			//获取目前的值
			var name=$("#serchText").attr("name");
	 		var value=$("#serchText").val();
	 		//初始化
	 		definite_json=[];
	 		//判断获取值
	 		if(name!=undefined){
	 			if(value!=""){
	 				definite_json.push({
	 	 		        "name":name,
	 	 		        "value":value
	 	 		    });
	 	 			searchExternalView.definiteSerch(definite_json);
	 	 		}else{
	 	 			$("#serchText").addClass("has-error");
	 	 		}
	 		}else{
	 			$("#dropSelectId").addClass("has-error");
	 		}
		});
		
		$(".saveConfClick").off("click").on("click",function(){
			var selectValue= $("#confSelect option:selected").val();
			editLabel_json=[];
			//遍历未添加
			$('#unAddtr tr').each(function(i,n){
				editLabel_json.push({
					 "id":n.id,
					 "state":"0",
					 "index":i
				});
			});    
			//遍历添加
			$('#addtr tr').each(function(i,n){
				editLabel_json.push({
					 "id":n.id,
					 "state":"1",
					 "index":i
				});
			});  
		var code=searchExternalView.saveConf(editLabel_json,selectValue);
		});
		/**
		 * 删除标签
		 * */
		$(".a_delete").off("click").on("click",function(){
			var id=$(this).attr("del_id");
			var code=searchExternalView.delLabel(id);
		});
	};
	
	
	

	/**
	 *html资源初始化
	 * */
	searchInternal.append();
	/**
	 * 事件方法的初始化
	 * */
	searchInternal.init();
});
//定义的方法



//获取外部js的json属性
var labelJson = searchExternalView.returnLabelJson();
var definiteJson = searchExternalView.returnDefiniteJson();
var criteriaJson = searchExternalView.returnCriteriaJson();
var confJson = searchExternalView.returnConfJson();
//定义输出的json
var arr_json = [];	
var search_json=[];
var definite_json=[];
var editLabel_json=[];


//封装方法
(function ($) {
	$.fn.extend({
		//初始化搜索div静态
		appendSearch:function(){
			/*var html='<div class="ibox-content" style="background-color: #EBEDF1;" >'+
		       	 '<!-- 搜索栏 -->'+
		       	 	   '<div id="common_serch">'+
		       	 	   '</div>'+
		                  '<!-- 高级搜索 -->'+
		                 '<div id="searchId" style="display:none">'+
		                  '<div class="row" >'+
		                  		'<!-- FORM条件 -->'+
		                  		'<form class="search-bar margin_top_a1" action="" id="searchForm">'+
		                  		'</form>'+
		                   '</div>'+
		                   '<div class="row">'+
			                    '<div class="col-sm-12" style="margin-top:20px;margin-left:30px;" >'+
			                		'<div class="input-group">'+
			                			'<button type="button"  class="btn button-info advanceedSerchClick" >搜索</button>'+
			                			'<button type="button"  class="btn button-default resetAllClick margin_left_a1" >清空</button>'+
			                			'<button type="button"  class="btn button-default windowTakeClick margin_left_a1" ><i class="fa fa-tags">标签</i></button>'+
			                			'<div id="labelDiv" style="float: right;margin-left: 20px;display:none">'+
			                				'<input id="label_value" type="text" style="width:200px;" class="form-control validate" validateType="name"  placeholder="请输入填写便签名称"/>'+
			                				'<button type="button"  class="btn button-info saveLabelClick" >确定</button>'+
			                				'<button type="button"  class="btn button-default closeSaveLabelClick" >取消</button>'+
			                			'</div>'+
									'</div>'+
						       		'<div class="operate_btn">'+
									        '<button style="height: 34px;" type="button" class="btn btn-default margin_left_a1 dropdown-toggle" data-toggle="dropdown">标签</button>'+
									        '<button style="display : inline;margin-left: 20px;" type="button" class="btn button-info margin_left_a1" data-toggle="modal" data-target="#serachDiaLog">筛选</button>'+
									        '<ul class="dropdown-menu dwLabel">'+
									         
									        '</ul>'+
									'</div>'+
			                	'</div>'+
		                   '</div>'+
		                '</div> '+
		   	'</div>';*/
			var html=	 ''+
	 		'<div class="ibox-content" style="background-color: #ebedf1;height: 52px;">'+
	 	
	 		  '<div class="row">'+
              '<div class="col-sm-12" >'+
					'<!-- 基本搜索 -->'+
					'<div id="common_serch" class="searchForm">'+
						'<ul>'+
						'<div id="generalSearch">'+
							'<li >'+
							'<span class="title">日期</span>'+
							'<input  type="text" name="datePicker" class="datePicker" id="submitDate" style="height: 32px;width:250px">'+
						'</li>'+
						'</div>'+
					'</ul>'+
					'</div>'+
					'<div class="clickBtn">'+
						'<button type="button"  data-type="reload" class="btn btn_submit submitClick" style="margin-left: -20px;">搜索</button>'+
						'<button type="button"  class="btn btn_default generalReset" style="margin-left: 5px;" >清空</button>'+
					'</div>'+
					  '<div class="serach_fold" id="openBtnDiv" style="display: block;">'+
				        '<a class="openBtn">展开</a>'+
				        '<i class="fa fa-angle-down fa-color"></i>'+
				      '</div>'+
				      '<div class="serach_fold" id="closeBtnDiv" style="display: none;">'+
				        '<a class="closeBtn">关闭</a>'+
				        '<i class="fa fa-angle-up fa-color"></i>'+
				     '</div>'+
				 	'<div  class="bqBtn">'+
						'<div class="dropdown">'+
							'<button type="button"  class="btn btn_default"  data-toggle="modal" data-target="#serachDiaLog">筛选</button>'+
							'<button type="button"  data-type="reload" class="btn btn_submit submitClick dropdown-toggle"  data-toggle="dropdown">标签</button>'+
					        '<ul class="dropdown-menu  dwLabel">'+
					         
					        '</ul>'+
				        '</div>'+
				     '</div>'+
				'</div>'+
 			'</div>'+
 			'</div>'+
 			'<!-- 高级搜索内容  -->'+
 			'<div id="detail_serch"  class="ibox-search" style="background-color: #ebedf1;display: none">'+
 			  '<div class="row">'+
              '<div class="col-sm-12" >'+
 				'<div class="searchForm">'+
					'<ul id="advancedSearch">'+
						'<li >'+
							'<span class="title">MAC</span>'+
							'<input  type="text" id="mac" name="mac" placeholder="输入MAC精确查询" class="form-control content" validatetype="name">'+
						'</li>'+
					 '</ul>'+
					'</div>'+
                '</div>'+
 			'</div>'+
 		   '</div>'+
 			'<!--   高级搜索的操作栏   -->'+
 			'<div   class="ibox-tags" style="display:none" >'+
 				  '<div class="row">'+
	                    '<div class="col-sm-12" >'+
	                		'<div class="input-group">'+
	                			'<button type="button"  class="btn btn_submit  advanceedSerchClick" >搜索</button>'+
	                			'<button type="button"  class="btn btn_default resetAllClick margin_left_a1" >清空</button>'+
	                			'<button type="button"  class="btn btn_default windowTakeClick margin_left_a1" ><i class="fa fa-tags">标签</i></button>'+
	                			'<div id="labelDiv" style="float: right;margin-left: 20px;display:none;">'+
	                				'<input id="label_value" type="text" style="width:200px;" class="form-control validate" validateType="name"  placeholder="请输入填写便签名称"/>'+
	                				'<button type="button"  class="btn btn_submit  saveLabelClick" >确定</button>'+
	                				'<button type="button"  class="btn btn_default closeSaveLabelClick" >取消</button>'+
	                			'</div>'+
							'</div>'+
	                	'</div>'+
 			        '</div>'+
 			'</div>';
		   	
		   	$("#searchDiv").append(html);
			
		},
		/*初始化搜索配置**/
		appendDialog:function(){
			var html='<div class="modal inmodal" id="serachDiaLog" tabindex="-1" role="dialog"  aria-hidden="true">'+
				        '<div class="modal-dialog" style="width:750px;">'+
				            '<div class="modal-content animated fadeIn">'+
				                '<div class="modal-header" style="height: 40px;background-color: #003399;color:  white;">'+
				                   '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: white;margin-top: -7px;opacity:3">'+
										'&times;'+
									'</button>'+
									'<span class="modal-title"  style="float:left;margin-top: -10px;color: white; font-size: 18px;">'+
										'请选择搜索条件！'+
									'</span>'+
				                '</div>'+
				                '<div class="modal-body">'+
				                	'<!-- 选择配置的加载 -->'+
				                	'<div class="row">'+
					                   	'<div  class="col-sm-12">'+
					                   		'<table style="margin-top: 1%;margin-left: 20% ; border: 0px solid transparent;">'+
												  '<tr>'+
												     '<td><h5>选择配置：</h5></td>'+
												     '<td>'+
												     '<select style="width:200px;height:30px" id="confSelect">'+
												     '</select>'+
												     '<button class="btn btn-primary chooseConfSearchClick"   style="margin: 0px 5px 5px 10px;">选择</button>'+
												     '<button class="btn btn-primary confSerchClick"  style="margin: 0px 5px 5px 0px;">重置</button>'+
												     '</td>'+
												  '</tr>'+
											'</table>'+
					                   	'</div>'+
				                   	'</div>'+
				                   '<!-- 列的信息 -->'+
				                    '<div class="row" style="margin-top:20px;">'+
					                   	'<div  class="col-sm-5">'+
					                   		'<table class="table table-bordered table-striped"  style="width:100%;overflow:auto;text-align:center;" id="stable1" >'+
										      '<tr>'+
										        '<td><input type="checkbox" id="unAddedId"/></td>'+
										        '<td>未添加搜索结果列</td>'+
										      '</tr>'+
										      '<tbody id="unAddtr">'+
										      '</tbody>'+
										   '</table>'+
					                   	'</div>'+
					                   	'<div  class="col-sm-1">'+
											   '<button style="margin: 20px 10px 20px -10px;" class="btn btn-default moveRightClick" ><img src="'+getContextPath()+'/static/img/right.png" /></button>'+
											   '<button style="margin: 10px 10px 20px -10px;" class="btn btn-default moveLeftClick" ><img src="'+getContextPath()+'/static/img/left.png" /></button>'+
					                   	'</div>'+
					                   	'<div  class="col-sm-6">'+
					                   		'<table class="table table-bordered table-striped"  style="width:100%;overflow:auto;text-align:center;" id="stable1" >'+
										      '<tr>'+
										        '<td><input type="checkbox" id="addedId"/></td>'+
										        '<td>已添加搜索结果列</td>'+
										        '<td>移动</td>'+
										      '</tr>'+
										      '<tbody id="addtr">'+
										      '</tbody>'+
										   '</table>'+
					                   	'</div>'+
					                '</div>'+
				                '</div>'+
				                '<div class="modal-footer">'+
				               	   '<div style="float: right">'+
				                     '<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>'+
				                     '<button type="button" class="btn btn-primary saveConfClick" data-dismiss="modal">保存设置</button>'+
				                   '</div>'+
				                '</div>'+
				            '</div>'+
				        '</div>'+
				    '</div>';
			$("#dialogDiv").append(html); 
		},
		//初始化普通搜索		
		appendCommonSerch:function(){
			var html='<div class="row">'+
			          	 '<div  class="col-sm-8">'+
			          		'<div id="generalSearch">'+
			      		    '</div>'+
			      		'</div>'+
				       	'<div class="col-sm-2" >'+
				       		'<div class="input-group commonBtn">'+
							        '<button style="height: 34px;" type="button" class="btn btn-default margin_left_a1 dropdown-toggle" data-toggle="dropdown">搜索</button>'+
							        '<button style="display : inline;margin-left: 20px;" type="button" class="btn button-info margin_left_a1" data-toggle="modal" data-target="#serachDiaLog">清空</button>'+
							        '<ul class="dropdown-menu dwLabel">'+
							         
							        '</ul>'+
							'</div>'+
				       	'</div>'+
				   	    '<div class="col-sm-2" >'+
				   	    	'<div id="advancedId">'+
				   	    		'<a class="btn">高级搜索</a>'+
				   	    		'<i class="fa fa-angle-down"></i>'+
				   	    	'</div>'+
				   	    	'<div id="commonId" style="display:none" >'+
								'<a class="btn">普通搜索</a>'+
								'<i class="fa fa-angle-up"></i>'+
							'</div>'+
		          		'</div>'+
			      '</div>';
		//	$("#common_serch").append(html); 
		},
		//添加搜索添加内容
		addDwLabel:function(){
			var labelJson = searchExternalView.returnLabelJson();
			$(".dwLabel").empty();
			if(labelJson!="" && labelJson!=undefined){
				var html="";
				$.each(labelJson, function(i, n){
					 html+="<li ><a class=\"a_content  labelClick\"  params='"+JSON.stringify(n.nodes)+"' >"+n.text+"</a><i class=\"fa fa-trash-o fa-lg a_delete \" del_id="+n.id+"></i></li>";
				});
				$(".dwLabel").append(html);
			}
		},
		//添加搜索添加内容
		addOperateBtn:function(){
			var btnJson = searchExternalView.returnBtnJson();
			//$(".dwLabel").empty();
			if(btnJson!="" && btnJson!=undefined){
				var html="";
				$.each(btnJson, function(i, n){
					 html+='<button id="'+n.name+'" name="'+n.name+'"  type="button" class="'+n.btnClass+'">'+n.text+'</button>';
				});
				$(".commonBtn").append(html);
			}
		},
		//高级搜索条件内容
		addAdvanced:function(){
			var criteriaJson = searchExternalView.returnCriteriaJson();
			if(criteriaJson!="" && criteriaJson!=undefined){
				$("#advancedSearch").empty();
				$.each(criteriaJson, function(i, n){
					 var html="";
					 //已添加列 未添加的列不展示
					 if(n.state=="1"){
					 	//type 1文本框，2下拉框，3 时间框 ,4 时间段 ,5 时间点 ,6 下拉框 (单个根据url反显) ，7 选择自定义 根据双下拉框 ，8 时间点选择区域
						switch (n.type) {
						case "1":
							html+=	html+='<li >'+
														'<span class="title">'+n.text+'</span>'+
					                   					'<input type="text" id="'+n.name+'" name="'+n.name+'" placeholder="输入'+n.text+'精确查询" class="form-control content validate" validateType="name"   >'+
					                   					'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
					                   			'</li>';
							break;
						 case "2":
							 		html+='<li >'+
            			 		'				<span class="title">'+n.text+'</span>'+
					                   					'<select id="'+n.name+'" name="'+n.name+'" class="form-control content validate" validateType="select") >'+
					                   						//默认提示语
					                   						'<option value="">——请选择——</option>';
					                   					//加载下拉框的内容
				                   						 $.each(n.option, function(i, n){
															html+='<option value='+n.value+'>'+n.text+'</option>';
														 });
															 
					                                        
				                   		html+= '</select><div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div></li>';
						 	
						 	break;
						 case "3"://单个时间框
							 html+='<li >'+
			 									'<span class="title">'+n.text+'</span>'+
														         '<input id="'+n.name+'"  name="'+n.name+'"  type="datetime" placeholder="输入'+n.text+'精确查询" name="start_time"  style="background-color: #ffffff;" class="form-control content" readonly>'+
														     '<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
										'</li>';
						 	break;	
						  case "4"://时间区域
								 html+='<li >'+
														'<span class="title">'+n.text+'</span>'+
							               			    	 '<input class="form-control content" id="'+n.name+'" name="'+n.name+'"  type="datetime"  placeholder="输入'+n.text+'精确查询" style="background-color: #ffffff;"  readonly>'+
							               			      '<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
						               			  	'</li>';
						 	break;
						  case "5"://时间点
								 html+='<li >'+
													'<span class="title">'+n.text+'</span>'+
				                   					'<input style="background-color: #ffffff;"   placeholder="输入'+n.text+'精确查询" type="text" id="'+n.name+'" name="'+n.name+'"  class="form-control content" readonly>'+
				                   				    '<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
				                   					'</li>';
							 	break;
						   case "6":
								 html+='<li >'+
								 				'<span class="title">'+n.text+'</span>'+
				                   					'<select id="'+n.name+'" name="'+n.name+'" class="form-control content validate" validateType="select") >'+
				                   						//默认提示语
				                   						'<option value="">——请选择——</option>';
				                   					//加载下拉框的内容
							 						var url=getContextPath()+n.url;
							 						if(n.url!=undefined && n.url!=""){
							 							$.ajax({
				                   							type : "POST",
				                   							url : url,
				                   							async:false,
				                   							dataType : "json",
				                   							success : function(data) {
				                   								 $.each(data, function(i, n){
																	html+='<option value='+n.ID+'>'+n.NAME+'</option>';
																 });
				                   							}
				                   						});	
							 						}
			                   						 
				                                        
				            html+= '</select>'+
				            	'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
				            	'</li>';
					 	  break;
						  case "7":
								 html+='<li >'+
													'<span class="title">'+n.text+'</span>'+
					                   				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goPopover('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
					                   				'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
					                   				'</li>';
						 	
						 	 break;
						case "8":
							 html+='<li >'+
										'<span class="title">'+n.text+'</span>'+
		                   				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goInterval('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
		                   				'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
		                   				'</li>';
						 	break;

					case "9":
						 html+='<li >'+
							'<span class="title">'+n.text+'</span>'+
            				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goMessage('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
            				'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
            				'</li>';
						 	break;
						default:
							break;
						}
						$("#advancedSearch").append(html);
					 }
						
					});
			}
			
		},
		//返现标签
		showLabel:function(item){
			//转换数据
			  var json=JSON.parse(item);
			  //返现数据
			  $.each(json, function(i, n){
				  	//初始化校验样式\
				  var obj=$("#"+n.name+"");
				  $.fn.validateClear(obj);
					switch (n.type) {//判断类型
					case "1"://文本
						$("#"+n.name+"",$("#detail_serch")).val(n.value);
						break;
					case "2"://下拉框
						$("#"+n.name+" option" ,$("#detail_serch")).removeAttr("selected");
						$("#"+n.name+" option[value="+n.value+"]" ,$("#detail_serch")).prop("selected",true);
						break;
					case "3"://日期
						$("#"+n.name+"" ,$("#detail_serch")).val(n.value);
						break;
					case "4"://时间区间
						$("#"+n.name+"" ,$("#detail_serch")).val(n.value);
						break;
					case "5"://时间点
						$("#"+n.name+"" ,$("#detail_serch")).val(n.value);
						break;
					case "6"://url下拉框
						$("#"+n.name+" option" ,$("#detail_serch")).removeAttr("selected");
						$("#"+n.name+" option[value="+n.value+"]" ,$("#detail_serch")).prop("selected",true);
						break;
					default:
						$("#"+n.name+"" ,$("#detail_serch")).val(n.value);	
						break;
					}
				});
		},
		//点击标签添加json 打开窗口
		windowTake:function(){
	    	 	//打开添加标签的div 
			  $.fn.validateClear($("#label_value"));
			  $("#label_value").val("");
	    	  $("#labelDiv").show(); 
		},
		//选择配置事件
		chooseConfSearch:function(confJson){
			if(confJson!="" && confJson!=undefined){
				//获取配置下拉框的值
				var selectValue= $("#confSelect option:selected").val();
				$.each(confJson, function(i, n){
					//判断配置id相同反显
					if(n.id==selectValue){
						$.fn.appendConf(n.nodes);
					}
				});
			}
			
		},
		//初始化选择配置的相关信息
		confSerch:function(){
			var confJson = searchExternalView.returnConfJson();
			if(confJson!="" && confJson!=undefined){
				$("#confSelect").empty();
				var html="";
				$.each(confJson, function(i, n){
					if(n.ischeck=="1"){
						html+='<option value="'+n.id+'" selected="true" >'+n.text+'</option>';
						//加载默认选中列的信息
						$.fn.appendConf(n.nodes);
					}else{
						html+='<option value="'+n.id+'">'+n.text+'</option>';
					}
					 
				});
				$("#confSelect").append(html);
			}
			
		},
		//基本搜索栏
		addComSerch:function(definiteJson){
			if(definiteJson!="" && definiteJson!=undefined){

				$("#generalSearch").empty();
				$.each(definiteJson, function(i, n){
					 var html="";
					 //已添加列 未添加的列不展示
					 if(n.state=="1"){
					 	//type 1文本框，2下拉框，3 时间框 ,4 时间段 ,5 时间点 ,6 下拉框 (单个根据url反显) ，7 选择自定义 根据双下拉框 ，8 时间点选择区域
						switch (n.type) {
						case "1":
							html+='<li >'+
											'<span class="title">'+n.text+'</span>'+
											'<input type="text" id="'+n.name+'" name="'+n.name+'" placeholder="输入'+n.text+'精确查询" class="form-control content validate" validateType="name"   >'+
										'</li>';
							break;
						 case "2":
								 html+='<li >'+
				                   			 		'<span class="title">'+n.text+'</span>'+
					                   					'<select id="'+n.name+'" name="'+n.name+'" class="form-control content validate" validateType="select") >'+
					                   						//默认提示语
					                   						'<option value="">——请选择——</option>';
					                   					//加载下拉框的内容
				                   						 $.each(n.option, function(i, n){
															html+='<option value='+n.value+'>'+n.text+'</option>';
														 });
															 
					                                        
					            html+= '</select></li>';
						 	
						 	break;
						 case "3"://单个时间框
						 	 html+='<li >'+
            			 					'<span class="title">'+n.text+'</span>'+
										    '<input id="'+n.name+'"  name="'+n.name+'"  type="datetime" placeholder="输入'+n.text+'精确查询" name="start_time"  style="background-color: #ffffff;" class="form-control content" readonly>'+
										'</li>';
														
						 	break;	
						  case "4"://时间区域
						 	 html+='<li >'+
	 											'<span class="title">'+n.text+'</span>'+
				               			    	 '<input class="form-control content" id="'+n.name+'" name="'+n.name+'"  type="datetime"  placeholder="输入'+n.text+'精确查询" style="background-color: #ffffff;"  readonly>'+
						               	'</li>';
						 	break;
						  case "5"://时间点
							 	 html+='<li >'+
													'<span class="title">'+n.text+'</span>'+
				                   					'<input style="background-color: #ffffff;"   placeholder="输入'+n.text+'精确查询" type="text" id="'+n.name+'" name="'+n.name+'"  class="form-control content" readonly>'+
				                   			'</li>';
							 	break;
						   case "6":
							 html+='<li >'+
							 						'<span class="title">'+n.text+'</span>'+
				                   					'<select id="'+n.name+'" name="'+n.name+'" class="form-control content validate" validateType="select") >'+
				                   						//默认提示语
				                   						'<option value="">——请选择——</option>';
				                   					//加载下拉框的内容
							 						var url=getContextPath()+n.url;
							 						if(n.url!=undefined && n.url!=""){
							 							$.ajax({
				                   							type : "POST",
				                   							url : url,
				                   							async:false,
				                   							dataType : "json",
				                   							success : function(data) {
				                   								 $.each(data, function(i, n){
																	html+='<option value='+n.ID+'>'+n.NAME+'</option>';
																 });
				                   							}
				                   						});	
							 						}
			                   						 
				                                        
				            html+= '</select></li>';
					 	  break;
						  case "7":
								 html+='<li >'+
													'<span class="title">'+n.text+'</span>'+
					                   				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goPopover('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
					                   		'</li>';
						 	
						 	 break;
						case "8":
						 		 html+='<li >'+
												'<span class="title">'+n.text+'</span>'+
				                   				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goInterval('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
				                   			'</li>';
						 	break;
						case "9":
							 html+='<li >'+
								'<span class="title">'+n.text+'</span>'+
	            				"<input params='"+JSON.stringify(n)+"' type=\"text\" onclick=goMessage('"+n.name+"')  id='"+n.name+"' name='"+n.name+"' pvalue class=\"form-control content validate\" data-placement=\"bottom\" rel=\"popover\" />"+
	            				'<div class="errorDiv"><i class="fa fa-exclamation-circle errorIcon"></i><span class="errorMsg">错误提醒</span>  </div>'+
	            				'</li>';
							 	break;
						default:
							break;
						}
						$("#generalSearch").append(html);
					 }
						
					});
			
			}
		},
		//表单的验证方法
		Validation:function(obj,valiType){
			var nameVal = obj.val();
			switch (valiType) {
				case "name":
					if(nameVal!=""){
						if((validateRegExp.name).test(nameVal)==false){//失败
							$.fn.validateError(obj);
						}else{//成功
							$.fn.validateSuccess(obj);
						};
					}else{
						$.fn.validateClear(obj);
					}
					break;
				case "select":
					if(nameVal!=""){//成功
						$.fn.validateSuccess(obj);
					}else{//为空时取消成功选中状态
						$.fn.validateClear(obj);
					}
					break;
				case "definite":	
					var name=obj.attr("name");
					if(nameVal!="" && name!=undefined){//成功
						$.fn.validateSuccess(obj);
					}else{//为空时取消成功选中状态
						$.fn.validateError(obj);
					}
					break;
				default:
					break;
				
			}
		},
		//校验失败的样式
		validateError:function(obj){
			if(obj.is(".has-success")){
				obj.removeClass("has-success");
			}
			if(!obj.is(".has-error")){
				obj.addClass("has-error");
			}
		},
		//校验成功的样式
		validateSuccess:function(obj){
			if(obj.is(".has-error")){
				obj.removeClass("has-error");
			}
			if(!obj.is(".has-success")){
				obj.addClass("has-success");
			}
		},
		//初始化样式
		validateClear:function(obj){
			//判断样式是否存在 存在则删除
			if(obj.is(".has-error")){
				obj.removeClass("has-error");
			}
			if(obj.is(".has-success")){
				obj.removeClass("has-success");
			}
		},
		//保存标签
		saveLabel:function(){
			var criteriaJson = searchExternalView.returnCriteriaJson();
			if(criteriaJson!="" && criteriaJson!=undefined){
				arr_json=[];
				 //得到搜索条件的json
	    	 	$.each(criteriaJson, function(i, n){
	    	 		var text=n.text;
	    	 		var type=n.type;
	    	 		var id=n.name;
	    	 		var value="";
	    	 		//获取目前的值
	    	 		//判断获取值
	    	 		switch (n.type) {
					case "2":
						value= $("#"+id+" option:selected",$("#detail_serch")).val();
						break;
					default:
						value=$("#"+id+"",$("#detail_serch")).val();
						break;
					}
	    	 		if(value!=undefined && value!=""){
	    	 			arr_json.push({
		    	 			"id":n.id,
					        "text":n.text,
					        "type":n.type,
					        "name":n.name,
					        "value":value
					    });
	    	 		}
	    	 		
	    	 	});  
			}
			//获取标签名称
			var label_value=$("#label_value").val();
			if(label_value!=""){
				//搜索的json
				var label_Json=[];
				label_Json.push({
					"text":label_value,
					"nodes":arr_json
				});
				//跳转外部事件
				var code=searchExternalView.addLabelJson(label_Json);
				$("#labelDiv").hide();
			}else{
				$.fn.validateError($("#label_value"));
			}
		},
		//取消操作
		closeSaveLabel:function(){
			$("#label_value").val("");
			$("#labelDiv").hide();
		},
		//添加选中与为选中的视图渲染
		appendConf:function(json){
			//清空表格内容
			$("#unAddtr").empty();
			$("#addtr").empty();
			//加载  
			$.each(json, function(i, n){
				switch (n.state) {//判断状态
				case "0"://未选中
					var html='<tr id="'+n.id+'">'+
								 '<td><input type="checkbox" name="unAdded" value="'+n.id+'-'+n.text+'"/></td>'+
								 '<td>'+n.text+'</td>'+
							'</tr>';
						$("#unAddtr").append(html);
					break;
				case "1"://选中
					var html='<tr id="'+n.id+'">'+
							        '<td><input type="checkbox" name="added" value="'+n.id+'-'+n.text+'"/></td>'+
							        '<td>'+n.text+'</td>'+
							        '<td class="moveTr">'+
								        '<button class="btn btn-default moveTrClick" params="up" >'+
								        	'<img src="'+getContextPath()+'/static/img/up.png" />'+
								        '</button>'+
								        '<button class="btn btn-default moveTrClick" params="down" >'+
								        	'<img src="'+getContextPath()+'/static/img/down.png" />'+
								        '</button>'+
							        '</td>'+
							      '</tr>';
						$("#addtr").append(html);  
					break;
				default:
					break;
				}
				
			});
		},
		//下拉框搜索事件
		showSelect:function(text,name,id){
			if($("#dropSelectId").is(".has-error")){
				$("#dropSelectId").removeClass("has-error");
			}
			$("#serchText").attr("placeholder","请输入:"+text+"");
			$("#serchText").attr("name",name);
			$(".definiteSerch").html(text);
		},
		generalResetAll:function(){
			//文本框和时间框
			  $("#generalSearch").find('input[type=text],input[type=datetime]').each(function() {
			 	 //表单重置
				 $(this).val('');
				 //初始化样式
				 $.fn.validateClear($(this));
			  });
			   //下拉框
			  $("#generalSearch").find('select').each(function() {
					$(this).find("option:first").attr("selected",true).siblings("option").attr("selected",false);
			    //默认选中第一行
					//$(this).find("option").attr('selected',false);
			    	//$(this).find("option:first").attr("selected","true");
			   	 //初始化样式
			   	$.fn.validateClear($(this));
			  });
		},
		//清空重置方法
		resetAll:function(){
			//文本框和时间框
			  $("#advancedSearch").find('input[type=text],input[type=datetime]').each(function() {
			 	 //表单重置
				 $(this).val('');
				 //初始化样式
				 $.fn.validateClear($(this));
			  });
			   //下拉框
			  $("#advancedSearch").find('select').each(function() {
					$(this).find("option:first").attr("selected",true).siblings("option").attr("selected",false);
			    //默认选中第一行
					//$(this).find("option").attr('selected',false);
			    	//$(this).find("option:first").attr("selected","true");
			   	 //初始化样式
			   	$.fn.validateClear($(this));
			  });
		},
		//下拉框搜索事件
		moveRight:function(){
			//得到未添加搜索列选中的id
		 	var chkValue = $("input:checkbox[name='unAdded']:checked").map(function() {
							return $(this).val();
						}).get().join(',');
		 	if(chkValue!=""){
		 		 //向右添加内容
		 		 $.fn.appendAdded(chkValue);
		 		 $("#unAddedId").prop("checked",false);
		 	}
		 	
		},
		//下拉框搜索事件
		moveLeft:function(){
			//得到未添加搜索列选中的id
		 	var chkValue = $("input:checkbox[name='added']:checked").map(function() {
							return $(this).val();
						}).get().join(',');
		 	if(chkValue!=""){
		 		 //向右添加内容
		 		 $.fn.appendUnAdded(chkValue);
		 		 //初始化复选框按钮
		 		 $("#addedId").prop("checked",false);
		 	}
		 	
		},
		//下拉框搜索事件
		removeAt:function(id){
			$("#"+id+"").remove();
		 	
		},
		/**
		 *添加列 分两种方法
		 *第一种:未添加搜索的列 appendUnAdded
		 *第二种:已添加搜索的列 appendAdded
		 */
		appendAdded:function(chkValue){
			var valueArray=chkValue.split(",");
		    for(var i=0;i<valueArray.length;i++){
		    	var array=valueArray[i].split("-");
		    	var text=array[1];
		    	var id=array[0];
		    	$.fn.removeAt(id);
			    var html='<tr id="'+id+'">'+
					        '<td><input type="checkbox" name="added" value="'+valueArray[i]+'"/></td>'+
					        '<td>'+text+'</td>'+
					        '<td class="moveTr">'+
						        '<button class="btn btn-default moveTrClick"  params="up"  >'+
					        	'<img src="'+getContextPath()+'/static/img/up.png" />'+
						        '</button>'+
						        '<button class="btn btn-default moveTrClick" params="down" >'+
						        	'<img src="'+getContextPath()+'/static/img/down.png" />'+
						        '</button>'+
					        '</td>'+
					      '</tr>';
			 	$("#addtr").append(html);
		 	//添加成功之后移除之前
		    }
		 	
		},
		//下拉框搜索事件
		appendUnAdded:function(chkValue){
			var valueArray=chkValue.split(",");
		    for(var i=0;i<valueArray.length;i++){
		    	var array=valueArray[i].split("-");
		    	var text=array[1];
		    	var id=array[0];
		    	$.fn.removeAt(id);
			    var html='<tr id="'+id+'">'+
				        '<td><input type="checkbox" name="unAdded"  value="'+valueArray[i]+'"/></td>'+
				        '<td>'+text+'</td>'+
				      '</tr>';
				$("#unAddtr").append(html);
		   }
		 	
		},
		//下拉框搜索事件
		moveTr:function(t,oper){
			var data_tr=t.parent().parent(); //获取到触发的tr
		       if(oper=="up"){    //向上移动
		          if($(data_tr).prev().html()==null){ //获取tr的前一个相同等级的元素是否为空
		              alert("已经是最顶部了!");
		              return;
		          }{
		               $(data_tr).insertBefore($(data_tr).prev()); //将本身插入到目标tr的前面 
		          }
		          }else{
		                if($(data_tr).next().html()==null){
		                alert("已经是最低部了!");
		                return;
		            }{
		                 $(data_tr).insertAfter($(data_tr).next()); //将本身插入到目标tr的后面 
		            }
		          }
		 	
		}
		
	});
})(window.jQuery);

function goInterval(id){
	var el = $("#"+id+"");
	var params=el.attr("params");
	 var json=  JSON.parse(params);
	var size=el.parent().find('div.popover').size();
	if(size>0){
		el.popover('destroy');
	}else{
		el.popover({
           html: true,  //实现对html可写
		   content: function() {
			   //执行两次暂未解决方法
		     return popover_content2(json,id); //内容函数
		   }
        }).popover("show");
		  // 调用 popover 的 shown 事件来加载内容
	}
	//添加内容
		$.each(json.nodes, function(i, n){
			switch (n.type) {
			case "5":
				 jeDate("#"+n.name+"",{
				      //  minDate:"01:02:08",              //最小日期  设置时间段
				      //  maxDate:"15:25:35",              //最大日期
				        format: "hh:mm:ss"
				    });
				break;
			default:
				break;
			}
		});
	
}
function goMessage(id){
	var el = $("#"+id+"",$("#detail_serch"));
	 var params=el.attr("params");
	 var json=  JSON.parse(params);
	var size=el.parent().find('div.popover').size();
	if(size>0){
		el.popover('destroy');
	}else{
		el.popover({
           html: true,  //实现对html可写
		   content: function() {
			   //执行两次暂未解决方法
		     return popoverMessage_content(json,id); //内容函数
		   }
        }).popover("show");
		  // 调用 popover 的 shown 事件来加载内容
	}
}
function goPopover(id){
	var el = $("#"+id+"",$("#detail_serch"));
	 var params=el.attr("params");
	 var json=  JSON.parse(params);
	var size=el.parent().find('div.popover').size();
	if(size>0){
		el.popover('destroy');
	}else{
		el.popover({
           html: true,  //实现对html可写
		   content: function() {
			   //执行两次暂未解决方法
		     return popover_content(json,id); //内容函数
		   }
        }).popover("show");
		  // 调用 popover 的 shown 事件来加载内容
	}
}
function closeSt(id){
	$("#"+id+"").trigger("click");
}
//内容函数，同时对样式进行修改
function popover_content2(json,id) {
	var tbId="tb"+id;
 var html=' <table class="table">'+
			 '<tbody id='+tbId+'>'+
			 	 '<tr style="background-color:#F7F7F7">'+
					'<td colspan="2">'+json.text+'<button type="button" class="close" aria-label="Close"><span aria-hidden="true" class="'+json.name+'" onclick=closeSt("'+json.name+'")>×</span></button></td>'+
							 '</tr>';
			 	$.each(json.nodes, function(i, n){
			 		switch (n.type) {
					case "5":
						html+='<tr>'+
						'<td colspan="2"><span>'+n.text+'</span>'+
           					'<input style="background-color: #ffffff;"   placeholder="输入'+n.text+'精确查询" type="text" id="'+n.name+'" name="'+n.name+'"  class="form-control" >'+
							 '</td>'+
						'</tr>';
						break;
					case "1":
						html+='<tr>'+
						'<td colspan="2"><span>'+n.text+'</span>'+
								'<input type="text" id="'+n.name+'" name="'+n.name+'" placeholder="输入'+n.text+'精确查询" class="form-control validate"  >'+
							 '</td>'+
						'</tr>';
						break;
					default:
						break;
					}
					
				 });
			     html+= '<tr>'+
					 '<td><button type="button" class="btn button-primary content-left" onclick=confimst2("'+id+'") >确定</button></td>'+
					 '<td><button type="button" class="btn button-primary content-left"  >清空</button></td>'+
				 '</tr>'+
			 '</tbody>'+
		 '</table>';
 return  html;
}
//内容函数，同时对样式进行修改
function popover_content(json,id) {
	var tbId="tb"+id;
 var html=' <table class="table">'+
			 '<tbody id='+tbId+'>'+
			 	 '<tr style="background-color:#F7F7F7">'+
					'<td colspan="2">'+json.text+'<button type="button" class="close" aria-label="Close"><span aria-hidden="true" class="'+json.name+'" onclick=closeSt("'+json.name+'")>×</span></button></td>'+
				 '</tr>';
			     $.each(json.nodes, function(i, n){
					html+='<tr>'+
							'<td colspan="2"><span>'+n.text+'</span>'+
									'<select class="form-control select_content"   id="'+n.name+'" params="'+n.url+'" onchange=gradeChange(this.options[this.selectedIndex].value,"'+tbId+'") >';
									if(n.url!=undefined && n.url!=""){
										var url=getContextPath()+n.url;
										if(i==0){
											 $.ajax({
													type : "Post",
													url : url,
													cache: true,
													async:false,
													data : {
														companyId : "1"
													},
													dataType : "json",
													success : function(result) {
														//var list=result.depts;
														$.each(result,function(i,n2){
															html+='<option value="'+n2.pkId+'">'+n2.text+'</option>';
														});
													}
												});
										}
									}
								
									 html+='</select>'+
								 '</td>'+
							'</tr>';
				 });
			     html+= '<tr>'+
					 '<td><button type="button" class="btn button-primary content-left" onclick=confimst("'+id+'") >确定</button></td>'+
					 '<td><button type="button" class="btn button-primary content-left"  id="add">清空</button></td>'+
				 '</tr>'+
			 '</tbody>'+
		 '</table>';
 return  html;
}
function popoverMessage_content(json,id) {
	var tbId="tb"+id;
		var html=' <table class="table">'+
			 '<tbody id='+tbId+'>'+
			 	 '<tr style="background-color:#F7F7F7">'+
					'<td colspan="2">'+json.text+'<button type="button" class="close" aria-label="Close"><span aria-hidden="true" class="'+json.name+'" onclick=closeSt("'+json.name+'")>×</span></button></td>'+
				 '</tr>';
					 $.each(json.nodes, function(i, n){
							switch (n.type) {
							case "2":
								html+='<tr>'+
								'<td colspan="2"><span>'+n.text+'</span>'+
										'<select class="form-control select_content"   id="'+n.name+'"  onchange=messageSelect(this.options[this.selectedIndex].value) >'+
										'<option value="&gt;">&gt;</option><option value="=">=</option><option value="&lt;">&lt;</option>'+
										'</select>'+
									 '</td>'+
								'</tr>';
								break;
							case "1":
								html+='<tr>'+
								'<td colspan="2"><span>'+n.text+'</span>'+
										'<input type="text" id="'+n.name+'" name="'+n.name+'" placeholder="输入'+n.text+'精确查询" class="form-control validate"  >'+
									 '</td>'+
								'</tr>';
								break;
							default:
								break;
							}
							
						 });
			     html+= '<tr>'+
					 '<td><button type="button" class="btn button-primary content-left" onclick=confimst3("'+id+'") >确定</button></td>'+
					 '<td><button type="button" class="btn button-primary content-left"  id="add">清空</button></td>'+
				 '</tr>'+
			 '</tbody>'+
		 '</table>';
 return  html;
}
function confimst(id){
	//input的id
	var  tid="tb"+id;//tbody的id
	//获取下拉框的内容
	//第一个下拉框
	var sid1=$("#"+tid+" tr:eq(1)").find("select").attr("id");
	var sid2=$("#"+tid+" tr:eq(2)").find("select").attr("id");
	var selectValue1= $("#"+sid1+" option:selected").val();
	var selectText1= $("#"+sid1+" option:selected").text();
	var selectValue2= $("#"+sid2+" option:selected").val();
	var selectText2= $("#"+sid2+" option:selected").text();
	var showText=selectText1+"-"+selectText2;
	var showVlaue=selectValue1+";"+selectValue2;
	$("#"+id+"").val(showText);
	$("#"+id+"").attr("pvalue",showVlaue);
	//关闭窗口
	closeSt(id);
	
}
function confimst2(id){
	//input的id
	var  tid="tb"+id;//tbody的id
	//获取下拉框的内容
	//第一个下拉框
	var sid1=$("#"+tid+" tr:eq(1)").find("input").attr("id");
	var sid2=$("#"+tid+" tr:eq(2)").find("input").attr("id");
	var selectValue1= $("#"+sid1+"").val();
	var selectValue2= $("#"+sid2+"").val();
	var showVlaue=selectValue1+";"+selectValue2;
	$("#"+id+"").val(showVlaue);
	$("#"+id+"").attr("pvalue",showVlaue);
	//关闭窗口
	closeSt(id);
	
}
function confimst3(id){
	//input的id
	var  tid="tb"+id;//tbody的id
	//获取下拉框的内容
	//第一个下拉框
	var sid1=$("#"+tid+" tr:eq(1)").find("select").attr("id");
	var selectValue1= $("#"+sid1+" option:selected").val();
	var selectText1= $("#"+sid1+" option:selected").text();
	
	var sid2=$("#"+tid+" tr:eq(2)").find("input").attr("id");
	var selectValue2= $("#"+sid2+"").val();
	var showVlaue=selectValue1+";"+selectValue2;
	$("#"+id+"").val(showVlaue);
	$("#"+id+"").attr("pvalue",showVlaue);
	//关闭窗口
	closeSt(id);
	
}
function gradeChange(value,id){
	//获取变化下拉的id
	var id=$("#"+id+" tr:eq(2)").find("select").attr("id");
	var params=$("#"+id+"").attr("params");
	var url=getContextPath()+params;
	 $.ajax({
			type : "Post",
			url : url,
			data : {
				companyId : "1",
				parentId : value
			},
			dataType : "json",
			success : function(result) {
				var html="";
				$.each(result,function(i,n){
					html+='<option value="'+n.pkId+'">'+n.text+'</option>';
				});
				$("#"+id+"").empty();
				$("#"+id+"").append(html);
				
			}
		});
}

//下拉事件
//获取项目路径
function getContextPath() {

    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}