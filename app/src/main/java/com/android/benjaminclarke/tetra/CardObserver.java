package com.android.benjaminclarke.tetra;

import android.view.MotionEvent;

/**
 * Created by benjaminclarke on 04/09/2016.
 */
public interface CardObserver {

    void cardTap(Card c, MotionEvent e);
    void cardSwipe(Card c, MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
    void cardToss(Card c);
}
