package com.yanxing.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载图片
 * Created by lishuangxiang on 2016/11/24.
 */

public class DownloadImageUtil {

    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private DownloadListener mDownloadListener;

    public static DownloadImageUtil getInstance() {
        return SingleHolder.mDownloadUtils;
    }

    private static class SingleHolder {
        private static final DownloadImageUtil mDownloadUtils = new DownloadImageUtil();
    }

    private DownloadImageUtil() {
    }

    public void downloadImage(final String url, DownloadListener downloadListener) {
        this.mDownloadListener = downloadListener;
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        InputStream inStream = conn.getInputStream();
                        FileUtil.writeStInput(FileUtil.getStoragePath() + "DCIM/Camera/"
                                , System.currentTimeMillis() + ".jpg", inStream);
                        mDownloadListener.finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface DownloadListener {
        void finish();
    }
}
