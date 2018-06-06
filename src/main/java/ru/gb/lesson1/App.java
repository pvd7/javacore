/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson1;

public class App {
    /**
     * 1. Разобраться с имеющимся кодом;
     * 2. Добавить класс Team, который будет содержать название команды,
     * массив из четырех участников (в конструкторе можно сразу указыватьвсех участников ),
     * метод для вывода информации о членах команды, прошедших дистанцию,
     * метод вывода информации обо всех членах команды.
     * 3. Добавить класс Course (полоса препятствий),
     * в котором будут находиться массив препятствий и метод,
     * который будет просить команду пройти всю полосу;
     */
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
