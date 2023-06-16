package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class webViewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        webView=findViewById(R.id.webView);
        Intent intent=getIntent();
        if(null!=intent){
            String url=intent.getStringExtra("url");
            if(null !=url){
                webView.loadUrl(url);
                webView.setWebViewClient(new WebViewClient());
            }

        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
