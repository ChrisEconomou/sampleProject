package com.chriseconomou.sampleproject.event;

/**
 * Created by oikonomouc on 09/02/2015.
 */
public class AddToBagEvent {

    private String mProductId;

    public AddToBagEvent(String productId) {
        this.mProductId = productId;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String mProductId) {
        this.mProductId = mProductId;
    }

}
