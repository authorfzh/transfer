//标签json
var labelSearchJson=[
    {
        "id":"130",
        "nodes":[
            {
                "id":"17",
                "name":"datePicker",
                "parentId":"",
                "type":"4",
                "value":"2018-11-30;2018-11-30"
            },
            {
                "id":"145",
                "name":"money_interval",
                "parentId":"",
                "type":"8",
                "value":"1;1"
            },
            {
                "id":"142",
                "name":"time_interval",
                "parentId":"",
                "type":"8",
                "value":"1;111"
            }
        ],
        "text":"111"
    }
]
//普通搜索json
var generalSearchJson=[
    {
        "id":"4",
        "name":"SITE_ID",
        "option":[
            {
                "id":"5",
                "name":"site_id_option",
                "text":"已导入",
                "value":"1"
            },
            {
                "id":"6",
                "name":"site_id_option",
                "text":"未导入",
                "value":"2"
            },
            {
                "id":"7",
                "name":"site_id_option",
                "text":"未处理",
                "value":"3"
            }
        ],
        "state":"1",
        "text":"CRM状态",
        "type":"2"
    },
    {
        "id":"12",
        "name":"customerName",
        "state":"1",
        "text":"客户姓名",
        "type":"1"
    }
]

//高级搜索
var advancedSearchJson=[
    {
        "id":"17",
        "index":"0",
        "name":"datePicker",
        "state":"1",
        "text":"选择日期",
        "type":"4"
    },
    {
        "id":"30",
        "index":"0",
        "name":"searchSource",
        "state":"0",
        "text":"搜索来源",
        "type":"1"
    },
    {
        "id":"142",
        "index":"1",
        "name":"time_interval",
        "nodes":[
            {
                "id":"144",
                "name":"time_interval_end",
                "text":"结束",
                "type":"5"
            },
            {
                "id":"143",
                "name":"time_interval_begin",
                "text":"开始",
                "type":"5"
            }
        ],
        "state":"1",
        "text":"时间段",
        "type":"8"
    },
    {
        "id":"19",
        "index":"1",
        "name":"mobilePhone",
        "state":"0",
        "text":"手机",
        "type":"1"
    },
    {
        "id":"29",
        "index":"2",
        "name":"fitstVisit",
        "state":"1",
        "text":"最近访问",
        "type":"1"
    },
    {
        "id":"21",
        "index":"2",
        "name":"email",
        "state":"0",
        "text":"电子邮件",
        "type":"1"
    },
    {
        "id":"145",
        "index":"3",
        "name":"money_interval",
        "nodes":[
            {
                "id":"148",
                "name":"money_interval_end",
                "text":"金额结束",
                "type":"1"
            },
            {
                "id":"146",
                "name":"money_interval_begin",
                "text":"金额开始",
                "type":"1"
            }
        ],
        "state":"1",
        "text":"金额",
        "type":"8"
    },
    {
        "id":"27",
        "index":"3",
        "name":"companyWebsite",
        "state":"0",
        "text":"公司网址",
        "type":"1"
    },
    {
        "id":"149",
        "index":"4",
        "name":"address",
        "nodes":[
            {
                "id":"151",
                "name":"address_city",
                "text":"选择市",
                "url":"/Service/dictionary/queryForList"
            },
            {
                "id":"150",
                "name":"address_provice",
                "text":"选择省",
                "url":"/Service/dictionary/queryForList"
            }
        ],
        "state":"1",
        "text":"选择地区",
        "type":"7"
    },
    {
        "id":"28",
        "index":"4",
        "name":"s_tujing",
        "state":"0",
        "text":"途径",
        "type":"6",
        "url":"/Service/Customer/queryCrmSourceMap"
    },
    {
        "id":"22",
        "index":"5",
        "name":"remarks",
        "state":"1",
        "text":"备注",
        "type":"1"
    },
    {
        "id":"26",
        "index":"5",
        "name":"companyName",
        "state":"0",
        "text":"公司名称",
        "type":"1"
    },
    {
        "id":"38",
        "index":"6",
        "name":"s_createrId",
        "nodes":[
            {
                "id":"39",
                "name":"o_department",
                "text":"部门",
                "url":"/Service/Department/getCascade",
                "value":"1"
            },
            {
                "id":"40",
                "name":"o_customer",
                "text":"客服",
                "url":"/Service/Department/getCascadeCustomer",
                "value":"2"
            }
        ],
        "state":"1",
        "text":"选择创建者",
        "type":"7"
    },
    {
        "id":"12",
        "index":"6",
        "name":"customerName",
        "state":"0",
        "text":"客户姓名",
        "type":"1"
    },
    {
        "id":"20",
        "index":"7",
        "name":"qq",
        "state":"0",
        "text":"QQ",
        "type":"1"
    },
    {
        "id":"18",
        "index":"7",
        "name":"siteId",
        "state":"1",
        "text":"子站点",
        "type":"6",
        "url":"/Service/Customer/querySite"
    },
    {
        "id":"13",
        "index":"8",
        "name":"sex",
        "option":[
            {
                "id":"14",
                "name":"sex_id_option",
                "text":"男",
                "value":"1"
            },
            {
                "id":"15",
                "name":"sex_id_option",
                "text":"女",
                "value":"0"
            }
        ],
        "state":"0",
        "text":"性别",
        "type":"2"
    },
    {
        "id":"32",
        "index":"8",
        "name":"orderType",
        "option":[
            {
                "id":"33",
                "name":"orderType_option",
                "text":"有订单",
                "value":"1"
            },
            {
                "id":"34",
                "name":"orderType_option",
                "text":"无订单",
                "value":"2"
            }
        ],
        "state":"1",
        "text":"订单类型",
        "type":"2"
    },
    {
        "id":"31",
        "index":"9",
        "name":"searchWord",
        "state":"0",
        "text":"搜索词",
        "type":"1"
    },
    {
        "id":"23",
        "index":"10",
        "name":"s_chatType",
        "option":[
            {
                "id":"24",
                "name":"s_chatType_option",
                "text":"pc",
                "value":"pc"
            },
            {
                "id":"25",
                "name":"s_chatType_option",
                "text":"phone",
                "value":"phone"
            }
        ],
        "state":"0",
        "text":"设备类型",
        "type":"2"
    },
    {
        "id":"1001",
        "index":"20",
        "name":"s_visitorMessageNumber",
        "nodes":[
            {
                "id":"1002",
                "name":"s_visitorMessageNumberType",
                "text":"类型",
                "type":"2",
                "url":"/Service/Department/getCascade"
            },
            {
                "id":"1003",
                "name":"s_visitorMessageValue",
                "text":"值",
                "type":"1"
            }
        ],
        "state":"1",
        "text":"访客消息数",
        "type":"9"
    }
]

//用户配置json
var configSearchJson=[
    {
        "id":"92",
        "ischeck":"1",
        "nodes":[
            {
                "id":"865",
                "index":"0",
                "state":"1",
                "text":"选择日期"
            },
            {
                "id":"876",
                "index":"0",
                "state":"0",
                "text":"搜索来源"
            },
            {
                "id":"350",
                "index":"1",
                "state":"1",
                "text":"时间段"
            },
            {
                "id":"867",
                "index":"1",
                "state":"0",
                "text":"手机"
            },
            {
                "id":"875",
                "index":"2",
                "state":"1",
                "text":"最近访问"
            },
            {
                "id":"869",
                "index":"2",
                "state":"0",
                "text":"电子邮件"
            },
            {
                "id":"351",
                "index":"3",
                "state":"1",
                "text":"金额"
            },
            {
                "id":"873",
                "index":"3",
                "state":"0",
                "text":"公司网址"
            },
            {
                "id":"881",
                "index":"4",
                "state":"1",
                "text":"选择地区"
            },
            {
                "id":"874",
                "index":"4",
                "state":"0",
                "text":"途径"
            },
            {
                "id":"872",
                "index":"5",
                "state":"0",
                "text":"公司名称"
            },
            {
                "id":"870",
                "index":"5",
                "state":"1",
                "text":"备注"
            },
            {
                "id":"863",
                "index":"6",
                "state":"0",
                "text":"客户姓名"
            },
            {
                "id":"880",
                "index":"6",
                "state":"1",
                "text":"选择创建者"
            },
            {
                "id":"868",
                "index":"7",
                "state":"0",
                "text":"QQ"
            },
            {
                "id":"866",
                "index":"7",
                "state":"1",
                "text":"子站点"
            },
            {
                "id":"878",
                "index":"8",
                "state":"1",
                "text":"订单类型"
            },
            {
                "id":"864",
                "index":"8",
                "state":"0",
                "text":"性别"
            },
            {
                "id":"877",
                "index":"9",
                "state":"0",
                "text":"搜索词"
            },
            {
                "id":"871",
                "index":"10",
                "state":"0",
                "text":"设备类型"
            },
            {
                "id":"32",
                "index":"20",
                "state":"1",
                "text":"访客消息数"
            }
        ],
        "text":"系统默认配置2"
    }
]