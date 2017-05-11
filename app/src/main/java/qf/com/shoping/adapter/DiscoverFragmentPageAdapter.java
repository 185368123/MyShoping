package qf.com.shoping.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import qf.com.shoping.constants.TitlesConstants;
import qf.com.shoping.fragment.DiscoverItemFragment;

/**
 * Created by li on 2017/2/23.
 */

public class DiscoverFragmentPageAdapter extends FragmentStatePagerAdapter {
    public DiscoverFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DiscoverItemFragment fragment=new DiscoverItemFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return TitlesConstants.tabTitle.length;
    }
    public CharSequence getPageTitle(int position) {
        return TitlesConstants.tabTitle[position];
    }
}
