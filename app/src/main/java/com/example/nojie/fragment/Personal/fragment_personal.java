package com.example.nojie.fragment.Personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nojie.R;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class fragment_personal extends Fragment {

    private View root;
    private TitleBar titleBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_personal, container, false);
        initContentView(root);
        initData();
        return root;
    }
    private void initContentView(View root) {
        titleBar = root.findViewById(R.id.personal_titleBar);
    }
    private void initData() {
            titleBar.setLeftIcon(R.drawable.message);
            titleBar.setRightIcon(R.drawable.set_up);
            titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                @Override
                public void onLeftClick(View v) {

                }

                @Override
                public void onTitleClick(View v) {

                }

                @Override
                public void onRightClick(View v) {

                }
            });
    }


}
