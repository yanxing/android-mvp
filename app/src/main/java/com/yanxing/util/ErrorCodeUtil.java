package com.yanxing.util;

/**
 * 错误代码
 * Created by lishuangxiang on 2016/11/22.
 */

public class ErrorCodeUtil {

    public static final int SUCCESS_CODE = 200;

    /**
     * 错误代码为SUCCESS_CODE,代表响应成功
     * @param code
     * @return
     */
    public static boolean isErrorSuccess(int code){
        return code==SUCCESS_CODE;
    }

}
