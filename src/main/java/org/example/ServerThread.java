package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerThread extends Thread {
    PrintWriter out;
    BufferedReader in;
    private TaskV6Service service;

    public ServerThread(Socket socket, TaskV6Service service) {
        this.service = service;
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out.println("Вітальна інформація Луценко Володимир 4141, варіант 16");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formatted = now.format(formatter);
        out.println("Поточна дата:" + formatted);
        while (true) {
            try {
                String s = in.readLine();
                out.println(service.getAnswer(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
