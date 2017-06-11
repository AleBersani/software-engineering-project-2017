package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.exectutionmiddleware.ClientServerRequestVisitor;

import java.io.Serializable;

public class Connection implements Serializable, ClientServerRequest {
    private String name;
    private String password;

    public Connection(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void acceptClientServerRequestVisitor(ClientServerRequestVisitor clientServerRequestVisitor) {
        clientServerRequestVisitor.visitClientServerRequest(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
