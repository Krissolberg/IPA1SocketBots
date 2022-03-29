package no.kristoffer.ipa1socketbots.client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

import static no.kristoffer.ipa1socketbots.IOUtil.closeEverything;

public class Client {

//    private Socket socket;
//    private BufferedReader bufferedReader;
//    private BufferedWriter bufferedWriter;

    // Usikker på om det er nødvendig å initialisere attributene

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Arguments list should be 3: IP, PORT, BOT");
            System.exit(1);
        }

        String[] verbs = {"eat", "walk", "talk", "drink", "play", "code", "run", "cry", "laugh"};

        try {
            System.out.println("no.kristoffer.ipa1socketbots.client.Client started");
            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);



            bufferedReader.lines().forEach( line -> {

                String[] words = line.split("\\W+");
                String action = "";

                for (int i = 0; i < words.length; ++i) {
                    int finalI = i;
                    boolean verbMatch = Arrays.stream(verbs).anyMatch(verb -> words[finalI].equals(verb));
                    if (verbMatch) {
                        action = words[finalI];
                    }
                }
                try {
                    switch (args[2]) {
                        case "alice": dataOutputStream.writeUTF(Bot.alice(action));
                        case "bob": dataOutputStream.writeUTF(Bot.bob(action));
                        default: {
                            System.out.printf("No BOT is named %s%n", args[2]);
                            System.exit(1);
                        }
                    }

                    dataOutputStream.flush();
                    dataOutputStream.close();
                    System.out.println(line);
                } catch(IOException e) {
                    e.printStackTrace();
                    closeEverything(socket, bufferedReader, dataOutputStream);
                }
            });

            bufferedReader.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}