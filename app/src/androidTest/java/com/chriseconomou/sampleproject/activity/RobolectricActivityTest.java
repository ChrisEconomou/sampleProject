package com.chriseconomou.sampleproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.chriseconomou.sampleproject.RobolectricTest;
import com.chriseconomou.sampleproject.application.SampleApplication;
import com.chriseconomou.sampleproject.fragment.BaseFragment;
import com.chriseconomou.sampleproject.util.Utils;

import org.junit.Ignore;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

@Ignore
public abstract class RobolectricActivityTest extends RobolectricTest {

    private static final String TAG = RobolectricActivityTest.class.getSimpleName();
    private SampleApplication mSampleApplication;
    private ActivityController mActivityController;

    protected FragmentActivity mActivity;

    protected BaseFragment mFragment;

    public void setUp(Class classOfActivity) {
        this.setUp(null, null, classOfActivity, null);
    }

    public void setUp(BaseFragment fragment, String fragmentTag, Class classOfActivity) {
        this.setUp(fragment, fragmentTag, classOfActivity, null);
    }

    public void setUp(Class classOfActivity, Bundle bundle) {
        this.setUp(null, null, classOfActivity, bundle);
    }

    public void setUp(BaseFragment fragment, String fragmentTag, Class classOfActivity, Bundle bundle) {
        MockitoAnnotations.initMocks(this);
        mSampleApplication = ((SampleApplication) Robolectric.application);
        mActivityController = Robolectric.buildActivity(classOfActivity);
        if (bundle != null) {
            Intent intent = new Intent();
            intent.putExtras(bundle);
            mActivityController.withIntent(intent);
        }
        mockObjects(mSampleApplication);
        mActivity = (FragmentActivity) createActivity();
        mFragment = fragment;
        if (mFragment != null) {
            Utils.addFragment(mActivity, mFragment, fragmentTag);
        }
        obtainViewReferences();
    }

    private Object createActivity() {
        return mActivityController.create().start().resume().visible().get();
    }

    protected abstract void mockObjects(SampleApplication sampleApplication);

    protected abstract void obtainViewReferences();

    protected <E extends View> E getView(int id) {
        try {
            return (E) mActivity.findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

}
