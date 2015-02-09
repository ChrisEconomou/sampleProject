package com.chriseconomou.sampleproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.chriseconomou.sampleproject.application.SampleApplication;
import com.chriseconomou.sampleproject.error.ErrorManager;
import com.chriseconomou.sampleproject.network.Api;

import butterknife.ButterKnife;

/**
 * Base activity, that holds access to global instances and has activity/fragment management methods.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected Api mApi;
    private ErrorManager mErrorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.inject(this);
        SampleApplication app = ((SampleApplication) getApplication());
        mApi = app.getApi();
        mErrorManager = app.getErrorManager();
    }

    protected abstract int getLayoutId();

    protected abstract void initializeViews(Bundle savedInstanceState);


    protected void startActivity(Class classOfActivity, boolean newTask) {
        startActivity(classOfActivity, null, newTask);
    }


    protected void startActivity(Class classOfActivity, Bundle bundle, boolean newTask) {
        Intent intent = new Intent(this, classOfActivity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (newTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }


    public void handleError(Throwable throwable) {
        mErrorManager.handleError(mErrorManager.getError(throwable), this);
    }
}

