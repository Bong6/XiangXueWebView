package com.example.webview.webviewclient;

/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-02-10 21:33
*/

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webview.WebViewCallBack;
import com.example.webview.WebViewFragment;

public class XiangxueWebViewClient extends WebViewClient {
    private WebViewCallBack mWebViewCallBack;
    private static final String TAG = "XiangxueWebViewClient";

    public XiangxueWebViewClient(WebViewCallBack callBack){
        this.mWebViewCallBack = callBack;
    }

    //
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if(mWebViewCallBack != null) {
            mWebViewCallBack.pageStarted(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if(mWebViewCallBack != null) {
            mWebViewCallBack.pageFinished(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if(mWebViewCallBack != null) {
            mWebViewCallBack.onError();
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

}
