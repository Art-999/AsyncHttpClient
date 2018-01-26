package com.example.arturmusayelyan.asynchttpclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arturmusayelyan.asynchttpclient.R;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ChildCats;

import java.util.Collections;
import java.util.List;

/**
 * Created by artur.musayelyan on 26/01/2018.
 */

public class ChildRecycleAdapter extends RecyclerView.Adapter<ChildRecycleAdapter.MyViewHolder> {
    private Context context;
    private List<ChildCats> datalistChild = Collections.emptyList();
    private LayoutInflater layoutInflater;

    public ChildRecycleAdapter(Context context, List<ChildCats> datalistChild) {
        this.context = context;
        this.datalistChild = datalistChild;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_row_child, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildCats currentCats = datalistChild.get(position);
        holder.id.setText("id:" + currentCats.getCategoryId().toString());
        holder.name.setText("name:" + currentCats.getCategoryName().toString());
        holder.count.setText("count:" + currentCats.getCategoryCount().toString());
    }

    @Override
    public int getItemCount() {
        return datalistChild.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, count;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_field_tv);
            name = itemView.findViewById(R.id.name_field_tv);
            count = itemView.findViewById(R.id.count_field_tv);
        }
    }
}
