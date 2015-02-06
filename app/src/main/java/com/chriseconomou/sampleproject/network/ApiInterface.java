package com.chriseconomou.sampleproject.network;


import com.chriseconomou.sampleproject.data.Response;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import rx.Observable;


/**
 * Api retrofit interface.
 */
public interface ApiInterface {


    @GET("/Test")
    @Headers("Content-Type: application/json")
    Observable<Response> getTests(@Header("X-Parse-REST-API-Key") String apiKey, @Header("X-Parse-Application-Id") String applicationId);


}
