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

/**
 * Created by Johnson on 11/23/15.
 */
public class RelationAdapter extends ArrayAdapter<String> {
    String[] relationsImage;
    TextView relation_button;
    ImageView relation_image;
    Context context;
    RelativeLayout topLayout;

    public RelationAdapter(Context context, String[] relations, String[] relationsImage) {
        super(context, R.layout.adapter_hobby,relations);
        this.relationsImage = relationsImage;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter_relation, parent, false);

        String title=getItem(position);
        relation_button = (TextView)customView.findViewById(R.id.relation_button);
        relation_button.setText(title);

        relation_image = (ImageView)customView.findViewById(R.id.relation_image);
        Resources res = context.getResources();

        String mDrawableName = relationsImage[position];
        int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        relation_image.setImageDrawable(drawable);

        topLayout = (RelativeLayout) customView.findViewById(R.id.relation_layout);
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbox_f4);
                TextView confirm = (TextView) dialog.findViewById(R.id.confirm33);
                TextView b = (TextView) v.findViewById(R.id.relation_button);
                final String r = b.getText().toString();
                // final Intent intent = new Intent(Intent.ACTION_VIEW);
                //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO go to next fragment

                        UserProfile u = Singleton.getUserInstance();
                        u.setHerRelation(r);

                        ((ProfileActivity) context).goToThirdFragment();

                        //need to change the intent/fragment
                        dialog.dismiss();
                    }
                });
                TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss33);
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
