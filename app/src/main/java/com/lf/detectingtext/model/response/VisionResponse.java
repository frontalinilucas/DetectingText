package com.lf.detectingtext.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 11/4/17.
 */

public class VisionResponse {

    @SerializedName("responses")
    private List<AnnotationResponse> mResponsesList;

    public List<String> getStringList() {
        List<String> stringList = new ArrayList<>();
        for (AnnotationResponse annotationResponse : mResponsesList) {
            if(annotationResponse.getAnnotationsList() != null){
                for(int i = 1; i < annotationResponse.getAnnotationsList().size(); i++){
                    stringList.add(annotationResponse.getAnnotationsList().get(i).getDescription());
                }
            }
        }

        return stringList;
    }
}
