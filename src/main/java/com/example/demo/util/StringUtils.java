package com.example.demo.util;


import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public abstract class StringUtils {

	/**
	 * 手机号码
	 * 移动：134[0-8],135,136,137,138,139,147,150,151,157,158,159,182,183,187,188,
	 * 联通：130,131,132,152,155,156,185,186
	 * 电信：133,1349,153,180,189
	 */
	private static final String MOBILE_REG_EXP_STRING = "^0?1[3|4|5|8][0-9]\\d{8}$";

	public static final String EMPTY = "";

	public static final int INDEX_NOT_FOUND = -1;

	/** 下划线 */
	private static final char SEPARATOR = '_';

	private StringUtils() {
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * * 判断一个Collection是否为空， 包含List，Set，Queue
	 *
	 * @param coll 要判断的Collection
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Collection<?> coll)
	{
		return isNull(coll) || coll.isEmpty();
	}

	public static boolean isBlank(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否全部为空
	 * @param values
	 * @return
	 */
	public static boolean isBlankLoop(String ...values) {
		int strLen;
		if (values == null || (strLen = values.length) == 0) {
			return true;
		}
		for (String value: values
			 ) {
			if (value == null || (strLen = value.length()) == 0) {
				continue;
			}
			for (int i = 0; i < strLen; i++) {
				if ((Character.isWhitespace(value.charAt(i)) == false)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断传入的字符串数组是否包含空字符串
	 * @see #isBlank(String)
	 * @param values
	 * @return
	 */
	public static boolean hasBlank(String... values) {
		if (values == null || values.length == 0) {
		      return true;
		}
		for (String value: values) {
			if (isBlank(value)) {
				return true;
			}
		}
		return false;
	}






	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	/**
	 * @see #isBlankLoop(String...)
	 * @param values
	 * @return
	 */
	public static boolean isNotBlankLoop(String ...values) {
		return !isBlankLoop(values);
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1){
			return false;
		}

		int i = 0;
		if (length > 1 && chars[0] == '-'){
			i = 1;
		}
		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	/**
	 * 过滤不可见字符
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input))){
			return "";
		}
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF))){
				out.append(current);
			}
		}
		return out.toString();
	}

	public static String getFirstUpper(String str) {
		String newStr = "";
		if (str.length() > 0) {
			newStr = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
		return newStr;
	}

	/**
	 * 当前值为空则取默认值返回
	 * 
	 * @param value
	 * @param defaultVal
	 * @return
	 */
	public static String defaultIfBlank(String value, String defaultVal) {
		return StringUtils.isNotBlank(value) ? value : defaultVal;
	}

	/**
	 * 替换指定位置的字符
	 * 
	 * @param index
	 *            位置
	 * @param ostr
	 *            源字符串
	 * @param replaceChar
	 *            替换的字符
	 * @return
	 */
	public static String replaceAtIndex(int index, String ostr, String replaceChar) {
		if (isNotBlank(ostr)) {
			try {
				return ostr.replaceFirst(ostr.charAt(index) + "", replaceChar);
			} catch (Exception e) {
				return ostr;
			}
		} else {
			return ostr;
		}
	}

	/**
	 * 填充左边字符
	 * 
	 * @param source
	 *            源字符串
	 * @param fillChar
	 *            填充字符
	 * @param len
	 *            填充到的长度
	 * @return 填充后的字符串
	 */
	public static String fillLeft(String source, char fillChar, int len) {
		StringBuffer ret = new StringBuffer();
		if (null == source){
			ret.append("");
		}
		if (source.length() > len) {
			ret.append(source);
		} else {
			int slen = source.length();
			while (ret.toString().length() + slen < len) {
				ret.append(fillChar);
			}
			ret.append(source);
		}
		return ret.toString();
	}

	public static String filterStr(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.replaceAll("'", "''");
		return str;
	}

	/**
	 * 字符串中没有number，全是字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAllChar(String str) {
		String regex = ".*[a-zA-Z]+.*";
		Matcher m = Pattern.compile(regex).matcher(str);
		return m.matches();
	}

	public static boolean isMapOrListJson(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		str=str.trim();
		if (str.indexOf("{") == 0 && str.lastIndexOf("}") == str.length()-1) {
			return true;
		}
		if (str.indexOf("[") == 0 && str.lastIndexOf("]") == str.length()-1) {
			return true;
		}
		return false;
	}

	public static boolean isMobileNO(String str) {
		if (isBlank(str)) {
			return false;
		}
		str = str.trim();
		Pattern p = Pattern.compile(MOBILE_REG_EXP_STRING);
		Matcher m = p.matcher(str);
		return m.matches();
		
	}
	/**
	 * 下划线转驼峰命名
	 */
	public static String toUnderScoreCase(String str)
	{
		if (str == null)
		{
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 前置字符是否大写
		boolean preCharIsUpperCase = true;
		// 当前字符是否大写
		boolean curreCharIsUpperCase = true;
		// 下一字符是否大写
		boolean nexteCharIsUpperCase = true;
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (i > 0)
			{
				preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
			}
			else
			{
				preCharIsUpperCase = false;
			}

			curreCharIsUpperCase = Character.isUpperCase(c);

			if (i < (str.length() - 1))
			{
				nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
			{
				sb.append(SEPARATOR);
			}
			else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
			{
				sb.append(SEPARATOR);
			}
			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * * 判断一个对象是否为空
	 *
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object)
	{
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object)
	{
		return !isNull(object);
	}

	/**
	 * 去空格
	 */
	public static String trim(String str)
	{
		return (str == null ? "" : str.trim());
	}

	/**
	 * 是否包含字符串
	 *
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs)
	{
		if (str != null && strs != null)
		{
			for (String s : strs)
			{
				if (str.equalsIgnoreCase(trim(s)))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * * 判断一个Collection是否非空，包含List，Set，Queue
	 *
	 * @param coll 要判断的Collection
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Collection<?> coll)
	{
		return !isEmpty(coll);
	}

	/**
	 * * 判断一个字符串是否为非空串
	 *
	 * @param str String
	 * @return true：非空串 false：空串
	 */
	public static boolean isNotEmpty(String str)
	{
		return !isEmpty(str);
	}

	/**
	 * * 判断一个对象数组是否为空
	 *
	 * @param objects 要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects)
	{
		return isNull(objects) || (objects.length == 0);
	}

}
