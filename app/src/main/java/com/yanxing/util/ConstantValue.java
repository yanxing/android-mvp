package com.yanxing.util;

import com.facebook.common.util.ByteConstants;

/**
 * Created by lishuangxiang on 2016/11/22.
 */

public class ConstantValue {

    public static final String URL="https://api.douban.com/v2/movie/";




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
