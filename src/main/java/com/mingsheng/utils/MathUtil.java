package com.mingsheng.utils;

public class MathUtil {

    public static String getId(){

            int intFlag = (int)(Math.random() * 1000000);

            String flag = String.valueOf(intFlag);
            if (flag.length() == 6 && flag.substring(0, 1).equals("9"))
            {
                intFlag=intFlag;
            }
            else
            {
                intFlag = intFlag + 100000;

            }

        return intFlag+"";
    }


}
