package com.dimas.quicksort;

public class QuicksortThread extends Thread {
    private Quicksort quicksort;
    private SortItem item;

    public QuicksortThread(Quicksort quicksort) {
        this.quicksort = quicksort;
    }

    public void setSortItem(SortItem item) {
        this.item = item;
    }

    /**
     * Runner of this thread.
     */
    @Override
    public void run() {
        try {
            quicksort.sort(item.getList(), item.getLo(), item.getHi());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
