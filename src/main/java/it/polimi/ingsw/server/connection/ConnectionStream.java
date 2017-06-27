package it.polimi.ingsw.server.connection;

import it.polimi.ingsw.shared.support.Registrable;

import java.io.ObjectOutputStream;
import java.util.Optional;

public class ConnectionStream {
    private ObjectOutputStream objectOutputStream;
    private Registrable registrable;

    public ConnectionStream() {
        objectOutputStream = null;
        registrable = null;
    }

    public ConnectionStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
        registrable = null;
    }

    public ConnectionStream(Registrable registrable) {
        this.registrable = registrable;
        objectOutputStream = null;
    }

    public Optional<ObjectOutputStream> getObjectOutputStream() {
        if (objectOutputStream == null)
            return Optional.empty();
        return Optional.of(objectOutputStream);
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
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
