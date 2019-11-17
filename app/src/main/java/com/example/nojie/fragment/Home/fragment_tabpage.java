package com.example.nojie.fragment.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.nojie.R;
import com.example.nojie.fragment.Home.map.fragment_map;
import com.example.nojie.fragment.Home.square.fragment_square;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class fragment_tabpage extends Fragment {

    private View viewContent;
    private TabLayout my_tablayout;
    private ViewPager my_viewpager;
    public static fragment_tabpage myFragment;

    private int mode = TabLayout.MODE_FIXED;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.fragment_tab,container,false);
        initConentView(viewContent);
        initData();
        return viewContent;
    }
    public void initConentView(View viewContent) {
        this.my_tablayout = (TabLayout) viewContent.findViewById(R.id.my_tablayout);
        this.my_viewpager = (ViewPager) viewContent.findViewById(R.id.my_viewpager);
    }
    public void initData() {
        //创建一个viewpager的adapter
        MyTabFragmentAdapter adapter = new MyTabFragmentAdapter(getFragmentManager());
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new fragment_inspiration());
        fragments.add(new fragment_map());
        fragments.add(new fragment_square());
        String[] titlesArr = {"灵感", "探索","广场"};

        adapter.setTitlesArr(titlesArr);
        adapter.setFragments(fragments);
        this.my_viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.my_tablayout.setupWithViewPager(this.my_viewpager);
        my_tablayout.setTabMode(mode);
    }
}
