package com.example.littlecorrect;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class TchMain extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tch_main);

        if (getActionBar() != null){
            getActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(Fragment4.class,
                        "批改",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(Fragment5.class,
                        "记录",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(Fragment6.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();

    }
}
