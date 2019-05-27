package com.example.littlecorrect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.littlecorrect.Fragment2.handler;

public class comment extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        TextView textview1=(TextView)findViewById(R.id.tvteaid);
        textview1.setText(MainActivity.teaid);
        TextView textview2=(TextView)findViewById(R.id.tvcomment);
        textview2.setText(MainActivity.comment);
       // ImageView imageView = (ImageView)findViewById(R.id.Imgcheck);
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap) msg.obj;
                ImageView imageView = (ImageView)findViewById(R.id.image1);
                imageView.setImageBitmap(bitmap);//将图片的流转换成图片
            }
        };
            OkHttpClient okHttpClient = new OkHttpClient();
            //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
            Request request = new Request.Builder()
                    .url("http://39.96.172.141:80/TestTomcat8/images/"+MainActivity.pname)
                    .build();
            //3.创建一个Call对象，参数是request对象，发送请求
            Call call = okHttpClient.newCall(request);
            //4.异步请求，请求加入调度
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Message msg = new Message();
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                }
            });



    }

}








