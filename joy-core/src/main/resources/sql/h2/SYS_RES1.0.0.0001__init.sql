/* 注册数据源 */
insert into `t_sys_data_src`(`id`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`active`,`charset`,`db_alias`,`db_name`,`db_type`,`ip_address`,`jndi_name`,`max_conn_count`,`min_conn_count`,`name`,`parameter`,`password`,`server_port`,`username`,`db_url`) 
values ('JoyDs',1,null,null,null,null,null,null,0,null,null,null,1,null,null,null,null,null,'JDBC/JOY',null,null,'JOY平台数据源',null,'tangwl',null,'root','jdbc:mysql://localhost:3306/joy');

/* 注册代码表 */
insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`active_field`,`code_field`,`deleted_field`,`grouping_comment_field`,`grouping_field`,`order_field`,`parent_field`,`pinyin_field`,`segment_rule`,`table_comment`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODES',1,1,null,null,null,null,null,null,0,null,null,null,null,'ACTIVE','CODE','DELETED','GROUP_COMMENT','GROUP_ID','ORDINAL','PARENT_CODE','PIN_YIN','SEGMENT_RULE','总代码表','t_sys_codes','TRANS','JoyDs');

/* 布尔代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_YES_NOT_0','1','1',null,null,null,null,null,null,'0',null,null,null,null,'0','布尔','yes_not','2',null,'fou',null,'否','not');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_YES_NOT_1','1','1',null,null,null,null,null,null,'0',null,null,null,null,'1','布尔','yes_not','1',null,'shi',null,'是','yes');

/* 性别代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_SEX_0','1','1',null,null,null,null,null,null,'0',null,null,null,null,'0','性别','sex','9',null,'wei zhi de xing bei',null,'未知的性别','unknown');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_SEX_1','1','1',null,null,null,null,null,null,'0',null,null,null,null,'1','性别','sex','1',null,'nan',null,'男','male');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_SEX_2','1','1',null,null,null,null,null,null,'0',null,null,null,null,'2','性别','sex','2',null,'nv',null,'女','female');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_SEX_9','1','1',null,null,null,null,null,null,'0',null,null,null,null,'9','性别','sex','10',null,'wei shuo ming de xing bei',null,'未说明的性别','untold');

/* 周期类型代码 */
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_1','1','1',null,null,null,null,null,null,'0',null,null,null,null,'1','周期类型','time_unit',null,null,'nian',null,'年','year');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_2','1','1',null,null,null,null,null,null,'0',null,null,null,null,'2','周期类型','time_unit',null,null,'yue',null,'月','month');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_3','1','1',null,null,null,null,null,null,'0',null,null,null,null,'3','周期类型','time_unit',null,null,'zhou',null,'周','week');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_4','1','1',null,null,null,null,null,null,'0',null,null,null,null,'4','周期类型','time_unit',null,null,'ri',null,'日','day');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_5','1','1',null,null,null,null,null,null,'0',null,null,null,null,'5','周期类型','time_unit',null,null,'xiao shi',null,'小时','hour');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_6','1','1',null,null,null,null,null,null,'0',null,null,null,null,'6','周期类型','time_unit',null,null,'fen zhong',null,'分钟','minute');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_7','1','1',null,null,null,null,null,null,'0',null,null,null,null,'7','周期类型','time_unit',null,null,'miao',null,'秒','second');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_8','1','1',null,null,null,null,null,null,'0',null,null,null,null,'8','周期类型','time_unit',null,null,'hao miao',null,'毫秒','millisecond');
insert into `t_sys_codes`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`remark`,`update_dept`,`update_time`,`update_user`,`code`,`group_comment`,`group_id`,`ordinal`,`parent_code`,`pin_yin`,`segment_rule`,`trans`,`trnas_en_us`) 
values ('JOY_TIME_UNIT_9','1','1',null,null,null,null,null,null,'0',null,null,null,null,'9','周期类型','time_unit',null,null,'wei miao',null,'微秒','microsecond');


/* 初始化系统参数 */
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('1','','应用名称','APP_NAME','JOY企业级通用快速开发平台',1,1,'9',null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('2',null,'应用名称简称','APP_NAME_ABBR','JOY',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('3',null,'应用版本','APP_VERSION','V1.0.0',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('4',null,'版权终止年份','APP_COPY_RIGHT_END_YEAR','2013',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('5',null,'版权起始年份','APP_COPY_RIGHT_START_YEAR','2011',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('6',null,'版权所有人','APP_COPY_RIGHT_AUTHOR','Kevice',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`remark`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('7',null,'应用类名包前缀','APP_CLASS_PREFIX','org.joy',1,1,null,null,null,null,null,null,0,null,null,null,0);


/* 初始化系统菜单 */
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY001',1,0,null,'1','','系统管理',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY010',0,0,null,'2','JOY008','用户管理',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY011',0,0,null,'3','JOY008','角色管理',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY012',0,0,null,'4','JOY008','组管理',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY013',0,0,null,'3','JOY001','个人信息维护',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY014',0,0,null,'1','JOY013','密码修改',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY015',0,0,null,'3',null,'帮助',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY016',0,0,null,'2',null,'系统监控',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY017',1,0,null,'1','JOY016','日志监控',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY018',0,0,null,'1','JOY017','脚本执行日志','/sqlScriptInstallLog/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY019',0,0,null,'2','JOY024','用户登陆日志','/userLoginLog/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY002',1,0,null,'1','JOY001','系统资源',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY020',0,0,null,'3','JOY024','用户操作日志','/userOperationLog/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY021',0,0,null,'2','JOY016','性能监控',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY022',0,0,null,'1','JOY021','系统资源监控','/sysResMonitor/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY023',0,0,null,'2','JOY021','SQL执行监控','/sqlExecMonitor/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY024',0,0,null,'3','JOY016','安全监控',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY003',0,0,null,'1','JOY002','数据源','/sysDataSrc/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY004',0,0,null,'2','JOY002','关系数据库对象','/mdRdbObj/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY005',0,0,null,'3','JOY002','系统参数','/sysParam/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY006',0,0,null,'4','JOY002','代码表','/sysCodeTable/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY007',0,0,null,'5','JOY002','任务计划','/qrtzJobPlan/list');
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY008',0,0,null,'2','JOY001','权限管理',null);
insert into `t_sys_menu`(`id`,`active`,`deleted`,`icon`,`order_num`,`parent_id`,`text`,`url`) values ('JOY009',0,0,null,'1','JOY008','机构管理',null);