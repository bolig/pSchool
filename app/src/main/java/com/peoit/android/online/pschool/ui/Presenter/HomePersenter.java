package com.peoit.android.online.pschool.ui.Presenter;

import android.widget.ListView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.UserTypeBase;
import com.peoit.android.online.pschool.config.UserTypeCallBack;
import com.peoit.android.online.pschool.entity.NavigationItem;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.exception.NoLoginEcxeption;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomePersenter extends BasePresenter<UserInfo> implements UserTypeBase{

    private ListView dataList;
    private List<NavigationItem> navigationItems = new ArrayList<>();

    public HomePersenter(ActBase actBase) {
        super(actBase);
        try {
            UserTypeCallBack callBack = new UserTypeCallBack(this);
        } catch (NoLoginEcxeption noLoginEcxeption) {
            noLoginEcxeption.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }

    @Override
    public Class getGsonClass() {
        return null;
    }

    public void setNavigationDataList(ListView dataList){
        if (dataList == null)
            throw new NullPointerException(" @libo datalist is null ");
        this.dataList = dataList;
        addHeader();
        addFooter();
    }

    private void addFooter() {

    }

    private void addHeader() {

    }

    @Override
    public void current_is_parent() {
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, R.drawable.ic_arrow_right, "基本资料"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuschool, R.drawable.ic_arrow_right, "学校绑定"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubankcard, R.drawable.ic_arrow_right, "银行卡绑定"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuchangepass, R.drawable.ic_arrow_right, "密码修改"));
    }

    @Override
    public void current_is_teacher() {
        navigationItems.add(new NavigationItem(R.mipmap.lefephoneimage, R.drawable.ic_arrow_right, "基本资料"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, R.drawable.ic_arrow_right, "基本资料"));
        navigationItems.add(new NavigationItem(R.mipmap.lefthouseimage, R.drawable.ic_arrow_right, "基本资料"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuchangepass, R.drawable.ic_arrow_right, "密码修改"));
    }

    @Override
    public void current_is_expert() {
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, R.drawable.ic_arrow_right, "基本资料"));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, R.drawable.ic_arrow_right, "基本资料"));
    }
}
