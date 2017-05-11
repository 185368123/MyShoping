package qf.com.shoping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import qf.com.shoping.R;
import qf.com.shoping.bean.DetailBean_b;

/**
 * Created by li on 2017/2/22.
 */

public class DetailListAdapter extends BaseAdapter {
    private Context context;
    private List<DetailBean_b.DataBean.ContentListBean> data = new ArrayList<>();
    private LayoutInflater inflater;

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("Type", "" + data.get(position).getType());
       if (data.get(position).getType()==5){
           return 0;
       }else
        return data.get(position).getType();
    }

    public DetailListAdapter(Context context, List<DetailBean_b.DataBean.ContentListBean> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;

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
        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;
        ViewHolder5 viewHolder5 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case 1:
                    convertView = inflater.inflate(R.layout.detail_list_item1_layout, parent, false);
                    viewHolder1 = new ViewHolder1();
                    viewHolder1.type1_iv= (ImageView) convertView.findViewById(R.id.type1_iv);
                    viewHolder1.type1_tv= (TextView) convertView.findViewById(R.id.type1_tv);
                    convertView.setTag(viewHolder1);
                    break;
                case 2:
                    convertView = inflater.inflate(R.layout.detail_list_item2_layout, parent, false);
                    viewHolder2=new ViewHolder2();
                    viewHolder2.type2_tv= (TextView) convertView.findViewById(R.id.type2_tv);
                    convertView.setTag(viewHolder2);
                    break;
                case 0:
                    convertView = inflater.inflate(R.layout.detail_list_item5_layout, parent, false);
                    viewHolder5=new ViewHolder5();
                    viewHolder5.type5_iv= (ImageView) convertView.findViewById(R.id.type5_iv);
                    viewHolder5.type5_name= (TextView) convertView.findViewById(R.id.type_name);
                    viewHolder5.type5_price= (TextView) convertView.findViewById(R.id.type5_price);
                    viewHolder5.likes= (TextView) convertView.findViewById(R.id.likes_tv);
                    convertView.setTag(viewHolder5);
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    viewHolder1= (ViewHolder1) convertView.getTag();
                    break;
                case 2:
                    viewHolder2= (ViewHolder2) convertView.getTag();
                    break;
                case 0:
                    viewHolder5= (ViewHolder5) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case 1:
                viewHolder1.type1_tv.setText(data.get(position).getImage_desc());
                Glide.with(context).load(data.get(position).getImage_url()).placeholder(R.drawable.ic_page_tip_data_error).into(viewHolder1.type1_iv);
                break;
            case 2:
                viewHolder2.type2_tv.setText(data.get(position).getText_content());
                break;
            case 0:
                Glide.with(context).load(data.get(position).getPic()).into(viewHolder5.type5_iv);
                viewHolder5.type5_name.setText(data.get(position).getItem_subtitle() + "  " + data.get(position).getItem_name());
                viewHolder5.type5_price.setText(data.get(position).getPrice());
                viewHolder5.likes.setText(data.get(position).getLikes());
                break;
        }
        return convertView;
    }

    class ViewHolder1{
        TextView type1_tv;
        ImageView type1_iv;
    }
    class ViewHolder2{
        TextView type2_tv;
    }
    class ViewHolder5{
        ImageView type5_iv;
        TextView type5_name;
        TextView type5_price;
        TextView likes;
    }
}
