package com.lf.detectingtext.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 10/4/17.
 */

public class AnnotationRequest {

    @SerializedName("image")
    private Image mImage;

    @SerializedName("features")
    private List<Feature> mFeaturesList;

    public AnnotationRequest(Image image){
        this.mImage = image;
        mFeaturesList = new ArrayList<>();
    }

    public void add(Feature feature){
        mFeaturesList.add(feature);
    }

}
