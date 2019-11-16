package com.example.nojie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nojie.R;
import com.example.nojie.fragment.Home.fragment_inspiration;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class fragment_article extends Fragment {

    private TitleBar titleBar;
    private View root;
    private Fragment currentFragment = new Fragment();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("chakan"+"-->onCreatView");
        root = inflater.inflate(R.layout.fragment_article,container,false);


        initConentView(root);
        initData();
        return root;
    }
    private void initConentView(View root) {
        titleBar = root.findViewById(R.id.article_title);
    }
    private void initData() {
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new fragment_inspiration()).commit();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }
    private void showFragment(Fragment fragment){
        FragmentManager manager=getFragmentManager();
        if (currentFragment != fragment){//  判断传入的fragment是不是当前的currentFragmentgit
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);//  不是则隐藏
            currentFragment = fragment;  //  然后将传入的fragment赋值给currentFragment
            if (!fragment.isAdded()){ //  判断传入的fragment是否已经被add()过
                transaction.add(R.id.nav_host_fragment,fragment).show(fragment).commit();
            }else{
                transaction.show(fragment).commit();
            }
        }
    }

}
