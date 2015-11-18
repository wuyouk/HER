package com.example.davidyu.her.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.davidyu.her.Adapters.PagerAdapter;
import com.example.davidyu.her.Authenticator.LoginActivity;
import com.example.davidyu.her.Authenticator.UserLocalStore;
import com.example.davidyu.her.Fragments.MainSlidingDailyTipsTabFragment;
import com.example.davidyu.her.Fragments.NavigationDrawerFragment;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Views.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    ViewPager pager;
    SlidingTabLayout tabs;
    UserLocalStore userLocalStore;
    //TODO: set up pull down to refresh function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment navigationdrawerfragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);
        navigationdrawerfragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(),this));
        tabs=(SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabView(R.layout.fragment_sliding_tab, R.id.position2);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorHighlight);
            }
        });
        tabs.setBackgroundColor(getResources().getColor(R.color.primaryColorLight));
        tabs.setViewPager(pager);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //check if user is logged in
    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == false){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    //helper function to check if user is logged in
    private boolean authenticate(){
        return userLocalStore.isUserLoggedIn();
    }

    public void updateTipList(){
        pager.setCurrentItem(0);


    }
}
