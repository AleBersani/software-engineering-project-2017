package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    private Socket socket;

    private Scanner input;
    private PrintWriter output;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream());
            while (true) {
                String line = input.nextLine();
                if (line.equals("quit")) {
                    break;
                } else {
                    ReceiverHandler receiverHandler = new ReceiverHandler(line);
                    receiverHandler.run();
                }
            }
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
