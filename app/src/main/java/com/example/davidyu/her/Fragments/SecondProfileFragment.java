package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.davidyu.her.Adapters.PersonalityAdapter;
import com.example.davidyu.her.R;


public class SecondProfileFragment extends Fragment {
    String personalities[];
    String personalitiesDescription[];
    String imageSource[];
    ListView listView;
    ListAdapter adapter;

    public static SecondProfileFragment getInstance(int position) {
        SecondProfileFragment sf = new SecondProfileFragment();
        return sf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_second_profile,container, false);

        personalities=getResources().getStringArray(R.array.personalities);
        personalitiesDescription = getResources().getStringArray(R.array.personalities_description);
        imageSource = getResources().getStringArray(R.array.p_pic);
        listView = (ListView) layout.findViewById(R.id.listView);
        adapter=new PersonalityAdapter(getActivity(), personalities,personalitiesDescription, imageSource);
        listView.setAdapter(adapter);
        return layout;
    }
}
