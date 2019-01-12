package com.example.shahajalal.webviewtemplatestandard;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;


public class WbViewActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressBar progressBar;
    private Button button;
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb_view);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpHost httpproxy = new HttpHost("45.77.135.180",21);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpproxy);






        //System.setProperty("http.proxyHost", "45.77.135.180");
        //System.setProperty("http.proxyPort","21");
        progressBar=findViewById(R.id.progressBar);
        webview= findViewById(R.id.webviewid);
        button=findViewById(R.id.buttonid);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings websettings=webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setBuiltInZoomControls(true);
        websettings.getDatabaseEnabled();
        websettings.getCacheMode();
        websettings.getAllowUniversalAccessFromFileURLs();
        websettings.getAllowFileAccessFromFileURLs();
        websettings.getAllowContentAccess();
        websettings.supportMultipleWindows();
        websettings.setMediaPlaybackRequiresUserGesture(true);
        websettings.getJavaScriptCanOpenWindowsAutomatically();
        websettings.setDomStorageEnabled(true);
        websettings.setLoadsImagesAutomatically(true);
        webview.setWebViewClient(new myWebClient());
        webview.loadUrl("http://m.rtnn.net/");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent=getOpenFacebookIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + "https://m.facebook.com/tuhin.rahman.733"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/tuhin.rahman.733"));
        }
    }




    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            Toast.makeText(WbViewActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            //super.onReceivedError(view, errorCode, description, failingUrl);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }



}
