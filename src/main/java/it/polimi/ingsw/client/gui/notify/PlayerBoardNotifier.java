package it.polimi.ingsw.client.gui.notify;

import java.util.Observable;

public class PlayerBoardNotifier extends Observable {
    private PlayerBoardNotifier() {}

    private static class PlayerBoardNotifierHolder {
        private static final PlayerBoardNotifier INSTANCE = new PlayerBoardNotifier();
    }

    public static PlayerBoardNotifier getInstance() {
        return PlayerBoardNotifierHolder.INSTANCE;
    }

    public void updateGui() {

    }
}
