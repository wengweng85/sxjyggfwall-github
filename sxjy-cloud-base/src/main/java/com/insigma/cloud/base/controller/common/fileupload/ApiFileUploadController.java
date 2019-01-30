package com.insigma.cloud.base.controller.common.fileupload;

import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.annotation.UserLog;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.FileNumberInfo;
import com.insigma.mvc.model.SuploadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * �ļ��ϴ�
 * 
 */

@RestController
@Api(description = "�ļ��ϴ�")
public class ApiFileUploadController {

    @Resource
    private ApiFileUploadService apiFileUploadService;

    /**
     * �ϴ��ļ�Ϊ��׼��ƽ̨
     * @param file
     * @param file_name
     * @param file_bus_type
     * @param file_bus_id
     * @param fileRandomFlag
     * @param desc
     * @return
     * @throws Exception
     */
    /**
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��׼��ƽ̨�ļ��ϴ�", notes = "��׼��ƽ̨�ļ��ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "������Ϣid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
    })
    @UserLog("��׼��ƽ̨�ļ��ϴ�")
    @PostMapping(value = "/uploadFileForBzh", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg uploadImage(
            @RequestParam("uploadFile") MultipartFile file ,
            @RequestParam("file_name") String file_name,
            @RequestParam("file_bus_type") String file_bus_type,
            @RequestParam("file_bus_id") String file_bus_id,
            @RequestParam("fileRandomFlag") String fileRandomFlag,
            @RequestParam(value = "desc",required = false) String desc
    ) throws Exception {
        return AjaxReturnMsg.success(apiFileUploadService.uploadImageForBzh( file,file_name,file_bus_type,file_bus_id,fileRandomFlag,desc));
    }


    /**
     * �ļ��ϴ�
     * @param file
     * @param file_bus_type
     * @param file_bus_id
     * @param desc
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�ļ��ϴ�", notes = "�ļ��ϴ�", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file_bus_id", value = "������Ϣid", required = true, paramType = "query"),
            @ApiImplicitParam(name = "file_name", value = "�ļ����ƣ�û�к�׺", required = true, paramType = "query")
    })
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg uploadFile(
            @RequestParam("uploadFile") MultipartFile file ,
            @RequestParam("file_bus_type") String file_bus_type,
            @RequestParam("file_bus_id") String file_bus_id,
            @RequestParam(value = "desc",required = false) String desc )
            throws Exception {
        String file_name=file.getOriginalFilename();
        return AjaxReturnMsg.success(apiFileUploadService.uploadFile(file,file_bus_type,file_name,file_bus_id,desc));
    }


    /**
     * ��ȡ�ļ�
     */
    @PostMapping(value = "/getFileUploadInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getUploadInfo(@RequestBody SuploadFile suploadFile) throws Exception {
        PageInfo<SuploadFile> pageinfo = apiFileUploadService.selectFileByUserId(suploadFile.getAaa004());
        return AjaxReturnMsg.success(pageinfo);
    }

    /**
     * ɾ���ļ�
     * @return
     */
    @PostMapping(value = "/fileUploadInfo/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg deleteFileById(@RequestBody SuploadFile suploadFile) {
        apiFileUploadService.deleteFileByID(suploadFile);
        return AjaxReturnMsg.success("�ɹ�");
    }
    /**
     * ��ȡ�ض��ļ��б�
     */
    @PostMapping(value = "/getFileUploadInfoList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFileUploadInfoList(@RequestBody SuploadFile suploadFile) throws Exception {
        List<SuploadFile> list = apiFileUploadService.getFileUploadInfoList(suploadFile);
        return AjaxReturnMsg.success(list);
    }

    /**
     * ��ȡ�ļ��б�
     */
    @PostMapping(value = "/getFileUploadInfoListAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFileUploadInfoListAll(@RequestBody SuploadFile suploadFile) throws Exception {
        List<SuploadFile> list = apiFileUploadService.getFileUploadInfoListAll(suploadFile);
        return AjaxReturnMsg.success(list);
    }


    /**
     * ��ȡ�ļ���
     */
    @PostMapping(value = "/getFileInfoByte", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFileInfoByte(@RequestBody SuploadFile suploadFile) throws Exception {
        byte[] filebyte = apiFileUploadService.download(suploadFile.getAaa007());
        suploadFile.setFilearray(filebyte);
        return AjaxReturnMsg.success(suploadFile);
    }

    /**
     * ��ѯ�ϴ�ͼƬ��չʾͼƬ����
     *
     * @param suploadFile
     */
    @PostMapping(value = "/UploadFileNumberInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getFileInfo(@RequestBody SuploadFile suploadFile) {
        FileNumberInfo fileNumberInfo = apiFileUploadService.getUploadFileInfoNumber(suploadFile);
        return AjaxReturnMsg.success(fileNumberInfo);
    }

}
