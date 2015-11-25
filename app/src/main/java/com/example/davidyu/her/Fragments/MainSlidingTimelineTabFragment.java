package com.example.davidyu.her.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Adapters.TimeLineAdapter;
import com.example.davidyu.her.Adapters.TipsRecyclerViewAdapter;
import com.example.davidyu.her.Authenticator.CustomRequest;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;
import com.example.davidyu.her.models.ChildEntity;
import com.example.davidyu.her.models.GroupEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David Yu on 6/5/2015.
 * Modified by Cao Chao on 16/11/2015
 */
public class MainSlidingTimelineTabFragment extends Fragment {

    //link the server here to get the following arrays:
    static String[] Category = {"Mathematics", "Economics", "Sciences"};
    static String[] descriptions = {"Trignometry - Circumcentre", "Price System", "Chemical Bonding"};
    ListView listView;

    private ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private TimeLineAdapter adapter;
    private FloatingActionButton fab;
    private Calendar c = Calendar.getInstance();
    private int date = c.get(Calendar.DATE);
    private int current_date = date;
    DateFormat df;

    public static MainSlidingTimelineTabFragment getInstance(int position) {
        MainSlidingTimelineTabFragment slidingTabFragment = new MainSlidingTimelineTabFragment();
        return slidingTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.timeline_main, container, false);
        df = DateFormat.getDateInstance();
        expandableListView = (ExpandableListView) layout.findViewById(R.id.expandableListView);
        fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "I m clicked!!!", Toast.LENGTH_SHORT).show();
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getActivity().getApplicationContext());
                View promptsView = li.inflate(R.layout.popup, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        //Toast.makeText(getActivity().getApplicationContext(), userInput.getText(), Toast.LENGTH_SHORT).show();
                                        //addGroup(df.format(new Date()));
                                        updateTimeline(userInput.getText().toString());
                                        //addChild(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        initView();
        return layout;

    }

    @Override
    public void onResume() {
        super.onResume();

        getTimeline();
    }

    private void initView() {
        lists = new ArrayList<>();
        //lists = initList();
        adapter = new TimeLineAdapter(this.getContext(), lists);
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        expandableListView.setSelection(0);// 设置默认选中项
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }

    }


    public void updateUI(){

        lists = Singleton.getTimeline();

        if(adapter==null){
            adapter = new TimeLineAdapter(this.getContext(), lists);
            expandableListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            adapter.setGroupList(lists);
            adapter.notifyDataSetChanged();
        }
    }

    //helper function to get timeline from server
    private void getTimeline(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        //parameters to be passed into volley POST request

        Map<String,String> params = new HashMap<>();
        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "getTimeLine.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("volley", "success");

                        List<Tip> tipList = new ArrayList<>();

                        List<GroupEntity> groupEntities = new ArrayList<>();

                        JSONArray jsonArray;
                        JSONObject jsonObject;
                        String name = "", text = "", image = "", date = "";

                        try {
                            jsonArray = response.getJSONArray("timeline");

                            //loop through json array
                            for(int i=0; i<jsonArray.length(); i++){
                                jsonObject = jsonArray.getJSONObject(i);

                                date = jsonObject.getString("date");
                                GroupEntity dateGroupEntity = new GroupEntity(date);

                                /**
                                 * get data for each date
                                 */
                                JSONArray jsonEntryArray = jsonObject.getJSONArray("entry");

                                List<ChildEntity> childEntities = new ArrayList<>();

                                for (int j=0; j<jsonEntryArray.length(); j++){
                                    text = jsonEntryArray.getJSONObject(j).getString("text");

                                    ChildEntity childEntity = new ChildEntity(text);

                                    childEntities.add(childEntity);

                                }

                                dateGroupEntity.setChildEntities(childEntities);

                                groupEntities.add(dateGroupEntity);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Singleton.getInstance().setTimeline(groupEntities);

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

    //helper function to update timeline
    private void updateTimeline(String message){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        //parameters to be passed into volley POST request

        Map<String,String> params = new HashMap<>();
        params.put("message", message);
        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "addTimeLine.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("volley", "success");

                        getTimeline();

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
