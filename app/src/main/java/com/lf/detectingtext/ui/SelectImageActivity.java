package com.lf.detectingtext.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.lf.detectingtext.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectImageActivity extends AppCompatActivity {

    private static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";
    private static final int PICK_IMAGE = 501;

    private Uri mUri;

    @BindView(R.id.btnSelectImage)
    AppCompatButton mBtnSelectImage;

    @BindView(R.id.imageSelected)
    AppCompatImageView mImageSelected;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        ButterKnife.bind(this);

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(KEY_IMAGE_URI)){
                mUri = savedInstanceState.getParcelable(KEY_IMAGE_URI);
                showImage();
            }
        }

        mBtnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.selectImage)), PICK_IMAGE);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUri != null){
                    startActivity(DetectTextActivity.getIntent(SelectImageActivity.this, mUri));
                }else{
                    Snackbar.make(mFab, getString(R.string.noImageSelected), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_IMAGE_URI, mUri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch(requestCode){
                case PICK_IMAGE:
                    mUri = data.getData();
                    showImage();
                    break;
            }
        }
    }

    private void showImage() {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
            mImageSelected.setImageBitmap(bitmap);
        } catch (IOException e) {
            Snackbar.make(mFab, getString(R.string.errorSelectImage), Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
