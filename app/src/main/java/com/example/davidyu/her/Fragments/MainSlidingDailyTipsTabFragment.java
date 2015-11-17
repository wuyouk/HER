package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Activities.MainActivity;
import com.example.davidyu.her.Adapters.TipsRecyclerViewAdapter;
import com.example.davidyu.her.Authenticator.CustomRequest;
import com.example.davidyu.her.Authenticator.ServerRequest;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;
import com.example.davidyu.her.Updateable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainSlidingDailyTipsTabFragment extends Fragment implements Updateable {

    RecyclerView tipRecyclerView;
    List<Tip> tipList = new ArrayList<>();
    TipsRecyclerViewAdapter adapter;

    ServerRequest serverRequest;

    public static MainSlidingDailyTipsTabFragment getInstance(int position){
        MainSlidingDailyTipsTabFragment slidingTabFragment = new MainSlidingDailyTipsTabFragment();
        return slidingTabFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_main_sliding_daily_tips_tab,container, false);

        //find recycler view and set layout managers
        tipRecyclerView = (RecyclerView) layout.findViewById(R.id.tipRecyclerView);
        tipRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tipRecyclerView.setHasFixedSize(true);

        getTips();

        updateUI();

        /*
        Tip t = new Tip();
        t.setName("Tip 1");
        t.setText("RSIEhTRSTHRSTUHrST:JTOYHNROISNTHYORSSTYHRSYTJRST");
        t.setIcon("http://i.cs.hku.hk/~sclee/android/pictures/test.jpg");

        tipList.add(t);*/

        //updateUI();

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){

        tipList = Singleton.getTipList();



        if(adapter==null){
            adapter = new TipsRecyclerViewAdapter(tipList, getActivity());
            tipRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            adapter.setTipsList(tipList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void update() {
        updateUI();
    }

    public void getTips(){

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        //parameters to be passed into volley POST request

        Map<String,String> params = new HashMap<>();
        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "getTips.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("volley", "success");

                        List<Tip> tipList = new ArrayList<>();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        String name = "", text = "", image = "";

                        try {
                            jsonArray = response.getJSONArray("tips");

                            //loop through json array
                            for(int i=0; i<jsonArray.length(); i++){
                                jsonObject = jsonArray.getJSONObject(i);
                                name = jsonObject.getString("name");
                                text = jsonObject.getString("text");
                                image = jsonObject.getString("image");

                                Tip t = new Tip();
                                t.setName(name);
                                t.setText(text);
                                t.setIcon(image);

                                tipList.add(t);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Singleton.getInstance().setTipList(tipList);

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
