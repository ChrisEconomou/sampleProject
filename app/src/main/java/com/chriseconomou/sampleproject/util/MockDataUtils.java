package com.chriseconomou.sampleproject.util;

import android.content.Context;

import com.chriseconomou.sampleproject.data.CategoriesResponse;
import com.chriseconomou.sampleproject.data.ProductDetailsResponse;
import com.chriseconomou.sampleproject.data.ProductsResponse;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Utils for obtaining mock json data.
 */
public class MockDataUtils {

    public static CategoriesResponse getCategoriesMenResponse(Context context) {
        return (CategoriesResponse) getMockResponse(context, "cats_men.json", CategoriesResponse.class);
    }

    public static CategoriesResponse getCategoriesWomenResponse(Context context) {
        return (CategoriesResponse) getMockResponse(context, "cats_women.json", CategoriesResponse.class);
    }

    public static ProductsResponse getProductsResponse(Context context) {
        return (ProductsResponse) getMockResponse(context, "anycat_products.json", ProductsResponse.class);
    }

    public static ProductDetailsResponse getProductDetailsResponse(Context context) {
        return (ProductDetailsResponse) getMockResponse(context, "anyproduct_details.json", ProductDetailsResponse.class);
    }



    public static Response getRetrofitResponseWithBody(TypedInput body) {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header("", ""));
        return new Response("", 401, "", headers, body);
    }

    public static InputStream getInputStreamFromJsonFile(Context context, String jsonFileName) {
        String invalidGrantResponse = getJsonString(context, jsonFileName);
        return new ByteArrayInputStream(invalidGrantResponse.getBytes());
    }

    private static Object getMockResponse(Context context, String fileName, Class className) {
        String jsonString = getJsonString(context, fileName);
        return GsonUtil.deserialize(jsonString, className);
    }


    public static String getJsonString(Context context, String fileName) {
        String jsonString = null;

        StringBuilder builder = new StringBuilder();
        InputStream json;
        try {
            json = context.getAssets().open("mock/" + fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(json));
            String str;

            while ((str = in.readLine()) != null) {
                builder.append(str);
            }

            in.close();
            jsonString = builder.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

}