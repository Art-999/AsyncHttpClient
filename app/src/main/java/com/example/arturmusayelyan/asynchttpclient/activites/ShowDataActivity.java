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
import com.example.arturmusayelyan.asynchttpclient.adapters.ChildRecycleAdapter;
import com.example.arturmusayelyan.asynchttpclient.adapters.ShowDataRecycleAdapter;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ChildCats;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;
import com.example.arturmusayelyan.asynchttpclient.interfaces.RecycleItemClick;
import com.example.arturmusayelyan.asynchttpclient.network.WebServiceManager;
import com.example.arturmusayelyan.asynchttpclient.utils.JsonParser;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ShowDataActivity extends AppCompatActivity implements RecycleItemClick {
    public final static String PARENTS_CATS = "parentsCats";
    public final static String CHILD_CATS = "chaldCats";

    private RecyclerView recyclerView;
    private ArrayList<ParentCats> dataList;
    private ArrayList<ChildCats> childList;
    private ProgressBar progressBar;
    private String dataListFromIntent;
    private View view;
    private boolean adapterChecking;

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
//        ShowDataRecycleAdapter adapter = new ShowDataRecycleAdapter(this, dataList);
//        recyclerView.setAdapter(adapter);
//        adapter.setRecycleItemClickListener(this);
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view1);
        progressBar = findViewById(R.id.progress_bar);
        view = findViewById(R.id.progress_include_layout);
        adapterChecking = false;
    }

    public void showProgressIncludeLayout(final int mils) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.VISIBLE);
                    }
                });
                SystemClock.sleep(mils);
                hideProgressIncludeLayout();
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
                showProgressIncludeLayout(2000);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                dataList = JsonParser.parseResultAllJsonArrayToList(response);
                initParentAdapter(dataList);
//                recyclerView.invalidate();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                hideProgressIncludeLayout();
                Toast.makeText(ShowDataActivity.this, "Connection failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    //karevor metoda
    private void initParentAdapter(ArrayList<ParentCats> data) {
        if (data != null && data.size() > 0) {
            ShowDataRecycleAdapter adapter = new ShowDataRecycleAdapter(this, data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setRecycleItemClickListener(this);
        }
    }

    private void initChildAdapter(ArrayList<ChildCats> data) {
        if (data != null && data.size() > 0) {
            ChildRecycleAdapter adapter = new ChildRecycleAdapter(this, data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapterChecking = true;
        }
    }


    @Override
    public void recycleItemClick(View view, final int position) {
        // Toast.makeText(this, "Item clicked " + position, Toast.LENGTH_SHORT).show();
        WebServiceManager.getCallResult(this, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                showProgressIncludeLayout(1000);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                childList = JsonParser.parseResultChildJsonArrayToList(response, position);
                Log.d("Art", childList + "");
                // initParentAdapter(dataList, CHILD_CATS);
                initChildAdapter(childList);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (adapterChecking) {
            initParentAdapter(dataList);
            adapterChecking = false;
        } else {
            super.onBackPressed();
        }
        //Toast.makeText(this,"Back worked",Toast.LENGTH_SHORT).show();

    }
}
