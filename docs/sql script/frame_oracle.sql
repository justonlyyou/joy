-- 创建quartz包依赖的表 by 唐玮琳 10:45 2011-11-8
CREATE TABLE t_qrtz_job_details
  (
    JOB_NAME  VARCHAR2(200) NOT NULL,
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    JOB_CLASS_NAME   VARCHAR2(250) NOT NULL, 
    IS_DURABLE VARCHAR2(1) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    IS_STATEFUL VARCHAR2(1) NOT NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NOT NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (JOB_NAME,JOB_GROUP)
);
CREATE TABLE t_qrtz_job_listeners
  (
    JOB_NAME  VARCHAR2(200) NOT NULL, 
    JOB_GROUP VARCHAR2(200) NOT NULL,
    JOB_LISTENER VARCHAR2(200) NOT NULL,
    PRIMARY KEY (JOB_NAME,JOB_GROUP,JOB_LISTENER),
    FOREIGN KEY (JOB_NAME,JOB_GROUP) 
	REFERENCES t_QRTZ_JOB_DETAILS(JOB_NAME,JOB_GROUP)
);
CREATE TABLE t_qrtz_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL, 
    JOB_GROUP VARCHAR2(200) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    NEXT_FIRE_TIME NUMBER(13) NULL,
    PREV_FIRE_TIME NUMBER(13) NULL,
    PRIORITY NUMBER(13) NULL,
    TRIGGER_STATE VARCHAR2(16) NOT NULL,
    TRIGGER_TYPE VARCHAR2(8) NOT NULL,
    START_TIME NUMBER(13) NOT NULL,
    END_TIME NUMBER(13) NULL,
    CALENDAR_NAME VARCHAR2(200) NULL,
    MISFIRE_INSTR NUMBER(2) NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (JOB_NAME,JOB_GROUP) 
	REFERENCES t_QRTZ_JOB_DETAILS(JOB_NAME,JOB_GROUP) 
);
CREATE TABLE t_qrtz_simple_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    REPEAT_COUNT NUMBER(7) NOT NULL,
    REPEAT_INTERVAL NUMBER(12) NOT NULL,
    TIMES_TRIGGERED NUMBER(10) NOT NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
	REFERENCES t_QRTZ_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE t_qrtz_cron_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    CRON_EXPRESSION VARCHAR2(120) NOT NULL,
    TIME_ZONE_ID VARCHAR2(80),
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
	REFERENCES t_QRTZ_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE t_qrtz_blob_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    BLOB_DATA BLOB NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
        REFERENCES t_QRTZ_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE t_qrtz_trigger_listeners
  (
    TRIGGER_NAME  VARCHAR2(200) NOT NULL, 
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    TRIGGER_LISTENER VARCHAR2(200) NOT NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_LISTENER),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
	REFERENCES t_QRTZ_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE t_qrtz_calendars
  (
    CALENDAR_NAME  VARCHAR2(200) NOT NULL, 
    CALENDAR BLOB NOT NULL,
    PRIMARY KEY (CALENDAR_NAME)
);
CREATE TABLE t_qrtz_paused_trigger_grps
  (
    TRIGGER_GROUP  VARCHAR2(200) NOT NULL, 
    PRIMARY KEY (TRIGGER_GROUP)
);
CREATE TABLE t_qrtz_fired_triggers 
  (
    ENTRY_ID VARCHAR2(95) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    FIRED_TIME NUMBER(13) NOT NULL,
    PRIORITY NUMBER(13) NOT NULL,
    STATE VARCHAR2(16) NOT NULL,
    JOB_NAME VARCHAR2(200) NULL,
    JOB_GROUP VARCHAR2(200) NULL,
    IS_STATEFUL VARCHAR2(1) NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NULL,
    PRIMARY KEY (ENTRY_ID)
);
CREATE TABLE t_qrtz_scheduler_state 
  (
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    LAST_CHECKIN_TIME NUMBER(13) NOT NULL,
    CHECKIN_INTERVAL NUMBER(13) NOT NULL,
    PRIMARY KEY (INSTANCE_NAME)
);
CREATE TABLE t_qrtz_locks
  (
    LOCK_NAME  VARCHAR2(40) NOT NULL, 
    PRIMARY KEY (LOCK_NAME)
);
INSERT INTO t_qrtz_locks values('TRIGGER_ACCESS');
INSERT INTO t_qrtz_locks values('JOB_ACCESS');
INSERT INTO t_qrtz_locks values('CALENDAR_ACCESS');
INSERT INTO t_qrtz_locks values('STATE_ACCESS');
INSERT INTO t_qrtz_locks values('MISFIRE_ACCESS');
create index idx_qrtz_j_req_recovery on t_qrtz_job_details(REQUESTS_RECOVERY);
create index idx_qrtz_t_next_fire_time on t_qrtz_triggers(NEXT_FIRE_TIME);
create index idx_qrtz_t_state on t_qrtz_triggers(TRIGGER_STATE);
create index idx_qrtz_t_nft_st on t_qrtz_triggers(NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_qrtz_t_volatile on t_qrtz_triggers(IS_VOLATILE);
create index idx_qrtz_ft_trig_name on t_qrtz_fired_triggers(TRIGGER_NAME);
create index idx_qrtz_ft_trig_group on t_qrtz_fired_triggers(TRIGGER_GROUP);
create index idx_qrtz_ft_trig_nm_gp on t_qrtz_fired_triggers(TRIGGER_NAME,TRIGGER_GROUP);
create index idx_qrtz_ft_trig_volatile on t_qrtz_fired_triggers(IS_VOLATILE);
create index idx_qrtz_ft_trig_inst_name on t_qrtz_fired_triggers(INSTANCE_NAME);
create index idx_qrtz_ft_job_name on t_qrtz_fired_triggers(JOB_NAME);
create index idx_qrtz_ft_job_group on t_qrtz_fired_triggers(JOB_GROUP);
create index idx_qrtz_ft_job_stateful on t_qrtz_fired_triggers(IS_STATEFUL);
create index idx_qrtz_ft_job_req_recovery on t_qrtz_fired_triggers(REQUESTS_RECOVERY);

-- Create table
create table T_CFG_SEQUENCE_RULE
(
  ID000           CHAR(2) not null,
  NAME            VARCHAR2(30),
  START_VALUE     NUMBER(19) default 1 not null,
  MAX_VALUE       NUMBER(19),
  PERIOD_TYPE     NUMBER(1) default 0 not null,
  PERIOD_COUNT    NUMBER(19),
  LENGTH          NUMBER(2),
  PREFIX          VARCHAR2(30),
  SUFFIX          VARCHAR2(30),
  INCREMENT_VALUE NUMBER(10) default 1 not null,
  CACHE_SIZE      NUMBER(10) default 20 not null
);
-- Add comments to the table 
comment on table T_CFG_SEQUENCE_RULE
  is '序列号生成规则表';
-- Add comments to the columns 
comment on column T_CFG_SEQUENCE_RULE.ID000
  is '序列号规则类型标识';
comment on column T_CFG_SEQUENCE_RULE.NAME
  is '序列号名称';
comment on column T_CFG_SEQUENCE_RULE.START_VALUE
  is '列号起始值。默认为1';
comment on column T_CFG_SEQUENCE_RULE.MAX_VALUE
  is '序列号最大值，大于0时，将忽略周期类型';
comment on column T_CFG_SEQUENCE_RULE.PERIOD_TYPE
  is '周期类型。0：不循环（默认），1-年，2-月，4-日，5-小时，6-分，7-秒';
comment on column T_CFG_SEQUENCE_RULE.PERIOD_COUNT
  is '周期数';
comment on column T_CFG_SEQUENCE_RULE.LENGTH
  is '长度,。有定义时，不足位数会自动左补0';
comment on column T_CFG_SEQUENCE_RULE.PREFIX
  is '前缀';
comment on column T_CFG_SEQUENCE_RULE.SUFFIX
  is '后缀';
comment on column T_CFG_SEQUENCE_RULE.INCREMENT_VALUE
  is '增量，默认为1';
comment on column T_CFG_SEQUENCE_RULE.CACHE_SIZE
  is '缓存大小。不需要缓存时可设置为1';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CFG_SEQUENCE_RULE
  add constraint QWXXBK_SEQ_RULE_PK primary key (ID000)
  using index;

-- Create table
create table T_CFG_SEQUENCE_NUM
(
  ID000                 CHAR(3) not null,
  RULE_ID               CHAR(2) not null,
  SEQ_NAME              VARCHAR2(30),
  CUR_SEQ               NUMBER(19) default 0 not null,
  CUR_PERIOD_START_TIME VARCHAR2(14),
  ACTIVE                CHAR(1) default 0 not null,
  PREFIX_PARAMS         VARCHAR2(100),
  SUFFIX_PARAMS         VARCHAR2(100)
);
-- Add comments to the table 
comment on table T_CFG_SEQUENCE_NUM
  is '序列号';
-- Add comments to the columns 
comment on column T_CFG_SEQUENCE_NUM.ID000
  is '序列号类型标识';
comment on column T_CFG_SEQUENCE_NUM.RULE_ID
  is '序列号规则类型标识，外键';
comment on column T_CFG_SEQUENCE_NUM.SEQ_NAME
  is '序列号名称';
comment on column T_CFG_SEQUENCE_NUM.CUR_SEQ
  is '当前序列号(不包含前缀、后缀)';
comment on column T_CFG_SEQUENCE_NUM.CUR_PERIOD_START_TIME
  is '当前周期开始时间，周期如果为不循环，无值';
comment on column T_CFG_SEQUENCE_NUM.ACTIVE
  is '有效性，0：无效，1：有效';
comment on column T_CFG_SEQUENCE_NUM.PREFIX_PARAMS
  is '规则中前缀模板的参数值串，以逗号分隔';
comment on column T_CFG_SEQUENCE_NUM.SUFFIX_PARAMS
  is '规则中后缀模板的参数值串，以逗号分隔';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CFG_SEQUENCE_NUM
  add constraint QWXXBK_SEQ_NUM_PK primary key (ID000)
  using index;
alter table T_CFG_SEQUENCE_NUM
  add constraint QWXXBK_SEQ_NUM_FK foreign key (RULE_ID)
  references T_CFG_SEQUENCE_RULE (ID000);

-- Create table
create table T_STAT_SEQUENCE_NUM
(
  ID0000            VARCHAR2(32) not null,
  NUM_ID            CHAR(3) not null,
  PERIOD_TYPE       NUMBER(1) default 0 not null,
  PERIOD_COUNT      NUMBER(19),
  MAX_NUM           NUMBER(19),
  MAX_FULL_NUM      VARCHAR2(256),
  NUM_COUNT         NUMBER(19),
  PERIOD_START_TIME VARCHAR2(14),
  STAT_TIME         VARCHAR2(14)
);
-- Add comments to the table 
comment on table T_STAT_SEQUENCE_NUM
  is '序列号统计表';
-- Add comments to the columns 
comment on column T_STAT_SEQUENCE_NUM.ID0000
  is '主键';
comment on column T_STAT_SEQUENCE_NUM.NUM_ID
  is '外键，序列号码表主键';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_TYPE
  is '周期类型。0：不循环（默认），1：秒，2：分，3：小时，4：日，5：月，6：年';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_COUNT
  is '周期数';
comment on column T_STAT_SEQUENCE_NUM.MAX_NUM
  is '本轮生成的最大序列号';
comment on column T_STAT_SEQUENCE_NUM.MAX_FULL_NUM
  is '本轮生成的最大序列号完整内容(包括前缀、后缀)';
comment on column T_STAT_SEQUENCE_NUM.NUM_COUNT
  is '本轮生成的序列号总个数';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_START_TIME
  is '周期/本轮开始时间';
comment on column T_STAT_SEQUENCE_NUM.STAT_TIME
  is '统计时间(入库时间)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_STAT_SEQUENCE_NUM
  add constraint PK_T_STAT_SEQUENCE_NUM primary key (ID0000)
  using index;
-- Create/Recreate indexes 
create index IDX_STAT_SEQ_NUM_NUM_ID on T_STAT_SEQUENCE_NUM (NUM_ID);
create index IDX_STAT_SEQ_NUM_START_TIME on T_STAT_SEQUENCE_NUM (PERIOD_START_TIME);

-- Create table
create table T_CFG_QRTZ_JOB_PLAN
(
  ID0000            VARCHAR2(12) not null,
  NAME              VARCHAR2(90),
  RUN_STATE         CHAR(1),
  SCHEDULE_MOD      VARCHAR2(10),
  SCHEDULE_INTERVAL CHAR(1),
  SCHEDULE_MONTH    VARCHAR2(50),
  SCHEDULE_WEEK     VARCHAR2(50),
  SCHEDULE_DAY      VARCHAR2(50),
  SCHEDULE_HOUR     VARCHAR2(50),
  SCHEDULE_MINUTE   VARCHAR2(50),
  BEGIN_TIME        DATE,
  END_TIME          DATE,
  LAST_RUN_TIME     DATE,
  NEXT_RUN_TIME     DATE,
  REMARK            VARCHAR2(4000),
  CREATE_TIME       DATE,
  UPDATE_TIME       DATE,
  USER_ID           VARCHAR2(50)
);
-- Add comments to the table 
comment on table T_CFG_QRTZ_JOB_PLAN
  is '作业调度计划配置表';
-- Add comments to the columns 
comment on column T_CFG_QRTZ_JOB_PLAN.ID0000
  is '主键编号';
comment on column T_CFG_QRTZ_JOB_PLAN.NAME
  is '计划名称';
comment on column T_CFG_QRTZ_JOB_PLAN.RUN_STATE
  is '运行状态1-启动，2-停止';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MOD
  is '时间调度模式：1-定时执行，2-单次执行，3-间隔执行，4-立即执行';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_INTERVAL
  is '定时执行间隔：1-年，2-月，3-周，4-日，5-小时，6-分钟';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MONTH
  is '间隔月数';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_WEEK
  is '周：定时周几，支持多个，1-7';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_DAY
  is '日：定时每月的几号，1-31，L表示最后一天。间隔日，天数。';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_HOUR
  is '小时：定时的小时，0-23。间隔，小时数。';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MINUTE
  is '分钟：定时的分钟，0-59。间隔，分钟数。';
comment on column T_CFG_QRTZ_JOB_PLAN.BEGIN_TIME
  is '起始时间';
comment on column T_CFG_QRTZ_JOB_PLAN.END_TIME
  is '结束时间';
comment on column T_CFG_QRTZ_JOB_PLAN.LAST_RUN_TIME
  is '上次执行时间';
comment on column T_CFG_QRTZ_JOB_PLAN.NEXT_RUN_TIME
  is '下次执行时间';
comment on column T_CFG_QRTZ_JOB_PLAN.REMARK
  is '计划说明';
comment on column T_CFG_QRTZ_JOB_PLAN.CREATE_TIME
  is '创建时间';
comment on column T_CFG_QRTZ_JOB_PLAN.UPDATE_TIME
  is '更新时间';
comment on column T_CFG_QRTZ_JOB_PLAN.USER_ID
  is '用户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CFG_QRTZ_JOB_PLAN
  add constraint PK_TESTSCHEDULE_ID primary key (ID0000)
  using index;

-- Create table
create table T_LOG_SQL
(
  ID          VARCHAR2(32) not null,
  LOG_TIME    VARCHAR2(17),
  APP         VARCHAR2(12),
  MODULE      VARCHAR2(30),
  RUN_TIME    NUMBER(7),
  SQL_TEXT    VARCHAR2(4000),
  VAR         VARCHAR2(2000),
  CLASS       VARCHAR2(128),
  METHOD      VARCHAR2(64),
  LINE_NUMBER NUMBER(4)
);
-- Add comments to the table 
comment on table T_LOG_SQL
  is 'Sql脚本日志';
-- Add comments to the columns 
comment on column T_LOG_SQL.LOG_TIME
  is '打日志的时刻';
comment on column T_LOG_SQL.APP
  is '应用（或子系统）简称';
comment on column T_LOG_SQL.MODULE
  is '模块简称';
comment on column T_LOG_SQL.RUN_TIME
  is '耗时(毫秒)';
comment on column T_LOG_SQL.SQL_TEXT
  is 'SQL语句';
comment on column T_LOG_SQL.VAR
  is '变量，WHERE条件值，以英文逗号“,”隔开';
comment on column T_LOG_SQL.CLASS
  is '类';
comment on column T_LOG_SQL.METHOD
  is '方法';
comment on column T_LOG_SQL.LINE_NUMBER
  is '行数';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_LOG_SQL
  add constraint PK_T_LOG_SQL primary key (ID)
  using index;

-- Create table
create table T_CFG_SYS_MENU
(
  CODE             VARCHAR2(6) not null,
  CODE_NAME        VARCHAR2(60),
  PCODE            VARCHAR2(6),
  URL              VARCHAR2(100),
  TARGET           VARCHAR2(20),
  TREE_TYPE        CHAR(1),
  PIC              VARCHAR2(20),
  ORDER_NUM        CHAR(2),
  SUBSYS_POWERTYPE VARCHAR2(1)
);
-- Add comments to the table 
comment on table T_CFG_SYS_MENU
  is '系统菜单';
-- Add comments to the columns 
comment on column T_CFG_SYS_MENU.CODE
  is '主键';
comment on column T_CFG_SYS_MENU.CODE_NAME
  is '菜单项的中文名称';
comment on column T_CFG_SYS_MENU.PCODE
  is '父节点code';
comment on column T_CFG_SYS_MENU.URL
  is '链接串';
comment on column T_CFG_SYS_MENU.TARGET
  is '链接的目标窗口';
comment on column T_CFG_SYS_MENU.TREE_TYPE
  is '0表示父节点，1表示中间结点，2表示子节点';
comment on column T_CFG_SYS_MENU.PIC
  is '节点的图片';
comment on column T_CFG_SYS_MENU.ORDER_NUM
  is '节点的顺序';
comment on column T_CFG_SYS_MENU.SUBSYS_POWERTYPE
  is '子系统权限标识：Z、T，空格表示所有子系统共用权限';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CFG_SYS_MENU
  add constraint PK_T_CFG_QWXXBK_MENU primary key (CODE)
  using index;

-- Create table
create table T_CFG_SYS_PARAMETER
(
  PARAM_NAME  VARCHAR2(64) not null,
  PARAM_VALUE VARCHAR2(200),
  MEMO        VARCHAR2(1000)
);
-- Add comments to the table 
comment on table T_CFG_SYS_PARAMETER
  is '全网信息布控系统参数表';
-- Add comments to the columns 
comment on column T_CFG_SYS_PARAMETER.PARAM_NAME
  is '系统参数名';
comment on column T_CFG_SYS_PARAMETER.PARAM_VALUE
  is '参数值';
comment on column T_CFG_SYS_PARAMETER.MEMO
  is '说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CFG_SYS_PARAMETER
  add constraint PK_T_CFG_SYS_PARAMETER primary key (PARAM_NAME)
  using index;


create table T_CODE_DATA_TYPE
(
  CODE_ID    VARCHAR2(30) not null,
  CODE_NAME  VARCHAR2(30),
  CODE_ORDER NUMBER(5)
)
;
comment on table T_CODE_DATA_TYPE
  is '数据展现列型';
comment on column T_CODE_DATA_TYPE.CODE_ID
  is '展现类型编号';
comment on column T_CODE_DATA_TYPE.CODE_NAME
  is '展现类型名称';
comment on column T_CODE_DATA_TYPE.CODE_ORDER
  is '顺序号';
alter table T_CODE_DATA_TYPE
  add constraint PK_T_CDTYPE primary key (CODE_ID);

create table T_MD_DATA_SOURCE
(
  DATASOURCE_ID    CHAR(10) not null,
  DB_TYPE          VARCHAR2(30),
  NAME             VARCHAR2(50),
  JNDI_NAME        VARCHAR2(30),
  USERNAME         VARCHAR2(30),
  PASSWORD         VARCHAR2(30),
  PARAMETER        VARCHAR2(500),
  CHARSET          CHAR(1),
  IS_DELETED       CHAR(1),
  IP_ADDRESS       VARCHAR2(30),
  SERVER_PORT      VARCHAR2(5),
  MAX_CONN_COUNT   NUMBER(5) default 10,
  MIN_CONN_COUNT   NUMBER(5) default 5,
  DBNAME           VARCHAR2(50),
  DATASOURCE_ALIAS VARCHAR2(50),
  IS_SYSTEM        CHAR(1),
  CREATE_USER      VARCHAR2(50),
  CREATE_TIME      VARCHAR2(14),
  CREATE_DEPT      VARCHAR2(16),
  DBLINK           VARCHAR2(100),
  SIGN             CHAR(1)
)
;
comment on table T_MD_DATA_SOURCE
  is '数据源定义';
comment on column T_MD_DATA_SOURCE.DATASOURCE_ID
  is '数据源编号';
comment on column T_MD_DATA_SOURCE.DB_TYPE
  is '数据库类型';
comment on column T_MD_DATA_SOURCE.NAME
  is '数据源名称';
comment on column T_MD_DATA_SOURCE.JNDI_NAME
  is 'JNDI名称';
comment on column T_MD_DATA_SOURCE.USERNAME
  is '用户名';
comment on column T_MD_DATA_SOURCE.PASSWORD
  is '密码';
comment on column T_MD_DATA_SOURCE.PARAMETER
  is '数据库参数';
comment on column T_MD_DATA_SOURCE.CHARSET
  is '字符集(0:中文;1:英文)';
comment on column T_MD_DATA_SOURCE.IS_DELETED
  is '删除标识; 1-删除；0-使用中';
comment on column T_MD_DATA_SOURCE.IP_ADDRESS
  is '主机IP地址';
comment on column T_MD_DATA_SOURCE.SERVER_PORT
  is '服务器端口';
comment on column T_MD_DATA_SOURCE.MAX_CONN_COUNT
  is '最大连接数';
comment on column T_MD_DATA_SOURCE.MIN_CONN_COUNT
  is '最小连接数';
comment on column T_MD_DATA_SOURCE.DBNAME
  is '数据库名';
comment on column T_MD_DATA_SOURCE.DATASOURCE_ALIAS
  is '数据库别名';
comment on column T_MD_DATA_SOURCE.IS_SYSTEM
  is '内置数据源标识符(1内置,0用户定义)';
comment on column T_MD_DATA_SOURCE.CREATE_USER
  is '创建用户ID';
comment on column T_MD_DATA_SOURCE.CREATE_TIME
  is '创建时间';
comment on column T_MD_DATA_SOURCE.CREATE_DEPT
  is '创建单位编码';
comment on column T_MD_DATA_SOURCE.DBLINK
  is '数据链路';
comment on column T_MD_DATA_SOURCE.SIGN
  is '删除标识，该字段停止使用，使用IS_DELETED字段';
alter table T_MD_DATA_SOURCE
  add constraint PK_T_MD_DS primary key (DATASOURCE_ID);


create table T_MD_CODE_DIC
(
  CODE_ID       VARCHAR2(60) not null,
  DATASOURCE_ID CHAR(10),
  NAME          VARCHAR2(60),
  ENTITY_NAME   VARCHAR2(30),
  CODE_FIELD    VARCHAR2(30),
  NAME_FIELD    VARCHAR2(30),
  PY_FIELD      VARCHAR2(30),
  WB_FIELD      VARCHAR2(30),
  ORDER_FIELD   VARCHAR2(30),
  SEGMENT_RULE  VARCHAR2(30),
  VIEW_SQL      VARCHAR2(3000),
  IS_SYSTEM     CHAR(1),
  LOAD_TYPE     CHAR(1)
)
;
comment on column T_MD_CODE_DIC.CODE_ID
  is '表码编号';
comment on column T_MD_CODE_DIC.DATASOURCE_ID
  is '数据源编号';
comment on column T_MD_CODE_DIC.NAME
  is '表码名称';
comment on column T_MD_CODE_DIC.ENTITY_NAME
  is '实体名';
comment on column T_MD_CODE_DIC.CODE_FIELD
  is '表码字段';
comment on column T_MD_CODE_DIC.NAME_FIELD
  is '名称字段';
comment on column T_MD_CODE_DIC.PY_FIELD
  is '拼音字段';
comment on column T_MD_CODE_DIC.WB_FIELD
  is '五笔字段';
comment on column T_MD_CODE_DIC.ORDER_FIELD
  is '排序字段';
comment on column T_MD_CODE_DIC.SEGMENT_RULE
  is '分段规则(2;2;2)';
comment on column T_MD_CODE_DIC.VIEW_SQL
  is '视图SQL';
comment on column T_MD_CODE_DIC.IS_SYSTEM
  is '表码类型（1表示是内置表码，0表示是用户定义的表码）';
comment on column T_MD_CODE_DIC.LOAD_TYPE
  is '加载类型：1、启动时加载；2、触发加载；3、不加载';
alter table T_MD_CODE_DIC
  add constraint PK_T_CDIC primary key (CODE_ID);
alter table T_MD_CODE_DIC
  add constraint FK_T_CDFRMD foreign key (DATASOURCE_ID)
  references T_MD_DATA_SOURCE (DATASOURCE_ID);


create table T_MD_DATA_FIELD
(
  DATAFIELD_ID    VARCHAR2(32) not null,
  DATAOBJECT_ID   VARCHAR2(32),
  FIELD_ORDER     NUMBER(5),
  ENTITY_NAME     VARCHAR2(60),
  DISPLAY_NAME    VARCHAR2(90),
  FIELD_TYPE      VARCHAR2(30),
  LENGTH          NUMBER(5),
  IS_FIX_LENGTH   CHAR(1),
  PRECISION       NUMBER,
  SCALE           NUMBER,
  IS_NULLABLE     CHAR(1),
  DATA_TYPE       VARCHAR2(30),
  CODE_ID         VARCHAR2(60),
  DESCRIPTION     VARCHAR2(255),
  COMMON_FIELD_ID VARCHAR2(30),
  IS_DELETED      CHAR(1),
  IS_KEY          CHAR(1),
  DATE_FORMAT     VARCHAR2(30),
  TRS_FIELD_TYPE  VARCHAR2(30),
  COL_NAME        VARCHAR2(30),
  SPLITCODE       VARCHAR2(30),
  IS_DETAIL       CHAR(1),
  IS_BRIEF        CHAR(1),
  IS_INCREMENT    CHAR(1),
  IS_ENCRYPT      CHAR(1)
)
;
comment on table T_MD_DATA_FIELD
  is '数据字段表';
comment on column T_MD_DATA_FIELD.DATAFIELD_ID
  is '字段编号';
comment on column T_MD_DATA_FIELD.DATAOBJECT_ID
  is '数据对象编号';
comment on column T_MD_DATA_FIELD.FIELD_ORDER
  is '排序号';
comment on column T_MD_DATA_FIELD.ENTITY_NAME
  is '字段实体名';
comment on column T_MD_DATA_FIELD.DISPLAY_NAME
  is '字段显示名';
comment on column T_MD_DATA_FIELD.FIELD_TYPE
  is '数据类型';
comment on column T_MD_DATA_FIELD.LENGTH
  is '字段大小';
comment on column T_MD_DATA_FIELD.IS_FIX_LENGTH
  is '是否固定大小';
comment on column T_MD_DATA_FIELD.PRECISION
  is '数值精度';
comment on column T_MD_DATA_FIELD.SCALE
  is '数值范围';
comment on column T_MD_DATA_FIELD.IS_NULLABLE
  is '是否可空';
comment on column T_MD_DATA_FIELD.DATA_TYPE
  is '数据表现类型';
comment on column T_MD_DATA_FIELD.CODE_ID
  is '引用表码ID';
comment on column T_MD_DATA_FIELD.DESCRIPTION
  is '描述';
comment on column T_MD_DATA_FIELD.COMMON_FIELD_ID
  is '公共标识字段名';
comment on column T_MD_DATA_FIELD.IS_DELETED
  is '删除标识';
comment on column T_MD_DATA_FIELD.IS_KEY
  is '主键标识';
comment on column T_MD_DATA_FIELD.DATE_FORMAT
  is '日期时间格式';
comment on column T_MD_DATA_FIELD.TRS_FIELD_TYPE
  is 'TRS字段类型';
comment on column T_MD_DATA_FIELD.COL_NAME
  is '字段名称';
comment on column T_MD_DATA_FIELD.SPLITCODE
  is '表码分隔符';
comment on column T_MD_DATA_FIELD.IS_DETAIL
  is '详细输出字段，1:是；0:否';
comment on column T_MD_DATA_FIELD.IS_BRIEF
  is '简要输出字段，1:是；0:否';
comment on column T_MD_DATA_FIELD.IS_INCREMENT
  is '增量标识字段，1:是；0:否
一个数据对象只能有一个增量标识字段';
comment on column T_MD_DATA_FIELD.IS_ENCRYPT
  is '是否加密 ，1：是，0：否';
alter table T_MD_DATA_FIELD
  add constraint PK_T_MD_DF primary key (DATAFIELD_ID);


create table T_MD_DATA_OBJECT
(
  DATAOBJECT_ID     VARCHAR2(32) not null,
  DATASOURCE_ID     CHAR(10),
  ENTITY_NAME       VARCHAR2(60),
  DISPLAY_NAME      VARCHAR2(250),
  DATAOBJECT_TYPE   CHAR(2) default 'TB',
  OWNER             VARCHAR2(60),
  DESCRIPTION       VARCHAR2(1024),
  IS_DELETED        CHAR(1),
  TRS_DATASOURCE_ID CHAR(10),
  TRS_DATAOBJECT_ID VARCHAR2(50),
  SECRECY_LEVEL     CHAR(1) default '0',
  TABLE_TYPE        CHAR(1),
  IS_SYSTEM         CHAR(1),
  CREATE_USER       VARCHAR2(50),
  CREATE_TIME       VARCHAR2(14),
  CREATE_DEPT       VARCHAR2(16),
  DQS_ID            VARCHAR2(60),
  ISOPENSECRET      VARCHAR2(1),
  VIEW_MASTER_TABLE VARCHAR2(60)
)
;
comment on table T_MD_DATA_OBJECT
  is '数据对象定义';
comment on column T_MD_DATA_OBJECT.DATAOBJECT_ID
  is '数据对象编号';
comment on column T_MD_DATA_OBJECT.DATASOURCE_ID
  is '数据源编号';
comment on column T_MD_DATA_OBJECT.ENTITY_NAME
  is '数据对象实体名';
comment on column T_MD_DATA_OBJECT.DISPLAY_NAME
  is '数据对象中文名称';
comment on column T_MD_DATA_OBJECT.DATAOBJECT_TYPE
  is '数据对象类型(TB:表；VW:视图；DQ分布式)';
comment on column T_MD_DATA_OBJECT.OWNER
  is '对象所有者';
comment on column T_MD_DATA_OBJECT.DESCRIPTION
  is '简要描述';
comment on column T_MD_DATA_OBJECT.IS_DELETED
  is '删除标识';
comment on column T_MD_DATA_OBJECT.TRS_DATASOURCE_ID
  is 'TRS数据源ID';
comment on column T_MD_DATA_OBJECT.TRS_DATAOBJECT_ID
  is 'TRS数据对象ID';
comment on column T_MD_DATA_OBJECT.SECRECY_LEVEL
  is '数据对象密级(0:低级别;1:高级别';
comment on column T_MD_DATA_OBJECT.TABLE_TYPE
  is '数据对象类型 0.业务表，1即席比对临时表，2结果表，3分布式表，a关注区域明细表';
comment on column T_MD_DATA_OBJECT.IS_SYSTEM
  is '是否系统内置对象0否.1是';
comment on column T_MD_DATA_OBJECT.CREATE_USER
  is '创建用户ID';
comment on column T_MD_DATA_OBJECT.CREATE_TIME
  is '创建时间';
comment on column T_MD_DATA_OBJECT.CREATE_DEPT
  is '创建单位编码';
comment on column T_MD_DATA_OBJECT.DQS_ID
  is '分布式数据源ID';
comment on column T_MD_DATA_OBJECT.ISOPENSECRET
  is '是否使用密级查询';
comment on column T_MD_DATA_OBJECT.VIEW_MASTER_TABLE
  is '视图主表';
alter table T_MD_DATA_OBJECT
  add constraint PK_T_MD_DO primary key (DATAOBJECT_ID);
alter table T_MD_DATA_OBJECT
  add constraint FK_TMD_FK_TMD foreign key (DATASOURCE_ID)
  references T_MD_DATA_SOURCE (DATASOURCE_ID);

