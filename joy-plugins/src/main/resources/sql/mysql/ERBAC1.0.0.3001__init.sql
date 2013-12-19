/* 权限资源类型代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_AUTH_RES_TYPE_01','1','1',null,null,null,null,null,null,'0',null,null,null,null,'01','权限资源类型','auth_res_type',null,null,null,null,'URL','URL');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_AUTH_RES_TYPE_02','1','1',null,null,null,null,null,null,'0',null,null,null,null,'01','权限资源类型','auth_res_type',null,null,'lei de fang fa',null,'类的方法','URL');

/* 权限类型代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_AUTHORITY_TYPE_1','1','1',null,null,null,null,null,null,'0',null,null,null,null,'1','权限类型','authority_type',null,null,'ke fang wen',null,'可访问','accessible');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_AUTHORITY_TYPE_2','1','1',null,null,null,null,null,null,'0',null,null,null,null,'2','权限类型','authority_type',null,null,'ke shou quan',null,'可授权','authorizable');


/* 用户帐号状态代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_USER_STATUS_00','1','1',null,null,null,null,null,null,'0',null,null,null,null,'00','用户帐号状态','user_status',null,null,'jin yong',null,'禁用',' forbidden');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`description`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_CODE_USER_STATUS_10','1','1',null,null,null,null,null,null,'0',null,null,null,null,'10','用户帐号状态','user_status',null,null,'zheng chang',null,'正常',' normal');

/* 初始化用户 */
insert into `t_erbac_user`(`id`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`user_account`,`description`,`last_login_time`,`login_count`,`user_name`,`password`,`sex_code`,`stauts_code`,`active`,`online`) 
values ('kevice_id',0,null,null,null,null,null,null,0,null,null,null,'kevice',null,null,null,'唐玮琳','1e6cc81db305cf52c0533b2692211890','1','01',1,0);
