package com.example.webview;

/*
    @Author 姜小龙
    @Description

            具体的WebView实现
                ->  兼容Activity

    @Date 2022-02-07 14:30
*/

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.webview.databinding.ActivityWebviewBinding;
import com.example.webview.utils.Constants;


public class WebViewActivity extends AppCompatActivity {

    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置DataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        //设置标题
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));

        //是否显示自定义的顶部ActionBar
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true)? View.VISIBLE:View.GONE);
        //返回上个页面
        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });


        //将WebView作为Fragment嵌入FrameLayout帧布局里
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constants.URL),true);
        transaction.replace(R.id.web_view_fragment, fragment).commit();
    }

    public void updateTitle(String title){
        mBinding.title.setText(title);
    }

}
