package com.example.davidyu.her.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.Adapters.HobbyAdapter;
import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

import java.util.ArrayList;


public class ThirdProfileFragment extends Fragment {
    ListView listView;
    ListAdapter adapter;
    String hobbies[];
    RelativeLayout topLayout;
    Context context;

    public static ThirdProfileFragment getInstance(int position){
        ThirdProfileFragment tf = new ThirdProfileFragment();
        return tf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_third_profile, container, false);
        context = getActivity();
        hobbies=getResources().getStringArray(R.array.hobbies);

        listView = (ListView) layout.findViewById(R.id.listView3);

        adapter=new HobbyAdapter(getActivity(), hobbies);
        listView.setAdapter(adapter);
        Button button= (Button) layout.findViewById(R.id.confirm_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmHobby(v);
            }
        });
        return layout;
    }

    private void confirmHobby(View v){

        UserProfile u = Singleton.getUserInstance();
        ArrayList<String> a = u.getHobby();
        String hobby = "";

        for (String s : a) {
            hobby = hobby + "-" + s;
        }

        Toast.makeText(getActivity(), hobby.toString(), Toast.LENGTH_SHORT).show();

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogbox_f3);
        TextView confirm = (TextView) dialog.findViewById(R.id.confirm3);
        // final Intent intent = new Intent(Intent.ACTION_VIEW);
        //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO go to next fragment
                ((ProfileActivity)context).goToFourFragment();
                dialog.dismiss();
            }
        });
        TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss3);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        Toast.makeText(getActivity(), u.getPersonality(), Toast.LENGTH_SHORT).show();
    }
}
