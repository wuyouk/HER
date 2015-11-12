package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.davidyu.her.R;

public class NavigationDrawerAdapter2 extends ArrayAdapter<String> {
    public NavigationDrawerAdapter2(Context context, String[] titles) {
        super(context, R.layout.adapter2_navigation_drawer,titles);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator= LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter2_navigation_drawer, parent, false);
        String title=getItem(position);
        TextView textView=(TextView)customView.findViewById(R.id.item);
        textView.setText(title);

        return customView;

    }


}
