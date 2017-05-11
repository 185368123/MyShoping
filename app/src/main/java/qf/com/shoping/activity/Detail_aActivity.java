package qf.com.shoping.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.adapter.Detail_aAdapter;
import qf.com.shoping.alipay.AuthResult;
import qf.com.shoping.alipay.PayResult;
import qf.com.shoping.bean.DetailBean_a;
import qf.com.shoping.constants.KeyConstants;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_aActivity extends NoActionBarActivity{


    Map<String, String> map;
    private String id;
    private ListView lv;
    private View view;
    private TextView title_tv;
    private ImageView user_iv;
    private TextView user_tv;
    private TextView view_tv;
    private List<DetailBean_a.DataBean.ProductListBean> data=new ArrayList<>();
    private Detail_aAdapter aAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_a);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(KeyConstants.KEY_DETAIL_ID);
        map = new HashMap<>();
        initMap(id);
        initListView();
    }

    LayoutInflater inflater ;

    private void initListView() {
        inflater = LayoutInflater.from(this);
        lv = (ListView) findViewById(R.id.detail_a_list);
        view = inflater.inflate(R.layout.detail_list_head_layout,null);
        title_tv = (TextView) view.findViewById(R.id.detail_list_title_tv);
        user_iv = (ImageView) view.findViewById(R.id.detail_list_user_iv);
        user_tv = (TextView) view.findViewById(R.id.detail_list_user_tv);
        view_tv = (TextView) view.findViewById(R.id.detail_list_views_tv);
        lv.addHeaderView(view);
        aAdapter = new Detail_aAdapter(this,data);
        lv.setAdapter(aAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<DetailBean_a> call = api.getDetailBean_a(map);
        call.enqueue(new Callback<DetailBean_a>() {
            @Override
            public void onResponse(Call<DetailBean_a> call, Response<DetailBean_a> response) {
                DetailBean_a body = response.body();
                Log.e( "DetailBean_a",body.toString());
                title_tv.setText(body.getData().getTitle());
                Glide.with(Detail_aActivity.this).load(body.getData().getUser().getAvatar()).into(user_iv);
                user_tv.setText(body.getData().getUser().getNickname());
                view_tv.setText(body.getData().getViews());
                data.clear();
                data.addAll(body.getData().getProduct_list());
                aAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DetailBean_a> call, Throwable t) {
                Log.e( "DetailBean_a","错误");
            }
        });

    }

    private void getData_a(Api api, final List<DetailBean_a.DataBean.ProductListBean> data) {

        Call<DetailBean_a> call = api.getDetailBean_a(map);
        call.enqueue(new Callback<DetailBean_a>() {
            @Override
            public void onResponse(Call<DetailBean_a> call, Response<DetailBean_a> response) {
                DetailBean_a body = response.body();
                Log.e("getData_a", body.getData().getProduct_list().toString());
                data.addAll(body.getData().getProduct_list());

            }

            @Override
            public void onFailure(Call<DetailBean_a> call, Throwable t) {

            }
        });
    }

    private void initMap(String id) {
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
        map.put("id", id);
        map.put("trace_id", "");
        map.put("search_key", "");
        map.put("statistics_uv", "1");
        map.put("is_night", "0");
    }

    public void detail_back(View view) {
        finish();
    }

}
