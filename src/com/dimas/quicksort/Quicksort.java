package com.dimas.quicksort;

import java.util.List;

public class Quicksort implements QuicksortInterface {
    public void sort(List<Integer> list) throws Exception {
        sort(list, 0, list.size() - 1);
    }

    public void sort(List<Integer> list, CallbackInterface callback) throws Exception {
        sort(list, 0, list.size() - 1, callback);
    }

    /**
     * @param list
     * @param lo
     * @param hi
     * @throws Exception
     */
    public void sort(List<Integer> list, int lo, int hi) throws Exception {
        if (lo < hi) {
            int partition = partition(list, lo, hi);
            sort(list, lo, partition);
            sort(list, partition + 1, hi);
        }
    }

    /**
     * @param list
     * @param lo
     * @param hi
     * @throws Exception
     */
    public void sort(List<Integer> list, int lo, int hi, CallbackInterface callback) throws Exception {
        if (lo < hi) {
            int partition = partition(list, lo, hi);
            callback.call(list, lo, hi, partition);
        }
    }

    /**
     * @param list
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    private int partition(List<Integer> list, int lo, int hi) throws Exception {
        int pivotId = getPivotId(list, lo, hi);
        int i = lo - 1;
        int j = hi + 1;
        int pivot = list.get(pivotId);

        while (true) {
            do {
                i += 1;
            } while (i < list.size() && list.get(i).intValue() < pivot);
            do {
                j -= 1;
            } while (j >= 0 && list.get(j).intValue() > pivot);

            if (i >= j) {
                break;
            } else {
                swap(list, i, j);
            }
        }

        return j;
    }

    /**
     * @param list
     * @param i
     * @param j
     */
    private void swap(List<Integer> list, int i, int j) {
        Integer e = list.get(i);
        list.set(i, list.get(j));
        list.set(j, e);
    }

    /**
     * @param list
     * @return
     * @throws Exception
     */
    private int getPivotId(List<Integer> list, int lo, int hi) throws Exception {
        return (hi - lo) / 2 + lo;
    }
}
