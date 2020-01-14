package com.example.nojie.fragment.Personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.nojie.Myapp;
import com.example.nojie.R;
import com.example.nojie.utility.SendRequest;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.nojie.fragment.Personal.fragment_personal.ClipSquareBitmap;

public class activity_personal_setting extends Activity {

    private ImageView icon;
    private TitleBar titleBar;
    private Button subButton;
    private Myapp myapp;
    private Button logout_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_setting);

        initContent();
        initData();
    }

    private void initContent() {
        myapp = (Myapp)getApplication();
        icon = findViewById(R.id.personal_setting_icon_view);
        titleBar = findViewById(R.id.personal_setting_titleBar);
        subButton = findViewById(R.id.personal_setting_submit);
        logout_btn = findViewById(R.id.personal_setting_logout);
    }

    private void initData(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        //设置bitmap.getWidth()可以获得圆形
        Bitmap bitmap1 = ClipSquareBitmap(bitmap,200,bitmap.getWidth());
        icon.setImageBitmap(bitmap1);

        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            SendRequest sendRequest = new SendRequest("/pull_user?userName="+myapp.getUserName());
                            String response = sendRequest.sendRequest();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            SendRequest sendRequest = new SendRequest("/login/out?userName="+ myapp.getUserName());
                            String reponse = sendRequest.sendRequest();
                            if(reponse.equals("true")){
                                myapp.setLogin_status(false);
                                myapp.setUserName(null);
                                myapp.setUserId(null);
                                finish();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
