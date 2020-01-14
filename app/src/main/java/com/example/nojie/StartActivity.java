package com.example.nojie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.mapcore2d.db;
import com.example.nojie.model.Current_User;
import com.example.nojie.utility.SendRequest;

import org.json.JSONObject;
import org.litepal.LitePal;

//每次开启App进入的第一个界面
public class StartActivity extends AppCompatActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final StartActivity view = this;

        View root = (FrameLayout)findViewById(R.id.StartActivity);

        TextView textView1= findViewById(R.id.StartText1);
        TextView textView2 = findViewById(R.id.StartText2);
        textView2.setVisibility(View.INVISIBLE);
        textView1.setText("无界音乐");
        textView1.setAlpha(1f);
        textView1.setVisibility(View.VISIBLE);
        textView1.animate()
                .alpha(0f)
                .setDuration(1000)
                .setListener(null)
                .setStartDelay(1000);

        textView2.setText("创作无界");
        textView2.setAlpha(0f);
        textView2.setVisibility(View.VISIBLE);
        textView2.animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(null)
                .setStartDelay(2000);

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(StartActivity.this, MainActivity.class);
            startActivity(intent);
                return true;
            }
        });
    }


}
