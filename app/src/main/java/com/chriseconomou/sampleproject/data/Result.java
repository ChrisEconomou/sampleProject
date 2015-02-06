package com.chriseconomou.sampleproject.data;

import com.google.gson.annotations.Expose;

/**
 * Model that holds the date we get back from the api
 */
public class Result {

@Expose
public String createdAt;
@Expose
public String name;
@Expose
public String objectId;
@Expose
public String updatedAt;
@Expose
public String url;

    @Override
    public String toString() {
        return "Result{" +
                "createdAt='" + createdAt + '\'' +
                ", name='" + name + '\'' +
                ", objectId='" + objectId + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
