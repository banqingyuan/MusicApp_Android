package com.example.nojie.fragment.Personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.nojie.R;
import com.example.nojie.utility.SendRequest;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class RegisterActivity extends Activity {

    private EditText userName_edit;
    private EditText password_edit;
    private EditText password_Confirm_Edit;
    private Button subbmit_button;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_register);
        initView();
        initData();
    }

    private void initView() {
        userName_edit = findViewById(R.id.register_username_edit);
        password_edit = findViewById(R.id.register_password_edit);
        password_Confirm_Edit = findViewById(R.id.register_password_confirm_edit);
        subbmit_button = findViewById(R.id.register_submit_button);
        titleBar = findViewById(R.id.personal_register_titleBar);
    }
    private void initData(){
        final Integer[] tag = {0};
        final String[] register_response = new String[1];
        subbmit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = userName_edit.getText().toString();
                final String password = password_edit.getText().toString();
                final String password_Confirm = password_Confirm_Edit.getText().toString();
                if(password.equals(password_Confirm)){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                SendRequest sendRequest = new SendRequest("/register?userName="+ userName +"&password="+password);
                                register_response[0] = sendRequest.sendRequest();
                                tag[0] = 1;
//                                Toast.makeText(getApplicationContext(),"用户名已被使用",Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    while (tag[0]!=1){ }
                    if(register_response[0].equals("success")){
                        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else if(register_response[0].equals("exist")){
                        Toast.makeText(getApplicationContext(),"用户名已存在",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"密码输入不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
    }
}
