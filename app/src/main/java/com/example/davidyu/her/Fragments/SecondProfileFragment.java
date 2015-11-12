package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidyu.her.R;


public class SecondProfileFragment extends Fragment {

    public static SecondProfileFragment getInstance(int position) {
        SecondProfileFragment sf = new SecondProfileFragment();
        return sf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_second_profile,container, false);

        return layout;
    }
}
