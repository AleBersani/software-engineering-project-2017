package it.polimi.ingsw.client.gui.notify.gameboardresponses;

public class ActionResult implements GameBoardResponse {
    private boolean result;

    public ActionResult(boolean result) {
        this.result = result;
    }

    @Override
    public void acceptGameBoardVisitor(GameBoardVisitor gameBoardVisitor) {
        gameBoardVisitor.visitGameBoardResponse(this);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
