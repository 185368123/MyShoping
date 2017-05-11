package qf.com.shoping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import qf.com.shoping.R;
import qf.com.shoping.bean.DiscoverBean;

/**
 * Created by li on 2017/2/23.
 */

public class DiscoverHotRecyclerViewAdapter extends RecyclerView.Adapter<DiscoverHotRecyclerViewAdapter.MyViewHolder> {
    private List<DiscoverBean.DataBean.ActivityListBean> data;
    private final LayoutInflater inflater;

    public DiscoverHotRecyclerViewAdapter(Context context, List<DiscoverBean.DataBean.ActivityListBean> dataSubjectList) {
        this.data = dataSubjectList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder=new MyViewHolder(inflater.inflate(R.layout.discover_hot_recycle_layout,null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
           holder.setI(R.id.discover_hot_recycle_layouyt_iv,data.get(position).getIcon());
        holder.setT(R.id.discover_hot_recycle_layouyt_title,data.get(position).getTitle());
        holder.setT(R.id.discover_hot_recycle_layouyt_user,data.get(position).getUsers()+"人参与");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setI(int id, String url) {
            ImageView iv = (ImageView) view.findViewById(id);
            Glide.with(view.getContext()).load(url).into(iv);
        }

        public void setT(int id, String url) {
            TextView tv = (TextView) view.findViewById(id);
            tv.setText(url);
        }
    }
}
