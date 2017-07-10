package it.polimi.ingsw.client.gui.notify.gameboardresponses;

public interface GameBoardVisitor {
    void visitGameBoardResponse(ActionResult actionResult);
    void visitGameBoardResponse(CouncilPrivilegeEvent councilPrivilegeEvent);
    void visitGameBoardResponse(TurnStatus turnStatus);
    void visitGameBoardResponse(UpdateBoard updateBoard);
}
