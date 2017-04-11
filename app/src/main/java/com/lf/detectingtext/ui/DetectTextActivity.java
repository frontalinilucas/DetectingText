package com.lf.detectingtext.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.lf.detectingtext.BuildConfig;
import com.lf.detectingtext.R;
import com.lf.detectingtext.adapter.DetectTextAdapter;
import com.lf.detectingtext.interfaces.IApiGoogleCloudVision;
import com.lf.detectingtext.model.request.AnnotationRequest;
import com.lf.detectingtext.model.request.Feature;
import com.lf.detectingtext.model.request.Image;
import com.lf.detectingtext.model.request.VisionRequest;
import com.lf.detectingtext.model.response.VisionResponse;
import com.lf.detectingtext.utils.Constants;
import com.lf.detectingtext.utils.DividerEndItemDecoration;
import com.lf.detectingtext.utils.DividerItemDecoration;
import com.lusfold.spinnerloading.SpinnerLoading;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas on 11/4/17.
 */

public class DetectTextActivity extends BaseActivity {

    private static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";
    private static final int CIRCLE_RADIUS = 7;

    private IApiGoogleCloudVision mApiServices;
    private Uri mUri;

    @BindView(R.id.content)
    RelativeLayout mContent;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.spinnerLoading)
    SpinnerLoading mSpinnerLoading;

    @BindView(R.id.noTextInImage)
    AppCompatTextView mNoTextInImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_text);
        ButterKnife.bind(this);
        mSpinnerLoading.setCircleRadius(CIRCLE_RADIUS);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(KEY_IMAGE_URI))
                mUri = savedInstanceState.getParcelable(KEY_IMAGE_URI);
        }else{
            mUri = getIntent().getParcelableExtra(KEY_IMAGE_URI);
        }

        mApiServices = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_GCV_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApiGoogleCloudVision.class);

        mRecyclerView.setAdapter(new DetectTextAdapter(null));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DetectTextActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(DetectTextActivity.this, R.drawable.custom_dividerrecyclerview)));
        mRecyclerView.addItemDecoration(new DividerEndItemDecoration(ContextCompat.getDrawable(DetectTextActivity.this, R.drawable.custom_dividerrecyclerview)));

        getImageText();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_IMAGE_URI, mUri);
    }

    public static Intent getIntent(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, DetectTextActivity.class);
        intent.putExtra(KEY_IMAGE_URI, uri);
        return intent;
    }

    private void getImageText() {
        Bitmap bitmap;
        try{
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
        }catch(Exception e){
            Snackbar.make(mContent, getString(R.string.errorSelectImage), Snackbar.LENGTH_LONG).show();
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encoded = Base64.encodeToString(byteArrayOutputStream .toByteArray(), Base64.DEFAULT).replaceAll("\n", "");

        VisionRequest request = new VisionRequest(encoded);
        Call<VisionResponse> callResponse = mApiServices.textDetection(request);
        callResponse.enqueue(new Callback<VisionResponse>() {
            @Override
            public void onResponse(Call<VisionResponse> call, Response<VisionResponse> response) {
                if(response.code() == Constants.CODE_RESULT_OK){
                    VisionResponse visionResponse = response.body();
                    List<String> stringList = visionResponse.getStringList();
                    mSpinnerLoading.setVisibility(View.GONE);
                    if(stringList.size() > 0){
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mRecyclerView.swapAdapter(new DetectTextAdapter(stringList), true);
                    }else{
                        mNoTextInImage.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<VisionResponse> call, Throwable t) {
                Snackbar.make(mContent, getString(R.string.errorSelectImage), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
