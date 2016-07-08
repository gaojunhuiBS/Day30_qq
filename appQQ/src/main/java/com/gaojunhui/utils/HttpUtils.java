package com.gaojunhui.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/3.
 */
public class HttpUtils {
    //okHttp连接网络
    public static OkHttpClient okHttpClient;

    public interface MyCallback {
        void onFail();

        void onSuccess(String result);
    }

    static {
        okHttpClient = new OkHttpClient();
    }

    public static void doGet(final String urls, final MyCallback myCallback) {
//        useOkhttp(url, myCallback);
//        useUrlConnection(urls, myCallback);
        //创建网络请求
        Request request = new Request.Builder().url(urls).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //非UI线程
                //创建一个UI线程中的handler
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallback.onFail();
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Handler handler = new Handler(Looper.getMainLooper());

                if (response.code() == 200) {
                    final String result = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onSuccess(result);
                        }
                    });

                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallback.onFail();
                        }
                    });
                }
            }
        });
    }
//Http连接网络
    public static boolean isNetWorkConn (Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null){
            return info.isConnected();
        }else {
            return false;
        }
    }
    public static byte[] getDataFromHttp(String path){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            if (connection.getResponseCode()==200){
                InputStream is = connection.getInputStream();
                int temp=0;
                byte[] bs = new byte[1024];
                while ((temp=is.read(bs))!=-1){
                    baos.write(bs,0,temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }


}
