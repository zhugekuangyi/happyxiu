package com.mingsheng.utils;

/**
 * @Description: 获取时间戳
 */
public class CTM {
	
	public static String get(){
		String str="";
	    try {
			str= String.valueOf(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static long getCtmToS(){
		String str="";
	    try {
			str= String.valueOf(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Long.parseLong(str.substring(0, str.length()-3));
	}
	
}
