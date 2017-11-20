package com.yanxing.iview;

import com.yanxing.base.BaseView;
import com.yanxing.model.WeiXinHot;

import rx.Observable;

/**
 * Created by lishuangxiang on 2016/9/13.
 */
public interface WeiXinHotView extends BaseView<WeiXinHot>{

    /**
     * 取消Rx中的订阅
     * @return
     */
    Observable.Transformer<WeiXinHot, WeiXinHot> rxLifecycle();
}
