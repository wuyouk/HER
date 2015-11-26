package com.example.davidyu.her.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

public class PersonalityAdapter extends ArrayAdapter<String> {
    String[] personalitiesDescription;
    String[] imageSource;
    TextView textView, textView10;
    RelativeLayout topLayout;
    Context context;
    ImageView image;
    String p;

    public PersonalityAdapter(Context context, String[] personalities, String[] personalitiesDescription, String[] imageSource) {
        super(context, R.layout.adapter_personality,personalities);
        this.personalitiesDescription = personalitiesDescription;
        this.imageSource = imageSource;
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
        image = (ImageView) customView.findViewById(R.id.imageView);
        Resources res = context.getResources();
        String mDrawableName = imageSource[position];
        int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        image.setImageDrawable(drawable);
        topLayout = (RelativeLayout) customView.findViewById(R.id.topLayout);
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbox_getting_curious);
                TextView confirm = (TextView) dialog.findViewById(R.id.confirm);
                TextView tv = (TextView)v.findViewById(R.id.card);
                p = tv.getText().toString();
               // final Intent intent = new Intent(Intent.ACTION_VIEW);
              //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO go to next fragment

                        UserProfile u = Singleton.getUserInstance();
                        u.setHerPersonality(p);

                        Singleton.setCurrentPersonality(p);

                        ((ProfileActivity) context).goToThirdFragment();
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
