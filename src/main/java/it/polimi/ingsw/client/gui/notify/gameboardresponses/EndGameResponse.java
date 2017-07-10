package it.polimi.ingsw.client.gui.notify.gameboardresponses;

public class EndGameResponse implements GameBoardResponse {
    @Override
    public void acceptGameBoardVisitor(GameBoardVisitor gameBoardVisitor) {
        gameBoardVisitor.visitGameBoardResponse(this);
    }
}
