package com.lf.detectingtext.utils;

import android.app.Activity;

import com.lf.detectingtext.R;

/**
 * Created by Lucas on 11/4/17.
 */

public class Utils {

    public static void addStartTransitionAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void addFinishTransitionAnimation(Activity activity){
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
