package it.polimi.ingsw.client.gui.notify;

import it.polimi.ingsw.shared.support.GameStartType;

import java.util.Observable;

public class GameChoiceNotifier extends Observable {
    private GameChoiceNotifier() {}

    private static class GameChoiceNotifierHolder {
        private static final GameChoiceNotifier INSTANCE = new GameChoiceNotifier();
    }

    public static GameChoiceNotifier getInstance() {
        return GameChoiceNotifierHolder.INSTANCE;
    }

    public void updateGui(boolean accepted) {
        setChanged();
        notifyObservers(accepted);
    }
}
