package com.example.temple.utils;

import android.text.TextUtils;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;


public class DateUtil {

    // ==格式到年==
    /**
     * 日期格式，年份，例如：2004，2008
     */
    public static final String DATE_FORMAT_YYYY = "yyyy";


    // ==格式到年月 ==
    /**
     * 日期格式，年份和月份，例如：200707，200808
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 日期格式，年份和月份，例如：200707，2008-08
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";


    // ==格式到年月日==
    /**
     * 日期格式，年月日，例如：050630，080808
     */
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";

    /**
     * 日期格式，年月日，用横杠分开，例如：06-12-25，08-08-08
     */
    public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

    /**
     * 日期格式，年月日，例如：20050630，20080808
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式，年月日，例如：2016.10.05
     */
    public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";

    public static final String DATE_FORMAT_POINTYYYYMM = "yyyy.MM";

    /**
     * 日期格式，年月日，例如：2016年10月05日
     */
    public static final String DATE_TIME_FORMAT_YYYY年MM月DD日 = "yyyy年MM月dd日";


    public static final String DATE_TIME_FORMAT_MM月DD日 = "MM月dd日";

    // ==格式到年月日 时分 ==

    /**
     * 日期格式，年月日时分，例如：200506301210，200808081210
     */
    public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

    /**
     * 日期格式，年月日时分，例如：20001230 12:00，20080808 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

    /**
     * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";


    // ==格式到年月日 时分秒==
    /**
     * 日期格式，年月日时分秒，例如：20001230120000，20080808200808
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
     * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";


    // ==格式到年月日 时分秒 毫秒==
    /**
     * 日期格式，年月日时分秒毫秒，例如：20001230120000123，20080808200808456
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";


    // ==特殊格式==
    /**
     * 日期格式，月日时分，例如：10-05 12:00
     */
    public static final String DATE_FORMAT_MMDDHHMI = "MM-dd HH:mm";


    public static final String DATE_FORMAT_MMDDHHMM = "MM月dd日 HH:mm";
    /* ************工具方法***************   */

    /**
     * 获取某日期的年份
     *
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取某日期的月份
     *
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取某日期的日数
     *
     * @param date
     * @return
     */
    public static Integer getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);//获取日
        return day;
    }

    /**
     * 格式化Date时间
     *
     * @param time       Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        if (null == time) {
            throw new IllegalArgumentException("日期不能为空");
        }
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 格式化Timestamp时间
     *
     * @param timestamp  Timestamp类型时间
     * @param timeFromat
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认值为当前时间Date
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            if (defaultValue != null)
                return parseDateToStr(defaultValue, timeFromat);
            else
                return parseDateToStr(new Date(), timeFromat);
        }
    }

    /**
     * 格式化Date时间
     *
     * @param time         Date类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 默认时间值String类型
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final String defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 格式化String时间
     *
     * @param time       String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            date = dateFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化String时间
     *
     * @param strTime      String类型时间
     * @param timeFromat   String类型格式
     * @param defaultValue 异常时返回的默认值
     * @return
     */
    public static Date parseStrToDate(String strTime, String timeFromat,
                                      Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 当strTime为2008-9时返回为2008-9-1 00:00格式日期时间，无法转换返回null.
     *
     * @param strTime
     * @return
     */
    public static Date strToDate(String strTime) {
        if (strTime == null || strTime.trim().length() <= 0)
            return null;

        Date date = null;
        List<String> list = new ArrayList<String>(0);

        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
        list.add(DATE_FORMAT_YYYY_MM_DD);
        //list.add(DATE_FORMAT_YY_MM_DD);
        list.add(DATE_FORMAT_YYYYMMDD);
        list.add(DATE_FORMAT_YYYY_MM);
        list.add(DATE_FORMAT_YYYYMM);
        list.add(DATE_FORMAT_YYYY);


        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String format = (String) iter.next();
            if (strTime.indexOf("-") > 0 && format.indexOf("-") < 0)
                continue;
            if (strTime.indexOf("-") < 0 && format.indexOf("-") > 0)
                continue;
            if (strTime.length() > format.length())
                continue;
            date = parseStrToDate(strTime, format);
            if (date != null)
                break;
        }

        return date;
    }

    /**
     * 解析两个日期之间的所有月份
     *
     * @param beginDateStr 开始日期，至少精确到yyyy-MM
     * @param endDateStr   结束日期，至少精确到yyyy-MM
     * @return yyyy-MM日期集合
     */
    public static List<String> getMonthListOfDate(String beginDateStr, String endDateStr) {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        // 返回的月份列表
        String sRet = "";

        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        List<String> list = new ArrayList<String>();

        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);

            // 设置日历
            beginGC = new GregorianCalendar();
            beginGC.setTime(beginDate);

            endGC = new GregorianCalendar();
            endGC.setTime(endDate);

            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                sRet = beginGC.get(Calendar.YEAR) + "-"
                        + (beginGC.get(Calendar.MONTH) + 1);
                list.add(sRet);
                // 以月为单位，增加时间
                beginGC.add(Calendar.MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析两个日期段之间的所有日期
     *
     * @param beginDateStr 开始日期  ，至少精确到yyyy-MM-dd
     * @param endDateStr   结束日期  ，至少精确到yyyy-MM-dd
     * @return yyyy-MM-dd日期集合
     */
    public static List<String> getDayListOfDate(String beginDateStr, String endDateStr) {
        // 指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        // 定义一些变量
        Date beginDate = null;
        Date endDate = null;

        Calendar beginGC = null;
        Calendar endGC = null;
        List<String> list = new ArrayList<String>();

        try {
            // 将字符串parse成日期
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);

            // 设置日历
            beginGC = Calendar.getInstance();
            beginGC.setTime(beginDate);

            endGC = Calendar.getInstance();
            endGC.setTime(endDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 直到两个时间相同
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {

                list.add(sdf.format(beginGC.getTime()));
                // 以日为单位，增加时间
                beginGC.add(Calendar.DAY_OF_MONTH, 1);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }


    /**
     * 获取当下年份指定前后数量的年份集合
     *
     * @param before 当下年份前年数
     * @param behind 当下年份后年数
     * @return 集合
     */
    public static List<Integer> getYearListOfYears(int before, int behind) {
        if (before < 0 || behind < 0) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        Calendar c = null;
        c = Calendar.getInstance();
        c.setTime(new Date());
        int currYear = Calendar.getInstance().get(Calendar.YEAR);

        int startYear = currYear - before;
        int endYear = currYear + behind;
        for (int i = startYear; i < endYear; i++) {
            list.add(Integer.valueOf(i));
        }
        return list;
    }

    /**
     * 获取当前日期是一年中第几周
     *
     * @param date
     * @return
     */
    public static Integer getWeekthOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取某一年各星期的始终时间
     * 实例：getWeekList(2016)，第52周(从2016-12-26至2017-01-01)
     *
     * @param
     * @return
     */
    public static HashMap<Integer, String> getWeekTimeOfYear(int year) {
        HashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayOfWeekStart = "";
        String dayOfWeekEnd = "";
        for (int i = 1; i <= count; i++) {
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
            map.put(Integer.valueOf(i), "第" + i + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
        }
        return map;

    }

    /**
     * 获取某一年的总周数
     *
     * @param year
     * @return
     */
    public static Integer getWeekCountOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());
        return count;
    }

    /**
     * 获取指定日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 获取指定日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    /**
     * 获取某年某周的第一天
     *
     * @param year 目标年份
     * @param week 目标周数
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年某周的最后一天
     *
     * @param year 目标年份
     * @param week 目标周数
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year  目标年份
     * @param month 目标月份
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);

        int day = c.getActualMinimum(c.DAY_OF_MONTH);

        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  目标年份
     * @param month 目标月份
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMaximum(c.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 获取某个日期为星期几
     *
     * @param date
     * @return String "星期*"
     */
    public static String getDayWeekOfDate1(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获得指定日期的星期几数
     *
     * @param date
     * @return int
     */
    public static Integer getDayWeekOfDate2(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 验证字符串是否为日期
     * 验证格式:YYYYMMDD、YYYY_MM_DD、YYYYMMDDHHMISS、YYYYMMDD_HH_MI、YYYY_MM_DD_HH_MI、YYYYMMDDHHMISSSSS、YYYY_MM_DD_HH_MI_SS
     *
     * @param strTime
     * @return null时返回false;true为日期，false不为日期
     */
    public static boolean validateIsDate(String strTime) {
        if (strTime == null || strTime.trim().length() <= 0)
            return false;

        Date date = null;
        List<String> list = new ArrayList<String>(0);

        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
        list.add(DATE_FORMAT_YYYY_MM_DD);
        //list.add(DATE_FORMAT_YY_MM_DD);
        list.add(DATE_FORMAT_YYYYMMDD);
        //list.add(DATE_FORMAT_YYYY_MM);
        //list.add(DATE_FORMAT_YYYYMM);
        //list.add(DATE_FORMAT_YYYY);

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String format = (String) iter.next();
            if (strTime.indexOf("-") > 0 && format.indexOf("-") < 0)
                continue;
            if (strTime.indexOf("-") < 0 && format.indexOf("-") > 0)
                continue;
            if (strTime.length() > format.length())
                continue;
            date = parseStrToDate(strTime.trim(), format);
            if (date != null)
                break;
        }

        if (date != null) {
            System.out.println("生成的日期:" + DateUtil.parseDateToStr(date, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS, "--null--"));
            return true;
        }
        return false;
    }

    /**
     * 将指定日期的时分秒格式为零
     *
     * @param date
     * @return
     */
    public static Date formatHhMmSsOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定时间加减参数后的日期(不计算则输入0)
     *
     * @param date        指定日期
     * @param year        年数，可正可负
     * @param month       月数，可正可负
     * @param day         天数，可正可负
     * @param hour        小时数，可正可负
     * @param minute      分钟数，可正可负
     * @param second      秒数，可正可负
     * @param millisecond 毫秒数，可正可负
     * @return 计算后的日期
     */
    public static Date addDate(Date date, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);//加减年数
        c.add(Calendar.MONTH, month);//加减月数
        c.add(Calendar.DATE, day);//加减天数
        c.add(Calendar.HOUR, hour);//加减小时数
        c.add(Calendar.MINUTE, minute);//加减分钟数
        c.add(Calendar.SECOND, second);//加减秒
        c.add(Calendar.MILLISECOND, millisecond);//加减毫秒数

        return c.getTime();
    }

    /**
     * 获得两个日期的时间戳之差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDistanceTimestamp(Date startDate, Date endDate) {
        long daysBetween = (endDate.getTime() - startDate.getTime() + 1000000) / (3600 * 24 * 1000);
        return daysBetween;
    }

    /**
     * 判断二个时间是否为同年同月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Boolean compareIsSameMonth(Date date1, Date date2) {
        boolean flag = false;
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        if (year1 == year2) {
            int month1 = getMonth(date1);
            int month2 = getMonth(date2);
            if (month1 == month2) flag = true;
        }
        return flag;
    }

    /**
     * 获得两个时间相差距离多少天多少小时多少分多少秒
     *
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTime(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：{天, 时, 分, 秒}
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        Date one;
        Date two;
        long year = 0;
        long month = 0;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            year = diff / (24 * 60 * 60 * 1000) / 30 / 12;
            month = diff / (24 * 60 * 60 * 1000) / 30;
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String times = "";
        if (year > 0) {
            if (month > 0) {
                times = year + "年" + month + "个月";
            } else {
                times = year + "年";
            }
        } else {
            if (month > 0) {
                times = month + "个月";
            } else {
                times = day + "天";
            }
        }
        return times;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @return 相差天数
     */
    public static Long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static Long getDistanceDays(String str1, String str2,String pattern) throws Exception {
        DateFormat df = new SimpleDateFormat(pattern);
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date getDayBeginTime(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date getDayEndTime(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public List<String> getYearList() {
        List<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        return monthList;
    }

    public List<String> getYearListWithEdu() {
        List<String> monthList = new ArrayList<>();
        monthList.add("在读");
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        return monthList;
    }

    public List<String> getYearListWithToNow() {
        List<String> monthList = new ArrayList<>();
        monthList.add("至今");
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        return monthList;
    }


    public static List<String> getNormalOptionYear() {
        /**
         * 完整的月份数据1~12
         */
        List<String> monthList = new ArrayList<>();
        /**
         * 滚轮选择器中年份的选项数据
         */
        List<String> optionYears = new ArrayList<>();
        /**
         * 滚轮选择器中月份的选项数据
         */
        List<List<String>> optionMonths = new ArrayList<>();
        //设置完整的月份数据，即1~12
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        //月份获取到的数据是0~11，所以要加1
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        for (int i = curYear + 1; i >= 1989; i--) {
            //设置常规时间
            if (i == curYear + 1) {

            } else {
                optionYears.add(String.valueOf(i));
                optionMonths.add(monthList);
            }
        }
        return optionYears;
    }

    public static List<List<String>> getNormalOptionMonth() {
        /**
         * 完整的月份数据1~12
         */
        List<String> monthList = new ArrayList<>();
        /**
         * 滚轮选择器中年份的选项数据
         */
        List<String> optionYears = new ArrayList<>();
        /**
         * 滚轮选择器中月份的选项数据
         */
        List<List<String>> optionMonths = new ArrayList<>();
        //设置完整的月份数据，即1~12
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        //月份获取到的数据是0~11，所以要加1
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        for (int i = curYear + 1; i >= 1989; i--) {
            //设置常规时间
            if (i == curYear + 1) {

            } else {
                optionYears.add(String.valueOf(i));
                optionMonths.add(monthList);
            }
        }
        return optionMonths;
    }

    public static void main(String[] args) {
        try {
            DateUtil dateUtil = new DateUtil();
            System.out.println();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 创建时间戳
     *
     * @return
     */
    public static String createTimeSteamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前时间
     *
     * @param template 时间格式，默认为 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentDateString(String template) {
        if (TextUtils.isEmpty(template)) {
            template = "yyyy-MM-dd HH:mm:ss";// 大写"HH"：24小时制，小写"hh"：12小时制
        }

        SimpleDateFormat formatter = new SimpleDateFormat(template, Locale.getDefault());

        System.out.println("getCurrentDateString = " + formatter.format(new Date()));

        return formatter.format(new Date());
    }

    /**
     * 获取 2 个时间的自然年历的时间间隔
     *
     * @param nextTime     后面的时间，需要大于 previousTime，空则默认为当前时间
     * @param previousTime 前面的时间，空则默认为当前时间
     * @param format       时间格式，eg:"yyyy-MM-dd", "yyyy-MM-dd hh:mm:ss"
     * @return [年, 月, 日, 小时, 分钟, 秒]的数组
     */
    public static int[] getTimeIntervalArray(String nextTime, String previousTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date nextDate;
        Date previousDate;
        Calendar nextCalendar = Calendar.getInstance();
        Calendar previousCalendar = Calendar.getInstance();

        // 空则取当前时间
        try {
            nextDate = dateFormat.parse(TextUtils.isEmpty(nextTime) ? getCurrentDateString(format) : nextTime);
            nextCalendar.setTime(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 空则取当前时间
        try {
            previousDate = dateFormat.parse(TextUtils.isEmpty(previousTime) ? getCurrentDateString(format) : previousTime);
            previousCalendar.setTime(previousDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getTimeIntervalArray(nextCalendar, previousCalendar);
    }

    /**
     * 获取 2 个时间的自然年历的时间间隔
     *
     * @param nextDate     后面的时间，需要大于 previousDate
     * @param previousDate 前面的时间
     * @return [年, 月, 日, 小时, 分钟, 秒]的数组
     */
    public static int[] getTimeIntervalArray(Calendar nextDate, Calendar previousDate) {
        int year = nextDate.get(Calendar.YEAR) - previousDate.get(Calendar.YEAR);
        int month = nextDate.get(Calendar.MONTH) - previousDate.get(Calendar.MONTH);
        int day = nextDate.get(Calendar.DAY_OF_MONTH) - previousDate.get(Calendar.DAY_OF_MONTH);
        int hour = nextDate.get(Calendar.HOUR_OF_DAY) - previousDate.get(Calendar.HOUR_OF_DAY);// 24小时制
        int min = nextDate.get(Calendar.MINUTE) - previousDate.get(Calendar.MINUTE);
        int second = nextDate.get(Calendar.SECOND) - previousDate.get(Calendar.SECOND);

        boolean hasBorrowDay = false;// "时"是否向"天"借过一位

        if (second < 0) {
            second += 60;
            min--;
        }

        if (min < 0) {
            min += 60;
            hour--;
        }

        if (hour < 0) {
            hour += 24;
            day--;

            hasBorrowDay = true;
        }

        if (day < 0) {
            // 计算截止日期的上一个月有多少天，补上去
            Calendar tempDate = (Calendar) nextDate.clone();
            tempDate.add(Calendar.MONTH, -1);// 获取截止日期的上一个月
            day += tempDate.getActualMaximum(Calendar.DAY_OF_MONTH);

            // nextDate是月底最后一天，且day=这个月的天数，即是刚好一整个月，比如20160131~20160229，day=29，实则为1个月
            if (!hasBorrowDay
                    && nextDate.get(Calendar.DAY_OF_MONTH) == nextDate.getActualMaximum(Calendar.DAY_OF_MONTH)// 日期为月底最后一天
                    && day >= nextDate.getActualMaximum(Calendar.DAY_OF_MONTH)) {// day刚好是nextDate一个月的天数，或大于nextDate月的天数（比如2月可能只有28天）
                day = 0;// 因为这样判断是相当于刚好是整月了，那么不用向 month 借位，只需将 day 置 0
            } else {// 向month借一位
                month--;
            }
        }

        if (month < 0) {
            month += 12;
            year--;
        }

        return new int[]{year, month, day, hour, min, second};
    }

    // -2:前天             -1：昨天            0：今天             1：明天             2：后天
    public static int getDayString(Date startDate) {
        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis() + offSet) / 86400000;
        long start = (startDate.getTime() + offSet) / 86400000;
        return (int) (start - today);
    }

    /**
     * 获取当月天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * @param newDate 期望时间格式
     * @param oldDate 时间本身的格式
     * @param time    时间字符串
     * @return
     */
    public static String getDate(String newDate, String oldDate, String time) {
        String date = "";
        try {
            date = new SimpleDateFormat(newDate)
                    .format(new SimpleDateFormat(oldDate)
                            .parse(time)
                            .getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 半小时以后的时间
     * @param time
     * @return
     */
    public static String halfHourLater(String time) {
        Date refreshDate = parseStrToDate(time, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        Calendar cal = Calendar.getInstance();
        cal.setTime(refreshDate);
        cal.add(Calendar.MINUTE, 30);
        Date d2 = cal.getTime();
        return new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS).format(d2);
    }


    /**
     * 是否是当前时间以后
     *
     * @param beginDay
     * @param pattern
     * @return
     */
    public static boolean isAfterDay(String beginDay, String pattern) {
        try {
            Date currentDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date duringDate = format.parse(beginDay);
            if (currentDate.after(duringDate)) {//currentDate  大于 duringDate
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * @param nowDate   要比较的时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return true在时间段内，false不在时间段内
     * @throws Exception
     */
    public static boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date now = format.parse(nowDate);
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);

        long nowTime = now.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();

        return nowTime >= startTime && nowTime <= endTime;
    }

    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 比较两个时间的大小
     *
     * @param nowDate
     * @param compareDate
     * @return
     */
    public static boolean compareDate(String nowDate, String compareDate, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        try {
            Date now = df.parse(nowDate);
            Date compare = df.parse(compareDate);
            if (now.before(compare) ) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
