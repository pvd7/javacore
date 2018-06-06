/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson3;

import ru.gb.lesson3.entity.PhoneBook;
import ru.gb.lesson3.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App {
    /**
     * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
     * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
     * Посчитать, сколько раз встречается каждое слово.
     */
    private static void Task1() {
        String str = "hello hello hello1 hello2 hello4 hello7 hello7 hello1 hello hello hello1 hello2 hello4 hello7 hello7 hello1 hello hello hello1 hello2 hello4 hello7 hello7 hello1";

        HashMap<String, Integer> hashmap = new HashMap<>();
        for (String s: str.split(" ")) {
            hashmap.put(s, hashmap.getOrDefault(s, 0) + 1);
        }
        System.out.println(hashmap);
    }

    /**
     *2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
     * В этот телефонный справочник с помощью метода add() можно добавлять записи.
     * С помощью метода get() искать номер телефона по фамилии.
     * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
     * тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    private static void Task2() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(new User("Ivan", "22-3-21"));
        phoneBook.add(new User("Ivan", "22-3-22"));
        phoneBook.add(new User("Peter", "22-3-23"));
        phoneBook.add(new User("Dima", "22-3-24"));
        phoneBook.add(new User("Sasha", "22-3-25"));
        phoneBook.add(new User("Alex", "22-3-26"));

        System.out.println(phoneBook.get("Ivan"));
        System.out.println(phoneBook.get("Dima"));
        System.out.println(phoneBook.get("Dima2"));
    }

    public static void main(String[] args) {
        System.out.println("Task1");
        Task1();

        System.out.println();
        System.out.println("Task2");
        Task2();
    }
}
