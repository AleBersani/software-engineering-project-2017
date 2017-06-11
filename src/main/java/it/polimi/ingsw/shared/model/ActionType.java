package it.polimi.ingsw.shared.model;

import java.io.Serializable;

/**
 * Enum that describes the types of action th eplayer can perform
 */
public enum ActionType implements Serializable {
    GREEN_TOWER,
    YELLOW_TOWER,
    BLUE_TOWER,
    PURPLE_TOWER,
    COUNCIL_PALACE,
    PRODUCTION,
    HARVEST,
    MARKET,
    LEADER_PLACEMENT,
    LEADER_ACTIVATION,
    LEARD_DISCARD
}
