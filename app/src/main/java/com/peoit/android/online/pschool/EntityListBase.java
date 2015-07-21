package com.peoit.android.online.pschool;

import java.util.List;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface EntityListBase<T> extends EntityBase{
    void add(T t);

    void addAll(List<T> lists);

    T remove(int index);

    void remove(T t);

    List<T> getDataByMatch();
}
