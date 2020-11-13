package com.dimas.quicksort;

import java.util.List;

interface CallbackInterface {
    public void call(List<Integer> list, int lo, int hi, int partition) throws Exception;
}
