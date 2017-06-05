package it.polimi.ingsw.parser;

import com.google.gson.*;
import it.polimi.ingsw.gamelogic.basics.*;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.CardFlashExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.ConditionalProduction;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.MultipleProduction;
import it.polimi.ingsw.gamelogic.cards.development.*;
import it.polimi.ingsw.gamelogic.cards.development.Character;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Parser Class manages the parsing from Json to Java Objects.
 */
public class Parser {


    public Parser() throws IOException {

    }

    private List<Goods> parseRequirementsOnCosts(JsonArray costs) {
        List<Goods> parsedCosts = new ArrayList<>();
        Gson gson = new Gson();
        for(int index=0; index<costs.size(); index++){
            Goods cost = gson.fromJson(costs.get(index).getAsJsonObject(), Goods.class);
            parsedCosts.add(cost);
        }
        return parsedCosts;
    }









    /*public Map<String, List<AdditionalCardInfo>> parseAdditionalInfoMap() throws IOException {
        Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap = new HashMap<>();
        //parseAdditionalInfoDevCards(parsedAdditionalInfoMap);
        parseAdditionalInfoLeaders(parsedAdditionalInfoMap);
        parseAdditionalInfoExcommunications(parsedAdditionalInfoMap);
        return parsedAdditionalInfoMap;
    }*/



    private void parseListAddInfo(List<AdditionalCardInfo> addInfoList, JsonObject card,
                                  String addInfoToInstantiate, String name) {
        JsonArray addInfoTypes = card.get(addInfoToInstantiate).getAsJsonArray();
        int actionTypeIndex = 0;
        for(int index=0; index<addInfoList.size(); index++){

        }
    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     * @param actionTypeIndex
     * @param name
     */
    private void parseCardFlashAction(List<AdditionalCardInfo> addInfoList, JsonObject card,
                                      int actionTypeIndex, String name) {
        JsonObject actionType = card.get("actionType").getAsJsonArray().get(actionTypeIndex).getAsJsonObject();
        ActionType parsedActionType = parseActionType(actionType);
        int actionValue = card.get("actionValue").getAsInt();
        Gson gson = new Gson();
        Goods discount = gson.fromJson(card.get("discount").getAsJsonArray().get(0).getAsJsonObject(), Goods.class);
        //addInfoList.add(new CardFlashAction(name, ,actionValue), discount));
    }

    private ActionType parseActionType(JsonElement actionType) {
        String actionTypeToParse = actionType.getAsString();
        Map<String, ActionType> valuesToReturn = new HashMap<>();
        valuesToReturn.put ("greenTower", ActionType.GREEN_TOWER);
        valuesToReturn.put("yellowTower", ActionType.YELLOW_TOWER);
        valuesToReturn.put("blueTower", ActionType.BLUE_TOWER);
        valuesToReturn.put("purpleTower",ActionType.PURPLE_TOWER);
        valuesToReturn.put("production",ActionType.PRODUCTION);
        valuesToReturn.put("harvest",ActionType.HARVEST);
        return valuesToReturn.get(actionTypeToParse);
    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseConditionalProduction(List<AdditionalCardInfo> addInfoList, JsonObject card, String name) {
        GeneralColor cardColor = decideGeneralColorEnum(card.get("cardColor").getAsString());
        addInfoList.add(new ConditionalProduction(name, cardColor));
    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     * @param name
     */
    private void parseMultipleProduction(List<AdditionalCardInfo> addInfoList, JsonObject card, String name) {
        JsonArray costs = card.get("costs").getAsJsonArray();
        JsonArray result = card.get("result").getAsJsonArray();
        List<Goods> parsedCosts = parseRequirementsOnCosts(costs);
        List<ExchangingGoods> parsedResults = parseResults(result);
        addInfoList.add(new MultipleProduction(name, parsedCosts, parsedResults));
    }

    private List<ExchangingGoods> parseResults(JsonArray result) {
        List<ExchangingGoods> parsedResults = new ArrayList<>();
        Gson gson = new Gson();
        for(int index=0; index<result.size(); index++){
            ExchangingGoods singleResult = gson.fromJson(result.get(index).getAsJsonObject(), ExchangingGoods.class);
            parsedResults.add(singleResult);
        }
        return parsedResults;
    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseRequirementsOnCard(List<AdditionalCardInfo> addInfoList, JsonObject card) {

    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseRewardsOnCard(List<AdditionalCardInfo> addInfoList, JsonObject card) {

    }

    /**
     * TODO CHANGE INSTANTGOODS NAME FROM JSON AND IN THE REMAINING PARSING METHODS IN CONSUMABLEGOODS
     * TODO CARDINFORMATIONS IN OTHER JSON?
     * @param addInfoList
     * @param card
     */
    private void parseCardFlashExchangingGoods(List<AdditionalCardInfo> addInfoList, JsonObject card) {
        String name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        ExchangingGoods parsedExchangingGoods = parseExchangingGoods(card, "instantGoods");
        addInfoList.add(new CardFlashExchangingGoods(name, parsedExchangingGoods));
    }

    /**
     * Private method that sets during parsing, GeneralColor Enum attributes.
     * @param cardColor
     * @return
     */
    private GeneralColor decideGeneralColorEnum(String cardColor) {
        Map<String, GeneralColor> cardColorToReturn = new HashMap<>();
        cardColorToReturn.put("blue", GeneralColor.BLUE);
        cardColorToReturn.put("yellow", GeneralColor.YELLOW);
        cardColorToReturn.put("green", GeneralColor.GREEN);
        cardColorToReturn.put("purple", GeneralColor.PURPLE);

        return cardColorToReturn.get(cardColor);
    }

    /**
     * Private method that parses instantEffect in an ExchangingGoods Object, from Json.
     * @param card JsonObject from which method gets a specific field.
     * @return ExchangingGoods Object containing instantEffect set in Json file.
     */
    private ExchangingGoods parseExchangingGoods(JsonObject card, String fieldName) {
        Gson gson = new Gson();
        JsonArray exchangingGoods = card.get(fieldName).getAsJsonArray();
        ExchangingGoods parsedExchangingGoods = gson.fromJson(exchangingGoods.get(0).getAsJsonObject(),
                ExchangingGoods.class);

        return parsedExchangingGoods;
    }























}