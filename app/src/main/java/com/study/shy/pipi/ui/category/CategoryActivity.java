package com.study.shy.pipi.ui.category;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.study.shy.pipi.R;
import com.study.shy.pipi.base.BaseActivity;
import com.study.shy.pipi.bean.CategoryContext;
import com.study.shy.pipi.bean.CategoryInfo;
import com.study.shy.pipi.eventbean.SubBean;
import com.study.shy.pipi.http.HttpManager;
import com.study.shy.pipi.ui.subscibe.EndlessRecyclerOnScrollListener;
import com.study.shy.pipi.ui.view.SubButton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.iv_head_bg)
    ImageView ivHeadBg;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_head_text)
    TextView tvHeadText;
    @BindView(R.id.rv_category_context)
    RecyclerView rvContext;
    @BindView(R.id.bn_subscibe)
    SubButton bnSubscibe;

    List<CategoryContext.ItemListBean> list = new ArrayList<>();
    Map<String, String> map;
    int start = 0, num = 10;
    String categoryId,categoryName;

    @Override
    public int intiLayout() {
        return R.layout.activity_category;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        categoryId = intent.getStringExtra("CategoryId");
        categoryName = intent.getStringExtra("CategoryName");
        //Log.e("跳转得到的Id",""+id);

        if(SPUtils.getInstance("Subscibe").getBoolean(categoryName)){
            bnSubscibe.setSelect(true);
        }else {
            bnSubscibe.setSelect(false);
        }

        /**头部标题 图片的请求**/
        map = new HashMap<>();
        map.put("id", categoryId);
        HttpManager.getInstance().getCategoryInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("请求失败！");
                        Log.e("请求失败异常", "" + e.getMessage());
                        ivHeadBg.setBackgroundColor(Color.BLACK);
                        tvHeadText.setText("正在玩命开发中...");
                        tvHeadTitle.setText("待开发");
                        bnSubscibe.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(CategoryInfo categoryInfo) {
                        getCategoryInfoSuccess(categoryInfo);
                    }
                });
        /**该分类下的视频内容**/
        HttpManager.getInstance().getCategoryContext(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryContext>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShort("请求失败！");
                        Log.e("请求失败异常", "" + e.getMessage());
                    }

                    @Override
                    public void onNext(CategoryContext categoryContext) {
                        //Log.e("分类下的视频bean",""+categoryContext.toString());
                        getCategoryContextSuccess(categoryContext);
                    }
                });
    }

    public void getCategoryInfoSuccess(CategoryInfo categoryInfo) {
        Glide.with(this)
                .load(categoryInfo.getCategoryInfo().getHeaderImage())
                .into(ivHeadBg);
        tvHeadTitle.setText(categoryInfo.getCategoryInfo().getName());
        tvHeadText.setText(categoryInfo.getCategoryInfo().getDescription());
    }

    public void getCategoryContextSuccess(CategoryContext categoryContext) {

        start += 10;

        for (int i = 0; i < categoryContext.getItemList().size(); i++) {
            list.add(categoryContext.getItemList().get(i));
        }

        CategoryContextAdapter adapter = new CategoryContextAdapter(CategoryActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(CategoryActivity.this);
        rvContext.setLayoutManager(manager);
        rvContext.setAdapter(adapter);
        rvContext.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                /**该分类下的视频内容**/
                map.put("start", "" + start);
                map.put("num", "" + num);
                HttpManager.getInstance().getCategoryContext(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<CategoryContext>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtils.showShort("请求失败！");
                                Log.e("请求失败异常", "" + e.getMessage());
                            }

                            @Override
                            public void onNext(CategoryContext categoryContext) {
                                adapter.updateData(categoryContext);
                            }
                        });
            }
        });
    }


    @OnClick(R.id.bn_subscibe)
    public void onViewClicked() {
        if (bnSubscibe.getSelect()){
            SPUtils.getInstance("Subscibe").put(categoryName,true);
            //EventBus.getDefault().post(new SubBean(categoryName,categoryId,bnSubscibe.getSelect()));

        }else {
            SPUtils.getInstance("Subscibe").put(categoryName,false);
            //EventBus.getDefault().post(new SubBean(categoryName,categoryId,bnSubscibe.getSelect()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
