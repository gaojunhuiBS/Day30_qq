package com.gaojunhui.day6_28qq;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gaojunhui.adapter.MyRecyclerAdapter_Video;

import java.util.ArrayList;
import java.util.List;

import bean.Video;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import parserjson.ParserJson;
import utils.Tools;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Video_fragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private String url="http://c.3g.163.com/nc/video/Tlist/T1457069041911/0-10.html";
    private List<Video> list=new ArrayList<>();
    private PtrClassicFrameLayout ptr_video;
    private Handler handler=new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_video,null );
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_video);
        ptr_video= (PtrClassicFrameLayout) view.findViewById(R.id.ptr_video);
        getDataToVideo(url);
        downRefresh();
        return view;
    }

    /**
     * 下拉刷新
     */
    private void downRefresh() {
        ptr_video.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return ((MainActivity) getActivity()).verticalOffset1 == 0 && PtrDefaultHandler.checkContentCanBePulledDown(
                        frame, content, header
                );
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataToVideo(url);
                        ptr_video.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 获取数据，适配和监听图片
     * @param url
     */
    public void getDataToVideo(String url){
        Tools.doGet(url, new Tools.MyCallback() {
            @Override
            public void onFail() {

            }
            @Override
            public void onSuccess(String result) {
                list.addAll(ParserJson.parserJsonToVideo(result));
                Log.i("tag", "onSuccess: " + list.size());
//                MyAdapterVideo adapterVideo=new MyAdapterVideo(list,getActivity());
//                lv_video.setAdapter(adapterVideo);
                MyRecyclerAdapter_Video adapter_video = new MyRecyclerAdapter_Video(list, getActivity());
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayout.VERTICAL);
//                GridLayoutManager manager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
//                瀑布流
//                StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                adapter_video.setLisener(new MyRecyclerAdapter_Video.ItemClickLisener() {
                    @Override
                    public void onImageClick(View v, int position) {
                        Toast.makeText(getActivity(), "图片被点击了", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(adapter_video);
            }
        });

    }
}
