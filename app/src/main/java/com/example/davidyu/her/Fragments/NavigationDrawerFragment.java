package com.example.davidyu.her.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.Adapters.NavigationDrawerAdapter1;
import com.example.davidyu.her.Adapters.NavigationDrawerAdapter2;
import com.example.davidyu.her.Authenticator.LoginActivity;
import com.example.davidyu.her.Authenticator.ServerRequest;
import com.example.davidyu.her.Authenticator.UserLocalStore;
import com.example.davidyu.her.R;

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle abdt;
    private DrawerLayout drawerlayout;
    private String[] titles={"Edit Profile", "Setting"};
    private int [] icons = {R.drawable.profile, R.drawable.setting};
    private String[] items={"LOGOUT"};
    ListAdapter adapter1;
    ListAdapter adapter2;
    ListView listView;
    ListView listView2;
    static Activity activity;
    UserLocalStore userLocalStore;
    ServerRequest serverRequest;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLocalStore = new UserLocalStore(getActivity());
        serverRequest = new ServerRequest(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer,container, false);
    }

    public void setUp(DrawerLayout drawerlayout, Toolbar toolbar) {//called in MainActivity
        this.drawerlayout=drawerlayout;

        listView = (ListView) getActivity().findViewById(R.id.ND_list1);
        adapter1=new NavigationDrawerAdapter1(getActivity(), titles, icons);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //TODO: insert profile activity
                        Intent i=new Intent(getActivity(), ProfileActivity.class);
                        getActivity().startActivity(i);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                //Intent i2 = new Intent(getActivity(), ConversationActivity.class);
               // startActivity(i2);

            }
        });
        activity = this.getActivity();
        listView2 = (ListView) getActivity().findViewById(R.id.ND_list2);
        adapter2=new NavigationDrawerAdapter2(getActivity(), items);
        listView2.setAdapter(adapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //TODO:
                        //Intent i1 = new Intent(getActivity(), TutorTermsAndConditionsActivity.class);
                        //startActivity(i1);
                        serverRequest.logoutUser();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        break;

                    case 1:
                        //TODO:
                        //Intent i2 = new Intent(getActivity(), LoginActivity.class);
                        //startActivity(i2);
                        //activity.finish();


                        //clear session data using volley

                        serverRequest.logoutUser();
                        startActivity(new Intent(getContext(), LoginActivity.class));

                        break;

                    default:
                        break;
                }
            }
        });

        drawerlayout.setDrawerListener(abdt);
        drawerlayout.post(new Runnable() {
            @Override
            public void run() {
                abdt.syncState();
            }
        });

        abdt=new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        this.drawerlayout.setDrawerListener(abdt);
    }
}
