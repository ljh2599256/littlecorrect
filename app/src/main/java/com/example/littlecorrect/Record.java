package com.example.littlecorrect;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Record extends Activity implements View.OnClickListener{

    public static String x="";
    String[] data = new String[100];
    int count=0;
    static String str="";
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            str = String.valueOf(msg.obj);
            super.handleMessage(msg);

            Type type = new TypeToken<List<Graph>>(){}.getType();
            Gson gson = new Gson();
            List<Graph> gg = gson.fromJson(str, type);
            String[] data = new String[100];
            int count=0;
            for(Graph g :gg) {
                data[count]="";
                data[count]="问题id: "+g.getgraphname()+"       科目： "+g.getclasstype();
                if(g.getlooked()==1)
                {
                    data[count]=data[count]+"     已批阅";
                }
                else
                {
                    data[count]=data[count]+"     未批阅";
                }
                data[count]=data[count]+"\n"+g.getcomment();
                count++;
            }
            String[] ee=new String[count];
            for(int i=0;i<count;i++)
            {
                ee[i]=data[i];
            }
            final ListView listView = (ListView) findViewById(R.id.listview);//在视图中找到ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Record.this,android.R.layout.simple_list_item_1,ee);//新建并配置ArrayAapeter
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {
                    Object o = listView.getItemAtPosition(p);
//                    Intent intent = new Intent();
//                    intent.setClass(Record.this, TchLogin.class);
//                    startActivity(intent);
//                    MainActivity.this.finish();
            }
            });

         };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        ActionBar actionBar = getActionBar();
        int color = Color.parseColor("#66CCFF");
        ColorDrawable drawable = new ColorDrawable(color);
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setDisplayShowHomeEnabled(false);

//        if (getActionBar() != null){
//            getActionBar().hide();
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
        get();
    }

    public void get(){

        Gson gson = new Gson();
        User u = new User();
        u.setid(MainActivity.login_id);
        String param = gson.toJson(u);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody r = RequestBody.create(JSON, param);
        Request request = new Request.Builder()
                .url("http://192.168.163.24:8080/TestTomcat8/Getcomment")
                .post(r)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call=okHttpClient.newCall(request);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responsedata = response.body().string();
                String x=responsedata;
                Message msg=new Message();
                msg.obj=x;
                handler.sendMessage(msg);
            }
        });
    }
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnstudent) {

        }
    }

}

