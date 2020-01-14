package com.example.nojie.fragment.Personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.nojie.MyIndexRecyclerViewAdapter;
import com.example.nojie.Myapp;
import com.example.nojie.R;
import com.example.nojie.fragment.Article.activity_article;
import com.example.nojie.utility.SendRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class fragment_personal extends Fragment {

    private View root;
    private TitleBar titleBar;
    private ImageView icon;
    private TextView username;
    private TextView usersign;
    private RecyclerView recyclerView;
    private List<personal_action> myDataset = new ArrayList<>();

    private Myapp myapp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myapp = (Myapp)getActivity().getApplication();
        root = inflater.inflate(R.layout.fragment_personal, container, false);
        initContentView(root);
        initData();
        if(!myapp.getLogin_status()) {
            Intent intent = new Intent(getActivity(), Login_Acitvity.class);
            startActivity(intent);
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        int i = 3000;
        for(int j=0;j<i;j++){

        }
        if(!myapp.getLogin_status()) {
            Intent intent = new Intent(getActivity(), Login_Acitvity.class);
            startActivity(intent);
        }
    }

    private void initContentView(View root) {
        titleBar = root.findViewById(R.id.personal_titleBar);
        icon = root.findViewById(R.id.person_icon);
        username = root.findViewById(R.id.personal_user_name);
        usersign = root.findViewById(R.id.personal_user_sign);
        recyclerView = root.findViewById(R.id.personal_recycler_view);
    }
    private void initData() {

        personal_action personal_action1 = new personal_action();
        personal_action personal_action2 = new personal_action();

        personal_action1.setAction_type("发表了评论：");
        personal_action2.setAction_type("发表了新作：");

        personal_action1.setContent_text("宝哥归来，苏曼奥运中国团举杯");
        personal_action2.setContent_text("陌路之魂皆可以爱相期");

        myDataset.add(personal_action1);
        myDataset.add(personal_action2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyPersonalRecyclerViewAdapter adapter = new MyPersonalRecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerItemClickLitener(new MyPersonalRecyclerViewAdapter.onRecyclerItemClickLitener() {
            @Override
            public void onRecyclerItemClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部点击"+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRecyclerItemLongClick(RecyclerView.ViewHolder view, int position) {
                Toast.makeText(getContext(), "内部长按"+position, Toast.LENGTH_SHORT).show();
            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        //设置bitmap.getWidth()可以获得圆形
        Bitmap bitmap1 = ClipSquareBitmap(bitmap,200,bitmap.getWidth());
        icon.setImageBitmap(bitmap1);
        username.setText("单纯不做作的我");
        usersign.setText("国家一级赖床运动员");

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
                    Intent intent = new Intent(getActivity(), activity_personal_setting.class);
                    startActivity(intent);
                }
            });
    }
    //将ImageView中的图片传入并输出圆形bitmap
    public static Bitmap ClipSquareBitmap(Bitmap bmp, int width, int radius) {
        if (bmp == null || width <= 0)
            return null;
        //如果图片比较小就没必要进行缩放了

        /**
         * 把图片进行缩放，以宽高最小的一边为准，缩放图片比例
         * */
        if (bmp.getWidth() > width && bmp.getHeight() > width) {
            if (bmp.getWidth() > bmp.getHeight()) {
                bmp = Bitmap.createScaledBitmap(bmp, (int) (((float) width) * bmp.getWidth() / bmp.getHeight()), width, false);
            } else {
                bmp = Bitmap.createScaledBitmap(bmp, width, (int) (((float) width) * bmp.getHeight() / bmp.getWidth()), false);
            }

        } else {
            width = bmp.getWidth() > bmp.getHeight() ? bmp.getHeight() : bmp.getWidth();
            Log.d("zeyu","宽" + width + ",w" + bmp.getWidth() + ",h" + bmp.getHeight());
            if (radius > width) {
                radius = width;
            }
        }
        Bitmap output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        //设置画笔全透明
        canvas.drawARGB(0, 0, 0, 0);
        Paint paints = new Paint();
        paints.setColor(Color.WHITE);
        paints.setAntiAlias(true);//去锯齿
        paints.setFilterBitmap(true);
        //防抖动
        paints.setDither(true);

        //把图片圆形绘制矩形
        if (radius <= 0)
            canvas.drawRect(new Rect(0, 0, width, width), paints);
        else //绘制圆角
            canvas.drawRoundRect(new RectF(0, 0, width, width), radius, radius, paints);
        // 取两层绘制交集。显示前景色。
        paints.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect = new Rect();
        if (bmp.getWidth() >= bmp.getHeight()) {
            rect.set((bmp.getWidth() - width) / 2, 0, (bmp.getWidth() + width) / 2, width);
        } else {
            rect.set(0, (bmp.getHeight() - width) / 2, width, (bmp.getHeight() + width) / 2);
        }
        Rect rect2 = new Rect(0, 0, width, width);
        //第一个rect 针对bmp的绘制区域，rect2表示绘制到上面位置
        canvas.drawBitmap(bmp, rect, rect2, paints);
        bmp.recycle();
        return output;
    }
}
