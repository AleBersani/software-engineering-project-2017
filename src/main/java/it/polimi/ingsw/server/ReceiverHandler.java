package it.polimi.ingsw.server;

public class ReceiverHandler {
    private Object object;

    public ReceiverHandler(Object object) {
        this.object = object;
    }

    public void run() {
        System.out.println(object.toString());
        /*
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
        }*/
    }
}
