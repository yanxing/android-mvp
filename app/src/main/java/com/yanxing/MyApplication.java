package com.yanxing;

import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.yanxing.networklibrary.RetrofitManage;
import com.yanxing.util.ConstantValue;

import java.io.File;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by lishuangxiang on 2016/1/26.
 */
public class MyApplication extends DaggerApplication {

    private static final String STORAGE_PATH = Environment.getExternalStorageDirectory() + "/";

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManage.getInstance().init(ConstantValue.URL,true);
        initFresco();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    /**
     * 初始化fresco
     */
    private  void initFresco(){
        ProgressiveJpegConfig pjpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            @Override
            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);
                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };
        File file=new File(STORAGE_PATH);
        //自定义图片的磁盘配置,fresco缓存文件后缀cnt
        DiskCacheConfig diskCacheConfig =  DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(file)//缓存图片基路径
                .setBaseDirectoryName(ConstantValue.CACHE_IMAGE)//文件夹名
                .setMaxCacheSize(ConstantValue.MAX_DISK_CACHE_SIZE)//默认缓存的最大大小。
                .setMaxCacheSizeOnLowDiskSpace(ConstantValue.MAX_DISK_CACHE_LOW_SIZE)//缓存的最大大小,使用设备时低磁盘空间。
                .setMaxCacheSizeOnVeryLowDiskSpace(ConstantValue.MAX_DISK_CACHE_VERYLOW_SIZE)//缓存的最大大小,当设备极低磁盘空间
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(pjpegConfig)
                .setMainDiskCacheConfig(diskCacheConfig)//磁盘缓存配置
                .build();
        Fresco.initialize(getApplicationContext(),imagePipelineConfig);
    }
}
