package com.lf.detectingtext.interfaces;

import com.lf.detectingtext.model.request.VisionRequest;
import com.lf.detectingtext.model.response.VisionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lucas on 24/3/17.
 */

public interface IApiGoogleCloudVision {

    @POST("/v1/images:annotate")
    Call<VisionResponse> textDetection(
            @Query("key") String key,
            @Body VisionRequest requests);

}
