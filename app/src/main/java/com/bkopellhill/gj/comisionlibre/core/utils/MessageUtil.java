package com.bkopellhill.gj.comisionlibre.core.utils;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import com.bitcodeing.framework.activities.BaseActivity;
import com.bitcodeing.framework.alert.Alerter;
import com.bitcodeing.framework.intefaces.OnHideAlertListener;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

@SuppressLint("StaticFieldLeak")
public class MessageUtil {

    private static BaseActivity context;
    private static int time = 3000;

    public static MessageUtil with(BaseActivity ctx){
        context = ctx;
        time = 3000;
        return new MessageUtil();
    }

    public static MessageUtil with(BaseActivity ctx, int showTime){
        context = ctx;
        time = showTime;
        return new MessageUtil();
    }

    public void show(int title,int content,int backgroundColor){
        Alerter.create(context)
                .setTitle(context.getString(title))
                .setText(context.getString(content))
                .setDuration(time)
                .setBackgroundColor(backgroundColor)
                .show();
    }

    public void show(int title, int content, int backgroundColor, OnHideAlertListener callback){
        Alerter.create(context)
                .setTitle(context.getString(title))
                .setText(context.getString(content))
                .setDuration(time)
                .setOnHideListener(callback)
                .setBackgroundColor(backgroundColor)
                .show();
    }

    public void show(String title, String content, int backgroundColor){
        Alerter.create(context)
                .setTitle(title)
                .setText(content)
                .setDuration(time)
                .setBackgroundColor(backgroundColor)
                .show();
    }

    public void show(int title,int content,int backgroundColor,int icon){
        Alerter.create(context)
                .setTitle(context.getString(title))
                .setText(context.getString(content))
                .setBackgroundColor(backgroundColor)
                .setFilter(false,0)
                .setDuration(time)
                .setIcon(ContextCompat.getDrawable(context,icon))
                .show();
    }

    public void show(String title, String content, int backgroundColor, int icon){
        Alerter.create(context)
                .setTitle(title)
                .setText(content)
                .setBackgroundColor(backgroundColor)
                .setFilter(false,0)
                .setDuration(time)
                .setIcon(ContextCompat.getDrawable(context,icon))
                .show();
    }

    public void show(int title,int content,int backgroundColor,int icon,int time){
        Alerter.create(context)
                .setTitle(context.getString(title))
                .setText(context.getString(content))
                .setBackgroundColor(backgroundColor)
                .setDuration(time)
                .setFilter(false,0)
                .setIcon(ContextCompat.getDrawable(context,icon))
                .show();
    }

    public void show(String title, String content, int backgroundColor, int icon,int time){
        Alerter.create(context)
                .setTitle(title)
                .setText(content)
                .setBackgroundColor(backgroundColor)
                .setFilter(false,0)
                .setDuration(time)
                .setIcon(ContextCompat.getDrawable(context,icon))
                .show();
    }
}
