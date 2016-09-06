package com.android.benjaminclarke.tetra;

import android.content.Context;
import android.view.MotionEvent;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public class MyUtils {
    public static int dp(int px, Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return(int) (px*scale + 0.5f);
    }

    public static Direction coordsToDirections(MotionEvent e1, MotionEvent e2){
        double a = Math.toDegrees(Math.atan2(e1.getY()  - e2.getY(), e2.getX() - e1.getX()));
        if(a > -45 && a <= 45){
            return Direction.RIGHT;
        } else if (a < -45 && a > -135){
            return Direction.DOWN;
        } else if(a > 45 && a < 145){
            return Direction.UP;
        } else{
            return Direction.LEFT;
        }

    }
}
