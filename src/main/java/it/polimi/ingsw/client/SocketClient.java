package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class SocketClient {
    private final static Logger LOGGER = Logger.getLogger(SocketClient.class.getName());

    private String ip;
    private int port;

    private Socket socket;
    private Scanner socketInput;
    private PrintWriter socketOutput;

    public static void main(String argv[]) {
        SocketClient socketClient = new SocketClient("127.0.0.1", 6677);
        try {
            socketClient.startSocketClient();
        } catch (IOException e) {
            LOGGER.info("Connection Error");
        }
    }

    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startSocketClient() throws IOException {
        socket = new Socket(ip, port);
        LOGGER.info("Connection established");
        socketInput = new Scanner(socket.getInputStream());
        socketOutput = new PrintWriter(socket.getOutputStream());
        try {
            readSocket();
        } catch(NoSuchElementException e) {
            LOGGER.info("Connection closed");
        } finally {
            closeConnection();
        }
    }

    private void readSocket() {
        while (true) {
            String socketLine = socketInput.nextLine();
            System.out.println(socketLine);
        }
    }

    private void closeConnection() {
        socketInput.close();
        socketOutput.close();
        try {
            socket.close();
        } catch (IOException e) {
            LOGGER.info("Error while closing connection");
        }
    }

    public void writeSocket(String message) {
        socketOutput.println(message);
        socketOutput.flush();
    }
}
