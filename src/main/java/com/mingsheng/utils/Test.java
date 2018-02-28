package com.mingsheng.utils;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        String id = MathUtil.getId();
        while (Integer.parseInt(id)<666666){
            id=MathUtil.getId();
        }

        System.out.println(id);
    }
}
