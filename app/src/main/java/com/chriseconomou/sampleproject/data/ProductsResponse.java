package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductsResponse {

@Expose
@SerializedName("AlsoSearched")
public List<Object> alsoSearched = new ArrayList<Object>();

@Expose
@SerializedName("Description")
public String description;

@Expose
@SerializedName("Facets")
public List<Facet> facets = new ArrayList<Facet>();

@Expose
@SerializedName("ItemCount")
public Integer itemCount;

@Expose
@SerializedName("Listings")
public List<Product> products = new ArrayList<Product>();

@Expose
@SerializedName("RedirectUrl")
public String redirectUrl;

@Expose
@SerializedName("SortType")
public String sortType;

}