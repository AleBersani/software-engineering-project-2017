package it.polimi.ingsw.client.gui.notify;


import it.polimi.ingsw.client.model.Card;

import java.util.List;
import java.util.Observable;

public class LeaderChoiceNotifier extends Observable {
    private LeaderChoiceNotifier() {}

    private static class LeaderChoiceNotifierHolder {
        private static final LeaderChoiceNotifier INSTANCE = new LeaderChoiceNotifier();
    }

    public static LeaderChoiceNotifier getInstance() {
        return LeaderChoiceNotifierHolder.INSTANCE;
    }

    public void updateGui(List<Card> leaders) {
        setChanged();
        notifyObservers(leaders);
    }
}
