package com.example.temple.activity.fiveblessings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.adapter.BaiJiaXingAdapter;
import com.example.temple.dialog.SurNamePopup;
import com.example.temple.view.SpacesItemDecoration;
import com.lxj.xpopup.XPopup;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class BaiJiaXingActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.iv_left)
    RelativeLayout mIvLeft;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_list)
    RecyclerView mViewList;

    @BindView(R.id.tv_surname_sacrifice)
    TextView mTvSurnameSacrifice;
    @BindView(R.id.tv_ancestral_worship)
    TextView mTvAncestralWorship;
    @BindView(R.id.tv_wedding_banquet)
    TextView mTvWeddingBanquet;

    private SurNamePopup surNamePopup;
    private BaiJiaXingAdapter mAdapter;
    private int mPage = 0, mSize = 10, mTotalPage;
    String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baijiaxing;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
        mViewList.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(7.5f), 4));
        mViewList.setLayoutManager(new GridLayoutManager(this, 4));
        mViewList.setHasFixedSize(true);
        mAdapter = new BaiJiaXingAdapter(R.layout.item_baijiaxing);
        mViewList.setAdapter(mAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView tv_load_empty = view.findViewById(R.id.tv_load_empty);
        tv_load_empty.setText("暂无数据");
        ImageView iv_load_empty = view.findViewById(R.id.iv_load_empty);
        iv_load_empty.setImageResource(R.mipmap.haode_botton);

        mAdapter.setEmptyView(view);

        //假数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("趙");
        data.add("錢");
        data.add("孫");
        data.add("李");
        data.add("周");
        data.add("吳");
        data.add("鄭");
        data.add("王");
        mAdapter.setList(data);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {


                if(mAdapter.getData().get(position).equals("趙")){
                    title="赵(zhào)";
                    content="【起源】出自嬴姓，嬴姓的出现是因为舜帝（姚姓，后代以姚为姓）赐姓给他的女婿伯益（颛顼帝孙）为“嬴”，并把自己的姚姓的女儿嫁给他。虽然使用嬴姓的祖先是伯益，但赵姓的具体始祖是造父。";
                }else if(mAdapter.getData().get(position).equals("錢")){
                    title="钱(qián)";
                    content="【起源】钱姓可以追溯至黄帝七世孙彭祖。这位史传寿高880岁的寿星真名叫篯铿，后被尧赏识，受封于大彭，成为彭姓的祖先。而彭祖之子篯孚，在西周都城，任钱府（掌管钱财的官署）上士（官名），篯孚就以“钱”为姓，所以彭姓与钱姓本为一家，民间至今也有“彭钱不分家”的说法。由于西周建都于镐京（今陕西西安），篯孚必在京为官，所以钱姓也是发源于陕西一带。";
                }else if(mAdapter.getData().get(position).equals("孫")){
                    title="孙(sūn)";
                    content="【起源】源于姬姓。公元前1055年，周公姬旦封康叔於卫（今河南淇县），建立卫国。春秋时，他的八世孙姬和因为攻灭西戎有功，被周平王赐为公爵，史称卫武公。卫武公有一个儿子名叫惠孙，惠孙的孙子名乙，字武仲，又叫武仲乙，把祖父惠孙的字作为姓氏，姓孙。因此武仲乙又称孙乙，他的后代就以孙为姓。";
                }else if(mAdapter.getData().get(position).equals("李")){
                    title="李(lǐ)";
                    content="【起源】源出嬴姓,即认为李氏源出嬴姓，血缘先祖为皋陶（一作咎繇，嬴姓，一说：姬姓），皋陶曾被任命为舜的大理（掌管刑法的官），遂以官命族为理氏（“理”“李”古字相通），先为理氏，后为李氏。 至商纣王时，世袭为理官的理徵因直谏触怒纣王被杀，其子利贞随母亲契和氏逃难，后到豫东鹿邑定居。因沿途食李子(木本植物的果实)得以生存，为了报答“李子”保命之恩和躲避纣王的追捕，并且理、李同音，自利贞开始改理为李，从此中国有了李姓。得姓始祖为李利贞，李耳为十一世。";
                }else if(mAdapter.getData().get(position).equals("周")){
                    title="周(zhōu)";
                    content="【起源】出自古周国。周姓的最早出现，可追溯到远古的黄帝时期。 5000年前的黄帝时代已经存在周部落，黄帝臣子周昌和周书，都属周部落（古周国）。 据《姓氏考略》所载，相传黄帝时就有一位叫周昌的大将，至商代又有一名叫周任的太史，这两个人的后代都以周为姓氏。《河图运录法》载，远古黄帝轩辕氏有大将周昌，商代有太史周任，两人后代均以周为姓氏，分散于古时的汝南（今河南、安徽一带）、庐江（今安徽、湖北境）、浔阳（今属江西）、临川（今属江西）、陈留（今属河南）、沛国（今河南、安徽、江苏境）、泰山（今属山东）、河南（今属河南）等地。";
                }else if(mAdapter.getData().get(position).equals("吳")){
                    title="吴(wú)";
                    content="【起源】源于姜姓,早在黄帝之前，有一个属于姜姓的部落，这个部落以驺虞为图腾。上古时，虞和吴同音，后来转为谐音，且字形相近，可以通用，到战国时期，虞、吴才开始区别。所以这支部落把图腾解释为吴，把吴作为部落的名字。吴部落勇敢剽悍，善于狩猎，首领叫吴权，是炎帝的大臣。吴部落在姜水（今陕西陇县陇山东）活动，他们居住的地方叫吴山。吴权的后裔中有个叫吴枢的女子，嫁给有熊部落的少典，生了个儿子，就是后来的黄帝。夏朝时，吴部落迁徙到观津（今河北武邑东）。夏王少康时期，吴部落有个人叫吴贺，以善射著称，曾和当时的神箭手后羿比射。这个姜姓吴部落的人，后来就以吴为姓氏，至今有5000年以上的历史。";
                }else if(mAdapter.getData().get(position).equals("鄭")){
                    title="郑(zhènɡ)";
                    content="【起源】源于姬姓，以国号为氏。出自周宣王之弟姬友的封地郑国，远祖为郑桓公。 公元前375年，郑国被韩国所灭。郑国灭亡后，散居于京（今河南荥阳京襄城）、制（今荥阳西）、祭（今河南郑州东）、陈（今河南淮阳）、宋（今河南商丘）等地，为纪念故国，郑国人相继改姓为郑，自此，郑姓诞生。";
                }else if(mAdapter.getData().get(position).equals("王")){
                    title="王(wánɡ)";
                    content="【起源】周灵王长子太子晋，称王子晋，因直谏而被废为平民。其子宗敬仍在朝中任司徒之职，时人因其是王族的后代便称为“王家”，这支族人遂以王为氏。 先秦时期，这支王姓一直活跃于河南洛阳一带，秦末汉初，王离之子王元和王威，为避战乱分别迁徙至山东琅琊、山西太原，最终形成琅琊王氏、太原王氏。";
                }

                surNamePopup = new SurNamePopup(mContext,title,content);
                new XPopup.Builder(mContext)
                        .dismissOnBackPressed(true)
                        .dismissOnTouchOutside(true)
                        .asCustom(surNamePopup)
                        .show();
            }
        });
    }


    public void getList() {
//        RxHttp.get(Comments.HAODE_COPY)
//                .add("page", mPage)
//                .add("size", mSize)
//                .asResponse(ChaoJinListBean.class)
//                .doFinally(() -> {
        //请求结束，当前在主线程回调
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadMore();
        }
//                })
//                .to(RxLife.toMain(this))
//                .subscribe(model -> {
//                    mTotalPage = model.getData().getTotalPages();
//                    onJsonDataGetSuccess(model.getData(), 2000);
//                }, (OnError) error -> {
//                    onJsonDataGetFailed(error.getErrorCode(), error.getErrorMsg(), 2000);
//                });
    }


//    @Override
//    public void onJsonDataGetSuccess(Object re_data, int reqcode) {
//        super.onJsonDataGetSuccess(re_data, reqcode);
//        if (reqcode == 2000) {
//            ChaoJinListBean bean = (ChaoJinListBean) re_data;
//            if (bean.getContent() != null && bean.getContent().size() > 0) {
//                if (mPage == 0) {
//                    jinWenListAdapter.setList(bean.getContent());
//                } else {
//                    jinWenListAdapter.addData(bean.getContent());
//                }
//            } else {
//                if (mPage == 0) {
//                    jinWenListAdapter.setList(null);
//                }
//            }
//
//        }
//    }


    protected void refreshData() {
        //分页情况用于刷新数据
        mPage = 0;
        getList();
    }

    protected void loadMoreData() {
        //分页情况用于加载更多数据
        if (mPage < mTotalPage - 1) {
            mPage++;
            getList();
        } else {
            ToastUtils.showShort("没有更多数据了");

            if (refreshLayout.isLoading()) {
                refreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvLeft.setOnClickListener(this);
        mTvSurnameSacrifice.setOnClickListener(this);
        mTvAncestralWorship.setOnClickListener(this);
        mTvWeddingBanquet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_wedding_banquet) {
            startActivity(new Intent(BaiJiaXingActivity.this, WeddingBanquetActivity.class));
        } else if (v.getId() == R.id.tv_ancestral_worship) {
            startActivity(new Intent(BaiJiaXingActivity.this, AncestralWorshipActivity.class));
        } else if (v.getId() == R.id.tv_surname_sacrifice) {
            startActivity(new Intent(BaiJiaXingActivity.this, SurnameSacrificeActivity.class));
        }

    }


}