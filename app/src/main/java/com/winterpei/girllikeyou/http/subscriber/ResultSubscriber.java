package com.winterpei.girllikeyou.http.subscriber;

import android.text.TextUtils;

import com.google.gson.JsonSyntaxException;
import com.winterpei.girllikeyou.http.exception.ErrorMessageFactory;
import com.winterpei.girllikeyou.http.exception.NetworkConnectionException;
import com.winterpei.girllikeyou.http.exception.RequestIllegalException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author xingyang.pei
 * @date 2017/9/4.
 */

public abstract class ResultSubscriber<E> extends Subscriber<E> {

    @Override
    public void onError(Throwable e) {
        if (handleResponseError((Exception) e)) {
            return;
        }
        onFailure(e.getMessage());
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(E e) {
        onSuccess(e);
    }


    protected boolean handleResponseError(Exception e) {
        String message = ErrorMessageFactory.create(e);
        if (e instanceof NetworkConnectionException || e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            onFailure(message);
            return true;
        }
        if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("http")) {
            onFailure("服务异常，请稍后再试...");
        } else {
            onFailure(e.getMessage());
        }
        if (e instanceof JsonSyntaxException) {
            onFailure(new JsonSyntaxException("解析异常").getMessage());
            return true;
        }
        if (e instanceof RequestIllegalException) {
            onFailure(new RequestIllegalException("请求服务器失败").getMessage());
            return true;
        }
        if (e instanceof HttpException) {
            onFailure(new Exception("服务异常，请稍后再试...").getMessage());
            return true;
        }
        if (!TextUtils.isEmpty(message)) {
            if (message.contains("443") || message.contains("Failed")) {
                onFailure(new Exception("服务异常，请稍后再试...").getMessage());
                return true;
            }
        }
        return false;
    }

    protected abstract void onFailure(String s);

    protected abstract void onSuccess(E e);

}
