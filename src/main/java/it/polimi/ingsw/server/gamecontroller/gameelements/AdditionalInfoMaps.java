package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.AdditionalCardInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalInfoMaps {
    private static Map<String, List<AdditionalCardInfo>> flashEffectsOnChoice;
    private static Map<String, List<AdditionalCardInfo>> flashEffectsNotSelectable;
    private static Map<String, List<AdditionalCardInfo>> permanentEffectsOnChoice;
    private static Map<String, List<AdditionalCardInfo>> permanentEffectsNotSelectable;

    public static void initializeMaps() {
        flashEffectsOnChoice = new HashMap<>();
        flashEffectsNotSelectable = new HashMap<>();
        permanentEffectsOnChoice = new HashMap<>();
        permanentEffectsNotSelectable = new HashMap<>();
    }

    public static Map<String, List<AdditionalCardInfo>> getFlashEffectsOnChoice() {
        return flashEffectsOnChoice;
    }

    public static void setFlashEffectsOnChoice(Map<String, List<AdditionalCardInfo>> flashEffectsOnChoice) {
        AdditionalInfoMaps.flashEffectsOnChoice = flashEffectsOnChoice;
    }

    public static Map<String, List<AdditionalCardInfo>> getFlashEffectsNotSelectable() {
        return flashEffectsNotSelectable;
    }

    public static void setFlashEffectsNotSelectable(Map<String, List<AdditionalCardInfo>> flashEffectsNotSelectable) {
        AdditionalInfoMaps.flashEffectsNotSelectable = flashEffectsNotSelectable;
    }

    public static Map<String, List<AdditionalCardInfo>> getPermanentEffectsOnChoice() {
        return permanentEffectsOnChoice;
    }

    public static void setPermanentEffectsOnChoice(Map<String, List<AdditionalCardInfo>> permanentEffectsOnChoice) {
        AdditionalInfoMaps.permanentEffectsOnChoice = permanentEffectsOnChoice;
    }

    public static Map<String, List<AdditionalCardInfo>> getPermanentEffectsNotSelectable() {
        return permanentEffectsNotSelectable;
    }

    public static void setPermanentEffectsNotSelectable(Map<String, List<AdditionalCardInfo>> permanentEffectsNotSelectable) {
        AdditionalInfoMaps.permanentEffectsNotSelectable = permanentEffectsNotSelectable;
    }
}
