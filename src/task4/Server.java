package task4;

import echoServer.EchoServer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {}
    public void establish() {
        ServerSocket serverSocket = null;
        try {
            serverSocket= new ServerSocket(8080, 2, InetAddress.getLocalHost());
        }catch (IOException e) {
            System.out.println("Could not listen on port: 8000");
            System.exit(-1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        }catch (IOException e) {
            System.out.println("Accept failed: 8000");
            System.exit(-1);
        }
        ObjectInputStream in = null;
        Object inputObject = null;
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
        }catch (IOException ioe) {
            System.out.println("Failed in creating streams");
            System.exit(-1);
        }
        try {
            while((inputObject = in.readObject()) != null) {
                System.out.println(inputObject.toString());
            }
        }catch (IOException ioe) {
            System.out.println("Failed in reading, writing");
            System.exit(-1);
        }catch (ClassNotFoundException ce) {
            System.out.println("Class Not found");
            System.exit(-1);
        }
        try {
            clientSocket.close();
            serverSocket.close();
        }catch (IOException e) {
            System.out.println("Could not close");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.establish();
    }

}
