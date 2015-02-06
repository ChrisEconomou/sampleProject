package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oikonomouc on 05/02/2015.
 */
public class Response {

    @Expose
    public List<Result> results = new ArrayList<Result>();
}
