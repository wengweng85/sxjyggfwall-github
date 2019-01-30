package com.insigma.cloud.base.dao.common.fileupload;

import java.util.List;
import java.util.Map;

import com.insigma.mvc.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * �ļ��ϴ����
 */
@Mapper
public interface ApiFileUploadMapper {

    /**
     * ��ȡ�ļ�������Ϣ
     * @param businesstype
     * @return
     */
    SFileType getFileTypeInfo(String businesstype);

    /**
     * �����ļ���¼
     * @param sfilerecord
     */
    void saveFileRecord( SFileRecord sfilerecord);

    /**
     * ����ҵ���¼
     * @param sfilerecord
     */
    void saveBusRecord(SFileRecord sfilerecord);

    /**
     * ����ҵ���¼
     * @param sfilerecord
     */
    void updateBusRecord(SFileRecord sfilerecord);

    /**
     * ͨ���ļ�md5��ѯ�ļ�
     * @param file_md5
     * @return
     */
    SFileRecord getFileUploadRecordByFileMd5(String file_md5);

    /**
     * ͨ��ҵ��id��ѯ�ļ�
     * @param bus_uuid
     * @return
     */
    SFileRecord getBusFileRecordByBusId(String bus_uuid);

    /**
     * ��ȡ�ļ��б���Ϣ
     * @param sFileRecord
     * @return
     */
    List<SFileRecord> getBusFileRecordListByBusId(SFileRecord sFileRecord);

    /**
     * ͨ���ļ�ҵ����ɾ���ļ�ҵ���¼
     * @param bus_uuid
     * @return
     */
    int deleteFileByBusUuid(String bus_uuid);

    /**
     * ����ɾ������
     * @param ids
     * @return
     */
    int batDeleteData(String[] ids);

    /**
     *  ͨ���ļ�id�������ҵ��id��ҵ��״̬Ϊ��Ч״̬
     * @return
     */
    int batupdateBusIdByBusUuidArray(Map<String,Object> map);

    /**
     * ͨ���ļ����ɾ���ļ�
     * @param file_uuid
     * @return
     */
    int deleteFileByFileUuid(String file_uuid);

	/**
     * ��ѯ�ļ��б�
     * @param suploadFile
     * @return
     */
    List<SuploadFile> selectFileByUserId(SuploadFile suploadFile);

    /**
     * ɾ���ļ�
     *
     * @param suploadFile
     */
    void deleteFileByID(SuploadFile suploadFile);

    /**
     * ��ѯ�ϴ�ͼƬ���ٸ�
     *
     * @param suploadFile
     */
    int getFileCount(SuploadFile suploadFile);

    /**
     * ��ѯ�ļ��б�
     * @param suploadFile
     * @return
     */
    List<SuploadFile> selectFileByUser(SuploadFile suploadFile);
    
    /**
     * �����ļ���¼(�����һ��)
     * @param suploadFile
     */
    void saveFileMessage(SuploadFile suploadFile);

    /**
     * ͨ��ҵ��id��ѯ�ļ������һ��)
     * @param aaa001
     * @return
     */
    SuploadFile getSuploadFileByID(String aaa001);


    /**
     * ����excel�ļ��ϴ���¼��
     * @param sExcelBatch
     */
    public void saveExelBatch(SysExcelBatch sExcelBatch);


    /**
     * �����ļ���¼��
     * @param sExcelBatch
     */
    public void updateExelBatch(SysExcelBatch sExcelBatch);

    /**
     * ͨ��id��ȡ���κ�
     * @param excel_batch_id
     * @return
     */
    public SysExcelBatch getExcelBatchById(String excel_batch_id);


    public SysExcelBatch getExcelBatchByNumber(String number);
    /**
     * ��ҳ��ѯ
     * @param sExcelBatch
     * @return
     */
    public List<SysExcelBatch> getExcelBatchList(SysExcelBatch sExcelBatch);

    /**
     * ͨ�����κ�ɾ��������Ϣ
     * @param number
     * @return
     */
    public int deleteExcelBatchByNumber(String number);



    public int updateExelBatchErrorFilePath(SysExcelBatch sExcelBatch);


    /**
     * ��ȡ�ļ�������Ϣ
     * @param businesstype
     * @return
     */
    SExcelType getExcelFileTypeInfo(String businesstype);
    
}
