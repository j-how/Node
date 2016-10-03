package com.djangohow.training.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by django on 2016/10/1.
 */
public class StockWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StockWidgetFactory(getApplicationContext(),intent);
    }
}
