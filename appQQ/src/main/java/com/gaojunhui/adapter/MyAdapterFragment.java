package com.gaojunhui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MyAdapterFragment extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MyAdapterFragment(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "新闻";
            case 1:
                return "视频";
        }
        return null;
    }
}
