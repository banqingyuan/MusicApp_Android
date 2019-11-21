package com.example.nojie.fragment.Personal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.nojie.R;

import static com.example.nojie.fragment.Personal.fragment_personal.ClipSquareBitmap;

public class activity_personal_setting extends Activity {

    private ImageView icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);

        initContent();
    }

    private void initContent() {
        icon = findViewById(R.id.personal_setting_icon_view);
    }

    private void initData(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        //设置bitmap.getWidth()可以获得圆形
        Bitmap bitmap1 = ClipSquareBitmap(bitmap,200,bitmap.getWidth());
        icon.setImageBitmap(bitmap1);
    }
}
