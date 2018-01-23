package com.example.arturmusayelyan.asynchttpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arturmusayelyan.asynchttpclient.network.WebServiceManager;
import com.example.arturmusayelyan.asynchttpclient.utils.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView resultTv;
    private Button callBtn;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.text_view_result);
        callBtn = findViewById(R.id.do_call_button);
        callBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.do_call_button:
                getCallResult();
                break;
        }
    }

    private void getCallResult() {
        WebServiceManager.getCallResult(this, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                resultTv.setText("Starting...");
                callBtn.setEnabled(false);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //nuyne sovorakan Array-ov
//                ParentCats[] parentCatsDataList=gson.fromJson(String.valueOf(response),ParentCats[].class);
//                resultTv.setText(parentCatsDataList[1].toString());

                // result=JsonParser.parseResultAllJsonArrayToList(response).toString();
                result = JsonParser.parseResultChildJsonArrayToList(response, 1).toString();

                if (result != null) {
                    resultTv.setText(result);
                }
                callBtn.setEnabled(true);
            }

        });
    }
}
