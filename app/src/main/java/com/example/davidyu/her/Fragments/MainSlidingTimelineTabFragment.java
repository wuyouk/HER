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

import com.example.davidyu.her.Adapters.TimeLineAdapter;
import com.example.davidyu.her.R;
import com.example.davidyu.her.models.ChildEntity;
import com.example.davidyu.her.models.GroupEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
                                        Toast.makeText(getActivity().getApplicationContext(), userInput.getText(), Toast.LENGTH_SHORT).show();
                                        //addGroup(df.format(new Date()));
                                        addChild(userInput.getText().toString());
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

    private void initView() {
        lists = initList();
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

    private List<GroupEntity> initList() {

        List<GroupEntity> groupList;
        //test
        String[] groupArray = new String[]{"Nov 1, 2015", "Nov 2, 2015", "Nov 3, 2015"};
        String[][] childTimeArray = new String[][]{
                {"Watch Martian", "Go to su canteen", "Climb TaiPing Mountain"},
                {"Go to Saiwang"}, {"Go to Lan Kwai Fong", "Open room"}};
        groupList = new ArrayList<GroupEntity>();
        for (int i = 0; i < groupArray.length; i++) {
            GroupEntity groupEntity = new GroupEntity(groupArray[i]);
            List<ChildEntity> childList = new ArrayList<ChildEntity>();
            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildEntity childStatusEntity = new ChildEntity(childTimeArray[i][j]);
                childList.add(childStatusEntity);
            }
            groupEntity.setChildEntities(childList);
            groupList.add(groupEntity);
        }
        return groupList;
    }

    private void addGroup(String date) {

        String group_name = date;
        GroupEntity groupEntity = new GroupEntity(group_name);
        lists.add(groupEntity);
        adapter = new TimeLineAdapter(this.getContext(), lists);
        expandableListView.setAdapter(adapter);
    }

    private void addChild(String event)
    {
        if(adapter.getGroupCount() == 0)
        {
            adapter.AddGroup(df.format(new Date()));
            adapter.getGroupEntity(0).addChild(event);
            adapter.notifyDataSetChanged();
        }
        else
        {
            if(adapter.getGroupEntity(adapter.getGroupCount() - 1 ).getGroupName().equals(df.format(new Date())))
            {
                adapter.getGroupEntity(adapter.getGroupCount() - 1).addChild(event);
                adapter.notifyDataSetChanged();
            }
            else
            {
                adapter.AddGroup(df.format(new Date()));
                Log.d("add", event);
                adapter.getGroupEntity(adapter.getGroupCount() - 1).addChild(event);
                adapter.notifyDataSetChanged();
            }
        }






       /* //expandableListView.setAdapter();
        ChildEntity childEntity  = new ChildEntity(event);
        List<ChildEntity> childList = lists.get(lists.size() - 1).getChildEntities();
        childList.add(childEntity);
        lists.get(lists.size() - 1).setChildEntities(childList);
        adapter = new TimeLineAdapter(this.getContext(), lists);
        expandableListView.setAdapter(adapter);
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }
        */

    }


}
