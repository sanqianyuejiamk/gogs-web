package com.mengka.springboot.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String FORMAT_YYYYMMDD2 = "yyyy-MM-dd";

    public static final String FORMAT_MMDD = "MM月dd日";


    public static String toDate(Date dt, String sFmt) {
        if (null == dt || StringUtils.isBlank(sFmt)) {
            return null;
        }
        SimpleDateFormat sdfFrom = null;
        String sRet = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            sRet = sdfFrom.format(dt).toString();
        } catch (Exception ex) {
            return null;
        } finally {
            sdfFrom = null;
        }
        return sRet;
    }

    public static Date toDate(String sDate, String sFmt) {
        if (StringUtils.isBlank(sDate) || StringUtils.isBlank(sFmt)) {
            return null;
        }

        SimpleDateFormat sdfFrom = null;
        Date dt = null;
        try {
            sdfFrom = new SimpleDateFormat(sFmt);
            dt = sdfFrom.parse(sDate);
        } catch (Exception ex) {
            return null;
        } finally {
            sdfFrom = null;
        }

        return dt;
    }

    public static Date dayBefore(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 计算tempBuyTimeDate距离nowDate有多少时间之前
     *
     * @param nowDate
     * @param tempBuyTimeDate 购买时间
     * @return
     */
    public static String distanceTime(Date nowDate, Date tempBuyTimeDate) {
        long ssDistan = (nowDate.getTime() - tempBuyTimeDate.getTime()) / 1000;
        long mmDistan = (nowDate.getTime() - tempBuyTimeDate.getTime()) / 1000 / 60;
        long hhDistan = (nowDate.getTime() - tempBuyTimeDate.getTime()) / 1000 / 60 / 60;
        long ddDistan = (nowDate.getTime() - tempBuyTimeDate.getTime()) / 1000 / 60 / 60 / 24;
        long monthDistan = (nowDate.getTime() - tempBuyTimeDate.getTime()) / 1000 / 60 / 60 / 24 / 30;
        String buyTimestrString = "";
        if (monthDistan != 0) {
            buyTimestrString = monthDistan + "个月前";
        } else {
            if (ddDistan != 0) {
                buyTimestrString = ddDistan + "天前";
            } else {
                if (hhDistan != 0) {
                    buyTimestrString = hhDistan + "小时前";
                } else {
                    if (mmDistan != 0) {
                        buyTimestrString = mmDistan + "分钟前";
                    } else {
                        buyTimestrString = ssDistan + "秒前";
                    }
                }
            }
        }
        return buyTimestrString;
    }

    public static String toDateUnixTime(long t1, String sFmt) {
        Date d1 = new Date(t1*1000);
        return toDate(d1,sFmt);
    }

    public static int toDateInt(Date dt, String sFmt) {
        return Integer.parseInt(toDate(dt, sFmt));
    }

    public static Date toDate(Integer intDate, String sFmt) {
        return toDate(String.valueOf(intDate), sFmt);
    }

    public static Date dayBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -14);
        return calendar.getTime();
    }

    public static boolean isValidDate(String str, String sFmt) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(sFmt);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isValidDate(int date) {
        return isValidDate(String.valueOf(date), FORMAT_YYYYMMDD);
    }

    public static long delDate(int startDateId, int endDateId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return (sdf.parse(String.valueOf(startDateId)).getTime() - sdf.parse(String.valueOf(endDateId)).getTime()) / (24 * 60 * 60 * 1000);
    }

    public static int getDiscrepantDays(Date dateStart, Date dateEnd) {
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }
}
