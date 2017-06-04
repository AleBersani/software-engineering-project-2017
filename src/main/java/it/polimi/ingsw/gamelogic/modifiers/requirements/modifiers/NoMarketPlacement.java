package it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers;

import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.BoardActionRequirements;

/**
 * TODO: JavaDoc
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
