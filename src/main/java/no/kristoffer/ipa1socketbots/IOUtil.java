package no.kristoffer.ipa1socketbots;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public final class IOUtil {

    // Lage egen metode for å close

    private IOUtil() {
    }

    public static void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public static void closeEverything(Socket socket, BufferedReader bufferedReader, DataOutputStream dataOutputStream) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
