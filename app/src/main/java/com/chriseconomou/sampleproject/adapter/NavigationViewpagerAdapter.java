package com.chriseconomou.sampleproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.fragment.CategoriesListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christosoikonomou on 07/02/2015.
 */
public class NavigationViewpagerAdapter extends FragmentPagerAdapter  {

    private List<String> mCategoryTypes = new ArrayList<String>();

    public NavigationViewpagerAdapter(FragmentManager fm, List<String> categoryTypes) {
        super(fm);
        mCategoryTypes = categoryTypes;
    }

    @Override
    public Fragment getItem(int position) {
        return CategoriesListFragment.newInstance(mCategoryTypes.get(position));
    }

    @Override
    public int getCount() {
        return mCategoryTypes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  mCategoryTypes.get(position);
    }
}
