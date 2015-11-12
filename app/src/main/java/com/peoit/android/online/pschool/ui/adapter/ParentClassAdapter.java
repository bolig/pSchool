package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.ui.activity.ParentsClassroomActivity;

/**
 * author:libo
 * time:2015/10/15
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ParentClassAdapter extends BaseAdapter {

    private final Context mContext;
    String[] list = Constants.mParentClasses;

    public ParentClassAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public String getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getItemData(String str){
        if (str.contains(Constants.$_ADD_$)){
            str = str.replace(Constants.$_ADD_$, "");
        }
        return str;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.act_parent_class_list_item, null);
        }
        String str = getItem(position);
        final String strItemData = getItemData(str);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_title);
        tv.setText(strItemData);
        if (str.contains(Constants.$_ADD_$)){
            tv.setBackgroundColor(mContext.getResources().getColor(R.color.md_orange_500));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(mContext, 36));
            tv.setLayoutParams(params);
            tv.setSelected(true);
            tv.setOnClickListener(null);
        } else {
            tv.setBackgroundResource(R.drawable.parent_class_item_sel);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(mContext, 48));
            tv.setLayoutParams(params);
            tv.setSelected(false);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParentsClassroomActivity.startThisActivity((Activity) mContext, strItemData);
                }
            });
        }
        return convertView;
    }

}
