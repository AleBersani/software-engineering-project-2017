package it.polimi.ingsw.server;

import it.polimi.ingsw.server.database.DBHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiverHandler {
    private String message;

    public ReceiverHandler(String message) {
        this.message = message;
    }

    public void run() {
        System.out.println(message);
        String[] messageSplit = message.split("_");
        DBHandler.connect();
        try {
            ResultSet resultSet = DBHandler.executeQuery("SELECT * FROM Users WHERE name = '" + messageSplit[0] +
                    "' AND password = '" + messageSplit[1] + "';");
            if (resultSet.next()) {
                System.out.println("Benvenuto: " + messageSplit[0]);
            } else {
                System.out.println("Utente non trovato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
