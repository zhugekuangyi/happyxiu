package com.mingsheng.utils;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        String [] a = {"15","21","22","23","24","25","26","27","28","29","30","31","32","33","34","36","37","38","39","40","41","42","43","44","45","46"};

        String [] b = {"5w3Hmt6P", "akshasN4", "aksuasN4","cAphaQN9","cjshasN4","cjshosN4","csphaQN9","dqphasN6","dsphasN6","dsphasN8","dsphasN9","kgsfasN4","kksfasN4","kssfasN4","nkshasN4","smphaQN9","smphasN9","ssphaQN9", "vjshasN4", "vkshasN4", "vsphasN6","vssfasN4","vsshasN4","vsshasN5","vsshasN6"};

        String [] c={"外屏破碎（显示和触摸正常）",
                "前置摄像头故障",
                "SIM卡无法识别/无法取出",
                "外屏破碎（内屏有灰角）",
                "闪光灯故障",
                "异常发热",
                "屏幕完整（显示或触摸不正常）",
                "扬声器无声/声音异常",
                "充电过慢",
                "无法充电",
                "待机续航时间短",
                "其他问题",
                "有电流声",
                "后置摄像头故障",
                "进水",
                "后壳变形损坏",
                "中框变形损坏",
                "屏幕其他问题",
                "静音键故障",
                "开机键故障",
                "送话器失灵",
                "声音键故障",
                "声音其他问题",
                "连接耳机播放无声/耳机声音异常",
                "听筒无声/声音异常"};
        String []d={"更换新屏",
                "更换前置摄像头",
                "工程师检修",
                "更换新屏",
                "维修主板",
                "工程师检修",
                "工程师检修",
                "更换扬声器",
                "更换电池",
                "更换尾插",
                "更换电池",
                "工程师检修",
                "工程师检修",
                "更换后置摄像头",
                "工程师检修",
                "更换后壳",
                "更换中框",
                "工程师检修",
                "更换静音键排",
                "更换开机键排线",
                "更换尾插",
                "更换声音键排",
                "工程师检修",
                "维修主板",
                "更换听筒"};
        String []e={"1",
                "6",
                "8",
                "1",
                "6",
                "8",
                "1",
                "7",
                "3",
                "3",
                "3",
                "8",
                "8",
                "6",
                "8",
                "2",
                "2",
                "1",
                "5",
                "5",
                "7",
                "5",
                "7",
                "7",
                "7"};
        String ar="";
        for (int i = 0; i <a.length ; i++) {
            for (int j = 0; j <b.length ; j++) {
                ar+="insert into repairResult  (questionId,mobileId,questionResult,description,ctime,questionType) value (\""+b[j]+"\",\""+a[i]+"\",\""+d[j]+"\",\""+c[j]+"\",now(),\""+e[j]+"\");";
            }
        }
        System.out.println(ar);
    }
}
