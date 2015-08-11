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
public class GradeByIdAdapter extends EntityAdapter<SingleGradeInfo> {

    public GradeByIdAdapter(Activity mAc, int layoutId, MyHScrollView mdeader_view) {
        super(mAc, layoutId);
    }

    public GradeByIdAdapter(Activity mAc, int layoutId, List<SingleGradeInfo> dates) {
        super(mAc, layoutId, dates);
    }

    @Override
    protected ViewHolderBase getViewHolder() {
        return new ViewHolder();
    }

    @Override
    protected void initView(int position, SingleGradeInfo data, ViewHolderBase holderBase, View convertView) {

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

        private View rowView;

        private InterceptScrollContainer iscScroll;
        private MyHScrollView scScroll;

        @Override
        public void inflaer(View convertView) {
            iscScroll = (InterceptScrollContainer) convertView.findViewById(R.id.isc_scroll);
            scScroll = (MyHScrollView) convertView.findViewById(R.id.sc_scroll);

            scScroll.AddOnScrollChangedListener(new OnScrollChangedListenerImp(scScroll));

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

            tvItem1.setTextAppearance(mAc, R.style.list_item_b);
            tvItem2.setTextAppearance(mAc, R.style.list_item_b);
            tvItem3.setTextAppearance(mAc, R.style.list_item_b);
            tvItem4.setTextAppearance(mAc, R.style.list_item_b);
            tvItem5.setTextAppearance(mAc, R.style.list_item_b);
            tvItem1.setTextAppearance(mAc, R.style.list_item_b);
            tvItem1.setTextAppearance(mAc, R.style.list_item_b);
            tvItem1.setTextAppearance(mAc, R.style.list_item_b);
            tvItem1.setTextAppearance(mAc, R.style.list_item_b);

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
    };
}
