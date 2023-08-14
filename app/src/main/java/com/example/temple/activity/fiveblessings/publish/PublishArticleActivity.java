package com.example.temple.activity.fiveblessings.publish;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.dialog.StyleSelectPopup;
import com.example.temple.utils.GlideEngine;
import com.hw.videoprocessor.VideoProcessor;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lxj.xpopup.XPopup;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 发布文章
 */
public class PublishArticleActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout iv_left;
    @BindView(R.id.tv_pic)
    TextView tv_pic;
    @BindView(R.id.tv_video)
    TextView tv_video;

    @BindView(R.id.video_view)
    VideoView mVv;

    private int PHOTO_MAX_SIZE = 9;
    private int typeImage;

    private ArrayList<String> picList = new ArrayList<>();
    private StyleSelectPopup styleSelectPopup;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_article;
    }

    @Override
    protected void initView() {
        baseTitleGone();

//添加播放控制条,还是自定义好点
        mVv.setMediaController(new MediaController(this));

//        // 播放在线视频
//        Uri rawUri = Uri.parse("https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/6be6fed3bb634cd1ee695a4f9c4904ab.mp4");
//        mVv.setVideoPath(rawUri.toString());
//        mVv.start();
    }

    ArrayList<String> photos = new ArrayList<>();
    ArrayList<String> videos = new ArrayList<>();

    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_pic.setOnClickListener(this);
        tv_video.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_pic) {
            typeImage = PictureMimeType.ofImage();
            selectPicture(photos);
        } else if (v.getId() == R.id.tv_video) {
            typeImage = PictureMimeType.ofVideo();
            selectPicture(videos);
        }

    }

    private void selectPicture(ArrayList<String> photos) {

        styleSelectPopup = new StyleSelectPopup(this, new StyleSelectPopup.onClickDone() {
            @Override
            public void selectData(String type) {
                RxPermissions rxPermission = new RxPermissions(PublishArticleActivity.this);

                if ("photograph".equals(type)) {//拍照
                    rxPermission
                            .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                                    , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) { // Always true pre-M
                                    PictureSelector.create(PublishArticleActivity.this)
                                            .openCamera(typeImage)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                                            .maxSelectNum(typeImage == PictureMimeType.ofImage() ? PHOTO_MAX_SIZE : 1)// 最大图片选择数量 int
                                            .minSelectNum(1)// 最小选择数量 int
                                            .imageSpanCount(5)// 每行显示个数 int
                                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                            .previewImage(true)// 是否可预览图片 true or false
                                            .isCamera(true)// 是否显示拍照按钮 true or false
                                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                            .enableCrop(false)// 是否裁剪 true or false
                                            .compress(true)// 是否压缩 true or false
                                            .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                                            .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                                            .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                                            .isDragFrame(false)// 是否可拖动裁剪框(固定)
                                            .loadImageEngine(GlideEngine.createGlideEngine())
                                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                                } else {
                                    showPermissionDailog("您未打开使用相机或者存储权限", "请允许轩辕仙境使用相机或者存储权限，用于拍照或者上传图片");
                                }
                            });
                } else if ("album".equals(type)) {//相册
                    //相册
                    rxPermission
                            .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                                    , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) { // Always true pre-M
                                    PictureSelector.create(PublishArticleActivity.this)
                                            .openGallery(typeImage)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                                            .maxSelectNum(typeImage == PictureMimeType.ofImage() ? PHOTO_MAX_SIZE : 1)// 最大图片选择数量 int
                                            .minSelectNum(1)// 最小选择数量 int
                                            .imageSpanCount(5)// 每行显示个数 int
                                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                            .previewImage(true)// 是否可预览图片 true or false
                                            .isCamera(true)// 是否显示拍照按钮 true or false
                                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                            .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                                            .enableCrop(false)// 是否裁剪 true or false
                                            .compress(true)// 是否压缩 true or false
                                            .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                                            .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                                            .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                                            .isDragFrame(false)// 是否可拖动裁剪框(固定)
                                            .loadImageEngine(GlideEngine.createGlideEngine())
                                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                                } else {
                                    showPermissionDailog("您未打开使用相机或者存储权限", "请允许轩辕仙境使用相机或者存储权限，用于拍照或者上传图片");
                                }
                            });
                }
            }


        });
        new XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asCustom(styleSelectPopup)
                .show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            // 图片选择结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            // 例如 LocalMedia 里面返回三种path
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
            // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
            ArrayList<String> backPics = new ArrayList<>();
            for (LocalMedia media : selectList) {
                if (media.getWidth() != 0) {
                }
                if (media.getHeight() != 0) {
                }
                if (media.isCompressed()) {
                    picList.add(media.getCompressPath());
                    backPics.add(media.getCompressPath());
                } else if (media.isCut()) {
                    picList.add(media.getCutPath());
                    backPics.add(media.getCutPath());
                } else {
                    picList.add(media.getPath());
                    backPics.add(media.getPath());
                }
            }

            if (typeImage == PictureMimeType.ofImage()) {
                //图片

            } else if (typeImage == PictureMimeType.ofVideo()) {
                //压缩视频
                compressVideo2(this, backPics.get(0));
            }


            Log.d("select photos:", "" + backPics);

        }

    }

    public void compressVideo2(Context context, String path) {
        String compressPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "VID_" + System.currentTimeMillis() + ".mp4";
        new Thread(() -> {
            boolean success = true;
            long start = 0;
            long end;
            try {
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();

                retriever.setDataSource(context, Uri.parse(path));
                int originWidth = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                int originHeight = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                int bitrate = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE));

                start = System.currentTimeMillis();

                //视频压缩
                VideoProcessor.processor(context)
                        .input(Uri.parse(path))
                        .outHeight(originHeight)
                        .outWidth(originWidth)
                        .output(compressPath)
                        .bitrate(bitrate)//这个属性不加,有些小视频反而越压缩越大 比特率会影响到压缩视频之后的效果，可以动态去设置比特率和帧速率去调整压缩效果 bitrate/ 2 视频会有点模糊
                        //以下参数全部为可选
//                        .outWidth(outWidth)
//                        .outHeight(outHeight)
//                            .startTimeMs(startTimeMs)//用于剪辑视频
//                            .endTimeMs(endTimeMs)    //用于剪辑视频
//                            .speed(2f)            //改变视频速率，用于快慢放
//                            .changeAudioSpeed(true) //改变视频速率时，音频是否同步变化
//                            .bitrate(bitrate)       //输出视频比特率
//                            .frameRate(frameRate)   //帧率
//                            .iFrameInterval(iFrameInterval)  //关键帧距，为0时可输出全关键帧视频（部分机器上需为-1）
//                            .progressListener(listener)      //可输出视频处理进度
                        .process();
            } catch (Exception e) {
                success = false;
                Log.e("SSSSS错误", e.toString() + "");
                e.printStackTrace();
            }
            if (success) {
                end = System.currentTimeMillis();
                Log.e("SSSSS4", "压缩耗时：" + (end - start) / 1000 + "秒");
                Log.e("SSSSS5", "视频压缩后大小：" + new File(compressPath).length() / 1024 / 1024 + "MB");
                File file = new File(path);
                long length = file.length();//B -1024->kb =1024=MB
                long kb = length / 1024;
                long mb = kb / 1024;
                int compressFileSize = (int) new File(compressPath).length() / 1024 / 1024;

                //压缩后 正式操作的时候 要把压缩后的视频删除 不然手机里会有视频
                if (compressFileSize >= 50) {
                    //再次进行压缩
                    compressVideo2(context, compressPath);
                } else {
                    Log.e("SSSSS6", "onResult === " + compressPath+"\n"+"视频大小：" + new File(compressPath).length() / 1024 / 1024 + "MB");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            mVv.setVideoPath(compressPath);
                            mVv.start();
                        }
                    });
                }

            }
        }).start();
    }

}