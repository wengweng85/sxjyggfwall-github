package com.insigma.cloud.base.controller.common.fileupload;

import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.FileNumberInfo;
import com.insigma.mvc.model.SuploadFile;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传
 * 
 */

@RestController
@RequestMapping("/api")
@Api(description = "文件上传")
public class ApiFileUploadController {

    @Resource
    private ApiFileUploadService apiFileUploadService;

    /**
     * 上传文件
     * @param file
     * @param file_name
     * @param file_bus_type
     * @param file_bus_id
     * @param fileRandomFlag
     * @param desc
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile/uploadImage",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxReturnMsg uploadImage(
            @RequestParam("uploadFile") MultipartFile file ,
            @RequestParam("file_name") String file_name,
            @RequestParam("file_bus_type") String file_bus_type,
            @RequestParam("file_bus_id") String file_bus_id,
            @RequestParam("fileRandomFlag") String fileRandomFlag,
            @RequestParam(value = "desc",required = false) String desc
    ) throws Exception {
        return AjaxReturnMsg.success(apiFileUploadService.uploadImage( file,file_name,file_bus_type,file_bus_id,fileRandomFlag,desc));
    }

    /**
     * 获取文件
     */
    @RequestMapping(value = "/getFileUploadInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getUploadInfo(@RequestBody SuploadFile suploadFile) throws Exception {
        PageInfo<SuploadFile> pageinfo = apiFileUploadService.selectFileByUserId(suploadFile.getAaa002(),suploadFile.getAaa004());
        return AjaxReturnMsg.success(pageinfo);
    }

    /**
     * 删除文件
     * @return
     */
    @RequestMapping(value = "/fileUploadInfo/delete", method = RequestMethod.POST)
    public AjaxReturnMsg deleteFileById(@RequestBody SuploadFile suploadFile) {
        apiFileUploadService.deleteFileByID(suploadFile);
        return AjaxReturnMsg.success("成功");
    }
    /**
     * 获取特定文件列表
     */
    @RequestMapping(value = "/getFileUploadInfoList", method = RequestMethod.POST)
    public AjaxReturnMsg getFileUploadInfoList(@RequestBody SuploadFile suploadFile) throws Exception {
        List<SuploadFile> list = apiFileUploadService.getFileUploadInfoList(suploadFile);
        return AjaxReturnMsg.success(list);
    }

    /**
     * 获取文件列表
     */
    @RequestMapping(value = "/getFileUploadInfoListAll", method = RequestMethod.POST)
    public AjaxReturnMsg getFileUploadInfoListAll(@RequestBody SuploadFile suploadFile) throws Exception {
        List<SuploadFile> list = apiFileUploadService.getFileUploadInfoListAll(suploadFile);
        return AjaxReturnMsg.success(list);
    }


    /**
     * 获取文件流
     */
    @RequestMapping(value = "/getFileInfoByte", method = RequestMethod.POST)
    public AjaxReturnMsg getFileInfoByte(@RequestBody SuploadFile suploadFile) throws Exception {
        byte[] filebyte = apiFileUploadService.download(suploadFile.getAaa007());
        suploadFile.setFilearray(filebyte);
        return AjaxReturnMsg.success(suploadFile);
    }

    /**
     * 查询上传图片和展示图片数量
     *
     * @param suploadFile
     */
    @RequestMapping(value = "/UploadFileNumberInfo", method = RequestMethod.POST)
    public AjaxReturnMsg getFileInfo(@RequestBody SuploadFile suploadFile) {
        FileNumberInfo fileNumberInfo = apiFileUploadService.getUploadFileInfoNumber(suploadFile);
        return AjaxReturnMsg.success(fileNumberInfo);
    }

}
