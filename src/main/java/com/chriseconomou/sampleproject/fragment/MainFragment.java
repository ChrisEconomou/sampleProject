package com.chriseconomou.sampleproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.adapter.SampleListAdapter;
import com.chriseconomou.sampleproject.data.Response;
import com.chriseconomou.sampleproject.data.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import rx.Observer;


public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    @InjectView(R.id.button_get_results)
    Button mButtonGetResults;

    @InjectView(R.id.list_results)
    ListView mListResults;

    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SampleListAdapter mSampleListAdapter;

    private List<Result> mResultList = new ArrayList<Result>();

    public static final MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
        getResults();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mSampleListAdapter = new SampleListAdapter(getActivity(),R.layout.view_list_item,mResultList);
        mListResults.setAdapter(mSampleListAdapter);
    }

    private void getResults() {
        mApi.getData(new Observer<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                handleError(throwable);
            }

            @Override
            public void onNext(Response storeDetailsResponse) {
                updateData(storeDetailsResponse.results);
            }
        });
    }

    private void updateData(List<Result> results){
        mResultList.clear();
        mResultList.addAll(results);
        mSampleListAdapter.notifyDataSetChanged();

    }
}
