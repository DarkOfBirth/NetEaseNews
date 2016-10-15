package com.example.dllo.neteasenews.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.neteasenews.R;


/**
 * Created by dllo on 16/9/29.
 */
public class TupianFragment extends android.support.v4.app.Fragment {

    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tupian, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        rv = (RecyclerView) view.findViewById(R.id.rv_tupian);
        MyTupianAdapter adapter = new MyTupianAdapter(getActivity());
//        adapter.setTupien();
        rv.setAdapter(adapter);

    }
}
