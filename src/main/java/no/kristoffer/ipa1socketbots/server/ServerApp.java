package no.kristoffer.ipa1socketbots.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApp {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Server server = new Server(serverSocket);

        // Take arguments in main
        // 1 How to listen to inputs from the terminal
        // 2 How to run the code waiting for clients in its own thread
        // 3 Take what's written from terminal and send to clients
        // 4 Don't wanna close socket everytime I read and write, wanna keep socket open until I kick a client
        // 5 How to listen to commands etc ctrl+c

        server.connectClients();



    }
}
