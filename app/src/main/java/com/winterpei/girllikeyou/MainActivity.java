package com.winterpei.girllikeyou;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winterpei.girllikeyou.Girls.adapter.GirlsRecAdapter;
import com.winterpei.girllikeyou.Girls.presenter.GirlsPresenter;
import com.winterpei.girllikeyou.Girls.protocol.GirlsItemPorotocol;
import com.winterpei.girllikeyou.Girls.view.GirlsView;
import com.winterpei.girllikeyou.base.activity.BaseActivity;
import com.winterpei.girllikeyou.utils.LogUtils;
import com.winterpei.girllikeyou.widget.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements GirlsView, GirlsRecAdapter.OnItemClickListener, CustomRecyclerView.OnItemScrollChangeListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerview)
    CustomRecyclerView mRecyclerView;
    @BindView(R.id.content_iv)
    ImageView mContentIv;

    private int pageSize = 1;
    private List<GirlsItemPorotocol> mItemList;

    private GirlsPresenter mGirlsPresenter;
    private GirlsRecAdapter mGirlsRecAdapter;

    @Override
    protected int layoutId() {
        return R.layout.base_listview_layout;
    }

    @Override
    protected void initView() {
        if (null == mRecyclerView) {
            mRecyclerView = ButterKnife.findById(this, R.id.recyclerview);
        }
        if (null == mContentIv) {
            mContentIv = ButterKnife.findById(this, R.id.content_iv);
        }
        //操作控件
        //创建线性布局
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        StaggeredGridLayoutManager staggeredGridLayoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecAdapter = new GirlsRecAdapter(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mGirlsRecAdapter);
        mRecyclerView.setOnItemScrollChangeListener(this);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        mGirlsRecAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initViewGroup() {

    }

    @Override
    protected void initPresenter() {
        mGirlsPresenter = new GirlsPresenter(this);
    }

    @Override
    protected void initData() {
        mItemList = new ArrayList<>();
        mGirlsPresenter.getGirlsData(String.valueOf(pageSize));
    }

    @Override
    public void getGirlsListSuccess(List<GirlsItemPorotocol> resultsBeen) {

        LogUtils.show(TAG, resultsBeen.toString());
        mItemList.addAll(resultsBeen);
        Glide.with(MainActivity.this).load(resultsBeen.get(0).url).into(mContentIv);
        mGirlsRecAdapter.setData(resultsBeen);
    }

    @Override
    public void getGirlsListFailure(String msg) {
        LogUtils.show(TAG, msg);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mItemList.size() > 0) {
            Glide.with(MainActivity.this).load(mItemList.get(position).url).into(mContentIv);
        }
    }

    @Override
    public void onItemScrollChange(View view, int position) {
        if (mItemList.size() > 0) {
            Glide.with(MainActivity.this).load(mItemList.get(position).url).into(mContentIv);
        }
    }
}
