package com.example.nojie.fragment.Personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.example.nojie.Myapp;
import com.example.nojie.R;
import com.example.nojie.model.Current_User;
import com.example.nojie.utility.SendRequest;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class Login_Acitvity extends Activity {


    private TitleBar login_TitleBar;
    private Button login_button;
    private Button register_button;
    private EditText userName_edit;
    private EditText password_edit;
    private Myapp myapp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        initLoginContentView();
        initLoginData();
    }
    private void initLoginContentView() {
        myapp = (Myapp)getApplication();
        login_TitleBar = findViewById(R.id.personal_login_titleBar);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        userName_edit = findViewById(R.id.username_edit);
        password_edit = findViewById(R.id.password_edit);

    }

    private void initLoginData() {
        if(myapp.getLogin_status()){
            login_TitleBar.setOnTitleBarListener(new OnTitleBarListener() {
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
        }
        login_TitleBar.setTitle("用户登录");

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        final String[] login_response = new String[1];
        final int[] tag = {0};
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = userName_edit.getText().toString();
                final String password = password_edit.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            SendRequest sendRequest = new SendRequest("/login/in?userName="+ userName +"&pwd="+password);
                            login_response[0] = sendRequest.sendRequest();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        tag[0] =1;
                    }
                }).start();
                while (tag[0]!=1){}
                if (login_response[0].equals("wrong_password")) {
                    Toast.makeText(getApplicationContext(),"密码错误请重新登录",Toast.LENGTH_SHORT).show();
                }else if(login_response[0].equals("error")) {
                    Toast.makeText(getApplicationContext(),"出现错误请稍后重试",Toast.LENGTH_SHORT).show();
                }else if (!login_response[0].isEmpty()){
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    myapp.setLogin_status(true);
                    JSONObject user = JSONObject.parseObject(login_response[0]);
                    myapp.setUserName((String)user.get("userName"));
                    myapp.setUserId((int)user.get("userId"));
                    myapp.setAge((int)user.get("age"));
                    myapp.setBeWatched((String)user.get("beWatched"));
                    myapp.setBirthday((String)user.get("birthday"));
                    myapp.setCollectList((String)user.get("collectList"));
                    myapp.setIconUrl((String) user.get("iconUrl"));
                    myapp.setLocation((String) user.get("location"));
                    myapp.setSex((String)user.get("sex"));
                    myapp.setSign((String)user.get("sign"));
                    myapp.setTag((String)user.get("tag"));
                    myapp.setVerify((String)user.get("verify"));
                    myapp.setWatch((String)user.get("watch"));
                    myapp.setWorkList((String)user.get("workList"));
                    finish();
                }
            }
        });
    }
}
