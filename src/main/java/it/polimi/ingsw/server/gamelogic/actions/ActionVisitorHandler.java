package it.polimi.ingsw.server.gamelogic.actions;

import it.polimi.ingsw.server.gamelogic.actions.description.BoardAction;
import it.polimi.ingsw.server.gamelogic.actions.description.CardAction;
import it.polimi.ingsw.server.gamelogic.actions.description.LeaderAction;
import it.polimi.ingsw.server.gamelogic.player.Player;

public class ActionVisitorHandler implements ActionVisitor {
    private Action action;
    private Player player;

    public ActionVisitorHandler(Action action, Player player) {
        this.action = action;
        this.player = player;
    }

    @Override
    public void visitActionDescription(BoardAction boardAction) {

    }

    @Override
    public void visitActionDescription(CardAction cardAction) {

    }

    @Override
    public void visitActionDescription(LeaderAction leaderAction) {

    }
}
