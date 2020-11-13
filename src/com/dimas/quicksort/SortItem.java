package com.dimas.quicksort;

import java.util.List;

final public class SortItem {
    private int lo;
    private int hi;
    private List<Integer> list;

    public SortItem() {}

    public SortItem(List<Integer> list, int lo, int hi) {
        this.list = list;
        this.lo = lo;
        this.hi = hi;
    }

    public List<Integer> getList() {
        return this.list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public int getLo() {
        return this.lo;
    }

    public void setLo(int lo) {
        this.lo = lo;
    }

    public int getHi() {
        return this.hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }
}
