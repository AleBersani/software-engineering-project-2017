package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;

import java.util.ArrayList;
import java.util.List;

public class CouncilPalace {
    private ExchangingGoods instantGoods;
    private int requiredValue;

    private List<String> playerOrder;

    public CouncilPalace(ExchangingGoods instantGoods, int requiredValue) {
        this.instantGoods = instantGoods;
        this.requiredValue = requiredValue;
        playerOrder = new ArrayList<>();
    }

    /**
     * It adds a player to the Player Order list if the player enters
     * the Council Palace for the first time in the game
     * @param player Name of the player added to the list
     **/
    public void addToPlayerOrder(String player) {
        if (playerOrder.isEmpty() || !isAlreadyPresent(player))
            playerOrder.add(player);
    }

    /**
     * It controls if the player is already in the Player Order list
     **/
    private boolean isAlreadyPresent(String player) {
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

    public List<String> getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(List<String> playerOrder) {
        this.playerOrder = playerOrder;
    }
}
