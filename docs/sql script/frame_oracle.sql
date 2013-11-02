-- ����quartz�������ı� by ������ 10:45 2011-11-8
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
  is '���к����ɹ����';
-- Add comments to the columns 
comment on column T_CFG_SEQUENCE_RULE.ID000
  is '���кŹ������ͱ�ʶ';
comment on column T_CFG_SEQUENCE_RULE.NAME
  is '���к�����';
comment on column T_CFG_SEQUENCE_RULE.START_VALUE
  is '�к���ʼֵ��Ĭ��Ϊ1';
comment on column T_CFG_SEQUENCE_RULE.MAX_VALUE
  is '���к����ֵ������0ʱ����������������';
comment on column T_CFG_SEQUENCE_RULE.PERIOD_TYPE
  is '�������͡�0����ѭ����Ĭ�ϣ���1-�꣬2-�£�4-�գ�5-Сʱ��6-�֣�7-��';
comment on column T_CFG_SEQUENCE_RULE.PERIOD_COUNT
  is '������';
comment on column T_CFG_SEQUENCE_RULE.LENGTH
  is '����,���ж���ʱ������λ�����Զ���0';
comment on column T_CFG_SEQUENCE_RULE.PREFIX
  is 'ǰ׺';
comment on column T_CFG_SEQUENCE_RULE.SUFFIX
  is '��׺';
comment on column T_CFG_SEQUENCE_RULE.INCREMENT_VALUE
  is '������Ĭ��Ϊ1';
comment on column T_CFG_SEQUENCE_RULE.CACHE_SIZE
  is '�����С������Ҫ����ʱ������Ϊ1';
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
  is '���к�';
-- Add comments to the columns 
comment on column T_CFG_SEQUENCE_NUM.ID000
  is '���к����ͱ�ʶ';
comment on column T_CFG_SEQUENCE_NUM.RULE_ID
  is '���кŹ������ͱ�ʶ�����';
comment on column T_CFG_SEQUENCE_NUM.SEQ_NAME
  is '���к�����';
comment on column T_CFG_SEQUENCE_NUM.CUR_SEQ
  is '��ǰ���к�(������ǰ׺����׺)';
comment on column T_CFG_SEQUENCE_NUM.CUR_PERIOD_START_TIME
  is '��ǰ���ڿ�ʼʱ�䣬�������Ϊ��ѭ������ֵ';
comment on column T_CFG_SEQUENCE_NUM.ACTIVE
  is '��Ч�ԣ�0����Ч��1����Ч';
comment on column T_CFG_SEQUENCE_NUM.PREFIX_PARAMS
  is '������ǰ׺ģ��Ĳ���ֵ�����Զ��ŷָ�';
comment on column T_CFG_SEQUENCE_NUM.SUFFIX_PARAMS
  is '�����к�׺ģ��Ĳ���ֵ�����Զ��ŷָ�';
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
  is '���к�ͳ�Ʊ�';
-- Add comments to the columns 
comment on column T_STAT_SEQUENCE_NUM.ID0000
  is '����';
comment on column T_STAT_SEQUENCE_NUM.NUM_ID
  is '��������к��������';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_TYPE
  is '�������͡�0����ѭ����Ĭ�ϣ���1���룬2���֣�3��Сʱ��4���գ�5���£�6����';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_COUNT
  is '������';
comment on column T_STAT_SEQUENCE_NUM.MAX_NUM
  is '�������ɵ�������к�';
comment on column T_STAT_SEQUENCE_NUM.MAX_FULL_NUM
  is '�������ɵ�������к���������(����ǰ׺����׺)';
comment on column T_STAT_SEQUENCE_NUM.NUM_COUNT
  is '�������ɵ����к��ܸ���';
comment on column T_STAT_SEQUENCE_NUM.PERIOD_START_TIME
  is '����/���ֿ�ʼʱ��';
comment on column T_STAT_SEQUENCE_NUM.STAT_TIME
  is 'ͳ��ʱ��(���ʱ��)';
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
  is '��ҵ���ȼƻ����ñ�';
-- Add comments to the columns 
comment on column T_CFG_QRTZ_JOB_PLAN.ID0000
  is '�������';
comment on column T_CFG_QRTZ_JOB_PLAN.NAME
  is '�ƻ�����';
comment on column T_CFG_QRTZ_JOB_PLAN.RUN_STATE
  is '����״̬1-������2-ֹͣ';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MOD
  is 'ʱ�����ģʽ��1-��ʱִ�У�2-����ִ�У�3-���ִ�У�4-����ִ��';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_INTERVAL
  is '��ʱִ�м����1-�꣬2-�£�3-�ܣ�4-�գ�5-Сʱ��6-����';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MONTH
  is '�������';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_WEEK
  is '�ܣ���ʱ�ܼ���֧�ֶ����1-7';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_DAY
  is '�գ���ʱÿ�µļ��ţ�1-31��L��ʾ���һ�졣����գ�������';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_HOUR
  is 'Сʱ����ʱ��Сʱ��0-23�������Сʱ����';
comment on column T_CFG_QRTZ_JOB_PLAN.SCHEDULE_MINUTE
  is '���ӣ���ʱ�ķ��ӣ�0-59���������������';
comment on column T_CFG_QRTZ_JOB_PLAN.BEGIN_TIME
  is '��ʼʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.END_TIME
  is '����ʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.LAST_RUN_TIME
  is '�ϴ�ִ��ʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.NEXT_RUN_TIME
  is '�´�ִ��ʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.REMARK
  is '�ƻ�˵��';
comment on column T_CFG_QRTZ_JOB_PLAN.CREATE_TIME
  is '����ʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.UPDATE_TIME
  is '����ʱ��';
comment on column T_CFG_QRTZ_JOB_PLAN.USER_ID
  is '�û�ID';
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
  is 'Sql�ű���־';
-- Add comments to the columns 
comment on column T_LOG_SQL.LOG_TIME
  is '����־��ʱ��';
comment on column T_LOG_SQL.APP
  is 'Ӧ�ã�����ϵͳ�����';
comment on column T_LOG_SQL.MODULE
  is 'ģ����';
comment on column T_LOG_SQL.RUN_TIME
  is '��ʱ(����)';
comment on column T_LOG_SQL.SQL_TEXT
  is 'SQL���';
comment on column T_LOG_SQL.VAR
  is '������WHERE����ֵ����Ӣ�Ķ��š�,������';
comment on column T_LOG_SQL.CLASS
  is '��';
comment on column T_LOG_SQL.METHOD
  is '����';
comment on column T_LOG_SQL.LINE_NUMBER
  is '����';
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
  is 'ϵͳ�˵�';
-- Add comments to the columns 
comment on column T_CFG_SYS_MENU.CODE
  is '����';
comment on column T_CFG_SYS_MENU.CODE_NAME
  is '�˵������������';
comment on column T_CFG_SYS_MENU.PCODE
  is '���ڵ�code';
comment on column T_CFG_SYS_MENU.URL
  is '���Ӵ�';
comment on column T_CFG_SYS_MENU.TARGET
  is '���ӵ�Ŀ�괰��';
comment on column T_CFG_SYS_MENU.TREE_TYPE
  is '0��ʾ���ڵ㣬1��ʾ�м��㣬2��ʾ�ӽڵ�';
comment on column T_CFG_SYS_MENU.PIC
  is '�ڵ��ͼƬ';
comment on column T_CFG_SYS_MENU.ORDER_NUM
  is '�ڵ��˳��';
comment on column T_CFG_SYS_MENU.SUBSYS_POWERTYPE
  is '��ϵͳȨ�ޱ�ʶ��Z��T���ո��ʾ������ϵͳ����Ȩ��';
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
  is 'ȫ����Ϣ����ϵͳ������';
-- Add comments to the columns 
comment on column T_CFG_SYS_PARAMETER.PARAM_NAME
  is 'ϵͳ������';
comment on column T_CFG_SYS_PARAMETER.PARAM_VALUE
  is '����ֵ';
comment on column T_CFG_SYS_PARAMETER.MEMO
  is '˵��';
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
  is '����չ������';
comment on column T_CODE_DATA_TYPE.CODE_ID
  is 'չ�����ͱ��';
comment on column T_CODE_DATA_TYPE.CODE_NAME
  is 'չ����������';
comment on column T_CODE_DATA_TYPE.CODE_ORDER
  is '˳���';
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
  is '����Դ����';
comment on column T_MD_DATA_SOURCE.DATASOURCE_ID
  is '����Դ���';
comment on column T_MD_DATA_SOURCE.DB_TYPE
  is '���ݿ�����';
comment on column T_MD_DATA_SOURCE.NAME
  is '����Դ����';
comment on column T_MD_DATA_SOURCE.JNDI_NAME
  is 'JNDI����';
comment on column T_MD_DATA_SOURCE.USERNAME
  is '�û���';
comment on column T_MD_DATA_SOURCE.PASSWORD
  is '����';
comment on column T_MD_DATA_SOURCE.PARAMETER
  is '���ݿ����';
comment on column T_MD_DATA_SOURCE.CHARSET
  is '�ַ���(0:����;1:Ӣ��)';
comment on column T_MD_DATA_SOURCE.IS_DELETED
  is 'ɾ����ʶ; 1-ɾ����0-ʹ����';
comment on column T_MD_DATA_SOURCE.IP_ADDRESS
  is '����IP��ַ';
comment on column T_MD_DATA_SOURCE.SERVER_PORT
  is '�������˿�';
comment on column T_MD_DATA_SOURCE.MAX_CONN_COUNT
  is '���������';
comment on column T_MD_DATA_SOURCE.MIN_CONN_COUNT
  is '��С������';
comment on column T_MD_DATA_SOURCE.DBNAME
  is '���ݿ���';
comment on column T_MD_DATA_SOURCE.DATASOURCE_ALIAS
  is '���ݿ����';
comment on column T_MD_DATA_SOURCE.IS_SYSTEM
  is '��������Դ��ʶ��(1����,0�û�����)';
comment on column T_MD_DATA_SOURCE.CREATE_USER
  is '�����û�ID';
comment on column T_MD_DATA_SOURCE.CREATE_TIME
  is '����ʱ��';
comment on column T_MD_DATA_SOURCE.CREATE_DEPT
  is '������λ����';
comment on column T_MD_DATA_SOURCE.DBLINK
  is '������·';
comment on column T_MD_DATA_SOURCE.SIGN
  is 'ɾ����ʶ�����ֶ�ֹͣʹ�ã�ʹ��IS_DELETED�ֶ�';
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
  is '������';
comment on column T_MD_CODE_DIC.DATASOURCE_ID
  is '����Դ���';
comment on column T_MD_CODE_DIC.NAME
  is '��������';
comment on column T_MD_CODE_DIC.ENTITY_NAME
  is 'ʵ����';
comment on column T_MD_CODE_DIC.CODE_FIELD
  is '�����ֶ�';
comment on column T_MD_CODE_DIC.NAME_FIELD
  is '�����ֶ�';
comment on column T_MD_CODE_DIC.PY_FIELD
  is 'ƴ���ֶ�';
comment on column T_MD_CODE_DIC.WB_FIELD
  is '����ֶ�';
comment on column T_MD_CODE_DIC.ORDER_FIELD
  is '�����ֶ�';
comment on column T_MD_CODE_DIC.SEGMENT_RULE
  is '�ֶι���(2;2;2)';
comment on column T_MD_CODE_DIC.VIEW_SQL
  is '��ͼSQL';
comment on column T_MD_CODE_DIC.IS_SYSTEM
  is '�������ͣ�1��ʾ�����ñ��룬0��ʾ���û�����ı��룩';
comment on column T_MD_CODE_DIC.LOAD_TYPE
  is '�������ͣ�1������ʱ���أ�2���������أ�3��������';
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
  is '�����ֶα�';
comment on column T_MD_DATA_FIELD.DATAFIELD_ID
  is '�ֶα��';
comment on column T_MD_DATA_FIELD.DATAOBJECT_ID
  is '���ݶ�����';
comment on column T_MD_DATA_FIELD.FIELD_ORDER
  is '�����';
comment on column T_MD_DATA_FIELD.ENTITY_NAME
  is '�ֶ�ʵ����';
comment on column T_MD_DATA_FIELD.DISPLAY_NAME
  is '�ֶ���ʾ��';
comment on column T_MD_DATA_FIELD.FIELD_TYPE
  is '��������';
comment on column T_MD_DATA_FIELD.LENGTH
  is '�ֶδ�С';
comment on column T_MD_DATA_FIELD.IS_FIX_LENGTH
  is '�Ƿ�̶���С';
comment on column T_MD_DATA_FIELD.PRECISION
  is '��ֵ����';
comment on column T_MD_DATA_FIELD.SCALE
  is '��ֵ��Χ';
comment on column T_MD_DATA_FIELD.IS_NULLABLE
  is '�Ƿ�ɿ�';
comment on column T_MD_DATA_FIELD.DATA_TYPE
  is '���ݱ�������';
comment on column T_MD_DATA_FIELD.CODE_ID
  is '���ñ���ID';
comment on column T_MD_DATA_FIELD.DESCRIPTION
  is '����';
comment on column T_MD_DATA_FIELD.COMMON_FIELD_ID
  is '������ʶ�ֶ���';
comment on column T_MD_DATA_FIELD.IS_DELETED
  is 'ɾ����ʶ';
comment on column T_MD_DATA_FIELD.IS_KEY
  is '������ʶ';
comment on column T_MD_DATA_FIELD.DATE_FORMAT
  is '����ʱ���ʽ';
comment on column T_MD_DATA_FIELD.TRS_FIELD_TYPE
  is 'TRS�ֶ�����';
comment on column T_MD_DATA_FIELD.COL_NAME
  is '�ֶ�����';
comment on column T_MD_DATA_FIELD.SPLITCODE
  is '����ָ���';
comment on column T_MD_DATA_FIELD.IS_DETAIL
  is '��ϸ����ֶΣ�1:�ǣ�0:��';
comment on column T_MD_DATA_FIELD.IS_BRIEF
  is '��Ҫ����ֶΣ�1:�ǣ�0:��';
comment on column T_MD_DATA_FIELD.IS_INCREMENT
  is '������ʶ�ֶΣ�1:�ǣ�0:��
һ�����ݶ���ֻ����һ��������ʶ�ֶ�';
comment on column T_MD_DATA_FIELD.IS_ENCRYPT
  is '�Ƿ���� ��1���ǣ�0����';
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
  is '���ݶ�����';
comment on column T_MD_DATA_OBJECT.DATAOBJECT_ID
  is '���ݶ�����';
comment on column T_MD_DATA_OBJECT.DATASOURCE_ID
  is '����Դ���';
comment on column T_MD_DATA_OBJECT.ENTITY_NAME
  is '���ݶ���ʵ����';
comment on column T_MD_DATA_OBJECT.DISPLAY_NAME
  is '���ݶ�����������';
comment on column T_MD_DATA_OBJECT.DATAOBJECT_TYPE
  is '���ݶ�������(TB:��VW:��ͼ��DQ�ֲ�ʽ)';
comment on column T_MD_DATA_OBJECT.OWNER
  is '����������';
comment on column T_MD_DATA_OBJECT.DESCRIPTION
  is '��Ҫ����';
comment on column T_MD_DATA_OBJECT.IS_DELETED
  is 'ɾ����ʶ';
comment on column T_MD_DATA_OBJECT.TRS_DATASOURCE_ID
  is 'TRS����ԴID';
comment on column T_MD_DATA_OBJECT.TRS_DATAOBJECT_ID
  is 'TRS���ݶ���ID';
comment on column T_MD_DATA_OBJECT.SECRECY_LEVEL
  is '���ݶ����ܼ�(0:�ͼ���;1:�߼���';
comment on column T_MD_DATA_OBJECT.TABLE_TYPE
  is '���ݶ������� 0.ҵ���1��ϯ�ȶ���ʱ��2�����3�ֲ�ʽ��a��ע������ϸ��';
comment on column T_MD_DATA_OBJECT.IS_SYSTEM
  is '�Ƿ�ϵͳ���ö���0��.1��';
comment on column T_MD_DATA_OBJECT.CREATE_USER
  is '�����û�ID';
comment on column T_MD_DATA_OBJECT.CREATE_TIME
  is '����ʱ��';
comment on column T_MD_DATA_OBJECT.CREATE_DEPT
  is '������λ����';
comment on column T_MD_DATA_OBJECT.DQS_ID
  is '�ֲ�ʽ����ԴID';
comment on column T_MD_DATA_OBJECT.ISOPENSECRET
  is '�Ƿ�ʹ���ܼ���ѯ';
comment on column T_MD_DATA_OBJECT.VIEW_MASTER_TABLE
  is '��ͼ����';
alter table T_MD_DATA_OBJECT
  add constraint PK_T_MD_DO primary key (DATAOBJECT_ID);
alter table T_MD_DATA_OBJECT
  add constraint FK_TMD_FK_TMD foreign key (DATASOURCE_ID)
  references T_MD_DATA_SOURCE (DATASOURCE_ID);

