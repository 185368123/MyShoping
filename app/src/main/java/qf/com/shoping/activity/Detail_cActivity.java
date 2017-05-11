package qf.com.shoping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.HttpUtil;
import qf.com.shoping.R;
import qf.com.shoping.adapter.Detail_aAdapter;
import qf.com.shoping.adapter.Detail_cAdapter;
import qf.com.shoping.bean.DetailBean_a;
import qf.com.shoping.bean.DetailBean_c;
import qf.com.shoping.constants.KeyConstants;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.CustomConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_cActivity extends NoActionBarActivity {
    Map<String, String> map;
    private String id;
    private ListView lv;
    private View view;
    private TextView title_tv;
    private ImageView user_iv;
    private TextView user_tv;
    private TextView view_tv;
    private List<DetailBean_c> data = new ArrayList<>();
    private Detail_cAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_c);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(KeyConstants.KEY_DETAIL_ID);
        map = new HashMap<>();
        initMap(id);
       initListView();
    }

    LayoutInflater inflater;

    private void initListView() {
        inflater = LayoutInflater.from(this);
        lv = (ListView) findViewById(R.id.detail_c_list);
        view = inflater.inflate(R.layout.detail_list_head_layout, null);
        title_tv = (TextView) view.findViewById(R.id.detail_list_title_tv);
        user_iv = (ImageView) view.findViewById(R.id.detail_list_user_iv);
        user_tv = (TextView) view.findViewById(R.id.detail_list_user_tv);
        view_tv = (TextView) view.findViewById(R.id.detail_list_views_tv);
        lv.addHeaderView(view);
        adapter = new Detail_cAdapter(this, data);
        lv.setAdapter(adapter);
        new HttpUtil().loadJson(UrlConstants.baseUrl + UrlConstants.Type5Url, map, new HttpUtil.OnCompletedListener() {
            @Override
            public void OnCompleted(String jsonString) {
                Log.e("jsonString",jsonString);
                try {
                    JSONObject jObject=new JSONObject(jsonString);
                    JSONObject jObjectData=jObject.getJSONObject("data");
                    String title1=jObjectData.getString("title");
                    String desc=jObjectData.getString("desc");
                    JSONObject jObjectUser=jObjectData.getJSONObject("user");
                    String nickname1=jObjectUser.getString("nickname");
                    String avatar1=jObjectUser.getString("avatar");
                    title_tv.setText(title1);
                    Glide.with(Detail_cActivity.this).load(avatar1).into(user_iv);
                    user_tv.setText(nickname1);
                    view_tv.setText(desc);
                    JSONArray jArray=jObjectData.getJSONArray("post_list");
                    for (int i = 0; i < jArray.length(); i++) {
                          JSONObject object=jArray.getJSONObject(i);
                        JSONObject objectUser=object.getJSONObject("user");
                        JSONObject objectBrand_product=object.getJSONArray("brand_product").getJSONObject(0);
                        String title=objectBrand_product.getString("title");
                        String nickname=objectUser.getString("nickname");
                        String avatar=objectUser.getString("avatar");
                        String pics=object.getString("middle_pic_url");
                        String likes=objectBrand_product.getString("likes");
                        String content=object.getString("content");
                        String pic=objectBrand_product.getString("pic");
                        String price=objectBrand_product.getString("price");
                        DetailBean_c detailBean_c=new DetailBean_c(avatar,content,likes,nickname,pic,pics,price,title);
                        data.add(detailBean_c);
                        Log.e("detailBean_c",detailBean_c.toString() );
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnLoadError() {
                Log.e("jsonString","解析错误");
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
