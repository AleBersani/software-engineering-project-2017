package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.model.Utils;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BasicAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.requests.clientserver.*;

public class RequestsGeneratorCli {
    private BaseInformation baseInformation;


    public RequestsGeneratorCli() {
        baseInformation = new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName());
    }

    public ChosenLeader generateLeaderChoice(String leaderName) {
        return new ChosenLeader(baseInformation, leaderName);
    }

    public ChosenBonusTile generateChosenBonusTile(String bonusTileIdentifier) {
        return new ChosenBonusTile(baseInformation, bonusTileIdentifier);
    }

    public PawnPlacement generatePawnPlacement(String boardIdentifier, String pawnColor, int numberOfServants) {
        int actionValue = Utils.pawnValueGivenPawnColor(PawnColor.valueOf(pawnColor));
        ActionDescription actionDescription = new BoardAction(
                new BasicAction(Utils.getActionTypeByBoardIdentifier(BoardIdentifier.valueOf(boardIdentifier)),
                        BoardIdentifier.valueOf(boardIdentifier), actionValue),
                PawnColor.valueOf(pawnColor), numberOfServants);
        return new PawnPlacement(baseInformation, actionDescription);
    }

    public LeaderAction generateLeaderAction(ActionType actionType, String leaderCardName) {
        return new LeaderAction(actionType, leaderCardName);
    }
}
