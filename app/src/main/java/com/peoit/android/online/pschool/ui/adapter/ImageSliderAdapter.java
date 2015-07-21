package com.peoit.android.online.pschool.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页轮播图片
 * author:libo
 * time:2015/7/20
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ImageSliderAdapter extends PagerAdapter {
    private List<View> imgs;

    public ImageSliderAdapter(List<View> imgs) {
        upDatalist(imgs);
    }

    public void upDatalist(List<View> imgs) {
        if (imgs == null)
            imgs = new ArrayList<>();
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (getRealCount() == 0)
            return null;
        //对ViewPager页号求模取出View列表中要显示的项
        position %= imgs.size();
        if (position < 0) {
            position = imgs.size() + position;
        }
        ImageView view = (ImageView) imgs.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (getRealCount() == 0)
//            return;
//        container.removeView(imgs.get(position));
    }
}
