/*
 * Copyright (c) 2018.
 * JavaCore. Advanced level. Homework
 * @author Pavel Dymov
 */

package ru.gb.lesson5;

import java.util.concurrent.TimeUnit;

public class App {
    /**
     * Вычисления в массиве
     *
     * @param src    массив данных
     * @param srcPos стартовая позиция
     * @param count  количество элементов
     */
    private static void arrayCalc(float[] src, int srcPos, int count) {
        int size = srcPos + count;
        for (int i = srcPos; i < size; i++) {
            src[i] = (float) (src[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    /**
     * Пробегаем по массиву и вычисляем значения в главном потоке
     *
     * @param arr массив данных
     */
    private static void oneThread(float[] arr) {
        // значения по умолчанию
        for (int i = 0; i < arr.length; i++) arr[i] = 1;

        long timeStart = System.nanoTime();
        arrayCalc(arr, 0, arr.length);
        System.out.println("oneThread: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");
    }

    /**
     * Выносим вычисления массива в разные потоки
     * для разделения массива на части сдвигаем стартовую позицию
     *
     * @param arr         массив данных
     * @param threadCount количество потоков, по которым разнести вычисления
     */
    private static void multiThread(float[] arr, int threadCount) {
        // значения по умолчанию
        for (int i = 0; i < arr.length; i++) arr[i] = 1;
        // шаг смещения в массиве
        int offset = arr.length / threadCount;
        // создаем и запускаем потоки
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            Runnable task = () -> {
                long timeStart = System.nanoTime();
                arrayCalc(arr, finalI * offset, offset);
                System.out.println("multiThread: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");
            };
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public static void main(String[] args) {
        final int size = 1000000;
        final float[] arr = new float[size];

        System.out.printf("size: %s\n", size);

        oneThread(arr);

        int threadCount = 2;
        multiThread(arr, threadCount);
    }
}
