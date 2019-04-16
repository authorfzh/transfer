var EASYLIAO = {
    ctxPath: "",
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    confirm: function (tip, ensure) {//询问框
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    log: function (info) {
        console.log(info);
    },
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    info: function (info) {
        EASYLIAO.alert(info, 0);
    },
    success: function (info) {
        EASYLIAO.alert(info, 1);
    },
    error: function (info) {
        EASYLIAO.alert(info, 2);
    },
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    showInputTree: function (inputId, inputTreeContentId, leftOffset, rightOffset) {
        var onBodyDown = function (event) {
            if (!(event.target.id == "menuBtn" || event.target.id == inputTreeContentId || $(event.target).parents("#" + inputTreeContentId).length > 0)) {
                $("#" + inputTreeContentId).fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
            }
        };

        if(leftOffset == undefined && rightOffset == undefined){
            var inputDiv = $("#" + inputId);
            var inputDivOffset = $("#" + inputId).offset();
            $("#" + inputTreeContentId).css({
                left: inputDivOffset.left + "px",
                top: inputDivOffset.top + inputDiv.outerHeight() + "px"
            }).slideDown("fast");
        }else{
            $("#" + inputTreeContentId).css({
                left: leftOffset + "px",
                top: rightOffset + "px"
            }).slideDown("fast");
        }

        $("body").bind("mousedown", onBodyDown);
    },
    baseAjax: function (url, tip) {
        var ajax = new $ax(EASYLIAO.ctxPath + url, function (data) {
            EASYLIAO.success(tip + "成功!");
        }, function (data) {
            EASYLIAO.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    changeAjax: function (url) {
        return EASYLIAO.baseAjax(url, "修改");
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    },
    sessionTimeoutRegistry: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
           // contentType: "application/xml;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
            	
            	if (XMLHttpRequest.getResponseHeader )
            		{
		                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
		                if (sessionstatus == "timeout") {
		                    //如果超时就处理 ，指定要跳转的页面
		                    window.location = EASYLIAO.ctxPath + "/global/sessionError";
		                }
            		}
            }
        });
    },
    /*
     * 文本框必填校验
     */
    checkRequired:function(checkedValue){
    	if(""==checkedValue || null==checkedValue){
    		return false;
    	}
    	return true;
    },
    /*
     * 特殊必填校验
     * 参数1：要验证的值
     * 参数2：提示信息
     */
    checkRequired2:function(checkedValue,prompt){
    	if(""==checkedValue || null==checkedValue){
    		layer.alert(prompt);
    		return false;
    	}
    	return true;
    },
    /*
     * 时间格式转换,转成yyyy-MM-dd的格式
     */
    timeFormatConversion:function(time){
    	var time = new Date(time);
        var mytime = time.getFullYear();
        if((time.getMonth() + 1)<10){
        	mytime+='-0' + (time.getMonth() + 1);
        }else{
        	mytime+='-' + (time.getMonth() + 1);
        }
        if(time.getDate()<10){
        	mytime+='-0' + time.getDate();
        }else{
        	mytime+='-' + time.getDate();
        }
        return mytime;
    }
};