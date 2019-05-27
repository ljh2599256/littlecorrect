package com.example.littlecorrect;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    public static String teaid;
    public static String pname;
    public static String comment;
    public static String login_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(String.format(getString(R.string.ip)));
        if (getActionBar() != null){
            getActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        Button sendRequest1=(Button)findViewById(R.id.btnstudent);
        Button sendRequest2=(Button)findViewById(R.id.btnteacher);
        sendRequest1.setOnClickListener((View.OnClickListener) this);
        sendRequest2.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v) {


        if(v.getId()==R.id.btnstudent)
        {
            skip1(v);
        }
        if(v.getId()==R.id.btnteacher)
        {
            skip2(v);
        }

    }
    public void skip(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Record.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
    public void skip1(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, StuLogin.class);
        startActivity(intent);
        //MainActivity.this.finish();
    }
    public void skip2(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, TchLogin.class);
        startActivity(intent);
        //MainActivity.this.finish();
    }
    //把actionBar的文字标题居中
    public static void setCustomActionBar(Activity activity, int resource) {
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(activity).inflate(resource, null);
        ActionBar actionBar = activity.getActionBar();
        actionBar.setCustomView(mActionBarView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }

}
