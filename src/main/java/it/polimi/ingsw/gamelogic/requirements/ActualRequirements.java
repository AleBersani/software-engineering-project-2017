package it.polimi.ingsw.gamelogic.requirements;

import it.polimi.ingsw.gamelogic.basics.LeaderCost;

public class ActualRequirements implements Requirements{
    private LeaderCost leaderCost;
    private int actionValue;
    private boolean spaceAvailability;
    /*
        TODO: insert an ActionDescription? We need number of pawns, too
        TODO: constructors. Differentiate between different kinds (combo) of requirements
     */

    @Override
    public boolean hasRequirements() {
        /*
            TODO:
         */
        return true;
    }
}
