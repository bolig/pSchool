package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.peoit.android.libview.list.InterceptScrollContainer;
import com.peoit.android.libview.list.MyHScrollView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.entity.SingleGradeInfo;

import java.util.List;

/**
 * author:libo
 * time:2015/8/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class GradeByidAdapter extends EntityAdapter<SingleGradeInfo> {

    private View mheader_view;

    public GradeByidAdapter(Activity mAc, int layoutId, View mheader_view) {
        super(mAc, layoutId);
        this.mheader_view = mheader_view;
    }

    public GradeByidAdapter(Activity mAc, int layoutId, List<SingleGradeInfo> dates, View mheader_view) {
        super(mAc, layoutId, dates);
        this.mheader_view = mheader_view;
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, SingleGradeInfo data, ViewHolderBase holderBase, View convertView) {
        ViewHolder holder = (ViewHolder) holderBase;
        holder.tvItem1.setText(data.getStuno());
        holder.tvItem2.setText(data.getStuname());
        holder.tvItem3.setText(data.getClassName());
        holder.tvItem4.setText(data.getV1());
        holder.tvItem5.setText(data.getV2());
        holder.tvItem6.setText(data.getV3());
        holder.tvItem7.setText(data.getV4());
        holder.tvItem8.setText(data.getV5());
        holder.tvItem9.setText(data.getV6());
        holder.tvItem10.setText(data.getV7());
        holder.tvItem11.setText(data.getV8());
        holder.tvItem12.setText(data.getV9());
        holder.tvItem13.setText(data.getTop());
    }

    private class ViewHolder implements ViewHolderBase{

        private TextView tvItem1;
        private TextView tvItem2;
        private TextView tvItem3;
        private TextView tvItem4;
        private TextView tvItem5;
        private TextView tvItem6;
        private TextView tvItem7;
        private TextView tvItem8;
        private TextView tvItem9;
        private TextView tvItem10;
        private TextView tvItem11;
        private TextView tvItem12;
        private TextView tvItem13;

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
            rowView = mAc.getLayoutInflater().inflate(R.layout.act_grade_by_id_row_item, null);
            rowView.setBackgroundColor(mAc.getResources().getColor(R.color.white_));

            tvItem1 = (TextView) rowView.findViewById(R.id.tv_item1);
            tvItem2 = (TextView) rowView.findViewById(R.id.tv_item2);
            tvItem3 = (TextView) rowView.findViewById(R.id.tv_item3);
            tvItem4 = (TextView) rowView.findViewById(R.id.tv_item4);
            tvItem5 = (TextView) rowView.findViewById(R.id.tv_item5);
            tvItem6 = (TextView) rowView.findViewById(R.id.tv_item6);
            tvItem7 = (TextView) rowView.findViewById(R.id.tv_item7);
            tvItem8 = (TextView) rowView.findViewById(R.id.tv_item8);
            tvItem9 = (TextView) rowView.findViewById(R.id.tv_item9);
            tvItem10 = (TextView) rowView.findViewById(R.id.tv_item10);
            tvItem11 = (TextView) rowView.findViewById(R.id.tv_item11);
            tvItem12 = (TextView) rowView.findViewById(R.id.tv_item12);
            tvItem13 = (TextView) rowView.findViewById(R.id.tv_item13);

            tvItem1.setTextAppearance(mAc, R.style.list_item_b12);
            tvItem2.setTextAppearance(mAc, R.style.list_item_b);
            tvItem3.setTextAppearance(mAc, R.style.list_item_b12);
            tvItem4.setTextAppearance(mAc, R.style.list_item_b);
            tvItem5.setTextAppearance(mAc, R.style.list_item_b);
            tvItem6.setTextAppearance(mAc, R.style.list_item_b);
            tvItem7.setTextAppearance(mAc, R.style.list_item_b);
            tvItem8.setTextAppearance(mAc, R.style.list_item_b);
            tvItem9.setTextAppearance(mAc, R.style.list_item_b);
            tvItem10.setTextAppearance(mAc, R.style.list_item_b);
            tvItem11.setTextAppearance(mAc, R.style.list_item_b);
            tvItem12.setTextAppearance(mAc, R.style.list_item_b);
            tvItem13.setTextAppearance(mAc, R.style.list_item_b);

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
