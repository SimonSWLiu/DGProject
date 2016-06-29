package com.study.demo;

import java.sql.Timestamp;

import com.onemenu.server.util.FormatUtils;

public class TestTimestamp {

    public static void main(String[] args) {

        System.out.println(FormatUtils.formatStringToDate("2015-03-01"));

        System.out.println(FormatUtils.formatStringToTime("23:25"));

        int day = 15;
        // TODO Auto-generated method stub
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(currentTimestamp);

        Timestamp smallTimestamp = Timestamp.valueOf("2015-01-17 23:41:53.673");
        System.out.println(smallTimestamp);

        Timestamp bigTimestamp = Timestamp.valueOf("2015-01-30 23:41:53.673");
        System.out.println(bigTimestamp);

        System.out.println((smallTimestamp.getTime() - currentTimestamp.getTime()) > day * 24 * 60
                * 60 * 1000);
        System.out.println((bigTimestamp.getTime() - currentTimestamp.getTime()) > day * 24 * 60
                * 60 * 1000);

    }

}
