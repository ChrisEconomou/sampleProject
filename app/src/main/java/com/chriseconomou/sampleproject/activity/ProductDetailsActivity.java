package com.chriseconomou.sampleproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.fragment.NavigationDrawerFragment;
import com.chriseconomou.sampleproject.fragment.ProductDetailFragment;
import com.chriseconomou.sampleproject.fragment.ProductsFragment;
import com.chriseconomou.sampleproject.util.Utils;


public class ProductDetailsActivity extends BaseActivity {

    private static final String ARG_PRODUCT_ID = "arg_product_id";

    private String mProductId;
    public static Intent getIntent(Activity callingActivity, String productId) {
        Intent intent = new Intent(callingActivity, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PRODUCT_ID, productId);
        intent.putExtras(bundle);
        return intent;
    }

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtainArguments();
        initializeViews(savedInstanceState);

    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        if(mProductId!=null){
            Utils.replaceFragment(this, R.id.container, ProductDetailFragment.newInstance(mProductId), ProductDetailFragment.TAG, false);
        }

    }

    private void obtainArguments(){
        if(getIntent().getExtras()!=null){
            mProductId=getIntent().getExtras().getString(ARG_PRODUCT_ID);
        }
    }

}
