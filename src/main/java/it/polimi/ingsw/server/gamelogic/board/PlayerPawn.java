package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.Objects;

/**
 * Class that describes the Player's Pawns
 */
public class PlayerPawn {
    private PlayerDetails playerDetails;
    private PawnColor pawnColor;

    public PlayerPawn() {
        playerDetails = new PlayerDetails();
        pawnColor = PawnColor.UNCOLORED;
    }

    public PlayerPawn(PlayerDetails playerDetails, PawnColor pawnColor) {
        this.playerDetails = playerDetails;
        this.pawnColor = pawnColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerPawn that = (PlayerPawn) o;
        return Objects.equals(getPlayerDetails(), that.getPlayerDetails()) &&
                getPawnColor() == that.getPawnColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerDetails(), getPawnColor());
    }

    /**
     * Checks if the player details are empty
     * @return true if the player details are empty
     */
    public boolean isEmpty() {
        return playerDetails.isEmpty() &&
                pawnColor == PawnColor.UNCOLORED;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerDetails playerDetails) {
        this.playerDetails = playerDetails;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }
}
