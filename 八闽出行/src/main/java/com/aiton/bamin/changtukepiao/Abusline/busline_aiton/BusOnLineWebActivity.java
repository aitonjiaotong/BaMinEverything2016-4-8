package com.aiton.bamin.changtukepiao.Abusline.busline_aiton;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.aiton.bamin.changtukepiao.Bchangtukepiao.constant.ConstantTicket;
import com.aiton.bamin.changtukepiao.R;

public class BusOnLineWebActivity extends AppCompatActivity
{

    private WebView mWebview_online_bus;
    private LinearLayout mLl_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_on_line_web);
        findViewID();
        initUI();
    }

    private void initUI()
    {
        WebSettings settings = mWebview_online_bus.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebview_online_bus.setWebViewClient(new MyWebViewClient());
        mWebview_online_bus.setWebChromeClient(new MyWebChromeClient());
        mWebview_online_bus.loadUrl(ConstantTicket.URL.ONLINE_BUS_WEB_HTML);

        //启用数据库
        settings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //启用地理定位
        settings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        settings.setDomStorageEnabled(true);

        // 设置可以支持缩放
//        settings.setBuiltInZoomControls(true);
//        settings.setSupportZoom(true);
        //自适应屏幕
//        settings.setLoadWithOverviewMode(true);
//        settings.setUseWideViewPort(true);
        mWebview_online_bus.requestFocusFromTouch();
    }

    private void findViewID()
    {
        mWebview_online_bus = (WebView) findViewById(R.id.webview_online_bus);
        mLl_loading = (LinearLayout) findViewById(R.id.ll_loading);
    }

    class MyWebChromeClient extends WebChromeClient
    {
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback)
        {
            super.onShowCustomView(view, callback);
        }

        //配置权限（同样在WebChromeClient中实现）
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback)
        {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
            mLl_loading.setVisibility(View.GONE);
            mWebview_online_bus.setVisibility(View.VISIBLE);
        }

    }
}
