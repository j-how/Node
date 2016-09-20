package com.djangohow.udacity.utils;

import com.djangohow.udacity.vo.MovieBean;
import com.udacity.bean.BeanMovie;

/**
 * Created by django on 2016/8/28.
 */
public class MessageEvent {
    public String url;
    public BeanMovie mBean;

    public MessageEvent(String url, BeanMovie movieBean) {
        this.url = url;
        mBean = movieBean;
    }
}
