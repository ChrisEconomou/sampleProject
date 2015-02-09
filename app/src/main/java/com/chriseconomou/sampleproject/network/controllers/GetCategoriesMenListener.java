package com.chriseconomou.sampleproject.network.controllers;

import com.chriseconomou.sampleproject.data.CategoriesResponse;

public interface GetCategoriesMenListener extends BaseListener {

    void onGetCategoriesMenError(Throwable throwable);

	void onGetCategoriesMenSuccesful(CategoriesResponse categoriesResponse);
}