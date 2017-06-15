package it.polimi.ingsw.server;

import it.polimi.ingsw.shared.Registrable;

import java.net.Socket;
import java.util.Optional;

public class ConnectionStream {
    private Socket socket;
    private Registrable registrable;

    public ConnectionStream() {
        socket = null;
        registrable = null;
    }

    public ConnectionStream(Socket socket) {
        this.socket = socket;
        registrable = null;
    }

    public ConnectionStream(Registrable registrable) {
        this.registrable = registrable;
        socket = null;
    }

    public Optional<Socket> getSocket() {
        if (socket == null)
            return Optional.empty();
        return Optional.of(socket);
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Optional<Registrable> getRegistrable() {
        if (registrable == null)
            return Optional.empty();
        return Optional.of(registrable);
    }

    public void setRegistrable(Registrable registrable) {
        this.registrable = registrable;
    }
}
