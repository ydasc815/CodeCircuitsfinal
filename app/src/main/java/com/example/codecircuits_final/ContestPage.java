package com.example.codecircuits_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ContestPage extends AppCompatActivity {
    public static String MESSAGE_KEY = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_page);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MESSAGE_KEY);


        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl(url);
    }
}
