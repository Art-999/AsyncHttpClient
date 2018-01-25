package com.example.arturmusayelyan.asynchttpclient.activites;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.arturmusayelyan.asynchttpclient.R;
import com.example.arturmusayelyan.asynchttpclient.adapters.ShowDataRecycleAdapter;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;
import com.example.arturmusayelyan.asynchttpclient.network.WebServiceManager;
import com.example.arturmusayelyan.asynchttpclient.utils.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ShowData extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ParentCats> dataList;
    private ProgressBar progressBar;
    private String dataListFromIntent;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        init();
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            dataListFromIntent = extras.getString("dataKey");
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<ParentCats>>() {
//            }.getType();
//            dataList = gson.fromJson(dataListFromIntent, ParentCats.class);
//        }
        getCallResult();
        ShowDataRecycleAdapter adapter = new ShowDataRecycleAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view1);
        progressBar = findViewById(R.id.progress_bar);
        view = findViewById(R.id.progress_include_layout);
    }

    public void showProgressIncludeLayout() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.VISIBLE);
                    }
                });
//                SystemClock.sleep(2000);
//                hideProgressIncludeLayout();
            }
        });
        thread.start();
    }

    public void hideProgressIncludeLayout() {
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        });
    }

    private void getCallResult() {
        WebServiceManager.getCallResult(this, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                // progressBar.setVisibility(View.VISIBLE);
                showProgressIncludeLayout();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                dataList = JsonParser.parseResultAllJsonArrayToList(response);
                Log.d("Art", dataList.toString());
                recyclerView.setAdapter(new ShowDataRecycleAdapter(getApplicationContext(), dataList));
                recyclerView.invalidate();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        hideProgressIncludeLayout();
                    }
                }).start();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                hideProgressIncludeLayout();
                Toast.makeText(ShowData.this, "Connection faild", Toast.LENGTH_LONG).show();
            }
        });

    }
}
