package qf.com.shoping.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.activity.CatalogueActivity;
import qf.com.shoping.activity.RollPagerViewActivity;
import qf.com.shoping.adapter.HomeFragmentPageAdapter;
import qf.com.shoping.adapter.HomeLoopPagerAdapter;
import qf.com.shoping.bean.HomeRollPageBean;
import qf.com.shoping.constants.KeyConstants;
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

public class HomeFragment extends Fragment implements View.OnClickListener {
    List<String> data = new ArrayList<>();
    List<HomeRollPageBean.DataBeanX.BannerBean> banner;
    private HomeLoopPagerAdapter loopPagerAdapter;
    private HomeFragmentPageAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeFragmentPageAdapter(getChildFragmentManager());
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button search= (Button) view.findViewById(R.id.search);
        search.setOnClickListener(this);
        TabLayout tablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager vp = (ViewPager) view.findViewById(R.id.vp);
        RollPagerView rpv = (RollPagerView) view.findViewById(R.id.rpv);

        vp.setAdapter(adapter);

        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setBackgroundColor(Color.WHITE);//设置背景颜色
        tablayout.setTabTextColors(Color.BLACK, Color.RED);//设置字体选中前的颜色
        //绑定tabLayout和viewPager
        tablayout.setupWithViewPager(vp);

        loopPagerAdapter = new HomeLoopPagerAdapter(rpv, getContext(), data);
        rpv.setAdapter(loopPagerAdapter);
        rpv.setPlayDelay(2000);
        rpv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), RollPagerViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(KeyConstants.KEY_IDS, banner.get(position).getExtend());
                bundle.putString(KeyConstants.KEY_TITLE,banner.get(position).getTitle());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }


    public void getData() {

        Map<String, String> map = new HashMap<>();
        map.put("app_id", "com.jzyd.BanTang");
        map.put("app_installtime", "1487344257");
        map.put("app_versions", "5.9.7");
        map.put("channel_name", "yingyongbao");
        map.put("client_id", "bt_app_android");
        map.put("client_secret", "ffcda7a1c4ff338e05c42e7972ba7b8d");
        map.put("oauth_token", "");
        map.put("os_versions", "4.4.4");
        map.put("screensize", "1080");
        map.put("track_device_info", "MI NOTE LTE");
        map.put("track_deviceid", "867993022060477");
        map.put("track_user_id", "");
        map.put("v", "25");


        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HomeRollPageBean> call = api.getRollPageBean(map);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                HomeRollPageBean body = (HomeRollPageBean) response.body();
                banner = body.getData().getBanner();
                //Log.e("qwer", body.getData().getBanner().get(0).getPhoto());
                for (int i = 0; i < banner.size(); i++) {
                    data.add(banner.get(i).getPhoto());

                }
                loopPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("qwer", "请求错误");
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getContext(), CatalogueActivity.class);
        getContext().startActivity(intent);
    }
}
