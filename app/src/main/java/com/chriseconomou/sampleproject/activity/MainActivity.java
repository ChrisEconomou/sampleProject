package com.chriseconomou.sampleproject.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.event.CategoryEvent;
import com.chriseconomou.sampleproject.fragment.NavigationDrawerFragment;
import com.chriseconomou.sampleproject.fragment.ProductsFragment;
import com.chriseconomou.sampleproject.util.Utils;

import de.greenrobot.event.EventBus;


public class MainActivity extends BaseToolbarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews(savedInstanceState);

    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        Utils.replaceFragment(this, R.id.container, ProductsFragment.newInstance(), ProductsFragment.TAG, false);
    }


    @Override
    public void onNavigationDrawerItemSelected(String categoryId) {
        EventBus.getDefault().post(new CategoryEvent(categoryId));
        if (mNavigationDrawerFragment != null) {
            mNavigationDrawerFragment.closeNavigationDrawer();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mNavigationDrawerFragment.getDrawerToggle().syncState();
    }
}
