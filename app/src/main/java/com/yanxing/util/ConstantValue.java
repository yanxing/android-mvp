package com.yanxing.util;

import com.facebook.common.util.ByteConstants;

/**
 * Created by lishuangxiang on 2016/11/22.
 */

public class ConstantValue {

    /**
     * 百度apistore
     */
    public static final String APIKEY="c2d0a9c6eaac04c80cef2dfb7dcbf10d";

    public static final String URL="http://apis.baidu.com/";
    /**
     * 微信精选
     */
    public static final String WEIXIN_HOT="txapi/weixin/wxhot";




    /*--------------------------------------------------------------fresco配置---------------------------------------------------------*/
    /**
     * 默认图极低磁盘空间缓存的最大值
     */
    public static final int MAX_DISK_CACHE_VERYLOW_SIZE = 10 * ByteConstants.MB;
    /**
     * 默认图低磁盘空间缓存的最大值
     */
    public static final int MAX_DISK_CACHE_LOW_SIZE = 30 * ByteConstants.MB;
    /**
     * 默认图磁盘缓存的最大值
     */
    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;

    /**
     * 缓存图片文件夹
     */
    public static final String CACHE_IMAGE="yanxing/image/";
}
