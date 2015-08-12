package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.CheckInInfo;

/**
 * author:libo
 * time:2015/8/12
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CheckInAdapter extends EntityAdapter<CheckInInfo> {

    public CheckInAdapter(Activity mAc, int layoutId) {
        super(mAc, layoutId);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, CheckInInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvName.setText(data.getStuname());
        holder.tvCheckInStutas.setText(data.getStatus());
        if ("未处理".equals(data.getStatus()) || "迟到".equals(data.getStatus())) {
            holder.tvCheckInStutas.setSelected(true);
        } else if ("正常打卡".equals(data.getStatus()) || "休息".equals(data.getStatus())) {
            holder.tvCheckInStutas.setSelected(false);
        } else {
            holder.tvCheckInStutas.setSelected(true);
        }
        holder.tvCheckIn.setText("上学:" + data.getStime() + "\n" + "放学:" + data.getEtime());
        holder.tvRealStime.setText(data.getSTime());
        holder.tvRealEtime.setText(data.getETime());
        holder.tvLateTime.setText(data.getSCd());
        holder.llLate.setVisibility(TextUtils.isEmpty(data.getSCd()) ? View.GONE : View.VISIBLE);
        holder.tvTime.setText(data.getDetDate());
    }

    private class ViewHolder implements ViewHolderBase {
        private TextView tvName;
        private TextView tvCheckInStutas;
        private TextView tvCheckIn;
        private TextView tvRealStime;
        private TextView tvRealEtime;
        private TextView tvLateTime;
        private LinearLayout llLate;
        private TextView tvTime;

        @Override
        public void inflaer(View convertView) {
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvCheckInStutas = (TextView) convertView.findViewById(R.id.tv_check_in_stutas);
            tvCheckIn = (TextView) convertView.findViewById(R.id.tv_check_in);
            tvRealStime = (TextView) convertView.findViewById(R.id.tv_real_stime);
            tvRealEtime = (TextView) convertView.findViewById(R.id.tv_real_etime);
            tvLateTime = (TextView) convertView.findViewById(R.id.tv_late_time);
            llLate = (LinearLayout) convertView.findViewById(R.id.ll_late);
            tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        }
    }

    private enum CheckStatus {
        NONE(true, "未处理"),
        LATE(true, "迟到"),
        NORMAL(false, "正常打卡"),
        REST(false, "休息");

        private boolean isSelected;
        private String status;

        CheckStatus(boolean isSelected, String status) {
            this.isSelected = isSelected;
            this.status = status;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean equals(String status) {
            CheckStatus[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].getStatus().equals(status)) {
                    return values[i].isSelected();
                }
            }
            return false;
        }
    }
}
