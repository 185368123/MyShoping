package qf.com.shoping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.adapter.HomeRecyclerViewAdapter;
import qf.com.shoping.bean.HomeHotTopicBean;
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

public class HomeItemFragment extends Fragment {

    private int position;

    Map<String,String> map=new HashMap<>();
    private List<HomeHotTopicBean.DataBean.TopicBean> data=new ArrayList<>();
    private HomeRecyclerViewAdapter adapter;
    private RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeRecyclerViewAdapter(getContext(),data);
        Bundle bundle=getArguments();
        position = bundle.getInt(KeyConstants.KEY_LIST);
       getD(position);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_item_fragment_layout,container,false);
        return view;
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.recyc_layout);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    private void getD(int position) {
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
        map.put("page","0");
        map.put("pagesize","20");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service=retrofit.create(Api.class);

        switch (position){
            case 0:
                map.put("app_open_count","25");
                map.put("update_time","0");
                map.put("last_get_time","1487640591");
                Call<HomeHotTopicBean> callRecommendTopic=service.getHomeRecommendTopic(map);
                getData(callRecommendTopic);
                break;
            case 1:
                Call<HomeHotTopicBean> callNewestTopic=service.getHomeNewestTopic(map);
                getData(callNewestTopic);
                break;
            case 2:
                Call<HomeHotTopicBean> callHotTopic=service.getHomeHotTopic(map);
                getData(callHotTopic);
                break;
            case 3:
                map.put("ids","21");
                Call<HomeHotTopicBean> callGiftTopic=service.getHomeGiftTopic(map);
                getData(callGiftTopic);
                break;
            case 4:
                map.put("ids","11");
                Call<HomeHotTopicBean> callFoodTopic=service.getHomeGiftTopic(map);
                getData(callFoodTopic);
                break;
            case 5:
                map.put("ids","3");
                Call<HomeHotTopicBean> callLifeTopic=service.getHomeGiftTopic(map);
                getData(callLifeTopic);
                break;
            case 6:
                map.put("ids","20");
                Call<HomeHotTopicBean> callDesignTopic=service.getHomeGiftTopic(map);
                getData(callDesignTopic);
                break;
            case 7:
                map.put("ids","13");
                Call<HomeHotTopicBean> callHomeTopic=service.getHomeGiftTopic(map);
                getData(callHomeTopic);
                break;
            case 8:
                map.put("ids","2");
                Call<HomeHotTopicBean> callDigitalTopic=service.getHomeGiftTopic(map);
                getData(callDigitalTopic);
                break;
            case 9:
                map.put("ids","12");
                Call<HomeHotTopicBean> callReadTopic=service.getHomeGiftTopic(map);
                getData(callReadTopic);
                break;
            case 10:
                map.put("ids","23");
                Call<HomeHotTopicBean> callStudentTopic=service.getHomeGiftTopic(map);
                getData(callStudentTopic);
                break;
            case 11:
                map.put("ids","9");
                Call<HomeHotTopicBean> callWorkTopic=service.getHomeGiftTopic(map);
                getData(callWorkTopic);
                break;
            case 12:
                map.put("ids","14");
                Call<HomeHotTopicBean> callBeautyTopic=service.getHomeGiftTopic(map);
                getData(callBeautyTopic);
                break;
            case 13:
                map.put("ids","1");
                Call<HomeHotTopicBean> callNursingTopic=service.getHomeGiftTopic(map);
                getData(callNursingTopic);
                break;
            case 14:
                map.put("ids","26");
                Call<HomeHotTopicBean> callSportTopic=service.getHomeGiftTopic(map);
                getData(callSportTopic);
                break;
        }
    }
    private void getData(Call<HomeHotTopicBean> callHotTopic) {
        callHotTopic.enqueue(new Callback<HomeHotTopicBean>() {
            @Override
            public void onResponse(Call<HomeHotTopicBean> call, Response<HomeHotTopicBean> response) {
                HomeHotTopicBean body = response.body();
                data.addAll(body.getData().getTopic()) ;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeHotTopicBean> call, Throwable t) {

            }
        });
    }
}
