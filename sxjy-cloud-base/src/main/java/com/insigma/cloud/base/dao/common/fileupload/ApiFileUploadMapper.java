package com.insigma.cloud.base.dao.common.fileupload;

import java.util.List;
import java.util.Map;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文件上传相关
 */
@Mapper
public interface ApiFileUploadMapper {

    /**
     * 获取文件类型信息
     * @param businesstype
     * @return
     */
    SFileType getFileTypeInfo(String businesstype);

    /**
     * 保存文件记录
     * @param sfilerecord
     */
    void saveFileRecord( SFileRecord sfilerecord);

    /**
     * 保存业务记录
     * @param sfilerecord
     */
    void saveBusRecord(SFileRecord sfilerecord);

    /**
     * 更新业务记录
     * @param sfilerecord
     */
    void updateBusRecord(SFileRecord sfilerecord);

    /**
     * 通过文件md5查询文件
     * @param file_md5
     * @return
     */
    SFileRecord getFileUploadRecordByFileMd5(String file_md5);

    /**
     * 通过业务id查询文件
     * @param bus_uuid
     * @return
     */
    SFileRecord getBusFileRecordByBusId(String bus_uuid);

    /**
     * 获取文件列表信息
     * @param sFileRecord
     * @return
     */
    List<SFileRecord> getBusFileRecordListByBusId(SFileRecord sFileRecord);

    /**
     * 通过文件业务编号删除文件业务记录
     * @param bus_uuid
     * @return
     */
    int deleteFileByBusUuid(String bus_uuid);

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int batDeleteData(String[] ids);

    /**
     *  通过文件id数组更新业务id及业务状态为有效状态
     * @return
     */
    int batupdateBusIdByBusUuidArray(Map<String,Object> map);

    /**
     * 通过文件编号删除文件
     * @param file_uuid
     * @return
     */
    int deleteFileByFileUuid(String file_uuid);

	/**
     * 查询文件列表
     * @param suploadFile
     * @return
     */
    List<SuploadFile> selectFileByUserId(SuploadFile suploadFile);

    /**
     * 删除文件
     *
     * @param suploadFile
     */
    void deleteFileByID(SuploadFile suploadFile);

    /**
     * 查询上传图片多少个
     *
     * @param suploadFile
     */
    int getFileCount(SuploadFile suploadFile);

    /**
     * 查询文件列表
     * @param suploadFile
     * @return
     */
    List<SuploadFile> selectFileByUser(SuploadFile suploadFile);
    
    /**
     * 保存文件记录(最多跑一次)
     * @param suploadFile
     */
    void saveFileMessage(SuploadFile suploadFile);

    /**
     * 通过业务id查询文件最多跑一次)
     * @param aaa001
     * @return
     */
    SuploadFile getSuploadFileByID(String aaa001);


    /**
     * 保存excel文件上传记录表
     * @param sExcelBatch
     */
    public void saveExelBatch(SysExcelBatch sExcelBatch);


    /**
     * 更新文件记录表
     * @param sExcelBatch
     */
    public void updateExelBatch(SysExcelBatch sExcelBatch);

    /**
     * 通过id获取批次号
     * @param excel_batch_id
     * @return
     */
    public SysExcelBatch getExcelBatchById(String excel_batch_id);


    public SysExcelBatch getExcelBatchByNumber(String number);
    /**
     * 分页查询
     * @param sExcelBatch
     * @return
     */
    public List<SysExcelBatch> getExcelBatchList(SysExcelBatch sExcelBatch);

    /**
     * 通过批次号删除批次信息
     * @param number
     * @return
     */
    public int deleteExcelBatchByNumber(String number);



    public int updateExelBatchErrorFilePath(SysExcelBatch sExcelBatch);


    /**
     * 获取文件类型信息
     * @param businesstype
     * @return
     */
    SExcelType getExcelFileTypeInfo(String businesstype);
    
}
