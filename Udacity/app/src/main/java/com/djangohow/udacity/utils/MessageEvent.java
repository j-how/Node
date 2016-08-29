package com.djangohow.udacity.utils;

import com.djangohow.udacity.vo.MovieBean;

/**
 * Created by django on 2016/8/28.
 */
public class MessageEvent {
    public String url;
    public MovieBean mBean;

    public MessageEvent(String url, MovieBean movieBean) {
        this.url = url;
        mBean = movieBean;
    }
}
