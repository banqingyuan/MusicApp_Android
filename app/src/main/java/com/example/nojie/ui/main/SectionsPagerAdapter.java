package com.example.nojie.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

import com.example.nojie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1};
    private final Context mContext;
    private List<PlaceholderFragment> mlist=new ArrayList<>();
    private PlaceholderFragment placeholderFragment1 = new PlaceholderFragment().newInstance(1);
    private PlaceholderFragment placeholderFragment2 = new PlaceholderFragment().newInstance(2);
    private PlaceholderFragment placeholderFragment3 = new PlaceholderFragment().newInstance(3);
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        System.out.println(position+1);
        mlist.add(placeholderFragment1);
        mlist.add(placeholderFragment2);
        mlist.add(placeholderFragment3);
        return mlist.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        // 通过这个可以生成无限多个滑动页面
        return 3;
    }
}