package com.example.littlecorrect;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Looper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TchLogin extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tch_login);
        ActionBar actionBar = getActionBar();
        int color = Color.parseColor("#66CCFF");
        ColorDrawable drawable = new ColorDrawable(color);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setDisplayShowHomeEnabled(false);
        MainActivity.setCustomActionBar(this, R.layout.login_title);
        Button sendRequest=(Button)findViewById(R.id.btnlogin);
        sendRequest.setOnClickListener((View.OnClickListener) this);
        Button sendRequest2=(Button)findViewById(R.id.btnregister);
        sendRequest2.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnlogin)
        {
            log();
        }
        if(v.getId()==R.id.btnregister)
        {
            skip(v);
        }
    }

    private void log(){

        EditText id=(EditText)findViewById(R.id.etid);
        EditText pwd=(EditText)findViewById(R.id.etpwd);
        final String sendid=id.getText().toString();
        final String sendpwd=pwd.getText().toString();
        Gson gson=new Gson();
        Teacher tech = new Teacher();
        tech.setteaid(sendid);
        tech.setpassword(sendpwd);
        String param=gson.toJson(tech);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody r =RequestBody.create(JSON,param);
        Request request = new Request.Builder()
                .url(String.format(getString(R.string.ip))+"TeacherLogin")
                .post(r)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsedata = response.body().string();
                if(responsedata.equals("true"))
                {
                      MainActivity.login_id=sendid;
                    Intent intent = new Intent();
                    intent.setClass(TchLogin.this, TchMain.class);
                    startActivity(intent);
                    //TchLogin.this.finish();
                }
                else
                {
                    Looper.prepare();
                    Toast.makeText(TchLogin.this,responsedata,Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
    }
    public void skip(View view){
        Intent intent = new Intent();
        intent.setClass(TchLogin.this, TchRegister.class);
        startActivity(intent);
        TchLogin.this.finish();
    }
}
