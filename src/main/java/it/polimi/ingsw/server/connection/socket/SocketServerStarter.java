package it.polimi.ingsw.server.connection.socket;

public class SocketServerStarter implements Runnable {
    @Override
    public void run() {
        SocketServer socketServer = SocketServer.getSocketServer();
        socketServer.startServer();
    }
}
