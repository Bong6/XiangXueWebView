package com.example.common.autoservice;

import android.content.Context;

/*
*       接口下层
*               ->  模块之间互相不依赖，当另一个模块要使用到一个模块时，通过common层调用另一个模块的东西
*
* */
public interface IWebViewService {

    /*
    *       参数1 由哪个Activity/Fragment 启动WebViewActivity
    *       参数2 加载网页的网址
    *       参数3 网页标题
    *       参数4 是否显示自定义的ActionBar
    * */
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);
}
