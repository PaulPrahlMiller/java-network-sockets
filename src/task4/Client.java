package task4;

import echoServer.EchoClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public Client() {}
    public void establish() {
        Socket echoSocket = null;
        ObjectOutputStream out = null;
        try {
            echoSocket = new Socket("192.168.8.106", 8080, InetAddress.getLocalHost(), 5000);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O");
            System.exit(1);
        }
        try{
            PersistentTime time = new PersistentTime();
            out.writeObject(time);
            out.close();
            echoSocket.close();
        } catch (IOException ioe) {
            System.out.println("Failed");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.establish();
    }
}
