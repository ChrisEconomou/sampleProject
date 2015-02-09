package com.chriseconomou.sampleproject.event;

/**
 * Created by christosoikonomou on 07/02/2015.
 */
public class CategoryEvent {


    private String mCategoryId;

    public CategoryEvent(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

}
