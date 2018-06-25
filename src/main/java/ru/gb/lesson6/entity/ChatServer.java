/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6.entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * сервер чата
 */
public class ChatServer {

    private final ServerSocket serverSocket;
    // список тредов-обработчиков подключенных клиентов
    private final List<ServerSocketThread> serverSocketThreadList = new LinkedList<>();

    /**
     * Создает сервер чата
     *
     * @param port порт
     * @throws IOException
     */
    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            serverSocketThreadList.add(new ServerSocketThread(this, socket));
        }
    }

    public List<ServerSocketThread> getServerSocketThreadList() {
        return serverSocketThreadList;
    }
}
