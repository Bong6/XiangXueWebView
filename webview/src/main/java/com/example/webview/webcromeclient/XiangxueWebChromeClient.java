package com.example.webview.webcromeclient;

/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-02-10 22:17
*/

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.webview.WebViewCallBack;

public class XiangxueWebChromeClient extends WebChromeClient {
    private WebViewCallBack mWebViewCallBack;
    private static final String TAG = "XiangxueWebChromeClient";

    public XiangxueWebChromeClient(WebViewCallBack webViewCallBack) {
        this.mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if(mWebViewCallBack != null) {
            mWebViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

}