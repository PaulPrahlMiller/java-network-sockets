package multiClientServer;

import echoServer.EchoClient;

public class ClientMain {
    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        echoClient.establish();
    }
}
