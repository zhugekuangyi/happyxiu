package com.mingsheng.utils;

import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) {

        System.out.println(MyUUID.getUUID());

        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}
