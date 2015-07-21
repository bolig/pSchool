package com.peoit.android.online.pschool.entity;

import android.os.Handler;
import android.os.Message;

/**
 * author:libo
 * time:2015/7/20
 * E-mail:boli_android@163.com
 * last: ...
 */
public abstract class SliderTimeThread extends Thread{
    private int sliderTime = 7;
    private boolean isSlider = true;

    public synchronized void jian(){
        sliderTime -- ;
    }

    public synchronized boolean isToSliderTime(){
        return sliderTime < 0;
    }

    public synchronized void reSet(){
        sliderTime = 7;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            callBack();
            reSet();
        }
    };

    public synchronized void onPance(){
        isSlider = false;
    }

    public synchronized void onStart(){
        reSet();
        isSlider = true;
    }

    private synchronized boolean isSlider(){
        return isSlider;
    }

    @Override
    public void run() {
        while (true){
            if (isToSliderTime() && isSlider()){
                handler.sendEmptyMessage(0);
            } else {
                try {
                    sliderTime --;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract void callBack();
}
