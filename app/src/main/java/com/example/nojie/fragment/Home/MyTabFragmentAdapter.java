package com.example.nojie.fragment.Home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyTabFragmentAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = "MyTabFragmentAdapter";

    private String[] titleArray;
    private List<Fragment> listFragments;

    public MyTabFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        Log.d(TAG,"listFragments.size() = " + listFragments.size());
        return listFragments.size();
    }
    public void addFragment(Fragment fragment){
        this.listFragments.add(fragment);
    }

    public void setFragments(List<Fragment> fragments){
        this.listFragments = fragments;
    }

    //解决了一个TabTitle不显示的问题
    //见博文https://www.cnblogs.com/neillee/p/7001976.html
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArray[position];
    }

    public void setTitlesArr (String[] titlesArr) {
        this.titleArray = titlesArr;
    }
}
