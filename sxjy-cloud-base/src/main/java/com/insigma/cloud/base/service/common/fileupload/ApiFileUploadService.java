package com.insigma.cloud.base.service.common.fileupload;

import com.github.pagehelper.PageInfo;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;


/**
 * ApiFileUploadService
 */
public interface ApiFileUploadService {

	SFileRecord uploadFile(MultipartFile multipartFile, String file_bus_type, String file_bus_name, String file_bus_id) throws Exception;

	SFileRecord uploadFile(MultipartFile multipartFile, String file_bus_type, String file_bus_name,String file_bus_id, String desc) throws Exception;

	SFileType getFileType(String businessType);

	SuploadFile uploadFileForBzh(MultipartFile multipartFile, String file_bus_type, String file_bus_name, String file_bus_id, String businessType, String fileRandomFlag) throws Exception;

	byte[] download(String file_path) ;

	void deleteFileByID(String aaa001);

	SuploadFile uploadImageForBzh(MultipartFile file, String file_name, String file_bus_type, String file_bus_id, String fileRandomFlag, String desc) throws Exception;

	PageInfo<SuploadFile> selectFileByUserId( String aaa004);

	List<SuploadFile> getAllFileByUserId(String aaa010, String aaa011);

	void deleteFileByID(SuploadFile suploadFile);

	List<SuploadFile> getFileUploadInfoList(SuploadFile suploadFile);

	List<SuploadFile> getFileUploadInfoListAll(SuploadFile suploadFile);

	FileNumberInfo getUploadFileInfoNumber(SuploadFile suploadFile);

	PageInfo<SysExcelBatch>  getExcelBatchList(SysExcelBatch sExcelBatch );

	SysExcelBatch getExcelBatchById(String  excel_batch_id);

}
