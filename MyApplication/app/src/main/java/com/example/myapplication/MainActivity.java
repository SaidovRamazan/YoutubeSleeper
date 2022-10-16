package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    //Intent intent;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        String youtubeUrl = "";

        if (Intent.ACTION_SEND.equals(action) && type != null)
            if ("text/plain".equals(type))
                youtubeUrl = handleSendText(intent); // Handle text being sent


        try {
            new VideoLinkProvider().getVideoLink(youtubeUrl);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так " + e.getMessage());
        }

        WebView webView   = findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        WebChromeClient webChromeClient = new WebChromeClient();


        webView.loadUrl("http://ru.savefrom.net/#url=" + youtubeUrl);

        /*

        WebViewClient webViewClient = new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };

         */

        webView.setWebChromeClient(webChromeClient);
    }

    String handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            return sharedText;
        }
        return "";
    }

}


