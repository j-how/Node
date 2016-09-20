package com.djangohow.udacity.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by django on 2016/9/13.
 */
public class NetUtils {
    public static OkHttpClient client = new OkHttpClient();
    public static String getString(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }else {
                Log.i("tag", "getString: "+ response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
