package com.hao.controller;

import com.hao.entity.UserInf;
import com.hao.service.impl.UserInfServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: 罗泽豪
 * @Date: 2022/1/10 14:22
 * @Description: 进行上传下载文件
 */
@Controller
public class UpAndDown {

    @GetMapping( "/download")
    public ResponseEntity<byte[]> download(
            HttpServletRequest request,
            String filename
    ) throws IOException {
        String realPath = request.getServletContext().getRealPath("/test");//获取下载文件的路径
        File file = new File(realPath, filename);//把下载文件构成一个文件处理   filename:前台传过来的文件名称

        HttpHeaders headers = new HttpHeaders();//设置头信息
        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");//设置响应的文件名

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    @ResponseBody //不写会默认返回当前路径！！
    public HashMap<String, Object> upload(
            MultipartFile file,
            HttpServletRequest req
    ) throws Exception, IOException{
        System.out.println("file数据："+file);
        //接收文件数据
        System.out.println(file.getContentType());//  image/jpeg   获取上传文件的类型
        System.out.println(file.getName());//image  获取file标签的name属性  <input type="file" name="image" >
        System.out.println(file.getOriginalFilename());//1.jpg   获取上传文件的名称

        //获取到上传的文件数据
        String contentType = file.getContentType();

        HashMap<String, Object> hashMap = new HashMap<>();
        //判断上传文件是否为空
        if (contentType==null) {
            hashMap.put("path","");
            hashMap.put("status",400);
        }

        //动态获取上传文件夹的路径
        ServletContext context = req.getServletContext();
        String realPath = context.getRealPath("/test");//获取本地存储位置的绝对路径

        String filename = file.getOriginalFilename();//获取上传时的文件名称
        filename = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(filename);//创建一个新的文件名称    getExtension(name):获取文件后缀名

        File f= new File(realPath, filename);
        file.transferTo(f);//将上传的文件存储到指定位置

        hashMap.put("path",filename);
        hashMap.put("status",200);

        /**
         * 进行写入数据库!
         */
        return hashMap;
    }




    @Autowired
    UserInfServiceImpl userInfService;
    
    @PostMapping("/uploadBase64")
    @ResponseBody
    public String base64UpLoad(
            @RequestBody HashMap<String,Object>map,
            HttpServletRequest request
    ){
        try{
            String base64Data = map.get("base64Data").toString();
            String userId = map.get("userId").toString();
            String dataPrix = "";
            String data = "";
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = userId + suffix;

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流
                System.out.println(request.getServletContext().getRealPath("/upload"));
                FileUtils.writeByteArrayToFile(new File(request.getServletContext().getRealPath("/uploadFace"), tempFileName), bs);
            }catch(Exception ee){
                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
            }
            UserInf userInf = new UserInf();
            userInf.setId(Integer.valueOf(userId));
            userInf.setFacepath(tempFileName);
            userInfService.updateUserInF(userInf);
            
            

            return "success";
        }catch (Exception e) {
            return "error";
        }
    }
    

}
