package com.example.davidyu.her.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.davidyu.her.Adapters.ProfileAdapter;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Views.SlidingTabLayout;


public class ProfileActivity extends FragmentActivity {

    private ViewPager viewPager;
    private SlidingTabLayout stl;
    private ImageView backButton;
    private Bitmap bmp;
    private TextView username_display;
    private String userFbname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TODO: implement the back button to go back to main activity

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.setImageResource(R.drawable.back_arrow_highlight);
                onBackPressed();
            }
        });

        viewPager=(ViewPager) findViewById(R.id.profile_pager);
        viewPager.setAdapter(new ProfileAdapter(getSupportFragmentManager(), getApplicationContext()));
        stl=(SlidingTabLayout) findViewById(R.id.profile_tabs);
        stl.setDistributeEvenly(true);
        stl.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorHighlight);
            }
        });
        stl.setBackgroundColor(getResources().getColor(R.color.primaryColorLight));
        stl.setViewPager(viewPager);
    }

}
