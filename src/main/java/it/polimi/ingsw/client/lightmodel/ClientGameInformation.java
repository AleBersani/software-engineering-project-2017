package it.polimi.ingsw.client.lightmodel;

public class ClientGameInformation {
    private BoardLight boardLight;
    private PlayerLight playerLight;

    public ClientGameInformation(BoardLight boardLight, PlayerLight playerLight) {
        this.boardLight = boardLight;
        this.playerLight = playerLight;
    }

    public BoardLight getBoardLight() {
        return boardLight;
    }

    public void setBoardLight(BoardLight boardLight) {
        this.boardLight = boardLight;
    }

    public PlayerLight getPlayerLight() {
        return playerLight;
    }

    public void setPlayerLight(PlayerLight playerLight) {
        this.playerLight = playerLight;
    }
}
