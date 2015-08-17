package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.easemob.EMCallBack;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.User;
import com.easemob.chatuidemo.utils.CommonUtils;
import com.easemob.exceptions.EaseMobException;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.entity.UserInfo;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.Base.PsApplication;
import com.peoit.android.online.pschool.ui.Presenter.HomePersenter;
import com.peoit.android.online.pschool.ui.adapter.ImageSliderAdapter;
import com.peoit.android.online.pschool.ui.view.PsActionBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;

/**
 * 首页
 * <p/>
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener, EMEventListener {
    private int currentItem = Integer.MAX_VALUE / 2;
    private int[] imgs;
    private List<View> views = new ArrayList<>();
    public static HomeActivity instance;
    private PsActionBar actionbar;
    private DrawerLayout mDrawerLayout;
    private SliderLayout mViewPager;
    private PagerIndicator mLl_point;
    private ImageSliderAdapter mSliderAdapter;
    private View layout_imageSlider;
    private FrameLayout mLayout_body;
    private Map<String, Integer> res_files;
    private LinearLayout ll_item1;
    private LinearLayout ll_item2;
    private LinearLayout ll_item3;
    private LinearLayout ll_item4;
    private LinearLayout ll_item5;
    private LinearLayout ll_item6;

    private ListView dataList;

    private TextView tv_unread_msg_number;
    private int childWitd;
    private static String chatname, groupid;
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
    private UserInfo userInfo;
    private String currentNikeName;
    private HomePersenter mPersenter;
    private TextView logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_home);
        instance = this;
        chatname = PsApplication.getInstance().getUserName();
        Log.i("chatname", chatname + "");
        timer_sys_check = new Timer();
        timer_sys_check.schedule(new Page_check_task(), 1000, 1000);

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
        refreshUI();
        Log.i("onResume", "onResume");
        Log.i("onResume3", isLogin() + "");

        if (isLogin()) {
            userInfo = CommonUtil.getCurrentUser();
            if (userInfo != null) {
                Log.i("onResume2", userInfo.toString());
                currentUsername = userInfo.getUsername();
                currentNikeName = userInfo.getNickname();
                login();
            }
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        timer_sys_check.cancel();
        super.onDestroy();
    }

    @Override
    public void initData() {
        res_files = new HashMap<String, Integer>();
        res_files.put("item0", R.mipmap.home_01);
        res_files.put("item1", R.mipmap.home_01);
        res_files.put("item2", R.mipmap.home_01);
        res_files.put("item3", R.mipmap.home_01);
        res_files.put("item4", R.mipmap.home_01);

        mPersenter = new HomePersenter(this);
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

        dataList = (ListView) findViewById(R.id.data_list);

        mPersenter.setNavigationDataList(dataList);

        logout = (TextView) findViewById(R.id.logout);

        mLayout_body = (FrameLayout) findViewById(R.id.layout_body);

        layout_imageSlider = getLayoutInflater().inflate(R.layout.in_home_viewpager, null);

        mLayout_body.addView(layout_imageSlider);


        mViewPager = (SliderLayout) layout_imageSlider.findViewById(R.id.slider);
        mLl_point = (PagerIndicator) layout_imageSlider.findViewById(R.id.custom_indicator);

        for (String name : res_files.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(res_files.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mViewPager.addSlider(textSliderView);
        }
        mViewPager.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mViewPager.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mViewPager.setCustomAnimation(new DescriptionAnimation());
        mViewPager.setDuration(4000);

        ll_item1 = (LinearLayout) findViewById(R.id.homl_ll_item1);
        ll_item2 = (LinearLayout) findViewById(R.id.homl_ll_item2);
        ll_item3 = (LinearLayout) findViewById(R.id.homl_ll_item3);
        ll_item4 = (LinearLayout) findViewById(R.id.homl_ll_item4);
        ll_item5 = (LinearLayout) findViewById(R.id.homl_ll_item5);
        ll_item6 = (LinearLayout) findViewById(R.id.homl_ll_item6);
        tv_unread_msg_number = (TextView) findViewById(R.id.main_unread_msg_number);
        tv_unread_msg_number.setVisibility(View.GONE);

        childWitd = (CommonUtil.w_screeen - CommonUtil.dip2px(mContext, 2)) / 3;
        setLinearlayoutWidth(ll_item1);
        setLinearlayoutWidth(ll_item2);
        setLinearlayoutWidth(ll_item3);
        setLinearlayoutWidth(ll_item4);
        setLinearlayoutWidth(ll_item5);
        setLinearlayoutWidth(ll_item6);

    }

//    @Override
//    protected void onResume() {
//        // register the event listener when enter the foreground
//        EMChatManager.getInstance().registerEventListener(this,
//                new EMNotifierEvent.Event[]{EMNotifierEvent.Event.EventNewMessage, EMNotifierEvent.Event.EventOfflineMessage, EMNotifierEvent.Event.EventConversationListChanged});
//        super.onResume();
//    }

    @Override
    protected void onStop() {
        super.onStop();
        EMChatManager.getInstance().unregisterEventListener(this);
    }

    private void setLinearlayoutWidth(LinearLayout layout) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.width = childWitd;
        layoutParams.height = childWitd;
        layout.setLayoutParams(layoutParams);
    }

    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(this);

        ll_item1.setOnClickListener(this);
        ll_item2.setOnClickListener(this);
        ll_item3.setOnClickListener(this);
        ll_item4.setOnClickListener(this);
        ll_item5.setOnClickListener(this);
        ll_item6.setOnClickListener(this);

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
        } else
            finish();
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
    public void onSliderClick(BaseSliderView baseSliderView) {
        Toast.makeText(this, baseSliderView.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == ll_item1) {
            if (isLoginAndToLogin())
                //金融IC卡充值
                BankICActivity.startThisActivity(mContext);
        } else if (v == ll_item2) {
//            //校园信息
//            if (isLoginAndToLogin())
//                NewsActivity.startThisActivity(mContext, NewsActivity.test, "新闻");
        } else if (v == ll_item3) {
            //校园通知
            if (isLoginAndToLogin())
                NoticeActivity.startThisActivity(mContext);
        } else if (v == ll_item4) {
            //学校专栏
            if (isLoginAndToLogin())
                FeatureActivity.startThisActivity(mContext);
        } else if (v == ll_item5) {
            //交流
            if (isLoginAndToLogin() && !TextUtils.isEmpty(chatname)) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                // it is group chat
                intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
                intent.putExtra("groupId", groupid);
                Log.i("chatname", chatname);
                Log.i("groupid", groupid);
                //intent.putExtra("groupId", "85759016126382492");
                startActivityForResult(intent, 0);
            }
        } else if (v == ll_item6) {
            //支付

        }
    }

    private String currentUsername = "xdd02";
    private String currentPassword = "dba508b941b095bcd5060ff742a436e2";
    private boolean progressShow;

    /**
     * 登录
     *
     * @param
     */
    public void login() {
        if (!CommonUtils.isNetWorkConnected(this)) {
            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(currentUsername)) {
            //提示用户名不能为空
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        progressShow = true;
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                progressShow = false;
            }
        });
        pd.setMessage(getString(R.string.Is_landing));
        pd.show();

        final long start = System.currentTimeMillis();
        // 调用sdk登陆方法登陆聊天服务器
        EMChatManager.getInstance().login(currentUsername, currentPassword, new EMCallBack() {
            @Override
            public void onSuccess() {
                if (!progressShow) {
                    return;
                }
                // 登陆成功，保存用户名密码
                PsApplication.getInstance().setUserName(currentUsername);
                PsApplication.getInstance().setPassword(currentPassword);
                chatname = PsApplication.getInstance().getUserName();

                try {
                    // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                    // ** manually load all local groups and
                    EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    // 处理好友和群组
                    initializeContacts();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 取好友或者群聊失败，不让进入主页面
                    runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            PsApplication.getInstance().logout(null);
                            Toast.makeText(getApplicationContext(), R.string.login_failure_failed, Toast.LENGTH_LONG).show();
                        }
                    });
                    return;
                }
                if (!HomeActivity.this.isFinishing() && pd.isShowing()) {
                    pd.dismiss();
                }
                List<EMGroup> grouplist = null;
                try {
                    grouplist = EMGroupManager.getInstance().getGroupsFromServer();
                } catch (EaseMobException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (grouplist != null && grouplist.size() > 0) {
                    Log.i("grouplist", grouplist.size() + "---" + grouplist.toString());
                    groupid = grouplist.get(0).getGroupId();
                    DemoApplication.getInstance().setNickName(currentNikeName);
                } else {
                    //showToast("你尚未被添加进任何群组, 给你");
                }
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {
                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initializeContacts() {
        Map<String, User> userlist = new HashMap<String, User>();
        // 添加user"申请与通知"
        User newFriends = new User();
        newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
        String strChat = getResources().getString(
                R.string.Application_and_notify);
        newFriends.setNick(strChat);

        userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
        // 添加"群聊"
        User groupUser = new User();
        String strGroup = getResources().getString(R.string.group_chat);
        groupUser.setUsername(Constant.GROUP_USERNAME);
        groupUser.setNick(strGroup);
        groupUser.setHeader("");
        userlist.put(Constant.GROUP_USERNAME, groupUser);

        // 添加"Robot"
        User robotUser = new User();
        String strRobot = getResources().getString(R.string.robot_chat);
        robotUser.setUsername(Constant.CHAT_ROBOT);
        robotUser.setNick(strRobot);
        robotUser.setHeader("");
        userlist.put(Constant.CHAT_ROBOT, robotUser);

        // 存入内存
        //PsApplication.getInstance().setContactList(userlist);
        // 存入db
        UserDao dao = new UserDao(this);
        List<User> users = new ArrayList<User>(userlist.values());
        dao.saveContactList(users);
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
            tv_unread_msg_number.setText(unreadmsgcount + "");
            tv_unread_msg_number.setVisibility(View.VISIBLE);
        } else {
            tv_unread_msg_number.setVisibility(View.GONE);
        }
        //tv_unread_msg_number.invalidate();
    }

}
