package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.DiceColor;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static void main(String args[]) {
        BoardLight boardLight = BoardLight.getInstance();
        List<DiceLight> diceLightList = new ArrayList<>();
        diceLightList.add(new DiceLight(DiceColor.BLACK, 1));
        diceLightList.add(new DiceLight(DiceColor.ORANGE, 2));
        diceLightList.add(new DiceLight(DiceColor.WHITE, 3));
        boardLight.setDiceLightList(diceLightList);
        System.out.println(pawnValueGivenPawnColor(PawnColor.BLACK));
    }

    public static ActionType getActionTypeByBoardIdentifier(BoardIdentifier boardIdentifier) {
        ActionType actionType = ActionType.UNDEFINED;
        if ('T' == boardIdentifier.toString().charAt(0)) {
            String towerIdentifier = boardIdentifier.toString().substring(0, 3);
            switch (towerIdentifier) {
                case "T_G":
                    actionType = ActionType.GREEN_TOWER;
                    break;
                case "T_Y":
                    actionType = ActionType.YELLOW_TOWER;
                    break;
                case "T_B":
                    actionType = ActionType.BLUE_TOWER;
                    break;
                case "T_P":
                    actionType = ActionType.PURPLE_TOWER;
                    break;
            }
        }
        switch (boardIdentifier) {
            case PRODUCTION_1:
            case PRODUCTION_2:
                actionType = ActionType.PRODUCTION;
                break;
            case HARVEST_1:
            case HARVEST_2:
                actionType = ActionType.HARVEST;
                break;
            case M_1:
            case M_2:
            case M_3:
            case M_4:
                actionType = ActionType.MARKET;
                break;
            case COUNCIL_PALACE:
                actionType = ActionType.COUNCIL_PALACE;
        }

        return actionType;
    }

    public static int pawnValueGivenPawnColor(PawnColor pawnColor) {
        if (pawnColor != PawnColor.NEUTRAL) {
            for (DiceLight diceLight : BoardLight.getInstance().getDiceLightList()) {
                if (diceLight.getDiceColor().toString().equals(pawnColor.toString())) {
                    return diceLight.getValue();
                }
            }
        }
        return 0;
    }
}
