package com.peoit.android.online.pschool.ui.Presenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.UserTypeBase;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.UserTypeCallBack;
import com.peoit.android.online.pschool.entity.NavigationItem;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.exception.NoLoginEcxeption;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.activity.BankCardActivity;
import com.peoit.android.online.pschool.ui.activity.BasicInfoActivity;
import com.peoit.android.online.pschool.ui.activity.ChangePasswordActivity;
import com.peoit.android.online.pschool.ui.activity.SchoolBindActivity;
import com.peoit.android.online.pschool.ui.adapter.NavigationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/10
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomePersenter extends BasePresenter<UserInfo> implements UserTypeBase {

    private UserTypeCallBack callBack;
    private ListView dataList;
    private List<NavigationItem> navigationItems = new ArrayList<>();
    private NavigationAdapter adapter;
    private View footer_view;
    private View header_view;

    private TextView tvUser;
    private TextView phone;

    private TextView logout;
    private UserInfo user;

    public HomePersenter(ActBase actBase) {
        super(actBase);
        try {
            callBack = new UserTypeCallBack(this);
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

    public void setNavigationDataList(ListView dataList) {
        if (dataList == null)
            throw new NullPointerException(" @libo datalist is null ");
        this.dataList = dataList;

        addHeader();

        user = mActBase.getCurrentUser();

        callBack.start();
    }

//    private void addFooter() {
//        footer_view = LayoutInflater.from(mActBase.getContext()).inflate(R.layout.nav_list_footer, null);
//
//        logout = (TextView) footer_view.findViewById(R.id.logout);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mActBase.logout();
//                mActBase.finish();
//            }
//        });
//
//
//
//        dataList.addFooterView(footer_view);
//    }

    private void addHeader() {
        header_view = LayoutInflater.from(mActBase.getContext()).inflate(R.layout.nav_list_header, null);

        tvUser = (TextView) header_view.findViewById(R.id.tv_user);
        phone = (TextView) header_view.findViewById(R.id.phone);

        dataList.addHeaderView(header_view);
    }



    @Override
    public void current_is_parent() {
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, R.drawable.ic_arrow_right, "基本资料", true));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuschool, R.drawable.ic_arrow_right, "学校绑定", true));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubankcard, R.drawable.ic_arrow_right, "银行卡绑定", true));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuchangepass, R.drawable.ic_arrow_right, "密码修改", true));

        tvUser.setText(user.getStuname() + "的家长");
        phone.setText("电话:" + user.getFatmobile());

        adapter = new NavigationAdapter((Activity) mActBase.getContext(), R.layout.layout_navigation_item, navigationItems);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                }else if (position == 1){
                    //基本资料
                    BasicInfoActivity.startThisActivity((Activity) mActBase.getContext());

                }else if (position == 2){
                    //学校绑定
                    SchoolBindActivity.startThisActivity((Activity) mActBase.getContext());

                }else if (position == 3){
                    //银行卡绑定
                    BankCardActivity.startThisActivity((Activity) mActBase.getContext());

                }else if (position == 4){
                    //密码修改
                    ChangePasswordActivity.startThisActivity((Activity) mActBase.getContext());

                }
            }
        });
    }

    @Override
    public void current_is_teacher() {
        navigationItems.add(new NavigationItem(R.mipmap.lefephoneimage, -1, "电话:" + user.getPhone(), false));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenubaseinfo, -1, "用户名:" + user.getSchoolName() + user.getCalssName() + "班主任", false));
        navigationItems.add(new NavigationItem(R.mipmap.lefthouseimage, -1, "班级:" + user.getCalssName()
                , false));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuchangepass, R.drawable.ic_arrow_right, "密码修改", true));

        tvUser.setText(user.getNickname());
        phone.setVisibility(CommonUtil.GONE);

        adapter = new NavigationAdapter((Activity) mActBase.getContext(), R.layout.layout_navigation_item, navigationItems);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void current_is_expert() {
        navigationItems.add(new NavigationItem(R.mipmap.lefephoneimage, -1, "电话:" + user.getPhone(), false));
        navigationItems.add(new NavigationItem(R.mipmap.leftmenuchangepass, R.drawable.ic_arrow_right, "密码修改", true));

        tvUser.setText(user.getNickname());
        phone.setVisibility(CommonUtil.GONE);

        adapter = new NavigationAdapter((Activity) mActBase.getContext(), R.layout.layout_navigation_item, navigationItems);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
