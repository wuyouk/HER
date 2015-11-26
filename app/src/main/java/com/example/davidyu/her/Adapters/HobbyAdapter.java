package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

/**
 * Created by Johnson on 11/23/15.
 */
public class HobbyAdapter extends ArrayAdapter<String> {
    //String[] hobbies;
    TextView textView;
    RelativeLayout topLayout;
    Context context;

    public HobbyAdapter(Context context, String[] hobbies) {
        super(context, R.layout.adapter_hobby,hobbies);
        //this.hobbies = personalitiesDescription;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator= LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter_hobby, parent, false);

        final String title=getItem(position);
        textView=(TextView)customView.findViewById(R.id.hobbyName);
        textView.setText(title);

        final CheckBox c = (CheckBox) customView.findViewById(R.id.hCheckBox);
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (buttonView.isChecked()) {
                    //checked
                    if(!Singleton.getHobbyList().contains(title)){
                        Singleton.getHobbyList().add(title);
                    }
                }
                else
                {
                    //not checked
                    if(Singleton.getHobbyList().contains(title)){
                        int i = Singleton.getHobbyList().indexOf(title);
                        Singleton.getHobbyList().remove(i);
                    }
                }
                }
        });

        topLayout = (RelativeLayout) customView.findViewById(R.id.topLayout3);
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox c = (CheckBox) v.findViewById(R.id.hCheckBox);
                TextView t = (TextView) v.findViewById(R.id.hobbyName);
                UserProfile u = Singleton.getUserInstance();
                if (!c.isChecked()){
                    c.setChecked(true);
                    u.addHobby(t.getText().toString());
                } else {
                    c.setChecked(false);
                    u.cancelHobby(t.getText().toString());
                }
            }
        });
        return customView;
    }
}
