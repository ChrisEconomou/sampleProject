package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Facet {

@Expose
@SerializedName("FacetValues")
public List<FacetValue> facetValues = new ArrayList<FacetValue>();

@Expose
@SerializedName("Id")
public String id;

@Expose
@SerializedName("Name")
public String name;

@Expose
@SerializedName("Sequence")
public Integer sequence;

}