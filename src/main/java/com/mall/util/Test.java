package com.mall.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


/**
 * @Description:
 * @Author: Bentao She
 * @Date: 2021/8/12 9:53
 * @Version: V1.0
 **/




public class Test {

    public static boolean compareTwoLocalTimeString(String startAttendanceTime, String EndClosingTime){
        LocalTime start = LocalTime.parse(startAttendanceTime);
        LocalTime end = LocalTime.parse(EndClosingTime);
        return start.isAfter(end);
    }

    public static Date setDateWithinOverNight(LocalDate date, String attendanceTimeStr, String localTimeStr, SimpleDateFormat sdf){
        //1.隔天
        ParsePosition parsePosition = new ParsePosition(0);
        if(compareTwoLocalTimeString(attendanceTimeStr,localTimeStr)){
            date = date.plusDays(1);
            return sdf.parse(date.toString() + " " + localTimeStr,parsePosition);
        } else {
            // 2.没有隔天
            return sdf.parse(date.toString() + " " + localTimeStr, parsePosition);
        }
    }

    public static void main(String[] args) throws ParseException {
        //// Date类日期+时间
        //新建 Date对象
//        Date nowDate = new Date();
//        System.out.println("Date类默认时间格式是： " + nowDate);
//        //结果是 “Date类默认时间格式是： Fri Aug 13 10:54:33 CST 2021”
//        long nowDateMS = nowDate.getTime();
//
//        Date nowDatePlus1000s = new Date(nowDateMS + 1000 * 1000);
//        System.out.println(nowDatePlus1000s);
//        System.out.println(nowDatePlus1000s.compareTo(nowDate));
//
//
//        //sdf格式化
//        SimpleDateFormat sdf12 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//        String date1 = sdf12.format(nowDate);
//        System.out.println(date1);
//

//        String date2 = sdf24.format(nowDate);
//        System.out.println(date2);
//
//        SimpleDateFormat sdf123 = new SimpleDateFormat("HH-mm-ss");
//        System.out.println(sdf123.format(nowDate));
//
//
//        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
//
//        System.out.println("下面是sql.Date=====================");
//        // java.sql.Date
//        java.sql.Date now = new java.sql.Date(100000000);
//        System.out.println(now);
//
//        LocalDate date4 = now.toLocalDate();
//        System.out.println(date4);
//
//        System.out.println("下面是sql.Time=====================");
//        java.sql.Time nowTime = new java.sql.Time(1000000000);
//        System.out.println(nowTime);
//
//        System.out.println("下面是sql.Timestamp=====================");
//        java.sql.Timestamp nowTimestamp = new java.sql.Timestamp(1000000000);
//        System.out.println(nowTimestamp);

//        System.out.println("下面是util.Calendar=====================");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getWeekYear());
//        System.out.println(calendar.getTime());

//        System.out.println("下面是LocalDate=====================");
//        LocalDate now = LocalDate.now();
//        System.out.println(now + ": " + now.getDayOfMonth() + " :" + now.getDayOfWeek() + ":" + now.lengthOfMonth());
//        System.out.println();


//
//
//        System.out.println("Date类经过 SimpleDateFormat格式化后的格式是： " +date1);
//        //结果是 “Date类经过 SimpleDateFormat格式化后的格式是： 2021-08-13 10:54:33”
//
//        //sdf解析
//        String strDate = "2020-10-29 02:02:59";
//        Date date2 = sdf1.parse(strDate, new ParsePosition(0));
//        System.out.println("2020-10-29 02:02:59 String 经过sdf1的解析后是： " + date2);
//        //结果是 “2020-10-29 02:02:59 String 经过sdf1的解析后是： Thu Oct 29 02:02:59 CST 2020”
//        //以上两个互为逆操作
//        System.out.println("====================");
//
////        Date attendanceElastic = postponeTime(now, 1.0);
////        System.out.println(attendanceElastic);
////        Date test = postponeTime(now, 1.0);
////        System.out.println(test.after(attendanceElastic));
////
////        System.out.println();
//
        //// Java8 新的时间类 LocalDate  LocalTime  LocalDateTime

        // LocalDate 只保存日期
//     LocalDate nowLD = LocalDate.of(2021,8,15);
//     LocalDate sbt = LocalDate.of(2021,7,15);
//     System.out.println(nowLD.until(sbt,) + " \n --------");
//        System.out.println("LocalDate默认时间格式是: " + nowLD);
//        System.out.println(nowLD.getDayOfWeek() + " + " + nowLD.getMonthValue() + " + " + + nowLD.getDayOfYear());
//
//        //其他不变，只改变月，年。/
//        System.out.println(nowLD.withMonth(2) + " + " +  nowLD.withYear(2019));
//// 增加
//        System.out.println(nowLD.plusDays(1) + " + " + nowLD.plusWeeks(1) + " + " + nowLD.plusMonths(1)
//         + " + " + nowLD.plusYears(1));
//        //减少
//        System.out.println(nowLD.minusDays(1) + " + " + nowLD.minusWeeks(1) + " + " + nowLD.minusMonths(1)
//                + " + " + nowLD.minusYears(1));

        //还提供了计算两个时间点的时间差 until相关方法，复杂了一些，以后有时间再研究


        //结果是 “LocalDate默认时间格式是: 2021-08-13”
//
//        // LocalTime 只保存时间，精确到毫秒
//        LocalTime nowLocalTime = LocalTime.now();
//        System.out.println("LocalTime默认时间格式是: " + nowLocalTime);
        // 结果是 “LocalTime默认时间格式是: 11:10:33.319”
//
//        // LocalDateTime 同时保存日期和时间
//        LocalDateTime nowLocalDateTime = LocalDateTime.now();
//        System.out.println("LocalDateTime默认时间格式是: " + nowLocalDateTime);
//        // 结果是 "LocalDateTime默认时间格式是: 2021-08-13T11:10:33.319"
//        System.out.println("=======================");
//        LocalTime t1 = LocalTime.of(8,20);
//        LocalTime t2 = LocalTime.of(10,30);
//        System.out.println(t1.isBefore(t2));
//        System.out.println(t1.isAfter(t2));
//
//        LocalTime t3 = LocalTime.parse("08:20");
//
//        System.out.println(t1.compareTo(t3));
//
//        String date9 = sdf2.format(nowDate);
//        System.out.println(date9);
//        LocalTime t4 = LocalTime.parse(date9);
//        System.out.println(t3.isBefore(t4));
//
//        LocalTime t10 = LocalTime.parse("17:20");
//        System.out.println(t10);
//        System.out.println(t10.isAfter(t4));
//        System.out.println("========================测试LocalTime的加减");
//
//        System.out.println(t3.until(t10, MINUTES));
//        System.out.println(MINUTES.between(t3, t10));
//        t3.until(t10,MINUTES);
//        LocalTime t11 = t3.plusMinutes(540);
//        System.out.println(t11);
//
//        System.out.println("=================");
//        SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy:MM:dd");
//        String res = sdf10.format(new Date());
//        System.out.println(res);
        LocalTime t1 = LocalTime.of(8, 20);
//        LocalTime t2 = LocalTime.of(17, 20, 45, 342123342);
//        LocalTime t3 = LocalTime.parse("08:20", DateTimeFormatter.ISO_TIME);
//        LocalTime t4 = LocalDateTime.now().toLocalTime();
//
//        System.out.println(t1);
//        LocalTime t5 = t1.plusMinutes(1).plusHours(2);
//        System.out.println(t5);
//        System.out.println(t1.until(t5, MINUTES) + " ----");
//        System.out.println(t5.minusMinutes(t1.until(t5, MINUTES)));
//
//        System.out.println(t5.isAfter(t1));

//        SimpleDateFormat sdf24 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//        LocalDate today = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        System.out.println(today + " " + time);
//        System.out.println(today);
//        String nowTime = "08:20";
//        String now = today.toString() + " " + nowTime;
//
//        LocalDateTime t10 = LocalDateTime.now();
//        LocalDateTime t20 = t10.plusHours(1);
        //System.out.println(t20-t10);
//        String a1 = "03:00";
//        String a2 = "17:00";
//
//        System.out.println(LocalDate.now());
//        LocalDate ld= LocalDate.now().plusDays(1);
//        System.out.println(ld);
//        System.out.println(LocalDate.now().plusDays(1));
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


//        Date two = Test.setDateWithinOverNight(ld, a1, a2, sdf, parsePosition);
//        System.out.println(two + " ------");

//        Date three = Test.setDateWithinOverNight(ld, a2, a1, sdf);
//        System.out.println(three + " ====");
//        LocalDate test= LocalDate.of(2021,12,4);
//        LocalDate lastDay = test.with(TemporalAdjusters.lastDayOfMonth());
//        LocalDate firstDay = test.with(TemporalAdjusters.firstDayOfMonth());
//        System.out.println(lastDay);
//        System.out.println(firstDay);
//
//        LocalDate test2 = test.plusDays(3);
//        long days = test.until(test2,DAYS);
//        System.out.println(days);
//
//        int c = (int) days;

        //  List<Integer> aList = Arrays.asList(new Integer[]{2, 3, 4});
//        List<Integer> bList = new ArrayList<>();
//        bList.addAll(aList);
//        System.out.println(bList);
//
//        Set<LocalDate> test = new HashSet<>();
//        System.out.println(test.contains(LocalDate.now()));
//        Double a = 1.0;
//        System.out.println(a.equals(1.0));
//        String aaaaa = "加班0.5天";
//        Integer[] abc = {1,2,3,4,5,6};
//
//        Set<Integer> b = new HashSet<>();
//        Set<Integer> c = new HashSet<>();
//        b.add(1);
//        b.add(2);
//        b.add(3);
//
//        c.add(4);
//        b.addAll(c);
//        System.out.println(b);
//
//        String str = "2012-09-10 08:00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = sdf.parse(str,new ParsePosition(0));
//        System.out.println(date);


//        String t2 = "21:02";
//        String t3 = "23:02";
//
//        LocalTime t22 = LocalTime.parse(t3);
//        LocalTime t33 = t22.plusHours(2);
//        System.out.println(t33);
//
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//
//        Date a = new Date();
//        SimpleDateFormat toDateSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String test1 = toDateSdf.format(a);
//        System.out.println(test1);
//
//        //System.out.println(dateStrFormatter(ab));
//
//        String test124 = "2021-11-01 09:32:24";
//        System.out.println(dateStrFormatter(test124));
//        Date mm1 = toDateSdf.parse(test124);
//        SimpleDateFormat toStrSdf = new SimpleDateFormat("HH:mm:ss");
//        String res123 = toDateSdf.format(mm1);
//        System.out.println(res123);
//
//        String[] res = test124.split(" ");
//        String res2 = res[1];
//        System.out.println(res2);

//        String test123 = "09:00";
//        LocalTime ali = LocalTime.parse(test123);
//        System.out.println(ali);
//
//        double abc = 1.0;
//        double abd = 0.5;
//        if(abc == abd){
//            System.out.println("equals");
//        } else {
//            System.out.println("Not equals");
//        }
//
//        LocalTime alibaba = LocalTime.now();
//        System.out.println(alibaba);
//
//
//
//    }
//
//    public static String dateStrFormatter(String dateStr) throws ParseException {
//        SimpleDateFormat toDateSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = toDateSdf.parse(dateStr);
//        SimpleDateFormat toStrSdf = new SimpleDateFormat("HH:mm:ss");
//        return toStrSdf.format(date);
//    }
//        String hhmm = "HH:mm";
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(hhmm);
//        LocalTime timeStamp = LocalTime.parse("09:23", dtf);
//        System.out.println(timeStamp.toString());

//        LocalTime ccddd = LocalTime.parse("10:12");
//        LocalTime c1 = ccddd.plusHours(2);
//        System.out.println(c1);
//        System.out.println(ccddd);
//
//        LocalTime abc = LocalTime.now();
//        LocalDate abcd = LocalDate.now();
//        System.out.println( abcd.toString() + " " + ccddd.toString());
//
//        Map<LocalDate, String> map = new TreeMap<LocalDate, >();
//        LocalDate a1 = LocalDate.now();
//        LocalDate a2 = LocalDate.now().plusDays(1);
//        LocalDate a3 = LocalDate.now().minusDays(1);
//        map.put(a1, "apple");
//        map.put(a2, "orange");
//        map.put(a3, "banana");
    }
}
