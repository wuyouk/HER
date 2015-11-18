package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidyu.her.Model.Gift;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *Adapter class for the gifts recycler view
 */
public class GiftsRecyclerViewAdapter extends RecyclerView.Adapter<GiftsRecyclerViewAdapter.GiftViewHolder> {

    private List<Gift> giftList;
    private Context context;

    public GiftsRecyclerViewAdapter(List<Gift> inputList, Context context){
        this.giftList = inputList;
        this.context = context;
    }

    //method to inflate layout for individual cells
    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_gifts_recyclerview_item_layout, viewGroup, false);
        return new GiftViewHolder(view);
    }

    //populate each cell with appropriate information
    @Override
    public void onBindViewHolder(GiftViewHolder giftViewHolder, int i) {
        Gift gift = giftList.get(i);
        giftViewHolder.bindGift(gift);
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public void setGiftList(List<Gift> inputList){
        giftList = inputList;
    }

    /*********************************************************************************************/
    //class for individual cells inside recycler view
    class GiftViewHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener{

        ImageView giftIcon;
        TextView giftName, giftText;

        //constructor for setting up the layout of the viewholder
        public GiftViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            giftIcon = (ImageView) itemView.findViewById(R.id.giftIcon);
            giftName = (TextView) itemView.findViewById(R.id.giftName);
            giftText = (TextView) itemView.findViewById(R.id.giftDescription);

        }

        public void bindGift(Gift gift){
            // bind imageview using picasso library
            Picasso.with(context)
                    .load(gift.getIcon())
                    .resize(200,200)
                    //.placeholder(add drawable) //placeholder image for empty slots
                    //.error(add drawable) //image to use when download fails
                    .into(giftIcon);

            giftName.setText(gift.getName());
            giftText.setText(gift.getText());
        }

        //handle clicks for each cell inside recycler view
        @Override
        public void onClick(View v) {
            //TODO handle click to show detailed description of tip
        }
    }

}


