package com.peoit.android.online.pschool.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;

import java.util.HashMap;

/**
 * author:libo
 * time:2015/9/1
 * E-mail:boli_android@163.com
 * last: ...
 */
public class VideoGetFrameImageUtil {

    public static Bitmap getFrameImage(String uri){
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            media.setDataSource(uri, new HashMap<String, String>());
        } else {
            media.setDataSource(uri);
        }
        Bitmap bitmap = media.getFrameAtTime();
        return bitmap;
    }

}
