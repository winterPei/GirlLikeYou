package com.winterpei.girllikeyou.Girls.view;

import com.winterpei.girllikeyou.Girls.protocol.GirlsItemPorotocol;
import java.util.List;


/**
 * @author xingyang.pei
 * @date 2017/9/1.
 */

public interface GirlsView {

    void getGirlsListSuccess(List<GirlsItemPorotocol> result);

    void getGirlsListFailure(String msg);

}
