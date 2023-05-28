package task3;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerThread {

    PrintWriter output;
    BufferedReader input;
    Socket clientSocket;
    Integer ID;

    public ServerThread(Socket clientSocket, Integer clientId) {
        this.clientSocket = clientSocket;
        this.ID = clientId;
        initSocketIO();
    }

    private void initSocketIO() {
        try {
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ObjectOutputStream out = null;
        BufferedReader in = null;
        String inputLine;

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch(IOException e) {
            System.out.println("Failed to create I/O Streams for server thread");
            System.exit(-1);
        }

        try {
            while((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("q")) {
                    break;
                }
            }
            clientSocket.close();
        } catch(IOException e) {
            System.out.println("Failed read/write operation");
            System.exit(-1);
        }
    }
}
