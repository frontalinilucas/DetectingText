package com.lf.detectingtext.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lf.detectingtext.utils.Utils;

/**
 * Created by Lucas on 11/4/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        supportFinishAfterTransition();
        Utils.addFinishTransitionAnimation(this);
    }

    public void startActivity(Activity activity, Intent intent){
        startActivity(intent);
        Utils.addStartTransitionAnimation(activity);
    }

}
