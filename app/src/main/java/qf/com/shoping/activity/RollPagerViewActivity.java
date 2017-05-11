package qf.com.shoping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.bean.HomeRollPageDetailBean;
import qf.com.shoping.constants.KeyConstants;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RollPagerViewActivity extends NoActionBarActivity {
    Map<String, String> map;
    List<HomeRollPageDetailBean.DataBean.TopicBean> data = new ArrayList<>();
    private RecyclerView rv;
    Toolbar bar;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_pager_view);
        Bundle bundle = getIntent().getExtras();
        String extra = bundle.getString(KeyConstants.KEY_IDS);
        Log.e("extra",extra );
        String title = bundle.getString(KeyConstants.KEY_TITLE);
        initMap(extra);
        initRecyclerView();
        initToolBar(title);
    }

    public void back(View view) {
           finish();
    }

    private void initToolBar(String title) {
        bar = (Toolbar) findViewById(R.id.bar);
        tv = (TextView) bar.findViewById(R.id.bar_tv);
        tv.setText(title);
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById(R.id.rollpage_detail_rv);
        LinearLayoutManager llm = new LinearLayoutManager(RollPagerViewActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HomeRollPageDetailBean> call = api.getRollPageDetailBean(map);
        call.enqueue(new Callback<HomeRollPageDetailBean>() {
            @Override
            public void onResponse(Call<HomeRollPageDetailBean> call, Response<HomeRollPageDetailBean> response) {
                HomeRollPageDetailBean body = response.body();
                data.addAll(body.getData().getTopic());
                rv.setAdapter(new CommonAdapter<HomeRollPageDetailBean.DataBean.TopicBean>(RollPagerViewActivity.this, R.layout.roll_page_recycler_item_layout, data) {
                    @Override
                    protected void convert(ViewHolder holder, final HomeRollPageDetailBean.DataBean.TopicBean topicBean, int position) {
                        Glide.with(RollPagerViewActivity.this).load(topicBean.getPic()).into((ImageView) holder.getView(R.id.recyc_iv));
                        holder.setText(R.id.title_tv, topicBean.getTitle());
                        holder.setText(R.id.views_text, topicBean.getViews());
                        holder.setText(R.id.likes_text, topicBean.getLikes());
                        Glide.with(RollPagerViewActivity.this).load(topicBean.getUser().getAvatar()).into((ImageView) holder.getView(R.id.user_im));
                        holder.setText(R.id.user_name_text, topicBean.getUser().getNickname());
                        holder.setText(R.id.user_time_text, topicBean.getCreate_time_str());
                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (topicBean.getType().equals("2")){
                                    Intent intent=new Intent(v.getContext(),Detail_bActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString(KeyConstants.KEY_DETAIL_ID,topicBean.getId());
                                    intent.putExtras(bundle);
                                    v.getContext().startActivity(intent);
                                }else if (topicBean.getType().equals("")){
                                    Intent intent=new Intent(v.getContext(),Detail_aActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString(KeyConstants.KEY_DETAIL_ID,topicBean.getId());
                                    intent.putExtras(bundle);
                                    v.getContext().startActivity(intent);
                                }else if(topicBean.getType().equals("5")){
                                    Intent intent=new Intent(v.getContext(),Detail_cActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString(KeyConstants.KEY_DETAIL_ID,topicBean.getId());
                                    intent.putExtras(bundle);
                                    v.getContext().startActivity(intent);
                                }

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<HomeRollPageDetailBean> call, Throwable t) {
                Log.e("qwer", "访问错误");
            }
        });
    }

    private void initMap(String s) {
        map = new HashMap<>();
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
        map.put("ids", s);
    }
}
