package com.allan.springbootcommon.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 *
 * @author qinzz
 */
public class DateUtil {
	/**
	 * 获取本周的截止时间
	 * 
	 * @return
	 */
	public static Date getWeekendTime() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE), 23, 59, 59);
		return currentDate.getTime();
	}
	
	/**
	 * 获取本周的起始时间
	 * 
	 * @return
	 */
	public static Date getWeekstartTime() {
		Calendar now = Calendar.getInstance();
		int weekDay = now.get(Calendar.DAY_OF_WEEK) == 1 ? 8 : now.get(Calendar.DAY_OF_WEEK);
		now.add(Calendar.DATE, Calendar.MONDAY - weekDay);
		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
		return now.getTime();
	}
	
	/**
	 * 获取本日的起始时间
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar now = Calendar.getInstance();
		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 0, 0, 0);
		return now.getTime();
	}
	
	/**
	 * 获取本日的截止时间
	 * 
	 * @return
	 */
	public static Date getTodayEndTime() {
		Calendar now = Calendar.getInstance();
		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), 23, 59, 59);
		return now.getTime();
	}
	/**
	 * 设置时间到最后一秒钟
	 * @author zhaolei
	 * @date 2015年11月6日
	 * @param time
	 * @return
	 */
	public static Date getDateEndTime(Date time){
		Calendar now=Calendar.getInstance();
		now.setTime(time);
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH);
		int date=now.get(Calendar.DAY_OF_MONTH);		
		now.set(year, month, date, 23, 59, 59);
		return now.getTime();
	}
	/**
	 * 获取本月的起始时间
	 * 
	 * @return
	 */
	public static Date getMonthstartTime() {
		Calendar now = Calendar.getInstance();
		now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1, 0, 0, 0);
		return now.getTime();
	}
	
	/**
	 * 获取本月的截止时间
	 * 
	 * @return
	 */
	public static Date getMonendTime() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		lastDate.set(lastDate.get(Calendar.YEAR), lastDate.get(Calendar.MONTH), lastDate.get(Calendar.DATE), 23, 59, 59);
		return lastDate.getTime();
	}
	/**
	 * 获取指定时间的截止时间
	 * 
	 * @return
	 */
	public static Date getMonendTime(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		lastDate.set(lastDate.get(Calendar.YEAR), lastDate.get(Calendar.MONTH), lastDate.get(Calendar.DATE), 23, 59, 59);
		return lastDate.getTime();
	}
	/**
	 * 格式化时间
	 * 
	 * @return
	 */
	public static String format(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
	
	/**
	 * 格式化时间
	 * 
	 * @return
	 */
	public static String format(Date dt, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(dt);
	}
	
	/**
	 * 将字符串转换成日期
	 * 
	 * @return
	 */
	public static Date parse(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dt);
		}
		catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 将字符串转换成日期
	 * 
	 * @return
	 */
	public static Date parse(String dt, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return sdf.parse(dt);
		}
		catch (ParseException e) {
			return null;
		}
	}
	
	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		}
		else {
			return 1 - dayOfWeek;
		}
	}
	/**
	 * 获取给定时间返回的星期
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		Format f = new SimpleDateFormat("E");
		return f.format(cd.getTime());
	}
	public static String formatDate(Calendar paramCalendar) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return localSimpleDateFormat.format(paramCalendar.getTime());
	}
	
	public static String formatDateToString(Date paramDate) throws Exception {
		String str = null;
		if (paramDate == null)
			return "";
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = localSimpleDateFormat.format(paramDate);
		return str;
	}
	
	public static String formatDateToString2(Date paramDate) throws Exception {
		String str = null;
		if (paramDate == null)
			return "";
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HHyyyymmddMMss");
		str = localSimpleDateFormat.format(paramDate);
		return str;
	}
	
	public static String formatDateToHour(Date paramDate) throws Exception {
		String str = null;
		if (paramDate == null)
			return "";
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH");
		str = localSimpleDateFormat.format(paramDate);
		return str;
	}
	
	public static String formatDateToStringNoTime(Date paramDate) throws Exception {
		String str = null;
		if (paramDate == null)
			return "";
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		str = localSimpleDateFormat.format(paramDate);
		return str;
	}
	
	public static String formateDateToChinese(Calendar paramCalendar) {
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			return localSimpleDateFormat.format(paramCalendar.getTime());
		}
		catch (Exception localException) {
		}
		return "";
	}
	
	public static String formateDateToChineseWK(Calendar paramCalendar) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 EE");
		return localSimpleDateFormat.format(paramCalendar.getTime());
	}
	
	public static String formateDateToNumber(Calendar paramCalendar) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return localSimpleDateFormat.format(paramCalendar.getTime());
	}
	
	public static Calendar paserStringToCalendar(String paramString) {
		Calendar localCalendar = Calendar.getInstance();
		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date localDate = null;
			localDate = localSimpleDateFormat.parse(paramString);
			localCalendar.setTime(localDate);
		}
		catch (Exception localException) {
			return null;
		}
		return localCalendar;
	}
	
	public static Date parseFromString(String paramString) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat();
		try {
			return localSimpleDateFormat.parse(paramString);
		}
		catch (ParseException localParseException) {
		}
		return null;
	}
	
	/**
	 * 计算第几年
	 */
	public static int getYears(Date startDate) {
		Date now = new Date();
		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);
		
		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(now);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);
		long ys = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24) / 365;
		return (int) (ys + 1);
	}
	
	/**
	 * 取得当前时间的字符串格式 pattern为格式 yyyy-MM-dd hh:mm:ss yyyy-MM-dd
	 */
	public static String getDateNow(String pattern) {
		return format(new Date(), pattern);
	}
	
	/**
	 * 
	 * @param date1
	 * @description 与当前系统时间比较大小，如果比当前时间大，则返回true，否则返回false
	 * @author
	 * @todo
	 */
	public static boolean compareDateNow(Date date1) {
		return compareDate(date1, new Date());
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @description 比较时间大小 ，如果date1比date2大，则返回true，否则返回false
	 * @author
	 * @todo
	 */
	public static boolean compareDate(Date date1, Date date2) {
		if (date1 == null) {
			return false;
		}
		if (date2 == null) {
			return true;
		}
		return compareDate(format(date1), format(date2));
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return 比较时间大小 ，如果date1比date2大，则返回true，否则返回false
	 * @description
	 * @author
	 * @todo
	 */
	public static boolean compareDate(String date1, String date2) {
		boolean result = false;
		
		if (date1 == null) {
			return false;
		}
		if (date2 == null) {
			return true;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			/*System.out.println("date1:"+sdf.parse(date1).getTime());
			System.out.println("date2:"+sdf.parse(date2).getTime());
			System.out.println("date1:"+date1);
			System.out.println("date2:"+date2);*/
			if (sdf.parse(date1).getTime() > sdf.parse(date2).getTime()) {
				result = true;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	/**
	 * 判断时间date3 是否在date1 与 date2 之间
	 * @param date1
	 * @param date2
	 * @param date3
	 * @return
	 */
	public static boolean compareDate(String date1, String date2,String date3){
		boolean result = false;
		if (date1 == null) {
			return false;
		}
		if (date2 == null) {
			return false;
		}
		if (date3 == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//第三个时间如果满足 date1<date3<date2返回为真
			if (sdf.parse(date1).getTime() <= sdf.parse(date3).getTime() && sdf.parse(date2).getTime() >= sdf.parse(date3).getTime()) {
				result = true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 时间加法
	 * 
	 * @param number
	 *            天数 或月数 的数值
	 * @param unit
	 *            天 或 月 的单位 unit 1为天 2为月
	 * @return
	 */
	public static Date addDate(int number, short unit) {
		
		Calendar c = Calendar.getInstance();
		if (1 == unit) {
			c.add(Calendar.DAY_OF_MONTH, number);
		}
		else {
			c.add(Calendar.MONTH, number);
		}
		return c.getTime();
	}
	/**
	 * 时间加法指定日期
	 * 
	 * @param number
	 *            天数 或月数 的数值
	 * @param unit
	 *            天 或 月 的单位 unit 1为天 2为月
	 * @return
	 */
	public static Date addDate(Date date,int number, short unit) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (1 == unit) {
			c.add(Calendar.DAY_OF_MONTH, number);
		}
		else {
			c.add(Calendar.MONTH, number);
		}
		return c.getTime();
	}
	/**
	 * 时间减法
	 * 
	 * @param number
	 *            天数 或月数 的数值
	 * @param unit
	 *            天 或 月 的单位 unit 1为天 2为月
	 * @return
	 */
	public static Date reduceDate(int number, short unit) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		if (1 == unit) {
			c.add(Calendar.DAY_OF_MONTH, -number);
		}
		else {
			c.add(Calendar.MONTH, -number);
		}
		return c.getTime();
	}
	/**
	 * 设置给定时间的加减法
	 * @param date 时间字符串
	 * @param number 天数 减法 负数，加法 正数
	 * @param unit 天 或 月 的单位 unit 1为天 2为月
	 * @return
	 */
	public static String dateSet(String date,int number,int unit,String format){
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		if (1 == unit) {
			c.add(Calendar.DAY_OF_MONTH, number);
		}
		else {
			c.add(Calendar.MONTH, number);
		}
		return format(c.getTime(),format);
	}
	/**
	 * 设置给定时间的加减法
	 * @param date 时间字符串
	 * @param number 天数 减法 负数，加法 正数
	 * @param unit 天 或 月 的单位 unit 1为天 2为月
	 * @return
	 */
	public static Date dateSet(Date date,int number,int unit){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (1 == unit) {
			c.add(Calendar.DAY_OF_MONTH, number);
		}
		else {
			c.add(Calendar.MONTH, number);
		}
		return c.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String beginDate,String endDate)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date smdate=sdf.parse(beginDate);
			Date bdate=sdf.parse(endDate);
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days=(time2-time1)/(1000*3600*24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 两个日期比较大小
	 * @param DATE1
	 * @param DATE2
	 * @return  DATE1 > DATE2   返回1
	 * 			DATE1 < DATE2   返回-1
	 * 		    DATE1 = DATE2   返回0
	 */
	public static int compare_date(String DATE1, String DATE2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			}else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(getMondayPlus());
		System.out.println(getWeek(parse("2016-07-22")));
	}
}
