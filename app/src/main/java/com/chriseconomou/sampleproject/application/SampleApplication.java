package com.chriseconomou.sampleproject.application;

import android.app.Application;

import com.chriseconomou.sampleproject.error.ErrorManager;
import com.chriseconomou.sampleproject.database.PreferencesStorage;
import com.chriseconomou.sampleproject.network.Api;

import java.util.ArrayList;
import java.util.List;


/**
 * Global  {@link android.app.Application} instance. Instantiates all global instances.
 */
public class SampleApplication extends Application {


    private Api mApi;
    private ErrorManager mErrorManager;
    private PreferencesStorage mPreferencesStorage;
    private List<rx.Subscription> mSubscriptions;


    @Override
    public void onCreate() {
        super.onCreate();

        mApi = new Api(this);
        mPreferencesStorage = new PreferencesStorage(this);
        mSubscriptions = new ArrayList<>();
        mErrorManager = new ErrorManager(this);
    }

    /**
     * @return Api client to use store services.
     */

    public Api getApi() {
        return mApi;
    }


    public PreferencesStorage getPreferencesStorage() {
        return mPreferencesStorage;
    }

    public ErrorManager getErrorManager() {
        return mErrorManager;
    }
}
