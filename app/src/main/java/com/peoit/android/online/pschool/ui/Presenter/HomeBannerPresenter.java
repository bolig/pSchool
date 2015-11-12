package com.peoit.android.online.pschool.ui.Presenter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.peoit.android.online.pschool.ActBase;
import com.peoit.android.online.pschool.R;
import com.peoit.android.online.pschool.config.NetConstants;
import com.peoit.android.online.pschool.db.service.HomeBannerService;
import com.peoit.android.online.pschool.entity.HomeBannerInfo;
import com.peoit.android.online.pschool.net.CallBack;
import com.peoit.android.online.pschool.ui.Base.BasePresenter;
import com.peoit.android.online.pschool.ui.activity.BannerActivity;

import java.util.List;
import java.util.Map;

/**
 * 首页banner图
 * <p/>
 * author:libo
 * time:2015/8/31
 * E-mail:boli_android@163.com
 * last: ...
 */
public class HomeBannerPresenter extends BasePresenter<HomeBannerInfo> implements BaseSliderView.OnSliderClickListener {

    private final View img_layout;
    private SliderLayout mViewPager;
    private PagerIndicator mLl_point;
    private List<HomeBannerInfo> bannerInfos;

    public HomeBannerPresenter(ActBase actBase, View img_layout) {
        super(actBase);
        this.img_layout = img_layout;
        initView();
    }

    private void initView() {
        mViewPager = (SliderLayout) img_layout.findViewById(R.id.slider);
        mLl_point = (PagerIndicator) img_layout.findViewById(R.id.custom_indicator);

        mViewPager.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mViewPager.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mViewPager.setCustomAnimation(new DescriptionAnimation());
        mViewPager.setDuration(4000);
    }

    public void updataView(List<HomeBannerInfo> infos) {
        mViewPager.removeAllSliders();
        if (infos != null && infos.size() > 0) {
            for (HomeBannerInfo info : infos) {
                TextSliderView textSliderView = new TextSliderView(mActBase.getActivity());
                // initialize a SliderLayout
                textSliderView
                        .description(info.getTitle())
                        .image(NetConstants.IMAGE_HOST + info.getUrl())
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);
                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putLong("id", info.getId());
                textSliderView.getBundle()
                        .putString("title", info.getTitle());
                mViewPager.addSlider(textSliderView);
            }
        }
    }

    public void doLoadBannerImg() {
        request(NetConstants.NET_QUERY_AD, new CallBack<HomeBannerInfo>() {

            @Override
            public void onSimpleFailure(int error, String errorMsg) {

            }

            @Override
            public void onSimpleSuccessList(List<HomeBannerInfo> result) {
                if (result != null && result.size() > 0){
                    updataView(result);
                    HomeBannerService service = new HomeBannerService(mActBase.getActivity());
                    service.delAll();
                    service.addAll(result);
                }
            }
        });
    }

    @Override
    public Map<String, String> getParams() {
        return getSignParams();
    }

    @Override
    public Class<HomeBannerInfo> getGsonClass() {
        return HomeBannerInfo.class;
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        long id = baseSliderView.getBundle().getLong("id", -1);
        String title = baseSliderView.getBundle().getString("title");
        if (id > -1 && !TextUtils.isEmpty(title)) {
            BannerActivity.startThisActivity(mActBase.getActivity(), title, id);
        }
    }
}
