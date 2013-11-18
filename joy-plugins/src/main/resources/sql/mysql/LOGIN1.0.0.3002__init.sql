/* 登陆失败原因代码 */
CREATE TABLE `code_login_fail_reason` (
  `CODE` varchar(2) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆失败原因代码';

insert into `code_login_fail_reason`(`CODE`,`TRANS`) values ('11','密码错误');
insert into `code_login_fail_reason`(`CODE`,`TRANS`) values ('12','验证码错误');
insert into `code_login_fail_reason`(`CODE`,`TRANS`) values ('21','帐号被锁定');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_LOGIN_FAIL_REASON',1,1,null,null,null,null,null,null,0,null,null,null,'登陆失败原因代码','CODE',null,'CODE_LOGIN_FAIL_REASON','TRANS','JoyDs');


/* 登出系统方式代码 */
CREATE TABLE `code_logout_method` (
  `CODE` varchar(2) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登出系统方式代码';

insert into `code_logout_method`(`CODE`,`TRANS`) values ('11','点击退出按钮');
insert into `code_logout_method`(`CODE`,`TRANS`) values ('12','直接关闭浏览器');
insert into `code_logout_method`(`CODE`,`TRANS`) values ('21','超时');
insert into `code_logout_method`(`CODE`,`TRANS`) values ('99','其它');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_LOGOUT_METHOD',1,1,null,null,null,null,null,null,0,null,null,null,'登出系统方式代码','CODE',null,'CODE_LOGOUT_METHOD','TRANS','JoyDs');


