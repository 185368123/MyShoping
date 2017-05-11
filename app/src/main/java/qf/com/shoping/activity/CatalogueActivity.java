package qf.com.shoping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qf.com.shoping.R;
import qf.com.shoping.bean.CatalogueBean;
import qf.com.shoping.constants.UrlConstants;
import qf.com.shoping.interfaces.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatalogueActivity extends NoActionBarActivity implements AdapterView.OnItemClickListener {
    List<CatalogueBean.DataBean> data=new ArrayList<>();
    List<String> list=new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private ListView lv;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        lv = (ListView) findViewById(R.id.activity_catalogue_lv);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(this);



        rv = (RecyclerView) findViewById(R.id.activity_catalogue_rv);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);

        getData();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if(lv != null){
            lv.requestFocus();
            lv.setItemChecked(0, true);
            lv.smoothScrollToPosition(0);
            lv.setSelection(0);
        }
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
        map.put("is_new", "1");
        map.put("is_rec_cate", "1");


        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<CatalogueBean> call=api.getList(map);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
             CatalogueBean bean= (CatalogueBean) response.body();
                data.clear();
                data.addAll(bean.getData());
                //Log.e("qwer",data.get(1).getName());
                for (CatalogueBean.DataBean detail:data
                     ) {
                    list.add(detail.getName());
                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("qwer", "请求错误");
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //RecycleView设置适配器
        rv.setAdapter(new CommonAdapter<CatalogueBean.DataBean.SubclassBean>(this,R.layout.catalogue_recycle_layout,data.get(position).getSubclass()) {
            @Override
            protected void convert(ViewHolder holder, CatalogueBean.DataBean.SubclassBean s, int position) {
               holder.setText(R.id.text1,s.getName());
                Glide.with(CatalogueActivity.this).load(s.getIcon()).into((ImageView) holder.getView(R.id.icon));
            }
        });
    }
}
