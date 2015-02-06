package com.chriseconomou.sampleproject.activity;

import android.os.Bundle;

import com.chriseconomou.sampleproject.fragment.MainFragment;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews(savedInstanceState);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        initializeListFragment(savedInstanceState);
    }


    private void initializeListFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(MainFragment.newInstance(), android.R.id.content, MainFragment.TAG);
        }
    }


}
