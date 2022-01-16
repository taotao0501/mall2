/**
 * Copyright (C), 2015-2018, 浩鲸科技有限公司 FileName: StringUtil Author: zhouyl5 Date: 2018/12/18/018 18:32
 * Description: 阿里OSS工具类 History: <author> <time> <version> <desc> 作者姓名 修改时间 版本号 描述
 */
package com.mall.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈字符串工具类〉
 *
 * @author wangwei
 * @create 2021.12.18
 * @since 1.0.0
 */
public final class StringUtil { 
	/**
	 * email地址校验正则表达式<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;{@value}
	 */
	public final static String  MAIL_REG_EXPRESS = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/**
	 * 电话号码校验正则表达式<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;{@value}
	 */
	public final static String  TEL_REG_EXPRESS = "^\\d+$";//"^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	/**
	 * 随机串候选池<br/> 
	 * &nbsp;&nbsp;&nbsp;&nbsp;{@value}
	 */
	public final static char []ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
	      'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
	      'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
	      'x', 'y', 'z', '0', '1' };//最后又重复两个0和1，因为需要凑足数组长度为64


	private StringUtil(){}
	
	/**
	 * 字符串对象判空
	 * @param value 判定对象
	 * @return	判定结果
	 */
	public static boolean isNull(String value) {
		return (value == null || value.trim().length() == 0);
	}

	/**
	 *字符串对象判非空
	 * @param value 判定对象
	 * @return	判定结果
	 * @see #isNull(String)
	 */
	public static boolean isNotNull(String value) {
		return !isNull(value);
	}
	
	/**
	 * 判定字符串对象是否为数字组成
	 * @param value	判定对象
	 * @return			判定结果，true/false
	 */
	public static boolean isNumeric(String value){ 
		if(isNotNull(value)&&value.matches("\\d*")){
			return true; 
		}
		return false;
	}
	

	/**
	 * 将字符串形式的数字格式化成 Long 型,如果需要的字符串为空或者为非数字形式则返回 null.
	 * @param value	字符串形式的数字.
	 * @return			Long 或者 null.
	 */
	public static Long parseLong(String value) {
		Long longValue = null ;
		if(isNotNull(value)) {
			try {
				longValue = Long.parseLong(value) ;
			} catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return longValue ;
	}
	
	/**
	 * 将字符串形式的数字格式化成 Integer 型,如果需要的字符串为空或者为非数字形式则返回 null.
	 * @param value	字符串形式的数字.
	 * @return			Integer 或者 null.
	 */
	public static Integer parseInteger(String value) {
		Integer intValue = null ;
		if(isNotNull(value)) {
			try {
				intValue = Integer.valueOf(value) ;
			} catch(NumberFormatException e) {}
		}
		return intValue ;
	}
	
	/**
	 * 将字符串形式的数字格式化成 Double 型,如果需要的字符串为空或者为非数字形式则返回 null.
	 * @param value	字符串形式的数字.
	 * @return			Double 或者 null.
	 */
	public static Double parseDouble(String value) {
		Double doubleValue = null ;
		if(isNotNull(value)) {
			try {
				doubleValue = Double.valueOf(value) ;
			} catch(NumberFormatException e) {}
		}
		return doubleValue ;
	}
	


	/**
	 * 获取指定长度的随机串
	 * @param length	长度值
	 * @return				指定长度的随机串
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		if (length > 0) {
			int index = 0;
			char[] temp = new char[length];
			int num = random.nextInt();
			for (int i = 0; i < length % 5; i++) {
				temp[index++] = ch[num & 63];// 取后面六位，记得对应的二进制是以补码形式存在的。
				num >>= 6;// 63的二进制为:111111
				// 为什么要右移6位？因为数组里面一共有64个有效字符。为什么要除5取余？因为一个int型要用4个字节表示，也就是32位。
			}
			for (int i = 0; i < length / 5; i++) {
				num = random.nextInt();
				for (int j = 0; j < 5; j++) {
					temp[index++] = ch[num & 63];
					num >>= 6;
				}
			}
			return new String(temp, 0, length);
		} 
		return "";
	}

	/**
	  * 验证邮箱地址是否正确<br/>
	  * &nbsp;&nbsp;&nbsp;&nbsp;验证正则: {@value #MAIL_REG_EXPRESS}
	  * @param email	要验证的邮箱地址
	  * @return	验证结果
	  */
	 public static boolean isEmail(String email){
		 Pattern regex = Pattern.compile(MAIL_REG_EXPRESS);
		 Matcher matcher = regex.matcher(email);
		 return matcher.matches();
	 }
	 
	 /**
	  * 验证手机号码是否正确<br/>
	  * &nbsp;&nbsp;&nbsp;&nbsp;验证正则: {@value #TEL_REG_EXPRESS}
	  * @param tel		要验证的手机号码
	  * @return			验证结果,true/false
	  */
	 public static boolean isTel(String tel){
		 Pattern regex = Pattern.compile(TEL_REG_EXPRESS);
		 Matcher matcher = regex.matcher(tel);
		 return matcher.matches();
	 }


}
