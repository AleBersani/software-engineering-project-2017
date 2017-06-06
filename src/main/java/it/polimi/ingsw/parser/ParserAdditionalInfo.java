package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


public class ParserAdditionalInfo {
    private ParserSettings settings;

    public ParserAdditionalInfo() {
        settings = new ParserSettings();
    }

    /**
     * TODO JAVADOC
     * @param parsedAddOnChoice
     * @param parsedAddNotChoosable
     * @param additionalInfoCategory
     * @throws IOException
     */
    public void parseSingleAddCardInfosCategoryDevCards(Map<String, List<AdditionalCardInfo>> parsedAddOnChoice,
                                            Map<String, List<AdditionalCardInfo>> parsedAddNotChoosable,
                                            String additionalInfoCategory) throws Exception {
        JsonArray cards;
        JsonObject card;
        String name, jsonName;
        List<AdditionalCardInfo> parsedAddInfo;
        jsonName = "DevelopmentCards.json";
        JsonObject devCards = settings.extractJsonObject(jsonName);
        String[] fields = new String[]{"territory", "building", "character", "venture"};
        for (String string : fields) {
            cards = devCards.get(string).getAsJsonArray();
            for(int index=0; index<cards.size(); index++){
                card = cards.get(index).getAsJsonObject();
                name = getDevCardName(card);
                parsedAddInfo = parseSingleListAddInfoDevCards(additionalInfoCategory, "onChoice",
                                                                    card, jsonName);
                addToMap(name, parsedAddInfo, parsedAddOnChoice);
                parsedAddInfo = parseSingleListAddInfoDevCards(additionalInfoCategory, "notChoosable",
                                                                    card, jsonName);
                addToMap(name, parsedAddInfo, parsedAddNotChoosable);
            }
        }

    }

    public void parseSingleAddCardInfosCategoryOthers(Map<String, List<AdditionalCardInfo>> parsedAddOnChoice,
                                                      Map<String, List<AdditionalCardInfo>> parsedAddNotChoosable,
                                                      String additionalInfoCategory, String jsonName){
        JsonArray cards;
        JsonObject singleCard;
        String name;

    }

    /**
     * TODO JAVADOC
     * @param name
     * @param parsedAddInfo
     * @param mapToModify
     */
    private void addToMap(String name, List<AdditionalCardInfo> parsedAddInfo,
                          Map<String, List<AdditionalCardInfo>> mapToModify) {
        if(parsedAddInfo.size()>0)
            mapToModify.put(name, parsedAddInfo);
    }



    private List<AdditionalCardInfo> parseSingleListAddInfoDevCards(String addInfoCategory, String addInfoType,
                                                                    JsonObject card, String jsonName) throws Exception {
        int actionTypeIndex=0;
        String name, type;
        List<AdditionalCardInfo> parsedAddInfo = new ArrayList<>();
        JsonObject addInfoCategoryObject = card.get(addInfoCategory).getAsJsonObject();
        List<String> addInfoToInstantiate = getAddInfoToInstantiateFromField(addInfoCategoryObject, addInfoType);
        Map<String, Callable<String>> commands = new HashMap<>();
        commands.put("DevelopmentCards.json", ()->getDevCardName(card));
        commands.put("LeaderCards.json", ()->getLeaderName(card));
        commands.put("ExcommunicationTiles.json", ()->getExcommunicationName(card));
        name = commands.get(jsonName).call();

        for(int index=0; index<addInfoToInstantiate.size(); index++){
            type = addInfoToInstantiate.get(index);
            switch (type) {
                case "cardFlashAction": parsedAddInfo.add(parseCardFlashAction(card, name, actionTypeIndex));
                    actionTypeIndex++; break;
                case "conditionalProduction": parsedAddInfo.add(parseConditionalProduction(card, name)); break;
                case "multipleProduction": parsedAddInfo.add(parseMultipleProduction(card, name)); break;
                case "requirementsOnCard": parsedAddInfo.add(parseRequirementsOnCard(card, name)); break;
                case "rewardsOnCard": parsedAddInfo.add(parseRewardsOnCard(card, name)); break;
                default: parsedAddInfo.add(parseCardFlashExchangingGoods(card, name)); break;
            }
        }
        return parsedAddInfo;

    }




    /**
     * TODO all
     * @param card
     * @param name
     * @param actionTypeIndex
     */
    private AdditionalCardInfo parseCardFlashAction(JsonObject card,
                                                    String name, int actionTypeIndex) {
        Gson gson = new Gson();
        JsonObject actionType = card.get("actionType").getAsJsonArray().get(actionTypeIndex).getAsJsonObject();
        ActionType parsedActionType = parseActionType(actionType);
        int actionValue = card.get("actionValue").getAsInt();
        Goods discount = gson.fromJson(card.get("discount").getAsJsonArray().get(0).getAsJsonObject(), Goods.class);
        AdditionalCardInfo object = new CardFlashAction(name, parsedActionType,actionValue, discount);
        return object;
    }

    /**
     * TODO all
     * @param card
     * @param name
     */
    private AdditionalCardInfo parseConditionalProduction(JsonObject card, String name) {
        GeneralColor cardColor = parseGeneralColorEnum(card.get("cardColor").getAsString());
        AdditionalCardInfo object = new ConditionalProduction(name, cardColor);
        return object;
    }

    /**
     * TODO all
     * @param card
     * @param name
     */
    private AdditionalCardInfo parseMultipleProduction(JsonObject card, String name) {
        Gson gson = new Gson();
        JsonArray costs = card.get("costs").getAsJsonArray();
        JsonArray result = card.get("result").getAsJsonArray();
        List<Goods> parsedCosts = gson.fromJson(costs, new TypeToken<ArrayList<Goods>>(){}.getType());
        List<ExchangingGoods> parsedResults = gson.fromJson(result, new TypeToken<ArrayList<ExchangingGoods>>(){}.getType());
        AdditionalCardInfo object = new MultipleProduction(name, parsedCosts, parsedResults);
        return object;
    }

    /**
     * TODO all
     * @param card
     * @param name
     */
    private AdditionalCardInfo parseRequirementsOnCard(JsonObject card, String name) {

        return null;
    }

    /**
     * TODO all
     * @param card
     * @param name
     */
    private AdditionalCardInfo parseRewardsOnCard(JsonObject card, String name) {

        return null;
    }

    /**
     * TODO CHANGE INSTANTGOODS NAME FROM JSON AND IN THE REMAINING PARSING METHODS IN CONSUMABLEGOODS
     * TODO CARDINFORMATIONS IN OTHER JSON?
     * @param card
     * @param name
     */
    private AdditionalCardInfo parseCardFlashExchangingGoods(JsonObject card, String name) {
        Gson gson = new Gson();
        JsonObject exchGoods = card.get("instantGoods").getAsJsonArray().get(0).getAsJsonObject();
        ExchangingGoods parsedExchangingGoods = gson.fromJson(exchGoods, ExchangingGoods.class);
        AdditionalCardInfo object = new CardFlashExchangingGoods(name, parsedExchangingGoods);
        return object;
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
     * Private method that sets during parsing, GeneralColor Enum attributes.
     * @param cardColor
     * @return
     */
    private GeneralColor parseGeneralColorEnum(String cardColor) {
        Map<String, GeneralColor> generalColorToReturn = new HashMap<>();
        generalColorToReturn.put("yellow", GeneralColor.YELLOW);
        generalColorToReturn.put("green", GeneralColor.GREEN);
        generalColorToReturn.put("purple", GeneralColor.PURPLE);
        generalColorToReturn.put("blue", GeneralColor.BLUE);

        return generalColorToReturn.get(cardColor);
    }

    /**
     * TODO JAVADOC
     * @param card
     * @return
     */
    private String getDevCardName(JsonObject card) {
        String name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        return name;
    }

    /**
     *TODO JAVADOC
     * @param card
     * @return
     */
    private String getLeaderName(JsonObject card) {
        String name = card.get("name").getAsString();
        return name;
    }

    /**
     * TODO JAVADOC
     * @param card
     * @return
     */
    private String getExcommunicationName(JsonObject card) {
        String name = card.get("ExcommunicationDetails").getAsJsonObject().get("ExcommunicationTileName").getAsString();
        return name;
    }

    /**
     * TODO JAVADOC
     * @param addInfoList
     * @param field
     * @return
     */
    private List<String> getAddInfoToInstantiateFromField(JsonObject addInfoList, String field){
        List<String> addInfoToInstantiate = new ArrayList<>();
        JsonArray fieldArray = addInfoList.get(field).getAsJsonArray();
        for(int index=0; index<fieldArray.size(); index++){
            addInfoToInstantiate.add(fieldArray.get(index).getAsString());
        }
        return addInfoToInstantiate;

    }

    /**
     * TODO JAVADOC
     * @param addInfoList
     * @return
     */
    private boolean isEmpty(JsonArray addInfoList) {
        if(addInfoList.size()>0)
            return false;
        return true;
    }


















    /*private void parseTerritoryAddInfo(Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap, JsonArray territory) {
        JsonObject card;
        String name;
        for(int index=0; index<territory.size(); index++){
            card = territory.get(index).getAsJsonObject();
            name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
            List<AdditionalCardInfo> addInfoList = new ArrayList<>();
            parseListAddInfo(addInfoList, card, "", name);
        }
    }*/
}
