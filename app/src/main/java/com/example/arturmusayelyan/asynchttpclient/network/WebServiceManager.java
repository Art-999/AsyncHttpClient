package com.example.arturmusayelyan.asynchttpclient.network;

import android.content.Context;

import com.example.arturmusayelyan.asynchttpclient.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by artur.musayelyan on 23/01/2018.
 */

public class WebServiceManager {

    public static void getCallResult(Context context, AsyncHttpResponseHandler responseHandler) {
        initializeSSLContext(context);
        MyAppHttpClient.get(context, context.getString(R.string.backend_call_url), null, responseHandler);
    }

    public static void postCallResult(Context context, String relativeUrl, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        MyAppHttpClient.post(context, relativeUrl, entity, responseHandler);
    }

    public static void initializeSSLContext(Context mContext){
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
