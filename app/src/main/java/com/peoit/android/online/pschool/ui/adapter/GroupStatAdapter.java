package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.entity.UserStatInfo;
import com.peoit.android.online.pschool.ui.view.NoSrcollListView;

import java.util.List;

/**
 * author:libo
 * time:2015/10/22
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GroupStatAdapter extends EntityAdapter<UserStatInfo> {
    public GroupStatAdapter(Activity mAc, int layoutId) {
        super(mAc, layoutId);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new Viewholder();
    }

    @Override
    protected void initView(int position, UserStatInfo data, ViewHolderBase holderBase, View convertView) {
        Viewholder viewholder = (Viewholder) holderBase;
        viewholder.tvTitle.setText(data.getTitle());
        List<UserInfo> infos = data.getUserInfos();
        if (infos == null || infos.size() == 0){
            viewholder.tvTitle.setVisibility(View.GONE);
            viewholder.lvInfo.setVisibility(View.GONE);
        } else {
            viewholder.tvTitle.setVisibility(View.VISIBLE);
            viewholder.lvInfo.setVisibility(View.VISIBLE);
            viewholder.adapter.upDateList(infos);
        }
    }

    private class Viewholder implements ViewHolderBase{
        private TextView tvTitle;
        private NoSrcollListView lvInfo;
        private UserGroupAdapter adapter;

        @Override
        public void inflaer(View convertView) {
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            lvInfo = (NoSrcollListView) convertView.findViewById(R.id.lv_info);
            adapter = new UserGroupAdapter(mAc, R.layout.act_group_list_item);
            lvInfo.setAdapter(adapter);
        }
    }
}
