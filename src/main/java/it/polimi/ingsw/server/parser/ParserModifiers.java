package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers.*;
import it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ParserModifiers {
    private static final String PAWN_COLOR = "pawnColor";
    private static final String MALUS_ACTION_VALUE = "malusActionValue";

    public List<RequirementsModifier> parseListRequirementsModifier(List<String> modifiers, JsonObject card) throws Exception {
        List<RequirementsModifier> modifierList = new ArrayList<>();
        int bonusPawnValueIndex = 0;
        int fixedPawnValueIndex = 0;
        int bonusIndex = 0;
        Map<String, Callable<RequirementsModifier>> commands = constructRequirementsMap(card);
        for (String key : modifiers) {
            if ("bonusOnCardCost".equals(key)) {
                modifierList.add(parseBonusOnCardCost(card, bonusIndex));
                bonusIndex++;
            } else if ("bonusPawnValue".equals(key)) {
                modifierList.add(parseBonusPawnValue(card, bonusPawnValueIndex));
                bonusPawnValueIndex++;
            } else if ("fixedColouredPawnValue".equals(key)) {
                modifierList.add(parseFixedColouredPawnValue(card, fixedPawnValueIndex));
                fixedPawnValueIndex++;
            } else
                modifierList.add(commands.get(key).call());
        }
        return modifierList;
    }

    public RewardsModifier parseRewardsModifier(List<String> modifiers, JsonObject card) throws Exception {
        Map<String, Callable<RewardsModifier>> commands = new HashMap<>();
        commands.put("bonusRewards", ()->parseBonusRewards(card));
        commands.put("doubleRewards", ()->parseDoubleRewards(card));
        commands.put("malusRewards", ()->parseMalusRewards(card));
        commands.put("noTowerBonusRewards", ()->parseNoTowerBonusRewards(card));

        return commands.get(modifiers.get(0)).call();
    }

    private RequirementsModifier parseBonusActionValue(JsonObject card) {
        int bonusValue = card.get("bonusValue").getAsInt();
        List<ActionType> availableActions = parseListActionType(card);
        return new BonusActionValue(new AvailableActions(availableActions), bonusValue);
    }

    private RequirementsModifier parseBonusOnCardCost(JsonObject card, int bonusIndex) {
        Gson gson = new Gson();
        List<ActionType> availableActions = parseListActionType(card);
        JsonObject bonus = card.get("bonus").getAsJsonArray().get(bonusIndex).getAsJsonObject();
        Goods parsedBonus = gson.fromJson(bonus, Goods.class);
        return new BonusOnCardCost(new AvailableActions(availableActions), parsedBonus);
    }

    private RequirementsModifier parseBonusPawnsValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int bonusPawnValue = card.get("actionValueSurplus").getAsInt();
        return new BonusPawnsValue(new AvailableActions(availableActions),
                                                            pawnColors, bonusPawnValue);
    }

    private RequirementsModifier parseBonusPawnValue(JsonObject card, int bonusPawnValueIndex) {
        List<ActionType> availableActions = parseListActionType(card);
        PawnColor pawnColor = parsePawnColor(card.get(PAWN_COLOR).getAsJsonArray().
                                                get(bonusPawnValueIndex).getAsString());
        int bonusActionValue = card.get("actionValueSurplus").getAsInt();
        return new BonusPawnValue(new AvailableActions(availableActions),
                                                            pawnColor, bonusActionValue);
    }

    private RequirementsModifier parseCanPlaceOnOccupiedSpace(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new CanPlaceOnOccupiedSpace(new AvailableActions(availableActions));
    }

    private RequirementsModifier parseDoubleServants(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new DoubleServants(new AvailableActions(availableActions));
    }

    private RequirementsModifier parseFixedColouredPawnsValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int pawnsValue = card.get("pawnsValue").getAsInt();
        return new FixedColouredPawnsValue(new AvailableActions(availableActions),
                                                                    pawnColors, pawnsValue);
    }

    private RequirementsModifier parseFixedColouredPawnValue(JsonObject card, int fixedPawnValueIndex) {
        List<ActionType> availableActions = parseListActionType(card);
        PawnColor pawnColor = parsePawnColor(card.get(PAWN_COLOR).getAsJsonArray().get(fixedPawnValueIndex).getAsString());
        int pawnValue = card.get("pawnValue").getAsInt();
        return new FixedColouredPawnValue(new AvailableActions(availableActions),
                                                                    pawnColor, pawnValue);
    }

    private RequirementsModifier parseMalusActionValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        int malusValue = card.get(MALUS_ACTION_VALUE).getAsInt();
        return new MalusActionValue(new AvailableActions(availableActions), malusValue);
    }

    private RequirementsModifier parseMalusColouredPawns(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int malusValue = card.get(MALUS_ACTION_VALUE).getAsInt();
        return new MalusColouredPawns(new AvailableActions(availableActions),
                                                                pawnColors, malusValue);
    }

    private RequirementsModifier parseNoBonusGoodsOnTower(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new NoBonusGoodsOnTower(new AvailableActions(availableActions));
    }

    private RequirementsModifier parseNoMarketPlacement(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new NoMarketPlacement(new AvailableActions(availableActions));
    }

    private RequirementsModifier parseNoMilitaryForTerritories(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new NoMilitaryForTerritories(new AvailableActions(availableActions));
    }

    private RequirementsModifier parseNoOccupiedTowerCost(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new NoOccupiedTowerCost(new AvailableActions(availableActions));
    }

    /**
     * TODO DA RIVEDERE
     * @param card //
     * @return //
     */
    private RewardsModifier parseBonusRewards(JsonObject card) {
        Gson gson = new Gson();
        List<ActionType> availableActions = parseListActionType(card);
        Goods bonus = gson.fromJson(card.get("bonus").getAsJsonArray().get(0).getAsJsonObject(), Goods.class);
        return new BonusRewards(new AvailableActions(availableActions), bonus);
    }

    private RewardsModifier parseDoubleRewards(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new DoubleRewards(new AvailableActions(availableActions));
    }

    /**
     * @param card //
     * @return //
     */
    private RewardsModifier parseMalusRewards(JsonObject card) {
        Gson gson = new Gson();
        JsonObject goods = card.get("rewardsMalus").getAsJsonArray().get(0).getAsJsonObject();
        Goods parsedMalus = gson.fromJson(goods, Goods.class);
        List<ActionType> availableActions = parseListActionType(card);
        return new MalusRewards(new AvailableActions(availableActions), parsedMalus);
    }

    private RewardsModifier parseNoTowerBonusRewards(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        return new NoTowerBonusRewards(new AvailableActions(availableActions));
    }

    private List<ActionType> parseListActionType(JsonObject card) {
        Gson gson = new Gson();
        List<ActionType> parsedActionTypes = new ArrayList<>();
        JsonArray actionTypeArray = card.get("actionType").getAsJsonArray();
        List<String> actionTypes = gson.fromJson(actionTypeArray, new TypeToken<ArrayList<String>>(){}.getType());
        for (String str : actionTypes) {
            parsedActionTypes.add(parseActionType(str));
        }
        return parsedActionTypes;
    }

    private ActionType parseActionType(String actionType) {
        Map<String, ActionType> valuesToReturn = new HashMap<>();
        valuesToReturn.put("GREEN_TOWER", ActionType.GREEN_TOWER);
        valuesToReturn.put("YELLOW_TOWER", ActionType.YELLOW_TOWER);
        valuesToReturn.put("BLUE_TOWER", ActionType.BLUE_TOWER);
        valuesToReturn.put("PURPLE_TOWER",ActionType.PURPLE_TOWER);
        valuesToReturn.put("PRODUCTION",ActionType.PRODUCTION);
        valuesToReturn.put("HARVEST",ActionType.HARVEST);
        valuesToReturn.put("COUNCIL_PALACE", ActionType.COUNCIL_PALACE);
        valuesToReturn.put("MARKET", ActionType.MARKET);
        return valuesToReturn.get(actionType);
    }

    private List<PawnColor> parseListPawnColor(JsonObject card) {
        Gson gson = new Gson();
        List<PawnColor> parsedPawnColors = new ArrayList<>();
        JsonArray pawnColorsArray = card.get(PAWN_COLOR).getAsJsonArray();
        List<String> pawnColor = gson.fromJson(pawnColorsArray, new TypeToken<ArrayList<String>>(){}.getType());
        for (String str : pawnColor) {
            parsedPawnColors.add(parsePawnColor(str));
        }
        return parsedPawnColors;
    }

    private PawnColor parsePawnColor(String str) {
        Map<String, PawnColor> valuesToReturn = new HashMap<>();
        valuesToReturn.put ("orange", PawnColor.ORANGE);
        valuesToReturn.put("white", PawnColor.WHITE);
        valuesToReturn.put("black", PawnColor.BLACK);
        valuesToReturn.put("neutral", PawnColor.NEUTRAL);
        return valuesToReturn.get(str);
    }

    private Map<String, Callable<RequirementsModifier>> constructRequirementsMap(JsonObject card) {
        Map<String, Callable<RequirementsModifier>> commands = new HashMap<>();
        commands.put("bonusActionValue", () -> parseBonusActionValue(card));
        commands.put("bonusPawnsValue", () -> parseBonusPawnsValue(card));
        commands.put("canPlaceOnOccupiedSpace", () -> parseCanPlaceOnOccupiedSpace(card));
        commands.put("doubleServants", () -> parseDoubleServants(card));
        commands.put("fixedColouredPawnsValue", () -> parseFixedColouredPawnsValue(card));
        commands.put(MALUS_ACTION_VALUE, () -> parseMalusActionValue(card));
        commands.put("malusColouredPawns", () -> parseMalusColouredPawns(card));
        commands.put("noBonusGoodsOnTower", () -> parseNoBonusGoodsOnTower(card));
        commands.put("noMarketPlacement", () -> parseNoMarketPlacement(card));
        commands.put("noMilitaryForTerritories", () -> parseNoMilitaryForTerritories(card));
        commands.put("noOccupiedTowerCost", () -> parseNoOccupiedTowerCost(card));
        return commands;
    }
}