package qf.com.shoping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.HttpUtil;
import qf.com.shoping.R;
import qf.com.shoping.adapter.DiscoverLoopPagerAdapter;
import qf.com.shoping.bean.DiscoverBean;
import qf.com.shoping.bean.DiscoverNewBean;
import qf.com.shoping.bean.DiscoverRecBean;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by li on 2017/2/23.
 */

public class DiscoverItemFragment extends Fragment {
    int position;
    Map<String,String> map=new HashMap<>();
    private RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
       position=bundle.getInt("position");
        getData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_item_fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recyc_layout);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);


    }

    private void getData() {
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
        map.put("page", "0");
        map.put("pagesize", "20");

        final Gson gson=new Gson();
        switch (position){
            case 0:
                new HttpUtil().loadJson(UrlConstants.baseUrl + UrlConstants.discoverListByNew, map, new HttpUtil.OnCompletedListener() {
                    @Override
                    public void OnCompleted(String jsonString) {
                        Log.e( "OnCompleted: ",jsonString );
                        DiscoverNewBean newBean=gson.fromJson(jsonString,DiscoverNewBean.class);
                        final List<DiscoverNewBean.DataBean.ListBean> list = newBean.getData().getList();
                        rv.setAdapter(new CommonAdapter<DiscoverNewBean.DataBean.ListBean>(getContext(),R.layout.discover_view_page_layout,list) {
                            @Override
                            protected void convert(ViewHolder holder, DiscoverNewBean.DataBean.ListBean listBean, int position) {
                                if (listBean.getType_id().equals("4")){
                                Glide.with(getContext()).load(listBean.getPost().getUser().getAvatar()).into((ImageView) holder.getView(R.id.discover_view_page_user_iv));
                                holder.setText(R.id.discover_view_page_user_name,listBean.getPost().getUser().getNickname());
                                holder.setText(R.id.discover_view_page_user_time,listBean.getPost().getDatestr());
                                Glide.with(getContext()).load(listBean.getPost().getMiddle_pic_url()).into((ImageView) holder.getView(R.id.discover_view_page_iv));
                                holder.setText(R.id.discover_view_page_tv,listBean.getPost().getContent());
                                holder.setText(R.id.discover_view_page_views,listBean.getPost().getDynamic().getViews());
                                }else if (listBean.getType_id().equals("1")){
                                    Glide.with(getContext()).load(listBean.getTopic().getUser().getAvatar()).into((ImageView) holder.getView(R.id.discover_view_page_user_iv));
                                    holder.setText(R.id.discover_view_page_user_name,listBean.getTopic().getUser().getNickname());
                                    holder.setText(R.id.discover_view_page_user_time,listBean.getTopic().getDatestr());
                                    Glide.with(getContext()).load(listBean.getTopic().getPic()).into((ImageView) holder.getView(R.id.discover_view_page_iv));
                                    holder.setText(R.id.discover_view_page_tv,listBean.getTopic().getTitle());
//                                    holder.setText(R.id.discover_view_page_views,listBean.getTopic().getDynamic().getViews());
                                }
                            }
                        });
                    }

                    @Override
                    public void OnLoadError() {

                    }
                });
                break;
            case 1:
                new HttpUtil().loadJson(UrlConstants.baseUrl + UrlConstants.discoverListByRec, map, new HttpUtil.OnCompletedListener() {
                    @Override
                    public void OnCompleted(String jsonString) {
                        DiscoverRecBean recBean=gson.fromJson(jsonString,DiscoverRecBean.class);
                        List<DiscoverRecBean.DataBean.ListBean> list = recBean.getData().getList();
                        rv.setAdapter(new CommonAdapter<DiscoverRecBean.DataBean.ListBean>(getContext(),R.layout.discover_view_page_layout,list) {
                            @Override
                            protected void convert(ViewHolder holder, DiscoverRecBean.DataBean.ListBean listBean, int position) {
                                if (listBean.getType_id().equals("4")){
                                    Glide.with(getContext()).load(listBean.getPost().getUser().getAvatar()).into((ImageView) holder.getView(R.id.discover_view_page_user_iv));
                                    holder.setText(R.id.discover_view_page_user_name,listBean.getPost().getUser().getNickname());
                                    holder.setText(R.id.discover_view_page_user_time,listBean.getPost().getDatestr());
                                    Glide.with(getContext()).load(listBean.getPost().getMiddle_pic_url()).into((ImageView) holder.getView(R.id.discover_view_page_iv));
                                    holder.setText(R.id.discover_view_page_tv,listBean.getPost().getContent());
                                    holder.setText(R.id.discover_view_page_views,listBean.getPost().getDynamic().getViews());
                                }else if (listBean.getType_id().equals("1")){
                                    Glide.with(getContext()).load(listBean.getTopic().getUser().getAvatar()).into((ImageView) holder.getView(R.id.discover_view_page_user_iv));
                                    holder.setText(R.id.discover_view_page_user_name,listBean.getTopic().getUser().getNickname());
                                    holder.setText(R.id.discover_view_page_user_time,listBean.getTopic().getDatestr());
                                    Glide.with(getContext()).load(listBean.getTopic().getPic()).into((ImageView) holder.getView(R.id.discover_view_page_iv));
                                    holder.setText(R.id.discover_view_page_tv,listBean.getTopic().getTitle());
                                  //  holder.setText(R.id.discover_view_page_views,listBean.getTopic().getDynamic().getViews()+"");
                                }
                            }
                        });
                    }

                    @Override
                    public void OnLoadError() {

                    }
                });
                break;
        }
    }
}
