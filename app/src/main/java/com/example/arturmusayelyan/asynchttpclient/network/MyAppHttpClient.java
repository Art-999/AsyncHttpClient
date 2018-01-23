package com.example.arturmusayelyan.asynchttpclient.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by artur.musayelyan on 23/01/2018.
 */

public class MyAppHttpClient {
    private static final String BASE_URL = "https://freemegalist.com/api.php/?action=categories";
    private static AsyncHttpClient httpClient = new AsyncHttpClient();

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void cancelAllRequest() {
        httpClient.cancelAllRequests(true);
    }

    public static void get(Context context, String url, RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        httpClient.get(context, url, requestParams, responseHandler);
    }

    public static void post(Context context, String relativeUrl, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        httpClient.post(context, getAbsoluteUrl(relativeUrl), entity, "application/json", responseHandler);
    }
}
