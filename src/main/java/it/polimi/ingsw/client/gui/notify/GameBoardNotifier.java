package it.polimi.ingsw.client.gui.notify;

import java.util.Observable;

public class GameBoardNotifier extends Observable {
    private GameBoardNotifier() {}

    private static class GameBoardNotifierHolder {
        private final static GameBoardNotifier INSTANCE = new GameBoardNotifier();
    }

    public static GameBoardNotifier getInstance() {
        return GameBoardNotifierHolder.INSTANCE;
    }

    public void updateGui() {
        setChanged();
        notifyObservers();
    }
}
