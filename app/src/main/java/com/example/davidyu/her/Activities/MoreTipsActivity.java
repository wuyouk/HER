package com.example.davidyu.her.Activities;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

/**
 * Class to show the details of the tips
 */
public class MoreTipsActivity extends AppCompatActivity {

    int[] screenLocation = new int[2];
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;

    private static final int ANIM_DURATION = 500; //length of the animation

    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();

    private ImageView imageView;
    private TextView textView;

    ColorDrawable mBackground;

    //private ShadowLayout mShadowLayout;
    private LinearLayout topLevelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_more_tips);

        topLevelLayout = (LinearLayout) findViewById(R.id.topLevelLayout);

        textView = (TextView) findViewById(R.id.moreDetailsTextView);
        textView.setText(Singleton.getTipsText());

        imageView = (ImageView) findViewById(R.id.testImage);
        imageView.setImageBitmap(Singleton.getBitmap());

        //mBackground = new ColorDrawable(Color.BLACK);
        mBackground = new ColorDrawable(getResources().getColor(R.color.primaryColorLight));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topLevelLayout.setBackground(mBackground);
        }

        //launching from daily tips fragment
        if (savedInstanceState == null){
            ViewTreeObserver observer = imageView.getViewTreeObserver();

            //before anything is drawn onto the screen
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                    //get previous image screen location
                    screenLocation = Singleton.getScreenLocation();

                    //get previous image size
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

    //make image zoom in
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
        textView.setAlpha(0);

        // Animate scale and translation to go from thumbnail to full size
        imageView.animate().setDuration(duration).
                scaleX(1).scaleY(1).
                translationX(0).translationY(0).
                setInterpolator(sDecelerator).withEndAction(new Runnable() {
            @Override
            public void run() {
                textView.setTranslationY(-textView.getHeight());
                textView.animate().setDuration(duration/2).
                        translationY(0).alpha(1).
                        setInterpolator(sAccelerator);
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

    public void runExitAnimation(final Runnable endAction) {
        //final long duration = (long) (ANIM_DURATION * ActivityAnimations.sAnimatorScale);
        final long duration = (long) (ANIM_DURATION * 1);

        // No need to set initial values for the reverse animation; the image is at the
        // starting size/location that we want to start from. Just animate to the
        // thumbnail size/location that we retrieved earlier

        // Caveat: configuration change invalidates thumbnail positions; just animate
        // the scale around the center. Also, fade it out since it won't match up with
        // whatever's actually in the center
        final boolean fadeOut;
        /*
        if (getResources().getConfiguration().orientation != mOriginalOrientation) {
            mImageView.setPivotX(mImageView.getWidth() / 2);
            mImageView.setPivotY(mImageView.getHeight() / 2);
            mLeftDelta = 0;
            mTopDelta = 0;
            fadeOut = true;
        } else {
            fadeOut = false;
        }*/
        fadeOut = true;

         //First, slide/fade text out of the way
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.animate().translationY(-textView.getHeight()).alpha(0).
                    setDuration(duration / 2).setInterpolator(sAccelerator).
                    withEndAction(new Runnable() {
                        public void run() {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView.animate().setDuration(duration).
                                        scaleX(mWidthScale).scaleY(mHeightScale).
                                        translationX(mLeftDelta).translationY(mTopDelta).
                                        setInterpolator(sDecelerator).
                                        withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                            }
                                        });
                            }

                            /* Animate the shadow of the image
                            ObjectAnimator shadowAnim = ObjectAnimator.ofFloat(mShadowLayout,
                                    "shadowDepth", 1, 0);
                            shadowAnim.setDuration(duration);
                            shadowAnim.start();*/


                        }
                    });
        }

        // Fade out background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0);
        bgAnim.setDuration(duration);
        bgAnim.start();
    }

    @Override
    public void onBackPressed() {

        runExitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0,0);
    }
}
