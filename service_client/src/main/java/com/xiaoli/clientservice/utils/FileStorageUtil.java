package com.xiaoli.clientservice.utils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaoli
 * @Date 2022/4/6 20:48
 * @Version 1.0
 */
//文件保存工具类
public class FileStorageUtil {
    //上架商品文件图片到本地
    public static List<String> saveImageToLocal(MultipartFile[] files) {
        List<String> imageUrls = new ArrayList<>();
        //遍历文件
        for (MultipartFile file : files) {
            if(!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    //要存入本地的地址放到path里面
                    Path path = Paths.get( FileUtils.UPLOAD_FOLDER + "/");
                    //如果没有files文件夹，则创建
                    if (!Files.isWritable(path)) {
                        Files.createDirectories(path);
                    }
                    FileUtils.getFileByBytes(bytes, FileUtils.UPLOAD_FOLDER, file.getOriginalFilename());
                    //上传的文件路径
                    int flag = FileStorageUtil.RunTimeElement();
                    String filePath = "";
                    if( flag == 0 ) {
                        filePath = FileUtils.UPLOAD_FOLDER + "\\" + file.getOriginalFilename();
                    }else  if(flag == 1) {
                        filePath = FileUtils.UPLOAD_FOLDER + "/" + file.getOriginalFilename();
                    }
                    imageUrls.add(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return  imageUrls;
    }

    /**
     * 返回当前运行的环境
     * 0：代表window环境
     * 1：代表linux环境
     * 2:代表其他环境
     */
    public static int RunTimeElement() {
        String os = System.getProperty("os.name");
        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            return 0;
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            return 1;
        } else { //其它操作系统
            return 2;
        }
    }
}


