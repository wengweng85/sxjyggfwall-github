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
prompt =======================
prompt
create table SYS_GRID
(
  abz182     VARCHAR2(20) not null,
  aab301     VARCHAR2(20),
  ab_bf022   VARCHAR2(100),
  abz181     VARCHAR2(20),
  aae005     VARCHAR2(30),
  ab_bf023   VARCHAR2(100),
  ab_bf026   VARCHAR2(3),
  ab_bf027   NUMBER(8),
  aae100     VARCHAR2(3),
  aae006     VARCHAR2(100),
  aae004     VARCHAR2(20),
  ab_bf024   VARCHAR2(100),
  abf013     NUMBER(8),
  abf014     NUMBER(8),
  ab_bf025   VARCHAR2(20),
  abf057     VARCHAR2(20),
  ab_bf023_1 VARCHAR2(300),
  isgrid     VARCHAR2(3)
)
;
comment on table SYS_GRID
  is 'ҵ���֮������Ϣ��';
comment on column SYS_GRID.abz182
  is '������';
comment on column SYS_GRID.aab301
  is '��������-��Ӧs_group��groupid';
comment on column SYS_GRID.ab_bf022
  is '�ϼ�������';
comment on column SYS_GRID.abz181
  is '��������';
comment on column SYS_GRID.aae005
  is '��ϵ�绰';
comment on column SYS_GRID.ab_bf023
  is '��������';
comment on column SYS_GRID.ab_bf026
  is '���񼶱�';
comment on column SYS_GRID.ab_bf027
  is 'רί��';
comment on column SYS_GRID.aae100
  is '��Ч��־';
comment on column SYS_GRID.aae006
  is '��ϵ��ַ';
comment on column SYS_GRID.aae004
  is '��ϵ��';
comment on column SYS_GRID.ab_bf024
  is '��ط�Χ';
comment on column SYS_GRID.abf013
  is '����';
comment on column SYS_GRID.abf014
  is '������Ա��';
comment on column SYS_GRID.ab_bf025
  is '��������';
comment on column SYS_GRID.abf057
  is '������';
comment on column SYS_GRID.ab_bf023_1
  is '�������ƣ���λ�ã�';
comment on column SYS_GRID.isgrid
  is '�Ƿ�������';
create index IDX_S_GRID on SYS_GRID (AB_BF022);
alter table SYS_GRID
  add constraint PK_SYS_GRID primary key (ABZ182);

prompt
prompt Creating table SYS_GROUP_TABLE
prompt ==============================
prompt
create table SYS_GROUP_TABLE
(
  groupid      VARCHAR2(32) not null,
  description  VARCHAR2(500),
  parentid     VARCHAR2(32),
  org          VARCHAR2(8),
  districtcode VARCHAR2(32),
  license      VARCHAR2(20),
  groupname    VARCHAR2(100) not null,
  principal    VARCHAR2(32),
  shortname    VARCHAR2(50),
  address      VARCHAR2(150),
  tel          VARCHAR2(30),
  linkman      VARCHAR2(30),
  grouptype    VARCHAR2(3),
  chargedept   VARCHAR2(50),
  otherinfo    VARCHAR2(2000),
  owner        VARCHAR2(32),
  status       CHAR(1),
  createdate   DATE,
  grouplevel   VARCHAR2(32)
)
;
comment on table SYS_GROUP_TABLE
  is 'ϵͳ����֮������Ϣ��';
comment on column SYS_GROUP_TABLE.groupid
  is '����ID';
comment on column SYS_GROUP_TABLE.description
  is '�û�������';
comment on column SYS_GROUP_TABLE.parentid
  is '�ϼ��ṹID';
comment on column SYS_GROUP_TABLE.org
  is 'ϵͳ��������';
comment on column SYS_GROUP_TABLE.districtcode
  is '��������';
comment on column SYS_GROUP_TABLE.license
  is '������֯��������';
comment on column SYS_GROUP_TABLE.groupname
  is '�û�������';
comment on column SYS_GROUP_TABLE.principal
  is '����������';
comment on column SYS_GROUP_TABLE.shortname
  is '���';
comment on column SYS_GROUP_TABLE.address
  is '��ַ';
comment on column SYS_GROUP_TABLE.tel
  is '�绰';
comment on column SYS_GROUP_TABLE.linkman
  is '��ϵ��';
comment on column SYS_GROUP_TABLE.grouptype
  is '����������(1�������� 2��������)';
comment on column SYS_GROUP_TABLE.chargedept
  is '���ܲ���';
comment on column SYS_GROUP_TABLE.otherinfo
  is '������Ϣ';
comment on column SYS_GROUP_TABLE.owner
  is '������';
comment on column SYS_GROUP_TABLE.status
  is '״̬';
comment on column SYS_GROUP_TABLE.createdate
  is '����ʱ��';
comment on column SYS_GROUP_TABLE.grouplevel
  is '�����ȼ�(1ʡ��2�С�3���� 4��ֵ� 5������)';
alter table SYS_GROUP_TABLE
  add constraint PK_SYS_GROUP primary key (GROUPID);

prompt
prompt Creating table SYS_LOG
prompt ======================
prompt
create table SYS_LOG
(
  logid         VARCHAR2(36) not null,
  logtype       VARCHAR2(20),
  message       VARCHAR2(2000),
  logtime       DATE,
  cost          VARCHAR2(100),
  stackmsg      CLOB,
  exceptiontype VARCHAR2(100),
  usergent      VARCHAR2(1000) not null,
  ipaddr        VARCHAR2(200) not null,
  referer       VARCHAR2(1000),
  url           VARCHAR2(1000) not null,
  userid        VARCHAR2(36),
  cookie        VARCHAR2(1000),
  appkey        VARCHAR2(1000),
  method        VARCHAR2(20) not null,
  success       VARCHAR2(1000),
  responsemsg   CLOB,
  queryparam    CLOB,
  token         VARCHAR2(4000),
  interfacetype VARCHAR2(20)
)
;
comment on table SYS_LOG
  is '��ҵ��������ҵ���֮�ӿڷ�����־��¼��';
comment on column SYS_LOG.logid
  is '��־���(uuid)';
comment on column SYS_LOG.logtype
  is '��־�������ʹ���';
comment on column SYS_LOG.message
  is '��־����';
comment on column SYS_LOG.logtime
  is '����ʱ��';
comment on column SYS_LOG.cost
  is '����ķ�ʱ��(����)';
comment on column SYS_LOG.stackmsg
  is '�쳣ջ��Ϣ';
comment on column SYS_LOG.exceptiontype
  is '�쳣��������';
comment on column SYS_LOG.usergent
  is '������Ϣ';
comment on column SYS_LOG.ipaddr
  is '�ͻ���ip��ַ';
comment on column SYS_LOG.referer
  is '��ַ��Դ';
comment on column SYS_LOG.url
  is '����ĵ�ַ';
comment on column SYS_LOG.userid
  is '��ǰ������Ա���';
comment on column SYS_LOG.cookie
  is '״̬��';
comment on column SYS_LOG.appkey
  is '�ӿ�key';
comment on column SYS_LOG.method
  is '���󷽷���������';
comment on column SYS_LOG.success
  is '�����Ƿ�ɹ�����';
comment on column SYS_LOG.responsemsg
  is '������Ϣ';
comment on column SYS_LOG.queryparam
  is '���������Ϣ';
comment on column SYS_LOG.token
  is '����jwt��֤��';
comment on column SYS_LOG.interfacetype
  is '�ӿ����ʹ���';
alter table SYS_LOG
  add constraint PK_SYS_LOG primary key (LOGID);

prompt
prompt Creating table SYS_LOGININF
prompt ===========================
prompt
create table SYS_LOGININF
(
  loginhash VARCHAR2(32),
  logintime DATE,
  ip        VARCHAR2(100),
  usergent  VARCHAR2(1000),
  sessionid VARCHAR2(100)
)
;
comment on table SYS_LOGININF
  is 'ϵͳ����֮��¼״̬��¼��';
comment on column SYS_LOGININF.loginhash
  is '��¼��Ϣhash��Ϣsessionid+ip+agent';
comment on column SYS_LOGININF.logintime
  is '��¼ʱ��';
comment on column SYS_LOGININF.ip
  is '�û�ip';
comment on column SYS_LOGININF.usergent
  is 'usergent';
comment on column SYS_LOGININF.sessionid
  is 'sessionid';

prompt
prompt Creating table SYS_PARAM
prompt ========================
prompt
create table SYS_PARAM
(
  paramid      VARCHAR2(36) not null,
  paramtype    VARCHAR2(50),
  paramvalue   VARCHAR2(200),
  paramname    VARCHAR2(200),
  paramisvalid VARCHAR2(3)
)
;
comment on table SYS_PARAM
  is 'ϵͳ����֮�������ñ�';
comment on column SYS_PARAM.paramid
  is '�������(uuid)';
comment on column SYS_PARAM.paramtype
  is '��������';
comment on column SYS_PARAM.paramvalue
  is '����ֵ';
comment on column SYS_PARAM.paramname
  is '��������˵��';
comment on column SYS_PARAM.paramisvalid
  is '�����Ƿ���Ч';
alter table SYS_PARAM
  add constraint PK_SYS_PARAM primary key (PARAMID);

prompt
prompt Creating table SYS_PERMISSION
prompt =============================
prompt
create table SYS_PERMISSION
(
  permissionid  VARCHAR2(36) not null,
  permname      VARCHAR2(100) not null,
  permtype      VARCHAR2(100) not null,
  url           VARCHAR2(1000),
  parentid      VARCHAR2(32),
  enabled       VARCHAR2(3),
  sortnum       VARCHAR2(32),
  permdescribe  VARCHAR2(200),
  permcode      VARCHAR2(200),
  updatetime    DATE,
  iconcss       VARCHAR2(100),
  deleteable    VARCHAR2(10),
  isblanktarget VARCHAR2(10)
)
;
comment on table SYS_PERMISSION
  is 'ϵͳ����֮����ģ�����ñ�';
comment on column SYS_PERMISSION.permissionid
  is 'Ȩ�ޱ���,��Ź���uuid ';
comment on column SYS_PERMISSION.permname
  is 'Ȩ������';
comment on column SYS_PERMISSION.permtype
  is 'Ȩ������(1 �ڵ� 2Ҷ�� 3 ��ť)';
comment on column SYS_PERMISSION.url
  is 'Ҷ�ӽ���Ӧ����url(��Ե�ַ)';
comment on column SYS_PERMISSION.parentid
  is '��Ȩ�ޱ��';
comment on column SYS_PERMISSION.enabled
  is '�Ƿ���Ч��־';
comment on column SYS_PERMISSION.sortnum
  is '����ҳ��';
comment on column SYS_PERMISSION.permdescribe
  is 'Ȩ������';
comment on column SYS_PERMISSION.permcode
  is 'Ȩ�ޱ���';
comment on column SYS_PERMISSION.updatetime
  is 'Ȩ��������ʱ��';
comment on column SYS_PERMISSION.iconcss
  is 'ͼ����ʽ';
comment on column SYS_PERMISSION.deleteable
  is '�Ƿ����ɾ��(����ԱȨ�޲ſ���)';
comment on column SYS_PERMISSION.isblanktarget
  is '�򿪴��ڷ�ʽ(_blank)';
alter table SYS_PERMISSION
  add constraint PK_SYS_PERMISSION_K primary key (PERMISSIONID);

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table SYS_ROLE
(
  roleid       VARCHAR2(36) not null,
  rolename     VARCHAR2(200),
  roledescribe VARCHAR2(200),
  enabled      VARCHAR2(20),
  rolecode     VARCHAR2(200),
  updatetime   DATE
)
;
comment on table SYS_ROLE
  is 'ϵͳ����֮�û���ɫ��Ϣ�� ';
comment on column SYS_ROLE.roleid
  is '��ɫ��ű�Ź���uuid';
comment on column SYS_ROLE.rolename
  is '��ɫ����';
comment on column SYS_ROLE.roledescribe
  is '��ɫ����';
comment on column SYS_ROLE.enabled
  is '�Ƿ���Ч��־';
comment on column SYS_ROLE.rolecode
  is '��ɫ����';
comment on column SYS_ROLE.updatetime
  is '������ʱ��';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE_K primary key (ROLEID);

prompt
prompt Creating table SYS_ROLE_PERMISSION
prompt ==================================
prompt
create table SYS_ROLE_PERMISSION
(
  id           VARCHAR2(32) not null,
  roleid       VARCHAR2(32),
  permissionid VARCHAR2(32)
)
;
comment on table SYS_ROLE_PERMISSION
  is 'ϵͳ����֮��ɫ��Ȩ�޹�����';
comment on column SYS_ROLE_PERMISSION.id
  is '��ˮ�� uuid ';
comment on column SYS_ROLE_PERMISSION.roleid
  is '��ɫid';
comment on column SYS_ROLE_PERMISSION.permissionid
  is 'Ȩ�ޱ��';
alter table SYS_ROLE_PERMISSION
  add constraint PK_SYS_ROLE_PERMISSION primary key (ID);

prompt
prompt Creating table SYS_SENSITIVEWORD
prompt ================================
prompt
create table SYS_SENSITIVEWORD
(
  wordid   VARCHAR2(36) not null,
  wordname VARCHAR2(200)
)
;
comment on table SYS_SENSITIVEWORD
  is 'ϵͳ������֮ϵͳ���йؼ��ֿ�';
comment on column SYS_SENSITIVEWORD.wordid
  is '����';
comment on column SYS_SENSITIVEWORD.wordname
  is '�ؼ�������';
alter table SYS_SENSITIVEWORD
  add constraint PK_SYS_SENSITIVEWORD primary key (WORDID);

prompt
prompt Creating table SYS_USERGROUPREF_TABLE
prompt =====================================
prompt
create table SYS_USERGROUPREF_TABLE
(
  usergroupid VARCHAR2(32) not null,
  userid      VARCHAR2(32),
  groupid     VARCHAR2(32)
)
;
comment on table SYS_USERGROUPREF_TABLE
  is 'ϵͳ����֮�û�����������';
comment on column SYS_USERGROUPREF_TABLE.usergroupid
  is 'ID';
comment on column SYS_USERGROUPREF_TABLE.userid
  is '�û�ID';
comment on column SYS_USERGROUPREF_TABLE.groupid
  is '��ID';
alter table SYS_USERGROUPREF_TABLE
  add constraint PK_SYS_USERGROUPREF primary key (USERGROUPID);

prompt
prompt Creating table SYS_USER_PERMISSION
prompt ==================================
prompt
create table SYS_USER_PERMISSION
(
  id           VARCHAR2(32) not null,
  userid       VARCHAR2(32) not null,
  permissionid VARCHAR2(32) not null
)
;
comment on table SYS_USER_PERMISSION
  is 'ϵͳ����֮�û���Ȩ�޹�����';
comment on column SYS_USER_PERMISSION.id
  is '�û���Ȩ�޹�������';
comment on column SYS_USER_PERMISSION.userid
  is '�û�����ˮ��';
comment on column SYS_USER_PERMISSION.permissionid
  is 'Ȩ�ޱ��';
alter table SYS_USER_PERMISSION
  add constraint PK_SYS_USER_PERMISSION primary key (ID);

prompt
prompt Creating table SYS_USER_ROLE
prompt ============================
prompt
create table SYS_USER_ROLE
(
  id     VARCHAR2(32) not null,
  userid VARCHAR2(32) not null,
  roleid VARCHAR2(32) not null
)
;
comment on table SYS_USER_ROLE
  is 'ϵͳ����֮�û����ɫ������';
comment on column SYS_USER_ROLE.id
  is '��ˮ��';
comment on column SYS_USER_ROLE.userid
  is '�û�id';
comment on column SYS_USER_ROLE.roleid
  is '��ɫid';
alter table SYS_USER_ROLE
  add constraint PK_SYS_USER_ROLE primary key (ID);

prompt
prompt Creating table SYS_USER_TABLE
prompt =============================
prompt
create table SYS_USER_TABLE
(
  userid     VARCHAR2(32) not null,
  password   VARCHAR2(64) not null,
  username   VARCHAR2(50) not null,
  enabled    VARCHAR2(1) not null,
  isleader   VARCHAR2(1),
  cnname     VARCHAR2(32) not null,
  owner      VARCHAR2(32),
  macaddr    VARCHAR2(300),
  usertype   VARCHAR2(2),
  otherinfo  VARCHAR2(2000),
  createdate DATE,
  mobile     VARCHAR2(32),
  phone      VARCHAR2(32),
  email      VARCHAR2(32),
  address    VARCHAR2(300),
  salt       VARCHAR2(64),
  abz182     VARCHAR2(20)
)
;
comment on table SYS_USER_TABLE
  is 'ϵͳ����֮ƽ̨�û���';
comment on column SYS_USER_TABLE.userid
  is '�û�ID';
comment on column SYS_USER_TABLE.password
  is '����(md5)';
comment on column SYS_USER_TABLE.username
  is '��¼��';
comment on column SYS_USER_TABLE.enabled
  is '�û��Ƿ���Ч(1��Ч 0��Ч)';
comment on column SYS_USER_TABLE.isleader
  is '�Ƿ����˻�';
comment on column SYS_USER_TABLE.cnname
  is '�û���������';
comment on column SYS_USER_TABLE.owner
  is '������';
comment on column SYS_USER_TABLE.macaddr
  is '������ַ';
comment on column SYS_USER_TABLE.usertype
  is '���û�����(1.�����û���2.��������-���� 3.��������-����Ա 4.��������-רְίԱ)';
comment on column SYS_USER_TABLE.otherinfo
  is '������Ϣ';
comment on column SYS_USER_TABLE.createdate
  is '����ʱ��';
comment on column SYS_USER_TABLE.mobile
  is '�ֻ�����';
comment on column SYS_USER_TABLE.phone
  is '��ϵ�绰';
comment on column SYS_USER_TABLE.email
  is '�����ַ';
comment on column SYS_USER_TABLE.address
  is '��ϵ��ַ';
comment on column SYS_USER_TABLE.salt
  is '���������';
comment on column SYS_USER_TABLE.abz182
  is '������';
alter table SYS_USER_TABLE
  add constraint PK_SYS_USER primary key (USERID);

prompt
prompt Creating table S_APP_LOG
prompt ========================
prompt
create table S_APP_LOG
(
  logid        VARCHAR2(36) not null,
  logtime      DATE,
  url          VARCHAR2(1000) not null,
  appkey       VARCHAR2(36),
  interface_id VARCHAR2(36)
)
;
comment on table S_APP_LOG
  is '��ҵ��������ҵ���֮�ӿڷ�����־��¼��';
comment on column S_APP_LOG.logid
  is '��־���(uuid)';
comment on column S_APP_LOG.logtime
  is '����ʱ��';
comment on column S_APP_LOG.url
  is '����ĵ�ַ';
comment on column S_APP_LOG.appkey
  is '�������';
comment on column S_APP_LOG.interface_id
  is '���������Ϣ���(uuid)';
alter table S_APP_LOG
  add constraint PK_S_APP_LOG primary key (LOGID);

prompt
prompt Creating table S_APP_SSO
prompt ========================
prompt
create table S_APP_SSO
(
  ssoid        VARCHAR2(36),
  systypeid    VARCHAR2(36),
  ssoappname   VARCHAR2(200),
  appid        VARCHAR2(200),
  appsecret    VARCHAR2(200),
  ssoappvalid  VARCHAR2(20),
  ssoclienturl VARCHAR2(200)
)
;
comment on table S_APP_SSO
  is '��ҵ��������(����)-�����¼Ӧ�ÿͻ������ñ�';
comment on column S_APP_SSO.ssoid
  is '�����¼Ӧ�����ñ��(�������:UUID)';
comment on column S_APP_SSO.systypeid
  is 'Ӧ��ϵͳ���ͱ��(�������:UUID)';
comment on column S_APP_SSO.ssoappname
  is '�����¼Ӧ�ÿͻ�������';
comment on column S_APP_SSO.appid
  is '�����¼Ӧ�ÿͻ���APPID';
comment on column S_APP_SSO.appsecret
  is '�����¼Ӧ�ÿͻ���APPSECRET';
comment on column S_APP_SSO.ssoappvalid
  is '�Ƿ���Ч';
comment on column S_APP_SSO.ssoclienturl
  is '�����¼Ӧ�ÿͻ��˵�ַ';

prompt
prompt Creating table S_EMAILLOG
prompt =========================
prompt
create table S_EMAILLOG
(
  id             VARCHAR2(36) not null,
  optype         VARCHAR2(20) not null,
  send_userid    VARCHAR2(36) not null,
  receive_userid VARCHAR2(36),
  receive_email  VARCHAR2(200) not null,
  sendtime       DATE not null,
  title          VARCHAR2(1000) not null,
  content        CLOB,
  success        VARCHAR2(20) not null,
  failreason     VARCHAR2(4000),
  userlogid      VARCHAR2(36) not null
)
;
comment on table S_EMAILLOG
  is '[0051]��ҵ��������(����)-�ʼ�������־��Ϣ��';
comment on column S_EMAILLOG.id
  is '�ʼ�������־����(�������:UUID)';
comment on column S_EMAILLOG.optype
  is 'ҵ��������ʹ���';
comment on column S_EMAILLOG.send_userid
  is '�ʼ������ߵ��˺�����';
comment on column S_EMAILLOG.receive_userid
  is '�ʼ������ߵ��˺�����';
comment on column S_EMAILLOG.receive_email
  is '�ʼ������ߵ�������';
comment on column S_EMAILLOG.sendtime
  is '�ʼ�����ʱ��';
comment on column S_EMAILLOG.title
  is '�ʼ�����';
comment on column S_EMAILLOG.content
  is '�ʼ�����';
comment on column S_EMAILLOG.success
  is '�ʼ������Ƿ�ɹ�����';
comment on column S_EMAILLOG.failreason
  is '�ʼ�����ʧ��ԭ������';
comment on column S_EMAILLOG.userlogid
  is '�û�������־����(�������:UUID)';
alter table S_EMAILLOG
  add constraint PK_S_EMAILLOG primary key (ID);

prompt
prompt Creating table S_ERRORLOG
prompt =========================
prompt
create table S_ERRORLOG
(
  logid         VARCHAR2(36) not null,
  logtime       DATE not null,
  stackmsg      CLOB,
  message       VARCHAR2(2000),
  exceptiontype VARCHAR2(400),
  usergent      VARCHAR2(1000) not null,
  ipaddr        VARCHAR2(200) not null,
  url           VARCHAR2(2000) not null,
  cookie        VARCHAR2(2000),
  userid        VARCHAR2(36)
)
;
comment on table S_ERRORLOG
  is '[0047]��ҵ��������(����)-��վ�����쳣��־��Ϣ��';
comment on column S_ERRORLOG.logid
  is '��վ�����쳣��־����(�������:UUID)';
comment on column S_ERRORLOG.logtime
  is '�쳣����ʱ��';
comment on column S_ERRORLOG.stackmsg
  is '�쳣ջ��Ϣ����';
comment on column S_ERRORLOG.message
  is '��վ�����쳣��־����';
comment on column S_ERRORLOG.exceptiontype
  is '��վ�����쳣��������';
comment on column S_ERRORLOG.usergent
  is '�û�������Ϣ';
comment on column S_ERRORLOG.ipaddr
  is '�ͻ����豸IP��ַ';
comment on column S_ERRORLOG.url
  is '����ͳһ��Դ��λ��ַ';
comment on column S_ERRORLOG.cookie
  is '�����û�����Ϣ';
comment on column S_ERRORLOG.userid
  is '�û�id';
alter table S_ERRORLOG
  add constraint PK_S_ERRORLOG primary key (LOGID);

prompt
prompt Creating table S_FILETYPE
prompt =========================
prompt
create table S_FILETYPE
(
  businesstype VARCHAR2(20) not null,
  typename     VARCHAR2(100) not null,
  filemaxnum   NUMBER(20) not null,
  filemaxsize  NUMBER(20,2) not null
)
;
comment on table S_FILETYPE
  is '[0039]��ҵ��������(����)-�ϴ��ļ�ҵ�����Ͳ�����';
comment on column S_FILETYPE.businesstype
  is '�ϴ��ļ�ҵ�����ͱ��';
comment on column S_FILETYPE.typename
  is '�ϴ��ļ�ҵ����������';
comment on column S_FILETYPE.filemaxnum
  is '�ϴ��ļ���������';
comment on column S_FILETYPE.filemaxsize
  is '�ϴ��ļ���С����(��λ:M)';
alter table S_FILETYPE
  add constraint PK_S_FILETYPE primary key (BUSINESSTYPE);

prompt
prompt Creating table S_FILE_RECORD
prompt ============================
prompt
create table S_FILE_RECORD
(
  file_uuid            VARCHAR2(36) not null,
  file_name            VARCHAR2(200),
  file_length          NUMBER(16),
  file_addtime         DATE,
  file_path            VARCHAR2(200),
  file_status          VARCHAR2(20),
  file_type            VARCHAR2(20),
  file_rel_path        VARCHAR2(200),
  file_bus_id          VARCHAR2(36),
  file_bus_type        VARCHAR2(20),
  bus_addtime          DATE,
  file_bus_name        VARCHAR2(200),
  file_bus_description VARCHAR2(2000),
  file_rel_small_path  VARCHAR2(200),
  file_base64          CLOB
)
;
comment on table S_FILE_RECORD
  is '[0040]��ҵ��������(����)-�ϴ��ļ���Ϣ��';
comment on column S_FILE_RECORD.file_uuid
  is '�ϴ��ļ�����(�������:UUID)';
comment on column S_FILE_RECORD.file_name
  is '�ϴ��ļ�����(�������ϵ�����,�������:����ʱ�䵽��14λ+6λ����ַ���)';
comment on column S_FILE_RECORD.file_length
  is '�ϴ��ļ���С(��λ;�ֽ�)';
comment on column S_FILE_RECORD.file_addtime
  is '�ϴ��ļ��ϴ�ʱ��';
comment on column S_FILE_RECORD.file_path
  is '�ϴ��ļ��������ϴ洢����·��(�����ϴ��ļ�����)';
comment on column S_FILE_RECORD.file_status
  is '�ϴ��ļ�״̬����';
comment on column S_FILE_RECORD.file_type
  is '�ϴ��ļ���չ��';
comment on column S_FILE_RECORD.file_rel_path
  is '�ϴ��ļ��������ϴ洢���·��(�����ϴ��ļ�����)';
comment on column S_FILE_RECORD.file_bus_id
  is 'ҵ���ϴ��ļ�����ҵ����';
comment on column S_FILE_RECORD.file_bus_type
  is 'ҵ���ϴ��ļ�����ҵ��ҵ�����ʹ���';
comment on column S_FILE_RECORD.bus_addtime
  is 'ҵ����ʱ��';
comment on column S_FILE_RECORD.file_bus_name
  is 'ҵ���ϴ��ļ�ԭ��';
comment on column S_FILE_RECORD.file_bus_description
  is 'ҵ���ϴ��ļ�����';
comment on column S_FILE_RECORD.file_rel_small_path
  is '�ϴ��ļ�С�ļ��ϴ���ַ';
comment on column S_FILE_RECORD.file_base64
  is '�ϴ��ļ�base64�ļ���ʽ';
alter table S_FILE_RECORD
  add constraint PK_S_FILE_RECORD primary key (FILE_UUID);

prompt
prompt Creating table S_GROUP_TABLE
prompt ============================
prompt
create table S_GROUP_TABLE
(
  groupid      VARCHAR2(32) not null,
  description  VARCHAR2(500),
  parentid     VARCHAR2(32),
  org          VARCHAR2(8),
  districtcode VARCHAR2(32),
  license      VARCHAR2(20),
  name         VARCHAR2(100) not null,
  principal    VARCHAR2(32),
  shortname    VARCHAR2(50),
  address      VARCHAR2(150),
  tel          VARCHAR2(30),
  linkman      VARCHAR2(30),
  type         VARCHAR2(3),
  chargedept   VARCHAR2(50),
  otherinfo    VARCHAR2(2000),
  owner        VARCHAR2(32),
  status       CHAR(1),
  createdate   DATE
)
;
comment on table S_GROUP_TABLE
  is '[]��ҵ��������(����)-(��ܱ�)�û�����Ϣ��';
comment on column S_GROUP_TABLE.groupid
  is '����ID';
comment on column S_GROUP_TABLE.description
  is '�û�������';
comment on column S_GROUP_TABLE.parentid
  is '�ϼ��ṹID';
comment on column S_GROUP_TABLE.org
  is 'ϵͳ��������';
comment on column S_GROUP_TABLE.districtcode
  is '��������';
comment on column S_GROUP_TABLE.name
  is '�û�������';
comment on column S_GROUP_TABLE.principal
  is '����������';
comment on column S_GROUP_TABLE.shortname
  is '���';
comment on column S_GROUP_TABLE.address
  is '��ַ';
comment on column S_GROUP_TABLE.tel
  is '�绰';
comment on column S_GROUP_TABLE.linkman
  is '��ϵ��';
comment on column S_GROUP_TABLE.chargedept
  is '���ܲ���';
comment on column S_GROUP_TABLE.otherinfo
  is '������Ϣ';
comment on column S_GROUP_TABLE.owner
  is '������';
comment on column S_GROUP_TABLE.status
  is '״̬';
comment on column S_GROUP_TABLE.createdate
  is '����ʱ��';

prompt
prompt Creating table S_LOG
prompt ====================
prompt
create table S_LOG
(
  logid         VARCHAR2(36) not null,
  logtype       VARCHAR2(20),
  message       VARCHAR2(2000),
  logtime       DATE,
  cost          VARCHAR2(100),
  stackmsg      CLOB,
  exceptiontype VARCHAR2(100),
  usergent      VARCHAR2(1000) not null,
  ipaddr        VARCHAR2(200) not null,
  referer       VARCHAR2(1000),
  url           VARCHAR2(1000) not null,
  userid        VARCHAR2(36),
  cookie        VARCHAR2(1000),
  appkey        VARCHAR2(36),
  method        VARCHAR2(200) not null,
  success       VARCHAR2(1000),
  responsemsg   CLOB,
  queryparam    CLOB,
  token         VARCHAR2(4000),
  interfacetype VARCHAR2(36)
)
;
comment on table S_LOG
  is '��ҵ��������ҵ���֮�ӿڷ�����־��¼��';
comment on column S_LOG.logid
  is '��־���(uuid)';
comment on column S_LOG.logtype
  is '��־�������ʹ���';
comment on column S_LOG.message
  is '��־����';
comment on column S_LOG.logtime
  is '����ʱ��';
comment on column S_LOG.cost
  is '����ķ�ʱ��(����)';
comment on column S_LOG.stackmsg
  is '�쳣ջ��Ϣ';
comment on column S_LOG.exceptiontype
  is '�쳣��������';
comment on column S_LOG.usergent
  is '������Ϣ';
comment on column S_LOG.ipaddr
  is '�ͻ���ip��ַ';
comment on column S_LOG.referer
  is '��ַ��Դ';
comment on column S_LOG.url
  is '����ĵ�ַ';
comment on column S_LOG.userid
  is '��ǰ������Ա���';
comment on column S_LOG.cookie
  is '״̬��';
comment on column S_LOG.appkey
  is '�������';
comment on column S_LOG.method
  is '���󷽷���������';
comment on column S_LOG.success
  is '�����Ƿ�ɹ�����';
comment on column S_LOG.responsemsg
  is '������Ϣ';
comment on column S_LOG.queryparam
  is '���������Ϣ';
comment on column S_LOG.token
  is '����jwt��֤��';
comment on column S_LOG.interfacetype
  is '������';
alter table S_LOG
  add constraint PK_S_LOG primary key (LOGID);

prompt
prompt Creating table S_LOGININF
prompt =========================
prompt
create table S_LOGININF
(
  loginhash VARCHAR2(32),
  logintime DATE,
  ip        VARCHAR2(100),
  usergent  VARCHAR2(1000),
  sessionid VARCHAR2(100)
)
;
comment on table S_LOGININF
  is 'ϵͳ����֮��¼״̬��¼��';
comment on column S_LOGININF.loginhash
  is '��¼��Ϣhash��Ϣsessionid+ip+agent';
comment on column S_LOGININF.logintime
  is '��¼ʱ��';
comment on column S_LOGININF.ip
  is '�û�ip';
comment on column S_LOGININF.usergent
  is 'usergent';
comment on column S_LOGININF.sessionid
  is 'sessionid';

prompt
prompt Creating table S_PERMISSION
prompt ===========================
prompt
create table S_PERMISSION
(
  permissionid  VARCHAR2(36) not null,
  permname      VARCHAR2(100),
  permtype      VARCHAR2(100),
  url           VARCHAR2(1000),
  parentid      VARCHAR2(32),
  enabled       VARCHAR2(3),
  sortnum       VARCHAR2(32),
  permdescribe  VARCHAR2(200),
  permcode      VARCHAR2(200),
  updatetime    DATE,
  iconcss       VARCHAR2(100),
  deleteable    VARCHAR2(10),
  isblanktarget VARCHAR2(10),
  systypecode   VARCHAR2(36)
)
;
comment on table S_PERMISSION
  is 'ϵͳ����֮����ģ�����ñ�';
comment on column S_PERMISSION.permissionid
  is 'Ȩ�ޱ���,��Ź���uuid ';
comment on column S_PERMISSION.permname
  is 'Ȩ������';
comment on column S_PERMISSION.permtype
  is 'Ȩ������(1 �ڵ� 2Ҷ�� 3 ��ť)';
comment on column S_PERMISSION.url
  is 'Ҷ�ӽ���Ӧ����url(��Ե�ַ)';
comment on column S_PERMISSION.parentid
  is '��Ȩ�ޱ��';
comment on column S_PERMISSION.enabled
  is '�Ƿ���Ч��־';
comment on column S_PERMISSION.sortnum
  is '����ҳ��';
comment on column S_PERMISSION.permdescribe
  is 'Ȩ������';
comment on column S_PERMISSION.permcode
  is 'Ȩ�ޱ���';
comment on column S_PERMISSION.updatetime
  is 'Ȩ��������ʱ��';
comment on column S_PERMISSION.iconcss
  is 'ͼ����ʽ';
comment on column S_PERMISSION.deleteable
  is '�Ƿ����ɾ��(����ԱȨ�޲ſ���)';
comment on column S_PERMISSION.isblanktarget
  is '�򿪴��ڷ�ʽ(_blank)';
comment on column S_PERMISSION.systypecode
  is 'Ӧ��ϵͳ���Ϳ����(�������:S+5λ����)';
alter table S_PERMISSION
  add constraint PK_S_PERMISSION primary key (PERMISSIONID);

prompt
prompt Creating table S_ROLE
prompt =====================
prompt
create table S_ROLE
(
  roleid       VARCHAR2(36) not null,
  rolecode     VARCHAR2(200) not null,
  rolename     VARCHAR2(200) not null,
  roledescribe VARCHAR2(2000),
  createby     VARCHAR2(36),
  createdate   DATE not null,
  updateby     VARCHAR2(36),
  updatetime   DATE,
  enabled      VARCHAR2(20) not null,
  systypecode  VARCHAR2(36)
)
;
comment on table S_ROLE
  is '[0042]��ҵ��������(����)-(��ܱ�)��ɫ��Ϣ��';
comment on column S_ROLE.roleid
  is '��ɫ����(�������:UUID)';
comment on column S_ROLE.rolecode
  is '��ɫ����(�������:��д��ĸR+10λ�Զ�������)';
comment on column S_ROLE.rolename
  is '��ɫ����';
comment on column S_ROLE.roledescribe
  is '��ɫ����';
comment on column S_ROLE.createby
  is '����������';
comment on column S_ROLE.createdate
  is '����ʱ��';
comment on column S_ROLE.updateby
  is '����������';
comment on column S_ROLE.updatetime
  is '����ʱ��';
comment on column S_ROLE.enabled
  is '�Ƿ���Ч��־����';
comment on column S_ROLE.systypecode
  is 'Ӧ��ϵͳ���Ϳ����(�������:S+5λ����)';
alter table S_ROLE
  add constraint PK_S_ROLE_K primary key (ROLEID);

prompt
prompt Creating table S_ROLE_PERMISSION
prompt ================================
prompt
create table S_ROLE_PERMISSION
(
  id           VARCHAR2(36) not null,
  roleid       VARCHAR2(36) not null,
  permissionid VARCHAR2(36) not null
)
;
comment on table S_ROLE_PERMISSION
  is '[0044]��ҵ��������(����)-(��ܱ�)��ɫ�빦��ģ�������';
comment on column S_ROLE_PERMISSION.id
  is '��ɫ�빦��ģ���������(�������:UUID)';
comment on column S_ROLE_PERMISSION.roleid
  is '��ɫ����(�������:UUID)';
comment on column S_ROLE_PERMISSION.permissionid
  is '����ģ������(�������:UUID)';
alter table S_ROLE_PERMISSION
  add constraint PK_S_ROLE_PERMISSION primary key (ID);

prompt
prompt Creating table S_SMSLOG
prompt =======================
prompt
create table S_SMSLOG
(
  id             VARCHAR2(36) not null,
  optype         VARCHAR2(20) not null,
  send_userid    VARCHAR2(36),
  receive_userid VARCHAR2(36),
  receive_mobile VARCHAR2(50) not null,
  sendtime       DATE not null,
  content        VARCHAR2(2000) not null,
  success        VARCHAR2(20) not null,
  failreason     VARCHAR2(4000),
  userlogid      VARCHAR2(36) not null,
  smsgroup_id    VARCHAR2(36)
)
;
comment on table S_SMSLOG
  is '[0049]��ҵ��������(����)-���ŷ�����־��Ϣ��';
comment on column S_SMSLOG.id
  is '���ŷ�����־����(�������:UUID)';
comment on column S_SMSLOG.optype
  is 'ҵ��������ʹ���';
comment on column S_SMSLOG.send_userid
  is '���ŷ����ߵ��˺�����';
comment on column S_SMSLOG.receive_userid
  is '���Ž����ߵ��˺�����';
comment on column S_SMSLOG.receive_mobile
  is '���Ž������ֻ�����';
comment on column S_SMSLOG.sendtime
  is '���ŷ���ʱ��';
comment on column S_SMSLOG.content
  is '��������';
comment on column S_SMSLOG.success
  is '���ŷ����Ƿ�ɹ�����';
comment on column S_SMSLOG.failreason
  is '���ŷ���ʧ��ԭ������';
comment on column S_SMSLOG.userlogid
  is '�û�������־����(�������:UUID)';
comment on column S_SMSLOG.smsgroup_id
  is '����Ⱥ������';
alter table S_SMSLOG
  add constraint PK_S_SMSLOG primary key (ID);

prompt
prompt Creating table S_SYSTYPE
prompt ========================
prompt
create table S_SYSTYPE
(
  systypeid   VARCHAR2(36),
  systypecode VARCHAR2(36),
  sysname     VARCHAR2(200),
  sysdesc     VARCHAR2(500),
  sysurl      VARCHAR2(200)
)
;
comment on table S_SYSTYPE
  is '��ҵ��������(����)-(��ܱ�)Ӧ��ϵͳ���ͱ�';
comment on column S_SYSTYPE.systypeid
  is 'Ӧ��ϵͳ���ͱ��(�������:UUID)';
comment on column S_SYSTYPE.systypecode
  is 'Ӧ��ϵͳ���Ϳ����(�������:S+5λ����)';
comment on column S_SYSTYPE.sysname
  is 'Ӧ��ϵͳ����';
comment on column S_SYSTYPE.sysdesc
  is 'Ӧ��ϵͳ����';
comment on column S_SYSTYPE.sysurl
  is 'Ӧ��ϵͳ���ʵ�ַ';

prompt
prompt Creating table S_UPLOADFILE
prompt ===========================
prompt
create table S_UPLOADFILE
(
  aaa001 VARCHAR2(40) not null,
  aaa002 VARCHAR2(40) not null,
  aaa003 VARCHAR2(100),
  aaa004 VARCHAR2(20),
  aaa005 VARCHAR2(20),
  aaa006 NUMBER,
  aaa007 VARCHAR2(200) not null,
  aaa008 DATE not null,
  aaa009 VARCHAR2(10) not null,
  aaa010 VARCHAR2(50),
  aaa011 VARCHAR2(50)
)
;
comment on table S_UPLOADFILE
  is '�����ϴ�(�����һ��)';
comment on column S_UPLOADFILE.aaa001
  is 'uuid(����)';
comment on column S_UPLOADFILE.aaa002
  is '�û����';
comment on column S_UPLOADFILE.aaa003
  is '�ļ�����';
comment on column S_UPLOADFILE.aaa004
  is '�ļ����ͱ���';
comment on column S_UPLOADFILE.aaa005
  is '�ļ�����';
comment on column S_UPLOADFILE.aaa006
  is '�ļ���С';
comment on column S_UPLOADFILE.aaa007
  is '�ļ�·��';
comment on column S_UPLOADFILE.aaa008
  is '��������';
comment on column S_UPLOADFILE.aaa009
  is '��Ч��־';
comment on column S_UPLOADFILE.aaa010
  is 'ҵ������';
comment on column S_UPLOADFILE.aaa011
  is '�ļ��ϴ�������';
alter table S_UPLOADFILE
  add constraint PK_S_UPLOADFILE primary key (AAA001);

prompt
prompt Creating table S_USER
prompt =====================
prompt
create table S_USER
(
  userid        VARCHAR2(36) not null,
  username      VARCHAR2(200) not null,
  password      VARCHAR2(200) not null,
  usertype      VARCHAR2(20),
  email         VARCHAR2(200),
  mobile        VARCHAR2(32),
  lastlogintime DATE,
  lastloginip   VARCHAR2(200),
  enabled       VARCHAR2(20) not null,
  baseinfoid    VARCHAR2(36),
  isblacklist   VARCHAR2(20) not null,
  reason        VARCHAR2(200),
  registtime    DATE not null,
  logintimes    NUMBER(10),
  openid        VARCHAR2(200),
  fromaddr      VARCHAR2(20) not null,
  ismainaccount VARCHAR2(20) not null,
  aae013        VARCHAR2(2000),
  iscertified   VARCHAR2(20),
  name          VARCHAR2(200)
)
;
comment on table S_USER
  is '[0041]��ҵ��������(����)-(��ܱ�)��¼�˻���Ϣ��';
comment on column S_USER.userid
  is '������¼�˻�����(�������:UUID)';
comment on column S_USER.username
  is '������¼�˻���¼��';
comment on column S_USER.password
  is '������¼�˻�����';
comment on column S_USER.usertype
  is '������¼�˻����ʹ���';
comment on column S_USER.email
  is '��¼�����ʼ�';
comment on column S_USER.mobile
  is '��¼�ֻ���';
comment on column S_USER.lastlogintime
  is '����¼ʱ��';
comment on column S_USER.lastloginip
  is '����¼�����豸IP��ַ';
comment on column S_USER.enabled
  is '�Ƿ���Ч��־����';
comment on column S_USER.baseinfoid
  is 'ʵ����������Ϣ����(�ֱ��ӦCD01��CE01������ ����)';
comment on column S_USER.isblacklist
  is '�Ƿ�������������';
comment on column S_USER.reason
  is '���������ԭ������';
comment on column S_USER.registtime
  is 'ע������';
comment on column S_USER.logintimes
  is '��¼����';
comment on column S_USER.openid
  is '΢��OPENID';
comment on column S_USER.fromaddr
  is '�˻�ע����Դ����(1��Ƹ��ְ�� 2�����걨�� 3����ƽ̨����)';
comment on column S_USER.ismainaccount
  is '�Ƿ����˻�����';
comment on column S_USER.aae013
  is '�˻���Ϣ��ע';
comment on column S_USER.iscertified
  is '�˻��Ƿ���֤';
comment on column S_USER.name
  is '��ʵ����';
alter table S_USER
  add constraint PK_S_USER primary key (USERID);

prompt
prompt Creating table S_USERGROUPREF
prompt =============================
prompt
create table S_USERGROUPREF
(
  usergroupid VARCHAR2(32) not null,
  userid      VARCHAR2(32),
  groupid     VARCHAR2(32)
)
;
comment on table S_USERGROUPREF
  is '[]��ҵ��������(����)-(��ܱ�)�û��û��������';
comment on column S_USERGROUPREF.usergroupid
  is 'ID';
comment on column S_USERGROUPREF.userid
  is '�û�ID';
comment on column S_USERGROUPREF.groupid
  is '��ID';
alter table S_USERGROUPREF
  add constraint PK_SUSERGROUPREF primary key (USERGROUPID);

prompt
prompt Creating table S_USERLOG
prompt ========================
prompt
create table S_USERLOG
(
  logid    VARCHAR2(36) not null,
  optype   VARCHAR2(200) not null,
  logstime TIMESTAMP(6),
  logetime TIMESTAMP(6) not null,
  message  VARCHAR2(2000) not null,
  opstatus VARCHAR2(20),
  opreason VARCHAR2(4000),
  aae011   VARCHAR2(36),
  aae012   VARCHAR2(36),
  eec117   VARCHAR2(36),
  eec118   VARCHAR2(36),
  eec119   VARCHAR2(200)
)
;
comment on table S_USERLOG
  is '��ҵ��������ҵ���֮��վ�û�������־��¼��';
comment on column S_USERLOG.logid
  is '��־���(uuid)';
comment on column S_USERLOG.optype
  is '�û��������ʹ���';
comment on column S_USERLOG.logstime
  is '�û������ռǼ�¼��ʼʱ��';
comment on column S_USERLOG.logetime
  is '�û�������־��¼����ʱ��';
comment on column S_USERLOG.message
  is '�û���������';
comment on column S_USERLOG.opstatus
  is '�û�����״̬(1�ɹ� 0ʧ��)';
comment on column S_USERLOG.opreason
  is '�û�����ʧ��ԭ������';
comment on column S_USERLOG.aae011
  is '�����˻�����';
comment on column S_USERLOG.aae012
  is '�����˻�����';
comment on column S_USERLOG.eec117
  is '�����豸����ϵͳ���ʹ���';
comment on column S_USERLOG.eec118
  is '�����豸��������ʹ���';
comment on column S_USERLOG.eec119
  is '�����豸IP��ַ';
alter table S_USERLOG
  add constraint PK_T_USERLOG primary key (LOGID);

prompt
prompt Creating table S_USER_PERMISSION
prompt ================================
prompt
create table S_USER_PERMISSION
(
  id           VARCHAR2(36) not null,
  userid       VARCHAR2(36) not null,
  permissionid VARCHAR2(36) not null
)
;
comment on table S_USER_PERMISSION
  is '[0045]��ҵ��������(����)-(��ܱ�)�˻��빦��ģ�������';
comment on column S_USER_PERMISSION.id
  is '�˻��빦��ģ���������(�������:UUID)';
comment on column S_USER_PERMISSION.userid
  is '������¼�˻�����(�������:UUID)';
comment on column S_USER_PERMISSION.permissionid
  is '����ģ������(�������:UUID)';
alter table S_USER_PERMISSION
  add constraint PK_S_USER_PERMISSION primary key (ID);

prompt
prompt Creating table S_USER_ROLE
prompt ==========================
prompt
create table S_USER_ROLE
(
  id     VARCHAR2(36) not null,
  userid VARCHAR2(36) not null,
  roleid VARCHAR2(36) not null
)
;
comment on table S_USER_ROLE
  is '��ҵ��������ϵͳ��ܱ�֮�û����ɫ������';
comment on column S_USER_ROLE.id
  is '�˻����ɫ��������(�������:UUID)';
comment on column S_USER_ROLE.userid
  is '�û����';
comment on column S_USER_ROLE.roleid
  is '��ɫ���';
alter table S_USER_ROLE
  add constraint PK_S_USER_ROLE primary key (ID);

prompt
prompt Creating table S_VERCORD
prompt ========================
prompt
create table S_VERCORD
(
  vercodeid  VARCHAR2(36) not null,
  userlogid  VARCHAR2(36) not null,
  optype     VARCHAR2(20) not null,
  mobile     VARCHAR2(50),
  e_mail     VARCHAR2(50),
  vercode    VARCHAR2(200) not null,
  createdate TIMESTAMP(6) not null,
  enddate    TIMESTAMP(6) not null,
  aae100     VARCHAR2(20) not null,
  inputcode  VARCHAR2(50),
  inputtime  TIMESTAMP(6),
  verresult  VARCHAR2(20) not null
)
;
comment on table S_VERCORD
  is '[0047]��ҵ��������(����)-��֤����Ϣ��';
comment on column S_VERCORD.vercodeid
  is '��֤������(�������:UUID)';
comment on column S_VERCORD.userlogid
  is '�û�������־����(�������:UUID)';
comment on column S_VERCORD.optype
  is 'ҵ��������ʹ���';
comment on column S_VERCORD.mobile
  is '�ֻ�����';
comment on column S_VERCORD.e_mail
  is '��������';
comment on column S_VERCORD.vercode
  is '��֤��';
comment on column S_VERCORD.createdate
  is '����ʱ��';
comment on column S_VERCORD.enddate
  is 'ʧЧʱ��';
comment on column S_VERCORD.aae100
  is '��Ч��־����';
comment on column S_VERCORD.inputcode
  is '�û��������֤��';
comment on column S_VERCORD.inputtime
  is '�û�¼��ʱ��';
comment on column S_VERCORD.verresult
  is '��֤������ʹ���';
alter table S_VERCORD
  add constraint PK_S_VERCORD primary key (VERCODEID);

prompt
prompt Creating synonym AB01
prompt =====================
prompt
create or replace synonym AB01
  for SXJYRM.AB01;

prompt
prompt Creating synonym AB02
prompt =====================
prompt
create or replace synonym AB02
  for SXJYRM.AB02;

prompt
prompt Creating synonym AC01
prompt =====================
prompt
create or replace synonym AC01
  for SXJYRM.AC01;

prompt
prompt Creating synonym AC11
prompt =====================
prompt
create or replace synonym AC11
  for SXJYJYSY.AC11;

prompt
prompt Creating synonym AC12
prompt =====================
prompt
create or replace synonym AC12
  for SXJYJYSY.AC12;

prompt
prompt Creating synonym AC13
prompt =====================
prompt
create or replace synonym AC13
  for SXJYJYSY.AC13;

prompt
prompt Creating synonym AC14
prompt =====================
prompt
create or replace synonym AC14
  for SXJYJYSY.AC14;

prompt
prompt Creating synonym AC15
prompt =====================
prompt
create or replace synonym AC15
  for SXJYJYSY.AC15;

prompt
prompt Creating synonym CODE_TYPE
prompt ==========================
prompt
create or replace synonym CODE_TYPE
  for SXJYRM.CODE_TYPE;

prompt
prompt Creating synonym CODE_VALUE
prompt ===========================
prompt
create or replace synonym CODE_VALUE
  for SXJYRM.CODE_VALUE;

prompt
prompt Creating synonym SMT_GROUP
prompt ==========================
prompt
create or replace synonym SMT_GROUP
  for SXJYUC.SMT_GROUP;

prompt
prompt Creating synonym SMT_USER
prompt =========================
prompt
create or replace synonym SMT_USER
  for SXJYUC.SMT_USER;

prompt
prompt Creating synonym SMT_USERGROUPREF
prompt =================================
prompt
create or replace synonym SMT_USERGROUPREF
  for SXJYUC.SMT_USERGROUPREF;

prompt
prompt Creating view SYS_GROUP
prompt =======================
prompt
create or replace force view sys_group as
select GROUPID,
       DESCRIPTION,
       PARENTID,
       ORG,
       DISTRICTCODE,
       LICENSE,
       name   GROUPNAME,
       PRINCIPAL,
       SHORTNAME,
       ADDRESS,
       TEL,
       LINKMAN,
       type   GROUPTYPE,
       CHARGEDEPT,
       OTHERINFO,
       OWNER,
       STATUS,
       CREATEDATE
  from SMT_GROUP;

prompt
prompt Creating view SYS_USER
prompt ======================
prompt
create or replace force view sys_user as
select USERID,PASSWD PASSWORD,LOGINNAME USERNAME, USEFUL ENABLED,ISLEADER,USERNAME CNNAME,OWNER,MACADDR,USERTYPE,OTHERINFO,CREATEDATE ,'1' ABZ182,'' ADDRESS,'' EMAIL ,'' phone
    from SMT_USER;

prompt
prompt Creating view SYS_USERGROUPREF
prompt ==============================
prompt
create or replace force view sys_usergroupref as
select "USERGROUPID","USERID","GROUPID"
    from
   smt_usergroupref;

prompt
prompt Creating view V_AA10
prompt ====================
prompt
create or replace force view v_aa10 as
select code_type aaa100, code_value aaa102, code_name aaa103, SUB_CODE_VALUE aaa105 ,nvl(CODE_COLUMN_NAME,code_name)  aaa106  from code_value t  where t.code_type not in ('AAB301','AAC183_1','AAC200','AAC180') and exists (select 1 from code_type c where c.code_type=t.code_type )
UNION ALL
select 'AAB800' aaa100, code_value aaa102, code_name aaa103, SUB_CODE_VALUE aaa105, nvl(CODE_COLUMN_NAME,code_name)  aaa106 from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='1'
union all
select 'AAB801' aaa100, code_value aaa102, code_name aaa103, SUB_CODE_VALUE aaa105, nvl(CODE_COLUMN_NAME,code_name)  aaa106 from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='2'
AND t.SUB_CODE_VALUE='610000'
union all
select 'AAB802' aaa100, code_value aaa102, code_name aaa103, SUB_CODE_VALUE aaa105, nvl(CODE_COLUMN_NAME,code_name)  aaa106  from code_value t where t.code_type='AAB301' AND T.CODE_LEVEL='3'
union all
select 'CODETYPESORT',TYPE_SORT_CODE,TYPE_SORT_NAME,''aaa148 ,'' aaa106  from code_type_sort
union all
select 'SYSTYPECODE',SYSTYPECODE,SYSNAME,''aaa148 ,'' aaa106  from s_systype;

prompt
prompt Creating view V_CODE_TYPE
prompt =========================
prompt
create or replace force view v_code_type as
select aaa100 from v_aa10 group by aaa100;

prompt
prompt Creating view V_SUGGEST_DATA
prompt ============================
prompt
create or replace force view v_suggest_data as
select aab001 id,
    aab001 key,
    aab004 name,
    aab004 showname,
    'AB01' keytype
      from ab01;

prompt
prompt Creating view V_S_USER
prompt ======================
prompt
create or replace force view v_s_user as
select
    userid,
    username,
    password,
    usertype,
    email,
    mobile,
    lastlogintime,
    lastloginip,
    enabled,
    case when usertype ='1'  then
     (select aac001 from ac01 t where t.aac002=username)
     else
     (select aab001 from ab01 t where t.aab998=username)
     end
      baseinfoid,
    isblacklist,
    reason,
    registtime,
    logintimes,
    openid,
    fromaddr,
    ismainaccount,
    aae013,
    iscertified,
    name
from s_user t;

prompt
prompt Creating view V_USER_BASEINFO
prompt =============================
prompt
create or replace force view v_user_baseinfo as
select  aac001 baseinfoid ,aac001 id,aac003 name ,aac002, '' eac012,  '1' type from ac01;

prompt
prompt Creating function F_GETCODENAME
prompt ===============================
prompt
create or replace function F_GETCODENAME(ac_codetype in varchar2,ac_codevalue in varchar2) return varchar2 is

  Result varchar2(255);

begin

  begin

    select aaa103 into result from V_Aa10 where aaa100=ac_codetype and aaa102=ac_codevalue  and rownum<=1;

    return(Result);

  exception

  when others then

    return ac_codevalue;

  end;

end;
/


spool off
