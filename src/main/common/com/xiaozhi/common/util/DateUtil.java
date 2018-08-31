package com.xiaozhi.common.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author yhq [unicorn668@163.com] 创建时间：2012-11-2 上午11:18:44
 * @version V1.0
 */
public final class DateUtil {
    private static Logger logger = Logger.getLogger(DateUtil.class);
    /**
     * 默认的年月日
     */
    public static final String defaultPattern = "yyyy-MM-dd";
    public static final String CN_PATTERN = "yyyy年MM月dd日";
    public static final String DATE_DAY = "yyyy-MM-dd";
    public static final String DATE_DAY_NOLINE = "yyyyMMdd";
    /**
     * hour12HMSPattern年月日 时分秒 12小时制
     */
    public static final String hour12HMSPattern = "yyyy-MM-dd hh:mm:ss";

    /**
     * hour12HMPattern年月日 时分 12小时制
     */
    public static final String hour12HMPattern = "yyyy-MM-dd hh:mm";

    /**
     * hour12HPattern年月日 时 12小时制
     */
    public static final String hour12HPattern = "yyyy-MM-dd hh";

    /**
     * hour24HMSPattern年月日 时分秒 24小时制
     */
    public static final String hour24HMSPattern = "yyyy-MM-dd HH:mm:ss";
    
    public static final String localPattern = "yyyy年MM月dd日  HH:mm:ss";

    /**
     * hour24HMPattern年月日 时分 24小时制
     */
    public static final String hour24HMPattern = "yyyy-MM-dd HH:mm";

    /**
     * hour24HPattern年月日 时 24小时制
     */
    public static final String hour24HPattern = "yyyy-MM-dd HH";

    /**
     * hour24HPattern年月日 时 24小时制
     */
    public static final String hour24Pattern = "yyyyMMddHHmmss";

    private static final SimpleDateFormat df = new SimpleDateFormat(hour24HMSPattern);
    /**
     * 计算时间差，  例如返回  2天1小时4分20秒
     *
     * @param startDate 开始时间
     * @param endDate   例如返回  2天1小时4分20秒 的字符串
     * @return String
     */
    public static String daysBetween(Date startDate, Date endDate) {
        float d = endDate.getTime() - startDate.getTime();
        float dd = d / 86400000f;
        int ddd = (int) dd;

        float hh = (dd - ddd) * 24;
        int hhh = (int) hh;

        float mm = (hh - hhh) * 60;
        int mmm = (int) mm;

        float ss = (mm - mmm) * 60;
        int sss = (int) ss;
        return ddd + "天" + hhh + "小时" + mmm + "分" + sss + "秒";
    }

    public static String formatDate(Date date) {
        return df.format(date);
    }
    
    public static String formatDate(Date date, String formatStr) {
    	if(date==null){
    		return null;
    	}else{
    		return new SimpleDateFormat(formatStr).format(date);
    	}
    }
    
    public static Date getAddDate(Date date, int addNum) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.add(Calendar.DATE, addNum);
        return cld.getTime();
    }
    
    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }

    public static String getYestoday() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, -1);
        return format(cal1.getTime());
    }

    public static String getTheDayBeforeYestoday() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, -2);
        return format(cal1.getTime());
    }

    public static String getPreviousDay3() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, -3);
        return format(cal1.getTime());
    }

    public static String getPreviousDay4() {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, -4);
        return format(cal1.getTime());
    }

    /**
     * 取得距离今天N天的时间
     *
     * @param n       N：今天以后N天;-N今天以前N天
     * @param pattern 指定格式
     * @return
     * @create 2013-11-5 下午10:14:17 haoqj
     * @history
     */
    public static String getOneDay(int n, String pattern) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, n);
        return format(cal1.getTime(), pattern);
    }

    /**
     * 取得距离今天N天的时间(YYYY-MM-DD)
     *
     * @param n N：今天以后;-N今天以前N天
     * @return
     * @create 2013-11-5 下午10:14:17 haoqj
     * @history
     */
    public static String getOneDay(int n) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.add(Calendar.DATE, n);
        return format(cal1.getTime(), defaultPattern);
    }

    /**
     * 用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return format(date, hour24HMSPattern);
    }
    public static String getDate() {
        return format(new Date(), hour24HMSPattern);
    }
    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";

        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }

        return (returnValue);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) {
        return parse(strDate, defaultPattern);
    }

    /**
     * 使用参数Format将字符串转为Date
     *
     * @param strDate
     * @param pattern 字符、 格式参考<code>DateUtil 的静态常量</code>
     * @return Date
     * @throws ParseException
     */
    public static Date parse(String strDate, String pattern) {
        try {
            if (strDate != null && !"".equals(strDate)) {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                return df.parse(strDate);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        return null;
    }

    public static String formatDateTime(Date date) {
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return outFormat.format(date);
    }

    @SuppressWarnings("static-access")
    public static String getEndOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, Integer.parseInt(year));
        cal.set(cal.MONTH, Integer.parseInt(month) - 1);
        return cal.getActualMaximum(cal.DAY_OF_MONTH) + "";
    }

    public static String addDays(String sdate, int n) throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(parse(sdate, defaultPattern));
        cal1.add(Calendar.DATE, n);
        return format(cal1.getTime());

    }

    public static String getFirstOfMonth() throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date());
        cal1.set(5, 1);
        return format(cal1.getTime());

    }

    public static String getFirstOfMonth(String sDate) throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(parse(sDate, defaultPattern));
        cal1.set(5, 1);
        return format(cal1.getTime());

    }

    /**
     * 获取年
     *
     * @param sdate
     * @return String
     */
    public static String getYear(String sdate) {
        String[] date = sdate.split("-");
        return date[0];
    }

    /**
     * 获取月
     *
     * @param sdate
     * @return String
     */
    public static String getMonth(String sdate) {
        String[] date = sdate.split("-");
        return date[1];
    }

    public static String getCurrentYear() {
        Calendar cale = Calendar.getInstance();
        return Integer.toString(cale.get(Calendar.YEAR));
    }

    public static String getCurrentMonth() {
        Calendar cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH);
        month++;
        String sMonth = Integer.toString(month);
        if (month < 10)
            sMonth = "0" + month;
        return sMonth;
    }

    public static String getCurrentWeekOfYear() {
        Calendar cale = Calendar.getInstance();
        return String.valueOf(cale.get(Calendar.YEAR) * 100 + cale.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 获取天
     *
     * @param sdate
     * @return String
     */
    public static String getDay(String sdate) {
        String[] date = sdate.split("-");
        return date[2];
    }

    public static String getFullDate(String date) {
        if (date != null && date.length() == 1)
            return "0" + date;
        return date;
    }

    public static String getSimpleDateString(String sdate) {
        return sdate.replace("-", "");
    }

    //把日期从字符串转成日期型
    public static Date convertStringToDate(String pattern, String strDate)
            throws ParseException {
        Date aDate = null;
        aDate = parse(strDate, pattern);
        return aDate;
    }

    //根据指定格式得到当前日期的字符串
    public static String getTodayDate(String aMask) {
        Date date = new Date();
        return getDateTime(aMask, date);
    }

    //根据指定格式得到指定日期的字符串
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        df = new SimpleDateFormat(aMask);
        returnValue = df.format(aDate);
        return (returnValue);
    }

    /**
     * 处理日期格式转化
     *
     * @param date 日期对象
     * @return 日期的字符串格式
     */
    public static String dateFormat(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.get(Calendar.HOUR_OF_DAY);
        String pattern = "yyyy-MM-dd";
        if (c.get(Calendar.HOUR_OF_DAY) != 0) {
            pattern = "yyyy-MM-dd hh";
        } else if (c.get(Calendar.MINUTE) != 0) {
            pattern = "yyyy-MM-dd hh:mm";
        } else if (c.get(Calendar.SECOND) != 0) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * @param date    201306101201
     * @param pattern yyyy-MM-dd HH:mm
     * @return
     * @throws ParseException
     */
    public static Date dataFormat(String date, String pattern) throws ParseException {
        String newDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " +
                date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(newDate);
    }

    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date addDate(Date oldDate, int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }
    
    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param year
     * @return
     */
    public static Date addYear(Date oldDate, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }
    
    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param month
     * @return
     */
    public static Date addMonth(Date oldDate, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
    
    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param date
     * @return
     */
    public static Date addDay(Date oldDate, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }
    
    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param date
     * @return
     */
    public static Date addHour(Date oldDate, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }
    
    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     *
     * @param oleDate
     * @param minute
     * @return
     */
    public static Date addMin(Date oldDate, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 返回两个时间相差多少分钟
     *
     * @param a
     * @param b
     * @return
     */
    public static int subSecond(Date a, Date b) {
        return (int) (a.getTime() / (1000) - b.getTime() / (1000));
    }

    public static final Date getDateCurrentDate() {
        return getDateCurrentDate(defaultPattern);
    }

    public static final Date getDateCurrentDate(String pattern) {
        String strNow = getStrCurrentDate(pattern);
        return parse(strNow, pattern);
    }

    public static String getStrCurrentDate() {
        return getStrCurrentDate(defaultPattern);
    }

    public static String getStrCurrentDate(String pattern) {
        Date date = Calendar.getInstance().getTime();
        return format(date, pattern);
    }


    /**
     * 查询今天之前的日期
     *
     * @param beforeDay(把日期往后增加一天.整数往后推,负数往前移动) -1：表示昨天;1表示明天
     * @return
     */
    public static String getBeforeDate(int beforeDay) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, beforeDay);//把日期往后增加一天.整数往后推,负数往前移动
        Date endTime = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(endTime);
    }

    public static void main(String args[]) throws ParseException {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("a", "tes");
//		map.put("b", "tes");
//		map.clear();
//		if (map.get("b")!=null) {
//			System.out.println( "is b");
//			
//		}
//		List<String> list = new ArrayList<String>();
//		System.out.println(list.size());
//        long startDate = new Date().getTime();
//        long  halfHour = 30*60*1000;
        System.out.println((parse(getOneDay(1, defaultPattern), defaultPattern).getTime() - new Date().getTime()) / 1000);
//        System.out.println(addDateMinut(111132323));
    }

    /**
     * 获得本月的开始日期，即2012-01-01
     *
     * @return
     * @create 2013-11-11 下午04:28:40 徐承恩
     * @history
     */
    public static String getCurrentMonthStartTime() {
        Calendar c = Calendar.getInstance();
        String now = "";
        try {
            c.set(Calendar.DATE, 1);
            now = DateUtil.format(c.getTime(), defaultPattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前月的结束日期，即2012-01-31
     *
     * @return
     * @create 2013-11-11 下午04:28:57 徐承恩
     * @history
     */
    public static String getCurrentMonthEndTime() {
        Calendar c = Calendar.getInstance();
        String now = "";
        try {
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            now = DateUtil.format(c.getTime(), defaultPattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 获取当前时间后半小时的时间字符串
     *
     * @return
     * @create 2014-3-20 下午04:00:30 WUWEI
     * @history
     */
    public static String getNextHalfHourTime() {
        long crruTime = new Date().getTime();
        long halfHourTime = 30 * 60 * 1000;
        return format(new Date(crruTime + halfHourTime), hour24HMSPattern);
    }

    //当前时间的x分钟之后时间
    public static String addDateMinut(int x) throws ParseException
    // int x  当前时间几分钟后
    {
        SimpleDateFormat format = new SimpleDateFormat(hour24HMSPattern);// 24小时制
        //引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
        //量day格式一致
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);// 24小时制
        date = cal.getTime();
        cal = null;

        return format.format(date);

    }

    /**
     * 更改指定时间的"时/分/秒"
     * <br> 说明：<font color="red">如值为-1则不进行修改</font>
     *
     * @param date   原始时间
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return
     */
    public static Date updateTime(Date date, int hour, int minute, int second) {
        return update(date, -1, -1, -1, hour, minute, second, -1);
    }

    /**
     * 更改指定时间的"年/月/日/时/分/秒/毫秒"
     * <br> 说明：<font color="red">如值为-1则不进行修改</font>
     *
     * @param date        原始时间
     * @param year        年
     * @param month       月
     * @param day         日
     * @param hour        时
     * @param minute      分
     * @param second      秒
     * @param milliSecond 毫秒
     * @return
     */
    public static Date update(Date date, int year, int month, int day, int hour, int minute,
                              int second, int milliSecond) {

        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (year != -1)
            calendar.set(Calendar.YEAR, year);
        if (month != -1)
            calendar.set(Calendar.MONTH, month - 1);
        if (day != -1)
            calendar.set(Calendar.DATE, day);
        if (hour != -1)
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute != -1)
            calendar.set(Calendar.MINUTE, minute);
        if (second != -1)
            calendar.set(Calendar.SECOND, second);
        if (milliSecond != -1)
            calendar.set(Calendar.MILLISECOND, milliSecond);

        return calendar.getTime();
    }

    public static Date getBeforeDate(Date date, int beforeDay) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, beforeDay);//把日期往后增加一天.整数往后推,负数往前移动
        Date endTime = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return endTime;
    }

    public static final int dateSub(Date a, Date b) {

        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime() / (24 * 60 * 60 * 1000));

        return date <= 0 ? 0 : date;

    }

    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);

        //对比的时间
        String day = sf.format(date);

        return day.equals(nowDay);
    }
    
    /**
     * 一天的开始时间
     *
     * @param date
     * @return
     */

    public static final Date dateBegin(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        dateBegin(calendar);

        return calendar.getTime();

    }

    /**
     * 一天的结束时间
     *
     * @param date
     * @return
     */

    public static final Date dateEnd(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        dateEnd(calendar);

        return calendar.getTime();

    }

    /**
     * 一天的结束时间
     *
     * @param calendar
     * @return
     */

    public static final Calendar dateEnd(Calendar calendar) {

        if (calendar == null)

            return null;

        calendar.set(Calendar.HOUR_OF_DAY, 23);

        calendar.set(Calendar.MINUTE, 59);

        calendar.set(Calendar.SECOND, 59);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;

    }

    /**
     * 一天的开始时间
     *
     * @param calendar
     * @return
     */

    public static final Calendar dateBegin(Calendar calendar) {

        if (calendar == null)

            return null;

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;

    }
   
    /**
     * 一月的开始时间
     *
     * @param date
     * @return
     */

    public static final Date monthBegin(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DATE, day);

        dateBegin(calendar);

        return calendar.getTime();

    }

    /**
     * 一月的技术时间
     *
     * @param date
     * @return
     */

    public static final Date monthEnd(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DATE, day);

        dateEnd(calendar);

        return calendar.getTime();

    }

    /**
     * 一年的开始时间
     *
     * @param date
     * @return
     */

    public static final Date yearBegin(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.MONTH, 0);

        calendar.set(Calendar.DATE, 1);

        dateBegin(calendar);

        return calendar.getTime();

    }

    /**
     * 一年的结束时间
     *
     * @param date
     * @return
     */

    public static final Date yearEnd(Date date) {

        if (date == null)

            return null;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.MONTH, 11);

        calendar.set(Calendar.DATE, 31);

        dateEnd(calendar);

        return calendar.getTime();

    }
    
    
    /**
     * 获取整点时间
     *
     * @param date
     * @return
     */

    public static final Date getDateByHour(Date date,int hour) {
        return getDateByMinute(date, hour, 0);
    }
    
    /**
     * 获取时间
     *
     * @param date
     * @return
     */
    public static final Date getDateByMinute(Date date,int hour,int minute) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}