package it.polimi.ingsw.gamelogic.actions;

/**
 * Class that represent every action related to a Leader Card, in particular:
 *      - placement of a Leader
 *      - activation of a Leader
 *      - discard of a Leader
 */
public class LeaderAction implements ActionSupplement {
    private String leaderName;

    public LeaderAction(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
