package com.chriseconomou.sampleproject.network.controllers;

import com.chriseconomou.sampleproject.data.CategoriesResponse;

public interface GetCategoriesWomenListener extends BaseListener {

    void onGetCategoriesWomenError(Throwable throwable);

	void onGetCategoriesWomenSuccesful(CategoriesResponse categoriesResponse);
}