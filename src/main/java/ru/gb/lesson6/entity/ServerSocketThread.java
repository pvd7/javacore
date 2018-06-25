/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * тред-обработчик подключенных клиентов
 * принимает и пересылает сообщения от одного клиента всем.
 */
public class ServerSocketThread implements Runnable {

    private final ChatServer chatServer;
    private final Socket socket;

    private final Scanner in;
    private final PrintWriter out;

    /**
     * Создает тред-обработчик клиентских сообщений.
     *
     * @param chatServer сервер чата
     * @param socket     сокет клиента
     * @throws IOException
     */
    public ServerSocketThread(ChatServer chatServer, Socket socket) throws IOException {
        this.chatServer = chatServer;
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream());

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String str;
        while (!socket.isClosed()) {
            str = in.nextLine();
            // предеаем сообщения всем клиентам, за исключением откуда пришло
            for (ServerSocketThread serverSocketThread : chatServer.getServerSocketThreadList()) {
                if (!serverSocketThread.equals(this))
                    serverSocketThread.send(str);
            }
        }
    }

    private void send(String msg) {
        out.println(msg);
        out.flush();
    }

}
