package com.example.davidyu.her.Adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidyu.her.Activities.MainActivity;
import com.example.davidyu.her.Activities.MoreTipsActivity;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *
 */
public class TipsRecyclerViewAdapter extends RecyclerView.Adapter<TipsRecyclerViewAdapter.TipViewHolder> {

    private List<Tip> tipsList;
    private Context context;

    public TipsRecyclerViewAdapter(List<Tip> inputList, Context context){
        this.tipsList = inputList;
        this.context = context;
    }

    @Override
    public TipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_tips_recyclerview_item_layout, parent, false);
        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TipViewHolder holder, int position) {
        Tip tip = tipsList.get(position);
        holder.bindTip(tip);
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    public void setTipsList(List<Tip> input){
        tipsList = input;
    }

    /*********************************************************************************************/
    //class for individual cells inside recycler view
    class TipViewHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener{

        ImageView tipIcon;
        TextView tipName, tipText;

        //constructor for setting up the layout of the viewholder
        public TipViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tipIcon = (ImageView) itemView.findViewById(R.id.tipIcon);
            tipName = (TextView) itemView.findViewById(R.id.tipName);
            tipText = (TextView) itemView.findViewById(R.id.tipsDescription);

        }

        public void bindTip(Tip tip){
            // bind imageview using picasso library
            Picasso.with(context)
                    .load(tip.getIcon())
                    //.resize(200,200)
                    //.placeholder(add drawable) //placeholder image for empty slots
                    //.error(add drawable) //image to use when download fails
                    .into(tipIcon);

            tipName.setText(tip.getName());
            tipText.setText(tip.getText());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tipIcon.setTransitionName(context.getString(R.string.transition_string));
            }

        }

        //handle clicks for each cell inside recycler view

        @Override
        public void onClick(View v) {

            //get text of tip
            TextView textView = (TextView) v.findViewById(R.id.tipsDescription);

            //get image that is clicked on inside cardview
            ImageView imageView = (ImageView) v.findViewById(R.id.tipIcon);
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            Singleton.setBitmap(bitmap);

            //get image location on screen
            int[] screenLocation = new int[2];
            //v.getLocationOnScreen(screenLocation);
            imageView.getLocationOnScreen(screenLocation);
            Singleton.setScreenLocation(screenLocation);

            //get dimensions of the image
            int[] size = new int[2];
            size[0] = imageView.getWidth();
            size[1] = imageView.getHeight();
            Singleton.setSize(size);

            //save current text
            Singleton.setTipsText(textView.getText().toString());

            Intent i = new Intent(context, MoreTipsActivity.class);
            context.startActivity(i);

            //overrid default animations
            ((MainActivity)context).overridePendingTransition(0, 0);

        }
    }

}


