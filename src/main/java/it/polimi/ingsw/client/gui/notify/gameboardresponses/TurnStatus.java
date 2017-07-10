package it.polimi.ingsw.client.gui.notify.gameboardresponses;


public class TurnStatus implements GameBoardResponse {
    private boolean isMyTurn;

    public TurnStatus(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }

    @Override
    public void acceptGameBoardVisitor(GameBoardVisitor gameBoardVisitor) {
        gameBoardVisitor.visitGameBoardResponse(this);
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }
}
