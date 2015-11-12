package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.davidyu.her.Fragments.FirstProfileFragment;
import com.example.davidyu.her.Fragments.SecondProfileFragment;
import com.example.davidyu.her.Fragments.ThirdProfileFragment;
import com.example.davidyu.her.R;


public class ProfileAdapter extends FragmentPagerAdapter {

    String tabs[];
    Context main;
    public ProfileAdapter(android.support.v4.app.FragmentManager fm, Context c) {
        super(fm);
        this.main=c;
        tabs=main.getResources().getStringArray(R.array.tabs);
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        FirstProfileFragment fpf = FirstProfileFragment.getInstance(position);
        SecondProfileFragment spf = SecondProfileFragment.getInstance(position);
        ThirdProfileFragment tpf = ThirdProfileFragment.getInstance(position);


        if (position == 0)
            return fpf;
        else if (position == 1)
            return spf;
        else if (position == 2)
            return tpf;
        return null;
    }
}
