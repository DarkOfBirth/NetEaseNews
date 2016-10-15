package com.example.dllo.neteasenews.other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.dllo.neteasenews.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/30.
 */
public class MyTupianAdapter extends RecyclerView.Adapter<MyTupianAdapter.MyViewHolder>{
    Context context;
    ArrayList<Tupian> tupien;

    public MyTupianAdapter(Context context) {
        this.context = context;
    }

    public void setTupien(ArrayList<Tupian> tupien) {
        this.tupien = tupien;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tupian, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Picasso.with(context).load(mbean.get(position).getImgUrl()).into(viewHolder.img);
//        Picasso.with(context).load(tupien.get(position).getImg().into;

    }

    @Override
    public int getItemCount() {
        return tupien.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_tupian_img);
        }
    }
}
