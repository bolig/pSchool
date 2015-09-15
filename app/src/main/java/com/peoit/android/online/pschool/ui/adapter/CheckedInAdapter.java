package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.peoit.android.libview.list.InterceptScrollContainer;
import com.peoit.android.libview.list.MyHScrollView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.CheckInInfo;

/**
 * author:libo
 * time:2015/9/5
 * E-mail:boli_android@163.com
 * last: ...
 */
public class CheckedInAdapter extends EntityAdapter<CheckInInfo> {

    private final View mheader_view;

    public CheckedInAdapter(Activity mAc, int layoutId, View mheader_view) {
        super(mAc, layoutId);
        this.mheader_view = mheader_view;
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, CheckInInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvItem1.setText(data.getStuname());
        holder.tvItem2.setText(data.getStuclassname());
        holder.tvItem3.setText(data.getCreated());
        holder.tvItem4.setText(data.getSTime());
        holder.tvItem5.setText(data.getETime());
        showType(data.getType(), holder.tvItem6, holder);
    }

    private void showType(int type, TextView tvItem6, ViewHolder convertView) {
        switch (type) {
            case 1:
                tvItem6.setText("正常打卡");
                tvItem6.setTextColor(mAc.getResources().getColor(R.color.black_1));
                convertView.rowView.setBackgroundColor(mAc.getResources().getColor(R.color.white_));
                break;
            case 2:
                tvItem6.setText("迟到");
                tvItem6.setTextColor(mAc.getResources().getColor(R.color.md_red_500));
                convertView.rowView.setBackgroundColor(mAc.getResources().getColor(R.color.md_red_100));
                break;
            case 3:
                tvItem6.setText("未签到");
                tvItem6.setTextColor(mAc.getResources().getColor(R.color.md_orange_500));
                convertView.rowView.setBackgroundColor(mAc.getResources().getColor(R.color.md_orange_100));
                break;
        }
    }

    private class ViewHolder implements ViewHolderBase{
        private TextView tvItem1;
        private TextView tvItem2;
        private TextView tvItem3;
        private TextView tvItem4;
        private TextView tvItem5;
        private TextView tvItem6;

        private View rowView;

        private InterceptScrollContainer iscScroll;
        private MyHScrollView scScroll;

        @Override
        public void inflaer(View convertView) {

            iscScroll = (InterceptScrollContainer) convertView.findViewById(R.id.isc_scroll);
            scScroll = (MyHScrollView) convertView.findViewById(R.id.sc_scroll);

            MyHScrollView sv = (MyHScrollView) mheader_view.findViewById(R.id.sc_scroll);

            sv.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scScroll));

            addRowItem();
        }

        private void addRowItem() {
            rowView = mAc.getLayoutInflater().inflate(R.layout.act_check_row_item, null);
            rowView.setBackgroundColor(mAc.getResources().getColor(R.color.white_));

            tvItem1 = (TextView) rowView.findViewById(R.id.tv_item1);
            tvItem2 = (TextView) rowView.findViewById(R.id.tv_item2);
            tvItem3 = (TextView) rowView.findViewById(R.id.tv_item3);
            tvItem4 = (TextView) rowView.findViewById(R.id.tv_item4);
            tvItem5 = (TextView) rowView.findViewById(R.id.tv_item5);
            tvItem6 = (TextView) rowView.findViewById(R.id.tv_item6);

            tvItem1.setTextAppearance(mAc, R.style.list_item_b);
            tvItem2.setTextAppearance(mAc, R.style.list_item_b12);
            tvItem3.setTextAppearance(mAc, R.style.list_item_b12);
            tvItem4.setTextAppearance(mAc, R.style.list_item_b);
            tvItem5.setTextAppearance(mAc, R.style.list_item_b);
            tvItem6.setTextAppearance(mAc, R.style.list_item_b);

            scScroll.addView(rowView);
        }
    }

    class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
        MyHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    }
}
