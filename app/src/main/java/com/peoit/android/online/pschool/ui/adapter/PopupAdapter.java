package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;

import java.util.List;

/**
 * author:libo
 * time:2015/9/5
 * E-mail:boli_android@163.com
 * last: ...
 */
public class PopupAdapter extends BaseAdapter {

    private final Activity mAc;
    private final List<String> titles;

    public PopupAdapter(Activity mAc, List<String> titles) {
        this.mAc = mAc;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public String getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv_title = null;
        if (convertView == null) {
            convertView = mAc.getLayoutInflater().inflate(R.layout.popup_list_item, null);
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(tv_title);
        } else {
            tv_title = (TextView) convertView.getTag();
        }
        tv_title.setText(getItem(position));
        return convertView;
    }
}
