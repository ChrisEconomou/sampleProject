package com.chriseconomou.sampleproject.network;

import android.content.Context;
import android.util.Log;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.data.ProductDetailsResponse;
import com.chriseconomou.sampleproject.data.ProductsResponse;
import com.chriseconomou.sampleproject.data.Response;
import com.chriseconomou.sampleproject.network.controllers.GetCategoriesMenListener;
import com.chriseconomou.sampleproject.network.controllers.GetCategoriesWomenListener;
import com.chriseconomou.sampleproject.network.controllers.GetProductDetailsListener;
import com.chriseconomou.sampleproject.network.controllers.GetProductsListener;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Rest api client
 */
public class Api {

    public static final String TAG = Api.class.getSimpleName();

    private final Context mContext;
    private final ApiInterface mApiInterface;
    private final String mApiKey;
    private final String mApplicationId;

    public Api(Context context) {
        mContext = context;
        try {
            mApiInterface = buildRestAdapter(mContext.getString(R.string.api_url))
                    .create(ApiInterface.class);
        } catch (Exception e) {
            throw new IllegalStateException("Can't create api controllers");
        }
        mApiKey = mContext.getString(R.string.parse_api_key);
        mApplicationId = mContext.getString(R.string.application_id);
    }

    private RestAdapter buildRestAdapter(final String url) throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        int readTimeout = Integer.parseInt(mContext.getString(R.string.webApi_httpReadTimeout));
        int connectTimeout = Integer.parseInt(mContext
                .getString(R.string.webApi_httpConnectTimeout));

        okHttpClient.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        OkClient client = new OkClient(okHttpClient);

        return new RestAdapter.Builder().setErrorHandler(new ErrorHandler() {

            @Override
            public Throwable handleError(RetrofitError error) {
                return error;
            }
        }).setEndpoint(url).setLog(new RestAdapter.Log() {
            @Override
            public void log(final String message) {
                Log.d(TAG, message);
            }
        }).setLogLevel(RestAdapter.LogLevel.FULL).setClient(client)
                .build();
    }


    public Subscription getCategoriesMen(final GetCategoriesMenListener listener) {
        Observable<CategoriesResponse> observable = mApiInterface.getCategories(mContext.getString(R.string.categories_men_file));

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndObserver<CategoriesResponse>() {
                    @Override
                    public void onCompleted() {
                        listener.onEnd();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onGetCategoriesMenError(throwable);
                        listener.onEnd();
                    }

                    @Override
                    public void onNext(CategoriesResponse response) {
                        listener.onGetCategoriesMenSuccesful(response);

                    }
                });

        return subscription;

    }

    public Subscription getCategoriesWomen(final GetCategoriesWomenListener listener) {
        Observable<CategoriesResponse> observable = mApiInterface.getCategories(mContext.getString(R.string.categories_women_file));

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndObserver<CategoriesResponse>() {
                    @Override
                    public void onCompleted() {
                        listener.onEnd();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onGetCategoriesWomenError(throwable);
                        listener.onEnd();
                    }

                    @Override
                    public void onNext(CategoriesResponse response) {
                        listener.onGetCategoriesWomenSuccesful(response);

                    }
                });

        return subscription;

    }


    public Subscription getProducts(String categoryId, final GetProductsListener listener) {
        Observable<ProductsResponse> observable = mApiInterface.getProducts(categoryId);

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndObserver<ProductsResponse>() {
                    @Override
                    public void onCompleted() {
                        listener.onEnd();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onGetProductsError(throwable);
                        listener.onEnd();
                    }

                    @Override
                    public void onNext(ProductsResponse response) {
                        listener.onGetProductsSuccesful(response);

                    }
                });

        return subscription;

    }

    public Subscription getProductDetails(String productId, final GetProductDetailsListener listener) {
        Observable<ProductDetailsResponse> observable = mApiInterface.getProductDetails(productId);

        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndObserver<ProductDetailsResponse>() {
                    @Override
                    public void onCompleted() {
                        listener.onEnd();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        listener.onGetProductDetailsError(throwable);
                        listener.onEnd();
                    }

                    @Override
                    public void onNext(ProductDetailsResponse response) {
                        listener.onGetProductDetailsSuccesful(response);

                    }
                });

        return subscription;

    }

}
