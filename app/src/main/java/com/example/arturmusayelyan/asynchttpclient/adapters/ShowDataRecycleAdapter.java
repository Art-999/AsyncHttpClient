package com.example.arturmusayelyan.asynchttpclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.arturmusayelyan.asynchttpclient.R;
import com.example.arturmusayelyan.asynchttpclient.dataModel.ParentCats;
import com.example.arturmusayelyan.asynchttpclient.interfaces.RecycleItemClick;

import java.util.Collections;
import java.util.List;

/**
 * Created by artur.musayelyan on 24/01/2018.
 */

public class ShowDataRecycleAdapter extends RecyclerView.Adapter<ShowDataRecycleAdapter.MyViewHolder> {
    private List<ParentCats> datalist = Collections.emptyList();
    private Context context;
    private LayoutInflater inflater;
    private RecycleItemClick recycleItemClick;
    private String ID;

    public ShowDataRecycleAdapter(Context context, List<ParentCats> dataList) {
        this.context = context;
        this.datalist = dataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecycleItemClickListener(RecycleItemClick recycleItemClick){
        this.recycleItemClick=recycleItemClick;
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
            // Picasso.with(context).load(url).into(holder.imageView);
            Glide.with(context).load(url).listener(new RequestListener<String, GlideDrawable>() {

                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    //hide progress
                    return false;
                }
            }).into(holder.imageView);

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
        private LinearLayout llRoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_field_tv);
            name = itemView.findViewById(R.id.name_field_tv);
            count = itemView.findViewById(R.id.count_field_tv);
            imageView = itemView.findViewById(R.id.image_field);
            llRoot = itemView.findViewById(R.id.ll_root);
            llRoot.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           // Toast.makeText(context,"Item clicked "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
            recycleItemClick.recycleItemClick(view,getAdapterPosition());
        }
    }
}
