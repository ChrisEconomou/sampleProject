package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoriesResponse {

    @SerializedName("Description")
    @Expose
    public String description;

    @SerializedName("Listing")
    @Expose
    public List<CategoryListing> categoryListing = new ArrayList<CategoryListing>();

    @SerializedName("SortType")
    @Expose
    public String sortType;

}