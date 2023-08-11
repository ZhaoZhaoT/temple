package com.example.temple.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImage {
    /**
     * 保存view为图片
     */

    public static void save(final Context context, final View mView) {
        // 获取图片某布局
        mView.setDrawingCacheEnabled(true);
        mView.buildDrawingCache();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 要在运行在子线程中
                Bitmap bmp = mView.getDrawingCache();
                if (SaveImage.saveImageToGalleryBitmap(context, bmp)) {
                    ToastUtils.showShort("图片保存成功，请前往图库查看");
                } else {
                    ToastUtils.showShort("图片保存失败");
                }
                mView.destroyDrawingCache();
            }
        }, 100);
    }

    public static boolean saveImageToGalleryInt(Context context, int mipmap) {
        Resources res = context.getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(mipmap);
        Bitmap bitmap = d.getBitmap();
        // 首先保存图片
        File appDir = new File(context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "qtzn");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
            ToastUtils.showShort("保存成功,请前往图库查看");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("TAG", "FileNotFoundException: ");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "IOException: ");
            return false;
        }
    }

    public static boolean saveImageToGalleryBitmap(Context context, Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "qtzn");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("TAG", "FileNotFoundException: ");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG", "IOException: ");
            return false;
        }
    }
    //生成图片
    public static Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }
}
