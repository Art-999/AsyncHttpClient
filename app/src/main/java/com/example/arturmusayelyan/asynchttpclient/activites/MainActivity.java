package com.example.arturmusayelyan.asynchttpclient.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arturmusayelyan.asynchttpclient.R;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;
import com.example.arturmusayelyan.asynchttpclient.network.MyAppHttpClient;
import com.example.arturmusayelyan.asynchttpclient.network.WebServiceManager;
import com.example.arturmusayelyan.asynchttpclient.utils.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView resultTv;
    private Button callBtn, showDataBtn;
    private String result;
    private List<ParentCats> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.text_view_result);
        callBtn = findViewById(R.id.do_call_button);
        showDataBtn = findViewById(R.id.show_data_button);
        showDataBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);
        showDataBtn.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.do_call_button:
                getCallResult();
                break;
            case R.id.show_data_button:
//                Intent intent = new Intent(this, ShowData.class);
//                //intent.putExtra("dataKey", (Serializable) dataList);
//                intent.putExtra("dataKey", parseObjectToStringJson(dataList));
                MyAppHttpClient.cancelAllRequest();
                startActivity(new Intent(this, ShowData.class));
                break;
        }
    }

//    private String parseObjectToStringJson(List<ParentCats> dataList) {
//        List<ParentCats> datalist=dataList;
//        Gson gson = new Gson();
////        Type type = new TypeToken<List<ParentCats>>() {
////        }.getType();
//        String json = gson.toJson(datalist);
//        return json;
//    }


    private void getCallResult() {
        WebServiceManager.getCallResult(this, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                resultTv.setText("Starting...");
                callBtn.setEnabled(false);
                showDataBtn.setEnabled(false);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //nuyne sovorakan Array-ov
//                ParentCats[] parentCatsDataList=gson.fromJson(String.valueOf(response),ParentCats[].class);
//                resultTv.setText(parentCatsDataList[1].toString());

                // result=JsonParser.parseResultAllJsonArrayToList(response).toString();
                //   result = JsonParser.parseResultChildJsonArrayToList(response, 1).toString();
                dataList = JsonParser.parseResultAllJsonArrayToList(response);
                //dataList.get(1).getCategoryImage();
                result = JsonParser.parseResultChildJsonArrayToList(response, 1).toString();
                Log.d("Art", dataList.get(1).getCategoryImage());

                if (dataList != null) {
                    resultTv.setText(dataList.toString());
                }
                callBtn.setEnabled(true);
                showDataBtn.setEnabled(true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                callBtn.setEnabled(true);
                resultTv.setText(throwable.toString());

                Log.d("Art", throwable + "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                resultTv.setText(throwable.toString());
                Log.d("Art", "onFailure : " + statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            protected Object parseResponse(byte[] responseBody) throws JSONException {
                return super.parseResponse(responseBody);
            }
        });
    }
}
