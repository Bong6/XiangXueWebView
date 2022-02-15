package com.example.mywebviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.base.autoservice.XiangxueServiceLoader;
import com.example.common.autoservice.IWebViewService;
import com.example.webview.WebViewActivity;

import java.util.ServiceLoader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_webviewactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过工具类找到实现
                IWebViewService webviewService = XiangxueServiceLoader.load(IWebViewService.class);
                if (webviewService != null){
                    webviewService.startWebViewActivity(MainActivity.this,"https://www.baidu.com","百度",true);
                }
            }
        });
    }
}