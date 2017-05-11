package qf.com.shoping.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import qf.com.shoping.R;
import qf.com.shoping.activity.Detail_aActivity;
import qf.com.shoping.activity.Detail_bActivity;
import qf.com.shoping.activity.Detail_cActivity;
import qf.com.shoping.bean.HomeHotTopicBean;
import qf.com.shoping.constants.KeyConstants;

/**
 * Created by Administrator on 2017/2/9.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHoder> {

    private final List<HomeHotTopicBean.DataBean.TopicBean> been;
    LayoutInflater inflater;

    public HomeRecyclerViewAdapter(Context context, List<HomeHotTopicBean.DataBean.TopicBean> been) {
        inflater = LayoutInflater.from(context);
        this.been=been;
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_layout, parent, false);

        MyViewHoder hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, final int position) {
            holder.setText(R.id.tv,been.get(position).getTitle());
            holder.setText(R.id.name_tv,"小糖君");
            holder.setText(R.id.views_tv,been.get(position).getViews());
            holder.setText(R.id.likes_tv,been.get(position).getLikes());
            holder.setImg(R.id.iv,been.get(position).getPic());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (been.get(position).getType().equals("2")){
                    Intent intent=new Intent(v.getContext(),Detail_bActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString(KeyConstants.KEY_DETAIL_ID,been.get(position).getId());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }else if(been.get(position).getType().equals("")){
                    Intent intent=new Intent(v.getContext(),Detail_aActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString(KeyConstants.KEY_DETAIL_ID,been.get(position).getId());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }else if(been.get(position).getType().equals("5")){
                    Intent intent=new Intent(v.getContext(),Detail_cActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString(KeyConstants.KEY_DETAIL_ID,been.get(position).getId());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return been.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        View view;

        public MyViewHoder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setText(int id, String text) {
            TextView tv = (TextView) view.findViewById(id);
            tv.setText(text);
        }
        public void setImg(int id, String url) {
            ImageView iv= (ImageView) view.findViewById(id);
            Glide.with(view.getContext()).load(url).error(R.drawable.ic_dialog_version_update).into(iv);
        }
    }
}
