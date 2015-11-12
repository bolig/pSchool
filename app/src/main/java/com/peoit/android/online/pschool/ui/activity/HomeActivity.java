package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.config.Constants;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Presenter.HXHelperPresenter;
import com.peoit.android.online.pschool.ui.Presenter.HomeBannerPresenter;
import com.peoit.android.online.pschool.ui.Presenter.HomeItemPresenter;
import com.peoit.android.online.pschool.ui.Presenter.HomePersenter;
import com.peoit.android.online.pschool.ui.Presenter.QueryNoallotPresenter;
import com.peoit.android.online.pschool.ui.view.PsActionBar;
import com.peoit.android.online.pschool.utils.MyLogger;
import com.peoit.android.online.pschool.utils.NetWorkHelper;
import com.pgyersdk.update.PgyUpdateManager;

import java.util.Hashtable;
import java.util.Timer;

/**
 * 首页
 * <p/>
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeActivity extends BaseActivity implements ViewPagerEx.OnPageChangeListener, View.OnClickListener, EMEventListener {
    public static HomeActivity instance;
    private PsActionBar actionbar;
    public DrawerLayout mDrawerLayout;
    private View layout_imageSlider;
    private FrameLayout mLayout_body;

    private ListView dataList;

    private int childWitd;
    private Timer timer_sys_check;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    refreshUI();
                    break;
            }

        }
    };
    private HomePersenter mPersenter;
    private TextView logout;
    private GridView gv_item;
    private HomeItemPresenter homeItemPresenter;
    private HomeBannerPresenter mHomeBannerPresenter;

//    public HXHelperPresenter mHXHelperPresneter;

    private LinearLayout home_ll;
    private TextView home_tv;
    private QueryNoallotPresenter mQueryPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_home);

        instance = this;
        timer_sys_check = new Timer();
        timer_sys_check.schedule(new Page_check_task(), 1000, 1000);
        initReceiver();
    }

    private static final String action_network = "android.net.conn.CONNECTIVITY_CHANGE";

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetWorkHelper.checkNetState(mContext)) {
                mHomeBannerPresenter.doLoadBannerImg();
//                mHXHelperPresneter.login(false);
            }
        }
    };

    private void initReceiver() {
        IntentFilter filter = new IntentFilter(action_network);
        registerReceiver(myReceiver, filter);
    }

    class Page_check_task extends java.util.TimerTask {
        @Override
        public void run() {
            Message ms = new Message();
            ms.what = 1;
            handler.sendMessage(ms);
        }
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, HomeActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUI();
        DemoApplication.getInstance().setNickName(CommonUtil.getCurentuserNike());
        MyLogger.i("onResume", "onResume");
        MyLogger.i("onResume3", isLogin() + "");
    }

    @Override
    protected void onDestroy() {
        timer_sys_check.cancel();
        unregisterReceiver(myReceiver);
        MyLogger.i(">>>>>>>>>>>onDestroy");
        super.onDestroy();
    }

    @Override
    public void initData() {
        mPersenter = new HomePersenter(this);
        PgyUpdateManager.register(this, "ed2630a49feb59a5ac63b68e0bdd9bfc");
    }

    @Override
    public void initView() {
        actionbar = (PsActionBar) findViewById(R.id.actionbar);
        actionbar.settitle("铜仁家长网校");
        actionbar.addLeftBtn(R.mipmap.ic_menu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();
        dataList = (ListView) findViewById(R.id.data_list);
        mPersenter.setNavigationDataList(dataList);
        logout = (TextView) findViewById(R.id.logout);
        mLayout_body = (FrameLayout) findViewById(R.id.layout_body);
        layout_imageSlider = getLayoutInflater().inflate(R.layout.in_home_viewpager, null);

        mHomeBannerPresenter = new HomeBannerPresenter(this, layout_imageSlider);
        mHomeBannerPresenter.updataView(CommonUtil.mHomeBanners);
        mHomeBannerPresenter.doLoadBannerImg();

        mLayout_body.addView(layout_imageSlider);

        childWitd = (CommonUtil.w_screeen - CommonUtil.dip2px(mContext, 2)) / 3;

        gv_item = (GridView) findViewById(R.id.gv_item);
        home_ll = (LinearLayout) findViewById(R.id.home_ll1);
        home_tv = (TextView) findViewById(R.id.home_tv1);
        if (getShare().getBoolean(Constants.LOGIN_ISZHUANJIA, false)) {
            home_ll.setVisibility(View.VISIBLE);
            gv_item.setVisibility(View.GONE);
            mQueryPresenter = new QueryNoallotPresenter(this, home_tv);
            mQueryPresenter.start();
//            home_tv.setText(""+expert);
        } else {
            home_ll.setVisibility(View.GONE);
            gv_item.setVisibility(View.VISIBLE);
        }
        homeItemPresenter = new HomeItemPresenter(this, gv_item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMChatManager.getInstance().unregisterEventListener(this);
    }

    @Override
    public void initListener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                finish();
            }
        });
    }

    private boolean isBackPressed = false;

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        if (!isBackPressed) {
            CommonUtil.showToast("再按一次退出铜仁掌上家校");
            isBackPressed = !isBackPressed;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = !isBackPressed;
                }
            }, 5 * 1000);
        } else {
            finish();
            HXHelperPresenter.groupid = null;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 监听事件
     */
    @Override
    public void onEvent(EMNotifierEvent event) {
        //myToast("监听");
        Log.i("lister", "监听");
        switch (event.getEvent()) {
            case EventNewMessage: // 普通消息
            {
                EMMessage message = (EMMessage) event.getData();

                // 提示新消息
                HXSDKHelper.getInstance().getNotifier().onNewMsg(message);

                refreshUI();
                break;
            }

            case EventOfflineMessage: {
                refreshUI();
                break;
            }

            case EventConversationListChanged: {
                refreshUI();
                break;
            }

            default:
                break;
        }
    }

    public void refreshUI() {
        Log.i("HomeActivity_", CommonUtils.getTopActivity(HomeActivity.this));
        Log.i("refreshUI", "refreshUI");
        Hashtable<String, EMConversation> conversations = EMChatManager.getInstance().getAllConversations();
        int unreadmsgcount = 0;
        for (EMConversation conversation : conversations.values()) {
            unreadmsgcount = conversation.getUnreadMsgCount();
            Log.i("refreshUI", unreadmsgcount + "");
        }
        if (unreadmsgcount > 0) {
            homeItemPresenter.changeMarkCount(unreadmsgcount);
        } else {
            homeItemPresenter.changeMarkCount(0);
        }
    }

}
