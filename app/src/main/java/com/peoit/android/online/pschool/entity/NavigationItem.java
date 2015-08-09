package com.peoit.android.online.pschool.entity;

import com.peoit.android.online.pschool.EntityBase;

/**
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NavigationItem implements EntityBase {

    private int icon;
    private int rIcon;
    private String title;
    private boolean isClick = false;

    public NavigationItem(int icon, int rIcon, String title, boolean isClick) {
        this.icon = icon;
        this.rIcon = rIcon;
        this.title = title;
        this.isClick = isClick;
    }

    public int getrIcon() {
        return rIcon;
    }

    public void setrIcon(int rIcon) {
        this.rIcon = rIcon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    @Override
    public String toString() {
        return "NavigationItem{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", mark=" +
                '}';
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean match() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
