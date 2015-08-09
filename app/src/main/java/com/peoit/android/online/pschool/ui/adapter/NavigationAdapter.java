package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.NavigationItem;

import java.util.List;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NavigationAdapter extends EntityAdapter<NavigationItem>{

    public NavigationAdapter(Activity mAc, int layoutId, List<NavigationItem> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, NavigationItem data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.icon.setImageResource(data.getIcon());
        holder.title.setText(data.getTitle());
        holder.arrow.setVisibility(data.getrIcon() == -1 ? CommonUtil.GONE : CommonUtil.VISIBLE);
        if (data.isClick()){
            convertView.setBackgroundResource(R.drawable.list_item_sel);
        } else {
            convertView.setBackgroundColor(mAc.getResources().getColor(R.color.white_));
        }
    }

    private class ViewHolder implements ViewHolderBase{
        private ImageView icon;
        private TextView title;
        private ImageView arrow;

        @Override
        public void inflaer(View convertView) {
            icon = (ImageView) convertView.findViewById(R.id.icon);
            title = (TextView) convertView.findViewById(R.id.title);
            arrow = (ImageView) convertView.findViewById(R.id.arrow);
        }
    }
}
