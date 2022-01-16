package com.mall.util;

import com.mall.exception.AttendanceStatusEnum;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * @Description:
 * @Author: Bentao She
 * @Date: 2021/8/10 19:53
 * @Version: V1.0
 **/

public class DateWeekGenerator {
    public static void main(String[] args) {
//
//        LocalDate time = LocalDate.of(2021,8,1);
//
//
//        int month = time.getMonthValue();
//
//        Map<LocalDate, String> record = new LinkedHashMap<>();
//        while(time.getMonthValue() == month){
//            record.put(time,time.getDayOfWeek().toString());
//            time = time.plusDays(1);
//        }
//
//        String workDay = "MONDAY;TUESDAY;WEDNESDAY";
//        String[] workDayList = workDay.split(";");
//        //System.out.println(workDayList.length);
//        Set<String> workDaySet = new HashSet<>(Arrays.asList(workDayList));
//
//        for(String workD: workDayList){
//            System.out.println(workD);
//        }
//
//        LocalDate currentDate = LocalDate.now();
//        // 获取该月的作息表 workSchedule
//        Map<String, String> workSchedule = new LinkedHashMap<>();
//        for(Map.Entry<LocalDate, String> entry: record.entrySet()) {
//            if(entry.getKey().isBefore(currentDate)){
//                System.out.println(entry.getKey());
//            }
//
//        }

        LocalDate currentDate2 = LocalDate.now();
        System.out.println(currentDate2);
        LocalDate currentDate3 = LocalDate.of(2021,8,11);
        System.out.println(currentDate3);
        System.out.println(currentDate2.equals(currentDate3));
    }

}
