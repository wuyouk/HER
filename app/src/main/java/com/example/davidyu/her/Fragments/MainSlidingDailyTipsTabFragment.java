package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidyu.her.R;

public class MainSlidingDailyTipsTabFragment extends Fragment {

    public static MainSlidingDailyTipsTabFragment getInstance(int position){
        MainSlidingDailyTipsTabFragment slidingTabFragment = new MainSlidingDailyTipsTabFragment();
        return slidingTabFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_main_sliding_daily_tips_tab,container, false);
        return layout;
    }
}
