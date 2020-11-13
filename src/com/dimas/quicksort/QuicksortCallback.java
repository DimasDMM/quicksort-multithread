package com.dimas.quicksort;

import java.util.List;
import java.util.concurrent.BlockingQueue;

class QuicksortCallback implements CallbackInterface {
    private BlockingQueue<SortItem> queue;

    public QuicksortCallback(Quicksort quicksort, BlockingQueue<SortItem> queue, int maxCalls) {
        this.queue = queue;
    }

    public void call(List<Integer> list, int lo, int hi, int partition) {
        queue.add(new SortItem(list, lo, partition));
        queue.add(new SortItem(list, partition + 1, hi));
    }
}
