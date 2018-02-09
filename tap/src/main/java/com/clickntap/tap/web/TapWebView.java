package com.clickntap.tap.web;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.clickntap.tap.App;
import com.clickntap.tap.AppActivity;
import com.clickntap.tap.AppJs;

public class TapWebView extends WebView {

    private TapWebViewDelegate delegate;

    public TapWebViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(TapWebViewDelegate delegate) {
        this.delegate = delegate;
    }

    public String getOnloadCode() {
        return onloadCode;
    }

    public void setOnloadCode(String onloadCode) {
        this.onloadCode = onloadCode;
    }

    private String onloadCode;

    public void add(App app, AppActivity activity, String name) {
        addJavascriptInterface(new AppJs(app, activity), name);
    }

    public TapWebView(Context context) {
        super(context);
        init();
    }

    public TapWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setAllowFileAccessFromFileURLs(true);
        this.getSettings().setAllowUniversalAccessFromFileURLs(true);
        this.getSettings().setBuiltInZoomControls(false);
        this.getSettings().setAppCacheEnabled(true);
        this.getSettings().setBlockNetworkLoads(false);
        this.getSettings().setDomStorageEnabled(true);
        this.getSettings().setUserAgentString(this.getClass().getCanonicalName());
        this.getSettings().setMediaPlaybackRequiresUserGesture(false);
        this.setWebViewClient(new WebViewClient(){
            public void onPageFinished(final android.webkit.WebView view, String url) {
                if(delegate != null) {
                    delegate.onLoad();
                }
                if(getOnloadCode() != null) {
                    view.evaluateJavascript(getOnloadCode(), null);
                }
            }
       });
    }

}
