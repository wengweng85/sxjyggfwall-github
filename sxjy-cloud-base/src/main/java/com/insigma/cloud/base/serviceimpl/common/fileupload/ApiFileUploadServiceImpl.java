package com.insigma.cloud.base.serviceimpl.common.fileupload;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insigma.cloud.base.dao.common.fileupload.ApiFileUploadMapper;
import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.fastdfs.FastDFSClient;
import com.insigma.mvc.model.FileNumberInfo;
import com.insigma.mvc.model.SuploadFile;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
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
public class ApiFileUploadServiceImpl  implements ApiFileUploadService {

    @Resource
    private ApiFileUploadMapper apiFileUploadMapper;

    @Override
    public SuploadFile uploadFilejy(MultipartFile multipartFile, String file_bus_type, String file_name, String userid, String businessType, String fileRandomFlag) throws Exception {
        SuploadFile suploadFile = null;
        if (null!=file_name&&!file_name.equals("")) {
            file_name = URLDecoder.decode(file_name, "utf-8");
        }
        try {
            // 得到去除路径的文件名
            String originalFilename = multipartFile.getOriginalFilename();
            int indexofdoute = originalFilename.lastIndexOf(".");
            if (indexofdoute < 0) {
                throw new Exception("文件格式错误");
            }
            // 文件的后缀
            String endfix = originalFilename.substring(indexofdoute).toLowerCase();
            //上传并记录日志
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
     * 删除文件（最多跑一次）
     */
    @Override
    public void deleteFileByID(String aaa001) {
        SuploadFile suploadFile = apiFileUploadMapper.getSuploadFileByID(aaa001);
        //删除真实文件
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
     * 上传文件 fastdfs
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
        //保存文件记录
        apiFileUploadMapper.saveFileMessage(suploadFile);
        return suploadFile;
    }


    /**
     *  上传附件图片
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
    public SuploadFile uploadImage(MultipartFile file,String file_name,String file_bus_type, String file_bus_id, String fileRandomFlag, String desc) throws Exception{
        String userid = SUserUtil.getCurrentUser().getUserid();
        SuploadFile suploadFile = uploadFilejy(file,file_bus_type, file_name, userid,file_bus_id,fileRandomFlag);
        return suploadFile;
    }


    /**
     * 查询上传文件信息列表
     *
     * @param aaa002
     * @param aaa004
     */
    public com.github.pagehelper.PageInfo<SuploadFile> selectFileByUserId(String aaa002,String aaa004) {
        SuploadFile suploadFile1 = new SuploadFile();
        suploadFile1.setAaa002(aaa002);
        suploadFile1.setAaa004(aaa004);
        PageHelper.startPage(suploadFile1.getCurpage(), suploadFile1.getLimit());
        System.out.println(suploadFile1.getAaa002()+","+suploadFile1.getAaa004());
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile1);
        for (SuploadFile suploadFile : suploadFileList) {
            double file_length = suploadFile.getAaa006();
            // 单位字节转为兆
            suploadFile.setAaa006_str(String.format("%.2f", file_length / (1024 * 1024)) + "MB");
        }
        PageInfo<SuploadFile> pageInfo = new PageInfo<>(suploadFileList);
        return pageInfo;
    }

    /**
     * 删除图片和附件信息表
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
     * 查询上传文件信息
     *
     * @param suploadFile
     */
    @Override
    public List<SuploadFile> getFileUploadInfoList(SuploadFile suploadFile) {
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile);
        return suploadFileList;
    }

    /**
     * 查询上传文件信息
     *
     * @param suploadFile
     */
    @Override
    public List<SuploadFile> getFileUploadInfoListAll(SuploadFile suploadFile) {
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUser(suploadFile);
        return suploadFileList;
    }

    /**
     * 查询上传单位展示视频和图片信息
     *
     * @param suploadFile
     */
    @Override
    public FileNumberInfo getUploadFileInfoNumber(SuploadFile suploadFile) {
        FileNumberInfo fileNumberInfo = new FileNumberInfo();
        int uploadImageNumber = apiFileUploadMapper.getFileCount(suploadFile);
        fileNumberInfo.setUploadImageNumber(uploadImageNumber);
        return fileNumberInfo;
    }

    public static void main(String[] args){
        try{
            byte[] sda = new ApiFileUploadServiceImpl() .download("/group1/M00/00/05/CrxBTVvRcpSAeB_XAACdxuJeo1I081.png");
            System.out.println(sda);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 下载
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

    /**
     * 根据fastDFS返回的path得到文件的组名
     * @param path fastDFS返回的path
     * @return
     */
    public String getGroupFormFilePath(String path){
        return path.split("/")[1];
    }

    /**
     * 根据fastDFS返回的path得到文件名
     * @param path fastDFS返回的path
     * @return
     */
    public String getFileNameFormFilePath(String path) {
        String path_temp = path.substring(path.indexOf("/")+1);
        path_temp = path_temp.substring(path_temp.indexOf("/")+1);
        return path_temp;
    }

    @Override
    public List<SuploadFile> getAllFileByUserId(String aaa002, String aaa010,String aaa011) {
        SuploadFile suploadFile1 = new SuploadFile();
        suploadFile1.setAaa002(aaa002);
        suploadFile1.setAaa010(aaa010);
        suploadFile1.setAaa011(aaa011);
        List<SuploadFile> suploadFileList = apiFileUploadMapper.selectFileByUserId(suploadFile1);
        for (SuploadFile suploadFile : suploadFileList) {
            double file_length = suploadFile.getAaa006();
            // 单位字节转为兆
            suploadFile.setAaa006_str(String.format("%.2f", file_length / (1024 * 1024)) + "MB");
        }
        return suploadFileList;
    }
}
