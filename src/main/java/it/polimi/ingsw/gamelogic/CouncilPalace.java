import java.util.ArrayList;
import java.util.List;

public class CouncilPalace {
    private List<String> playerOrder;
    //private ExchangingGoods instantGoods;
    private int requiredValue;

    public static void main(String argv[]) {
        CouncilPalace councilPalace = new CouncilPalace(1);
        councilPalace.addToPlayerOrder("Dennis");
        councilPalace.addToPlayerOrder("Fabri");
        councilPalace.addToPlayerOrder("Cami");
        System.out.println(councilPalace.isAlreadyPresent("Fabri"));
    }

    public CouncilPalace(/*ExchangingGoods instantGoods,*/ int requiredValue) {
        //this.instantGoods = instantGoods;
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
        for (String playerName : playerOrder)
            if(playerName.equals(player))
                return true;

        //playerOrder.stream().anyMatch(s -> s.equals(player));
        return false;
    }

    public List<String> getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(List<String> playerOrder) {
        this.playerOrder = playerOrder;
    }
/*
    public Goods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(Goods instantGoods) {
        this.instantGoods = instantGoods;
    }
*/
    public int getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(int requiredValue) {
        this.requiredValue = requiredValue;
    }
}
