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

public class DiscoverRecyclerViewAdapter extends RecyclerView.Adapter<DiscoverRecyclerViewAdapter.MyViewHoder> {


    private List<DiscoverBean.DataBean.SubjectListBean> data;
    private final LayoutInflater inflater;

    public DiscoverRecyclerViewAdapter(Context context, List<DiscoverBean.DataBean.SubjectListBean> dataActivityList) {
        this.data = dataActivityList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHoder viewHoder=new MyViewHoder(inflater.inflate(R.layout.discover_recycle_layout,null));
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.setI(R.id.discover_recycle_layouyt_iv,data.get(position).getPhoto());
        holder.setT(R.id.discover_recycle_layouyt_tv,data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        View view;
        public MyViewHoder(View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setI(int id,String url){
            ImageView iv= (ImageView) view.findViewById(id);
            Glide.with(view.getContext()).load(url).into(iv);
        }
        public void setT(int id,String url){
            TextView tv= (TextView) view.findViewById(id);
            tv.setText(url);
        }
    }
}
