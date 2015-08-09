package com.peoit.android.online.pschool.entity;

/**
 *
 *
 * author:libo
 * time:2015/8/9
 * E-mail:boli_android@163.com
 * last: ...
 */
public class NavigationItem {

    private int icon;
    private int rIcon;
    private String title;

    public NavigationItem(int icon, int rIcon, String title) {
        this.icon = icon;
        this.rIcon = rIcon;
        this.title = title;
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

    @Override
    public String toString() {
        return "NavigationItem{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", mark=" +
                '}';
    }
}
