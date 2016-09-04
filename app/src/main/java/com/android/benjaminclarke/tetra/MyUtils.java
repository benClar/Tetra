package com.android.benjaminclarke.tetra;

import android.content.Context;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public class MyUtils {
    public static int dp(int px, Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return(int) (px*scale + 0.5f);
    }
}
