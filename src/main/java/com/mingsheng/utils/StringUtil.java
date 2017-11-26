package com.mingsheng.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串工具类
 */
public class StringUtil {
	
	public static final int TYPE_ALPHANUM = 1;
	public static final int TYPE_ALL = 2;
	
	public static final String ALPHANUM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final String ALL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()-_=+{}[]|\\;':\"<>,.?/";
	
	public static String randomString(int type, int length) {
		String chars;
		if (TYPE_ALPHANUM == type) {
			chars = ALPHANUM_CHARS;
		} else {
			chars = ALL_CHARS;
		}
		int charsLen = chars.length();
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int x = RandomUtils.nextInt(charsLen);
			sb.append(chars.charAt(x));
		}
		return sb.toString();
	}
	
	/**
	 * 随机字符串不包含  o O 0 l 1 I 
	 * @param length
	 * @return
	 */
	public static String randomStrNooO0l1I(int length) {
		String chars = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
		int charsLen = chars.length();
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int x = RandomUtils.nextInt(charsLen);
			sb.append(chars.charAt(x));
		}
		return sb.toString();
	}
	
	public static String randomUUID() {
		byte[] data = toByteArray(java.util.UUID.randomUUID());
		String s = Base64.encodeBase64URLSafeString(data);
		return s.split("=")[0];
	}

	private static byte[] toByteArray(java.util.UUID uuid) {
		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		byte[] buffer = new byte[16];

		for (int i = 0; i < 8; i++) {
			buffer[i] = (byte) (msb >>> 8 * (7 - i));
		}
		for (int i = 8; i < 16; i++) {
			buffer[i] = (byte) (lsb >>> 8 * (7 - i));
		}
		return buffer;
	}
	
	private static java.util.UUID toUUID(byte[] data) {
		long msb = 0;
		long lsb = 0;
		for (int i = 0; i < 8; i++) {
			msb = (msb << 8) | (data[i] & 0xff);
		}
		for (int i = 8; i < 16; i++) {
			lsb = (lsb << 8) | (data[i] & 0xff);
		}
		return new java.util.UUID(msb, lsb);
	}
	
	public static String toStandardUUID(String base64UUID) {
		byte[] data = Base64.decodeBase64(base64UUID);
		return toUUID(data).toString();
	}
	
	public static String fromStandardUUID(String standardUUID) {
		byte[] data = toByteArray(java.util.UUID.fromString(standardUUID));
		return Base64.encodeBase64String(data);
	}
	
	public static boolean isNumeric(String str) {
		while(true){
			if(str.indexOf("0")==0){
				str = str.substring(1);
			}else{
				break;
			}
		}
		Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 生成订单号
	 * @return
	 */
	public static String getOrderNum(){
		String orderNum = "";
		Random r = new Random();
		String code = "";
		for(int i = 1;i <= 3;i++){
			code += String.valueOf(r.nextInt(10));
		}
		orderNum = DateUtils.getNowTime("yyyyMMddHHmmss") + code;
		return orderNum;
	}

	public static String randomCode1(int length) {
		String chars = "ABCDEFGHJKLMNPQRSTVWXY";
		int charsLen = chars.length();
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int x = RandomUtils.nextInt(charsLen);
			sb.append(chars.charAt(x));
		}
		return sb.toString();
	}

	public static String randomCode2(int length) {
		String chars = "23456789";
		int charsLen = chars.length();
		StringBuffer sb = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int x = RandomUtils.nextInt(charsLen);
			sb.append(chars.charAt(x));
		}
		return sb.toString();
	}

	public static String shuffleForSortingString(String s) {
		char[] c = s.toCharArray();
		List<Character> lst = new ArrayList<Character>();
		for (int i = 0; i < c.length; i++) {
			lst.add(c[i]);
		}
		Collections.shuffle(lst);
		String resultStr = "";
		for (int i = 0; i < lst.size(); i++) {
			resultStr += lst.get(i);
		}
		return resultStr;
	}
	
	public static void main(String[] args) {
		System.out.println(randomString(TYPE_ALL, 32));
		System.out.println(randomString(TYPE_ALL, 32));
		System.out.println(randomString(TYPE_ALPHANUM, 8));
		System.out.println(randomString(TYPE_ALL, 16));

		/*String str = randomCode1(2)+randomCode2(3);
		str = shuffleForSortingString(str);
		System.out.println(str);*/
	}

}
