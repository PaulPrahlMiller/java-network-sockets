package multiClientServer;

import java.io.*;
import java.net.*;
public class MultiClientServer extends Thread {

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 8000");
            System.exit(-1);
        }
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                Server server = new Server(clientSocket);
                server.start();
            } catch (IOException e) {
                System.out.println("Accept failed:8000");
                System.exit(-1);
            }
        }
    }
}
