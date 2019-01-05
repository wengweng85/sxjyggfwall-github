package com.insigma.cloud.base.service.common.fileupload;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.FileNumberInfo;
import com.insigma.mvc.model.SuploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * ApiFileUploadService
 */
public interface ApiFileUploadService {

	    SuploadFile uploadFilejy(MultipartFile multipartFile, String file_bus_type, String file_bus_name, String file_bus_id, String businessType, String fileRandomFlag) throws Exception;
	    
	    public byte[] download(String file_path) ;
	    
	    void deleteFileByID(String aaa001);

	    SuploadFile uploadImage(MultipartFile file,String file_name,String file_bus_type, String file_bus_id, String fileRandomFlag, String desc) throws Exception;

		PageInfo<SuploadFile> selectFileByUserId(String aaa002, String aaa004);

		List<SuploadFile> getAllFileByUserId(String aaa002, String aaa010, String aaa011);
		
		void deleteFileByID(SuploadFile suploadFile);

		List<SuploadFile> getFileUploadInfoList(SuploadFile suploadFile);
	
		List<SuploadFile> getFileUploadInfoListAll(SuploadFile suploadFile);

		FileNumberInfo getUploadFileInfoNumber(SuploadFile suploadFile);

}
