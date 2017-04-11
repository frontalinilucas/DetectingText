package com.lf.detectingtext.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 11/4/17.
 */

public class VisionRequest {

    @SerializedName("requests")
    private List<AnnotationRequest> mRequestsList;

    public VisionRequest(AnnotationRequest annotationRequest){
        mRequestsList = new ArrayList<>();
        mRequestsList.add(annotationRequest);
    }

}
