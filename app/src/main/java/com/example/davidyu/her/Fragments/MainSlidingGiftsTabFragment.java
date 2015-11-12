package com.example.davidyu.her.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.davidyu.her.R;


public class MainSlidingGiftsTabFragment extends Fragment {

    static View layout;

    public static MainSlidingGiftsTabFragment getInstance(int position) {
        MainSlidingGiftsTabFragment slidingTabFragment = new MainSlidingGiftsTabFragment();
        return slidingTabFragment;
    }


    public void setItems(String[] Category, String[] descriptions, Bitmap[] qImages) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_main_sliding_gifts_tab, container, false);
        return layout;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}