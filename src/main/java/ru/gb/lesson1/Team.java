/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson1;

/**
 * Команда
 */
class Team {
    // члены команды
    private String[] humans;
    // полоса препятсвий
    private Course lastCourse;
    // результат прохождения полосы препятсвий
    private boolean[][] lastResult;

    Team(String[] humans) {
        this.humans = humans;
        this.lastResult = new boolean[humans.length][];
    }

    String[] getHumans() {
        return humans;
    }

    void setLastCourse(Course lastCourse) {
        this.lastCourse = lastCourse;
    }

    boolean[][] getLastResult() {
        return lastResult;
    }

    /**
     * Выводит в консоль результат прохождения последней полосы препятствий
     */
    void showResults() {
        if (!(lastCourse == null)) {
            String[] obstacles = lastCourse.getObstacles();
            String str;
            for (int i = 0; i < humans.length; i++) {
                str = "";
                for (int j = 0; j < lastResult[i].length; j++) {
                    str += obstacles[j] + ": " + Boolean.toString(lastResult[i][j]) + ", ";
                }
                System.out.println(humans[i] + " {" + str.substring(0, str.length() - 2) + "}");
            }
        } else {
            System.out.println("Нет данных");
        }
    }

}
