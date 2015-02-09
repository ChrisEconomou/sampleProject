package com.chriseconomou.sampleproject.network.controllers;

import com.chriseconomou.sampleproject.data.ProductDetailsResponse;
import com.chriseconomou.sampleproject.data.ProductsResponse;

public interface GetProductDetailsListener extends BaseListener {

    void onGetProductDetailsError(Throwable throwable);

	void onGetProductDetailsSuccesful(ProductDetailsResponse productsResponse);
}