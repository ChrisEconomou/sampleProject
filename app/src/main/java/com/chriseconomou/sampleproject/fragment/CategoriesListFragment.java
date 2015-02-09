package com.chriseconomou.sampleproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.adapter.CategoriesListAdapter;
import com.chriseconomou.sampleproject.data.CategoryListing;
import com.chriseconomou.sampleproject.database.CategoriesDatabaseAdapter;
import com.chriseconomou.sampleproject.event.CategoryEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;


public class CategoriesListFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    public static final String TAG = CategoriesListFragment.class.getSimpleName();

    private static final String ARG_TYPE = "arg_type";

    @InjectView(R.id.list_categories)
    ListView mListCatagories;

    private CategoriesListAdapter mCategoriesListAdapter;

    private List<CategoryListing> mResultList = new ArrayList<CategoryListing>();

    private String mType;

    private CategoriesDatabaseAdapter mCategoriesDatabaseAdapter;

    public static final CategoriesListFragment newInstance(String type) {
        CategoriesListFragment fragment = new CategoriesListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category_list;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mCategoriesDatabaseAdapter = new CategoriesDatabaseAdapter(getActivity());
        mCategoriesListAdapter = new CategoriesListAdapter(getActivity(), R.layout.view_list_item, mResultList);
        mListCatagories.setAdapter(mCategoriesListAdapter);
        mListCatagories.setOnItemClickListener(this);
        obtainArguments();
        if (mType != null) {
            updateData(mCategoriesDatabaseAdapter.getCategory(mType).categoryListing);
        }

    }


    private void updateData(List<CategoryListing> results) {
        mResultList.clear();
        mResultList.addAll(results);
        mCategoriesListAdapter.notifyDataSetChanged();

    }

    private void obtainArguments() {
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        EventBus.getDefault().post(new CategoryEvent(mResultList.get(i).categoryId));
    }
}
