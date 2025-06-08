
package main.java.com.theorigin.view;

import java.io.*;
import java.net.*;

public class ChatClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader stdIn;

    public static void main(String[] args) {
        String serverIpAddress = "localhost"; // Replace with actual server IP
        int serverPort = 1234; // Ensure this matches the server port

        try (Socket socket = new Socket(serverIpAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server");

            // Read the initial message from the server (e.g., username prompt) and send username
            String serverMessage = in.readLine();
            if (serverMessage != null) {
                System.out.println(serverMessage);
                String username = stdIn.readLine();
                out.println(username);
            }

            // Thread to read messages from the server
            Thread readMessagesThread = new Thread(() -> {
                try {
                    String receivedMessage;
                    while ((receivedMessage = in.readLine()) != null) {
                        System.out.println(receivedMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            });

            // Thread to send messages to the server
            Thread sendMessagesThread = new Thread(() -> {
                try {
                    String messageToSend;
                    while ((messageToSend = stdIn.readLine()) != null) {
                        out.println(messageToSend);
                    }
                } catch (IOException e) {
                    System.err.println("Error sending to server: " + e.getMessage());
                }
            });

            readMessagesThread.start();
            sendMessagesThread.start();

            // Keep the main thread alive until the user disconnects
            readMessagesThread.join();
            sendMessagesThread.join();

        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
