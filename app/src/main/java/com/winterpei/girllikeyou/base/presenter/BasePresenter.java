package com.winterpei.girllikeyou.base.presenter;

import com.winterpei.girllikeyou.GirlsApplication;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter extends HttpPresenter {

    private CompositeSubscription mSubscription = new CompositeSubscription();

    protected void subscribe(Subscription subscription) {
        this.mSubscription.add(subscription);
    }

    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.clear();
        }
    }

    public void resume() {

    }

    public void pause() {

    }

    public void destroy() {
        unsubscribe();
    }

    protected GirlsApplication app() {
        return GirlsApplication.getIns();
    }

}