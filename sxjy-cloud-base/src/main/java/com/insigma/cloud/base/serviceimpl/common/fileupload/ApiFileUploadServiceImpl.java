package com.insigma.cloud.base.serviceimpl.common.fileupload;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.insigma.cloud.base.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.fastdfs.FastDFSClient;
import com.insigma.mvc.model.*;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;


/**
 * ApiFileUploadServiceImpl
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ApiFileUploadServiceImpl  implements ApiFileUploadService {

    @Resource
    private ApiFileUploadMapper apiFileUploadMapper;

    @Override
    public SFileRecord uploadFile(MultipartFile multipartFile, String file_bus_type, String file_bus_name, String file_bus_id) throws Exception {
        return uploadFile(multipartFile, file_bus_type, file_bus_name, file_bus_id, null);
    }

    @Override
    public SFileRecord uploadFile(MultipartFile multipartFile, String file_bus_type, String file_bus_name, String file_bus_id, String desc) throws Exception {
        SFileRecord sfilerecord = null;
        if (StringUtil.isNotEmpty(file_bus_name)) {
            file_bus_name = URLDecoder.decode(file_bus_name, "utf-8");
        }
        if (StringUtil.isNotEmpty(desc)) {
            desc = URLDecoder.decode(desc, "utf-8");
        }

        SFileType sFileType = apiFileUploadMapper.getFileTypeInfo(file_bus_type);
        if (sFileType == null) {
            throw new Exception("文件类型编号不存在");
        }

        long MAX_SIZE = (long) (sFileType.getFileMaxSize() * 1024 * 1024);
        try {
            if (multipartFile.getSize() > MAX_SIZE) {
                throw new Exception("文件尺寸超过规定大小:" + sFileType.getFileMaxSize() + "M");
            }
            // 得到去除路径的文件名
            String originalFilename = multipartFile.getOriginalFilename();
            int indexofdoute = originalFilename.lastIndexOf(".");
            if (indexofdoute < 0) {
                throw new Exception("文件格式错误");
            }
            // 文件的后缀
            String endfix = originalFilename.substring(indexofdoute).toLowerCase();
           /* String[] arr = AppConfig.getProperties("limitFileType").split(",");
            if (!Arrays.asList(arr).contains(endfix)) {
                throw new Exception("文件格式不正确,请确认");
            }*/

            //上传并记录日志
            if (StringUtil.isNotEmpty(file_bus_id)) {
                SFileRecord condition = new SFileRecord();
                condition.setFile_bus_id(file_bus_id);
                condition.setFile_bus_type(file_bus_type);
                //查询已上传文件列表
                List<SFileRecord> list_file = apiFileUploadMapper.getBusFileRecordListByBusId(condition);

                //如果未上传过文件
                if (list_file.size() == 0) {
                    sfilerecord = upload(file_bus_type, file_bus_id, multipartFile);
                    sfilerecord.setFile_bus_name(file_bus_name);
                    sfilerecord.setFile_bus_description(desc);
                    apiFileUploadMapper.saveBusRecord(sfilerecord);
                    return sfilerecord;
                }

                if (sFileType.getFileMaxNum() == 1) {
                    //如果只能上传一个文件，则删除原有文件，更新原有记录
                    sfilerecord = upload(file_bus_type, file_bus_id, multipartFile);
                    SFileRecord oldFile = list_file.get(0);
                    //删除原有文件
                    deleteFile(oldFile);
                    sfilerecord.setBus_uuid(oldFile.getBus_uuid());
                    //更新原有记录
                    sfilerecord.setFile_bus_name(file_bus_name);
                    sfilerecord.setFile_bus_description(desc);
                    apiFileUploadMapper.updateBusRecord(sfilerecord);
                    return sfilerecord;
                } else if (list_file.size() >= sFileType.getFileMaxNum()) {
                    // 如果上传的文件已达到最大文件数，则提示
                    throw new Exception("已达到最大上传文件数");
                } else {
                    sfilerecord = upload(file_bus_type, file_bus_id, multipartFile);
                    sfilerecord.setFile_bus_name(file_bus_name);
                    sfilerecord.setFile_bus_description(desc);
                    apiFileUploadMapper.saveBusRecord(sfilerecord);
                    return sfilerecord;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理文件尺寸过大异常
            if (e instanceof FileUploadBase.SizeLimitExceededException) {
                throw new Exception("文件尺寸超过规定大小:" + sFileType.getFileMaxSize() + "M");
            }
            throw new Exception(e.getMessage());
        }
        return sfilerecord;
    }

    @Override
    public SFileType getFileType(String businessType) {
        SFileType sFileType = apiFileUploadMapper.getFileTypeInfo(businessType);
        return  sFileType;
    }

    /*
    * 上传文件
    *
    * @param file_bus_type
    * @param file_bus_id
    * @param multipartFile
    * @return
    * @throws Exception
    */
    public SFileRecord upload(String file_bus_type, String file_bus_id, MultipartFile multipartFile) throws Exception {
        String originalFilename=multipartFile.getOriginalFilename();
        String path=FastDFSClient.saveFile(multipartFile);
        String fileName = path.substring(path.lastIndexOf("/")+1);
        String file_path =  "/" + path;
        SFileRecord sfilerecord=new SFileRecord();
        sfilerecord.setFile_name(fileName);
        sfilerecord.setFile_length(multipartFile.getSize());
        sfilerecord.setFile_path(file_path);
        sfilerecord.setFile_status("1");
        sfilerecord.setFile_type(originalFilename.substring(originalFilename.lastIndexOf(".")+1));
        sfilerecord.setFile_rel_path(file_path);
        //保存文件记录
        apiFileUploadMapper.saveFileRecord(sfilerecord);
        sfilerecord.setFile_bus_name(originalFilename);// 文件原名
        sfilerecord.setFile_bus_id(file_bus_id);
        sfilerecord.setFile_bus_type(file_bus_type);
        return sfilerecord;
    }

    /**
     * 删除文件记录和真实文件
     *
     * @param sfilerecord
     */
    public void deleteFile(SFileRecord sfilerecord) {
        try {
            //删除文件记录
            apiFileUploadMapper.deleteFileByFileUuid(sfilerecord.getFile_uuid());
            String file_path = sfilerecord.getFile_path();
            String groupName = getGroupFormFilePath(file_path);
            String remoteFileName = getFileNameFormFilePath(file_path);
            FastDFSClient.deleteFile(groupName, remoteFileName);
            //FileUtil.delFileOnExist(sfilerecord.getFile_path());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public SuploadFile uploadFileForBzh(MultipartFile multipartFile, String file_bus_type, String file_name, String userid, String businessType, String fileRandomFlag) throws Exception {
        SuploadFile suploadFile = null;
        if (null!=file_name&&!file_name.equals("")) {
            file_name = URLDecoder.decode(file_name, "utf-8");
        }
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            int indexofdoute = originalFilename.lastIndexOf(".");
            if (indexofdoute < 0) {
                throw new Exception("�ļ���ʽ����");
            }
            String endfix = originalFilename.substring(indexofdoute).toLowerCase();
            if (null!=userid&&!userid.equals("")) {
                suploadFile = uploadjy(businessType,file_name,file_bus_type, userid, fileRandomFlag,multipartFile);
                return suploadFile;
            }

        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return suploadFile;
    }

    /**
     */
    @Override
    public void deleteFileByID(String aaa001) {
        SuploadFile suploadFile = apiFileUploadMapper.getSuploadFileByID(aaa001);
        String file_path = suploadFile.getAaa007();
        String groupName = getGroupFormFilePath(file_path);
        String remoteFileName = getFileNameFormFilePath(file_path);
        try{
            FastDFSClient.deleteFile(groupName, remoteFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file_bus_type
     * @param userid
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public SuploadFile uploadjy(String businessType,String file_name,String file_bus_type, String userid, String fileRandomFlag,  MultipartFile multipartFile) throws Exception {
        String originalFilename=multipartFile.getOriginalFilename();
        String path=FastDFSClient.saveFile(multipartFile);
        String file_path = "/" + path.substring(path.indexOf("group1",0),path.length());
        SuploadFile suploadFile=new SuploadFile();
        suploadFile.setAaa002(userid);
        suploadFile.setAaa003(file_name);
        suploadFile.setAaa004(file_bus_type);
        suploadFile.setAaa005(originalFilename.substring(originalFilename.lastIndexOf(".")+1));
        suploadFile.setAaa006(multipartFile.getSize());
        suploadFile.setAaa007(file_path);
        suploadFile.setAaa009("1");
        suploadFile.setAaa010(businessType);
        suploadFile.setAaa011(fileRandomFlag);
        apiFileUploadMapper.saveFileMessage(suploadFile);
        return suploadFile;
    }


    /**
     * @param file
     * @param file_name
     * @param file_bus_type
     * @param file_bus_id
     * @param fileRandomFlag
     * @param desc
     * @return
     * @throws Exception
     */
    @Override
    public SuploadFile uploadImageForBzh(MultipartFile file, String file_name, String file_bus_type, String file_bus_id, String fileRandomFlag, String desc) throws Exception{
        String userid = SUserUtil.getUserId();
        SuploadFile suploadFile = uploadFileForBzh(file,file_bus_type, file_name, userid,file_bus_id,fileRandomFlag);
        return suploadFile;
    }


    /**
     * @param aaa004
     */
    @Override
    public com.github.pagehelper.PageInfo<SuploadFile> selectFileByUserId(String aaa004) {
        SuploadFile suploadFile1 = new SuploadFile();
        suploadFile1.setAaa002(SUserUtil.getUserId());
        suploadFile1.setAaa004(aaa004);
        PageHelper.startPage(suploadFile1.getCurpage(), suploadFile1.getLimit());
        System.out.println(suploadFile1.getAaa002()+","+suploadFile1.getAaa004());
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile1);
        for (SuploadFile suploadFile : suploadFileList) {
            double file_length = suploadFile.getAaa006();
            suploadFile.setAaa006_str(String.format("%.2f", file_length / (1024 * 1024)) + "MB");
        }
        PageInfo<SuploadFile> pageInfo = new PageInfo<>(suploadFileList);
        return pageInfo;
    }

    /**
     *
     * @param suploadFile
     */
    @Override
    public void deleteFileByID(SuploadFile suploadFile) {
        List<SuploadFile> list = apiFileUploadMapper.selectFileByUserId(suploadFile);
        for(SuploadFile suploadFile1 : list){
            deleteFileByID(suploadFile1.getAaa001());
        }
        apiFileUploadMapper.deleteFileByID(suploadFile);
    }
    /**
     * @param suploadFile
     */
    @Override
    public List<SuploadFile> getFileUploadInfoList(SuploadFile suploadFile) {
        suploadFile.setAaa002(SUserUtil.getUserId());
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile);
        return suploadFileList;
    }

    /**
     *
     * @param suploadFile
     */
    @Override
    public List<SuploadFile> getFileUploadInfoListAll(SuploadFile suploadFile) {
        suploadFile.setAaa002(SUserUtil.getUserId());
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUser(suploadFile);
        return suploadFileList;
    }

    /**
     *
     * @param suploadFile
     */
    @Override
    public FileNumberInfo getUploadFileInfoNumber(SuploadFile suploadFile) {
        suploadFile.setAaa002(SUserUtil.getUserId());
        FileNumberInfo fileNumberInfo = new FileNumberInfo();
        int uploadImageNumber = apiFileUploadMapper.getFileCount(suploadFile);
        fileNumberInfo.setUploadImageNumber(uploadImageNumber);
        return fileNumberInfo;
    }


    /**
     */
    @Override
    public byte[] download(String file_path) {
        InputStream data = null;
        try {
            String remoteFileName = getFileNameFormFilePath(file_path);
            String groupName = getGroupFormFilePath(file_path);
            System.out.println(remoteFileName+","+groupName);
            data = FastDFSClient.downFile(groupName, remoteFileName);
            int size = data.available();
            byte[] buffer = new byte[size];
            IOUtils.read(data, buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(data);
        }
    }



    @Override
    public List<SuploadFile> getAllFileByUserId( String aaa010,String aaa011) {
        SuploadFile suploadFile1 = new SuploadFile();
        suploadFile1.setAaa002(SUserUtil.getUserId());
        suploadFile1.setAaa010(aaa010);
        suploadFile1.setAaa011(aaa011);
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile1);
        for (SuploadFile suploadFile : suploadFileList) {
            double file_length = suploadFile.getAaa006();
            suploadFile.setAaa006_str(String.format("%.2f", file_length / (1024 * 1024)) + "MB");
        }
        return suploadFileList;
    }

    /**
     * @return
     */
    public String getGroupFormFilePath(String path){
        return path.split("/")[1];
    }

    /**
     * @return
     */
    public String getFileNameFormFilePath(String path) {
        String path_temp = path.substring(path.indexOf("/")+1);
        path_temp = path_temp.substring(path_temp.indexOf("/")+1);
        return path_temp;
    }

    /**
     * 获取文件上传批次列表信息
     */
    @Override
    public PageInfo<SysExcelBatch>  getExcelBatchList(SysExcelBatch sExcelBatch) {
        PageHelper.offsetPage(sExcelBatch.getOffset(), sExcelBatch.getLimit());
        List<SysExcelBatch> list = apiFileUploadMapper.getExcelBatchList(sExcelBatch);
        PageInfo<SysExcelBatch> pageinfo = new PageInfo<SysExcelBatch>(list);
        return pageinfo;
    }

    @Override
    public SysExcelBatch getExcelBatchById(String excel_batch_id) {
        return apiFileUploadMapper.getExcelBatchById(excel_batch_id);
    }
}
