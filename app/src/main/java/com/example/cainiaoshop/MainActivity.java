package com.example.cainiaoshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.example.cainiaoshop.fragment.*;
import com.example.cainiaoshop.bean.Tab;
import com.example.cainiaoshop.widget.FragmentTabHost;
import com.example.cainiaoshop.widget.GlideImageLoader;
import com.youth.banner.*;
import com.youth.banner.listener.OnBannerListener;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(5);

    Banner banner;//banner组件
    List mlist;//图片资源
    List<String> mlist1;//轮播标题
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar !=null)
        {
            actionBar.hide();
        }

        mlist = new ArrayList<>();
        mlist.add("http://pic0.iqiyipic.com/common/lego/20190504/5c7c889174894cd7aed96218320e1945.jpg");
        mlist.add("http://pic3.iqiyipic.com/common/lego/20190504/902898f2117c41ccaea5fa36eb4d0545.jpg");
        mlist.add("http://pic3.iqiyipic.com/common/lego/20190504/8245013abf2b44ce8736d7435d4567dc.jpg");
        mlist.add("http://pic2.iqiyipic.com/common/lego/20190501/9cdcc1a900a34c1497aeff9c5af610f2.jpg");
        mlist1 = new ArrayList<>();
        mlist1.add("这是一个美好的早晨");
        mlist1.add("但我们并不美好");
        mlist1.add("因为我是学人工智能的");
        mlist1.add("已经被学金融的虐得头破血流");

        banner = findViewById(R.id.banner);

        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(mlist);//设置图片源
        banner.setBannerTitles(mlist1);//设置标题源
        banner.setDelayTime(2000);//设置轮播事件，单位毫秒
        banner.setBannerAnimation(Transformer.ZoomOutSlide);//设置轮播动画，动画种类很多，有兴趣的去试试吧，我在这里用的是默认
//stack

/**
 *  轮播图的点击事件
 */
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "这是第" + position +"个效果", Toast.LENGTH_SHORT).show();
            }
        });
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置

        banner.start();//开始轮播，一定要调用此方法。



    }



    private void initTab() {


        Tab tab_home = new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class,R.string.category,R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);


        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(R.id.tabhost);
        mTabhost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab : mTabs){

            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));

            mTabhost.addTab(tabSpec,tab.getFragment(),null);

        }

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);


    }


    private  View buildIndicator(Tab tab){


        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(getString(tab.getTitle()));


        return  view;
    }



}