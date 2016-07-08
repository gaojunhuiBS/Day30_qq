package com.gaojunhui.day6_28qq;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.gaojunhui.adapter.MyAdapterFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private SlidingPaneLayout slidingPaneLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp_main;
    private List<Fragment> fragments=new ArrayList<>();
    private Fragment news_f,video_f;
    private AppBarLayout appBar;
    public int verticalOffset1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appBar= (AppBarLayout) findViewById(R.id.appBar_main);
//        初始化控件
        initViews();
//        创建Fragment
        initFragments();
        MyAdapterFragment adapterFragment=new MyAdapterFragment(this.getSupportFragmentManager(),fragments);
        vp_main.setAdapter(adapterFragment);
        tabLayout.setupWithViewPager(vp_main);
//        默认显示的Fragment
        vp_main.setCurrentItem(0);
        //navgation监听
        navgetionLisener();
    }

    public void appBarLisener() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset1 = verticalOffset;
            }
        });
    }

    private void initViews() {
        slidingPaneLayout= (SlidingPaneLayout) findViewById(R.id.slidingLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        toolbar= (Toolbar) findViewById(R.id.toolbar_main);
        vp_main= (ViewPager) findViewById(R.id.vp_main);
        tabLayout= (TabLayout) findViewById(R.id.tableLayout_main);
        setSupportActionBar(toolbar);
        appBarLisener();
    }
    public void initFragments(){
        news_f=new News_fragment();
        video_f=new Video_fragment();

        fragments.add(news_f);
        fragments.add(video_f);
    }
    private void navgetionLisener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
//                transaction=manager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.navigation_movie:
                        vp_main.setCurrentItem(1,false);
                        slidingPaneLayout.closePane();
                        break;
                    case R.id.navigation_news:
                        vp_main.setCurrentItem(0);
                            slidingPaneLayout.closePane();
                        break;
                }
                return true;
            }
        });
    }
    /**
     * 默认显示
     */
//    public void initFragment(){
//        manager=getSupportFragmentManager();
//        transaction=manager.beginTransaction();
//        transaction.add(R.id.vp_main,new News_fragment());
//        transaction.commit();
////        vp_main.setCurrentItem(0);
//
//    }
}
