package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
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
        actionbar.addLeftBtn(R.drawable.abc_ic_clear_mtrl_alpha, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                        if (menuItem.getItemId() == R.id.nav_viewpager) {

                        } else if (menuItem.getItemId() == R.id.nav_subsamplingScale) {

                        } else if (menuItem.getItemId() == R.id.nav_gifview) {

                        } else if (menuItem.getItemId() == R.id.nav_home) {

                        }
                        return true;
                    }
                });
    }

    @Override
    public void initListener() {

    }
}
