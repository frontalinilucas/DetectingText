package com.lf.detectingtext.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lucas on 10/4/17.
 */

public class Image {

    @SerializedName("content")
    private String mContent;

    public Image(String content){
        this.mContent = content;
    }

    public String getContent() {
        return mContent;
    }
}
