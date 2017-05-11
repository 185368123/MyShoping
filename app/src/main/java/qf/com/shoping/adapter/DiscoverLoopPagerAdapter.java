package qf.com.shoping.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

import qf.com.shoping.bean.DiscoverBean;

/**
 * Created by li on 2017/2/20.
 */

public class DiscoverLoopPagerAdapter extends LoopPagerAdapter {
    Context context;
    List<DiscoverBean.DataBean.BannerBean>  data;
    public DiscoverLoopPagerAdapter(RollPagerView viewPager, Context context, List<DiscoverBean.DataBean.BannerBean>  data) {
        super(viewPager);
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Log.e("asd",data.get(position).getPhoto() );
        Glide.with(context).load(data.get(position).getPhoto()).into(iv);
        return iv;
    }
    @Override
    public int getRealCount() {
        return data.size();
    }
}
