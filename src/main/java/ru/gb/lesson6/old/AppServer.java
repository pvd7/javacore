/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6.old;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class AppServer {
    public static void main(String[] args) throws IOException {
        final ServerSocket srv = new ServerSocket(8888);
        AtomicReference<Socket> sct = new AtomicReference<>();
        PrintWriter pw = null;

        Runnable task = () -> {
            try {
                sct.set(srv.accept());
                System.out.println("Client online");
                Scanner sc = new Scanner(sct.get().getInputStream());
                String str;
                while (true) {
                    str = sc.nextLine();
                    if (str.equals("quit")) {
                        System.out.println("Client offline");
                        System.exit(0);
                    }
                    System.out.println("Client: " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

//        Thread thread = new Thread(task);
//        thread.start();

        String str;
        Scanner sc = new Scanner(System.in);
        while (true) {
            if ((pw == null) & (sct.get() != null)) {
                pw = new PrintWriter(sct.get().getOutputStream());
            }
            System.out.print("Server: ");
            str = sc.nextLine();
            if (pw != null) {
                pw.println(str);
                pw.flush();
            }
            if (str.compareTo("quit") == 0) {
                System.out.print("Server offline");
                System.exit(0);
            }
        }
    }
}
