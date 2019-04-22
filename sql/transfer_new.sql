CREATE TABLE tf_company (
  company_id int(11) NOT NULL AUTO_INCREMENT,
  company_name varchar(255),
  PRIMARY KEY (company_id)
) ENGINE=InnoDB CHARSET=utf8;

ALTER TABLE `tf_company`
MODIFY COLUMN `company_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '公司ID' FIRST ,
MODIFY COLUMN `company_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称' AFTER `company_id`;


CREATE TABLE tf_user (
  user_id varchar(255) not null comment '登录人ID',
  user_name varchar(255) comment '登录人名称',
	user_pass VARCHAR(100) comment '密码',
	company_id int,
	create_time datetime,	
  PRIMARY KEY (user_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE tf_sale_user (
  sale_id varchar(255) not null comment '销售人ID',
  sale_name varchar(255) comment '销售人名称',
	third_account VARCHAR(500) comment '第三方账号',
	group_id int comment '分组ID',
	company_id int comment '公司ID',
	create_time datetime comment '创建时间',	
  PRIMARY KEY (sale_id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_group (
  group_id int(11) NOT NULL AUTO_INCREMENT,
  group_name varchar(255) comment '分组名称',
	desp VARCHAR(500) comment '描述',
	project_id int comment '项目ID',
	company_id int comment '公司ID',
	create_time datetime comment '创建时间',	
  PRIMARY KEY (group_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE tf_project (
  id int(11) NOT NULL AUTO_INCREMENT,
  project_name varchar(255) comment '项目名称',
	project_desp VARCHAR(500) comment '项目描述',
	company_id int comment '公司ID',
	create_time datetime comment '创建时间',	
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_group_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  sale_id varchar(255) comment '销售人ID',
	group_id VARCHAR(500) comment '分组ID',
	company_id int comment '公司ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_channel (
  channel_id int(11) NOT NULL AUTO_INCREMENT,
  channel_name varchar(255) comment '渠道名称',
	company_id int comment '公司ID',
  PRIMARY KEY (channel_id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_data_source (
  id int(11) NOT NULL AUTO_INCREMENT,
  data_source varchar(255) comment '数据来源',
	config varchar(500) comment '配置',
	channel_id int comment '渠道ID',
	project_id int comment '项目ID',
	state int comment '状态',
	company_id int comment '公司ID',
	create_time datetime comment '创建时间',
	update_time datetime comment '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_push_interface (
  id int(11) NOT NULL AUTO_INCREMENT,
  project_path varchar(500) comment '项目路径',
	channel_id int comment '渠道ID',
	project_id int comment '项目ID',
	state int comment '状态',
	company_id int comment '公司ID',
	create_time datetime comment '创建时间',
	update_time datetime comment '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_allocation_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  type_name varchar(500) comment '分配类型',
	desp varchar(500) comment '描述',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_card_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  allocation_name varchar(255) comment '分配人员姓名',
	sex int comment '性别',
	mobile varchar(255) comment '手机号',
	msn varchar(255) comment '微信',
	age int comment '年龄',
	allocation_id int comment '分配类型',
	allocation_time datetime comment '分配时间',
	sale_id int comment '分配接收人',
	channel_id int comment '渠道ID',
	group_id int comment '分组ID',
	project_id int comment '项目ID',
	state int comment '状态',
	company_id int comment '公司ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_menu (
  id varchar(255) NOT NULL,
  menu_name varchar(500) comment '菜单名称',
	menu_url varchar(1000) comment '链接地址',
	state int comment '状态',
	parent_id varchar(500) comment '父菜单id',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('2a9a90ad371b470a9631abc6351bf728', '用户管理',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('c8d0f555c8544f59aaa7d36978c00e30', '销售人员管理',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('a8bd7504e9c2407f88785790fb66587b', '项目管理',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('d2230370a9874b318903bd32602c2e48', '渠道管理',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('35c7539c9d964ec8bbd7d58a62cb7e6b', '分组管理',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('8d6285c36a3743b4ab51ea7723dae1ad', '日志查询',"/",1,null);

