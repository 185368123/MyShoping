package qf.com.shoping.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.logging.Handler;

import qf.com.shoping.R;
import qf.com.shoping.activity.ALiPlayActivity;
import qf.com.shoping.activity.Detail_aActivity;
import qf.com.shoping.activity.TaoBaoActivity;
import qf.com.shoping.alipay.AliPlayUtil;
import qf.com.shoping.alipay.OrderInfoUtil2_0;
import qf.com.shoping.alipay.PayBean;
import qf.com.shoping.bean.DetailBean_a;

/**
 * Created by li on 2017/2/22.
 */

public class Detail_aAdapter extends BaseAdapter implements View.OnClickListener {

    private List<DetailBean_a.DataBean.ProductListBean> data;
    private  Context context;
    private final LayoutInflater inflater;
    private int myPosition;


    public Detail_aAdapter(Detail_aActivity context, List<DetailBean_a.DataBean.ProductListBean> data) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.data=data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        myPosition = position;
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.detail_a_list_item_layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.button_buy= (Button) convertView.findViewById(R.id.button_buy);
            viewHolder.detail_a_image= (ImageView) convertView.findViewById(R.id.detail_a_image);
            viewHolder.detail_a_likes= (TextView) convertView.findViewById(R.id.detail_a_likes_tv);
            viewHolder.detail_a_text= (TextView) convertView.findViewById(R.id.detail_a_text);
            viewHolder.detail_a_title= (TextView) convertView.findViewById(R.id.detail_a_title);
            viewHolder.detail_a_type5_iv= (ImageView) convertView.findViewById(R.id.detail_a_type5_iv);
            viewHolder.detail_a_type5_name= (TextView) convertView.findViewById(R.id.detail_a_type_name);
            viewHolder.detail_a_type5_price= (TextView) convertView.findViewById(R.id.detail_a_type5_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(data.get(position).getPic()).into(viewHolder.detail_a_image);
        viewHolder.detail_a_likes.setText(data.get(position).getLikes());
        viewHolder.detail_a_text.setText(data.get(position).getDesc());
        viewHolder.detail_a_title.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getPic()).into(viewHolder.detail_a_type5_iv);
        viewHolder.detail_a_type5_name.setText(data.get(position).getTitle());
        viewHolder.detail_a_type5_price.setText(data.get(position).getPrice());
        viewHolder.button_buy.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
       // Intent intent=new Intent(context, ALiPlayActivity.class);
        Intent intent=new Intent(context, TaoBaoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("money",data.get(myPosition).getPic());
        bundle.putString("reason",data.get(myPosition).getTitle());
        bundle.putString("url",data.get(myPosition).getUrl());
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    class ViewHolder{
        TextView  detail_a_title;
        TextView detail_a_text;
        ImageView detail_a_image;
        ImageView detail_a_type5_iv;
        TextView detail_a_type5_name;
        TextView detail_a_type5_price;
        TextView detail_a_likes;
        Button button_buy;
    }
}
