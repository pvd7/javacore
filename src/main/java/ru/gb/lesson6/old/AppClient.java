/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.gb.lesson6.old;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class AppClient {
    public static void main(String[] args) throws IOException {
        AtomicReference<Socket> sct = new AtomicReference<>();
        PrintWriter pw = null;

        Runnable task = () -> {
            try {
                sct.set(new Socket("localhost", 8888));
                System.out.println("Server online");
                Scanner sc = new Scanner(sct.get().getInputStream());
                String str;
                while (true) {
                    str = sc.nextLine();
                    if (str.equals("quit")) {
                        System.out.println("Server offline");
                        System.exit(0);
                    }
                    System.out.println("Server: " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        String str;
        Scanner sc = new Scanner(System.in);
        while (true) {
            if ((pw == null) & (sct.get() != null)) pw = new PrintWriter(sct.get().getOutputStream());
            System.out.print("Client: ");
            str = sc.nextLine();
            if (pw != null) {
                pw.println(str);
                pw.flush();
            }
            if (str.compareTo("quit") == 0) {
                System.out.print("Client offline");
                System.exit(0);
            }
        }
    }
}
