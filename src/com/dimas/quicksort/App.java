package com.dimas.quicksort;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    private static int N_THREADS = 10;
    private static int RANDOM_SEED = 42;
    private static int N_RUNS = 5;
    
    public static void main(String args[]) {
        Locale.setDefault(new Locale("en", "EN"));
        System.out.println("Working...");

        // List sizes to test
        int[] listSizes = new int[]{100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000, 5000000, 10000000, 50000000};

        try {
            System.out.println("Execution time:");
            for (int i = 0; i < listSizes.length; i++) {
                List<Integer> list = generateList(listSizes[i]);
                long basicTime = runBasicSort(list);
                long threadsTime = runSortWithThreads(list);

                System.out.println(listSizes[i] + "\t" + basicTime + "\t" + threadsTime);
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            System.out.println("- ERROR -");
            System.out.println(e.getMessage());
            System.out.println(sw.toString());
        }

        System.exit(0);
    }

    private static List<Integer> generateList(int size) {
        ArrayList<Integer> list = new ArrayList<Integer>(size);

        Random rand = new Random();
        rand.setSeed(RANDOM_SEED);
        
        for (int i = 0; i < size; i++) {
            Integer r = rand.nextInt() % 256;
            list.add(r);
        }

        return list;
    }

    private static long runBasicSort(List<Integer> originalList) throws Exception {
        long startTime, stopTime;
        long totalTime = 0;
        Quicksort quicksort = new Quicksort();

        // Run several times and compute average time
        for (int run = 0; run < N_RUNS; run++) {
            List<Integer> list = new ArrayList<Integer>(originalList);
            startTime = System.currentTimeMillis();

            quicksort.sort(list);
            
            stopTime = System.currentTimeMillis();
            totalTime += stopTime - startTime;
        }
        
        return totalTime / N_RUNS;
    }

    private static long runSortWithThreads(List<Integer> originalList) throws Exception {
        long startTime, stopTime;
        long totalTime = 0;
        Quicksort quicksort = new Quicksort();

        // Define exception handler for threads
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionStackTrace = sw.toString();
                
                System.out.println("- ERROR IN THREAD -");
                System.out.println(e.getMessage());
                System.out.println(exceptionStackTrace);
                System.exit(1);
            }
        };

        // Run several times and compute average time
        for (int run = 0; run < N_RUNS; run++) {
            List<Integer> list = new ArrayList<Integer>(originalList);

            // Create manager of threads
            BlockingQueue<SortItem> queue = new ArrayBlockingQueue<SortItem>(N_THREADS);
            QuicksortCallback callback = new QuicksortCallback(quicksort, queue, N_THREADS - 1);
            QuicksortManager manager = new QuicksortManager(N_THREADS, quicksort, queue, callback);

            // Create threads and add them to the manager
            for (int i = 0; i < N_THREADS; i++) {
                QuicksortThread thread = new QuicksortThread(quicksort);
                thread.setUncaughtExceptionHandler(h);
                manager.addThread(thread);
            }
            
            startTime = System.currentTimeMillis();
            
            manager.sort(list);
            
            stopTime = System.currentTimeMillis();
            totalTime += stopTime - startTime;
        }
        
        return totalTime / N_RUNS;
    }
}
