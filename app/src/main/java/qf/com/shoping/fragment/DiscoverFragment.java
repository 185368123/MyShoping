package qf.com.shoping.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.adapter.DiscoverFragmentPageAdapter;
import qf.com.shoping.adapter.DiscoverHotRecyclerViewAdapter;
import qf.com.shoping.adapter.DiscoverLoopPagerAdapter;
import qf.com.shoping.adapter.DiscoverRecyclerViewAdapter;
import qf.com.shoping.bean.DiscoverBean;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by li on 2017/2/18.
 */

public class DiscoverFragment extends Fragment {
    private RollPagerView rpv;
    private RecyclerView discoverRecycleLayouyt;
    private RecyclerView discoverHotRecycleLayouyt;
    private TabLayout tabLayout;
    private ViewPager vp;
    private List<DiscoverBean.DataBean.BannerBean> dataBanner=new ArrayList<>();
    private List<DiscoverBean.DataBean.ActivityListBean> dataActivityList=new ArrayList<>();
    private List<DiscoverBean.DataBean.SubjectListBean> dataSubjectList=new ArrayList<>();
    private DiscoverLoopPagerAdapter loopPagerAdapter;
    Map<String,String> map=new HashMap<>();
    private DiscoverRecyclerViewAdapter recyclerViewAdapter;
    private DiscoverHotRecyclerViewAdapter hotRecyclerViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.discover_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rpv = (RollPagerView) view.findViewById(R.id.rpv);
        discoverRecycleLayouyt = (RecyclerView) view.findViewById(R.id.discover_recycle_layouyt);
        discoverHotRecycleLayouyt = (RecyclerView) view.findViewById(R.id.discover_hot_recycle_layouyt);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        vp = (ViewPager) view.findViewById(R.id.vp);
        //设置广告轮播器
        initRollPagerView();
        //设置Recycle
        initRecycleLayouyt();
        //设置HotRecycle
        initHotRecycleLayouyt();
        //设置TabLayout和ViewPager
        initView();
    }

    private void initView() {
        DiscoverFragmentPageAdapter pageAdapter=new DiscoverFragmentPageAdapter(getChildFragmentManager());
        vp.setAdapter(pageAdapter);


        tabLayout.setBackgroundColor(Color.WHITE);//设置背景颜色
        tabLayout.setTabTextColors(Color.BLACK, Color.RED);//设置字体选中前的颜色
        //绑定tabLayout和viewPager
        tabLayout.setupWithViewPager(vp);


    }

    private void initHotRecycleLayouyt() {
        hotRecyclerViewAdapter = new DiscoverHotRecyclerViewAdapter(getContext(),dataActivityList);
        StaggeredGridLayoutManager glm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        discoverHotRecycleLayouyt.setLayoutManager(glm);
        discoverHotRecycleLayouyt.setAdapter(hotRecyclerViewAdapter);

    }

    private void initRecycleLayouyt() {
        recyclerViewAdapter = new DiscoverRecyclerViewAdapter(getContext(),dataSubjectList);
        //创建布局管理器
         StaggeredGridLayoutManager glm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        //解决item错位问题
        //glm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        discoverRecycleLayouyt.setLayoutManager(glm);
        discoverRecycleLayouyt.setAdapter(recyclerViewAdapter);
    }

    private void initRollPagerView() {
        loopPagerAdapter = new DiscoverLoopPagerAdapter(rpv,getContext(),dataBanner);
        rpv.setPlayDelay(2000);
        rpv.setAdapter(loopPagerAdapter);
    }

    public void getData() {
        map.put("app_id","com.jzyd.BanTang");
        map.put("app_installtime","1487344257");
        map.put("app_versions","5.9.7");
        map.put("channel_name","yingyongbao");
        map.put("client_id","bt_app_android");
        map.put("client_secret","ffcda7a1c4ff338e05c42e7972ba7b8d");
        map.put("oauth_token","");
        map.put("os_versions","4.4.4");
        map.put("screensize","1080");
        map.put("track_device_info","MI NOTE LTE");
        map.put("track_deviceid","867993022060477");
        map.put("track_user_id","");
        map.put("v","25");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service=retrofit.create(Api.class);
        Call<DiscoverBean> call=service.getDiscoverBean(map);
        call.enqueue(new Callback<DiscoverBean>() {
            @Override
            public void onResponse(Call<DiscoverBean> call, Response<DiscoverBean> response) {
                DiscoverBean body = response.body();
                dataBanner.clear();
                dataActivityList.clear();
                dataSubjectList.clear();
                dataBanner.addAll(body.getData().getBanner());
                dataActivityList.addAll(body.getData().getActivity_list());
                dataSubjectList.addAll(body.getData().getSubject_list());
                loopPagerAdapter.notifyDataSetChanged();
                recyclerViewAdapter.notifyDataSetChanged();
                hotRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DiscoverBean> call, Throwable t) {

            }
        });
    }
}
