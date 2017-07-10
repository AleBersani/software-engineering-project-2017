package it.polimi.ingsw.client.gui.notify.gameboardresponses;

public interface GameBoardVisitor {
    void visitGameBoardResponse(ActionResult actionResult);
    void visitGameBoardResponse(ConsumableActionChoice consumableActionChoice);
    void visitGameBoardResponse(CouncilPrivilegeEvent councilPrivilegeEvent);
    void visitGameBoardResponse(LorenzoChoice lorenzoChoice);
    void visitGameBoardResponse(TurnStatus turnStatus);
    void visitGameBoardResponse(UpdateBoard updateBoard);
}
