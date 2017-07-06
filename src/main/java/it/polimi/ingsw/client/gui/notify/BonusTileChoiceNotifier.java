package it.polimi.ingsw.client.gui.notify;

import java.util.List;
import java.util.Observable;

public class BonusTileChoiceNotifier extends Observable {
    private BonusTileChoiceNotifier() {}

    private static class BonusTileChoiceNotifierHolder {
        private static final BonusTileChoiceNotifier INSTANCE = new BonusTileChoiceNotifier();
    }

    public static BonusTileChoiceNotifier getInstance() {
        return BonusTileChoiceNotifierHolder.INSTANCE;
    }

    public void updateGui(List<String> bonusTiles) {
        setChanged();
        notifyObservers(bonusTiles);
    }

    public void updateGui() {
        setChanged();
        notifyObservers();
    }
}
