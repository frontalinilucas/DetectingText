package com.lf.detectingtext.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lucas on 11/4/17.
 */

public class AnnotationResponse {

    @SerializedName("textAnnotations")
    private List<EntityAnnotation> mAnnotationsList;

    public List<EntityAnnotation> getAnnotationsList() {
        return mAnnotationsList;
    }
}
