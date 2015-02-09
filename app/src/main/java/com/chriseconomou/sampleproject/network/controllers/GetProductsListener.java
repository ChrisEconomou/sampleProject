package com.chriseconomou.sampleproject.network.controllers;

import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.data.ProductsResponse;

public interface GetProductsListener extends BaseListener {

    void onGetProductsError(Throwable throwable);

	void onGetProductsSuccesful(ProductsResponse productsResponse);
}