package it.polimi.ingsw.client.gui.notify;

import java.util.Observable;

public class PlayerLoginNotifier extends Observable {
    private PlayerLoginNotifier() {}

    private static class PlayerLoginNotifierHolder {
        private static final PlayerLoginNotifier INSTANCE = new PlayerLoginNotifier();
    }

    public static PlayerLoginNotifier getInstance() {
        return PlayerLoginNotifierHolder.INSTANCE;
    }

    public void updateGui(boolean successful) {
        setChanged();
        notifyObservers(successful);
    }
}
