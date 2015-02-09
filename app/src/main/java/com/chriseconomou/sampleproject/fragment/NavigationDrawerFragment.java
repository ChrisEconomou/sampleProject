package com.chriseconomou.sampleproject.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ListView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.adapter.NavigationViewpagerAdapter;
import com.chriseconomou.sampleproject.view.NonSwipeViewPager;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class NavigationDrawerFragment extends BaseFragment {


    @InjectView(R.id.navigation_viewpager)
    NonSwipeViewPager mNavigationViewpager;

    @InjectView(R.id.titles)
    TabPageIndicator mTitles;

    private NavigationViewpagerAdapter mAdapter;
    private List<String> mCategories = new ArrayList<String>();

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";



    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mCategories.add("MEN");
        mCategories.add("WOMEN");

        mAdapter = new NavigationViewpagerAdapter(getFragmentManager(), mCategories);
        mNavigationViewpager.setAdapter(mAdapter);
        mTitles.setViewPager(mNavigationViewpager);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;




        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

}
