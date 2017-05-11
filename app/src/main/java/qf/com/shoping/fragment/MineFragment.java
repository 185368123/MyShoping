package qf.com.shoping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import qf.com.shoping.activity.LoginActivity;

/**
 * Created by li on 2017/2/18.
 */

public class MineFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent=new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        TextView tv=new TextView(getContext());
        tv.setText("我的");
        return tv;
    }
}
