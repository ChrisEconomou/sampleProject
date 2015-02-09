package com.chriseconomou.sampleproject.activity;

import android.os.Bundle;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.fragment.MainFragment;
import com.chriseconomou.sampleproject.fragment.NavigationDrawerFragment;
import com.chriseconomou.sampleproject.fragment.ProductsFragment;
import com.chriseconomou.sampleproject.util.Utils;


public class MainActivity extends BaseActivity {



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
        Utils.replaceFragment(this,R.id.container, ProductsFragment.newInstance(),ProductsFragment.TAG,false);
    }





}
