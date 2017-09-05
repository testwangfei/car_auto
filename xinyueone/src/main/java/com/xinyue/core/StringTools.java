package com.xinyue.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTools {
	
	/**
	 * 	此方法用于判断字符串是否为 null
	 */
	public static boolean isNull(String str) {
		return (str == null ? true : false);
	}

	/**
	 * 	此方法用于判断字符串是否为 空
	 */
	public static boolean isEmpty(String str) {
		return (str.equals("") ? true : false);
	}

	/**
	*	此方法用于判断字符串是否为null或空
	 */
	public static boolean isNullOrEmpty(String str) {
		return (isNull(str) || isEmpty(str));
	}

	/**
	 * 	此方法用于获取匹配器字符串
	 */
	public static String getMatch(String source, String rex) {
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return source.substring(matcher.start(), matcher.end());
		}
		return null;

	}

	/**
	 * 获取匹配的组
	 */
	public static String getMatchGroup(String source, String rex, int groupIndex) {
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group(groupIndex);
		}
		return null;

	}

	/**
	 * 这个方法是替换所有的字符串
	 */
	public static void replaceAll(String source, String rex, String replaceBy) {
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			matcher.replaceAll(replaceBy);
		}
	}

	/**
	 * 这种方法是判断是否匹配
	 */
	public static boolean isMatch(String source, String rex) {
		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}

	// 处理中文转换为Unicode编码
	public static String chineseToUnicode(String str) {
		char[] chars = str.toCharArray();
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			result += "\\u" + Integer.toString(chars[i], 16);
		}
		return result;
	}

	// 处理Unicode编号转换为中文
	public static String unicodeToChinese(String unicode) {	
		String[] strs = unicode.split("\\\\u");	// unicode 以 \\u 开始
		String result = "";
		for (int i = 1; i < strs.length; i++) {
			result += (char) Integer.valueOf(strs[i], 16).intValue();
		}
		return result;
	}
	
	// 测试方式是否可用的临时main方法
	public static void main(String[] args) {
		System.out.println(StringTools.isEmpty(""));
		System.out.println(StringTools.isNull(""));
		System.out.println(StringTools.isNullOrEmpty(""));
	}
	
}
