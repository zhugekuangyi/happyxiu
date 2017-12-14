package com.mingsheng.utils;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {

        File file = new File("/Users/chuqi/Desktop/1.png");

        CommonsMultipartFile file1 = new CommonsMultipartFile(file);

        OSSUtil.upload_img(file);
    }
}
