package com.chriseconomou.sampleproject.fragment;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.chriseconomou.sampleproject.activity.RobolectricActivityTest;
import com.chriseconomou.sampleproject.application.SampleApplication;

import org.junit.Ignore;

@Ignore
public abstract class RobolectricFragmentTest extends RobolectricActivityTest {

    private static final String TAG = RobolectricFragmentTest.class.getSimpleName();

    public void setUp(BaseFragment fragment, String fragmentTag) {
        this.setUp(fragment, fragmentTag, FragmentActivity.class);
    }

    public void setUp(BaseFragment fragment, String fragmentTag, Class classOfActivity) {
        super.setUp(fragment, fragmentTag, classOfActivity);
    }

    protected abstract void mockObjects(SampleApplication doddleApplication);

    protected abstract void obtainViewReferences();

    protected <E extends View> E getView(int id) {
        try {
            return (E) mFragment.getView().findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

}
