package com.mingsheng.utils;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

            int intFlag = (int)(Math.random() * 1000000);

            String flag = String.valueOf(intFlag);
            if (flag.length() == 6 && flag.substring(0, 1).equals("9"))
            {
                System.out.println(intFlag);
            }
            else
            {
                intFlag = intFlag + 100000;
                System.out.println(intFlag);
            }
    }
}
