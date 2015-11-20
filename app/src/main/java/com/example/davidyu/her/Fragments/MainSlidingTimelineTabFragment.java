package com.example.davidyu.her.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.davidyu.her.Adapters.TimeLineAdapter;
import com.example.davidyu.her.R;
import com.example.davidyu.her.models.ChildEntity;
import com.example.davidyu.her.models.GroupEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Yu on 6/5/2015.
 * Modified by Cao Chao on 16/11/2015
 */
public class MainSlidingTimelineTabFragment extends Fragment {

    //link the server here to get the following arrays:
    static String[] Category={"Mathematics","Economics","Sciences"};
    static String[]descriptions={"Trignometry - Circumcentre","Price System","Chemical Bonding"};
    ListView listView;

    private ExpandableListView expandableListView;
    private List<GroupEntity> lists;
    private TimeLineAdapter adapter;


    public static MainSlidingTimelineTabFragment getInstance(int position){
        MainSlidingTimelineTabFragment slidingTabFragment = new MainSlidingTimelineTabFragment();
        return slidingTabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View layout = inflater.inflate(R.layout.fragment_main_sliding_timeline_tab, container, false);
        View layout = inflater.inflate(R.layout.timeline_main, container, false);
   //     adapter = new MainSlidingArchiveTabAdapter(getActivity(),Category,descriptions,qImages,getActivity());
   //     listView = (ListView) layout.findViewById(R.id.quetions_listview);
   //     listView.setAdapter(adapter);
        expandableListView = (ExpandableListView)layout.findViewById(R.id.expandableListView);
        initView();
        return layout;

    }

    private void initView() {
        lists=initList();
        adapter=new TimeLineAdapter(this.getContext(),lists);
        //expandableListView= (ExpandableListView)findViewById(R.id.expandableListView);
        //expandableListView= (ExpandableListView)getActivity().findViewById(R.id.expandableListView);
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
        String[] groupArray = new String[] { "Nov 1","Nov 2", "Nov 3"};
        String[][] childTimeArray = new String[][] {
                { "test1",  "test2", "test3" },
                {  "test4"}, { "test5", "test6" } };
        groupList = new ArrayList<GroupEntity>();
        for (int i = 0; i < groupArray.length; i++){
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


}
