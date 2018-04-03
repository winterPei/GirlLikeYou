package com.winterpei.girllikeyou.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;

/**
 * @author xingyang.pei
 * @date 2017/9/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int layoutId();

    protected abstract void initView();

    protected abstract void initViewGroup();

    protected abstract void initPresenter();

    protected abstract void initData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        initView();
        initViewGroup();
        initPresenter();
        initData();
    }

}
