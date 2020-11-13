package com.dimas.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class QuicksortManager extends Thread implements QuicksortInterface {
    private List<QuicksortThread> threads;
    private int nThreads;
    private QuicksortCallback callback;
    private Quicksort quicksort;
    private BlockingQueue<SortItem> queue;
    
    public QuicksortManager(int nThreads, Quicksort quicksort, BlockingQueue<SortItem> queue, QuicksortCallback callback) {
        this.threads = new ArrayList<QuicksortThread>();
        this.nThreads = nThreads;
        this.quicksort = quicksort;
        this.queue = queue;
        this.callback = callback;
    }

    public List<QuicksortThread> getThreads() {
        return threads;
    }

    public void addThread(QuicksortThread thread) {
        threads.add(thread);
    }

    public void sort(List<Integer> list) throws Exception {
        SortItem firstItem = new SortItem(list, 0, list.size() - 1);
        queue.add(firstItem);

        int prevSize = 0;
        while (queue.size() < nThreads) {
            if (prevSize >= queue.size()) {
                // Too many threads for this list
                break;
            } else {
                prevSize = queue.size();
            }

            SortItem item = queue.take();
            quicksort.sort(list, item.getLo(), item.getHi(), callback);
        }
        
        // Run threads
        int workingThreads = 0;
        for (QuicksortThread thread : threads) {
            if (queue.isEmpty()) {
                break;
            }

            workingThreads++;
            thread.setSortItem(queue.take());
            thread.start();
        }
        for (QuicksortThread thread : threads) {
            if (workingThreads == 0) {
                break;
            }

            thread.join();
            workingThreads--;
        }
    }
}
