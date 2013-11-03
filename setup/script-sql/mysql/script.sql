
CREATE TABLE `code_sex` (
  `CODE` varchar(32) NOT NULL,
  `TRANS` varchar(45) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `code_sex`(`CODE`,`TRANS`) values ('0','δ֪���Ա�');
insert into `code_sex`(`CODE`,`TRANS`) values ('1','��');
insert into `code_sex`(`CODE`,`TRANS`) values ('2','Ů');
insert into `code_sex`(`CODE`,`TRANS`) values ('9','δ˵�����Ա�');

insert into `t_sys_data_src`(`id`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`active`,`charset`,`db_alias`,`db_name`,`db_type`,`ip_address`,`jndi_name`,`max_conn_count`,`min_conn_count`,`name`,`parameter`,`password`,`server_port`,`username`,`db_url`) 
values ('JoyDs',1,null,null,null,null,null,null,0,null,null,null,1,null,null,null,null,null,'JDBC/JOY',null,null,null,null,'tangwl',null,'root','jdbc:mysql://localhost:3306/joy');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_SEX',1,0,null,null,null,null,null,null,0,null,null,null,'�Ա����','CODE',null,'CODE_SEX','TRANS','JoyDs');


insert into `t_erbac_user`(`id`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`user_account`,`description`,`last_login_time`,`login_count`,`user_name`,`password`,`sex_code`,`stauts_code`,`active`) values ('kevice_id',0,null,null,null,null,null,null,0,null,null,null,'kevice',null,null,null,'������','1e6cc81db305cf52c0533b2692211890','1','01',1);


insert into `t_qrtz_job_plan`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cron_exp`,`effect_time`,`expire_time`,`last_fire_time`,`name`,`next_fire_time`,`remarks`,`run_state`) values ('PLAN1',0,1,null,null,null,null,null,null,0,null,null,null,'','20130912090807','20140912090807','20131012090807','���Լƻ�1','20131112090807','���Լƻ�1','1');



insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('1',1,null,'1','','ϵͳ����',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('10',0,null,'2','8','�û�����',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('11',0,null,'3','8','��ɫ����',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('12',0,null,'4','8','�����',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('13',0,null,'3','1','������Ϣά��',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('14',0,null,'1','13','�����޸�',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('15',0,null,'2',null,'����',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('2',1,null,'1','1','ϵͳ��Դ',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('3',0,null,'1','2','����Դ','/sysDataSrc/list',0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('4',0,null,'2','2','���ݶ���',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('5',0,null,'3','2','ϵͳ����','/sysParam/list',0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('6',0,null,'4','2','�����','/sysCodeTable/list',0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('7',0,null,'5','2','����ƻ�','/qrtzJobPlan/list',0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('8',0,null,'2','1','Ȩ�޹���',null,0);
insert into `t_sys_menu`(`id`,`active`,`icon`,`order_num`,`parent_id`,`TEXT`,`url`,`deleted`) values ('9',0,null,'1','8','��������',null,0);


insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('1','','Ӧ������','APP_NAME','JOY��ҵ��ͨ�ÿ��ٿ���ƽ̨',1,1,'9',null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('10',null,null,'test10',null,1,1,'1',null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('11',null,null,'test11',null,1,1,'2',null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('12',null,null,'test12',null,1,1,'0',null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('13',null,null,'test13',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('14',null,null,'test14',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('15',null,null,'test15',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('16',null,null,'test16',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('17',null,null,'test17',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('18',null,null,'test18',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('19',null,null,'test19',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('2',null,'Ӧ�����Ƽ��','APP_NAME_ABBR','JOY',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('20',null,null,'test20',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('21',null,null,'test21',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('22',null,null,'test1',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('23',null,null,'test2',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('24',null,null,'test3',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('25',null,null,'test4',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('26',null,null,'test5',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('27',null,null,'test6',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('28',null,null,'test7',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('29',null,null,'test8',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('3',null,'Ӧ�ð汾','APP_VERSION','V1.0.0',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('4',null,'��Ȩ��ֹ���','APP_COPY_RIGHT_END_YEAR','2013',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('5',null,'��Ȩ��ʼ���','APP_COPY_RIGHT_START_YEAR','2011',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('6',null,'��Ȩ������','APP_COPY_RIGHT_AUTHOR','Kevice',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('7',null,'Ӧ��������ǰ׺','APP_CLASS_PREFIX','com.kvc.joy',1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('8',null,null,'test8',null,1,1,null,null,null,null,null,null,0,null,null,null,0);
insert into `t_sys_param`(`id`,`default_value`,`description`,`param_name`,`param_value`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`encrypt`) values ('9',null,null,'test9',null,1,1,null,null,null,null,null,null,0,null,null,null,0);





