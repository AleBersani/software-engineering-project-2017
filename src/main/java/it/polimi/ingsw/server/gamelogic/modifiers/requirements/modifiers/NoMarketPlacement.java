package it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;

/**
 * Class that represents the effect of an Excommunication Tile that prevents the player to place any of his pawns
 * in the Market Slots
 */
public class NoMarketPlacement extends RequirementsModifier {
    public NoMarketPlacement(AvailableActions availableActions) {
        super(availableActions);
    }

    @Override
    public BoardActionRequirements modifyRequirements(BoardActionRequirements boardActionRequirements) {
        if (availableActions.hasAvailableAction(boardActionRequirements.getActionType())) {
            BoardActionRequirements noMarketPlacementBoardActionModifier = boardActionRequirements;
            noMarketPlacementBoardActionModifier.setCanPlace(false);
            return noMarketPlacementBoardActionModifier;
        }
        return boardActionRequirements;
    }
}