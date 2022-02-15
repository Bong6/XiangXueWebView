package com.example.webview;

/*
    @Author 姜小龙
    @Description TODO
    @Date 2022-02-10 21:34
*/

public interface WebViewCallBack {

    void pageStarted(String url);
    void pageFinished(String url);
    void onError();
    void updateTitle(String title);

}
