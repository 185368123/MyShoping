package qf.com.shoping.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import qf.com.shoping.R;
import qf.com.shoping.constants.TitlesConstants;
import qf.com.shoping.fragment.DiscoverFragment;
import qf.com.shoping.fragment.HomeFragment;
import qf.com.shoping.fragment.MineFragment;
import qf.com.shoping.fragment.MsgFragment;

public class MainActivity extends NoActionBarActivity {

    private FragmentTabHost tabHost;
    //底部图片选择器的id数组
    private int imgId[] = {R.drawable.tab_home_selector, R.drawable.tab_discove_selector, R.drawable.tab_message_selector, R.drawable.tab_mine_selector};
    //标签对应的Fragment的Class
    private Class fragments[] = {HomeFragment.class, DiscoverFragment.class, MsgFragment.class, MineFragment.class};
    private int view;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化TabHost布局
        initTabHost();

    }

    private void initTabHost() {
        tabHost = (FragmentTabHost) findViewById(R.id.tb);
        //指定动态加载Fragment的布局id
        tabHost.setup(this, getSupportFragmentManager(), R.id.frame_layout);
        //添加标签
        for (int i = 0; i < TitlesConstants.tabHostTitles.length; i++) {
            //创建一个新的标签，指定Tag,这个tab就是Fragment的tag
            TabHost.TabSpec spec = tabHost.newTabSpec("" + i);
            //设置标签的视图
            spec.setIndicator(getView(i));
            tabHost.addTab(spec, fragments[i], null);
            //把tab左右的分隔线设置为透明图片
            tabHost.getTabWidget().setDividerDrawable(new ColorDrawable(0x00000000));

        }
    }

    public View getView(int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(this);
        }
        View view = inflater.inflate(R.layout.tab_host_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.tab_host_iv);
        iv.setImageResource(imgId[i]);
        TextView tv = (TextView) view.findViewById(R.id.tab_host_tv);
        tv.setText(TitlesConstants.tabHostTitles[i]);
        return view;
    }
}
