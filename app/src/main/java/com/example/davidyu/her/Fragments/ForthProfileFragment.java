package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidyu.her.R;


public class ForthProfileFragment extends Fragment {

    public static ForthProfileFragment getInstance(int position) {
        ForthProfileFragment frf = new ForthProfileFragment();
        return frf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_forth_profile,container, false);

        return layout;
    }
}
