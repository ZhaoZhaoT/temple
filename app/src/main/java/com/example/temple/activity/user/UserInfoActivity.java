package com.example.temple.activity.user;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.bean.UserInfoBean;
import com.example.temple.network.Comments;
import com.example.temple.network.OnError;
import com.example.temple.utils.DataCacheUtil;
import com.example.temple.utils.GlideEngine;
import com.example.temple.utils.GlideUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rxjava.rxlife.RxLife;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rxhttp.wrapper.param.RxHttp;

public class UserInfoActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nikename)
    TextView tvNikename;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        baseTitle.setText("个人资料");
        statusBar.setBackgroundColor(getResources().getColor(R.color.background));
        rlTop.setBackgroundColor(getResources().getColor(R.color.background));
        tvNikename.setText(getIntent().getStringExtra("name"));
//        tvNikename.setText(1);
        String url = getIntent().getStringExtra("head");
        GlideUtils.loadCircleImage(mContext, url, R.mipmap.default_head, ivHead);

    }

    @Override
    protected void initListener() {
        super.initListener();
        llName.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_head) {
            getPermission();
        }else if(view.getId() == R.id.tv_save){
            updateInfo();
//            finish();
        }
    }

    public void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        startChoosePic();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
//                        showDialog();
                    }
                });
    }

    public void startChoosePic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .maxSelectNum(1)// 最大图片选择数量
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .minimumCompressSize(300)// 小于多少kb的图片不压缩
//                .imageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        LocalMedia item = selectList.get(0);
                        String loadPath;
                        if (item.isCompressed()) {
                            loadPath = item.getCompressPath();
                        } else {
                            if (TextUtils.isEmpty(item.getAndroidQToPath())) {
                                loadPath = item.getPath();
                            } else {
                                loadPath = item.getAndroidQToPath();
                            }
                        }
//                        Glide.with(mContext).load(loadPath).into(ivLogo);
                        GlideUtils.loadCircleImage(mContext, loadPath, R.mipmap.default_head, ivHead);
                        uploadFile(loadPath);
                    }
            }
        }
    }

    private String headUrl;

    public void uploadFile(String path) {
        showWaitDialog("", false);
        File file = new File(path);
        if (!file.exists()) return;
        RxHttp.postForm(Comments.FILE_UPLOAD)
                .addFile("file", file)
                .asResponse(Map.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    if (model.getData() != null) {
                        onJsonDataGetSuccess(model.getData(), 2000);
                    }
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
                });
    }


    public void updateInfo() {
        Map map = new HashMap();
        if (!TextUtils.isEmpty(headUrl)) {
            map.put("avatarUrl", headUrl);
        }
        map.put("nickName", tvNikename.getText().toString());
        RxHttp.postJson(Comments.UPDATE_INFO)
                .addAll(map)
                .asResponse(String.class)
                .to(RxLife.toMain(this))
                .subscribe(model -> {
                    onJsonDataGetSuccess(model.getData(), 1000);
                }, (OnError) error -> {
                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 1000);
                });
    }


    @Override
    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
        super.onJsonDataGetSuccess(re_data, reqcode);
        if (reqcode == 2000) {
            Map map = (Map) re_data;
            headUrl = (String) map.get("url");

        }else if(reqcode==1000){
            ToastUtils.showShort("保存成功");

            UserInfoBean user = DataCacheUtil.getInstance(mContext).getUserInfo();
            user.setAvatarUrl(headUrl);
            user.setNickName(tvNikename.getText().toString());
            DataCacheUtil.getInstance(mContext).saveUserInfo(user);


            Intent intent = new Intent();
            intent.putExtra("headUrl", headUrl);
            intent.putExtra("name", tvNikename.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}