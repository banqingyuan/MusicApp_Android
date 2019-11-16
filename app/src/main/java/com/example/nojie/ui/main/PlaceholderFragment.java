package com.example.nojie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.nojie.IndexActivity;
import com.example.nojie.MainActivity;
import com.example.nojie.R;
import com.example.nojie.utility.ScreenUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public int index;
    private View view;
    //是否可见
    public boolean isVisible = false;
    //是否初始化完成
    public boolean isInit = false;
    //是否已经加载过
    public boolean isLoadOver = false;

    //界面可见时再加载数据(该方法在onCreate()方法之前执行。)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisible = isVisibleToUser;
        setParam();
    }
    private final Animation translateAnimation = new TranslateAnimation(0,  - 250, 0, -180);//设置平移的起点和终点

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public PlaceholderFragment newInstance(int index) {
        this.index=index;
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);

        }
        pageViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onChanged(@Nullable String s) {

                ImageView frontview=view.findViewById(R.id.front);
                frontview.setImageResource(R.drawable.img03);
                frontview.setAlpha((float) 0.58);
                isInit = true;
                setParam();
                final TextView textView = view.findViewById(R.id.WelcomeText);
                switch (s){
                    case "Hello world from section: 1":
                        textView.setText("AI辅助作曲");
                        break;
                    case "Hello world from section: 2":
                        textView.setText("智能匹配填词");
                        break;
                    case "Hello world from section: 3":
                        textView.setText("原创作品任你唱");
                        frontview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setClass(view.getContext(), IndexActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                }
            }
        });
        return view;
    }
    private void setParam() {
        if (isInit && !isLoadOver && isVisible) {
            isLoadOver = true;
            setDates();
        }
    }
    /**
     * 在这里写请求网络等逻辑代码
     */
    private void setDates() {
        final ImageView imageView = view.findViewById(R.id.WelcomeOne);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onChanged(@Nullable String s) {
                switch (s){
                    case "Hello world from section: 1":
                        Glide.with(getContext())
                                .load(R.drawable.img1)
                                .into(imageView);
                        break;
                    case "Hello world from section: 2":
                        Glide.with(getContext())
                                .load(R.drawable.img2)
                                .into(imageView);
                        break;
                    case "Hello world from section: 3":
                        Glide.with(getContext())
                                .load(R.drawable.img3)
                                .into(imageView);
                        break;
                }


                translateAnimation.setDuration(2000);//动画持续的时间为1s
                translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                translateAnimation.setFillAfter(true);//不回到起始位置
                //如果不添加setFillEnabled和setFillAfter则动画执行结束后会自动回到远点
                translateAnimation.setAnimationListener(new Animation.AnimationListener(){

                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    public void onAnimationEnd(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                });
                imageView.setAnimation(translateAnimation);//给imageView添加的动画效果
                translateAnimation.startNow();//动画开始执行 放在最后即可
            }
        });
    }
}