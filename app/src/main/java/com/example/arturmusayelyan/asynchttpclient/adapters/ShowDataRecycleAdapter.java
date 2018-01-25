package com.example.arturmusayelyan.asynchttpclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arturmusayelyan.asynchttpclient.R;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;

import java.util.Collections;
import java.util.List;

/**
 * Created by artur.musayelyan on 24/01/2018.
 */

public class ShowDataRecycleAdapter extends RecyclerView.Adapter<ShowDataRecycleAdapter.MyViewHolder> {
    private List<ParentCats> datalist = Collections.emptyList();
    private Context context;
    private LayoutInflater inflater;

    public ShowDataRecycleAdapter(Context context, List<ParentCats> dataList) {
        this.context = context;
        this.datalist = dataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ParentCats currentCats = datalist.get(position);
        holder.id.setText("id:" + currentCats.getCategoryId().toString());
        holder.name.setText("name:" + currentCats.getCategoryName().toString());
        holder.count.setText("count:" + currentCats.getCategoryCount().toString());
        String url = context.getString(R.string.image_call_url) + currentCats.getCategoryImage();
        // String url="https://freemegalist.com/images/1410350005-home_&_garden.png";
        // Picasso.with(context).load(url).into(holder.imageView);
        Glide.with(context).load(url).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if (datalist != null) {
            return datalist.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView id, name, count;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_field_tv);
            name = itemView.findViewById(R.id.name_field_tv);
            count = itemView.findViewById(R.id.count_field_tv);
            imageView = itemView.findViewById(R.id.image_field);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
