
package com.team.starsort.collection;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterService {

    public static <T> int countOccurrences(CustomList<T> list, T target)
            throws InterruptedException, ExecutionException {

        //Проверка на пустую коллекцию
        if (list == null || list.isEmpty()) return 0;

        int threadsCount = 4; //количество потоков
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        AtomicInteger totalCount = new AtomicInteger(0);

        //Вычислкение одного куска
        int chunkSize = (list.size() + threadsCount - 1) / threadsCount;
        List<Future<?>> futures = new java.util.ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, list.size());

            if (start >= end) break;

            //Отправка задачи в пул потоков
            futures.add(executor.submit(() -> {
                for (int j = start; j < end; j++) {
                    if (java.util.Objects.equals(list.get(j), target)) {
                        totalCount.incrementAndGet(); // Безопасное увеличение
                    }
                }
            }));
        }

        //Ожидание завершения всех потоков
        for (Future<?> future : futures) {
            future.get();
        }

        executor.shutdown();
        return totalCount.get();
    }
}
