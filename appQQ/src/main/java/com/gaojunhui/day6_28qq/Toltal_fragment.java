//package com.gaojunhui.day6_28qq;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.gaojunhui.adapter.MyAdapterFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2016/6/28.
// */
//public class Toltal_fragment extends Fragment {
//    private View view;
//    private TabLayout tabLayout;
//    private Toolbar toolbar;
//    private ViewPager vp_toltal;
//    private List<Fragment> fragments=new ArrayList<>();
//    private Fragment news_f,video_f;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        initViews(inflater);
//        initFragments();
//        MyAdapterFragment adapterFragment=new MyAdapterFragment(getActivity().getSupportFragmentManager(),fragments);
//        vp_toltal.setAdapter(adapterFragment);
//        tabLayout.setupWithViewPager(vp_toltal);
//        return view;
//    }
//
//    private void initViews(LayoutInflater inflater) {
//        view=inflater.inflate(R.layout.total_fragment,null);
//        vp_toltal= (ViewPager) view.findViewById(R.id.vp_total);
//        tabLayout= (TabLayout) view.findViewById(R.id.tableLayout_total);
//        toolbar= (Toolbar) view.findViewById(R.id.toolbar_total);
//    }
//
//    public void initFragments(){
//        news_f=new News_fragment();
//        video_f=new Video_fragment();
//        fragments.add(news_f);
//        fragments.add(video_f);
//    }
//}
