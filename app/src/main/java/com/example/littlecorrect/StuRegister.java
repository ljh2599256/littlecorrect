package com.example.littlecorrect;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class StuRegister extends Activity {
    EditText evid;
    EditText evstuid;
    EditText check1;
    String TAG = "StuRegister";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_register);
        ActionBar actionBar = getActionBar();
        int color = Color.parseColor("#66CCFF");
        ColorDrawable drawable = new ColorDrawable(color);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setDisplayShowHomeEnabled(false);
        MainActivity.setCustomActionBar(this, R.layout.login_title);
        Button sendRequest = (Button) findViewById(R.id.btnregister);
        evid = (EditText) findViewById(R.id.etid);
        evstuid = (EditText) findViewById(R.id.etpwd);
        check1 = (EditText)findViewById(R.id.etpwdcheck);

    }
    public void send(View view)
    {
        final String sendid=evid.getText().toString();
        final String sendpwd=evstuid.getText().toString();
        final String check=check1.getText().toString();
        if(sendid.equals(""))
        {
            Toast.makeText(StuRegister.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(sendpwd.equals(""))
        {
            Toast.makeText(StuRegister.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!sendpwd.equals(check))
        {
            Toast.makeText(StuRegister.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        Gson gson=new Gson();
        User user = new User();
        user.setid(sendid);
        user.setpassword(sendpwd);
        String param=gson.toJson(user);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody r =RequestBody.create(JSON,param);
        Request request = new Request.Builder()
                .url(String.format(getString(R.string.ip))+"StudentRegist")
                .post(r)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsedata = response.body().string();
                System.out.println(responsedata);
                if(responsedata.equals("true"))
                {
                    MainActivity.login_id=sendid;
                    Intent intent = new Intent();
                    intent.setClass(StuRegister.this, StuMain.class);
                    startActivity(intent);
                    StuRegister.this.finish();
                }
                else {
                    Looper.prepare();
                    Toast.makeText(StuRegister.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    //showResponse(responsedata);
                }

            }
        });
    }
    /*private void showResponse(final String response)
    {
        final TextView responseText=(TextView)findViewById(R.id.textView);
        runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });*/
}