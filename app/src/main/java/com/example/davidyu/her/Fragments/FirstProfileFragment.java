package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidyu.her.R;


public class FirstProfileFragment extends Fragment {

    public static FirstProfileFragment getInstance(int position) {
        FirstProfileFragment ff = new FirstProfileFragment();
        return ff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_first_profile, container, false);
        return layout;
    }

}