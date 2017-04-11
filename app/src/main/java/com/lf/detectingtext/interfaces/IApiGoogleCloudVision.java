package com.lf.detectingtext.interfaces;

import com.lf.detectingtext.model.request.VisionRequest;
import com.lf.detectingtext.model.response.VisionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lucas on 24/3/17.
 */

public interface IApiGoogleCloudVision {

    @POST("/v1/images:annotate?key=AIzaSyDLXOgMfNnFyqrctmhvijYKHXfzEbRabXU")
    Call<VisionResponse> textDetection(@Body VisionRequest requests);

    /*@GET("/services/rest?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    Call<RecentPublicPhotos> textDetection(
            @Query("api_key") String api_key,
            @Query("per_page") int per_page,
            @Query("page") int page);*/

}
