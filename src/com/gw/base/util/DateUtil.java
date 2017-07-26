package com.gw.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gw.base.exception.OtherException;


/**
 * 日期时间帮助类
 * @author hanxu
 *	2015-04-03
 */
public class DateUtil {
	
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";
    
    
	/**
	 * 获取当时系统日期
	 * @return yyyy-MM-dd
	 */
	public static String getCurDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	
	/**
	 * 获取当前系统日期时间（中文格式）
	 * @return yyyy年MM月dd日 HH:mm
	 */
	public static String getCurTimeStrZh() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前系统日期时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前系统日期时间
	 * @return YYYYMMDDHHMMSS
	 */
	public static String getBankTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHMMSS");
		return sdf.format(new Date());
	}
	
	/**
	 * 将字符串类型的日期时间转换成Date类型
	 * @param souce
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static Date parseDate(String souce) throws OtherException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(souce.substring(0, 20));
		} catch (ParseException e) {
			throw new OtherException("9998", "日期格式转换失败");
		}
	}

	/**
	 * 获取一个月前的日期
	 * @return yyyy-MM-dd
	 */
	public static String getBeforeOneMonthDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 制定日期格式
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1); // 得到前一天
		c.add(Calendar.MONTH, -1); // 得到前一个月
		return df.format(c.getTime()); // 返回String型的时间
	}

	/**
	 * 获取三个月前的日期
	 * @return yyyy-MM-dd
	 */
	public static String getBeforeThreeMonthDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 制定日期格式
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1); // 得到前一天
		c.add(Calendar.MONTH, -4); // 得到前三个月
		return df.format(c.getTime()); // 返回String型的时间
	}

	/**
	 * 获取两个日期相隔的天数
	 * @param first 起始日期
	 * @param end   结止日期
	 * @return long
	 * @throws ParseException
	 */
	public static long betweenDate(String first, String end) throws OtherException {
		long day = 24L * 60L * 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(first);
			Date d2 = df.parse(end);
			return ((d2.getTime() - d1.getTime()) / day);
		} catch (ParseException e) {
			throw new OtherException("9998", "日期格式转换失败");
		}
	}

	/**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
}
