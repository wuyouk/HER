package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.davidyu.her.R;

/**
 * Created by Johnson on 11/23/15.
 */
public class RelationAdapter extends ArrayAdapter<String> {
    String[] relationsImage;
    Button relation_button;
    ImageView relation_image;
    Context context;

    public RelationAdapter(Context context, String[] relations, String[] relationsImage) {
        super(context, R.layout.adapter_hobby,relations);
        this.relationsImage = relationsImage;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter_relation, parent, false);

        String title=getItem(position);
        relation_button = (Button)customView.findViewById(R.id.relation_button);
        relation_button.setText(title);
        relation_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Go for it!", Toast.LENGTH_SHORT).show();
            }
        });

        relation_image = (ImageView)customView.findViewById(R.id.relation_image);
        Resources res = context.getResources();

        String mDrawableName = relationsImage[position];
        int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        relation_image.setImageDrawable(drawable);
        return customView;
    }


}
