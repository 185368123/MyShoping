package qf.com.shoping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import qf.com.shoping.R;
import qf.com.shoping.bean.DetailBean_a;
import qf.com.shoping.bean.DetailBean_c;

/**
 * Created by li on 2017/2/22.
 */

public class Detail_cAdapter extends BaseAdapter {

    private List<DetailBean_c> data;
    private  Context context;
    private final LayoutInflater inflater;

    public Detail_cAdapter(Context context, List<DetailBean_c> data) {
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
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.detail_c_list_item_layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.detail_a_image= (ImageView) convertView.findViewById(R.id.detail_a_image);
            viewHolder.detail_a_likes= (TextView) convertView.findViewById(R.id.detail_a_likes_tv);
            viewHolder.detail_a_text= (TextView) convertView.findViewById(R.id.detail_a_text);
            viewHolder.detail_a_type5_iv= (ImageView) convertView.findViewById(R.id.detail_a_type5_iv);
            viewHolder.detail_a_type5_name= (TextView) convertView.findViewById(R.id.detail_a_type_name);
            viewHolder.detail_a_type5_price= (TextView) convertView.findViewById(R.id.detail_a_type5_price);
            viewHolder.detaile_c_user_name= (TextView) convertView.findViewById(R.id.detaile_c_user_name);
            viewHolder.detaile_c_user_pic= (ImageView) convertView.findViewById(R.id.detaile_c_user_pic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.detaile_c_user_name.setText(data.get(position).getNickname());
        Glide.with(context).load(data.get(position).getAvatar()).into(viewHolder.detaile_c_user_pic);
        Glide.with(context).load(data.get(position).getPics()).into(viewHolder.detail_a_image);
        viewHolder.detail_a_likes.setText(data.get(position).getLikes());
        viewHolder.detail_a_text.setText(data.get(position).getContent());
        Glide.with(context).load(data.get(position).getPic()).into(viewHolder.detail_a_type5_iv);
        viewHolder.detail_a_type5_name.setText(data.get(position).getTitle());
        viewHolder.detail_a_type5_price.setText(data.get(position).getPrice());
        return convertView;
    }
    class ViewHolder{
        ImageView detaile_c_user_pic;
        TextView detaile_c_user_name;
        TextView detail_a_text;
        ImageView detail_a_image;
        ImageView detail_a_type5_iv;
        TextView detail_a_type5_name;
        TextView detail_a_type5_price;
        TextView detail_a_likes;
    }
}
