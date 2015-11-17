package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 11/15/2015.
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
                    .resize(200,200)
                    //.placeholder(add drawable) //placeholder image for empty slots
                    //.error(add drawable) //image to use when download fails
                    .into(tipIcon);

            tipName.setText(tip.getName());
            tipText.setText(tip.getText());
        }

        //handle clicks for each cell inside recycler view
        @Override
        public void onClick(View v) {
            //TODO handle click to show detailed description of tip
        }
    }

}


