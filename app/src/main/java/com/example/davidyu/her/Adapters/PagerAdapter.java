package com.example.davidyu.her.Adapters;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Display;
import android.view.WindowManager;

import com.example.davidyu.her.Fragments.MainSlidingDailyTipsTabFragment;
import com.example.davidyu.her.Fragments.MainSlidingGiftsTabFragment;
import com.example.davidyu.her.Fragments.MainSlidingTimelineTabFragment;
import com.example.davidyu.her.R;

public class PagerAdapter extends FragmentStatePagerAdapter {
    protected Context main;

    MainSlidingDailyTipsTabFragment mst0;
    MainSlidingGiftsTabFragment mst1;
    MainSlidingTimelineTabFragment mst2;

    public PagerAdapter(android.support.v4.app.FragmentManager fm, Context main) {
        super(fm);
        this.main = main;
    }

    public static int icons[] = {R.drawable.tip, R.drawable.gift, R.drawable.line};
    ImageSpan imageSpan = null;
    SpannableString spannablestring;

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        mst0 = MainSlidingDailyTipsTabFragment.getInstance(position);
        mst1 = MainSlidingGiftsTabFragment.getInstance(position);
        mst2 = MainSlidingTimelineTabFragment.getInstance(position);
        if (position == 0)
            return mst0;
        else if (position == 1)
            return mst1;
        else if(position == 2)
            return mst2;
        else
            return null;
    }


    public CharSequence getPageTitle(int position){//returns title at given position
        WindowManager wm = (WindowManager) main.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (size.x)/15;
        Drawable drawable = main.getResources().getDrawable(icons[position]);
        drawable.setBounds(0,0,width,width);
        imageSpan=new ImageSpan(drawable);
        spannablestring = new SpannableString(" ");
        spannablestring.setSpan(imageSpan, 0, spannablestring.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannablestring;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
