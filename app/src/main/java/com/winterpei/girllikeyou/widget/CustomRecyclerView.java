package com.winterpei.girllikeyou.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author xingyang.pei
 * @date 2017/9/6.
 */
public class CustomRecyclerView extends RecyclerView {

    public CustomRecyclerView(Context context) {
        this(context, null);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.addOnScrollListener(new OnScrollListener() {
            /**
             * 滚动时，判断当前第一个View是否发生变化，发生才回调
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View newView = getChildAt(0);
                if (null != mListener) {
                    if (null != newView && newView != mCurrentView) {
                        mCurrentView = newView;
                        mListener.onItemScrollChange(
                                mCurrentView, getChildPosition(mCurrentView)
                        );
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private View mCurrentView;

    public interface OnItemScrollChangeListener {
        void onItemScrollChange(View view, int position);
    }

    private OnItemScrollChangeListener mListener;

    public void setOnItemScrollChangeListener(OnItemScrollChangeListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(0);
//        if (null != mListener) {
//            mListener.onItemScrollChange(
//                    mCurrentView, getChildPosition(mCurrentView));
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentView = getChildAt(0);
            if (null != mListener) {
                mListener.onItemScrollChange(
                        mCurrentView, getChildPosition(mCurrentView));
            }
        }
        return super.onTouchEvent(e);
    }
}
