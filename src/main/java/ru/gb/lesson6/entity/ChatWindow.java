/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6.entity;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Чат
 */
public class ChatWindow extends JFrame {
    private final String userName;
    private final Socket socket;
    private final PrintWriter out;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private JPanel contentPane;
    private JButton btnSend;
    private JTextField tfText;
    private JTextArea taMessages;

    /**
     * Создает окно чата
     *
     * @param userName имя пользователя (nick name)
     * @param host     адрес сервера
     * @param port     порт
     * @throws IOException
     */
    public ChatWindow(String userName, String host, int port) throws IOException {
        setContentPane(contentPane);

        this.userName = userName;

        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getRootPane().setDefaultButton(btnSend);
        pack();
        setLocationRelativeTo(null);
        tfText.requestFocus();

        btnSend.addActionListener(e -> onSend());

        tfText.addActionListener(e -> onSend());

        socket = new Socket(host, port);

        out = new PrintWriter(socket.getOutputStream());

        Runnable task = () -> {
            try {
                Scanner sc = new Scanner(socket.getInputStream());
                String str;
                while (!socket.isClosed()) {
                    str = sc.nextLine();
                    taMessages.append(str + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }

    private void onSend() {
        String msg = DATE_FORMAT.format(System.currentTimeMillis()) + " " + getUserName() + ": " + tfText.getText();

        taMessages.append(msg + "\n");

        out.println(msg);
        out.flush();

        tfText.setText("");
        tfText.requestFocus();
    }

    public String getUserName() {
        return userName;
    }

    public static void main(String[] args) throws IOException {
        ChatWindow dialog = new ChatWindow("pavel", "", 8888);
        dialog.setVisible(true);
    }

}
