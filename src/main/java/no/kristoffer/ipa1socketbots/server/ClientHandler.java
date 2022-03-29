package no.kristoffer.ipa1socketbots.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static no.kristoffer.ipa1socketbots.IOUtil.closeEverything;

public class ClientHandler {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void readFromClient() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void writeMessage(String messageToSend) {
        try {
            this.bufferedWriter.write(messageToSend);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }



    public void closeClient() throws IOException {
        closeEverything(socket, bufferedReader, bufferedWriter);
    }
}
