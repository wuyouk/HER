package com.example.davidyu.her.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Adapters.GiftsRecyclerViewAdapter;
import com.example.davidyu.her.Adapters.TipsRecyclerViewAdapter;
import com.example.davidyu.her.Authenticator.CustomRequest;
import com.example.davidyu.her.Model.Gift;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainSlidingGiftsTabFragment extends Fragment {

    RecyclerView giftRecyclerView;
    List<Gift> giftList = new ArrayList<>();
    GiftsRecyclerViewAdapter adapter;

    static View layout;

    public static MainSlidingGiftsTabFragment getInstance(int position) {
        MainSlidingGiftsTabFragment slidingTabFragment = new MainSlidingGiftsTabFragment();
        return slidingTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_main_sliding_gifts_tab, container, false);

        //find recycler view and set layout managers
        giftRecyclerView = (RecyclerView) layout.findViewById(R.id.giftRecyclerView);
        giftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        giftRecyclerView.setHasFixedSize(true);

        Gift g = new Gift();
        g.setName("Gift");
        g.setText("STINRSTTOINRSTOINESRT");
        g.setIcon("http://i.cs.hku.hk/~sclee/android/pictures/cityLights.jpg");

        //giftList.add(g);

        //updateUI();

        return layout;

    }

    @Override
    public void onResume() {
        super.onResume();
        getGifts();
        updateUI();
    }

    private void updateUI(){

        giftList = Singleton.getGiftList();

        if(adapter==null){
            adapter = new GiftsRecyclerViewAdapter(giftList, getActivity());
            giftRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            adapter.setGiftList(giftList);
            adapter.notifyDataSetChanged();
        }

    }

    public void getGifts(){

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        //parameters to be passed into volley POST request
        Map<String,String> params = new HashMap<>();
        params.put("userID", Singleton.getUserID());
        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "getGifts.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("volleyGifts", "success");

                        List<Gift> giftList = new ArrayList<>();
                        //List<Tip> tipList = new ArrayList<>();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        String name = "", text = "", image = "";

                        try {
                            jsonArray = response.getJSONArray("gifts");

                            //loop through json array
                            for(int i=0; i<jsonArray.length(); i++){
                                jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                text = jsonObject.getString("text");
                                image = jsonObject.getString("image");

                                Gift g = new Gift();
                                g.setName(name);
                                g.setText(text);
                                g.setIcon(image);

                                giftList.add(g);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Singleton.getInstance().setGiftList(giftList);

                        updateUI();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "failure");
                        //progressDialog.dismiss();
                    }
                });

        queue.add(jsonObjectRequest);
    }
}