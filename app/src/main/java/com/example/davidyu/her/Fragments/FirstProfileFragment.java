package com.example.davidyu.her.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;


public class FirstProfileFragment extends Fragment {

    SeekBar seekBar, seekBar2;
    Spinner spinner, spinner2, spinner3, spinner4;
    TextView age1, age2;
    Context context;

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
        context = getActivity();
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
        Button button= (Button) layout.findViewById(R.id.confirm_button_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInformation(v);
            }
        });

        return layout;
    }

    private void confirmInformation(View v) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogbox_f1);
        TextView confirm = (TextView) dialog.findViewById(R.id.confirm1);
        // final Intent intent = new Intent(Intent.ACTION_VIEW);
        //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to next fragment
                UserProfile u = Singleton.getUserInstance();
                u.setMyAge(age1.getText().toString());
                u.setHerAge(age2.getText().toString());
                u.setMyOccupation(spinner.getSelectedItem().toString());
                u.setMyEarnings(spinner2.getSelectedItem().toString());
                u.setHerOccupation(spinner3.getSelectedItem().toString());
                u.setHerEarnings(spinner4.getSelectedItem().toString());
                ((ProfileActivity)context).goToSecondFragment();
                dialog.dismiss();
            }
        });
        TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss1);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

        Toast.makeText(getActivity(), "Confirmed basic information", Toast.LENGTH_SHORT).show();
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