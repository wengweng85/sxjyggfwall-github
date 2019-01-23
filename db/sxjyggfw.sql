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
  is '业务表之网格信息表';
comment on column SYS_GRID.abz182
  is '网格编号';
comment on column SYS_GRID.aab301
  is '行政区划-对应s_group表groupid';
comment on column SYS_GRID.ab_bf022
  is '上级网格编号';
comment on column SYS_GRID.abz181
  is '所属机构';
comment on column SYS_GRID.aae005
  is '联系电话';
comment on column SYS_GRID.ab_bf023
  is '网格名称';
comment on column SYS_GRID.ab_bf026
  is '网格级别';
comment on column SYS_GRID.ab_bf027
  is '专委数';
comment on column SYS_GRID.aae100
  is '有效标志';
comment on column SYS_GRID.aae006
  is '联系地址';
comment on column SYS_GRID.aae004
  is '联系人';
comment on column SYS_GRID.ab_bf024
  is '监控范围';
comment on column SYS_GRID.abf013
  is '人数';
comment on column SYS_GRID.abf014
  is '网格人员数';
comment on column SYS_GRID.ab_bf025
  is '网格负责人';
comment on column SYS_GRID.abf057
  is '排序编号';
comment on column SYS_GRID.ab_bf023_1
  is '网格名称（定位用）';
comment on column SYS_GRID.isgrid
  is '是否是网格';
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
  is '系统管理之机构信息表';
comment on column SYS_GROUP_TABLE.groupid
  is '机构ID';
comment on column SYS_GROUP_TABLE.description
  is '用户组描述';
comment on column SYS_GROUP_TABLE.parentid
  is '上级结构ID';
comment on column SYS_GROUP_TABLE.org
  is '系统机构编码';
comment on column SYS_GROUP_TABLE.districtcode
  is '地区代码';
comment on column SYS_GROUP_TABLE.license
  is '机构组织机构编码';
comment on column SYS_GROUP_TABLE.groupname
  is '用户组名称';
comment on column SYS_GROUP_TABLE.principal
  is '机构负责人';
comment on column SYS_GROUP_TABLE.shortname
  is '简称';
comment on column SYS_GROUP_TABLE.address
  is '地址';
comment on column SYS_GROUP_TABLE.tel
  is '电话';
comment on column SYS_GROUP_TABLE.linkman
  is '联系人';
comment on column SYS_GROUP_TABLE.grouptype
  is '主机构类型(1行政区划 2残联机构)';
comment on column SYS_GROUP_TABLE.chargedept
  is '主管部门';
comment on column SYS_GROUP_TABLE.otherinfo
  is '其他信息';
comment on column SYS_GROUP_TABLE.owner
  is '创建者';
comment on column SYS_GROUP_TABLE.status
  is '状态';
comment on column SYS_GROUP_TABLE.createdate
  is '创建时间';
comment on column SYS_GROUP_TABLE.grouplevel
  is '机构等级(1省、2市、3区县 4镇街道 5村社区)';
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
  is '就业公共服务业务表之接口访问日志记录表';
comment on column SYS_LOG.logid
  is '日志编号(uuid)';
comment on column SYS_LOG.logtype
  is '日志操作类型代码';
comment on column SYS_LOG.message
  is '日志标题';
comment on column SYS_LOG.logtime
  is '发生时间';
comment on column SYS_LOG.cost
  is '请求耗费时间(毫秒)';
comment on column SYS_LOG.stackmsg
  is '异常栈信息';
comment on column SYS_LOG.exceptiontype
  is '异常类型名称';
comment on column SYS_LOG.usergent
  is '代理信息';
comment on column SYS_LOG.ipaddr
  is '客户端ip地址';
comment on column SYS_LOG.referer
  is '地址来源';
comment on column SYS_LOG.url
  is '请求的地址';
comment on column SYS_LOG.userid
  is '当前操作人员编号';
comment on column SYS_LOG.cookie
  is '状态码';
comment on column SYS_LOG.appkey
  is '接口key';
comment on column SYS_LOG.method
  is '请求方法类型名称';
comment on column SYS_LOG.success
  is '请求是否成功代码';
comment on column SYS_LOG.responsemsg
  is '返回信息';
comment on column SYS_LOG.queryparam
  is '请求参数信息';
comment on column SYS_LOG.token
  is '请求jwt认证码';
comment on column SYS_LOG.interfacetype
  is '接口类型代码';
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
  is '系统管理之登录状态记录表';
comment on column SYS_LOGININF.loginhash
  is '登录信息hash信息sessionid+ip+agent';
comment on column SYS_LOGININF.logintime
  is '登录时间';
comment on column SYS_LOGININF.ip
  is '用户ip';
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
  is '系统管理之参数配置表';
comment on column SYS_PARAM.paramid
  is '参数编号(uuid)';
comment on column SYS_PARAM.paramtype
  is '参数类型';
comment on column SYS_PARAM.paramvalue
  is '参数值';
comment on column SYS_PARAM.paramname
  is '参数中文说明';
comment on column SYS_PARAM.paramisvalid
  is '参数是否有效';
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
  is '系统管理之功能模块配置表';
comment on column SYS_PERMISSION.permissionid
  is '权限表编号,编号规则uuid ';
comment on column SYS_PERMISSION.permname
  is '权限名称';
comment on column SYS_PERMISSION.permtype
  is '权限类型(1 节点 2叶子 3 按钮)';
comment on column SYS_PERMISSION.url
  is '叶子结点对应功能url(相对地址)';
comment on column SYS_PERMISSION.parentid
  is '父权限编号';
comment on column SYS_PERMISSION.enabled
  is '是否有效标志';
comment on column SYS_PERMISSION.sortnum
  is '排序页面';
comment on column SYS_PERMISSION.permdescribe
  is '权限描述';
comment on column SYS_PERMISSION.permcode
  is '权限编码';
comment on column SYS_PERMISSION.updatetime
  is '权限最后更新时间';
comment on column SYS_PERMISSION.iconcss
  is '图标样式';
comment on column SYS_PERMISSION.deleteable
  is '是否可以删除(管理员权限才可以)';
comment on column SYS_PERMISSION.isblanktarget
  is '打开窗口方式(_blank)';
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
  is '系统管理之用户角色信息表 ';
comment on column SYS_ROLE.roleid
  is '角色编号编号规则uuid';
comment on column SYS_ROLE.rolename
  is '角色名称';
comment on column SYS_ROLE.roledescribe
  is '角色描述';
comment on column SYS_ROLE.enabled
  is '是否有效标志';
comment on column SYS_ROLE.rolecode
  is '角色编码';
comment on column SYS_ROLE.updatetime
  is '最后更新时间';
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
  is '系统管理之角色与权限关联表';
comment on column SYS_ROLE_PERMISSION.id
  is '流水号 uuid ';
comment on column SYS_ROLE_PERMISSION.roleid
  is '角色id';
comment on column SYS_ROLE_PERMISSION.permissionid
  is '权限编号';
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
  is '系统管理功能之系统敏感关键字库';
comment on column SYS_SENSITIVEWORD.wordid
  is '主键';
comment on column SYS_SENSITIVEWORD.wordname
  is '关键字名字';
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
  is '系统管理之用户机构关联表';
comment on column SYS_USERGROUPREF_TABLE.usergroupid
  is 'ID';
comment on column SYS_USERGROUPREF_TABLE.userid
  is '用户ID';
comment on column SYS_USERGROUPREF_TABLE.groupid
  is '组ID';
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
  is '系统管理之用户与权限关联表';
comment on column SYS_USER_PERMISSION.id
  is '用户与权限关联表编号';
comment on column SYS_USER_PERMISSION.userid
  is '用户表流水号';
comment on column SYS_USER_PERMISSION.permissionid
  is '权限编号';
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
  is '系统管理之用户与角色关联表';
comment on column SYS_USER_ROLE.id
  is '流水号';
comment on column SYS_USER_ROLE.userid
  is '用户id';
comment on column SYS_USER_ROLE.roleid
  is '角色id';
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
  is '系统管理之平台用户表';
comment on column SYS_USER_TABLE.userid
  is '用户ID';
comment on column SYS_USER_TABLE.password
  is '密码(md5)';
comment on column SYS_USER_TABLE.username
  is '登录名';
comment on column SYS_USER_TABLE.enabled
  is '用户是否有效(1有效 0无效)';
comment on column SYS_USER_TABLE.isleader
  is '是否主账户';
comment on column SYS_USER_TABLE.cnname
  is '用户中文名称';
comment on column SYS_USER_TABLE.owner
  is '创建者';
comment on column SYS_USER_TABLE.macaddr
  is '网卡地址';
comment on column SYS_USER_TABLE.usertype
  is '主用户类型(1.管理用户、2.服务工作者-网格长 3.服务工作者-网格员 4.服务工作者-专职委员)';
comment on column SYS_USER_TABLE.otherinfo
  is '其它信息';
comment on column SYS_USER_TABLE.createdate
  is '创建时间';
comment on column SYS_USER_TABLE.mobile
  is '手机号码';
comment on column SYS_USER_TABLE.phone
  is '联系电话';
comment on column SYS_USER_TABLE.email
  is '邮箱地址';
comment on column SYS_USER_TABLE.address
  is '联系地址';
comment on column SYS_USER_TABLE.salt
  is '密码加密盐';
comment on column SYS_USER_TABLE.abz182
  is '网格编号';
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
  is '就业公共服务业务表之接口访问日志记录表';
comment on column S_APP_LOG.logid
  is '日志编号(uuid)';
comment on column S_APP_LOG.logtime
  is '发生时间';
comment on column S_APP_LOG.url
  is '请求的地址';
comment on column S_APP_LOG.appkey
  is '渠道编号';
comment on column S_APP_LOG.interface_id
  is '服务基本信息编号(uuid)';
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
  is '就业公共服务(外网)-单点登录应用客户端配置表';
comment on column S_APP_SSO.ssoid
  is '单点登录应用配置编号(编码规则:UUID)';
comment on column S_APP_SSO.systypeid
  is '应用系统类型编号(编码规则:UUID)';
comment on column S_APP_SSO.ssoappname
  is '单点登录应用客户端名称';
comment on column S_APP_SSO.appid
  is '单点登录应用客户端APPID';
comment on column S_APP_SSO.appsecret
  is '单点登录应用客户端APPSECRET';
comment on column S_APP_SSO.ssoappvalid
  is '是否有效';
comment on column S_APP_SSO.ssoclienturl
  is '单点登录应用客户端地址';

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
  is '[0051]就业公共服务(外网)-邮件发送日志信息表';
comment on column S_EMAILLOG.id
  is '邮件发送日志内码(编码规则:UUID)';
comment on column S_EMAILLOG.optype
  is '业务操作类型代码';
comment on column S_EMAILLOG.send_userid
  is '邮件发送者的账号内码';
comment on column S_EMAILLOG.receive_userid
  is '邮件接收者的账号内码';
comment on column S_EMAILLOG.receive_email
  is '邮件接收者电子邮箱';
comment on column S_EMAILLOG.sendtime
  is '邮件发送时间';
comment on column S_EMAILLOG.title
  is '邮件标题';
comment on column S_EMAILLOG.content
  is '邮件内容';
comment on column S_EMAILLOG.success
  is '邮件发送是否成功代码';
comment on column S_EMAILLOG.failreason
  is '邮件发送失败原因描述';
comment on column S_EMAILLOG.userlogid
  is '用户操作日志内码(编码规则:UUID)';
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
  is '[0047]就业公共服务(外网)-网站运行异常日志信息表';
comment on column S_ERRORLOG.logid
  is '网站运行异常日志内码(编码规则:UUID)';
comment on column S_ERRORLOG.logtime
  is '异常发生时间';
comment on column S_ERRORLOG.stackmsg
  is '异常栈信息描述';
comment on column S_ERRORLOG.message
  is '网站运行异常日志标题';
comment on column S_ERRORLOG.exceptiontype
  is '网站运行异常类型名称';
comment on column S_ERRORLOG.usergent
  is '用户代理信息';
comment on column S_ERRORLOG.ipaddr
  is '客户端设备IP地址';
comment on column S_ERRORLOG.url
  is '请求统一资源定位地址';
comment on column S_ERRORLOG.cookie
  is '请求用户端信息';
comment on column S_ERRORLOG.userid
  is '用户id';
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
  is '[0039]就业公共服务(外网)-上传文件业务类型参数表';
comment on column S_FILETYPE.businesstype
  is '上传文件业务类型编号';
comment on column S_FILETYPE.typename
  is '上传文件业务类型名称';
comment on column S_FILETYPE.filemaxnum
  is '上传文件数量限制';
comment on column S_FILETYPE.filemaxsize
  is '上传文件大小限制(单位:M)';
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
  is '[0040]就业公共服务(外网)-上传文件信息表';
comment on column S_FILE_RECORD.file_uuid
  is '上传文件内码(编码规则:UUID)';
comment on column S_FILE_RECORD.file_name
  is '上传文件编码(服务器上的名称,编码规则:日期时间到秒14位+6位随机字符码)';
comment on column S_FILE_RECORD.file_length
  is '上传文件大小(单位;字节)';
comment on column S_FILE_RECORD.file_addtime
  is '上传文件上传时间';
comment on column S_FILE_RECORD.file_path
  is '上传文件服务器上存储绝对路径(包括上传文件名称)';
comment on column S_FILE_RECORD.file_status
  is '上传文件状态代码';
comment on column S_FILE_RECORD.file_type
  is '上传文件扩展名';
comment on column S_FILE_RECORD.file_rel_path
  is '上传文件服务器上存储相对路径(包括上传文件名称)';
comment on column S_FILE_RECORD.file_bus_id
  is '业务上传文件所属业务编号';
comment on column S_FILE_RECORD.file_bus_type
  is '业务上传文件所属业务业务类型代码';
comment on column S_FILE_RECORD.bus_addtime
  is '业务发生时间';
comment on column S_FILE_RECORD.file_bus_name
  is '业务上传文件原名';
comment on column S_FILE_RECORD.file_bus_description
  is '业务上传文件描述';
comment on column S_FILE_RECORD.file_rel_small_path
  is '上传文件小文件上传地址';
comment on column S_FILE_RECORD.file_base64
  is '上传文件base64文件格式';
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
  is '[]就业公共服务(外网)-(框架表)用户组信息表';
comment on column S_GROUP_TABLE.groupid
  is '机构ID';
comment on column S_GROUP_TABLE.description
  is '用户组描述';
comment on column S_GROUP_TABLE.parentid
  is '上级结构ID';
comment on column S_GROUP_TABLE.org
  is '系统机构编码';
comment on column S_GROUP_TABLE.districtcode
  is '地区代码';
comment on column S_GROUP_TABLE.name
  is '用户组名称';
comment on column S_GROUP_TABLE.principal
  is '机构负责人';
comment on column S_GROUP_TABLE.shortname
  is '简称';
comment on column S_GROUP_TABLE.address
  is '地址';
comment on column S_GROUP_TABLE.tel
  is '电话';
comment on column S_GROUP_TABLE.linkman
  is '联系人';
comment on column S_GROUP_TABLE.chargedept
  is '主管部门';
comment on column S_GROUP_TABLE.otherinfo
  is '其他信息';
comment on column S_GROUP_TABLE.owner
  is '创建者';
comment on column S_GROUP_TABLE.status
  is '状态';
comment on column S_GROUP_TABLE.createdate
  is '创建时间';

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
  is '就业公共服务业务表之接口访问日志记录表';
comment on column S_LOG.logid
  is '日志编号(uuid)';
comment on column S_LOG.logtype
  is '日志操作类型代码';
comment on column S_LOG.message
  is '日志标题';
comment on column S_LOG.logtime
  is '发生时间';
comment on column S_LOG.cost
  is '请求耗费时间(毫秒)';
comment on column S_LOG.stackmsg
  is '异常栈信息';
comment on column S_LOG.exceptiontype
  is '异常类型名称';
comment on column S_LOG.usergent
  is '代理信息';
comment on column S_LOG.ipaddr
  is '客户端ip地址';
comment on column S_LOG.referer
  is '地址来源';
comment on column S_LOG.url
  is '请求的地址';
comment on column S_LOG.userid
  is '当前操作人员编号';
comment on column S_LOG.cookie
  is '状态码';
comment on column S_LOG.appkey
  is '渠道编号';
comment on column S_LOG.method
  is '请求方法类型名称';
comment on column S_LOG.success
  is '请求是否成功代码';
comment on column S_LOG.responsemsg
  is '返回信息';
comment on column S_LOG.queryparam
  is '请求参数信息';
comment on column S_LOG.token
  is '请求jwt认证码';
comment on column S_LOG.interfacetype
  is '服务编号';
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
  is '系统管理之登录状态记录表';
comment on column S_LOGININF.loginhash
  is '登录信息hash信息sessionid+ip+agent';
comment on column S_LOGININF.logintime
  is '登录时间';
comment on column S_LOGININF.ip
  is '用户ip';
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
  is '系统管理之功能模块配置表';
comment on column S_PERMISSION.permissionid
  is '权限表编号,编号规则uuid ';
comment on column S_PERMISSION.permname
  is '权限名称';
comment on column S_PERMISSION.permtype
  is '权限类型(1 节点 2叶子 3 按钮)';
comment on column S_PERMISSION.url
  is '叶子结点对应功能url(相对地址)';
comment on column S_PERMISSION.parentid
  is '父权限编号';
comment on column S_PERMISSION.enabled
  is '是否有效标志';
comment on column S_PERMISSION.sortnum
  is '排序页面';
comment on column S_PERMISSION.permdescribe
  is '权限描述';
comment on column S_PERMISSION.permcode
  is '权限编码';
comment on column S_PERMISSION.updatetime
  is '权限最后更新时间';
comment on column S_PERMISSION.iconcss
  is '图标样式';
comment on column S_PERMISSION.deleteable
  is '是否可以删除(管理员权限才可以)';
comment on column S_PERMISSION.isblanktarget
  is '打开窗口方式(_blank)';
comment on column S_PERMISSION.systypecode
  is '应用系统类型块编码(编码规则:S+5位数字)';
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
  is '[0042]就业公共服务(外网)-(框架表)角色信息表';
comment on column S_ROLE.roleid
  is '角色内码(编码规则:UUID)';
comment on column S_ROLE.rolecode
  is '角色编码(编码规则:大写字母R+10位自定义名称)';
comment on column S_ROLE.rolename
  is '角色名称';
comment on column S_ROLE.roledescribe
  is '角色描述';
comment on column S_ROLE.createby
  is '创建人内码';
comment on column S_ROLE.createdate
  is '创建时间';
comment on column S_ROLE.updateby
  is '更新人内码';
comment on column S_ROLE.updatetime
  is '更新时间';
comment on column S_ROLE.enabled
  is '是否有效标志代码';
comment on column S_ROLE.systypecode
  is '应用系统类型块编码(编码规则:S+5位数字)';
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
  is '[0044]就业公共服务(外网)-(框架表)角色与功能模块关联表';
comment on column S_ROLE_PERMISSION.id
  is '角色与功能模块关联内码(编码规则:UUID)';
comment on column S_ROLE_PERMISSION.roleid
  is '角色内码(编码规则:UUID)';
comment on column S_ROLE_PERMISSION.permissionid
  is '功能模块内码(编码规则:UUID)';
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
  is '[0049]就业公共服务(外网)-短信发送日志信息表';
comment on column S_SMSLOG.id
  is '短信发送日志内码(编码规则:UUID)';
comment on column S_SMSLOG.optype
  is '业务操作类型代码';
comment on column S_SMSLOG.send_userid
  is '短信发送者的账号内码';
comment on column S_SMSLOG.receive_userid
  is '短信接收者的账号内码';
comment on column S_SMSLOG.receive_mobile
  is '短信接收者手机号码';
comment on column S_SMSLOG.sendtime
  is '短信发送时间';
comment on column S_SMSLOG.content
  is '短信内容';
comment on column S_SMSLOG.success
  is '短信发送是否成功代码';
comment on column S_SMSLOG.failreason
  is '短信发送失败原因描述';
comment on column S_SMSLOG.userlogid
  is '用户操作日志内码(编码规则:UUID)';
comment on column S_SMSLOG.smsgroup_id
  is '短信群发内码';
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
  is '就业公共服务(外网)-(框架表)应用系统类型表';
comment on column S_SYSTYPE.systypeid
  is '应用系统类型编号(编码规则:UUID)';
comment on column S_SYSTYPE.systypecode
  is '应用系统类型块编码(编码规则:S+5位数字)';
comment on column S_SYSTYPE.sysname
  is '应用系统名称';
comment on column S_SYSTYPE.sysdesc
  is '应用系统描述';
comment on column S_SYSTYPE.sysurl
  is '应用系统访问地址';

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
  is '附件上传(最多跑一次)';
comment on column S_UPLOADFILE.aaa001
  is 'uuid(内码)';
comment on column S_UPLOADFILE.aaa002
  is '用户编号';
comment on column S_UPLOADFILE.aaa003
  is '文件名称';
comment on column S_UPLOADFILE.aaa004
  is '文件类型编码';
comment on column S_UPLOADFILE.aaa005
  is '文件类型';
comment on column S_UPLOADFILE.aaa006
  is '文件大小';
comment on column S_UPLOADFILE.aaa007
  is '文件路径';
comment on column S_UPLOADFILE.aaa008
  is '创建日期';
comment on column S_UPLOADFILE.aaa009
  is '有效标志';
comment on column S_UPLOADFILE.aaa010
  is '业务类型';
comment on column S_UPLOADFILE.aaa011
  is '文件上传的批次';
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
  is '[0041]就业公共服务(外网)-(框架表)登录账户信息表';
comment on column S_USER.userid
  is '外网登录账户内码(编码规则:UUID)';
comment on column S_USER.username
  is '外网登录账户登录名';
comment on column S_USER.password
  is '外网登录账户密码';
comment on column S_USER.usertype
  is '外网登录账户类型代码';
comment on column S_USER.email
  is '登录电子邮件';
comment on column S_USER.mobile
  is '登录手机号';
comment on column S_USER.lastlogintime
  is '最后登录时间';
comment on column S_USER.lastloginip
  is '最后登录访问设备IP地址';
comment on column S_USER.enabled
  is '是否有效标志代码';
comment on column S_USER.baseinfoid
  is '实体对象基本信息内码(分别对应CD01、CE01、机构 内码)';
comment on column S_USER.isblacklist
  is '是否加入黑名单代码';
comment on column S_USER.reason
  is '加入黑名单原因描述';
comment on column S_USER.registtime
  is '注册日期';
comment on column S_USER.logintimes
  is '登录次数';
comment on column S_USER.openid
  is '微信OPENID';
comment on column S_USER.fromaddr
  is '账户注册来源代码(1招聘求职网 2网上申报网 3管理平台分配)';
comment on column S_USER.ismainaccount
  is '是否主账户代码';
comment on column S_USER.aae013
  is '账户信息备注';
comment on column S_USER.iscertified
  is '账户是否认证';
comment on column S_USER.name
  is '真实名称';
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
  is '[]就业公共服务(外网)-(框架表)用户用户组关联表';
comment on column S_USERGROUPREF.usergroupid
  is 'ID';
comment on column S_USERGROUPREF.userid
  is '用户ID';
comment on column S_USERGROUPREF.groupid
  is '组ID';
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
  is '就业公共服务业务表之网站用户操作日志记录表';
comment on column S_USERLOG.logid
  is '日志编号(uuid)';
comment on column S_USERLOG.optype
  is '用户操作类型代码';
comment on column S_USERLOG.logstime
  is '用户操作日记记录开始时间';
comment on column S_USERLOG.logetime
  is '用户操作日志记录结束时间';
comment on column S_USERLOG.message
  is '用户操作描述';
comment on column S_USERLOG.opstatus
  is '用户操作状态(1成功 0失败)';
comment on column S_USERLOG.opreason
  is '用户操作失败原因描述';
comment on column S_USERLOG.aae011
  is '外网账户内码';
comment on column S_USERLOG.aae012
  is '内网账户内码';
comment on column S_USERLOG.eec117
  is '访问设备操作系统类型代码';
comment on column S_USERLOG.eec118
  is '访问设备浏览器类型代码';
comment on column S_USERLOG.eec119
  is '访问设备IP地址';
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
  is '[0045]就业公共服务(外网)-(框架表)账户与功能模块关联表';
comment on column S_USER_PERMISSION.id
  is '账户与功能模块关联内码(编码规则:UUID)';
comment on column S_USER_PERMISSION.userid
  is '外网登录账户内码(编码规则:UUID)';
comment on column S_USER_PERMISSION.permissionid
  is '功能模块内码(编码规则:UUID)';
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
  is '就业公共服务系统框架表之用户与角色关联表';
comment on column S_USER_ROLE.id
  is '账户与角色关联内码(编码规则:UUID)';
comment on column S_USER_ROLE.userid
  is '用户编号';
comment on column S_USER_ROLE.roleid
  is '角色编号';
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
  is '[0047]就业公共服务(外网)-验证码信息表';
comment on column S_VERCORD.vercodeid
  is '验证码内码(编码规则:UUID)';
comment on column S_VERCORD.userlogid
  is '用户操作日志内码(编码规则:UUID)';
comment on column S_VERCORD.optype
  is '业务操作类型代码';
comment on column S_VERCORD.mobile
  is '手机号码';
comment on column S_VERCORD.e_mail
  is '电子邮箱';
comment on column S_VERCORD.vercode
  is '验证码';
comment on column S_VERCORD.createdate
  is '创建时间';
comment on column S_VERCORD.enddate
  is '失效时间';
comment on column S_VERCORD.aae100
  is '有效标志代码';
comment on column S_VERCORD.inputcode
  is '用户输入的验证码';
comment on column S_VERCORD.inputtime
  is '用户录入时间';
comment on column S_VERCORD.verresult
  is '验证结果类型代码';
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
