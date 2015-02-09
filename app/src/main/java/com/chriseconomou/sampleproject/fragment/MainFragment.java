package com.chriseconomou.sampleproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.adapter.SampleListAdapter;
import com.chriseconomou.sampleproject.data.Response;
import com.chriseconomou.sampleproject.data.Result;
import com.chriseconomou.sampleproject.event.CategoryEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import rx.Observer;


public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getSimpleName();



    private SampleListAdapter mSampleListAdapter;

    private List<Result> mResultList = new ArrayList<Result>();

    public static final MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {

    }

    public void onEvent(CategoryEvent event){
        Toast.makeText(getActivity(),event.getCategoryId(),Toast.LENGTH_SHORT).show();
    }


}
