package com.peoit.android.online.pschool;

/**
 * author:libo
 * time:2015/7/13
 * E-mail:boli_android@163.com
 * last: ...
 */
public interface EntityListBase extends EntityBase{
//    void add(T t);
//
//    void addAll(List<T> lists);
//
//    T remove(int index);
//
//    void remove(T t);
//
//    List<T> getDataByMatch();

    //    private int page;
//    private int skip;
//    private int pageSize;
//
//    private List<T> data = new ArrayList<T>();
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public int getSkip() {
//        return skip;
//    }
//
//    public void setSkip(int skip) {
//        this.skip = skip;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    public List<T> getData() {
//        return data;
//    }
//
//    public void setData(List<T> data) {
//        this.data = data;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        BaseListEntity<?> that = (BaseListEntity<?>) o;
//
//        return data.equals(that.data);
//
//    }
//
//    @Override
//    public int hashCode() {
//        return data.hashCode();
//    }
//
//    @Override
//    public String toString() {
//        return "BaseListEntity{" +
//                "page=" + page +
//                ", skip=" + skip +
//                ", pageSize=" + pageSize +
//                ", data=" + data +
//                '}';
//    }
//
//    @Override
//    public void add(T t) {
//        if (data != null && t != null && !t.isNull() && !data.contains(t))
//            data.add(t);
//        else
//            MyLogger.e(">>>>>>> add entity failure <<<<<<<<");
//    }
//
//    @Override
//    public void addAll(List<T> lists) {
//        if (lists != null) {
//
//        }
//    }
//
//    @Override
//    public T remove(int index) {
//        if (data != null)
//            return data.remove(index);
//        else
//            return null;
//    }
//
//    @Override
//    public void remove(T t) {
//        if (data != null) {
//            data.remove(t);
//        }
//    }
//
//    @Override
//    public List<T> getDataByMatch() {
//        List<T> list = new ArrayList<T>();
//        if (data != null && data.size() > 0) {
//            for (T t : data) {
//                if (!t.isNull() && t.match())
//                    list.add(t);
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public boolean isNull() {
//        return data == null || data.isEmpty();
//    }
//
//    @Override
//    public boolean match() {
//        return false;
//    }
}
