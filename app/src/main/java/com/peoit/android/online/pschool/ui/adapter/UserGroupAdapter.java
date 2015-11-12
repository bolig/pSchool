package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.UserInfo;

/**
 * author:libo
 * time:2015/9/1
 * E-mail:boli_android@163.com
 * last: ...
 */
public class UserGroupAdapter extends EntityAdapter<UserInfo> {

    public UserGroupAdapter(Activity mAc, int layoutId) {
        super(mAc, layoutId);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, UserInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder mHolder = (ViewHolder) holderBase;
        mHolder.tvName.setText(getName(data));
    }

    private String getName(UserInfo data) {
        int type = Integer.valueOf(data.getIdentityType());
        if (type == 2){
            return data.getStuname() + "    家长";
        } else {
            return data.getNickname();
        }
    }

    private class ViewHolder implements ViewHolderBase{
        private TextView tvName;

        @Override
        public void inflaer(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
        }

    }
}
