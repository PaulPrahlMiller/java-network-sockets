package task3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private final Integer PORT = 8000;
    private final AtomicInteger CLIENT_ID = new AtomicInteger(0);
    private final ArrayList<ServerThread> connections = new ArrayList<>();

    public Server() {}

    public void start() {
        ServerSocket socket = null;
        Socket clientSocket;

        try {
            socket = new ServerSocket(PORT);
        } catch(IOException e) {
            System.out.println("Could not listen on port " + PORT);
            System.exit(-1);
        }

        while (true) {
            try {
                clientSocket = socket.accept();
                var id = CLIENT_ID.addAndGet(1);

                System.out.printf("Client %d connected on port %d", id, PORT);

                ServerThread thread = new ServerThread(clientSocket, id);
                connections.add(thread);


            } catch (IOException e) {
                System.out.printf("Accept failed on port %d", PORT);
                System.exit(-1);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
