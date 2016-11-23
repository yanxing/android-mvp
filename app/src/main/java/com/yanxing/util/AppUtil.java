package com.yanxing.util;

/**
 * Created by lishuangxiang on 2016/11/23.
 */

public class AppUtil {

    /**
     * 格式化事件
     * @param time 2016-11-11
     * @return
     */
    public static String formate(String time){
        if (time.contains("-")){
            String temp[]=time.split("-");
            StringBuilder stringBuilder=new StringBuilder();
            for (int i=0;i<temp.length;i++){
                if (i!=temp.length-1){
                    stringBuilder.append(temp[i]+".");
                }else {
                    stringBuilder.append(temp[i]);
                }
            }
            return stringBuilder.toString();
        }
        return time;
    }
}
