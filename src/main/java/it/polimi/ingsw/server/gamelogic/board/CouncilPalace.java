package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that describes the Council Palace space, where if the player puts his/her pawn he/she gets a Council's
 * Privilege that can be converted into a Good chosen by the player
 */
public class CouncilPalace {
    private ExchangingGoods instantGoods;
    private int requiredValue;

    private List<PlayerDetails> playerOrder;
    private List<PlayerPawn> playerPawnList;

    public CouncilPalace() {
        instantGoods = new ExchangingGoods();
        requiredValue = 0;
        playerOrder = new ArrayList<>();
        playerPawnList = new ArrayList<>();
    }

    public CouncilPalace(CouncilPalace councilPalace) {
        instantGoods = councilPalace.instantGoods;
        requiredValue = councilPalace.requiredValue;
        playerOrder = new ArrayList<>();
        playerPawnList = new ArrayList<>();
    }

    public CouncilPalace(ExchangingGoods instantGoods, int requiredValue) {
        this.instantGoods = instantGoods;
        this.requiredValue = requiredValue;
        playerOrder = new ArrayList<>();
        playerPawnList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CouncilPalace that = (CouncilPalace) o;
        return getRequiredValue() == that.getRequiredValue() &&
                Objects.equals(getInstantGoods(), that.getInstantGoods()) &&
                Objects.equals(getPlayerOrder(), that.getPlayerOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstantGoods(), getRequiredValue(), getPlayerOrder());
    }

    /**
     * It adds a player to the Player Order list if the player enters
     * the Council Palace for the first time in the game
     * @param player Name of the player added to the list
     **/
    public void addToPlayerOrder(PlayerDetails player) {
        if (playerOrder.isEmpty() || !isAlreadyPresent(player))
            playerOrder.add(player);
    }

    /**
     * It controls if the player is already in the Player Order list
     * @param player player details
     * @return true if the player is already present
     */
    private boolean isAlreadyPresent(PlayerDetails player) {
        return playerOrder.stream()
                .anyMatch(e -> e.equals(player));
    }

    public ExchangingGoods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
    }

    public int getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(int requiredValue) {
        this.requiredValue = requiredValue;
    }

    public List<PlayerDetails> getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(List<PlayerDetails> playerOrder) {
        this.playerOrder = playerOrder;
    }

    public List<PlayerPawn> getPlayerPawnList() {
        return playerPawnList;
    }

    public void setPlayerPawnList(List<PlayerPawn> playerPawnList) {
        this.playerPawnList = playerPawnList;
    }
}
