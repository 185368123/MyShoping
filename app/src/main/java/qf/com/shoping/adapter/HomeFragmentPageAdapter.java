package qf.com.shoping.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import qf.com.shoping.constants.KeyConstants;
import qf.com.shoping.constants.TitlesConstants;
import qf.com.shoping.fragment.HomeItemFragment;

/**
 * Created by li on 2017/2/18.
 */

public class HomeFragmentPageAdapter extends FragmentStatePagerAdapter{
    public HomeFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        HomeItemFragment fragment=new HomeItemFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(KeyConstants.KEY_LIST,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return TitlesConstants.tabLayoutTitles.length;
    }
    public CharSequence getPageTitle(int position) {
        return TitlesConstants.tabLayoutTitles[position];
    }
}
