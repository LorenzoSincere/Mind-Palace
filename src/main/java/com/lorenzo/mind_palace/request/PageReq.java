package com.lorenzo.mind_palace.request;

import com.sun.istack.internal.NotNull;


/**
 * @author libocheng
 */
public class PageReq {

    private int page;

    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" + "page=" + page +
                ", size=" + size +
                '}';
    }
}
