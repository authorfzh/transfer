/**模拟数据*/
/**二级菜单的json数据*/
var admin_json = [
			  {
				  "id":"1",
				  "text": "管理员菜单",
				  "type":"0",
				  nodes: [
					  {"id":"1","parentId":"1","text": "分配规则配置","url":"/rpm/jsp/assign/admin/counselor.jsp"},
					  {"id":"2","parentId":"1","text": "校区配置","url":""},
					  {"id":"3","parentId":"1","text": "项目配置","url":""},
					  {"id":"4","parentId":"1","text": "业务组配置","url":""},
					  {"id":"5","parentId":"1","text": "销售类别配置","url":""},
					  {"id":"6","parentId":"1","text": "销售人员配置","url":""},
					  {"id":"7","parentId":"1","text": "排班班次","url":""},
					  {"id":"8","parentId":"1","text": "排班设置","url":""},
					  {"id":"9","parentId":"1","text": "退回类型设置","url":""},
					  {"id":"10","parentId":"1","text": "名片分配","url":""}
				  ]
			  },
			   {
				  "id":"2",
			  	  "text": "销售菜单",
			  	  "type":"0",
				  nodes: [
					  {"id":"1","parentId":"2","text": "个人排班","url":""},
					  {"id":"1","parentId":"2","text": "名片处理","url":""},
					  {"id":"1","parentId":"2","text": "销售地址","url":""}
				  ]
			  },
			   {
				  "id":"3",
				  "text": "报表菜单",
				  "type":"0",
				  nodes: [
					  {"id":"1","parentId":"3","text": "名片分配监控","url":""},
					  {"id":"1","parentId":"3","text": "咨询师考核","url":""},
					  {"id":"1","parentId":"3","text": "我录入的名片","url":""}
				  ]
			  }
		 ];  
/**销售的json*/
var customer_json = [
                     {"id":"1","parentId":"","text": "线索","url":""},
                     {"id":"1","parentId":"","text": "客户","url":"/rpm/jsp/sell/customer_list.jsp"},
                     {"id":"1","parentId":"","text": "订单","url":""},
                     {"id":"1","parentId":"","text": "数据管理","url":""},
                     {"id":"1","parentId":"","text": "无效数据","url":""}
                     ];  

/**首页菜单*/
var menuJson = [
  			  {
  			  	  "id":"1",
  				  "text": "首页",
  				  "type":"0",
  				  "icon":"fa fa-home",
  				  "url":"/rpm/jsp/sell/customer_list.jsp"
  			  },{
  			  	  "id":"2",
  				  "text": "分配",
  				  "type":"1",
  				  "icon":"fa fa-check-square-o",
  				  "treeJson":admin_json
  			  },{
  			  	  "id":"3",
  				  "text": "销售",
  				  "type":"1",
  				  "icon":"fa fa-check-square-o",
  				  "treeJson": customer_json
  			  },{
  			  	  "id":"4",
  				  "text": "公海",
  				  "type":"0",
  				  "icon":"fa fa-file-text-o",
  			  },{
  			  	  "id":"5",
  				  "text": "数据",
  				  "type":"1",
  				  "icon":"fa fa-file-text-o",
  			  },{
  			  	  "id":"6",
  				  "text": "系统",
  				  "type":"1",
  				  "icon":"fa fa-file-text-o",
  			  },{
  			  	  "id":"7",
  				  "text": "日程",
  				  "type":"1",
  				  "icon":"fa fa-file-text-o",
  			  }
  			  
  	   ];

/**头部系统栏*/
var operate_json = [
                    {
                    	  "id":"1",
	      				  "text": "管理",
	      				  "type":"0",
	  					  nodes: [
	  						  {"id":"1","parentId":"1","text": "管理一","url":"/rpm/jsp/assign/admin/counselor.jsp"},
	  						  {"id":"1","parentId":"1","text": "管理二","url":"/rpm/jsp/assign/admin/counselor.jsp"},
	  						  {"id":"1","parentId":"1","text": "管理二","url":"/rpm/jsp/assign/admin/counselor.jsp"}
	  					  ]
                    },{
                    	  "id":"2",	
        				  "text": "系统",
        				  "type":"0",
      					   nodes: [
      						  {"id":"1","parentId":"1","text": "系统一","url":"/rpm/jsp/assign/admin/counselor.jsp"},
      						  {"id":"1","parentId":"1","text": "系统二","url":"/rpm/jsp/assign/admin/counselor.jsp"},
      						  {"id":"1","parentId":"1","text": "系统二","url":"/rpm/jsp/assign/admin/counselor.jsp"}
      					   ]
                     },{
                    	   "id":"3",
       				  	   "text": "消息",
       				  	   "type":"1",
       				  	   "messageNum":"8",
	  					    nodes: [
	  						  {"id":"1","parentId":"1","content": "消息一","url":"/rpm/jsp/assign/admin/counselor.jsp"},
	  						  {"id":"1","parentId":"1","content": "消息二","url":"/rpm/jsp/assign/admin/counselor.jsp"},
	  						  {"id":"1","parentId":"1","content": "消息二","url":"/rpm/jsp/assign/admin/counselor.jsp"}
	  					    ]
                     }
                  ];  