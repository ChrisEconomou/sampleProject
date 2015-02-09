package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product {

    @Expose
    @SerializedName("BasePrice")
    public Double basePrice;

    @Expose
    @SerializedName("Brand")
    public String brand;

    @Expose
    @SerializedName("CurrentPrice")
    public String currentPrice;

    @Expose
    @SerializedName("HasMoreColours")
    public Boolean hasMoreColours;

    @Expose
    @SerializedName("IsInSet")
    public Boolean isInSet;

    @Expose
    @SerializedName("PreviousPrice")
    public String previousPrice;

    @Expose
    @SerializedName("ProductId")
    public Integer productId;

    @Expose
    @SerializedName("ProductImageUrl")
    public List<String> productImageUrl = new ArrayList<String>();

    @Expose
    @SerializedName("RRP")
    public String rrp;

    @Expose
    @SerializedName("Title")
    public String title;

}