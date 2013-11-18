/* 任务运行状态代码 */
CREATE TABLE `code_job_run_state` (
  `CODE` varchar(2) NOT NULL COMMENT '代码',
  `TRANS` varchar(32) NOT NULL COMMENT '译文',
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务运行状态代码';

insert into `code_job_run_state`(`CODE`,`TRANS`) values ('00','未启动');
insert into `code_job_run_state`(`CODE`,`TRANS`) values ('11','运行中');
insert into `code_job_run_state`(`CODE`,`TRANS`) values ('21','异常终止');
insert into `code_job_run_state`(`CODE`,`TRANS`) values ('22','用户挂起');
insert into `code_job_run_state`(`CODE`,`TRANS`) values ('80','已完成');

insert into `t_sys_code_table`(`id`,`active`,`built_in`,`create_dept`,`create_time`,`create_user`,`delete_dept`,`delete_time`,`delete_user`,`deleted`,`update_dept`,`update_time`,`update_user`,`cn_table_name`,`code_field`,`parent_field`,`table_name`,`trans_field`,`data_src_id`) 
values ('JOY_CODE_JOB_RUN_STATE',1,1,null,null,null,null,null,null,0,null,null,null,'登陆失败原因代码','CODE',null,'CODE_JOB_RUN_STATE','TRANS','JoyDs');



