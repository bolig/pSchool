package com.peoit.android.online.pschool.ui.view.CardView;

import android.graphics.drawable.Drawable;

/**
 * author:libo
 * time:2015/8/3
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface CardViewDelegate {
    void setBackgroundDrawable(Drawable paramDrawable);
    Drawable getBackground();
    boolean getUseCompatPadding();
    boolean getPreventCornerOverlap();
    float getRadius();
    void setShadowPadding(int left, int top, int right, int bottom);
}
