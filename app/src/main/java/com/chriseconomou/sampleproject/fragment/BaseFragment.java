package  com.chriseconomou.sampleproject.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chriseconomou.sampleproject.application.SampleApplication;
import com.chriseconomou.sampleproject.error.ErrorManager;
import com.chriseconomou.sampleproject.network.Api;
import com.chriseconomou.sampleproject.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Base fragment, that has access to global instances, manages errors and token responses.
 */
public abstract class BaseFragment extends Fragment {


    protected Api mApi;

    private ErrorManager mErrorManager;
    private SampleApplication mSampleApplication;
    private List<rx.Subscription> mSubscriptions;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mSampleApplication = (SampleApplication) activity.getApplication();
        mApi = mSampleApplication.getApi();
        mErrorManager = mSampleApplication.getErrorManager();
        mSubscriptions = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        Utils.unsubscribeSubscriptions(mSubscriptions);
        super.onDestroy();

    }

    protected abstract int getLayoutId();

    protected abstract void initializeViews(Bundle savedInstanceState);

    public void handleError(Throwable throwable) {
            mErrorManager.handleError(mErrorManager.getError(throwable), getActivity());
    }

    protected void addSubscription(rx.Subscription subscription) {
        mSubscriptions.add(subscription);
    }

    protected ErrorManager getErrorManager() {
        return mErrorManager;
    }

    protected SampleApplication getApplication() {
        return mSampleApplication;
    }




}
