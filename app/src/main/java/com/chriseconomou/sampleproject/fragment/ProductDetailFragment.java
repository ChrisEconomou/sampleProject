package com.chriseconomou.sampleproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.adapter.ProductsGridAdapter;
import com.chriseconomou.sampleproject.data.ProductDetailsResponse;
import com.chriseconomou.sampleproject.network.controllers.GetProductDetailsListener;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;


public class ProductDetailFragment extends BaseFragment implements GetProductDetailsListener {

    public static final String TAG = ProductDetailFragment.class.getSimpleName();

    private static final String ARG_PRODUCT_ID = "arg_product_id";
    @InjectView(R.id.product_details_text_brand_name)
    TextView mTextBrandName;
    @InjectView(R.id.product_details_image)
    ImageView mImage;
    @InjectView(R.id.product_details_text_description)
    TextView mTextDescription;
    @InjectView(R.id.product_details_button_add_to_basket)
    Button mButtonAddToBasket;


    private ProductsGridAdapter mProductsGridAdapter;


    private String mProductId;

    public static final ProductDetailFragment newInstance(String productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(bundle);
        return new ProductDetailFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details;
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        obtainArguments();
        if (mProductId != null) {
            mApi.getProductDetails(mProductId, this);
        }

    }


    private void obtainArguments() {
        if (getArguments() != null) {
            mProductId = getArguments().getString(ARG_PRODUCT_ID);
        }
    }

    @Override
    public void onGetProductDetailsError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onGetProductDetailsSuccesful(ProductDetailsResponse productsResponse) {
        updateData(productsResponse);

    }

    @Override
    public void onEnd() {

    }

    private void updateData(ProductDetailsResponse productsResponse) {
        mTextBrandName.setText(productsResponse.brand);
        if(productsResponse.productImageUrls.size()>0 && productsResponse.productImageUrls.get(0)!=null){
            Picasso.with(getActivity()).load(productsResponse.productImageUrls.get(0)).into(mImage);
        }

        mTextDescription.setText(productsResponse.additionalInfo);
    }
}
