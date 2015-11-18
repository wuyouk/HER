package com.example.davidyu.her.Activities;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

/**
 * Created by r on 11/19/2015.
 */
public class MoreTipsActivity extends AppCompatActivity {

    int[] screenLocation = new int[2];
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;

    private static final int ANIM_DURATION = 500;

    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();

    private ImageView imageView;

    ColorDrawable mBackground;

    //private ShadowLayout mShadowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more_tips);

        imageView = (ImageView) findViewById(R.id.testImage);
        imageView.setImageBitmap(Singleton.getBitmap());



        if (savedInstanceState == null){
            ViewTreeObserver observer = imageView.getViewTreeObserver();

            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                    screenLocation = Singleton.getScreenLocation();

                    int[] size = new int[2];
                    size = Singleton.getSize();

                    // Figure out where the thumbnail and full size versions are, relative
                    // to the screen and each other
                    int[] currentScreenLocation = new int[2];
                    imageView.getLocationOnScreen(currentScreenLocation);

                    mLeftDelta = screenLocation[0] - currentScreenLocation[0];
                    mTopDelta = screenLocation[1] - currentScreenLocation[1];

                    // Scale factors to make the large version the same size as the thumbnail
                    mWidthScale = (float) size[0] / imageView.getWidth();
                    mHeightScale = (float) size[1] / imageView.getHeight();

                    runEnterAnimation();

                    return true;
                }
            });
        }
    }

    public void runEnterAnimation() {
        final long duration = (long) (ANIM_DURATION * 1);

        // Set starting values for properties we're going to animate. These
        // values scale and position the full size version down to the thumbnail
        // size/location, from which we'll animate it back up
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(mWidthScale);
        imageView.setScaleY(mHeightScale);
        imageView.setTranslationX(mLeftDelta);
        imageView.setTranslationY(mTopDelta);

        // We'll fade the text in later
        //mTextView.setAlpha(0);

        // Animate scale and translation to go from thumbnail to full size
        imageView.animate().setDuration(duration).
                scaleX(1).scaleY(1).
                translationX(0).translationY(0).
                setInterpolator(sDecelerator).
                withEndAction(new Runnable() {
                    public void run() {
                        // Animate the description in after the image animation
                        // is done. Slide and fade the text in from underneath
                        // the picture.
                        /*
                        mTextView.setTranslationY(-mTextView.getHeight());
                        mTextView.animate().setDuration(duration/2).
                                translationY(0).alpha(1).
                                setInterpolator(sDecelerator);*/
                    }
                });

        // Fade in the black background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();

/*
        // Animate a drop-shadow of the image
        ObjectAnimator shadowAnim = ObjectAnimator.ofFloat(mShadowLayout, "shadowDepth", 0, 1);
        shadowAnim.setDuration(duration);
        shadowAnim.start();*/
    }
}
