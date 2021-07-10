package com.baoge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static Date getdate(Date time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    //格式化规则
        String time1 = sdf.format(time);
        Date newDate =sdf.parse(time1);
        java.sql.Date resultDate = new java.sql.Date(newDate.getTime());
        return  resultDate;
    }
}
