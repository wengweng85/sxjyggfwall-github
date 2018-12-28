package com.insigma.cloud.base.dao.common.fileupload;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.insigma.mvc.model.SuploadFile;

/**
 * �ļ��ϴ����
 */
@Mapper
public interface ApiFileUploadMapper {

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
    
}
