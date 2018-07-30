package com.example.laksh.project3a1;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by laksh on 10/30/2017.
 */

public class webClient extends WebViewClient {

    //If WebViewClient is provided, return true means the host application handles the url,
    //  while return false means the current WebView handles the url.
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return false;

    }

}