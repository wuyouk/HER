package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.davidyu.her.Adapters.RelationAdapter;
import com.example.davidyu.her.R;


public class ForthProfileFragment extends Fragment {
    ListView listView;
    ListAdapter adapter;
    String relations[];
    String relationImages[];

    public static ForthProfileFragment getInstance(int position) {
        ForthProfileFragment frf = new ForthProfileFragment();
        return frf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_forth_profile,container, false);

        relations=getResources().getStringArray(R.array.relations);
        relationImages = getResources().getStringArray(R.array.r_pic);
        listView = (ListView) layout.findViewById(R.id.listView5);

        adapter=new RelationAdapter(getActivity(), relations, relationImages);
        listView.setAdapter(adapter);





        return layout;
    }
}
