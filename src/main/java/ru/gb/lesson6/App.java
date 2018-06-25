/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6;

import ru.gb.lesson6.entity.ChatServer;
import ru.gb.lesson6.entity.ChatWindow;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        /**
         * Эмуляция чата на несколько клиентов
         */
        String[] users = {"Pavel", "Ivan", "Masha"};
        chatEmulation(users);
    }

    private static void chatEmulation(String[] users) {
        final String host = "localhost";
        final int port = 8888;

        /**
         * запускаем сервер
         */
        Runnable serverTask = () -> {
            try {
                ChatServer chatServer = new ChatServer(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(serverTask);
        thread.start();

        /**
         * запускаем клиентов
         */
        for (int i = 0; i < users.length; i++) {
            int finalI = i;
            Runnable clientTask = () -> {
                try {
                    ChatWindow chatWindow = new ChatWindow(users[finalI], host, port);
                    chatWindow.setBounds(400 * finalI + 10, 10, 400, 400);
                    chatWindow.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            Thread clientThread = new Thread(clientTask);
            clientThread.start();
        }

    }

}
