package com.example.nojie.fragment.Home.square;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.nojie.R;

public class activity_square_list extends Activity {

    private ImageView back;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_list);

        initContent();
        initView();
    }

    private void initContent() {
        back = findViewById(R.id.square_list_cross_button);
        confirm = findViewById(R.id.square_list_confirm);
    }

    private void initView() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread() {
                    public void run() {
                        try {
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        } catch (Exception e) {
                            Log.e("Exception when send",
                                    e.toString());
                        }
                    }
                }.start();
            }
        });
    }
}
