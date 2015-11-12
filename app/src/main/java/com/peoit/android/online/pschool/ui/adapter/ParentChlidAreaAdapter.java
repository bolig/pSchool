package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.ui.activity.FamilyActivitiy;

/**
 * author:libo
 * time:2015/10/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public class ParentChlidAreaAdapter extends BaseAdapter {
    private final Context mContext;
    private String[] mAreas;

    public ParentChlidAreaAdapter(Context context, String[] areas) {
        this.mContext = context;
        updataList(areas);
    }

    public void updataList(String[] areas) {
        if (areas == null)
            areas = new String[]{};
        this.mAreas = areas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAreas.length;
    }

    @Override
    public String getItem(int position) {
        return mAreas[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView mtvTile = null;
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.act_parent_chlid_area_list_item, null);
            mtvTile = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(mtvTile);
        } else {
            mtvTile = (TextView) convertView.getTag();
        }
        String item = mAreas[position];
        final boolean isHeader = (!TextUtils.isEmpty(item)) && item.contains("header");
        if (isHeader) {
            mtvTile.setTextColor(mContext.getResources().getColor(R.color.white_1));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.md_orange_500));
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(mContext, 36));
            mtvTile.setLayoutParams(params1);
        } else {
            mtvTile.setTextColor(mContext.getResources().getColor(R.color.black_1));
            convertView.setBackgroundDrawable(null);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(mContext, 48));
            mtvTile.setLayoutParams(params1);
        }
        mtvTile.setText(getTitle(getItem(position)));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHeader) {
                    FamilyActivitiy.startThisActivity((Activity) mContext, Constants.mParentChildAreasDet[position]);
                }
            }
        });
        return convertView;
    }

    private String getTitle(String item) {
        if (((!TextUtils.isEmpty(item)) && item.contains("header"))) {
            item = item.replace("header", "");
        }
        return item;
    }
}
