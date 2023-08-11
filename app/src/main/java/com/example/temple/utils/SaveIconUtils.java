package com.example.temple.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 保存图片
 */
public class SaveIconUtils implements Serializable {

    private static SaveIconUtils mInstance = null;

    public static synchronized SaveIconUtils getInstance() {
        if (mInstance == null) {
            mInstance = new SaveIconUtils();
        }
        return mInstance;
    }

    public void save(Context mContext, Bitmap bitmap) {
        String path = PathUtils.getExternalPicturesPath() + File.separator + bitmap.hashCode() + ".jpg";
        if (!FileUtils.isFileExists(path)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // 大于等于29版本的保存方法
                ContentResolver resolver = mContext.getContentResolver();
                // 设置文件参数到ContentValues中
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, bitmap.hashCode() + ".jpg");
                values.put(MediaStore.Images.Media.DESCRIPTION, bitmap.hashCode() + ".jpg");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                Uri insertUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (insertUri != null) {
                    //若生成了uri，则表示该文件添加成功
                    //使用流将内容写入该uri中即可
                    OutputStream outputStream = null;
                    try {
                        outputStream = mContext.getContentResolver().openOutputStream(insertUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        ToastUtils.showShort("保存失败FileNotFoundException");
                    }
                    if (outputStream != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        try {
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("保存失败IOException");
                        } finally {
                            try {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else {
                    ToastUtils.showShort("保存失败未生成uri");
                }
            } else {
                // 低于29版本的保存方法
                boolean save = ImageUtils.save(bitmap, path, Bitmap.CompressFormat.JPEG);
                if (save) {
                    //媒体扫描
                    new SingleMediaScanner(mContext, path, new SingleMediaScanner.ScanListener() {
                        @Override
                        public void onScanFinish() {
                            // scanning...
                        }
                    });
                } else {
                    ToastUtils.showShort("保存失败");
                }
            }
        }
        ToastUtils.showShort("成功保存到" + path);
    }
}
