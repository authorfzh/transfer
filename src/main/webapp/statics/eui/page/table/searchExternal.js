

/**
 * 创建外部对象
 * */

var SearchExternalView=function(){
};
var searchExternalView=new SearchExternalView();

/**
 * 外部向内部提供的参数方法
 * */

/**
 * 属性信息
 * definiteJson 普通搜索条件的json
 * confJson  配置筛选搜索条件的json
 * criteriaJson 初始化高级的搜索条件的json
 * labelJson 标签的json
 * 
 * */
SearchExternalView.prototype.returnDefiniteJson = function() {
	/*return querySearchAjax("customer_code","generalSearch");*/
	return generalSearchJson;
};
SearchExternalView.prototype.returnCriteriaJson = function() {
	/**
	 * 参数：编码code,搜索类型 type,用户id
	 * */
	/*return querySearchAjax("customer_code","advancedSearch");*/
	return advancedSearchJson;
};
/**
 * ajax 返回的属性json的参数
 * 编码code ,搜索类型 type ,用户id,公司id
 * */
SearchExternalView.prototype.returnConfJson = function() {
	/**
	 * 参数：编码code ,搜索类型 type
	 * */
	
	/*return querySearchAjax("customer_code","configSearch");*/
	return configSearchJson;
};

SearchExternalView.prototype.returnLabelJson = function() {
	/*return querySearchAjax("customer_code","labelSearch");*/
	return labelSearchJson;
};
//外部添加自定义按钮
SearchExternalView.prototype.returnBtnJson = function() {
	var returnBtnJson="";
	return returnBtnJson;
};

/**
 * 内部向外部透出的方法
 * */
//保存展示列配置
SearchExternalView.prototype.saveConf = function(json,id) {
	var code="";
	var data = {} ;
	var jsonStr=JSON.stringify(json); 
	var idStr={id: id,type:"configSearch",code:"customer_code"};
	var jsonId=JSON.stringify(idStr);
	 data["configJson"] = jsonStr;
	 data["jsonId"] = jsonId;
	//var params={json:jsonStr};
	var url=getContextPath()+"/searchQuery/updateConfig.action";
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(data),
		contentType : "application/json;charsetset=UTF-8",//必须
		dataType : "json",
		cache: true,
		async:false,
		success : function(data) {
			code=data.code;
			//验证是否成功
			if(data.code="0000"){
				//保存成功刷新 高级和配置
				searchInternal.addAdvanced();
				searchInternal.confSerch();
				searchInternal.init();
				$('#openBtnDiv').trigger("click");
				toastr.success("保存配置成功!");
			}else{
				toastr.success("保存配置失败!");
			}
			
		}
	});
	return code;
};	
//普通搜索查询
SearchExternalView.prototype.definiteSerch = function(json) {
    searchInfoByKeyWord(json);
};	


//高级查询
SearchExternalView.prototype.advanceedSerch = function(json) {
	console.log(json)
   // searchInfoByKeyWord(json);
};	

//处理保存标签
SearchExternalView.prototype.addLabelJson = function(json) {
	var code="";
	var data = {} ;
	var jsonStr=JSON.stringify(json); 
	 data["labelJson"] = jsonStr;
	//var params={json:jsonStr};
	 var otherStr={code:"customer_code"};
	 var jsonOther=JSON.stringify(otherStr);
	 data["jsonOther"] = jsonOther;
	var url=getContextPath()+"/searchQuery/insertSearchConfig.action";
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(data),
		contentType : "application/json;charsetset=UTF-8",//必须
		dataType : "json",
		cache: true,
		async:false,
		success : function(data) {
			code=data.code;
			if(data.code="0000"){
				searchInternal.addDwLabel();
				searchInternal.init();
				toastr.success("创建标签成功!");
			}else{
				toastr.success("创建标签失败!");
			}
		}
	});
	return code;
};

//删除标签
SearchExternalView.prototype.delLabel = function(id) {
	var code="";
	var url=getContextPath()+"/searchQuery/deleteLabel.action?id="+id;
	$.ajax({
		type : "POST",
		url : url,
		contentType : "application/json;charsetset=UTF-8",//必须
		dataType : "json",
		cache: true,
		async:false,
		success : function(data) {
			code=data.code;
			if(data.code="0000"){
				searchInternal.addDwLabel();
				searchInternal.init();
				toastr.success("删除标签成功!");
			}else{
				toastr.success("删除标签失败!");
			}
		}
	});
	return code;
};
//字段扩展方法
SearchExternalView.prototype.extendClick = function(json) {
	alert("1");
};

//请求ajax方法

function querySearchAjax(code,type){
	var params={code:code,type:type};
	var url=getContextPath()+"/searchQuery/querySearch.action";
	$.ajax({
		type : "POST",
		url : url,
		async:false,
		data : params,
		dataType : "json",
		success : function(data) {
			json=data;
		}
	});
	return json;
	
}
