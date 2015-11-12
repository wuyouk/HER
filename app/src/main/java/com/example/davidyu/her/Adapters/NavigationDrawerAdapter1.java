package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidyu.her.R;


public class NavigationDrawerAdapter1 extends ArrayAdapter<String> {

    private int[] icons;

    public NavigationDrawerAdapter1(Context context, String[] title, int[] icons) {
        super(context, R.layout.adapter1_navigation_drawer, title);
        this.icons = icons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator= LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter1_navigation_drawer, parent, false);
        String title=getItem(position);
        TextView textView=(TextView)customView.findViewById(R.id.title);
        textView.setText(title);
        int icon = icons[position];
        ImageView image=(ImageView)customView.findViewById(R.id.icon);
        image.setImageResource(icon);

        return customView;

    }
}
