package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalInfoMaps {
    public static Map<String, List<AdditionalCardInfo>> flashEffectsOnChoice;
    public static Map<String, List<AdditionalCardInfo>> flashEffectsNotSelectable;
    public static Map<String, List<AdditionalCardInfo>> permanentEffectsOnChoice;
    public static Map<String, List<AdditionalCardInfo>> permanentEffectsNotSelectable;

    public static void initializeMaps() {
        flashEffectsOnChoice = new HashMap<>();
        flashEffectsNotSelectable = new HashMap<>();
        permanentEffectsOnChoice = new HashMap<>();
        permanentEffectsNotSelectable = new HashMap<>();
    }
}
