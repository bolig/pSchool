package com.peoit.android.online.pschool.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Environment;
import android.util.Log;

import com.peoit.android.online.pschool.config.CommonUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * bitmap 辅助类
 * <p/>
 * author:libo
 * time:2015/9/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class BitmapUtils {

    public static String SDPATH = Environment.getExternalStorageDirectory()
            + "/care/";

    public static String FILE_PATH = Environment.getExternalStorageDirectory()
            + "/care/temp.JPEG";

    /**
     * 保存 bitmap 到文件
     *
     * @param bm
     * @return
     */
    public static boolean saveBitmap(Bitmap bm) {
        Log.e("", "保存图片");
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(FILE_PATH);
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("", f.getAbsolutePath() + "," + f.length());
            Log.e("", "已经保存");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static InputStream getInputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    public static boolean saveDrawable(Drawable drawable) {
        return saveBitmap(drawable2Bitmap(drawable));
    }

    /**
     * 将 drawable 转换成 bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 创建文件夹
     *
     * @param dirName
     * @return
     * @throws IOException
     */
    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * 删除文件
     *
     * @param fileName
     */
    public static void delFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    /**
     * 删除文件夹
     */
    public static void deleteDir() {
        File dir = new File(SDPATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    /**
     * 判断绝对路径下的文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 压缩bitmap，当文件过大的时候， 通过此方法获取
     *
     * @param path
     * @return
     */
    public static Bitmap compressBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap photo = BitmapFactory.decodeFile(path, options);
        return photo;
    }

    public static Bitmap compressBitmap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.outWidth = width;
        options.outHeight = height;
        Bitmap photo = BitmapFactory.decodeFile(path, options);
        return photo;
    }

    public static Bitmap commpressBitmap(String path, long inSampleSize) {
        float fileb = (float) inSampleSize / (50f * 1024f) * 2;

        Bitmap BitmapOrg = BitmapFactory.decodeFile(path);

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = CommonUtil.w_screeen;
        int newHeight = (int) (height / fileb);

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);

        BitmapOrg.recycle();

        return resizedBitmap;
    }

    public static Bitmap commpressBitmap1(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/test.jpg", options); //此时返回bm为空

        options.inJustDecodeBounds = false;

        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = (int) (options.outHeight / (float) CommonUtil.w_screeen);

        if (be <= 0)

            be = 1;

        options.inSampleSize = be;

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        bitmap = BitmapFactory.decodeFile("/sdcard/test.jpg", options);

        return bitmap;
    }
}
