package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.middleware.ServerReceiver;
import it.polimi.ingsw.shared.support.GameStartType;
import it.polimi.ingsw.shared.support.Registrable;

import java.io.Serializable;

public class GameStartChoiceRMI implements Serializable, ClientServerRequest {
    private GameStartChoice gameStartChoice;
    private Registrable registrable;

    public GameStartChoiceRMI(GameStartChoice gameStartChoice) {
        this.gameStartChoice = gameStartChoice;
    }

    @Override
    public void acceptServerReceiver(ServerReceiver serverReceiver) {
        serverReceiver.visitClientServerRequest(this);
    }

    public GameStartChoiceRMI(GameStartChoice gameStartChoice, Registrable registrable) {
        this.gameStartChoice = gameStartChoice;
        this.registrable = registrable;
    }

    public String getPlayerName() {
        return gameStartChoice.getPlayerName();
    }

    public GameStartType getGameStartType() {
        return gameStartChoice.getGameStartType();
    }

    public GameStartChoice getGameStartChoice() {
        return gameStartChoice;
    }

    public void setGameStartChoice(GameStartChoice gameStartChoice) {
        this.gameStartChoice = gameStartChoice;
    }

    public Registrable getRegistrable() {
        return registrable;
    }

    public void setRegistrable(Registrable registrable) {
        this.registrable = registrable;
    }
}
