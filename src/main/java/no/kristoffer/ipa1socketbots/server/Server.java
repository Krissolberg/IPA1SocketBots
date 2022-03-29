package no.kristoffer.ipa1socketbots.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static no.kristoffer.ipa1socketbots.IOUtil.closeEverything;

public class Server {

    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Thread connectionThread;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void createGracefullyShutdown () {
        // This method will be called when ctrl+c is pressed in the console
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeServerSocket);
    }

    public void connectClients() {
        connectionThread = new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {

                    System.out.println("Waiting for clients...");
                    Socket socket = serverSocket.accept();
                    System.out.println("Connection established");
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clientHandlers.add(clientHandler);

                }

            } catch(IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                for (ClientHandler clientHandler : clientHandlers) {
                    clientHandler.closeClient();
                }
                serverSocket.close();
                connectionThread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.writeMessage(messageToSend);
        }
    }

}
