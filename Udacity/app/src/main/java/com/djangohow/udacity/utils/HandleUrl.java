package com.djangohow.udacity.utils;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by django on 2016/8/28.
 */
public class HandleUrl {
    @NonNull
    public static String getString(String url) {
        URLConnection connection;
        InputStream is;
        InputStreamReader isr;
        String result = "";
        try {
            connection = new URL(url).openConnection();
            is = connection.getInputStream();
            isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line=br.readLine())!=null){
                result += line;
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
