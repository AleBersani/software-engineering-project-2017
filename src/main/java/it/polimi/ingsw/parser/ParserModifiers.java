package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.EndGameRewards;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.PawnColor;
import it.polimi.ingsw.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers.*;
import it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers.*;
import it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers.*;

import java.util.*;
import java.util.concurrent.Callable;

public class ParserModifiers {

    public List<RequirementsModifier> parseListRequirementsModifier(List<String> modifiers, JsonObject card) throws Exception {
        List<RequirementsModifier> modifierList = new ArrayList<>();
        int bonusPawnValueIndex=0, fixedPawnValueIndex=0, bonusIndex=0;
        Map<String, Callable<RequirementsModifier>> commands = constructRequirementsMap(card);
        for (int index=0; index<modifiers.size(); index++) {
            String key = modifiers.get(index);
            if ("bonusOnCardCost".equals(key)) {
                modifierList.add(parseBonusOnCardCost(card, bonusIndex));
                bonusIndex++;
            }
            else if("bonusPawnValue".equals(key)){
                modifierList.add(parseBonusPawnValue(card, bonusPawnValueIndex));
                bonusPawnValueIndex++;
            }
            else if("fixedColouredPawnValue".equals(key)){
                modifierList.add(parseFixedColouredPawnValue(card, fixedPawnValueIndex));
                fixedPawnValueIndex++;
            }
            else
                modifierList.add(commands.get(key).call());
        }
        return modifierList;
    }


    public RewardsModifier parseListRewardsModifier(List<String> modifiers, JsonObject card) throws Exception {
        Map<String, Callable<RewardsModifier>> commands = new HashMap<>();
        commands.put("bonusRewards", ()->parseBonusRewards(card));
        commands.put("doubleRewards", ()->parseDoubleRewards(card));
        commands.put("malusRewards", ()->parseMalusRewards(card));
        commands.put("noTowerBonusRewards", ()->parseNoTowerBonusRewards(card));
        RewardsModifier object = commands.get(modifiers.get(0)).call();

        return object;
    }

    //PRIVATE METHODS


    private RequirementsModifier parseBonusActionValue(JsonObject card) {
        int bonusValue = card.get("bonusValue").getAsInt();
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new BonusActionValue(new AvailableActions(availableActions), bonusValue);
        return object;
    }

    private RequirementsModifier parseBonusOnCardCost(JsonObject card, int bonusIndex) {
        Gson gson = new Gson();
        List<ActionType> availableActions = parseListActionType(card);
        JsonObject bonus = card.get("bonus").getAsJsonArray().get(bonusIndex).getAsJsonObject();
        Goods parsedBonus = gson.fromJson(bonus, Goods.class);
        RequirementsModifier object = new BonusOnCardCost(new AvailableActions(availableActions), parsedBonus);
        return object;
    }

    private RequirementsModifier parseBonusPawnsValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int bonusPawnValue = card.get("actionValueSurplus").getAsInt();
        RequirementsModifier object = new BonusPawnsValue(new AvailableActions(availableActions),
                                                            pawnColors, bonusPawnValue);
        return object;
    }

    private RequirementsModifier parseBonusPawnValue(JsonObject card, int bonusPawnValueIndex) {
        List<ActionType> availableActions = parseListActionType(card);
        PawnColor pawnColor = parsePawnColor(card.get("pawnColor").getAsJsonArray().
                                                get(bonusPawnValueIndex).getAsString());
        int bonusActionValue = card.get("actionValueSurplus").getAsInt();
        RequirementsModifier object = new BonusPawnValue(new AvailableActions(availableActions),
                                                            pawnColor, bonusActionValue);
        return object;
    }

    private RequirementsModifier parseCanPlaceOnOccupiedSpace(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new CanPlaceOnOccupiedSpace(new AvailableActions(availableActions));
        return object;
    }

    private RequirementsModifier parseDoubleServants(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new DoubleServants(new AvailableActions(availableActions));
        return object;
    }

    private RequirementsModifier parseFixedColouredPawnsValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int pawnsValue = card.get("pawnsValue").getAsInt();
        RequirementsModifier object = new FixedColouredPawnsValue(new AvailableActions(availableActions),
                                                                    pawnColors, pawnsValue);
        return object;
    }

    private RequirementsModifier parseFixedColouredPawnValue(JsonObject card, int fixedPawnValueIndex) {
        List<ActionType> availableActions = parseListActionType(card);
        PawnColor pawnColor = parsePawnColor(card.get("pawnColor").getAsJsonArray().get(fixedPawnValueIndex).getAsString());
        int pawnValue = card.get("pawnValue").getAsInt();
        RequirementsModifier object = new FixedColouredPawnValue(new AvailableActions(availableActions),
                                                                    pawnColor, pawnValue);
        return object;
    }

    private RequirementsModifier parseMalusActionValue(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        int malusValue = card.get("malusActionValue").getAsInt();
        RequirementsModifier object = new MalusActionValue(new AvailableActions(availableActions), malusValue);
        return object;
    }

    private RequirementsModifier parseMalusColouredPawns(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        List<PawnColor> pawnColors = parseListPawnColor(card);
        int malusValue = card.get("malusActionValue").getAsInt();
        RequirementsModifier object = new MalusColouredPawns(new AvailableActions(availableActions),
                                                                pawnColors, malusValue);
        return object;
    }

    private RequirementsModifier parseNoBonusGoodsOnTower(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new NoBonusGoodsOnTower(new AvailableActions(availableActions));
        return object;
    }

    private RequirementsModifier parseNoMarketPlacement(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new NoMarketPlacement(new AvailableActions(availableActions));
        return object;
    }

    private RequirementsModifier parseNoMilitaryForTerritories(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new NoMilitaryForTerritories(new AvailableActions(availableActions));
        return object;
    }

    private RequirementsModifier parseNoOccupiedTowerCost(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RequirementsModifier object = new NoOccupiedTowerCost(new AvailableActions(availableActions));
        return object;
    }



    /**
     * TODO DA RIVEDERE
     * @param card
     * @return
     */
    private RewardsModifier parseBonusRewards(JsonObject card) {
        Gson gson = new Gson();
        List<ActionType> availableActions = parseListActionType(card);
        Goods bonus = gson.fromJson(card.get("bonus").getAsJsonArray().get(0).getAsJsonObject(), Goods.class);
        RewardsModifier object = new BonusRewards(new AvailableActions(availableActions), bonus);
        return object;
    }

    private RewardsModifier parseDoubleRewards(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RewardsModifier object = new DoubleRewards(new AvailableActions(availableActions));
        return object;
    }

    /**
     * @param card
     * @return
     */
    private RewardsModifier parseMalusRewards(JsonObject card) {
        Gson gson = new Gson();
        JsonObject goods = card.get("rewardsMalus").getAsJsonArray().get(0).getAsJsonObject();
        Goods parsedMalus = gson.fromJson(goods, Goods.class);
        List<ActionType> availableActions = parseListActionType(card);
        RewardsModifier object = new MalusRewards(new AvailableActions(availableActions), parsedMalus);
        return object;
    }

    private RewardsModifier parseNoTowerBonusRewards(JsonObject card) {
        List<ActionType> availableActions = parseListActionType(card);
        RewardsModifier object = new NoTowerBonusRewards(new AvailableActions(availableActions));
        return object;
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
        JsonArray pawnColorsArray = card.get("pawnColor").getAsJsonArray();
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
        commands.put("bonusActionValue", ()->parseBonusActionValue(card));
        commands.put("bonusPawnsValue", ()->parseBonusPawnsValue(card));
        commands.put("canPlaceOnOccupiedSpace", ()->parseCanPlaceOnOccupiedSpace(card));
        commands.put("doubleServants", ()->parseDoubleServants(card));
        commands.put("fixedColouredPawnsValue", ()->parseFixedColouredPawnsValue(card));
        commands.put("malusActionValue", ()->parseMalusActionValue(card));
        commands.put("malusColouredPawns", ()->parseMalusColouredPawns(card));
        commands.put("noBonusGoodsOnTower", ()->parseNoBonusGoodsOnTower(card));
        commands.put("noMarketPlacement", ()->parseNoMarketPlacement(card));
        commands.put("noMilitaryForTerritories", ()->parseNoMilitaryForTerritories(card));
        commands.put("noOccupiedTowerCost", ()->parseNoOccupiedTowerCost(card));
        return commands;
    }

}
