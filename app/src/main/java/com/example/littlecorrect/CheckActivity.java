package com.example.littlecorrect;

import android.app.Activity;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

public class CheckActivity extends Activity {
    EditText checklog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        if (getActionBar() != null){
            getActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        checklog=(EditText)findViewById(R.id.EVBack);
    }
    public void upload(View view)
    {
        String check=checklog.getText().toString();
        Graph graph=new Graph();
        Gson gson=new Gson();
        graph.setcomment(check);
        graph.setteaid(MainActivity.login_id);
        String param=gson.toJson(graph);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody r =RequestBody.create(JSON,param);
        Request request = new Request.Builder()
                .url(String.format(getString(R.string.ip))+"Comment")
                .post(r)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(CheckActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
                //showResponse(responsedata);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsedata = response.body().string();
                System.out.println(responsedata);
                if(responsedata.equals("true"))
                {
                    Looper.prepare();
                    Toast.makeText(CheckActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    //showResponse(responsedata);
                }
                else {
                    Looper.prepare();
                    Toast.makeText(CheckActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    //showResponse(responsedata);
                }

            }
        });
    }
}