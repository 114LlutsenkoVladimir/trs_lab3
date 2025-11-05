package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ServerMain {
    static void main() {
        new ServerMain().run();
    }

    private void run() {
        TaskV6Service service = new TaskV6Service();
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread st = new ServerThread(socket, service);
                st.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
