package com.lf.detectingtext.model.request;

import com.google.gson.annotations.SerializedName;
import com.lf.detectingtext.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 11/4/17.
 */

public class VisionRequest {

    @SerializedName("requests")
    private List<AnnotationRequest> mRequestsList;

    public VisionRequest(String encoded){
        mRequestsList = new ArrayList<>();
        Image image = new Image(encoded);
        Feature feature = new Feature(Constants.TYPE_TEXT_DETECTION);
        AnnotationRequest annotationRequest = new AnnotationRequest(image);
        annotationRequest.add(feature);
        add(annotationRequest);
    }

    public void add(AnnotationRequest annotationRequest){
        mRequestsList.add(annotationRequest);
    }

}
