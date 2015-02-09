package com.chriseconomou.sampleproject.error;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.chriseconomou.sampleproject.R;
import com.chriseconomou.sampleproject.data.ErrorResponse;
import com.chriseconomou.sampleproject.util.GsonUtil;
import com.chriseconomou.sampleproject.util.Utils;

import java.io.IOException;

import retrofit.RetrofitError;

/**
 * Translates and handles errors from api.
 */
public class ErrorManager {

    private static final String TAG = ErrorManager.class.getSimpleName();
    private Context mContext;


    public ErrorManager(Context context) {
        mContext = context;
    }


    public Error getError(Throwable throwable) {

        if (throwable != null && throwable instanceof RetrofitError) {
            RetrofitError retrofitError = (RetrofitError) throwable;
            if (retrofitError.getKind() == RetrofitError.Kind.NETWORK) {

                return createNetworkError();

            } else {

                return createServerError(retrofitError);

            }
        } else {
            return createDefaultError();
        }

    }


    public void handleError(Error error, FragmentActivity activity) {
        showToast(error);
    }


    private Error createServerError(RetrofitError retrofitError) {

        String messageBody = null;
        try {
            messageBody = Utils.fromStream(retrofitError.getResponse().getBody().in());

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (messageBody != null) {
            ErrorResponse errorResponse = GsonUtil.deserialize(messageBody, ErrorResponse.class);
            if (errorResponse != null) {
                return createError(errorResponse);
            } else {
                return createDefaultError();
            }

        } else {
            return createDefaultError();
        }


    }

    private Error createError(ErrorResponse response) {

        if (response.error != null) {
            return createErrorFromErrorResponse(response);
        } else {
            return createDefaultError();
        }


    }


    private Error createErrorFromErrorResponse(ErrorResponse response) {
        if (response.error != null) {
            return new Error(response.error);
        } else {
            return createDefaultError();
        }
    }


    private Error createNetworkError() {
        return new Error(mContext.getResources().getString(R.string.error_network_message));
    }

    private Error createDefaultError() {
        return new Error(mContext.getString(R.string.error_default_message));
    }


    private void showToast(Error error) {
        Toast.makeText(mContext, error.getErrorDescription(), Toast.LENGTH_LONG).show();
    }
}
