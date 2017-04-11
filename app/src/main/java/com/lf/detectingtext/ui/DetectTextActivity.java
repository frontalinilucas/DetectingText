package com.lf.detectingtext.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.lf.detectingtext.BuildConfig;
import com.lf.detectingtext.R;
import com.lf.detectingtext.interfaces.IApiGoogleCloudVision;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas on 11/4/17.
 */

public class DetectTextActivity extends AppCompatActivity {

    private static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";

    private IApiGoogleCloudVision mApiServices;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_text);
        ButterKnife.bind(this);

        mApiServices = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_GCV_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApiGoogleCloudVision.class);
    }

    public static Intent getIntent(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, DetectTextActivity.class);
        intent.putExtra(KEY_IMAGE_URI, uri);
        return intent;
    }

    /*
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
    byte[] byteArray = byteArrayOutputStream .toByteArray();
    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
    encoded = encoded.replaceAll("\n", "");
    Log.i("123456789", encoded);


    Feature feature = new Feature("TEXT_DETECTION");
    Image image = new Image(encoded);
    AnnotationRequest annotationRequest = new AnnotationRequest(image);
    annotationRequest.add(feature);
    VisionRequest request = new VisionRequest(annotationRequest);
    Call<VisionResponse> callResponse = mApiServices.textDetection(request);
    callResponse.enqueue(new Callback<VisionResponse>() {
        @Override
        public void onResponse(Call<VisionResponse> call, Response<VisionResponse> response) {
            if(response.code() == 200){
                VisionResponse r = response.body();
                Log.i("123456789", r.toString());
                Log.i("123456789", "onResponse");
            }

        }

        @Override
        public void onFailure(Call<VisionResponse> call, Throwable t) {
            Snackbar.make(mFab, getString(R.string.errorSelectImage), Snackbar.LENGTH_LONG).show();
        }
    });
     */
}
