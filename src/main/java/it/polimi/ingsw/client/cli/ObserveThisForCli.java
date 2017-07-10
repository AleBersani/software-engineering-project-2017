package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.model.Card;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ObserveThisForCli implements Runnable{
    private AtomicBoolean variableToObserve;


    public ObserveThisForCli(AtomicBoolean variableToObserve) {
        this.variableToObserve = variableToObserve;
    }

    @Override
    public void run() {
        if (variableToObserve.get()) {
            while(variableToObserve.get()) {
            }
            return;
        } else {
            while(!variableToObserve.get()){
            }
            return;
        }
    }


}
