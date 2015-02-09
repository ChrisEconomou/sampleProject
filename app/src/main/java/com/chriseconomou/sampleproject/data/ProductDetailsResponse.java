package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsResponse {

@Expose
@SerializedName("BasePrice")
public Double basePrice;

@Expose
@SerializedName("Brand")
public String brand;

@Expose
@SerializedName("Colour")
public Object colour;

@Expose
@SerializedName("CurrentPrice")
public String currentPrice;

@Expose
@SerializedName("InStock")
public Boolean inStock;

@Expose
@SerializedName("IsInSet")
public Boolean isInSet;

@Expose
@SerializedName("PreviousPrice")
public String previousPrice;

@Expose
@SerializedName("PriceType")
public String priceType;

@Expose
@SerializedName("ProductId")
public Integer productId;

@Expose
@SerializedName("ProductImageUrls")
public List<String> productImageUrls = new ArrayList<String>();

@Expose
@SerializedName("RRP")
public String rrp;

@Expose
@SerializedName("Size")
public Object size;

@Expose
@SerializedName("Sku")
public String sku;

@Expose
@SerializedName("Title")
public String title;

@Expose
@SerializedName("AdditionalInfo")
public String additionalInfo;

}

