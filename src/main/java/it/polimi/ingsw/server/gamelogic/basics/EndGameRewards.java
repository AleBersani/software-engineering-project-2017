package it.polimi.ingsw.server.gamelogic.basics;

import java.util.List;
import java.util.Map;

/**
 * This is the class that counts Victory points at the end of the game. Victory points can come from:-
 *  - the number of Goods of the player
 *  - the number of some kind of cards
 *  - the number of Faith and Military Points
 */
public class EndGameRewards {
    private Map<String, List<GoodsForEndGamePossession>> rewardsForPossessions;

    public EndGameRewards(Map<String, List<GoodsForEndGamePossession>> rewardsForPossessions) {
        this.rewardsForPossessions = rewardsForPossessions;
    }

    public Map<String, List<GoodsForEndGamePossession>> getRewardsForPossessions() {
        return rewardsForPossessions;
    }

    public void setRewardsForPossessions(Map<String, List<GoodsForEndGamePossession>> rewardsForPossessions) {
        this.rewardsForPossessions = rewardsForPossessions;
    }
}
