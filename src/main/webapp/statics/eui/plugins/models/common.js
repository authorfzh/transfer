var webcallCommon2 = {
	companyId: cId ? cId : 1,
	converDate: function(value){
		var rgx = /^(\d{4})\-(\d{1,2})\-(\d{1,2})$/;
		if(webcallCommon.isNotNull(value)){
			if(value.match(rgx)){
				var ss = value.split("-");
				var y = parseInt(ss[0],10);
				var m = parseInt(ss[1],10);
				var d = parseInt(ss[2],10);
				value = m+"/"+d+"/"+y;
			} 
		}
		return value;		
	},	
	converTime: function(value){
		var rgx = /-/g;
		if(webcallCommon.isNotNull(value)) value = value.replace(rgx,'/');
		return value;		
	},
	setInitSearchTime: function(){
		var endDate = new Date();
		var startDate = new Date(endDate.getTime() - 15*24*60*60*1000);
		var sm = startDate.getMonth() + 1;
		var em = endDate.getMonth() + 1;
		var monthStart = sm < 10 ? "0" + sm : sm;
		var monthEnd = em < 10 ? "0" + em : em;
		var endDateStr = endDate.getFullYear()+"-"+monthEnd+"-"+endDate.getDate() + " 23:59:59";
		var startDateStr = startDate.getFullYear()+"-"+monthStart+"-"+startDate.getDate() +" 00:00:00";
		$("#endDate").datebox('setValue',endDateStr);
		$("#startDate").datebox('setValue',startDateStr);
	},	
	setInitSearchHoursTime: function(){
		var endDate = new Date();
		var startDate = endDate;
		var sm = startDate.getMonth() + 1;
		var em = endDate.getMonth() + 1;
		var monthStart = sm < 10 ? "0" + sm : sm;
		var monthEnd = em < 10 ? "0" + em : em;
		var endDateStr = endDate.getFullYear()+"-"+monthEnd+"-"+endDate.getDate() + " 23:59:59";
		var startDateStr = startDate.getFullYear()+"-"+monthStart+"-"+startDate.getDate() +" 00:00:00";
		$("#endDate").datebox('setValue',endDateStr);
		$("#startDate").datebox('setValue',startDateStr);
	},	
	isNotNull: function(v){
		if(v && v.length > 0) return true;
		return false;
	},
	doAjax: function(params,url,successCallback,errorCallback){
		$.ajax({
			type:"post",
			url:url,
			data:params,
			dataType: "json",
			success:function (data) {
				if(successCallback) successCallback(data);
				else alert(data.message);
			},
			error:function(data){
				if(errorCallback) errorCallback(data);
				else alert("执行操作失败!");
			}
		});
	}
};

var webcallCommon = $.extend(webcallCommon2, webcallCommon);