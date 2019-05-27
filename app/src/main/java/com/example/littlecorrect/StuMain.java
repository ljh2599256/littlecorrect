package com.example.littlecorrect;

import android.app.ActionBar;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.littlecorrect.R;

public class StuMain extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_main);


        if (getActionBar() != null){
            getActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(Fragment1.class,
                        "上传",
                        R.drawable.item1_before,
                        R.drawable.item1_after)
                .addItem(Fragment2.class,
                        "作业",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(Fragment3.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();

    }
}
