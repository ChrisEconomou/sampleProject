package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryListing {

    @SerializedName("CategoryId")
    @Expose
    public String categoryId;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("ProductCount")
    @Expose
    public Integer productCount;

}