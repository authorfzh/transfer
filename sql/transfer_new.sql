CREATE TABLE tf_company (
  company_id int(11) NOT NULL AUTO_INCREMENT,
  company_name varchar(255),
  PRIMARY KEY (company_id)
) ENGINE=InnoDB CHARSET=utf8;

ALTER TABLE `tf_company`
MODIFY COLUMN `company_id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '��˾ID' FIRST ,
MODIFY COLUMN `company_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '��˾����' AFTER `company_id`;


CREATE TABLE tf_user (
  user_id varchar(255) not null comment '��¼��ID',
  user_name varchar(255) comment '��¼������',
	user_pass VARCHAR(100) comment '����',
	company_id int,
	create_time datetime,	
  PRIMARY KEY (user_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE tf_sale_user (
  sale_id varchar(255) not null comment '������ID',
  sale_name varchar(255) comment '����������',
	third_account VARCHAR(500) comment '�������˺�',
	group_id int comment '����ID',
	company_id int comment '��˾ID',
	create_time datetime comment '����ʱ��',	
  PRIMARY KEY (sale_id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_group (
  group_id int(11) NOT NULL AUTO_INCREMENT,
  group_name varchar(255) comment '��������',
	desp VARCHAR(500) comment '����',
	project_id int comment '��ĿID',
	company_id int comment '��˾ID',
	create_time datetime comment '����ʱ��',	
  PRIMARY KEY (group_id)
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE tf_project (
  id int(11) NOT NULL AUTO_INCREMENT,
  project_name varchar(255) comment '��Ŀ����',
	project_desp VARCHAR(500) comment '��Ŀ����',
	company_id int comment '��˾ID',
	create_time datetime comment '����ʱ��',	
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_group_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  sale_id varchar(255) comment '������ID',
	group_id VARCHAR(500) comment '����ID',
	company_id int comment '��˾ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_channel (
  channel_id int(11) NOT NULL AUTO_INCREMENT,
  channel_name varchar(255) comment '��������',
	company_id int comment '��˾ID',
  PRIMARY KEY (channel_id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_data_source (
  id int(11) NOT NULL AUTO_INCREMENT,
  data_source varchar(255) comment '������Դ',
	config varchar(500) comment '����',
	channel_id int comment '����ID',
	project_id int comment '��ĿID',
	state int comment '״̬',
	company_id int comment '��˾ID',
	create_time datetime comment '����ʱ��',
	update_time datetime comment '����ʱ��',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_push_interface (
  id int(11) NOT NULL AUTO_INCREMENT,
  project_path varchar(500) comment '��Ŀ·��',
	channel_id int comment '����ID',
	project_id int comment '��ĿID',
	state int comment '״̬',
	company_id int comment '��˾ID',
	create_time datetime comment '����ʱ��',
	update_time datetime comment '����ʱ��',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_allocation_type (
  id int(11) NOT NULL AUTO_INCREMENT,
  type_name varchar(500) comment '��������',
	desp varchar(500) comment '����',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_card_log (
  id int(11) NOT NULL AUTO_INCREMENT,
  allocation_name varchar(255) comment '������Ա����',
	sex int comment '�Ա�',
	mobile varchar(255) comment '�ֻ���',
	msn varchar(255) comment '΢��',
	age int comment '����',
	allocation_id int comment '��������',
	allocation_time datetime comment '����ʱ��',
	sale_id int comment '���������',
	channel_id int comment '����ID',
	group_id int comment '����ID',
	project_id int comment '��ĿID',
	state int comment '״̬',
	company_id int comment '��˾ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tf_menu (
  id varchar(255) NOT NULL,
  menu_name varchar(500) comment '�˵�����',
	menu_url varchar(1000) comment '���ӵ�ַ',
	state int comment '״̬',
	parent_id varchar(500) comment '���˵�id',
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=utf8;

insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('2a9a90ad371b470a9631abc6351bf728', '�û�����',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('c8d0f555c8544f59aaa7d36978c00e30', '������Ա����',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('a8bd7504e9c2407f88785790fb66587b', '��Ŀ����',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('d2230370a9874b318903bd32602c2e48', '��������',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('35c7539c9d964ec8bbd7d58a62cb7e6b', '�������',"/",1,null);
insert into tf_menu(id, menu_name, menu_url, state, parent_id) values ('8d6285c36a3743b4ab51ea7723dae1ad', '��־��ѯ',"/",1,null);

