package com.peoit.android.online.pschool.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.CommonUtil;
import com.peoit.android.online.pschool.ui.Base.BaseActivity;
import com.peoit.android.online.pschool.ui.adapter.ImageSliderAdapter;
import com.peoit.android.online.pschool.ui.view.PsActionBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:libo
 * time:2015/7/14
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private int currentItem = Integer.MAX_VALUE/2;
    private int[] imgs;
    private List<View> views = new ArrayList<>();

    private PsActionBar actionbar;
    private DrawerLayout mDrawerLayout;
    private SliderLayout mViewPager;
    private PagerIndicator mLl_point;
    private ImageSliderAdapter mSliderAdapter;
    private View layout_imageSlider;
    private FrameLayout mLayout_body;
    private Map<String, Integer> res_files;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isMainUI = false;
        setContentView(R.layout.act_home);
    }

    public static void startThisActivity(Activity mAc) {
        Intent intent = new Intent(mAc, HomeActivity.class);
        mAc.startActivity(intent);
    }

    @Override
    public void initData() {
        res_files = new HashMap<String, Integer>();
        res_files.put("item0", R.mipmap.home_01);
        res_files.put("item1", R.mipmap.home_01);
        res_files.put("item2", R.mipmap.home_01);
        res_files.put("item3", R.mipmap.home_01);
        res_files.put("item4", R.mipmap.home_01);
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

        mLayout_body = (FrameLayout) findViewById(R.id.layout_body);

        layout_imageSlider = getLayoutInflater().inflate(R.layout.in_home_viewpager, null);
        mLayout_body.addView(layout_imageSlider);

        mViewPager = (SliderLayout) layout_imageSlider.findViewById(R.id.slider);
        mLl_point = (PagerIndicator) layout_imageSlider.findViewById(R.id.custom_indicator);

        for(String name : res_files.keySet()){
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
                    .putString("extra",name);

            mViewPager.addSlider(textSliderView);
        }
        mViewPager.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mViewPager.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mViewPager.setCustomAnimation(new DescriptionAnimation());
        mViewPager.setDuration(4000);
        mViewPager.addOnPageChangeListener(this);
    }


    private ImageView getSliderImage() {
        ImageView iv = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(this, 200));
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return iv;
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

                        } else if (R.id.menu_more == menuItem.getItemId()) {

                        } else if (R.id.menu_logout == menuItem.getItemId()) {
                            LoginActivity.startThisActivity(mContext);
                        }
                        return true;
                    }
                });
    }

    @Override
    public void initListener() {

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
}
