/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson1;

public class App {
    private static String[] humans = {"Вася", "Петя", "Коля", "Ваня"};
    private static String[] obstacles = {"бассейн с акулами", "минное поле", "аццкое пламя", "спасти рядового райана", "зомби лэнд"} ;
    public static void main(String[] args) {
        // создаем команду
        Team team = new Team(humans);
        // создаем полосу препятствий
        Course course = new Course(obstacles);
        // говорим команде пройти полосу препятствий
        course.doIt(team);
        // выводим последний результат прохождения полосы
        team.showResults();
    }
}
