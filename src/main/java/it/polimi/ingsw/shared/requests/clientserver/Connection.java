package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;

import java.io.Serializable;

public class Connection implements Serializable, ClientServerRequest {
    private String name;
    private String psw;

    public Connection(String name, String password) {
        this.name = name;
        this.psw = password;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    @Override
    public void acceptClientServerRequestVisitor(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String password) {
        this.psw = password;
    }
}
