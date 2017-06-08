package it.polimi.ingsw.client;

import java.util.Scanner;

public class SocketReader implements Runnable {
    private Scanner socketInput;

    public SocketReader(Scanner socketInput) {
        this.socketInput = socketInput;
    }

    @Override
    public void run() {
        while (true) {
            String socketLine = socketInput.nextLine();
            System.out.println(socketLine);
        }
    }
}
