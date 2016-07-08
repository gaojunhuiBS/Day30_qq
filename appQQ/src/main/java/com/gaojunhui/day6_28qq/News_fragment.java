package com.gaojunhui.day6_28qq;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.gaojunhui.adapter.MyRecyclerAdapter_News;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bean.News;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import parserjson.ParserJson;
import utils.PullTorefreshByRecycler;
import utils.Tools;

/**
 * Created by Administrator on 2016/6/28.
 */
public class News_fragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<News> list=new ArrayList<>();
    private String url="http://c.3g.163.com/nc/article/list/T1348649776727/0-20.html";
    private PtrClassicFrameLayout ptr_news;
    private Handler handler=new Handler();
    private PullTorefreshByRecycler refreshListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_news,null);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_news);
        ptr_news= (PtrClassicFrameLayout) view.findViewById(R.id.ptr_news);
//        refreshListView= (PullTorefreshByRecycler) view.findViewById(R.id.refresh_lv);
        getDataTolv(url);
//        initScrollView();
//        下拉刷新
//        refresh();
        downReferesh();
        return view;
    }
    /**
     * 初始化刷新
     */
    private void initScrollView() {
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setPullLabel("下拉刷新", PullToRefreshBase.Mode.PULL_FROM_START);
        refreshListView.setRefreshingLabel("正在加载...", PullToRefreshBase.Mode.PULL_FROM_START);
        refreshListView.setReleaseLabel("放开刷新", PullToRefreshBase.Mode.PULL_FROM_START);
        refreshListView.setPullLabel("上拉加载", PullToRefreshBase.Mode.PULL_FROM_END);
        refreshListView.setRefreshingLabel("正在加载...", PullToRefreshBase.Mode.PULL_FROM_END);
        refreshListView.setReleaseLabel("放开加载", PullToRefreshBase.Mode.PULL_FROM_END);

    }
    /**
     * 上下拉刷新
     */
    private void refresh(){
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                getDataTolv(url);
                refreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                getDataTolv(url);
                refreshListView.onRefreshComplete();
            }
        });
    }
    /**
     * 下拉刷新
     *
     * */
    private void downReferesh() {
        ptr_news.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return (( MainActivity) getActivity()).verticalOffset1==0 && PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataTolv(url);
                        ptr_news.refreshComplete();
                    }
                }, 2000);
            }
        });
    }


    /**
     * 获得网络数据并适配
     */
    public void getDataTolv(String url){
        Tools.doGet(url, new Tools.MyCallback() {
            @Override
            public void onFail() {
            }

            @Override
            public void onSuccess(String result) {
                list.addAll(ParserJson.parserJsonToNews(result));
                MyRecyclerAdapter_News adapter_news=new MyRecyclerAdapter_News(list,getActivity());
                LinearLayoutManager manager=new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayout.VERTICAL);
                recyclerView.setLayoutManager(manager);
                adapter_news.setListener(new MyRecyclerAdapter_News.ItemOnClickListener() {
                    @Override
                    public void onImageClick(View v, int position) {
                        Toast.makeText(getActivity() ,"图片被点击", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(adapter_news);
            }
        });
    }
}
