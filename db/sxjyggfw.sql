----------------------------------------------
-- Export file for user SXJYGGFW@ORCL101    --
-- Created by wengsh on 2019/1/22, 16:59:28 --
----------------------------------------------

set define off
spool sxjyggfw.log

prompt
prompt Creating table CODE_TYPE_SORT
prompt =============================
prompt
create table CODE_TYPE_SORT
(
  type_sort_id    VARCHAR2(36) not null,
  type_sort_code  VARCHAR2(36),
  type_sort_name  VARCHAR2(50),
  type_sort_desc  VARCHAR2(200),
  type_sort_order NUMBER
)
;
comment on table CODE_TYPE_SORT
  is '[0078]ҵ��������ͱ�';
comment on column CODE_TYPE_SORT.type_sort_id
  is '�������ͱ��(�������:uuid)';
comment on column CODE_TYPE_SORT.type_sort_code
  is '�������ͱ���(�������:6-12λ��ĸ)';
comment on column CODE_TYPE_SORT.type_sort_name
  is '������������';
comment on column CODE_TYPE_SORT.type_sort_desc
  is '������������';
comment on column CODE_TYPE_SORT.type_sort_order
  is '�����������������';

prompt
prompt Creating table ITEM_INTERFACE_CONFIG
prompt ====================================
prompt
create table ITEM_INTERFACE_CONFIG
(
  interface_type VARCHAR2(32),
  interface_name VARCHAR2(50),
  interface_url  VARCHAR2(50)
)
;
comment on table ITEM_INTERFACE_CONFIG
  is '����ӿ����ñ�';
comment on column ITEM_INTERFACE_CONFIG.interface_type
  is '�ӿ�����';
comment on column ITEM_INTERFACE_CONFIG.interface_name
  is '�ӿ�����';
comment on column ITEM_INTERFACE_CONFIG.interface_url
  is '�ӿڵ�ַ';

prompt
prompt Creating table ITEM_LIST
prompt ========================
prompt
create table ITEM_LIST
(
  item_id          VARCHAR2(32),
  item_code        VARCHAR2(20),
  item_name        VARCHAR2(100),
  item_url         VARCHAR2(200),
  item_submit_type NUMBER(1),
  bsplatform_url   VARCHAR2(200)
)
;
comment on table ITEM_LIST
  is '�������������б�';
comment on column ITEM_LIST.item_id
  is '����ID';
comment on column ITEM_LIST.item_code
  is '������';
comment on column ITEM_LIST.item_name
  is '��������';
comment on column ITEM_LIST.item_url
  is 'ҵ��ϵͳ��ַ';
comment on column ITEM_LIST.item_submit_type
  is '1-ҵ��ϵͳ  2-��׼��ƽ̨';
comment on column ITEM_LIST.bsplatform_url
  is '��׼����ַ';

prompt
prompt Creating table ITEM_LIST2
prompt =========================
prompt
create table ITEM_LIST2
(
  item_id                    VARCHAR2(32),
  item_code                  VARCHAR2(20),
  item_name                  VARCHAR2(100),
  business_system_url_prefix VARCHAR2(200),
  item_submit_type           NUMBER(1),
  bsplatform_url_prefix      VARCHAR2(200)
)
;

prompt
prompt Creating table ITEM_LIST_CHECK
prompt ==============================
prompt
create table ITEM_LIST_CHECK
(
  item_id VARCHAR2(32),
  url     VARCHAR2(32),
  type    NUMBER(1)
)
;
comment on table ITEM_LIST_CHECK
  is '����У��ӿ�';
comment on column ITEM_LIST_CHECK.item_id
  is '����ID';
comment on column ITEM_LIST_CHECK.url
  is '�ӿڵ�ַ';
comment on column ITEM_LIST_CHECK.type
  is '�ӿ�����';

prompt
prompt Creating table ITEM_MATERIAL_LIST
prompt =================================
prompt
create table ITEM_MATERIAL_LIST
(
  item_id       VARCHAR2(20),
  material_code VARCHAR2(20),
  material_name VARCHAR2(100),
  necessity     NUMBER(1) default 0
)
;
comment on table ITEM_MATERIAL_LIST
  is '��������б�';
comment on column ITEM_MATERIAL_LIST.item_id
  is '������';
comment on column ITEM_MATERIAL_LIST.material_code
  is '���ϱ���';
comment on column ITEM_MATERIAL_LIST.material_name
  is '��������';
comment on column ITEM_MATERIAL_LIST.necessity
  is '�Ƿ�ش� 0-�� 1-��';

prompt
prompt Creating table SER_BROWSE
prompt =========================
prompt
create table SER_BROWSE
(
  browse_id  VARCHAR2(36) not null,
  cata_id    VARCHAR2(36) not null,
  userid     VARCHAR2(36) not null,
  createtime DATE not null,
  status     VARCHAR2(20) not null
)
;
comment on table SER_BROWSE
  is '[0104]��������-�������������¼��Ϣ��';
comment on column SER_BROWSE.browse_id
  is '�������������¼����(�������:UUID)';
comment on column SER_BROWSE.cata_id
  is '������������(�������:UUID)';
comment on column SER_BROWSE.userid
  is '����������˻�����(�û������е���Ա)';
comment on column SER_BROWSE.createtime
  is '�������';
comment on column SER_BROWSE.status
  is '�������������¼״̬����(1:��Ч��0:��Ч)';
alter table SER_BROWSE
  add constraint PK_SER_BROWSE primary key (BROWSE_ID);

prompt
prompt Creating table SER_BUS_TYPE
prompt ===========================
prompt
create table SER_BUS_TYPE
(
  bus_type_id         VARCHAR2(36) not null,
  bus_area_code       VARCHAR2(36) not null,
  bus_type_code       VARCHAR2(36) not null,
  bus_type_name       VARCHAR2(400) not null,
  bus_type_sort       NUMBER(16) not null,
  bus_type_pingyin    VARCHAR2(200),
  bus_type_desc       VARCHAR2(4000),
  bus_type_bigimgtype VARCHAR2(20),
  bus_type_smalltype  VARCHAR2(20),
  userid              VARCHAR2(36) not null,
  groupid             VARCHAR2(36) not null,
  createtime          DATE not null,
  edituserid          VARCHAR2(36),
  editgroupid         VARCHAR2(36),
  edittime            DATE,
  status              VARCHAR2(20) not null,
  parent_bus_type_id  VARCHAR2(36),
  bus_type_bigimg     VARCHAR2(4000),
  bus_type_smallimg   VARCHAR2(4000)
)
;
comment on table SER_BUS_TYPE
  is '[0095]��������-��������ҵ�������Ϣ��';
comment on column SER_BUS_TYPE.bus_type_id
  is '��������ҵ���������(�������:UUID)';
comment on column SER_BUS_TYPE.bus_area_code
  is '��������ҵ�����������������';
comment on column SER_BUS_TYPE.bus_type_code
  is '��������ҵ��������(�������:�Զ���)';
comment on column SER_BUS_TYPE.bus_type_name
  is '��������ҵ���������';
comment on column SER_BUS_TYPE.bus_type_sort
  is '��������ҵ��������������';
comment on column SER_BUS_TYPE.bus_type_pingyin
  is '��������ҵ�����ƴ��';
comment on column SER_BUS_TYPE.bus_type_desc
  is '����Ŀ¼ҵ�������������';
comment on column SER_BUS_TYPE.bus_type_bigimgtype
  is '��������ҵ������ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_BUS_TYPE.bus_type_smalltype
  is '��������ҵ�����Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_BUS_TYPE.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_BUS_TYPE.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_BUS_TYPE.createtime
  is '��������';
comment on column SER_BUS_TYPE.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_BUS_TYPE.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_BUS_TYPE.edittime
  is '����޸�����';
comment on column SER_BUS_TYPE.status
  is '��������ҵ�������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_BUS_TYPE.parent_bus_type_id
  is '������������ҵ���������';
comment on column SER_BUS_TYPE.bus_type_bigimg
  is '��������ҵ������ͼ���ļ���ַ';
comment on column SER_BUS_TYPE.bus_type_smallimg
  is '��������ҵ�����Сͼ���ļ���ַ';
alter table SER_BUS_TYPE
  add primary key (BUS_TYPE_ID);

prompt
prompt Creating table SER_CATALOGUE
prompt ============================
prompt
create table SER_CATALOGUE
(
  cata_id                 VARCHAR2(36) not null,
  cata_code               VARCHAR2(36),
  cata_name               VARCHAR2(400) not null,
  cata_pingyin            VARCHAR2(400),
  cata_desc               VARCHAR2(4000),
  cata_sort               NUMBER(16) not null,
  cata_bigimgtype         VARCHAR2(20),
  cata_smalltype          VARCHAR2(20),
  bus_type_id             VARCHAR2(36) not null,
  event_type_id           VARCHAR2(36),
  consumer_type_id        VARCHAR2(36),
  power_type_id           VARCHAR2(36),
  department_id           VARCHAR2(36),
  cata_hand_time_limit    VARCHAR2(100),
  cata_promise_time_limit VARCHAR2(100),
  cata_complaint_tel      VARCHAR2(200),
  cata_is_net             VARCHAR2(20),
  cata_video_url          VARCHAR2(400),
  cata_is_charge          VARCHAR2(200),
  cata_establish          VARCHAR2(4000),
  cata_hand_term          VARCHAR2(4000),
  cata_app_material       VARCHAR2(4000),
  cata_process            VARCHAR2(4000),
  userid                  VARCHAR2(36),
  createtime              DATE,
  groupid                 VARCHAR2(36),
  edituserid              VARCHAR2(36),
  editgroupid             VARCHAR2(36),
  edittime                DATE,
  status                  VARCHAR2(20),
  cata_process_type       VARCHAR2(20),
  cata_url                VARCHAR2(400),
  cata_process_pic        VARCHAR2(4000),
  cata_bigimg             VARCHAR2(4000),
  cata_smallimg           VARCHAR2(4000)
)
;
comment on table SER_CATALOGUE
  is '[0100]��������-��������������Ϣ�� ';
comment on column SER_CATALOGUE.cata_id
  is '������������(�������:UUID)';
comment on column SER_CATALOGUE.cata_code
  is '�����������(�������:�Զ���)';
comment on column SER_CATALOGUE.cata_name
  is '������������';
comment on column SER_CATALOGUE.cata_pingyin
  is '��������ƴ��';
comment on column SER_CATALOGUE.cata_desc
  is '����������������';
comment on column SER_CATALOGUE.cata_sort
  is '�����������������';
comment on column SER_CATALOGUE.cata_bigimgtype
  is '���������ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CATALOGUE.cata_smalltype
  is '��������Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CATALOGUE.bus_type_id
  is '��������ҵ���������';
comment on column SER_CATALOGUE.event_type_id
  is '��������������������';
comment on column SER_CATALOGUE.consumer_type_id
  is '����������������������';
comment on column SER_CATALOGUE.power_type_id
  is '��������������������';
comment on column SER_CATALOGUE.department_id
  is '����������';
comment on column SER_CATALOGUE.cata_hand_time_limit
  is '��������ʱ������';
comment on column SER_CATALOGUE.cata_promise_time_limit
  is '��ŵ����ʱ������';
comment on column SER_CATALOGUE.cata_complaint_tel
  is '�ල�绰';
comment on column SER_CATALOGUE.cata_is_net
  is '�Ƿ���Ҫ���߰������';
comment on column SER_CATALOGUE.cata_video_url
  is '���������������˵����Ƶ�ļ���ַ';
comment on column SER_CATALOGUE.cata_is_charge
  is '�Ƿ��շ�����';
comment on column SER_CATALOGUE.cata_establish
  is '�����������������';
comment on column SER_CATALOGUE.cata_hand_term
  is '��������������������';
comment on column SER_CATALOGUE.cata_app_material
  is '�������������������';
comment on column SER_CATALOGUE.cata_process
  is '�������������������';
comment on column SER_CATALOGUE.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE.createtime
  is '��������';
comment on column SER_CATALOGUE.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_CATALOGUE.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_CATALOGUE.edittime
  is '����޸�����';
comment on column SER_CATALOGUE.status
  is '����������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_CATALOGUE.cata_process_type
  is '��������ҵ���������ͼ�ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CATALOGUE.cata_url
  is '�����������߰�����ʵ�ַ';
comment on column SER_CATALOGUE.cata_process_pic
  is '��������ҵ���������ͼ�ļ���ַ';
comment on column SER_CATALOGUE.cata_bigimg
  is '���������ͼ���ļ���ַ';
comment on column SER_CATALOGUE.cata_smallimg
  is '��������Сͼ���ļ���ַ';
alter table SER_CATALOGUE
  add primary key (CATA_ID);

prompt
prompt Creating table SER_CATALOGUE_ATTACH
prompt ===================================
prompt
create table SER_CATALOGUE_ATTACH
(
  cata_attch_id            VARCHAR2(36) not null,
  cata_attch_code          VARCHAR2(36) not null,
  cata_detail_id           VARCHAR2(36) not null,
  cata_attch_name          VARCHAR2(400) not null,
  cata_attch_pingyin       VARCHAR2(200) not null,
  cata_attch_desc          VARCHAR2(4000) not null,
  cata_attch_sort          NUMBER not null,
  cata_attch_filetype      VARCHAR2(20) not null,
  cata_attch_filesize      NUMBER not null,
  cata_attch_file_location VARCHAR2(200) not null,
  userid                   VARCHAR2(36) not null,
  groupid                  VARCHAR2(36) not null,
  createtime               DATE not null,
  edituserid               VARCHAR2(36),
  editgroupid              VARCHAR2(36),
  edittime                 DATE,
  status                   VARCHAR2(36) not null,
  cata_attch_file_blob     BLOB
)
;
comment on table SER_CATALOGUE_ATTACH
  is '[0102]��������-��������ڸ�����Ϣ��';
comment on column SER_CATALOGUE_ATTACH.cata_attch_id
  is '��������ڸ�������(�������:UUID)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_code
  is '��������ڸ�������(�������:�Զ���)';
comment on column SER_CATALOGUE_ATTACH.cata_detail_id
  is '�����������������(�������:UUID)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_name
  is '��������ڸ�������';
comment on column SER_CATALOGUE_ATTACH.cata_attch_pingyin
  is '��������ڸ���ƴ��';
comment on column SER_CATALOGUE_ATTACH.cata_attch_desc
  is '��������ڸ�����������';
comment on column SER_CATALOGUE_ATTACH.cata_attch_sort
  is '��������ڸ��������';
comment on column SER_CATALOGUE_ATTACH.cata_attch_filetype
  is '��������ڸ����ļ���չ��(����:PNG��JPG��BMP��GIF��DOC��DOCX)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_filesize
  is '��������ڸ����ļ��ߴ�(��λ:byte �ֽ�)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_file_location
  is '��������ڸ����ļ���ַ';
comment on column SER_CATALOGUE_ATTACH.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE_ATTACH.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_CATALOGUE_ATTACH.createtime
  is '��������';
comment on column SER_CATALOGUE_ATTACH.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE_ATTACH.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_CATALOGUE_ATTACH.edittime
  is '����޸�����';
comment on column SER_CATALOGUE_ATTACH.status
  is '��������ڸ�����Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_file_blob
  is '��������ڸ����ļ�';
alter table SER_CATALOGUE_ATTACH
  add primary key (CATA_ATTCH_ID);

prompt
prompt Creating table SER_CATALOGUE_DETAIL
prompt ===================================
prompt
create table SER_CATALOGUE_DETAIL
(
  cata_detail_id         VARCHAR2(36) not null,
  cata_detail_code       VARCHAR2(36),
  cata_id                VARCHAR2(36),
  cata_detail_name       VARCHAR2(400),
  cata_detail_pingyin    VARCHAR2(200),
  cata_detail_desc       VARCHAR2(4000),
  cata_detail_sort       NUMBER,
  cata_detail_bigimgtype VARCHAR2(20),
  cata_detail_smalltype  VARCHAR2(20),
  userid                 VARCHAR2(36),
  groupid                VARCHAR2(36),
  createtime             DATE,
  edituserid             VARCHAR2(36),
  editgroupid            VARCHAR2(36),
  edittime               DATE,
  status                 VARCHAR2(20),
  cata_detail_bigimg     VARCHAR2(4000),
  cata_detail_smallimg   VARCHAR2(4000)
)
;
comment on table SER_CATALOGUE_DETAIL
  is '[0101]��������-���������������Ϣ��';
comment on column SER_CATALOGUE_DETAIL.cata_detail_id
  is '�����������������(�������:UUID)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_code
  is '������������ڱ���(�������:�Զ���)';
comment on column SER_CATALOGUE_DETAIL.cata_id
  is '������������(�������:UUID)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_name
  is '�����������������';
comment on column SER_CATALOGUE_DETAIL.cata_detail_pingyin
  is '�������������ƴ��';
comment on column SER_CATALOGUE_DETAIL.cata_detail_desc
  is '���������������������';
comment on column SER_CATALOGUE_DETAIL.cata_detail_sort
  is '������������������';
comment on column SER_CATALOGUE_DETAIL.cata_detail_bigimgtype
  is '������������ڴ�ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_smalltype
  is '�������������Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CATALOGUE_DETAIL.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE_DETAIL.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_CATALOGUE_DETAIL.createtime
  is '��������';
comment on column SER_CATALOGUE_DETAIL.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_CATALOGUE_DETAIL.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_CATALOGUE_DETAIL.edittime
  is '����޸�����';
comment on column SER_CATALOGUE_DETAIL.status
  is '���������������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_bigimg
  is '������������ڴ�ͼ���ļ���ַ';
comment on column SER_CATALOGUE_DETAIL.cata_detail_smallimg
  is '�������������Сͼ���ļ���ַ';
alter table SER_CATALOGUE_DETAIL
  add primary key (CATA_DETAIL_ID);

prompt
prompt Creating table SER_COLLECT
prompt ==========================
prompt
create table SER_COLLECT
(
  collect_id VARCHAR2(36) not null,
  cata_id    VARCHAR2(36) not null,
  userid     VARCHAR2(36) not null,
  createtime DATE not null,
  status     VARCHAR2(20) not null
)
;
comment on table SER_COLLECT
  is '[0103]��������-���������ղؼ�¼��Ϣ��';
comment on column SER_COLLECT.collect_id
  is '���������ղؼ�¼����(�������:UUID)';
comment on column SER_COLLECT.cata_id
  is '������������(�������:UUID)';
comment on column SER_COLLECT.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_COLLECT.createtime
  is '��������';
comment on column SER_COLLECT.status
  is '���������ղؼ�¼״̬����(1:��Ч��0:��Ч)';
alter table SER_COLLECT
  add primary key (COLLECT_ID);

prompt
prompt Creating table SER_CONSUMER_TYPE
prompt ================================
prompt
create table SER_CONSUMER_TYPE
(
  consumer_type_id         VARCHAR2(36) not null,
  bus_area_code            VARCHAR2(36) not null,
  consumer_type_code       VARCHAR2(36) not null,
  consumer_type_name       VARCHAR2(400) not null,
  consumer_type_sort       NUMBER(16),
  consumer_type_pingyin    VARCHAR2(200),
  consumer_type_desc       VARCHAR2(500),
  consumer_type_bigimgtype VARCHAR2(36),
  consumer_type_smalltype  VARCHAR2(36),
  userid                   VARCHAR2(36) not null,
  groupid                  VARCHAR2(36) not null,
  createtime               DATE not null,
  edituserid               VARCHAR2(36),
  editgroupid              VARCHAR2(36),
  edittime                 DATE,
  status                   VARCHAR2(36) not null,
  consumer_type_bigimg     VARCHAR2(500),
  consumer_type_smallimg   VARCHAR2(500)
)
;
comment on table SER_CONSUMER_TYPE
  is '[0097]��������-�������������������Ϣ��';
comment on column SER_CONSUMER_TYPE.consumer_type_id
  is '���������������������(�������:UUID)';
comment on column SER_CONSUMER_TYPE.bus_area_code
  is '�����������������������������';
comment on column SER_CONSUMER_TYPE.consumer_type_code
  is '��������������������(�������:�Զ���)';
comment on column SER_CONSUMER_TYPE.consumer_type_name
  is '���������������������';
comment on column SER_CONSUMER_TYPE.consumer_type_sort
  is '��������������������������';
comment on column SER_CONSUMER_TYPE.consumer_type_pingyin
  is '�����������������ƴ��';
comment on column SER_CONSUMER_TYPE.consumer_type_desc
  is '�������������������������';
comment on column SER_CONSUMER_TYPE.consumer_type_bigimgtype
  is '������������������ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CONSUMER_TYPE.consumer_type_smalltype
  is '�����������������Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_CONSUMER_TYPE.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_CONSUMER_TYPE.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_CONSUMER_TYPE.createtime
  is '��������';
comment on column SER_CONSUMER_TYPE.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_CONSUMER_TYPE.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_CONSUMER_TYPE.edittime
  is '����޸�����';
comment on column SER_CONSUMER_TYPE.status
  is '�������������������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_CONSUMER_TYPE.consumer_type_bigimg
  is '������������������ͼ���ļ���ַ';
comment on column SER_CONSUMER_TYPE.consumer_type_smallimg
  is '�����������������Сͼ���ļ���ַ';
alter table SER_CONSUMER_TYPE
  add primary key (CONSUMER_TYPE_ID);

prompt
prompt Creating table SER_DEPARTMENT
prompt =============================
prompt
create table SER_DEPARTMENT
(
  department_id        VARCHAR2(36) not null,
  department_address   VARCHAR2(400),
  department_longitude NUMBER(16,4),
  department_latitude  NUMBER(16,4),
  department_tel       VARCHAR2(200) not null,
  department_linkman   VARCHAR2(50) not null,
  userid               VARCHAR2(36),
  createtime           DATE,
  status               VARCHAR2(20),
  department_name      VARCHAR2(400) not null,
  department_img_type  VARCHAR2(20),
  groupid              VARCHAR2(36),
  department_route     VARCHAR2(4000),
  department_work_time VARCHAR2(200),
  department_img       VARCHAR2(4000),
  department_name_abb  VARCHAR2(400),
  department_desc      VARCHAR2(4000)
)
;
comment on table SER_DEPARTMENT
  is '[0099]��������-�������������Ż�����Ϣ��';
comment on column SER_DEPARTMENT.department_id
  is '������������������(�������:UUID)';
comment on column SER_DEPARTMENT.department_address
  is '�������������ŵ�ַ����';
comment on column SER_DEPARTMENT.department_longitude
  is '�������������ŵ�ַ����';
comment on column SER_DEPARTMENT.department_latitude
  is '�������������ŵ�ַγ��';
comment on column SER_DEPARTMENT.department_tel
  is '����������������ϵ�绰';
comment on column SER_DEPARTMENT.department_linkman
  is '����������������ϵ������';
comment on column SER_DEPARTMENT.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_DEPARTMENT.createtime
  is '��������';
comment on column SER_DEPARTMENT.status
  is '����������������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_DEPARTMENT.department_name
  is '������������������';
comment on column SER_DEPARTMENT.department_img_type
  is '���������������⾰��Ƭ�ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_DEPARTMENT.groupid
  is '��������������������������(�û������еĻ���)';
comment on column SER_DEPARTMENT.department_route
  is '�������������Ž�ͨ·������';
comment on column SER_DEPARTMENT.department_work_time
  is '�������������Ź���ʱ������';
comment on column SER_DEPARTMENT.department_img
  is '���������������⾰��Ƭ�ļ���ַ';
comment on column SER_DEPARTMENT.department_name_abb
  is '�������������ż��';
comment on column SER_DEPARTMENT.department_desc
  is '�������������ż������';
alter table SER_DEPARTMENT
  add primary key (DEPARTMENT_ID);

prompt
prompt Creating table SER_DESK
prompt =======================
prompt
create table SER_DESK
(
  desk_id    VARCHAR2(36) not null,
  desk_code  VARCHAR2(36) not null,
  cata_id    VARCHAR2(36) not null,
  userid     VARCHAR2(36) not null,
  createtime DATE not null,
  status     VARCHAR2(20) not null,
  desk_desc  VARCHAR2(4000),
  yw_id      VARCHAR2(200),
  version    VARCHAR2(20)
)
;
comment on table SER_DESK
  is '[0105]��������-��������ҵ�������Ϣ��';
comment on column SER_DESK.desk_id
  is '��������ҵ���������(�������:UUID)';
comment on column SER_DESK.desk_code
  is '��������ҵ��������(�������:�Զ���)';
comment on column SER_DESK.cata_id
  is '������������(�������:UUID)';
comment on column SER_DESK.userid
  is '�Ͷ��߸�������(�������:UUID)';
comment on column SER_DESK.createtime
  is '��������ҵ�����ʼʱ��';
comment on column SER_DESK.status
  is '��������ҵ�������Ч״̬����(1 ���� 2���� 3��ᣩ';
comment on column SER_DESK.desk_desc
  is '��������ҵ�������������';
comment on column SER_DESK.yw_id
  is '��������ҵ�������ϵͳҵ����';
comment on column SER_DESK.version
  is '�汾��';
alter table SER_DESK
  add primary key (DESK_ID);

prompt
prompt Creating table SER_EVENT_TYPE
prompt =============================
prompt
create table SER_EVENT_TYPE
(
  event_type_id         VARCHAR2(36) not null,
  bus_area_code         VARCHAR2(36) not null,
  event_type_code       VARCHAR2(36) not null,
  event_type_name       VARCHAR2(400) not null,
  event_type_sort       NUMBER(16),
  event_type_pingyin    VARCHAR2(200),
  event_type_desc       VARCHAR2(4000),
  event_type_bigimgtype VARCHAR2(20),
  event_type_smalltype  VARCHAR2(20),
  userid                VARCHAR2(36) not null,
  groupid               VARCHAR2(36) not null,
  createtime            DATE not null,
  edituserid            VARCHAR2(36),
  editgroupid           VARCHAR2(36),
  edittime              DATE,
  status                VARCHAR2(20) not null,
  parent_event_type_id  VARCHAR2(36),
  event_type_bigimg     VARCHAR2(4000),
  event_type_smallimg   VARCHAR2(4000)
)
;
comment on table SER_EVENT_TYPE
  is '[0096]��������-�����������ͷ�����Ϣ��';
comment on column SER_EVENT_TYPE.event_type_id
  is '�����������ͷ�������(�������:UUID)';
comment on column SER_EVENT_TYPE.bus_area_code
  is '�����������ͷ���������������';
comment on column SER_EVENT_TYPE.event_type_code
  is '�����������ͷ������(�������:�Զ���)';
comment on column SER_EVENT_TYPE.event_type_name
  is '�����������ͷ�������';
comment on column SER_EVENT_TYPE.event_type_sort
  is '�����������ͷ������������';
comment on column SER_EVENT_TYPE.event_type_pingyin
  is '�����������ͷ���ƴ��';
comment on column SER_EVENT_TYPE.event_type_desc
  is '�����������ͷ�����������';
comment on column SER_EVENT_TYPE.event_type_bigimgtype
  is '�����������ͷ����ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_EVENT_TYPE.event_type_smalltype
  is '�����������ͷ���Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_EVENT_TYPE.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_EVENT_TYPE.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_EVENT_TYPE.createtime
  is '��������';
comment on column SER_EVENT_TYPE.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_EVENT_TYPE.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_EVENT_TYPE.edittime
  is '����޸�����';
comment on column SER_EVENT_TYPE.status
  is '�����������ͷ�����Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_EVENT_TYPE.parent_event_type_id
  is '���������������ͷ�������';
comment on column SER_EVENT_TYPE.event_type_bigimg
  is '�����������ͷ����ͼ���ļ���ַ';
comment on column SER_EVENT_TYPE.event_type_smallimg
  is '�����������ͷ���Сͼ���ļ���ַ';
alter table SER_EVENT_TYPE
  add primary key (EVENT_TYPE_ID);

prompt
prompt Creating table SER_POWER_TYPE
prompt =============================
prompt
create table SER_POWER_TYPE
(
  power_type_id         VARCHAR2(36) not null,
  bus_area_code         VARCHAR2(36) not null,
  power_type_code       VARCHAR2(36) not null,
  power_type_name       VARCHAR2(400) not null,
  power_type_sort       NUMBER(16),
  power_type_pingyin    VARCHAR2(200),
  power_type_desc       VARCHAR2(4000),
  power_type_bigimgtype VARCHAR2(20),
  power_type_smalltype  VARCHAR2(20),
  userid                VARCHAR2(36) not null,
  groupid               VARCHAR2(36) not null,
  createtime            DATE not null,
  edituserid            VARCHAR2(36),
  editgroupid           VARCHAR2(36),
  edittime              DATE,
  status                VARCHAR2(20) not null,
  power_type_bigimg     VARCHAR2(4000),
  power_type_smallimg   VARCHAR2(4000)
)
;
comment on table SER_POWER_TYPE
  is '[0098]��������-���������������ͷ�����Ϣ��';
comment on column SER_POWER_TYPE.power_type_id
  is '���������������ͷ�������(�������:UUID)';
comment on column SER_POWER_TYPE.bus_area_code
  is '���������������ͷ���������������';
comment on column SER_POWER_TYPE.power_type_code
  is '���������������ͷ������(�������:�Զ���)';
comment on column SER_POWER_TYPE.power_type_name
  is '���������������ͷ�������';
comment on column SER_POWER_TYPE.power_type_sort
  is '���������������ͷ������������';
comment on column SER_POWER_TYPE.power_type_pingyin
  is '���������������ͷ���ƴ��';
comment on column SER_POWER_TYPE.power_type_desc
  is '���������������ͷ�����������';
comment on column SER_POWER_TYPE.power_type_bigimgtype
  is '���������������ͷ����ͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_POWER_TYPE.power_type_smalltype
  is '���������������ͷ���Сͼ���ļ���չ��(����:PNG��JPG��BMP��GIF)';
comment on column SER_POWER_TYPE.userid
  is '�����������˻�����(�û������е���Ա)';
comment on column SER_POWER_TYPE.groupid
  is '������������������(�û������еĻ���)';
comment on column SER_POWER_TYPE.createtime
  is '��������';
comment on column SER_POWER_TYPE.edituserid
  is '����޸��������˻�����(�û������е���Ա)';
comment on column SER_POWER_TYPE.editgroupid
  is '����޸���������������(�û������еĻ���)';
comment on column SER_POWER_TYPE.edittime
  is '����޸�����';
comment on column SER_POWER_TYPE.status
  is '���������������ͷ�����Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_POWER_TYPE.power_type_bigimg
  is '���������������ͷ����ͼ���ļ���ַ';
comment on column SER_POWER_TYPE.power_type_smallimg
  is '���������������ͷ���Сͼ���ļ���ַ';
alter table SER_POWER_TYPE
  add primary key (POWER_TYPE_ID);

prompt
prompt Creating table SER_SCORE
prompt ========================
prompt
create table SER_SCORE
(
  score_id              VARCHAR2(36) not null,
  cata_id               VARCHAR2(36) not null,
  score_level           VARCHAR2(20) not null,
  userid                VARCHAR2(36) not null,
  createtime            DATE not null,
  status                VARCHAR2(20) not null,
  score_desc            VARCHAR2(4000),
  score_feedback_desc   VARCHAR2(4000),
  score_feedback_userid VARCHAR2(36),
  score_feedback_time   DATE
)
;
comment on table SER_SCORE
  is '[0106]��������-��������ҵ�����������Ϣ��';
comment on column SER_SCORE.score_id
  is '��������ҵ�������������(�������:UUID)';
comment on column SER_SCORE.cata_id
  is '������������(�������:UUID)';
comment on column SER_SCORE.score_level
  is '��������ҵ��������۴���';
comment on column SER_SCORE.userid
  is '�Ͷ��߸�������(�������:UUID)';
comment on column SER_SCORE.createtime
  is 'ҵ���������ʱ��';
comment on column SER_SCORE.status
  is '��������ҵ�����������Ч״̬����(1:��Ч��0:��Ч)';
comment on column SER_SCORE.score_desc
  is '��������ҵ�����������������';
comment on column SER_SCORE.score_feedback_desc
  is '�������������ŷ�����������';
comment on column SER_SCORE.score_feedback_userid
  is '�������������ŷ�����Ա����';
comment on column SER_SCORE.score_feedback_time
  is '�������������ŷ���ʱ��';
alter table SER_SCORE
  add primary key (SCORE_ID);

prompt
prompt Creating table SYS_API_CHANNEL
prompt ==============================
prompt
create table SYS_API_CHANNEL
(
  channel_id       VARCHAR2(36) not null,
  channel_code     VARCHAR2(36),
  channel_name     VARCHAR2(100),
  channel_type     VARCHAR2(36),
  channel_describe VARCHAR2(100),
  channel_icon     VARCHAR2(100),
  channel_status   VARCHAR2(10),
  userid           VARCHAR2(100),
  addtime          DATE,
  auditid          VARCHAR2(100),
  audittime        DATE,
  channel_appkey   VARCHAR2(100)
)
;
comment on table SYS_API_CHANNEL
  is '�ӿڹ���֮����������Ϣ��';
comment on column SYS_API_CHANNEL.channel_id
  is '����������Ϣ����,���ɹ���uuid';
comment on column SYS_API_CHANNEL.channel_code
  is '��������������QD+����';
comment on column SYS_API_CHANNEL.channel_name
  is '��������';
comment on column SYS_API_CHANNEL.channel_type
  is '��������';
comment on column SYS_API_CHANNEL.channel_describe
  is '��������';
comment on column SYS_API_CHANNEL.channel_icon
  is '����ͼ��';
comment on column SYS_API_CHANNEL.channel_status
  is '����״̬(����ˡ�������ע��)';
comment on column SYS_API_CHANNEL.userid
  is '�����˱���';
comment on column SYS_API_CHANNEL.addtime
  is '����ʱ��';
comment on column SYS_API_CHANNEL.auditid
  is '�����';
comment on column SYS_API_CHANNEL.audittime
  is '���ʱ��';
comment on column SYS_API_CHANNEL.channel_appkey
  is '����KEY';
alter table SYS_API_CHANNEL
  add constraint PK_SYS_API_CHANNEL primary key (CHANNEL_ID);

prompt
prompt Creating table SYS_API_CHANNEL_INTERFACE
prompt ========================================
prompt
create table SYS_API_CHANNEL_INTERFACE
(
  channel_interface_id VARCHAR2(36) not null,
  channel_id           VARCHAR2(36),
  interface_id         VARCHAR2(36),
  userid               VARCHAR2(100),
  addtime              DATE,
  isvalid              VARCHAR2(36)
)
;
comment on table SYS_API_CHANNEL_INTERFACE
  is '�ӿڹ���֮������ӿڹ�����';
comment on column SYS_API_CHANNEL_INTERFACE.channel_interface_id
  is '��������(uuid)';
comment on column SYS_API_CHANNEL_INTERFACE.channel_id
  is '�������';
comment on column SYS_API_CHANNEL_INTERFACE.interface_id
  is '�ӿڱ��';
comment on column SYS_API_CHANNEL_INTERFACE.userid
  is '�����˱���';
comment on column SYS_API_CHANNEL_INTERFACE.addtime
  is '����ʱ��';
comment on column SYS_API_CHANNEL_INTERFACE.isvalid
  is '�Ƿ���Ч';
alter table SYS_API_CHANNEL_INTERFACE
  add constraint PK_SYS_API_CHANNEL_INTERFACE primary key (CHANNEL_INTERFACE_ID);

prompt
prompt Creating table SYS_API_INTERFACE
prompt ================================
prompt
create table SYS_API_INTERFACE
(
  interface_id          VARCHAR2(36) not null,
  interface_code        VARCHAR2(100) not null,
  interface_url         VARCHAR2(200) not null,
  interface_type        VARCHAR2(10) not null,
  interface_name        VARCHAR2(200) not null,
  isoffical             VARCHAR2(10) not null,
  interface_network     VARCHAR2(10) not null,
  interface_potocol     VARCHAR2(100),
  interface_status      VARCHAR2(10),
  userid                VARCHAR2(100),
  addtime               DATE,
  auditid               VARCHAR2(100),
  audittime             DATE,
  interface_detail_type VARCHAR2(10),
  par_interface_id      VARCHAR2(36)
)
;
comment on table SYS_API_INTERFACE
  is '�������-���������Ϣ��';
comment on column SYS_API_INTERFACE.interface_id
  is '���������Ϣ���(uuid)';
comment on column SYS_API_INTERFACE.interface_code
  is '���������Ϣ�������ɹ���JK+����';
comment on column SYS_API_INTERFACE.interface_url
  is '�����ַ';
comment on column SYS_API_INTERFACE.interface_type
  is '�����������';
comment on column SYS_API_INTERFACE.interface_name
  is '��������';
comment on column SYS_API_INTERFACE.isoffical
  is '�Ƿ���ʽ�ӿڷ���';
comment on column SYS_API_INTERFACE.interface_network
  is '����֧����������(������������)';
comment on column SYS_API_INTERFACE.interface_potocol
  is '����Э��(rest��webservice��socket��hessian��dubbo)';
comment on column SYS_API_INTERFACE.interface_status
  is '����״̬(����ˡ�������ע��)';
comment on column SYS_API_INTERFACE.userid
  is '�����˱���';
comment on column SYS_API_INTERFACE.addtime
  is '����ʱ��';
comment on column SYS_API_INTERFACE.auditid
  is '�����';
comment on column SYS_API_INTERFACE.audittime
  is '���ʱ��';
comment on column SYS_API_INTERFACE.interface_detail_type
  is '����С������';
comment on column SYS_API_INTERFACE.par_interface_id
  is '�����������Ϣ���(uuid)';
alter table SYS_API_INTERFACE
  add constraint PK_S_API_INTERFACE primary key (INTERFACE_ID);

prompt
prompt Creating table SYS_BUS_FILE_RECORD
prompt ==================================
prompt
create table SYS_BUS_FILE_RECORD
(
  bus_uuid      VARCHAR2(36) not null,
  file_uuid     VARCHAR2(36) not null,
  file_bus_id   VARCHAR2(36) not null,
  file_bus_type VARCHAR2(36) not null,
  bus_status    VARCHAR2(36),
  bus_addtime   DATE
)
;
comment on table SYS_BUS_FILE_RECORD
  is 'ϵͳ����֮ҵ���ļ���¼��';
comment on column SYS_BUS_FILE_RECORD.bus_uuid
  is 'ҵ���ļ���¼����';
comment on column SYS_BUS_FILE_RECORD.file_uuid
  is '�ļ��ϴ���¼����';
comment on column SYS_BUS_FILE_RECORD.file_bus_id
  is '�ļ�����ҵ����';
comment on column SYS_BUS_FILE_RECORD.file_bus_type
  is '�ļ�����ҵ��ҵ������';
comment on column SYS_BUS_FILE_RECORD.bus_status
  is '�ļ��ϴ�״̬';
comment on column SYS_BUS_FILE_RECORD.bus_addtime
  is 'ҵ����ʱ��';
alter table SYS_BUS_FILE_RECORD
  add constraint PK_SYS_BUS_FILE_RECORD primary key (BUS_UUID);

prompt
prompt Creating table SYS_ERRORLOG
prompt ===========================
prompt
create table SYS_ERRORLOG
(
  logid         VARCHAR2(36) not null,
  logtime       DATE,
  stackmsg      CLOB,
  message       VARCHAR2(2000),
  exceptiontype VARCHAR2(100),
  usergent      VARCHAR2(1000),
  ipaddr        VARCHAR2(200),
  referer       VARCHAR2(1000),
  url           VARCHAR2(1000),
  userid        VARCHAR2(32),
  cookie        VARCHAR2(1000)
)
;
comment on table SYS_ERRORLOG
  is 'ϵͳ����֮��վ�����쳣��־';
comment on column SYS_ERRORLOG.logid
  is '��־���(uuid)';
comment on column SYS_ERRORLOG.logtime
  is '����ʱ��';
comment on column SYS_ERRORLOG.stackmsg
  is '�쳣ջ��Ϣ';
comment on column SYS_ERRORLOG.message
  is '��־����';
comment on column SYS_ERRORLOG.exceptiontype
  is '�쳣����';
comment on column SYS_ERRORLOG.usergent
  is 'user-agent';
comment on column SYS_ERRORLOG.ipaddr
  is '�ͻ���ip��ַ';
comment on column SYS_ERRORLOG.referer
  is 'refer';
comment on column SYS_ERRORLOG.url
  is '����ĵ�ַ';
comment on column SYS_ERRORLOG.userid
  is '��ǰ������Աid';
comment on column SYS_ERRORLOG.cookie
  is 'cookie';
alter table SYS_ERRORLOG
  add constraint PK_SYS_ERRORLOG primary key (LOGID);

prompt
prompt Creating table SYS_EXCELTYPE
prompt ============================
prompt
create table SYS_EXCELTYPE
(
  businesstype VARCHAR2(20) not null,
  typename     VARCHAR2(100) not null,
  vsbeanname   VARCHAR2(100) not null,
  mincolumns   VARCHAR2(100)
)
;
comment on table SYS_EXCELTYPE
  is 'ϵͳ����֮excel���ݵ���״̬���ϴ�EXCEL�ļ�ҵ�����Ͳ�����';
comment on column SYS_EXCELTYPE.businesstype
  is 'excel�ϴ�ҵ�����ͱ��';
comment on column SYS_EXCELTYPE.typename
  is 'excel�ϴ�ҵ����������';
comment on column SYS_EXCELTYPE.vsbeanname
  is 'excel��������ҵ������ ��fuPingExcelImportService';
comment on column SYS_EXCELTYPE.mincolumns
  is 'excel�����п�� �� 6';
alter table SYS_EXCELTYPE
  add constraint PK_SYS_EXCELTYPE primary key (BUSINESSTYPE);

prompt
prompt Creating table SYS_EXCEL_BATCH
prompt ==============================
prompt
create table SYS_EXCEL_BATCH
(
  excel_batch_id            VARCHAR2(36) not null,
  excel_batch_number        VARCHAR2(36),
  excel_batch_total_count   NUMBER(10),
  excel_batch_error_count   NUMBER(10),
  excel_batch_begin_time    DATE,
  excel_batch_end_time      DATE,
  excel_batch_cost          NUMBER(10),
  excel_batch_file_path     VARCHAR2(500),
  excel_batch_file_length   NUMBER(10),
  excel_batch_excel_type    VARCHAR2(36),
  excel_batch_aae011        VARCHAR2(20),
  excel_batch_status        VARCHAR2(3),
  excel_batch_data_status   NUMBER(3),
  excel_batch_rt_code       VARCHAR2(20),
  excel_batch_rt_msg        VARCHAR2(200),
  excel_batch_file_name     VARCHAR2(200),
  excel_error_file_path     VARCHAR2(200),
  excel_error_file_download VARCHAR2(200)
)
;
comment on table SYS_EXCEL_BATCH
  is 'ϵͳ����֮excel���ݵ���״̬��';
comment on column SYS_EXCEL_BATCH.excel_batch_id
  is 'excel����״̬����(uuid)';
comment on column SYS_EXCEL_BATCH.excel_batch_number
  is 'excel�������κš����ɹ���������ʱ�������';
comment on column SYS_EXCEL_BATCH.excel_batch_total_count
  is 'excel������������';
comment on column SYS_EXCEL_BATCH.excel_batch_error_count
  is 'excel�������ݴ�������';
comment on column SYS_EXCEL_BATCH.excel_batch_begin_time
  is 'excel�������ݿ�ʼʱ��';
comment on column SYS_EXCEL_BATCH.excel_batch_end_time
  is 'excel�������ݽ���ʱ��';
comment on column SYS_EXCEL_BATCH.excel_batch_cost
  is 'excel�������ݺķ�ʱ��(�룩';
comment on column SYS_EXCEL_BATCH.excel_batch_file_path
  is 'excel�����ļ�·��';
comment on column SYS_EXCEL_BATCH.excel_batch_file_length
  is 'excel�����ļ���С';
comment on column SYS_EXCEL_BATCH.excel_batch_excel_type
  is 'excel��������';
comment on column SYS_EXCEL_BATCH.excel_batch_aae011
  is 'excel���뾭����';
comment on column SYS_EXCEL_BATCH.excel_batch_status
  is 'excel����״̬���벽��(-1Ĭ��״̬ 0ת��xlsx 1������ʱ�� 2������ʱ�� 3������� 4����ʧ��)';
comment on column SYS_EXCEL_BATCH.excel_batch_data_status
  is 'excel��������״̬(0-100)';
comment on column SYS_EXCEL_BATCH.excel_batch_rt_code
  is 'excel��������У���Ƿ�ɹ�';
comment on column SYS_EXCEL_BATCH.excel_batch_rt_msg
  is 'excel��������У�����ԭ��';
comment on column SYS_EXCEL_BATCH.excel_batch_file_name
  is 'excel�����ļ�ԭ�ļ���';
comment on column SYS_EXCEL_BATCH.excel_error_file_path
  is 'excel�����ļ��ļ�·��';
comment on column SYS_EXCEL_BATCH.excel_error_file_download
  is 'excel�����ļ��ļ�����״��0�������� 1���ɳɹ������� 2 ����ʧ�ܣ�';
alter table SYS_EXCEL_BATCH
  add constraint PK_SYS_EXCEL_BATCH primary key (EXCEL_BATCH_ID);

prompt
prompt Creating table SYS_FILE_RECORD
prompt ==============================
prompt
create table SYS_FILE_RECORD
(
  file_uuid     VARCHAR2(36) not null,
  file_name     VARCHAR2(200),
  file_length   VARCHAR2(32),
  file_addtime  DATE,
  file_path     VARCHAR2(200),
  file_status   VARCHAR2(32),
  file_md5      VARCHAR2(32),
  file_type     VARCHAR2(32),
  file_bus_id   VARCHAR2(32),
  file_bus_type VARCHAR2(32)
)
;
comment on table SYS_FILE_RECORD
  is 'ϵͳ����֮�ļ��ϴ���¼��';
comment on column SYS_FILE_RECORD.file_uuid
  is '�ļ��ϴ���¼����(uuid)';
comment on column SYS_FILE_RECORD.file_name
  is '�ļ�����';
comment on column SYS_FILE_RECORD.file_length
  is '�ļ���С��λ(byte)';
comment on column SYS_FILE_RECORD.file_addtime
  is '�ļ��ϴ�ʱ��';
comment on column SYS_FILE_RECORD.file_path
  is '�ļ��ϴ���ɫ·��';
comment on column SYS_FILE_RECORD.file_status
  is '�ļ�״̬(0��Ч 1��Ч���ͨ��)';
comment on column SYS_FILE_RECORD.file_md5
  is '�ļ�md5,�����ж��Ƿ��ظ��ϴ�';
comment on column SYS_FILE_RECORD.file_type
  is '�ļ�����';
comment on column SYS_FILE_RECORD.file_bus_id
  is '�ļ�����ҵ����';
comment on column SYS_FILE_RECORD.file_bus_type
  is '�ļ�����ҵ��ҵ������';
alter table SYS_FILE_RECORD
  add constraint PK_SYS_FILE_RECORD primary key (FILE_UUID);

prompt
prompt Creating table SYS_GRID
prompt ================