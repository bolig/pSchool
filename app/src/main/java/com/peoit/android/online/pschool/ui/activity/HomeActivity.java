package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.view.PsActionBar;

/**
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeActivity extends BaseActivity{
    private PsActionBar actionbar;
    private DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_home);
    }

    public static void startThisActivity(Activity mAc){
        Intent intent = new Intent(mAc, HomeActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        actionbar = (PsActionBar) findViewById(R.id.actionbar);
        actionbar.settitle("铜仁家长网校");
        actionbar.addLeftBtn(R.mipmap.ic_menu, new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        if (menuItem.getItemId() == R.id.menu_info) {
                           BasicInfoActivity.startThisActivity(mContext);
                        } else if (menuItem.getItemId() == R.id.menu_card) {

                        } else if (menuItem.getItemId() == R.id.menu_school) {

                        } else if (menuItem.getItemId() == R.id.menu_pass) {

                        } else if (menuItem.getItemId() == R.id.menu_version) {

                        } else if (R.id.menu_more == menuItem.getItemId()){

                        } else if (R.id.menu_logout == menuItem.getItemId()){

                        }
                        return true;
                    }
                });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void responseFailure(int errorCode) {

    }

    private boolean isBackPressed = false;

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
            return;
        }
        if (!isBackPressed){
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
}
