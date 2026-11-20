package com.team.starsort.collection;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterService {

    public static <T> int countOccurrences(CustomList<T> list, T target)
            throws InterruptedException, ExecutionException {

        if (list == null || list.isEmpty()) return 0;

        int threadsCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        AtomicInteger totalCount = new AtomicInteger(0);

        int chunkSize = (list.size() + threadsCount - 1) / threadsCount;
        List<Future<?>> futures = new java.util.ArrayList<>();
    }
}