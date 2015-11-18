package com.example.davidyu.her.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.R;

public class PersonalityAdapter extends ArrayAdapter<String> {
    String[] personalitiesDescription;
    TextView textView, textView10;
    RelativeLayout topLayout;
    Context context;

    public PersonalityAdapter(Context context, String[] personalities, String[] personalitiesDescription) {
        super(context, R.layout.adapter_personality,personalities);
        this.personalitiesDescription = personalitiesDescription;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator= LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter_personality, parent, false);
        String title=getItem(position);
        textView=(TextView)customView.findViewById(R.id.card);
        textView.setText(title);
        textView10 = (TextView)customView.findViewById(R.id.textView10);
        textView10.setText(personalitiesDescription[position]);
        topLayout = (RelativeLayout) customView.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbox_getting_curious);
                TextView confirm = (TextView) dialog.findViewById(R.id.confirm);
               // final Intent intent = new Intent(Intent.ACTION_VIEW);
              //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO go to next fragment
                        ((ProfileActivity)context).goToThirdFragment();
                        dialog.dismiss();
                    }
                });
                TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss);
                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }


        });

        return customView;
    }
}
