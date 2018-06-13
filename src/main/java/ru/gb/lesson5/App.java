/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson5;

import java.util.concurrent.TimeUnit;

public class App {
    static long arrayCalc(float[] src, int srcPos, int length) {
        long timeStart = System.nanoTime();
        int size = srcPos + length;
        for (int i = srcPos; i < size; i++) {
            src[i] = (float)(src[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Time [" + Thread.currentThread().getName() + "]: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");
        return System.nanoTime() - timeStart;
    }

    public static void main(String[] args) {
        final int size = 10000000;
        final int h = size / 2;

        float[] arr1 = new float[size];

        System.out.printf("size: %s\n", size);

        for (int i = 0; i < size; i++) arr1[i] = 1;

        arrayCalc(arr1, 0, size);

        for (int i = 0; i < size; i++) arr1[i] = 1;

        long timeStart = System.nanoTime();
        new Thread(() -> arrayCalc(arr1, 0, h)).start();
        new Thread(() -> arrayCalc(arr1, h, h)).start();
        System.out.println("Time [" + Thread.currentThread().getName() + "]: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");


        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);

        // Метод 2
//        for (int i = 0; i < size; i++) arr1[i] = 1;
//
//        Runnable task1 = () -> {
//            long timeStart1 = System.nanoTime();
//            arrayCalc(arr1, 0, h);
//            System.out.println("Method 2: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart1) + "ms");
//        };
//
//        Runnable task2 = () -> {
//            long timeStart1 = System.nanoTime();
//            arrayCalc(arr1, h, h);
//            System.out.println("Method 2: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart1) + "ms");
//        };
//
//        new Thread(task1).start();
//        new Thread(task2).start();


//        for (int i = 0; i < size; i++) arr1[i] = 1;
//        int threadCount = 3;
//        int partSize = size / threadCount;
//        for (int i = 0; i < threadCount; i++) {
//            int finalI = i;
//            Runnable task = () -> {
//                long timeStart1 = System.nanoTime();
//                arrayCalc(arr1, finalI * partSize, partSize);
//                System.out.println("Method 3: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart1) + "ms");
//            };
//            new Thread(task).start();
//        }
    }
}
