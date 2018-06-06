/*
 * Copyright (c) 2018.
 */

package ru.gb.lesson1;

import java.util.Random;

/**
 * Полоса препятствий
 */
class Course {
    private String[] obstacles;

    Course(String[] obstacles) {
        this.obstacles = obstacles;
    }

    String[] getObstacles() {
        return obstacles;
    }

    /**
     * прохождение полосы препятсвий командой
     * @param team команда
     */
    void doIt(Team team) {
        Random rnd = new Random();
        team.setLastCourse(this);
        boolean[][] result = team.getLastResult();
        int len = obstacles.length;
        for (int i = 0; i < team.getHumans().length; i++) {
            result[i] = new boolean[len];
            for (int j = 0; j < len; j++) {
                result[i][j] = rnd.nextBoolean();
            }
        }
    }
}
