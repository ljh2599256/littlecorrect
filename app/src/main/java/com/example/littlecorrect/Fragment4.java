package com.example.littlecorrect;


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
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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



/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    ImageView imageView;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            imageView.setImageBitmap(bitmap);//将图片的流转换成图片
        }
    };


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment4, container, false);
        imageView = (ImageView) root.findViewById(R.id.Imgcheck);
        Button getPhoto = root.findViewById(R.id.Btnsearch);
        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Gson gson = new Gson();
                Teacher teacher = new Teacher();
                teacher.setteaid(MainActivity.login_id);
                String param = gson.toJson(teacher);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody r = RequestBody.create(JSON, param);
                //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
                Request request = new Request.Builder()
                        .url(String.format(getString(R.string.ip))+"SendMap")
                        .post(r)
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
                        getPicture(response.body().string());
                    }
                });

            }

        });
        Button upload = root.findViewById(R.id.Btnupload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stu = new Intent(getActivity(), CheckActivity.class);
                startActivity(stu);
                }


        });

        return root;
    }


    public void getPicture(String GetUrl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
        Request request = new Request.Builder()
                .url(GetUrl)
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
