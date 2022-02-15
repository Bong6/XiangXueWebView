package com.example.webview;

/*
    @Author 姜小龙
    @Description

            实现common的接口，来启动WebViewActivity
                ->  所有的组件通过这个方法启动WebView
                ->  使得组件依赖common层就可以启动WebView，而不用组件之间互相依赖导致关系紊乱

    @Date 2022-02-07 15:15
*/

import android.content.Context;
import android.content.Intent;

import com.example.common.autoservice.IWebViewService;
import com.example.webview.utils.Constants;
import com.google.auto.service.AutoService;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    //启动Activity时，把所需要的参数传进去
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
            context.startActivity(intent);
        }
    }
}
