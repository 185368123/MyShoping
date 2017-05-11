package qf.com.shoping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;

import qf.com.shoping.constants.UrlConstants;

/**
 * Created by li on 2017/2/20.
 */

public class HomeLoopPagerAdapter extends LoopPagerAdapter {
    Context context;
    List<String>  data;
    public HomeLoopPagerAdapter(RollPagerView viewPager, Context context, List<String>  data) {
        super(viewPager);
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(data.get(position)).into(iv);
        return iv;
    }

    @Override
    public int getRealCount() {
        return data.size();
    }
}
