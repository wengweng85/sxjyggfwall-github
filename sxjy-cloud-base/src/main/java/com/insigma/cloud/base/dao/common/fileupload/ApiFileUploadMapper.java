package com.insigma.cloud.base.dao.common.fileupload;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.insigma.mvc.model.SuploadFile;

/**
 * 文件上传相关
 */
@Mapper
public interface ApiFileUploadMapper {

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
    
}
