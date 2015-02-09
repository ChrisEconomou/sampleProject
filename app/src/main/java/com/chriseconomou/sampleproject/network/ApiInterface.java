package com.chriseconomou.sampleproject.network;


import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.data.ProductDetailsResponse;
import com.chriseconomou.sampleproject.data.ProductsResponse;
import com.chriseconomou.sampleproject.data.Response;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Api retrofit interface.
 */
public interface ApiInterface {



    @GET("/{categoryType}")
    Observable<CategoriesResponse> getCategories(@Path("categoryType") String categoryType);

    @GET("/anycat_products.json")
    Observable<ProductsResponse> getProducts(@Query("catid") String catid);


    @GET("/anyproduct_details.json")
    Observable<ProductDetailsResponse> getProductDetails(@Query("catid") String producatId);

}
