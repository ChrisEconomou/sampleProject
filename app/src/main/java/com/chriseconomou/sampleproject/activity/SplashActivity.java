package com.chriseconomou.sampleproject.activity;

import android.os.Bundle;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.database.CategoriesDatabaseAdapter;

import com.chriseconomou.sampleproject.network.controllers.GetCategoriesMenListener;
import com.chriseconomou.sampleproject.network.controllers.GetCategoriesWomenListener;


public class SplashActivity extends BaseActivity implements GetCategoriesMenListener, GetCategoriesWomenListener {


    private boolean mGotCategoriesMen = false;
    private boolean mGotCategoriesWomen = false;

    private CategoriesDatabaseAdapter mCategoriesDatabaseAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spash_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews(savedInstanceState);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mCategoriesDatabaseAdapter = new CategoriesDatabaseAdapter(this);
        mApi.getCategoriesMen(this);
        mApi.getCategoriesWomen(this);
    }


    @Override
    public void onGetCategoriesMenError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onGetCategoriesMenSuccesful(CategoriesResponse categoriesResponse) {
        handleCatagoriesMenSuccess(categoriesResponse);
    }

    @Override
    public void onGetCategoriesWomenError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onGetCategoriesWomenSuccesful(CategoriesResponse categoriesResponse) {
        handleCatagoriesWomenSuccess(categoriesResponse);
    }

    @Override
    public void onEnd() {

    }

    private void handleCatagoriesMenSuccess(CategoriesResponse categoriesResponse) {
        saveCategory(categoriesResponse);
        mGotCategoriesMen = true;
        if (mGotCategoriesWomen) {
            proceedToNextActivity();
        }
    }

    private void handleCatagoriesWomenSuccess(CategoriesResponse categoriesResponse) {
        saveCategory(categoriesResponse);
        mGotCategoriesWomen = true;
        if (mGotCategoriesMen) {
            proceedToNextActivity();
        }
    }

    private void proceedToNextActivity() {
        startActivity(MainActivity.class, true);
    }

    private void saveCategory(CategoriesResponse categoriesResponse) {
        mCategoriesDatabaseAdapter.insert(categoriesResponse.description, categoriesResponse);
    }
}
