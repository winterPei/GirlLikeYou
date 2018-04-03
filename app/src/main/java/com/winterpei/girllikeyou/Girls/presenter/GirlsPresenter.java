package com.winterpei.girllikeyou.Girls.presenter;

import com.winterpei.girllikeyou.Girls.protocol.GirlsItemPorotocol;
import com.winterpei.girllikeyou.Girls.protocol.GirlsProtocol;
import com.winterpei.girllikeyou.Girls.view.GirlsView;
import com.winterpei.girllikeyou.base.presenter.BasePresenter;
import com.winterpei.girllikeyou.http.subscriber.ResultSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author xingyang.pei
 * @date 2017/9/1.
 */
public class GirlsPresenter extends BasePresenter {

    private GirlsView mGirlsView;

    public GirlsPresenter(GirlsView girlsView) {
        this.mGirlsView = girlsView;
    }

    public void getGirlsData(String pageId) {
        subscribe(girlsHttp(api().getGirlsList(pageId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultSubscriber<GirlsProtocol<GirlsItemPorotocol>>() {

                    @Override
                    protected void onFailure(String s) {
                        mGirlsView.getGirlsListFailure(s);
                    }

                    @Override
                    protected void onSuccess(GirlsProtocol<GirlsItemPorotocol> result) {
                        mGirlsView.getGirlsListSuccess(result.results);
                    }
                }));
    }

}
