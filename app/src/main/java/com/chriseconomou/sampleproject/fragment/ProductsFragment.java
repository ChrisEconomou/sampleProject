package com.chriseconomou.sampleproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.activity.ProductDetailsActivity;
import com.chriseconomou.sampleproject.adapter.ProductsGridAdapter;
import com.chriseconomou.sampleproject.adapter.SampleListAdapter;
import com.chriseconomou.sampleproject.data.Product;
import com.chriseconomou.sampleproject.data.ProductsResponse;
import com.chriseconomou.sampleproject.data.Result;
import com.chriseconomou.sampleproject.event.CategoryEvent;
import com.chriseconomou.sampleproject.network.controllers.GetProductsListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import de.greenrobot.event.EventBus;


public class ProductsFragment extends BaseFragment implements GetProductsListener, AdapterView.OnItemClickListener {

    public static final String TAG = ProductsFragment.class.getSimpleName();

    @InjectView(R.id.products_text_title)
    TextView mTextProductsTitle;

    @InjectView(R.id.products_grid)
    GridView mGridProducts;


    private ProductsGridAdapter mProductsGridAdapter;

    private List<Product> mProductList = new ArrayList<Product>();

    public static final ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_products;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mProductsGridAdapter = new ProductsGridAdapter(getActivity(), R.layout.view_grid_item, mProductList);
        mGridProducts.setAdapter(mProductsGridAdapter);
        mGridProducts.setOnItemClickListener(this);
    }

    public void onEvent(CategoryEvent event) {
        Toast.makeText(getActivity(), event.getCategoryId(), Toast.LENGTH_SHORT).show();
        mApi.getProducts(event.getCategoryId(), this);
    }


    @Override
    public void onGetProductsError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onGetProductsSuccesful(ProductsResponse productsResponse) {
        updateData(productsResponse);
    }

    @Override
    public void onEnd() {

    }

    private void updateData(ProductsResponse productsResponse) {
        mProductList.clear();
        mProductList.addAll(productsResponse.products);
        mProductsGridAdapter.notifyDataSetChanged();
        mTextProductsTitle.setText(productsResponse.description);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Integer productId=mProductsGridAdapter.getItem(i).productId;
        if(productId!=null){
            startActivity(ProductDetailsActivity.getIntent(getActivity(), String.valueOf(productId)));
        }

    }
}
