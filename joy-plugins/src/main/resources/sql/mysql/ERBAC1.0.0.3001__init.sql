/* 权限资源类型代码 */
CREATE TABLE `code_auth_res_type` (
  `CODE` varchar(2) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源类型代码';

insert into `code_auth_res_type`(`CODE`,`TRANS`) values ('01','URL');
insert into `code_auth_res_type`(`CODE`,`TRANS`) values ('02','类的方法');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_AUTH_RES_TYPE',1,1,null,null,null,null,null,null,0,null,null,null,'权限资源类型代码','CODE',null,'CODE_AUTH_RES_TYPE','TRANS','JoyDs');


/* 权限类型代码 */
CREATE TABLE `code_authority_type` (
  `CODE` varchar(1) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限类型代码';

insert into `code_authority_type`(`CODE`,`TRANS`) values ('1','可访问');
insert into `code_authority_type`(`CODE`,`TRANS`) values ('2','可授权');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_AUTHORITY_TYPE',1,1,null,null,null,null,null,null,0,null,null,null,'权限类型代码','CODE',null,'CODE_AUTHORITY_TYPE','TRANS','JoyDs');


/* 用户帐号状态代码 */
CREATE TABLE `code_user_status` (
  `CODE` varchar(2) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户帐号状态代码';

insert into `code_user_status`(`CODE`,`TRANS`) values ('00','禁用');
insert into `code_user_status`(`CODE`,`TRANS`) values ('10','正常');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_USER_STATUS',1,1,null,null,null,null,null,null,0,null,null,null,'用户帐号状态代码','CODE',null,'CODE_USER_STATUS','TRANS','JoyDs');

/* 初始化用户 */
insert into `t_erbac_user`(`id`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`user_account`,`description`,`last_login_time`,`login_count`,`user_name`,`password`,`sex_code`,`stauts_code`,`active`) values ('kevice_id',0,null,null,null,null,null,null,0,null,null,null,'kevice',null,null,null,'唐玮琳','1e6cc81db305cf52c0533b2692211890','1','01',1);




