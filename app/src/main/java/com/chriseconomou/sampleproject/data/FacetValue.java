package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacetValue {

@Expose
@SerializedName("Count")
public Integer count;

@Expose
@SerializedName("Id")
public String id;

@Expose
@SerializedName("Name")
public String name;

}