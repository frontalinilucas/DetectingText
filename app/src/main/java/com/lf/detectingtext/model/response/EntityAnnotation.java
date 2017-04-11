package com.lf.detectingtext.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lucas on 11/4/17.
 */

public class EntityAnnotation {

     @SerializedName("locale")
     private String mLocale;

     @SerializedName("description")
     private String mDescription;

     public String getLocale() {
          return mLocale;
     }

     public String getDescription() {
          return mDescription;
     }
}
