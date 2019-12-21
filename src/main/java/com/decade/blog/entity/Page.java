package com.decade.blog.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author decade
 * @create 2019-12-21 10:37
 */
@Data
public class Page<E> {

    /**
     * 每页存储的对象
     */
    private List<E> obj = new ArrayList<>();
    /**
     * 页码
     */
    private int pageNumber;
    /**
     * 每页显示的个数
     */
    private int size;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总个数
     */
    private int totalCount;
    /**
     * 当前页对应第一个对象的索引
     */
    private int currentIndex;

    public Page() {
    }

    public List<E> getObj() {
        return obj;
    }

    public void setObj(List<E> obj) {
        this.obj = obj;
    }

    public Page(List<E> obj) {
        this.obj = obj;
    }

    public int getPageNumber() {
        if (pageNumber < 1) {
            return 1;
        } else if (pageNumber > getTotalPage()) {
            return getTotalPage();
        }
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 总页数 = 总个数 / 当前页显示的个数
     * 由于是根据计算得到的，不需要set方法
     *
     * @return
     */
    public int getTotalPage() {
        int oldPageCount = getTotalCount() / getSize();
        int newPageCount = getTotalCount() % getSize() == 0 ? oldPageCount : oldPageCount + 1;
        return newPageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 当前页对应第一个对象的索引 = (pageNumber-1)*size
     * 由于是根据计算得到的，不需要set方法
     *
     * @return
     */
    public int getCurrentIndex() {
        return (getPageNumber() - 1) * getSize();
    }

    @Override
    public String toString() {
        return "Page{" +
                "obj=" + obj +
                ", pageNumber='" + pageNumber + '\'' +
                ", size=" + size +
                ", totalPage=" + getTotalPage() +
                ", totalCount=" + totalCount +
                ", currentIndex=" + getCurrentIndex() +
                '}';
    }

}
