package com.example.dllo.neteasenews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dllo.neteasenews.other.HappyFragment;
import com.example.dllo.neteasenews.other.Pefragment;
import com.example.dllo.neteasenews.other.TupianFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tv;
    private ViewPager vp;
    private ArrayList<Fragment> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TabLayout) findViewById(R.id.tv_main);
        vp = (ViewPager) findViewById(R.id.vp_main);

        arrayList = new ArrayList<>();
        arrayList.add(new HeadtiaoFragment());
        arrayList.add(new Pefragment());
        arrayList.add(new HappyFragment());
        arrayList.add(new TupianFragment());


        MyVpAdapter adapter = new MyVpAdapter(getSupportFragmentManager());
        adapter.setMfragment(arrayList);
        vp.setAdapter(adapter);
        tv.setupWithViewPager(vp);
    }
}
