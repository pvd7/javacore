/*
 * Copyright (c) 2018.
 * 
 */

package ru.gb.lesson5;

import java.util.concurrent.TimeUnit;

public class App {
    /**
     * Вычисления в массиве
     *
     * @param src    массив данных
     * @param srcPos стартовая позиция
     * @param count количество элементов
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
    private static void Method1(float[] arr) {
        System.out.print("Method 1: ");
        // значения по умолчанию
        for (int i = 0; i < arr.length; i++) arr[i] = 1;
        long timeStart = System.nanoTime();
        arrayCalc(arr, 0, arr.length);
        System.out.println("[" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");
    }

    /**
     * Выносим вычисления массива в разные потоки
     * для разделения массива на части сдвигаем стартовую позицию позицию
     *
     * @param arr         массив данных
     * @param threadCount количество потоков, по которым разнести вычисления
     */
    private static void Method2(float[] arr, int threadCount) {
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
                System.out.println("Method 2: [" + Thread.currentThread().getName() + "] " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) + "ms");
            };
            new Thread(task).start();
        }
    }

    public static void main(String[] args) {
        final int size = 1000000;
        float[] arr = new float[size];

        System.out.printf("size: %s\n", size);

        Method1(arr);

        int threadCount = 7;
        Method2(arr, threadCount);
    }
}
