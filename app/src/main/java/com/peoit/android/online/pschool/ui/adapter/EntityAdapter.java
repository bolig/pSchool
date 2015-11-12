package com.peoit.android.online.pschool.ui.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.peoit.android.online.pschool.EntityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体适配器
 *
 * @create libo 2015/1/22
 * @finish ...
 */
public abstract class EntityAdapter<T extends EntityBase> extends BaseAdapter {
    private LayoutInflater inflater;
    protected List<T> datas;
    protected int layoutId;
    protected Activity mAc;
    protected Fragment mFragment;

    public EntityAdapter(Activity mAc, int layoutId) {
        this(mAc, layoutId, null);
    }

    public EntityAdapter(Activity mAc, int layoutId, List<T> dates) {
        this.mAc = mAc;
        this.layoutId = layoutId;
        this.datas = dates;
        inflater = LayoutInflater.from(mAc);
        upDateList(dates);
    }

    public EntityAdapter(Fragment fragment, int layoutId) {
        this(fragment, layoutId, null);
    }

    public EntityAdapter(Fragment fragment, int layoutId, List<T> dates) {
        this.mAc = fragment.getActivity();
        this.mFragment = fragment;
        this.layoutId = layoutId;
        this.datas = dates;
        inflater = LayoutInflater.from(mAc);
        upDateList(dates);
    }

    protected View inflater() {
        if (inflater == null)
            throw new NullPointerException("@libo inflater is null");
        return inflater.inflate(layoutId, null);
    }

    public void upDateList(List<T> dates) {
        if (dates == null) {
            dates = new ArrayList<T>();
        }
        this.datas = dates;
        notifyDataSetChanged();
    }

    public void addHeadDataList(List<T> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }

        for (int i = datas.size() - 1; i >= 0; i--) {
            if (!this.datas.contains(datas.get(i))) {
                this.datas.add(0, datas.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void addFootDataList(List<T> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            if (!this.datas.contains(datas.get(i))) {
                this.datas.add(datas.get(i));
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 移除datas里的单个数据 by data
     *
     * @param data
     */
    public void removeDataItem(T data) {
        if (datas.contains(data)) {
            datas.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除datas里的所有数据
     */
    public void removeAllData() {
        if (datas != null) {
            datas.clear();
        }
    }

    /**
     * 移除datas里的单个数据，根据data下标
     *
     * @param position
     */
    public void removeDataItem(int position) {
        if (position >= 0 && datas.size() > 0 && datas.size() > position) {
            datas.remove(position);
            notifyDataSetChanged();
        }
    }

    protected void changData(int postition, T t) {
        datas.remove(postition);
        datas.add(postition, t);
        notifyDataSetChanged();
    }

    /**
     * 获取 match == true 的值
     *
     * @return
     */
    public List<T> getMatchDataList() {
        List<T> matchDataList = new ArrayList<>();
        if (datas == null || datas.size() == 0)
            return matchDataList;
        for (int i = 0; i < datas.size(); i++) {
            T data = datas.get(i);
            if (data.match())
                matchDataList.add(data);
        }
        return matchDataList;
    }

    public List<T> getDates() {
        return datas;
    }

    public void setDates(List<T> dates) {
        this.datas = dates;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderBase holderBase = null;
        if (convertView == null) {
            convertView = inflater();
            holderBase = getViewHolder();
            if (holderBase == null)
                throw new NullPointerException(" @libo ViewHolder is null");
            holderBase.inflaer(convertView);
            convertView.setTag(holderBase);
        } else {
            holderBase = (ViewHolderBase) convertView.getTag();
        }

        initView(position, getItem(position), holderBase, convertView);

        return convertView;
    }

    protected abstract ViewHolderBase getViewHolder();

    protected abstract void initView(int position, T data, ViewHolderBase holderBase, View convertView);

    public interface ViewHolderBase {
        void inflaer(View convertView);
    }
}
