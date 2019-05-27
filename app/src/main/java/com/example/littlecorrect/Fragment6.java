package com.example.littlecorrect;


import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment6 extends Fragment {

    EditText Name;
    EditText Birth;
    EditText School;
    EditText Interest;
    EditText Subject;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment6, container, false);
        Name=root.findViewById(R.id.etname);
        Birth=root.findViewById(R.id.etbirth);
        School=root.findViewById(R.id.etschool);
        Interest=root.findViewById(R.id.etinterest);
        Subject=root.findViewById(R.id.etsubject);
        Button Sure=root.findViewById(R.id.btnsure);
        Sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Gson gson = new Gson();
                Teacher teacher = new Teacher();
                teacher.setteaname(Name.getText().toString());
                teacher.setbirthday(Birth.getText().toString());
                teacher.setteaschool(School.getText().toString());
                teacher.setteaid(MainActivity.login_id);
                teacher.setclasstype(Interest.getText().toString());

                String param = gson.toJson(teacher);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody r = RequestBody.create(JSON, param);
                //2.创建Request.Builder对象，设置参数，请求方式如果是Get，就不用设置，默认就是Get
                Request request = new Request.Builder()
                        .url(String.format(getString(R.string.ip))+"TeacherUpdate")
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
                        if(response.equals("true"))
                        {
                            Looper.prepare();
                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        else {

                            Looper.prepare();
                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            //showResponse(responsedata);
                        }

                    }
                });
                root.findViewById(R.id.etid).setFocusable(false);
                root.findViewById(R.id.etid).setFocusableInTouchMode(false);
                root.findViewById(R.id.etname).setFocusable(false);
                root.findViewById(R.id.etname).setFocusableInTouchMode(false);
                root.findViewById(R.id.etbirth).setFocusable(false);
                root.findViewById(R.id.etbirth).setFocusableInTouchMode(false);
                root.findViewById(R.id.etinterest).setFocusable(false);
                root.findViewById(R.id.etinterest).setFocusableInTouchMode(false);
                root.findViewById(R.id.etschool).setFocusable(false);
                root.findViewById(R.id.etschool).setFocusableInTouchMode(false);
                root.findViewById(R.id.btnsure).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.btnchange).setVisibility(View.VISIBLE);

            }

        });
        root.findViewById(R.id.btnchange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.findViewById(R.id.etid).setFocusable(true);
                root.findViewById(R.id.etid).setFocusableInTouchMode(true);
                root.findViewById(R.id.etid).requestFocus(View.FOCUS_RIGHT);
                root.findViewById(R.id.etsubject).setFocusable(true);
                root.findViewById(R.id.etsubject).setFocusableInTouchMode(true);
                root.findViewById(R.id.etname).setFocusable(true);
                root.findViewById(R.id.etname).setFocusableInTouchMode(true);
                root.findViewById(R.id.etbirth).setFocusable(true);
                root.findViewById(R.id.etbirth).setFocusableInTouchMode(true);
                root.findViewById(R.id.etinterest).setFocusable(true);
                root.findViewById(R.id.etinterest).setFocusableInTouchMode(true);
                root.findViewById(R.id.etschool).setFocusable(true);
                root.findViewById(R.id.etschool).setFocusableInTouchMode(true);
                root.findViewById(R.id.btnchange).setVisibility(View.INVISIBLE);
                root.findViewById(R.id.btnsure).setVisibility(View.VISIBLE);
            }
        });
        return root;

    }
}