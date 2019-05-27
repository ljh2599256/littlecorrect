package com.example.littlecorrect;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.ContextCompat.getDataDir;
import static android.support.v4.content.ContextCompat.getExternalCacheDirs;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


public class Fragment1 extends Fragment {
    public static final int TAKE_PHOTO = 1;
    private static final String TAG = "pictureActivity" ;
    private ImageView picture;
    private Uri iamgeUri;
    private File ok;
    String Subject="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment1, container, false);

        getPermission();
        RadioButton Chinese=root.findViewById(R.id.rdobtn1);
        RadioButton Math=root.findViewById(R.id.rdobtn2);
        RadioButton English=root.findViewById(R.id.rdobtn3);
        RadioGroup rg = root.findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(R.id.rdobtn1 == checkedId){
                    Subject="Chinese";
                    Toast.makeText(getActivity(), "您选择了语文", Toast.LENGTH_SHORT).show();
                }
                else if(R.id.rdobtn2 == checkedId){
                   Subject="Math";
                    Toast.makeText(getActivity(), "您选择了数学", Toast.LENGTH_SHORT).show();
                }
                else if(R.id.rdobtn3 == checkedId){
                    Subject="English";
                    Toast.makeText(getActivity(), "您选择了英语", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageButton takePhoto = root.findViewById(R.id.imgbtncamera);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage = new File(getActivity().getExternalCacheDir(),"output_image.jpg");
                ok = outputImage;
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    iamgeUri = FileProvider.getUriForFile(getActivity(), "com.example.cameraalbumtest.fileprovider", outputImage);
                } else {
                    iamgeUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, iamgeUri);
                startActivityForResult(intent, TAKE_PHOTO);


            }

        });

        return root;
    }


        public void getPermission(){
            if (Build.VERSION.SDK_INT > 22) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //先判断有没有权限 ，没有就在这里进行权限的申请
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    //说明已经获取到摄像头权限了
                    Log.i("pictureActivity", "已经获取了权限");
                }
            } else {
//这个说明系统版本在6.0之下，不需要动态获取权限。
                Log.i("pictureActivity", "这个说明系统版本在6.0之下，不需要动态获取权限。");
            }
        }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {

                        //Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(iamgeUri));
                        //picture.setImageBitmap(bitmap);
                        upload();
                        //将图片解析成Bitmap对象，并把它显现出来

                }
                break;
            default:
                break;
        }
    }
    public void upload()
    {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);


        if(ok != null){
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), ok);
            String filename = ok.getName();
            Graph graph=new Graph();
            requestBody.addFormDataPart("file", filename, body).addFormDataPart(MainActivity.login_id,Subject);
        }
        Request request = new Request.Builder().url(String.format(getString(R.string.ip)) +"GetMap").post(requestBody.build()).build();

        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });


    }

}
