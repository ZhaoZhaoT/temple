
package com.example.temple.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.temple.R;


public class GenealogyEditAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    Context context;
    private OnItemSelectedListener listener;

    public GenealogyEditAdapter(Context mContext, int layoutResId, OnItemSelectedListener listener) {
        super(layoutResId);
        this.context = mContext;
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        LinearLayout lin_bg = helper.getView(R.id.lin_bg);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_nick = helper.getView(R.id.tv_nick);
        ImageView iv_head = helper.getView(R.id.iv_head);
        ImageView iv_sign = helper.getView(R.id.iv_sign);
        iv_sign.setImageResource(R.mipmap.icon_delect);

        if (item.equals("添加成员")) {
            tv_nick.setText("");
            tv_name.setText(item);
            lin_bg.setBackgroundResource(R.drawable.deep_gry_shallow_radius_twelve);
            iv_head.setImageResource(R.mipmap.icon_add);
//            iv_head.setImageResource();
            iv_sign.setVisibility(View.INVISIBLE);
        } else if (item.equals("奶奶")) {
//            iv_head.setImageResource(R.mipmap.icon_defult_head);
            lin_bg.setBackgroundResource(R.drawable.deep_gry_shallow_radius_twelve);
            iv_sign.setVisibility(View.VISIBLE);
            tv_nick.setText(item);
            //图片至灰
            Bitmap originImg = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_defult_head);
            Bitmap grayImg = Bitmap.createBitmap(originImg.getWidth() + 10, originImg.getHeight() + 10, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(grayImg);
            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0);
            ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
            paint.setColorFilter(colorMatrixFilter);
            canvas.drawBitmap(originImg, 0, 0, paint);
            iv_head.setImageBitmap(grayImg);


        } else {
            tv_nick.setText(item);
            iv_head.setImageResource(R.mipmap.icon_defult_head);
            lin_bg.setBackgroundResource(R.drawable.deep_yellow_two_radius_twelve);
            iv_sign.setVisibility(View.VISIBLE);

        }
        iv_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Log.e("SSSSS",getItemPosition(item)+"1");
                    listener.onItemSelected(getItemPosition(item));
                }
            }
        });

    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

}
