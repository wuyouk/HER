package com.example.davidyu.her.Fragments;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.davidyu.her.R;


public class FirstProfileFragment extends Fragment {

    SeekBar seekBar, seekBar2;
    Spinner spinner, spinner2, spinner3, spinner4;
    TextView age1, age2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static FirstProfileFragment getInstance(int position) {
        FirstProfileFragment ff = new FirstProfileFragment();
        return ff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_first_profile, container, false);
        age1 = (TextView) layout.findViewById(R.id.age1);
        age2 = (TextView) layout.findViewById(R.id.age2);
        seekBar = (SeekBar) layout.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                age1.setText(arg1+"");
            }
        });
        seekBar2 = (SeekBar) layout.findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                age2.setText(arg1+"");
            }
        });

        setSpinnerContent(layout);



        return layout;
    }

    private void setSpinnerContent(View layout)
    {
        spinner = (Spinner)layout.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.occupation_array, R.layout.spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(staticAdapter);

        spinner2 = (Spinner)layout.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.earnings_array,  R.layout.spinner_item);
        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(staticAdapter2);

        spinner3 = (Spinner)layout.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> staticAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.occupation_array,  R.layout.spinner_item);
        staticAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(staticAdapter3);

        spinner4 = (Spinner)layout.findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> staticAdapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.earnings_array,  R.layout.spinner_item);
        staticAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(staticAdapter4);
    }

}