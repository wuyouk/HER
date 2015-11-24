package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.davidyu.her.Adapters.HobbyAdapter;
import com.example.davidyu.her.R;


public class ThirdProfileFragment extends Fragment {
    ListView listView;
    ListAdapter adapter;
    String hobbies[];

    public static ThirdProfileFragment getInstance(int position){
        ThirdProfileFragment tf = new ThirdProfileFragment();
        return tf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_third_profile,container, false);

        hobbies=getResources().getStringArray(R.array.hobbies);

        listView = (ListView) layout.findViewById(R.id.listView3);

        adapter=new HobbyAdapter(getActivity(), hobbies);
        listView.setAdapter(adapter);
        return layout;
    }
}
