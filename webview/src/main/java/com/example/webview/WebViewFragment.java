package com.example.webview;

/*
    @Author 姜小龙
    @Description


            1.具体的WebView实现
                ->  兼容Fragment

            2.在页面正在加载、加载失败等 显示一些页面

    @Date 2022-02-07 17:03
*/

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.webview.databinding.FragmentWebviewBinding;
import com.example.webview.utils.Constants;
import com.example.webview.webcromeclient.XiangxueWebChromeClient;
import com.example.webview.webviewclient.XiangxueWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private boolean mCanNativeRefresh;
    private boolean mIsError = false;
    private static final String TAG = "WebViewFragment";


    //静态创建的方式，方便的知道要传入的参数
    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }


    //获取参数信息
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            mUrl = bundle.getString(Constants.URL);
            mCanNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        //js交互
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        //加载网址
        mBinding.webview.loadUrl(mUrl);
        //注册
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                //显示    正在加载
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        //设置自定义WebViewClient    WebCgrome
        mBinding.webview.setWebViewClient(new XiangxueWebViewClient(this));
        mBinding.webview.setWebChromeClient(new XiangxueWebChromeClient(this));

        //刷新监听
        mBinding.smartrefreshlayout.setOnRefreshListener(this);
        //设置是否有我自定义的刷新  ->  WebView好像内部有刷新功能？？？
        mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        //在第一次加载时不允许下拉加载更多
        mBinding.smartrefreshlayout.setEnableLoadMore(false);

        //使用LoadSir不一样      ->  不然报错The specified child already has a parent. You must call removeView() on the child's parent first
        //return mBinding.getRoot();
        return mLoadService.getLoadLayout();
    }

    //当正在加载页面时
    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    //当页面加载完成时
    @Override
    public void pageFinished(String url) {
        if(mIsError) {
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        }
        Log.d(TAG, "pageFinished");
        mBinding.smartrefreshlayout.finishRefresh();
        if (mLoadService != null) {
            if(mIsError){
                mLoadService.showCallback(ErrorCallback.class);
            }   else {
                mLoadService.showSuccess();
            }
        }
        mIsError = false;
    }

    //加载发生错误时   ->  因为默认一开始不允许使用刷新功能
    //              ->  加载发生错误时,要允许刷新
    @Override
    public void onError() {
        Log.e(TAG, "onError");
        mIsError = true;
        mBinding.smartrefreshlayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity)getActivity()).updateTitle(title);
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}
