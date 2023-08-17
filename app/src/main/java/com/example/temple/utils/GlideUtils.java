package com.example.temple.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.temple.R;
import com.example.temple.network.Comments;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide工具类
 */
public class GlideUtils {
    /*** 占位图 */
    public static int placeholderImage = R.color.text_gray;
    /*** 错误图 */
    public static int errorImage = R.color.text_gray;

    /**
     * 加载图片(默认)
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        if (url == null) return;
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage)
                .error(errorImage);

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    public static void loadBitmap(Context context, String url, ImageView imageView) {
        if (url == null) return;
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage)
                .error(errorImage);

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).asBitmap().load(url).apply(options).into(imageView);
        }
    }

    public static void loadImage(Context context, String url, ImageView imageView, int placeholder) {
        if (url == null) return;
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(placeholder);

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 指定图片大小;使用override()方法指定了一个图片的尺寸。
     * Glide现在只会将图片加载成width*height像素的尺寸，而不会管你的ImageView的大小是多少了。
     * 如果你想加载一张图片的原始尺寸的话，可以使用Target.SIZE_ORIGINAL关键字----override(Target.SIZE_ORIGINAL)
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param width     图片宽度
     * @param height    图片高度
     */
    public static void loadImageSize(Context context, String url, ImageView imageView, int width, int height) {
        if (url == null) return;
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage) //占位图
                .error(errorImage)             //错误图
                .override(width, height)
                .priority(Priority.HIGH);

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 禁用内存缓存功能
     * diskCacheStrategy()方法基本上就是Glide硬盘缓存功能的一切，它可以接收五种参数：
     * <p>
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     */

    public static void loadImageSizeKipMemoryCache(Context context, String url, ImageView imageView) {
        if (url == null) return;
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage) //占位图
                .error(errorImage)             //错误图
                .skipMemoryCache(true);          //禁用掉Glide的内存缓存功能
        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 预先加载图片
     * 在使用图片之前，预先把图片加载到缓存，调用了预加载之后，我们以后想再去加载这张图片就会非常快了，
     * 因为Glide会直接从缓存当中去读取图片并显示出来
     *
     * @param context 上下文
     * @param url     链接
     */
    public static void preloadImage(Context context, String url) {
        Glide.with(context).load(url).preload();
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     */
    public static void loadCircleImage(Context context, String url, int placeholderImage, ImageView imageView) {
        if (url == null) return;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()//设置圆形
                .placeholder(placeholderImage) //占位图
                .error(placeholderImage)             //错误图
                .priority(Priority.HIGH);

        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadCircleHead(Context context, String url, ImageView imageView) {
        if (url == null) return;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()//设置圆形
                /*.placeholder(R.mipmap.icon_head) //占位图
                .error(R.mipmap.icon_head)        */     //错误图
                .override(100, 100)
                .priority(Priority.HIGH);

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 加载圆形带边框图片
     *
     * @param context     上下文
     * @param url         链接
     * @param imageView   ImageView
     * @param borderSize  边框宽度 px
     * @param borderColor 边框颜色
     */
    public static void loadCircleWithBorderImage(Context context, String url, ImageView imageView,
                                                 int borderSize, @ColorInt int borderColor) {
        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                        new MultiTransformation<>(
                                new CenterCrop(),
                                new CropCircleWithBorderTransformation(SizeUtils.dp2px(borderSize), borderColor)
                        ))
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    public static void loadCircleWithBorderImage(Context context, int res, ImageView imageView,
                                                 int borderSize, @ColorInt int borderColor) {
        RequestOptions options = RequestOptions.bitmapTransform(
                        new MultiTransformation<>(
                                new CenterCrop(),
                                new CropCircleWithBorderTransformation(SizeUtils.dp2px(borderSize), borderColor)
                        ))
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图
        Glide.with(context).load(res).apply(options).into(imageView);
    }

    public static void loadWithBorderImage(Context context, String url, ImageView imageView,
                                           int borderSize, @ColorInt int borderColor) {
        if (url == null) return;
        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(new CenterCrop(),
                        new BorderRoundTransformation(context, 8, 0, SizeUtils.dp2px(borderSize), borderColor, 5)));
               /* .placeholder(R.mipmap.default_round)
                .error(R.mipmap.default_round);*/

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }


    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param empty     占位图
     * @param radius    圆角 px
     */
    public static void loadRoundCircleSeatImage(Context context, String url, ImageView imageView, int empty, int radius) {
        if (url == null) return;
        RequestOptions options = RequestOptions.bitmapTransform(
                        new MultiTransformation<>(
                                new CenterCrop(),
                                new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0,
                                        RoundedCornersTransformation.CornerType.ALL)
                        ))
                .placeholder(empty) //占位图
                .error(empty);      //错误图

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options)
                    .into(imageView);
        } else {
            Glide.with(context).load(url).apply(options)
                    .into(imageView);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param radius    圆角 px
     */
    public static void loadRoundCircleImage(Context context, String url, ImageView imageView, int radius) {

        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                        new MultiTransformation<>(
                                new CenterCrop(),
                                new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0,
                                        RoundedCornersTransformation.CornerType.ALL)
                        ))
                .placeholder(errorImage) //占位图
                .error(errorImage);      //错误图

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param radius    圆角 px
     */
    public static void loadRoundCircleImageLocal(Context context, String url, ImageView imageView,
                                                 int radius) {

        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                        new MultiTransformation<>(
                                new CenterCrop(),
                                new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0,
                                        RoundedCornersTransformation.CornerType.ALL)
                        ))
                .placeholder(errorImage) //占位图
                .error(errorImage);      //错误图

        Glide.with(context).load(url).apply(options).into(imageView);
    }


    public static void loadRoundCircleImage(Context context, int url, ImageView imageView,
                                            int radius) {
        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0,
                                RoundedCornersTransformation.CornerType.ALL)
                ));
               /* .placeholder(R.mipmap.default_round) //占位图
                .error(R.mipmap.default_round);*/            //错误图
        Glide.with(context).load(url).apply(options).into(imageView);

    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param corner
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, float corner) {
        if (url == null) return;

        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage)
                .error(errorImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .bitmapTransform(new RoundedCorners(SizeUtils.dp2px(corner)));

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }


    /**
     * 加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param radius    圆角 px
     * @param type      圆角位置
     */
    public static void loadRoundCircleImage(Context context, String url, ImageView imageView,
                                            int radius, RoundedCornersTransformation.CornerType type) {
        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0, type)
                ));
               /* .placeholder(R.mipmap.default_round_top) //此占位图针对上面有圆角的图片
                .error(R.mipmap.default_round_top);*/

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param radius    圆角 px
     * @param type      圆角位置
     */
    public static void loadRoundCircleImageLocal(Context context, String url, ImageView imageView,
                                                 int radius, RoundedCornersTransformation.CornerType type) {
        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0, type)
                ));
               /* .placeholder(R.mipmap.default_round_top) //此占位图针对上面有圆角的图片
                .error(R.mipmap.default_round_top);*/
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadBlogImage(Context context, String url, ImageView imageView,
                                     int radius, RoundedCornersTransformation.CornerType type) {
        if (url == null) return;

        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new RoundedCornersTransformation(SizeUtils.dp2px(radius), 0, type)
                ));
              /*  .placeholder(R.mipmap.default_blog) //此占位图针对上面有圆角的图片
                .error(R.mipmap.default_blog);*/

        if (!url.startsWith("http")) {
            Glide.with(context).load(Comments.BASE_IMAGE_URL + url).apply(options).into(imageView);
        } else {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }


    /**
     * 加载模糊图片（自定义透明度）
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param blur      模糊度，一般1-100够了，越大越模糊
     */
    /*public static void loadBlurImage(Context context, String url, ImageView imageView, int blur) {
        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new BlurTransformation(blur)
                ))
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图
        Glide.with(context).load(url).apply(options).into(imageView);
    }
*/
    /**
     * 加载模糊图片（自定义透明度）
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     * @param blur      模糊度，一般1-100够了，越大越模糊
     * @param sampling  取样
     */
    /*public static void loadBlurImage(Context context, String url, ImageView imageView, int blur, int sampling) {
        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new BlurTransformation(blur, sampling)
                ))
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图
        Glide.with(context).load(url).apply(options).into(imageView);
    }*/

    /**
     * 加载灰度(黑白)图片（自定义透明度）
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     */
   /* public static void loadBlackImage(Context context, String url, ImageView imageView) {
        RequestOptions options = RequestOptions.bitmapTransform(
                new MultiTransformation<>(
                        new CenterCrop(),
                        new GrayscaleTransformation()
                ))
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图
        Glide.with(context).load(url).apply(options).into(imageView);
    }*/

    /**
     * Glide.with(this).asGif()    //强制指定加载动态图片
     * 如果加载的图片不是gif，则asGif()会报错， 当然，asGif()不写也是可以正常加载的。
     * 加入了一个asBitmap()方法，这个方法的意思就是说这里只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了。
     * 如果你传入的还是一张GIF图的话，Glide会展示这张GIF图的第一帧，而不会去播放它。
     *
     * @param context   上下文
     * @param url       链接
     * @param imageView ImageView
     */
    private void loadGif(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderImage) //占位图
                .error(errorImage);            //错误图
        Glide.with(context)
                .load(url)
                .apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);

    }

    /**
     * 下载图片
     * 在RequestListener的onResourceReady方法里面获取下载File图片
     * new RequestListener<File>() {
     * *@Override
     * public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
     * return false;
     * }
     * <p>
     * *@Override
     * public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
     * //resource即为下载取得的图片File
     * return false;
     * }
     * }
     *
     * @param context         上下文
     * @param url             下载链接
     * @param requestListener 下载监听
     */
    public static void downloadImage(final Context context, final String url, RequestListener<File> requestListener) {
        Glide.with(context)
                .downloadOnly()
                .load(url)
                .addListener(requestListener).preload();
    }

    /**
     * 图片去色,返回灰度图片
     *
     * @param bmpOriginal 传入的图片
     * @return 去色后的图片
     */
    public static Bitmap toGrayScale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayScale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        float[] colorArray = {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0};

        cm.setSaturation(0);

        cm.set(colorArray);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayScale;
    }
}
