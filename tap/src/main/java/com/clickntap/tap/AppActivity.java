package com.clickntap.tap;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    private List<BroadcastReceiver> receivers;

    protected void onCreate(Bundle savedInstanceState) {
        receivers = new ArrayList<BroadcastReceiver>();
        super.onCreate(savedInstanceState);
    }

    protected void onDestroy() {
        super.onDestroy();
        for (BroadcastReceiver receiver : receivers) {
            unregisterReceiver(receiver);
        }
    }

    public void registerReceiver(BroadcastReceiver receiver, String channel) {
        registerReceiver(receiver, new IntentFilter(channel));
        receivers.add(receiver);
    }


    public App getApp() {
        return (App) getApplication();
    }

    public void setSize(int resourceId, int w, int h) {
        setSize((ViewGroup) findViewById(resourceId), w, h);
    }

    public void setSize(ViewGroup viewGroup, int w, int h) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams params = viewGroup.getLayoutParams();
        params.width = (int) (w * metrics.density);
        params.height = (int) (h * metrics.density);
        viewGroup.requestLayout();
    }

    public void setMargin(int resourceId, int left, int top, int right, int bottom) {
        setMargin((ViewGroup) findViewById(resourceId), left, top, right, bottom);
    }

    public int getDeviceWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) (metrics.widthPixels / metrics.density);
    }

    public int getDeviceHeight() {
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (int) ((metrics.heightPixels-statusBarHeight) / metrics.density);
    }

    public void setMargin(ViewGroup viewGroup, int left, int top, int right, int bottom) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        left = (int) (left * metrics.density);
        top = (int) (top * metrics.density);
        right = (int) (right * metrics.density);
        bottom = (int) (bottom * metrics.density);
        if (viewGroup.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams()).setMargins(left, top, right, bottom);
        }
        viewGroup.requestLayout();
    }

    public void setFrame(int resourceId, int left, int top, int w, int h) {
        ViewGroup viewGroup = (ViewGroup) findViewById(resourceId);
        setSize(viewGroup, w, h);
        setMargin(viewGroup, left, top, 0, 0);
    }

    public void setFrame(ViewGroup viewGroup, int left, int top, int w, int h) {
        setSize(viewGroup, w, h);
        setMargin(viewGroup, left, top, 0, 0);
    }
}
