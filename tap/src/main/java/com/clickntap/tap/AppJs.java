package com.clickntap.tap;

import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class AppJs {

    private App app;
    private AppActivity activity;

    public AppJs(App app, AppActivity activity) {
        this.app = app;
        this.activity = activity;
    }

    @JavascriptInterface
    public void notification(String json) {
        Intent intent = new Intent();
        intent.setAction("appjs-notification");
        intent.putExtra("json", json);
        app.sendBroadcast(intent);
    }

    @JavascriptInterface
    public void postMessage(String json) {
        Log.d("nsr", json);
        Intent intent = new Intent();
        intent.setAction("appjs-notification");
        intent.putExtra("json", json);
        app.sendBroadcast(intent);
    }

    @JavascriptInterface
    public int getDeviceWidth() {
        return activity.getDeviceWidth();
    }

    @JavascriptInterface
    public int getDeviceHeight() {
        return activity.getDeviceHeight();
    }

}
