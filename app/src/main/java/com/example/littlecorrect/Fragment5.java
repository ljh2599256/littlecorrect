package com.example.littlecorrect;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Fragment5 extends Fragment{

    public static String x="";
    String[] data = new String[100];
    int count=0;
    static String str="";
    public static Handler handler;

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment5,container,false);

        get();
        final ListView listView = (ListView)root.findViewById(R.id.listview);//在视图中找到ListView

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {

                str = String.valueOf(msg.obj);
                super.handleMessage(msg);

                Type type = new TypeToken<List<Graph>>(){}.getType();
                Gson gson = new Gson();
                final List<Graph> gg = gson.fromJson(str, type);
                String[] data = new String[100];
                int count=0;
                for(Graph g :gg) {
                    data[count]="";
                    data[count]="问题id: "+g.getgraphname()+"\n科目： "+g.getclasstype();
                    if(g.getlooked()==1)
                    {
                        data[count]=data[count]+"     已批阅";
                    }
                    else {
                        data[count] = data[count] + "     未批阅";
                    }
                    count++;
                }
                String[] ee=new String[count];
                for(int i=0;i<count;i++)
                {
                    ee[i]=data[i];
                    System.out.println(ee[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ee);//新建并配置ArrayAapeter
                listView.setAdapter(adapter);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {
//
//                        Graph graph=gg.get(p);
//                        MainActivity.teaid=graph.getteaid();
//                        MainActivity.comment=graph.getcomment();
//                        Intent intent=new Intent();
//                        intent.setClass(getActivity(), comment.class);
//                        startActivity(intent);
//                    }
//                });

            }
        };
        return root;
    }


    public void get(){

        Gson gson = new Gson();
        Teacher t = new Teacher();
        t.setteaid(MainActivity.login_id);
        String param = gson.toJson(t);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody r = RequestBody.create(JSON, param);
        Request request = new Request.Builder()
                .url(String.format(getString(R.string.ip))+"Getcommentt")
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



}


