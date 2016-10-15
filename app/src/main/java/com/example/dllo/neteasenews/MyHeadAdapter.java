package com.example.dllo.neteasenews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/29.
 */
public class MyHeadAdapter extends BaseAdapter{
    ArrayList<HeadBean> mbean;
    Context context;

    public void setMbean(ArrayList<HeadBean> mbean) {
        this.mbean = mbean;
        notifyDataSetChanged();
    }

    public MyHeadAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mbean == null ? 0 : mbean.size();
    }

    @Override
    public Object getItem(int position) {
        return mbean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_head, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(mbean.get(position).getTitle());
        Picasso.with(context).load(mbean.get(position).getImgUrl()).into(viewHolder.img);
        Log.d("MyHeadAdapter", mbean.get(position).getTitle());
        Log.d("MyHeadAdapter", mbean.get(position).getImgUrl());
        return convertView;
    }

    private class ViewHolder {

        private final ImageView img;
        private final TextView tv;

        public ViewHolder(View convertView) {
            img = (ImageView) convertView.findViewById(R.id.img_item);
            tv = (TextView) convertView.findViewById(R.id.tv_item);
        }
    }
}
