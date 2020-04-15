package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String dateToString(Date date,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }
}
