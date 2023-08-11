package com.example.temple.dialog;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple.R;
import com.example.temple.adapter.MemorialCeremonyAdapter;
import com.lxj.xpopup.core.BottomPopupView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * 祭奠访客记录
 */
public class JiDianRecordPopup extends BottomPopupView implements View.OnClickListener  {

    RecyclerView view_list;
    MemorialCeremonyAdapter memorialCeremonyAdapter;

    public JiDianRecordPopup(@NotNull Context context) {
        super(context);

    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_jinian;
    }
//
    @Override
    protected void onCreate() {
        super.onCreate();
        view_list = findViewById(R.id.view_list);
        view_list.setLayoutManager(new LinearLayoutManager(getContext()));
        memorialCeremonyAdapter = new MemorialCeremonyAdapter(R.layout.item_memorial_ceremony_record_list);
        view_list.setAdapter(memorialCeremonyAdapter);

        //假数据
        ArrayList<String> data2 = new ArrayList<String>();
        data2.add("净心神咒");
        data2.add("净口神咒");
        data2.add("安土地神咒");
        data2.add("净天地神咒");
        data2.add("祝香神咒");
        memorialCeremonyAdapter.setList(data2);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_click) {
//            ll_top.setVisibility(GONE);
//            tv_jieqian.setVisibility(VISIBLE);
        }
    }

    public interface onClickDone {
        void copy();
    }

}
