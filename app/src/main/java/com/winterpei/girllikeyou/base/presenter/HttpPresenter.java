package com.winterpei.girllikeyou.base.presenter;

import android.text.TextUtils;

import com.winterpei.girllikeyou.Girls.protocol.GirlsProtocol;
import com.winterpei.girllikeyou.GirlsApplication;
import com.winterpei.girllikeyou.base.BaseProtocol;
import com.winterpei.girllikeyou.http.ApiService;
import com.winterpei.girllikeyou.http.GirlsHttp;
import com.winterpei.girllikeyou.http.exception.NetworkConnectionException;
import com.winterpei.girllikeyou.http.exception.RequestIllegalException;
import com.winterpei.girllikeyou.utils.NetUtils;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author xingyang.pei
 * @date 2017/9/4.
 */

public class HttpPresenter {

    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY_COUNT = 2;

    /**
     * 请求非法重试标识
     */
    private static final int REQUEST_ILLEGAL_CODE = -100;

    protected ApiService api() {
        return GirlsHttp.api();
    }

    protected <T> Observable<GirlsProtocol<T>> girlsHttp(Observable<GirlsProtocol<T>> observable) {
        return girlsHttp(observable, null, MAX_RETRY_COUNT);
    }


    protected <T> Observable<GirlsProtocol<T>> girlsHttp(Observable<GirlsProtocol<T>> observable, String requestUrl) {
        return girlsHttp(observable, requestUrl, 0);
    }

    private <T> Observable<GirlsProtocol<T>> girlsHttp(Observable<GirlsProtocol<T>> observable, String url, int maxRetryCount) {

        if (!NetUtils.isConnected(GirlsApplication.getIns())) {
            return Observable.error(new NetworkConnectionException());
        }

        Observable<GirlsProtocol<T>> responseObservable = responseObservable(observable);
        if (maxRetryCount >= MAX_RETRY_COUNT || maxRetryCount == REQUEST_ILLEGAL_CODE) {
            return responseObservable;
        }
        return responseObservable.retryWhen(errors -> errors.flatMap((Func1<Throwable, Observable<?>>) throwable -> {
            if (throwable instanceof RequestIllegalException) {
                if (TextUtils.isEmpty(url)) {
                    return girlsHttp(observable.retry(), null, REQUEST_ILLEGAL_CODE);
                } else {
                    return girlsHttp(observable.retry(), url, REQUEST_ILLEGAL_CODE);
                }
            }
            return Observable.error((Throwable) throwable);
        }));
    }

    private <T> Observable<GirlsProtocol<T>> responseObservable(Observable<GirlsProtocol<T>> observable) {
        return observable.flatMap(response -> {
            if (response == null) {
                return Observable.error(new NetworkConnectionException());
            }
            return Observable.just(response);
        });
    }
}
