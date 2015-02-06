package com.chriseconomou.sampleproject.network;

import android.content.Context;
import android.util.Log;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.Response;
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

    /**
     * @param listener the listener that will be called when we get the response back
     * @return the list of Test objects.
     */

    public Subscription getData(Observer<Response> listener) {
        Observable<Response> observable = mApiInterface.getTests(mApiKey, mApplicationId);
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(listener);

    }


}
