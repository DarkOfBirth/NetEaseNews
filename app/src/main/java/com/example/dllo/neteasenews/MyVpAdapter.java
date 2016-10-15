package com.example.dllo.neteasenews;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/29.
 */
public class MyVpAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mfragment;
    Context mcontext;
    String [] titles = {"头条", "体育", "娱乐", "图片"};

    public void setMfragment(ArrayList<Fragment> mfragment) {
        this.mfragment = mfragment;
    }


    public MyVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragment.get(position);
    }

    @Override
    public int getCount() {
        return mfragment == null ? 0 : mfragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
