package com.mingsheng.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 获取当前时间
 */
public class DateUtils {

	/**
	 * 查询当前时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date dt1 = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return matter1.format(dt1);
	}

	/**
	 * 查询当前时间-自定义格式
	 * 
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowTime(String format) {
		Date dt1 = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat(format);
		return matter1.format(dt1);
	}
    /**
     * 填写时间戳
     * @param fromat
     * @return
     */
	public static String getNowTimes(String fromat){
		 String s1 = DateUtils.getNowTime(fromat);
		 if(s1.length()>9){
			 s1 = s1.substring(0,DateUtils.getNowTime(fromat).length()-1);
		 }
		 String s2 = s1.substring(s1.length()-3,s1.length()-2);
		String s3 = s1.substring(s1.length()-2,s1.length()-1);      
		if(s2.equals("0")){
			String s4 = s1.substring(0,s1.length()-3) + s3;
			return s4;
		}else{
			return s1;
		}
	}

	/**
	 * 指定时间增加天数
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	/**
	 * 指定时间增加天数
	 * @param dateString
	 * @param day
	 * @return
	 */
	public static String getDateBefore(String dateString, int day) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			date = sdf.parse(dateString);
		} catch (ParseException e) {
		}  
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return matter1.format(now.getTime());
	}
	
	/**
	 * 当前时间增加天数
	 * 
	 * @param day
	 * @return
	 */
	public static String getNowTimeAddDay(int day) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day);
		return sf.format(c.getTime());
	}

	/**
	 * 当前时间增加分钟
	 */
	public static String getNowTimeAddMin(String dateStr,int min){

        long oldStamp = getCtmByDate(dateStr);
        long newStamp =  oldStamp+min*60*1000;
        return getTimeByCtm(""+newStamp);
	}

	/**
	 * 当前时间增加天数
	 * 
	 * @param day
	 * @return
	 */
	public static String getNowTimeAddDay(String dateStr, int day) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(formatDate(dateStr));
		c.add(Calendar.DAY_OF_MONTH, day);
		return sf.format(c.getTime());
	}

	/**
	 * 时间字符串转换Date
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date formatDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 时间戳转换成时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param ctm
	 * @return
	 */
	public static String getTimeByCtm(String ctm) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(Long.parseLong(ctm)));
		return date;
	}

	/**
	 * 时间转换成时间戳
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long getCtmByDate(String dateStr) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatDate(dateStr));
		return calendar.getTimeInMillis();
	}


	/**
	 * 把yyyy-MM-dd HH:mm:ss字符串转换为yyyyMMddhhmmss字符串
	 * @param time
	 * @return
	 */
	public static String getStringTimeByString(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begin="";
		try {
			Date parse = format.parse(time);
			SimpleDateFormat df  = new SimpleDateFormat("yyyyMMddhhmmss");
			begin = df.format(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return begin;
	}

	/**
	 * 把yyyyMMddhhmmss字符串转换为yyyy-MM-dd HH:mm:ss字符串
	 * @param time
	 * @return
	 */
	public static String getSTimeByString(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String begin="";
		try {
			Date parse = format.parse(time);
			SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			begin = df.format(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return begin;
	}


	/**
	 * 把yyyy-MM-dd HH:mm:ss字符串转换为yyyy年MM月dd日字符串
	 * @param time
	 * @return
	 */
	public static String getTimeByTime(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begin="";
		try {
			Date parse = format.parse(time);
			SimpleDateFormat df  = new SimpleDateFormat("yyyy年MM月dd日");
			begin = df.format(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return begin;
	}


	public static  Long getTimeStamp(){
		return new Date().getTime();
	}

	/**
	 * 距离今天多久
	 * @param ctm
	 * @return
	 */
	public static String fromTodayByCtm(String ctm) {
		long nowCTM = CTM.getCtmToS();
		long oldCTM = Long.parseLong(ctm);
		if(oldCTM >= nowCTM){
			return "刚刚";
		}
		
		long ONE_MINUTE = 60;
		long ONE_HOUR = 3600;
		long ONE_DAY = 86400;
		long ONE_MONTH = 2592000;
		long ONE_YEAR = 31104000;

		long now = new Date().getTime() / 1000;
		long ago = now - oldCTM;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时";
		else if (ago <= ONE_DAY * 2)
			return "昨天";
		else if (ago <= ONE_DAY * 3)
			return "前天";
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			return month + "个月";
		} else {
			long year = ago / ONE_YEAR;
			return year + "年前";
		}
	}


	/**
	 * 距离今天多久
	 * @param youdate
	 * @return
	 */
	public static String fromTodayByDate(String youdate) {
		long nowCTM = System.currentTimeMillis();
		long oldCTM = getCtmByDate(youdate);
		if(oldCTM >= nowCTM){
			return "刚刚";
		}
		
		long ONE_MINUTE = 60;
		long ONE_HOUR = 3600;
		long ONE_DAY = 86400;
		long ONE_MONTH = 2592000;
		long ONE_YEAR = 31104000;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(youdate);
		} catch (ParseException e) {
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时";
		else if (ago <= ONE_DAY * 2)
			return "昨天";
		else if (ago <= ONE_DAY * 3)
			return "前天";
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			return month + "个月";
		} else {
			long year = ago / ONE_YEAR;
			return year + "年前";
		}
	}

	/**
	 * 获取当月的第一天
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));

		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		return simpleFormate.format(calendar.getTime());
	}

	/**
	 * 获取当月的最后一天
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		return simpleFormate.format(calendar.getTime());
	}
	/**
	 * 指定时间增加天数
	 * @param dateString
	 * @param day
	 * @return
	 */
	public static String getNextMonthFristDay(String dateString, int day) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateString);
		} catch (ParseException e) {
		}
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		return matter1.format(now.getTime());
	}


	public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

	public static void main(String[] args) {

        try {
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = sdf.parse("2016-06-06 09:44:23");
			Date date2 = sdf.parse("2017-03-03 12:59:28");
			long l = date2.getTime() - date1.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			System.out.println(day + "天" + hour + "小时" + min +"分" + s + "秒");*/
			//System.out.println(getCtmByDate("2017-05-05"));

			//System.out.println(getNowTimeAddDay(DateUtils.getNowTime(),2));
			//System.out.println(DateUtils.getNowTimeAddDay(14));
        	
        	 /*int i= compare_date("2016-03-08 15:46", "2017-03-01 23:59");
             System.out.println("i=="+i);*/
        	
        	/*String str = "2017-03-03 15:44:53.457";
        	str = str.substring(0,str.indexOf("."));
        	System.out.println(str);
        	

    		System.out.println(getDateBefore("2017-03-03 15:44:53", 1));*/
        	 
        		
        	
        	String str = "20170304074814191863";
        	
        	System.out.println(str.substring(0,4) +"-"+ str.substring(4,6)+"-"+ str.substring(6,8)+" "+str.substring(8,10)+":01:01");
        	
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
