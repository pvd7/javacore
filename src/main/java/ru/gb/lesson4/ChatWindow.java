/*
 * @author  Pavel Dymov
 * @github  https://github.com/pvd7/pdymov-javacore-advanced.git
 */

package ru.gb.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWindow extends JFrame {
    private static final int WINDOW_WIDTH  = 507;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POS_X  = 650;
    private static final int WINDOW_POS_Y  = 250;

    public JTextArea taMsg;
    public JTextField tfMsg;
    public JButton btnSendMsg;

    public ChatWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        // область сообщений
        taMsg = new JTextArea();
        taMsg.setEditable(false);
        add(new JScrollPane(taMsg), BorderLayout.CENTER);

        // поле ввода сообщения
        tfMsg = new JTextField();
        tfMsg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) btnSendMsgClick();
            }
        });

        // кнопка отправки сообщения
        btnSendMsg = new JButton("Send");
        btnSendMsg.addActionListener(e -> btnSendMsgClick());

        JPanel panelSendMsg = new JPanel();
        panelSendMsg.setLayout(new BorderLayout());
        panelSendMsg.add(tfMsg, BorderLayout.CENTER);
        panelSendMsg.add(btnSendMsg, BorderLayout.EAST);
        add(panelSendMsg, BorderLayout.SOUTH);

        setVisible(true);

        tfMsg.requestFocus();
    }

    private void btnSendMsgClick() {
        // добавляем наше сообщение в общий чат
        taMsg.append("You: " + tfMsg.getText() + "\n");

        // очищаем поле ввода сообщения и устанавливаем на нем фокус
        tfMsg.setText("");
        tfMsg.requestFocus();
    }
}
