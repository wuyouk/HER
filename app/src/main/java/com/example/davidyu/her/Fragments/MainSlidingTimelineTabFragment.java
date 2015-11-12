package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.davidyu.her.R;

/**
 * Created by David Yu on 6/5/2015.
 */
public class MainSlidingTimelineTabFragment extends Fragment {

    //link the server here to get the following arrays:
    static String[] Category={"Mathematics","Economics","Sciences"};
    static String[]descriptions={"Trignometry - Circumcentre","Price System","Chemical Bonding"};
    ListView listView;

    public static MainSlidingTimelineTabFragment getInstance(int position){
        MainSlidingTimelineTabFragment slidingTabFragment = new MainSlidingTimelineTabFragment();
        return slidingTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main_sliding_timeline_tab, container, false);
   //     adapter = new MainSlidingArchiveTabAdapter(getActivity(),Category,descriptions,qImages,getActivity());
   //     listView = (ListView) layout.findViewById(R.id.quetions_listview);
   //     listView.setAdapter(adapter);
        return layout;

    }




}
