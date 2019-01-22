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
  is '[0078]业务代码类型表';
comment on column CODE_TYPE_SORT.type_sort_id
  is '代码类型编号(编码规则:uuid)';
comment on column CODE_TYPE_SORT.type_sort_code
  is '代码类型编码(编码规则:6-12位字母)';
comment on column CODE_TYPE_SORT.type_sort_name
  is '代码类型名称';
comment on column CODE_TYPE_SORT.type_sort_desc
  is '代码类型描述';
comment on column CODE_TYPE_SORT.type_sort_order
  is '代码类型组内排序号';

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
  is '事项接口配置表';
comment on column ITEM_INTERFACE_CONFIG.interface_type
  is '接口类型';
comment on column ITEM_INTERFACE_CONFIG.interface_name
  is '接口名称';
comment on column ITEM_INTERFACE_CONFIG.interface_url
  is '接口地址';

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
  is '公共服务事项列表';
comment on column ITEM_LIST.item_id
  is '事项ID';
comment on column ITEM_LIST.item_code
  is '事项编号';
comment on column ITEM_LIST.item_name
  is '事项名称';
comment on column ITEM_LIST.item_url
  is '业务系统地址';
comment on column ITEM_LIST.item_submit_type
  is '1-业务系统  2-标准化平台';
comment on column ITEM_LIST.bsplatform_url
  is '标准化地址';

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
  is '事项校验接口';
comment on column ITEM_LIST_CHECK.item_id
  is '事项ID';
comment on column ITEM_LIST_CHECK.url
  is '接口地址';
comment on column ITEM_LIST_CHECK.type
  is '接口类型';

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
  is '事项材料列表';
comment on column ITEM_MATERIAL_LIST.item_id
  is '事项编号';
comment on column ITEM_MATERIAL_LIST.material_code
  is '材料编码';
comment on column ITEM_MATERIAL_LIST.material_name
  is '材料名称';
comment on column ITEM_MATERIAL_LIST.necessity
  is '是否必传 0-否 1-是';

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
  is '[0104]服务事项-服务事项浏览记录信息表';
comment on column SER_BROWSE.browse_id
  is '服务事项浏览记录内码(编码规则:UUID)';
comment on column SER_BROWSE.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_BROWSE.userid
  is '浏览人内网账户内码(用户中心中的人员)';
comment on column SER_BROWSE.createtime
  is '浏览日期';
comment on column SER_BROWSE.status
  is '服务事项浏览记录状态代码(1:有效、0:无效)';
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
  is '[0095]服务事项-服务事项业务分类信息表';
comment on column SER_BUS_TYPE.bus_type_id
  is '服务事项业务分类内码(编码规则:UUID)';
comment on column SER_BUS_TYPE.bus_area_code
  is '服务事项业务分类所属地区编码';
comment on column SER_BUS_TYPE.bus_type_code
  is '服务事项业务分类编码(编码规则:自定义)';
comment on column SER_BUS_TYPE.bus_type_name
  is '服务事项业务分类名称';
comment on column SER_BUS_TYPE.bus_type_sort
  is '服务事项业务分类组内排序号';
comment on column SER_BUS_TYPE.bus_type_pingyin
  is '服务事项业务分类拼音';
comment on column SER_BUS_TYPE.bus_type_desc
  is '服务目录业务分类内容描述';
comment on column SER_BUS_TYPE.bus_type_bigimgtype
  is '服务事项业务分类大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_BUS_TYPE.bus_type_smalltype
  is '服务事项业务分类小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_BUS_TYPE.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_BUS_TYPE.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_BUS_TYPE.createtime
  is '创建日期';
comment on column SER_BUS_TYPE.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_BUS_TYPE.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_BUS_TYPE.edittime
  is '最后修改日期';
comment on column SER_BUS_TYPE.status
  is '服务事项业务分类有效状态代码(1:有效、0:无效)';
comment on column SER_BUS_TYPE.parent_bus_type_id
  is '父级服务事项业务分类内码';
comment on column SER_BUS_TYPE.bus_type_bigimg
  is '服务事项业务分类大图标文件地址';
comment on column SER_BUS_TYPE.bus_type_smallimg
  is '服务事项业务分类小图标文件地址';
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
  is '[0100]服务事项-服务事项主体信息表 ';
comment on column SER_CATALOGUE.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_CATALOGUE.cata_code
  is '服务事项编码(编码规则:自定义)';
comment on column SER_CATALOGUE.cata_name
  is '服务事项名称';
comment on column SER_CATALOGUE.cata_pingyin
  is '服务事项拼音';
comment on column SER_CATALOGUE.cata_desc
  is '服务事项内容描述';
comment on column SER_CATALOGUE.cata_sort
  is '服务事项组内排序号';
comment on column SER_CATALOGUE.cata_bigimgtype
  is '服务事项大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CATALOGUE.cata_smalltype
  is '服务事项小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CATALOGUE.bus_type_id
  is '服务事项业务分类内码';
comment on column SER_CATALOGUE.event_type_id
  is '服务事项事项类型内码';
comment on column SER_CATALOGUE.consumer_type_id
  is '服务事项服务对象类型内码';
comment on column SER_CATALOGUE.power_type_id
  is '服务事项行政类型内码';
comment on column SER_CATALOGUE.department_id
  is '受理部门内码';
comment on column SER_CATALOGUE.cata_hand_time_limit
  is '法定办理时限描述';
comment on column SER_CATALOGUE.cata_promise_time_limit
  is '承诺办理时限描述';
comment on column SER_CATALOGUE.cata_complaint_tel
  is '监督电话';
comment on column SER_CATALOGUE.cata_is_net
  is '是否需要在线办理代码';
comment on column SER_CATALOGUE.cata_video_url
  is '服务事项办理流程说明视频文件地址';
comment on column SER_CATALOGUE.cata_is_charge
  is '是否收费描述';
comment on column SER_CATALOGUE.cata_establish
  is '服务事项法律依据描述';
comment on column SER_CATALOGUE.cata_hand_term
  is '服务事项申请条件描述';
comment on column SER_CATALOGUE.cata_app_material
  is '服务事项受理材料描述';
comment on column SER_CATALOGUE.cata_process
  is '服务事项办理流程描述';
comment on column SER_CATALOGUE.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE.createtime
  is '创建日期';
comment on column SER_CATALOGUE.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE.edittime
  is '最后修改日期';
comment on column SER_CATALOGUE.status
  is '服务事项有效状态代码(1:有效、0:无效)';
comment on column SER_CATALOGUE.cata_process_type
  is '服务事项业务办理流程图文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CATALOGUE.cata_url
  is '服务事项在线办理访问地址';
comment on column SER_CATALOGUE.cata_process_pic
  is '服务事项业务办理流程图文件地址';
comment on column SER_CATALOGUE.cata_bigimg
  is '服务事项大图标文件地址';
comment on column SER_CATALOGUE.cata_smallimg
  is '服务事项小图标文件地址';
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
  is '[0102]服务事项-服务事项环节附件信息表';
comment on column SER_CATALOGUE_ATTACH.cata_attch_id
  is '服务事项环节附件内码(编码规则:UUID)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_code
  is '服务事项环节附件编码(编码规则:自定义)';
comment on column SER_CATALOGUE_ATTACH.cata_detail_id
  is '服务事项办理环节内码(编码规则:UUID)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_name
  is '服务事项环节附件名称';
comment on column SER_CATALOGUE_ATTACH.cata_attch_pingyin
  is '服务事项环节附件拼音';
comment on column SER_CATALOGUE_ATTACH.cata_attch_desc
  is '服务事项环节附件内容描述';
comment on column SER_CATALOGUE_ATTACH.cata_attch_sort
  is '服务事项环节附件排序号';
comment on column SER_CATALOGUE_ATTACH.cata_attch_filetype
  is '服务事项环节附件文件扩展名(例如:PNG、JPG、BMP、GIF、DOC、DOCX)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_filesize
  is '服务事项环节附件文件尺寸(单位:byte 字节)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_file_location
  is '服务事项环节附件文件地址';
comment on column SER_CATALOGUE_ATTACH.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE_ATTACH.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE_ATTACH.createtime
  is '创建日期';
comment on column SER_CATALOGUE_ATTACH.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE_ATTACH.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE_ATTACH.edittime
  is '最后修改日期';
comment on column SER_CATALOGUE_ATTACH.status
  is '服务事项环节附件有效状态代码(1:有效、0:无效)';
comment on column SER_CATALOGUE_ATTACH.cata_attch_file_blob
  is '服务事项环节附件文件';
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
  is '[0101]服务事项-服务事项办理环节信息表';
comment on column SER_CATALOGUE_DETAIL.cata_detail_id
  is '服务事项办理环节内码(编码规则:UUID)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_code
  is '服务事项办理环节编码(编码规则:自定义)';
comment on column SER_CATALOGUE_DETAIL.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_name
  is '服务事项办理环节名称';
comment on column SER_CATALOGUE_DETAIL.cata_detail_pingyin
  is '服务事项办理环节拼音';
comment on column SER_CATALOGUE_DETAIL.cata_detail_desc
  is '服务事项办理环节内容描述';
comment on column SER_CATALOGUE_DETAIL.cata_detail_sort
  is '服务事项办理环节排序号';
comment on column SER_CATALOGUE_DETAIL.cata_detail_bigimgtype
  is '服务事项办理环节大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_smalltype
  is '服务事项办理环节小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CATALOGUE_DETAIL.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE_DETAIL.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE_DETAIL.createtime
  is '创建日期';
comment on column SER_CATALOGUE_DETAIL.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_CATALOGUE_DETAIL.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_CATALOGUE_DETAIL.edittime
  is '最后修改日期';
comment on column SER_CATALOGUE_DETAIL.status
  is '服务事项办理环节有效状态代码(1:有效、0:无效)';
comment on column SER_CATALOGUE_DETAIL.cata_detail_bigimg
  is '服务事项办理环节大图标文件地址';
comment on column SER_CATALOGUE_DETAIL.cata_detail_smallimg
  is '服务事项办理环节小图标文件地址';
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
  is '[0103]服务事项-服务事项收藏记录信息表';
comment on column SER_COLLECT.collect_id
  is '服务事项收藏记录内码(编码规则:UUID)';
comment on column SER_COLLECT.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_COLLECT.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_COLLECT.createtime
  is '创建日期';
comment on column SER_COLLECT.status
  is '服务事项收藏记录状态代码(1:有效、0:无效)';
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
  is '[0097]服务事项-服务事项服务对象分类信息表';
comment on column SER_CONSUMER_TYPE.consumer_type_id
  is '服务事项服务对象分类内码(编码规则:UUID)';
comment on column SER_CONSUMER_TYPE.bus_area_code
  is '服务事项服务对象分类所属地区编码';
comment on column SER_CONSUMER_TYPE.consumer_type_code
  is '服务事项服务对象分类编码(编码规则:自定义)';
comment on column SER_CONSUMER_TYPE.consumer_type_name
  is '服务事项服务对象分类名称';
comment on column SER_CONSUMER_TYPE.consumer_type_sort
  is '服务事项服务对象分类组内排序号';
comment on column SER_CONSUMER_TYPE.consumer_type_pingyin
  is '服务事项服务对象分类拼音';
comment on column SER_CONSUMER_TYPE.consumer_type_desc
  is '服务事项服务对象分类内容描述';
comment on column SER_CONSUMER_TYPE.consumer_type_bigimgtype
  is '服务事项服务对象分类大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CONSUMER_TYPE.consumer_type_smalltype
  is '服务事项服务对象分类小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_CONSUMER_TYPE.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_CONSUMER_TYPE.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_CONSUMER_TYPE.createtime
  is '创建日期';
comment on column SER_CONSUMER_TYPE.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_CONSUMER_TYPE.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_CONSUMER_TYPE.edittime
  is '最后修改日期';
comment on column SER_CONSUMER_TYPE.status
  is '服务事项服务对象分类有效状态代码(1:有效、0:无效)';
comment on column SER_CONSUMER_TYPE.consumer_type_bigimg
  is '服务事项服务对象分类大图标文件地址';
comment on column SER_CONSUMER_TYPE.consumer_type_smallimg
  is '服务事项服务对象分类小图标文件地址';
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
  is '[0099]服务事项-服务事项受理部门基本信息表';
comment on column SER_DEPARTMENT.department_id
  is '服务事项受理部门内码(编码规则:UUID)';
comment on column SER_DEPARTMENT.department_address
  is '服务事项受理部门地址描述';
comment on column SER_DEPARTMENT.department_longitude
  is '服务事项受理部门地址经度';
comment on column SER_DEPARTMENT.department_latitude
  is '服务事项受理部门地址纬度';
comment on column SER_DEPARTMENT.department_tel
  is '服务事项受理部门联系电话';
comment on column SER_DEPARTMENT.department_linkman
  is '服务事项受理部门联系人姓名';
comment on column SER_DEPARTMENT.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_DEPARTMENT.createtime
  is '创建日期';
comment on column SER_DEPARTMENT.status
  is '服务事项受理部门有效状态代码(1:有效、0:无效)';
comment on column SER_DEPARTMENT.department_name
  is '服务事项受理部门名称';
comment on column SER_DEPARTMENT.department_img_type
  is '服务事项受理部门外景照片文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_DEPARTMENT.groupid
  is '服务事项受理部门所属机构内码(用户中心中的机构)';
comment on column SER_DEPARTMENT.department_route
  is '服务事项受理部门交通路线描述';
comment on column SER_DEPARTMENT.department_work_time
  is '服务事项受理部门工作时间描述';
comment on column SER_DEPARTMENT.department_img
  is '服务事项受理部门外景照片文件地址';
comment on column SER_DEPARTMENT.department_name_abb
  is '服务事项受理部门简称';
comment on column SER_DEPARTMENT.department_desc
  is '服务事项受理部门简介描述';
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
  is '[0105]服务事项-服务事项业务办理信息表';
comment on column SER_DESK.desk_id
  is '服务事项业务办理内码(编码规则:UUID)';
comment on column SER_DESK.desk_code
  is '服务事项业务办理编码(编码规则:自定义)';
comment on column SER_DESK.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_DESK.userid
  is '劳动者个人内码(编码规则:UUID)';
comment on column SER_DESK.createtime
  is '服务事项业务办理开始时间';
comment on column SER_DESK.status
  is '服务事项业务办理有效状态代码(1 启动 2受理 3办结）';
comment on column SER_DESK.desk_desc
  is '服务事项业务办理内容描述';
comment on column SER_DESK.yw_id
  is '服务事项业务办理经办系统业务编号';
comment on column SER_DESK.version
  is '版本号';
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
  is '[0096]服务事项-服务事项类型分类信息表';
comment on column SER_EVENT_TYPE.event_type_id
  is '服务事项类型分类内码(编码规则:UUID)';
comment on column SER_EVENT_TYPE.bus_area_code
  is '服务事项类型分类所属地区编码';
comment on column SER_EVENT_TYPE.event_type_code
  is '服务事项类型分类编码(编码规则:自定义)';
comment on column SER_EVENT_TYPE.event_type_name
  is '服务事项类型分类名称';
comment on column SER_EVENT_TYPE.event_type_sort
  is '服务事项类型分类组内排序号';
comment on column SER_EVENT_TYPE.event_type_pingyin
  is '服务事项类型分类拼音';
comment on column SER_EVENT_TYPE.event_type_desc
  is '服务事项类型分类内容描述';
comment on column SER_EVENT_TYPE.event_type_bigimgtype
  is '服务事项类型分类大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_EVENT_TYPE.event_type_smalltype
  is '服务事项类型分类小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_EVENT_TYPE.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_EVENT_TYPE.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_EVENT_TYPE.createtime
  is '创建日期';
comment on column SER_EVENT_TYPE.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_EVENT_TYPE.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_EVENT_TYPE.edittime
  is '最后修改日期';
comment on column SER_EVENT_TYPE.status
  is '服务事项类型分类有效状态代码(1:有效、0:无效)';
comment on column SER_EVENT_TYPE.parent_event_type_id
  is '父级服务事项类型分类内码';
comment on column SER_EVENT_TYPE.event_type_bigimg
  is '服务事项类型分类大图标文件地址';
comment on column SER_EVENT_TYPE.event_type_smallimg
  is '服务事项类型分类小图标文件地址';
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
  is '[0098]服务事项-服务事项行政类型分类信息表';
comment on column SER_POWER_TYPE.power_type_id
  is '服务事项行政类型分类内码(编码规则:UUID)';
comment on column SER_POWER_TYPE.bus_area_code
  is '服务事项行政类型分类所属地区编码';
comment on column SER_POWER_TYPE.power_type_code
  is '服务事项行政类型分类编码(编码规则:自定义)';
comment on column SER_POWER_TYPE.power_type_name
  is '服务事项行政类型分类名称';
comment on column SER_POWER_TYPE.power_type_sort
  is '服务事项行政类型分类组内排序号';
comment on column SER_POWER_TYPE.power_type_pingyin
  is '服务事项行政类型分类拼音';
comment on column SER_POWER_TYPE.power_type_desc
  is '服务事项行政类型分类内容描述';
comment on column SER_POWER_TYPE.power_type_bigimgtype
  is '服务事项行政类型分类大图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_POWER_TYPE.power_type_smalltype
  is '服务事项行政类型分类小图标文件扩展名(例如:PNG、JPG、BMP、GIF)';
comment on column SER_POWER_TYPE.userid
  is '创建人内网账户内码(用户中心中的人员)';
comment on column SER_POWER_TYPE.groupid
  is '创建人所属机构内码(用户中心中的机构)';
comment on column SER_POWER_TYPE.createtime
  is '创建日期';
comment on column SER_POWER_TYPE.edituserid
  is '最后修改人内网账户内码(用户中心中的人员)';
comment on column SER_POWER_TYPE.editgroupid
  is '最后修改人所属机构内码(用户中心中的机构)';
comment on column SER_POWER_TYPE.edittime
  is '最后修改日期';
comment on column SER_POWER_TYPE.status
  is '服务事项行政类型分类有效状态代码(1:有效、0:无效)';
comment on column SER_POWER_TYPE.power_type_bigimg
  is '服务事项行政类型分类大图标文件地址';
comment on column SER_POWER_TYPE.power_type_smallimg
  is '服务事项行政类型分类小图标文件地址';
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
  is '[0106]服务事项-服务事项业务办理评价信息表';
comment on column SER_SCORE.score_id
  is '服务事项业务办理评价内码(编码规则:UUID)';
comment on column SER_SCORE.cata_id
  is '服务事项内码(编码规则:UUID)';
comment on column SER_SCORE.score_level
  is '服务事项业务办理评价代码';
comment on column SER_SCORE.userid
  is '劳动者个人内码(编码规则:UUID)';
comment on column SER_SCORE.createtime
  is '业务办理评价时间';
comment on column SER_SCORE.status
  is '服务事项业务办理评价有效状态代码(1:有效、0:无效)';
comment on column SER_SCORE.score_desc
  is '服务事项业务办理评价内容描述';
comment on column SER_SCORE.score_feedback_desc
  is '服务事项受理部门反馈内容描述';
comment on column SER_SCORE.score_feedback_userid
  is '服务事项受理部门反馈人员内码';
comment on column SER_SCORE.score_feedback_time
  is '服务事项受理部门反馈时间';
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
  is '接口管理之渠道基本信息表';
comment on column SYS_API_CHANNEL.channel_id
  is '渠道基本信息表编号,生成规则uuid';
comment on column SYS_API_CHANNEL.channel_code
  is '渠道编码编码规则QD+序列';
comment on column SYS_API_CHANNEL.channel_name
  is '渠道名称';
comment on column SYS_API_CHANNEL.channel_type
  is '渠道类型';
comment on column SYS_API_CHANNEL.channel_describe
  is '渠道描述';
comment on column SYS_API_CHANNEL.channel_icon
  is '渠道图标';
comment on column SYS_API_CHANNEL.channel_status
  is '渠道状态(待审核、开启、注销)';
comment on column SYS_API_CHANNEL.userid
  is '经办人编码';
comment on column SYS_API_CHANNEL.addtime
  is '经办时间';
comment on column SYS_API_CHANNEL.auditid
  is '审核人';
comment on column SYS_API_CHANNEL.audittime
  is '审核时间';
comment on column SYS_API_CHANNEL.channel_appkey
  is '渠道KEY';
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
  is '接口管理之渠道与接口关联表';
comment on column SYS_API_CHANNEL_INTERFACE.channel_interface_id
  is '关联表编号(uuid)';
comment on column SYS_API_CHANNEL_INTERFACE.channel_id
  is '渠道编号';
comment on column SYS_API_CHANNEL_INTERFACE.interface_id
  is '接口编号';
comment on column SYS_API_CHANNEL_INTERFACE.userid
  is '经办人编码';
comment on column SYS_API_CHANNEL_INTERFACE.addtime
  is '经办时间';
comment on column SYS_API_CHANNEL_INTERFACE.isvalid
  is '是否有效';
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
  is '服务管理-服务基本信息表';
comment on column SYS_API_INTERFACE.interface_id
  is '服务基本信息编号(uuid)';
comment on column SYS_API_INTERFACE.interface_code
  is '服务基本信息编码生成规则JK+序列';
comment on column SYS_API_INTERFACE.interface_url
  is '服务地址';
comment on column SYS_API_INTERFACE.interface_type
  is '服务大类类型';
comment on column SYS_API_INTERFACE.interface_name
  is '服务名称';
comment on column SYS_API_INTERFACE.isoffical
  is '是否正式接口服务';
comment on column SYS_API_INTERFACE.interface_network
  is '服务支持网络类型(内网、互联网)';
comment on column SYS_API_INTERFACE.interface_potocol
  is '服务协议(rest、webservice、socket、hessian、dubbo)';
comment on column SYS_API_INTERFACE.interface_status
  is '服务状态(待审核、开启、注销)';
comment on column SYS_API_INTERFACE.userid
  is '经办人编码';
comment on column SYS_API_INTERFACE.addtime
  is '经办时间';
comment on column SYS_API_INTERFACE.auditid
  is '审核人';
comment on column SYS_API_INTERFACE.audittime
  is '审核时间';
comment on column SYS_API_INTERFACE.interface_detail_type
  is '服务小类类型';
comment on column SYS_API_INTERFACE.par_interface_id
  is '父服务基本信息编号(uuid)';
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
  is '系统管理之业务文件记录表';
comment on column SYS_BUS_FILE_RECORD.bus_uuid
  is '业务文件记录表编号';
comment on column SYS_BUS_FILE_RECORD.file_uuid
  is '文件上传记录表编号';
comment on column SYS_BUS_FILE_RECORD.file_bus_id
  is '文件所属业务编号';
comment on column SYS_BUS_FILE_RECORD.file_bus_type
  is '文件所属业务业务类型';
comment on column SYS_BUS_FILE_RECORD.bus_status
  is '文件上传状态';
comment on column SYS_BUS_FILE_RECORD.bus_addtime
  is '业务发生时间';
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
  is '系统管理之网站运行异常日志';
comment on column SYS_ERRORLOG.logid
  is '日志编号(uuid)';
comment on column SYS_ERRORLOG.logtime
  is '发生时间';
comment on column SYS_ERRORLOG.stackmsg
  is '异常栈信息';
comment on column SYS_ERRORLOG.message
  is '日志标题';
comment on column SYS_ERRORLOG.exceptiontype
  is '异常类型';
comment on column SYS_ERRORLOG.usergent
  is 'user-agent';
comment on column SYS_ERRORLOG.ipaddr
  is '客户端ip地址';
comment on column SYS_ERRORLOG.referer
  is 'refer';
comment on column SYS_ERRORLOG.url
  is '请求的地址';
comment on column SYS_ERRORLOG.userid
  is '当前操作人员id';
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
  is '系统管理之excel数据导入状态表上传EXCEL文件业务类型参数表';
comment on column SYS_EXCELTYPE.businesstype
  is 'excel上传业务类型编号';
comment on column SYS_EXCELTYPE.typename
  is 'excel上传业务类型名称';
comment on column SYS_EXCELTYPE.vsbeanname
  is 'excel解析处理业务类名 如fuPingExcelImportService';
comment on column SYS_EXCELTYPE.mincolumns
  is 'excel解析列宽度 如 6';
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
  is '系统管理之excel数据导入状态表';
comment on column SYS_EXCEL_BATCH.excel_batch_id
  is 'excel导入状态表编号(uuid)';
comment on column SYS_EXCEL_BATCH.excel_batch_number
  is 'excel导入批次号。生成规则年月日时分秒毫秒';
comment on column SYS_EXCEL_BATCH.excel_batch_total_count
  is 'excel导入数据总量';
comment on column SYS_EXCEL_BATCH.excel_batch_error_count
  is 'excel导入数据错误数量';
comment on column SYS_EXCEL_BATCH.excel_batch_begin_time
  is 'excel导入数据开始时间';
comment on column SYS_EXCEL_BATCH.excel_batch_end_time
  is 'excel导入数据结束时间';
comment on column SYS_EXCEL_BATCH.excel_batch_cost
  is 'excel导入数据耗费时间(秒）';
comment on column SYS_EXCEL_BATCH.excel_batch_file_path
  is 'excel导入文件路径';
comment on column SYS_EXCEL_BATCH.excel_batch_file_length
  is 'excel导入文件大小';
comment on column SYS_EXCEL_BATCH.excel_batch_excel_type
  is 'excel导入类型';
comment on column SYS_EXCEL_BATCH.excel_batch_aae011
  is 'excel导入经办人';
comment on column SYS_EXCEL_BATCH.excel_batch_status
  is 'excel导入状态导入步骤(-1默认状态 0转换xlsx 1导入临时表 2解析临时表 3导入完成 4导入失败)';
comment on column SYS_EXCEL_BATCH.excel_batch_data_status
  is 'excel导入数据状态(0-100)';
comment on column SYS_EXCEL_BATCH.excel_batch_rt_code
  is 'excel导入数据校验是否成功';
comment on column SYS_EXCEL_BATCH.excel_batch_rt_msg
  is 'excel导入数据校验错误原因';
comment on column SYS_EXCEL_BATCH.excel_batch_file_name
  is 'excel导入文件原文件名';
comment on column SYS_EXCEL_BATCH.excel_error_file_path
  is 'excel导出文件文件路径';
comment on column SYS_EXCEL_BATCH.excel_error_file_download
  is 'excel导出文件文件生成状（0正在生成 1生成成功可下载 2 生成失败）';
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
  is '系统管理之文件上传记录表';
comment on column SYS_FILE_RECORD.file_uuid
  is '文件上传记录表编号(uuid)';
comment on column SYS_FILE_RECORD.file_name
  is '文件名称';
comment on column SYS_FILE_RECORD.file_length
  is '文件大小单位(byte)';
comment on column SYS_FILE_RECORD.file_addtime
  is '文件上传时间';
comment on column SYS_FILE_RECORD.file_path
  is '文件上传角色路径';
comment on column SYS_FILE_RECORD.file_status
  is '文件状态(0无效 1有效审核通过)';
comment on column SYS_FILE_RECORD.file_md5
  is '文件md5,用于判断是否重复上传';
comment on column SYS_FILE_RECORD.file_type
  is '文件类型';
comment on column SYS_FILE_RECORD.file_bus_id
  is '文件所属业务编号';
comment on column SYS_FILE_RECORD.file_bus_type
  is '文件所属业务业务类型';
alter table SYS_FILE_RECORD
  add constraint PK_SYS_FILE_RECORD primary key (FILE_UUID);

prompt
prompt Creating table SYS_GRID
prompt ================