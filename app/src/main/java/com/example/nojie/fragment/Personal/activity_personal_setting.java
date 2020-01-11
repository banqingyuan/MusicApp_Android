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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        initContent();
        initData();
    }

    private void initContent() {
        icon = findViewById(R.id.personal_setting_icon_view);
        titleBar = findViewById(R.id.personal_setting_titleBar);
        subButton = findViewById(R.id.personal_setting_submit);
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
                            SendRequest sendRequest = new SendRequest("/pull_user");
                            String response = sendRequest.sendRequest();
                            parseJSONWithJSONObject(response);//调用json解析的方法
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);//新建json对象实例
            JSONObject jsonObject1 = jsonObject.getJSONObject("section");
            String name = jsonObject1.getString("id");//取得其名字的值，一般是字符串

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
