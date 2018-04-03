package com.winterpei.girllikeyou.http;


import com.winterpei.girllikeyou.Girls.protocol.GirlsItemPorotocol;
import com.winterpei.girllikeyou.Girls.protocol.GirlsProtocol;
import com.winterpei.girllikeyou.base.BaseProtocol;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * @author xingyang.pei
 * @date 2017/9/4.
 */

public interface ApiService {

    @GET("data/%E7%A6%8F%E5%88%A9/30/{girlsId}")
    Observable<GirlsProtocol<GirlsItemPorotocol>> getGirlsList(@Path("girlsId") String girlsId);

}
