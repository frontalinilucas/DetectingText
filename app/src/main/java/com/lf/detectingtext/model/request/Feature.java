package com.lf.detectingtext.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lucas on 10/4/17.
 */

public class Feature {

    @SerializedName("type")
    private String mType;

    public Feature(String type){
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
