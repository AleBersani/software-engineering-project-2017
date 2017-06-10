package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.modifiers.endgamerewards.modifiers.*;
import it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.gamelogic.modifiers.rewards.modifiers.RewardsModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Class that contains methods necessary to parse additional info of cards useful to Server Controller.
 */
public class ParserAdditionalInfo {
    private ParserSettings settings;

    public ParserAdditionalInfo() {
        settings = new ParserSettings();
    }

    /**
     * This method modifies four maps passed as parameters parsing from Json files. Each map has as keys
     * a String (name of a card) and as value a List of AdditionalCardInfo.
     * and
     * @param flashOnChoice
     * @param flashNotChoosable
     * @param permanentOnChoice
     * @param permanentNotChoosable
     * @throws Exception
     */
    public void parseMapsAdditionalInfo(Map<String, List<AdditionalCardInfo>> flashOnChoice,
                                        Map<String, List<AdditionalCardInfo>> flashNotChoosable,
                                        Map<String, List<AdditionalCardInfo>> permanentOnChoice,
                                        Map<String, List<AdditionalCardInfo>> permanentNotChoosable) throws Exception {
        String category1, category2;
        category1 = "additionalInfoFlash";
        category2 = "additionalInfoPermanent";
        parseCategoryDevCards(flashOnChoice, flashNotChoosable, category1);
        parseCategoryDevCards(permanentOnChoice, permanentNotChoosable, category2);
        parseCategoryOthers(flashOnChoice, flashNotChoosable, category1);
        parseCategoryOthers(permanentOnChoice, permanentNotChoosable, category2);
    }

    /**
     * This method is called in order to modify two maps related to a certain category of AdditionalCardInfo, parsing
     * from Development Cards Json file.
     * @param parsedAddOnChoice
     * @param parsedAddNotChoosable
     * @param additionalInfoCategory A String that indicates the category of AdditionalCardInfo to parse from Json file.
     * @throws Exception Can be thrown by settings.extractJsonObject method and parseSingleListAddInfo method.
     */
    public void parseCategoryDevCards(Map<String, List<AdditionalCardInfo>> parsedAddOnChoice,
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
                parsedAddInfo = parseSingleListAddInfo(additionalInfoCategory, "onChoice",
                                                        card, jsonName);
                addToMap(name, parsedAddInfo, parsedAddOnChoice);
                parsedAddInfo = parseSingleListAddInfo(additionalInfoCategory, "notChoosable",
                                                        card, jsonName);
                addToMap(name, parsedAddInfo, parsedAddNotChoosable);
            }
        }
    }

    /**
     * This method is called in order to modify two maps related to a certain category of AdditionalCardInfo, parsing
     * from LeaderCards and ExcommunicationTiles Json files.
     * @param parsedAddOnChoice
     * @param parsedAddNotChoosable
     * @param additionalInfoCategory A String that indicates the category of AdditionalCardInfo to parse from Json file.
     * @throws Exception Can be thrown by settings.extractJsonObject method and parseSingleListAddInfo method.
     */
    public void parseCategoryOthers(Map<String, List<AdditionalCardInfo>> parsedAddOnChoice,
                                Map<String, List<AdditionalCardInfo>> parsedAddNotChoosable,
                                String additionalInfoCategory) throws Exception {
        String[] jsonKeys = {"LeaderCards", "ExcommunicationTiles"};
        String jsonName;
        JsonObject openJson, card;
        JsonArray cards;
        List<AdditionalCardInfo> parsedAddInfo;

        for (String json : jsonKeys) {
            jsonName = json + ".json";
            openJson = settings.extractJsonObject(jsonName);
            cards = openJson.get(json).getAsJsonArray();
            for (int index=0; index<cards.size(); index++) {
                card = cards.get(index).getAsJsonObject();
                parsedAddInfo = parseSingleListAddInfo(additionalInfoCategory, "onChoice",
                                                        card, jsonName);
                addToMap(getName(card, jsonName), parsedAddInfo, parsedAddOnChoice);
                parsedAddInfo = parseSingleListAddInfo(additionalInfoCategory, "notChoosable",
                                                        card, jsonName);
                addToMap(getName(card, jsonName), parsedAddInfo, parsedAddNotChoosable);
            }
        }
    }

    /**
     * This method parses from ExcommunicationTiles Json file a map describing the Excommunication Tiles of third
     * period (managed in a different way than other cards).
     * @return Map that couples a String (name of the card) and its related EndGameRewardsModifier.
     * @throws Exception Can be thrown by settings.extractJsonObject, getName or parseEndGameRewardsModifier methods.
     */
    public Map<String, EndGameRewardsModifier> parseThirdPeriodExcommunicationsMap() throws Exception {
        String name, jsonName = "ExcommunicationTiles.json";
        JsonObject card;
        Map<String, EndGameRewardsModifier> thirdPeriodExcommunication = new HashMap<>();

        JsonObject obj = settings.extractJsonObject(jsonName);
        JsonArray cards = obj.get("ExcommunicationTilesThirdPeriod").getAsJsonArray();
        for (int index=0; index<cards.size(); index++) {
            card = cards.get(index).getAsJsonObject();
            name = getName(card, jsonName);
            thirdPeriodExcommunication.put(name, parseEndGameRewardsModifier(card));
        }
        return thirdPeriodExcommunication;
    }

    /**
     * This method parses from ExcommunicationTile Json file a String that is compared to the key of a map
     * that return a new instantiated object (depending on the String).
     * @param card
     * @return An EndGameRewardsModifier object.
     * @throws Exception Can be thrown by the use of Callable's call method.
     */
    private EndGameRewardsModifier parseEndGameRewardsModifier(JsonObject card) throws Exception {
        Gson gson = new Gson();
        Map<String, Callable<EndGameRewardsModifier>> commands = new HashMap<>();

        commands.put("lessVictoryBasedOnBuildingsCosts", () ->new LessVictoryBasedOnBuildingsCosts());
        commands.put("lessVictoryBasedOnMilitary", () ->new LessVictoryBasedOnMilitary());
        commands.put("lessVictoryBasedOnVictory", () ->new LessVictoryBasedOnVictory());
        commands.put("lessVictoryForResources", () ->new LessVictoryForResources());
        commands.put("noCharacterCardsEndRewards", () ->new NoCharacterCardsEndRewards());
        commands.put("noTerritoryCardsEndRewards", () ->new NoTerritoryCardsEndRewards());
        commands.put("noVentureCardsEndRewards", () ->new NoVentureCardsEndRewards());
        List<String> modifiers = gson.fromJson(card.get("modifiers").getAsJsonArray(),
                                                new TypeToken<ArrayList<String>>(){}.getType());
        EndGameRewardsModifier obj = commands.get(modifiers.get(0)).call();
        return obj;
    }

    /**
     * This method parses from a generic Json file a List of AdditionalCardInfo (related to a certain card)
     * by comparing a List of String from Json with the keys of a map that return a new instantiated
     * object (depending on the String).
     * @param addInfoCategory
     * @param addInfoType
     * @param card
     * @param jsonName
     * @return A List of AdditionalCardInfo.
     * @throws Exception
     */
    private List<AdditionalCardInfo> parseSingleListAddInfo(String addInfoCategory, String addInfoType,
                                                            JsonObject card, String jsonName) throws Exception {
        int actionTypeIndex=0;
        String name, type;
        List<AdditionalCardInfo> parsedAddInfo = new ArrayList<>();
        JsonObject addInfoCategoryObject = card.get(addInfoCategory).getAsJsonObject();
        List<String> addInfoToInstantiate = getAddInfoToInstantiateFromField(addInfoCategoryObject, addInfoType);
        name = getName(card, jsonName);

        for(int index=0; index<addInfoToInstantiate.size(); index++){
            type = addInfoToInstantiate.get(index);
            switch (type) {
                case "cardFlashAction": parsedAddInfo.add(parseCardFlashAction(card, name, actionTypeIndex));
                    actionTypeIndex++; break;
                case "conditionalProduction": parsedAddInfo.add(parseConditionalProduction(card, name)); break;
                case "multipleProduction": parsedAddInfo.add(parseMultipleProduction(card, name)); break;
                case "requirementsOnCard": parsedAddInfo.add(parseRequirementsOnCard(card, name)); break;
                case "rewardsOnCard": parsedAddInfo.add(parseRewardsOnCard(card, name)); break;
                case "goodsBasedOnPossessions": parsedAddInfo.add(parseGoodsBasedOnPossessions(card, name)); break;
                case "cardFlashExchangingGoods": parsedAddInfo.add(parseCardFlashExchangingGoods(card, name)); break;
                default: break;
            }
        }
        return parsedAddInfo;
    }

    /**
     * This method returns a new instantiated CardFlashAction object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @param actionTypeIndex
     * @return An AdditionalCardInfo object (Dynamic type CardFlashAction)
     */
    private AdditionalCardInfo parseCardFlashAction(JsonObject card,
                                                    String name, int actionTypeIndex) {
        Gson gson = new Gson();

        String actionType = card.get("actionType").getAsJsonArray().get(actionTypeIndex).getAsString();
        ActionType parsedActionType = parseActionType(actionType);
        int actionValue = card.get("actionValue").getAsInt();
        Goods discount = gson.fromJson(card.get("discount").getAsJsonArray().get(0).getAsJsonObject(), Goods.class);
        AdditionalCardInfo object = new CardFlashAction(name, parsedActionType,actionValue, discount);
        return object;
    }

    /**
     * This method returns a new instantiated ConditionalProduction object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type ConditionalProduction)
     */
    private AdditionalCardInfo parseConditionalProduction(JsonObject card, String name) {
        GeneralColor cardColor = parseGeneralColorEnum(card.get("cardColor").getAsString());
        AdditionalCardInfo object = new ConditionalProduction(name, cardColor);
        return object;
    }

    /**
     * This method returns a new instantiated GoodsBasedOnPossessions object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type GoodsBasedOnPossessions)
     */
    private AdditionalCardInfo parseGoodsBasedOnPossessions(JsonObject card, String name) {
        Gson gson = new Gson();
        Goods rewardsForPossessions = gson.fromJson(card.get("rewardsForPossessions").getAsJsonArray().get(0).getAsJsonObject(),
                                                    Goods.class);
        String typeOfObjectRequired = card.get("typeOfObjectRequired").getAsString().toUpperCase();
        int numberOfObjectRequired = card.get("numberOfObjectRequired").getAsInt();
        AdditionalCardInfo object = new GoodsBasedOnPossessions(name, rewardsForPossessions, typeOfObjectRequired, numberOfObjectRequired);
        return object;
    }

    /**
     * This method returns a new instantiated MultipleProduction object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type MultipleProduction)
     */
    private AdditionalCardInfo parseMultipleProduction(JsonObject card, String name) {
        Gson gson = new Gson();
        JsonArray costs = card.get("costs").getAsJsonArray();
        JsonArray result = card.get("result").getAsJsonArray();
        List<Goods> parsedCosts = gson.fromJson(costs, new TypeToken<ArrayList<Goods>>(){}.getType());
        List<ExchangingGoods> parsedResults = gson.fromJson(result,
                                                new TypeToken<ArrayList<ExchangingGoods>>(){}.getType());
        AdditionalCardInfo object = new MultipleProduction(name, parsedCosts, parsedResults);
        return object;
    }

    /**
     * This method returns a new instantiated RequirementsOnCard object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type RequirementsOnCard)
     */
    private AdditionalCardInfo parseRequirementsOnCard(JsonObject card, String name) throws Exception {
        Gson gson = new Gson();
        ParserModifiers parserModifiers = new ParserModifiers();
        List<String> modifiers = gson.fromJson(card.get("modifiers").getAsJsonArray(),
                                                new TypeToken<ArrayList<String>>(){}.getType());
        List<RequirementsModifier> parsedModifiers = parserModifiers.parseListRequirementsModifier(modifiers, card);
        AdditionalCardInfo object = new RequirementsOnCard(name, parsedModifiers);
        return object;
    }

    /**
     * This method returns a new instantiated RewardsOnCard object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type RewardsOnCard)
     */
    private AdditionalCardInfo parseRewardsOnCard(JsonObject card, String name) throws Exception {
        Gson gson = new Gson();
        ParserModifiers parserModifiers = new ParserModifiers();
        List<String> modifiers = gson.fromJson(card.get("modifiers").getAsJsonArray(),
                                                new TypeToken<ArrayList<String>>(){}.getType());
        RewardsModifier parsedModifier = parserModifiers.parseListRewardsModifier(modifiers, card);
        AdditionalCardInfo object = new RewardsOnCard(name, parsedModifier);
        return object;
    }

    /**
     * This method returns a new instantiated CardFlashExchangingGoods object, retrieving necessary elements from Json file
     * for the instantiation.
     * @param card
     * @param name
     * @return An AdditionalCardInfo object (Dynamic type CardFlashExchangingGoods)
     */
    private AdditionalCardInfo parseCardFlashExchangingGoods(JsonObject card, String name) {
        Gson gson = new Gson();
        JsonObject exchGoods = card.get("instantEffect").getAsJsonArray().get(0).getAsJsonObject();
        ExchangingGoods parsedExchangingGoods = gson.fromJson(exchGoods, ExchangingGoods.class);
        AdditionalCardInfo object = new CardFlashExchangingGoods(name, parsedExchangingGoods);
        return object;
    }

    /**
     * Auxiliary method that receives a String as parameter and returns a particular ActionType enum property.
     * @param actionType
     * @return ActionType enum property
     */
    private ActionType parseActionType(String actionType) {
        Map<String, ActionType> valuesToReturn = new HashMap<>();
        valuesToReturn.put ("GREEN_TOWER", ActionType.GREEN_TOWER);
        valuesToReturn.put("YELLOW_TOWER", ActionType.YELLOW_TOWER);
        valuesToReturn.put("BLUE_TOWER", ActionType.BLUE_TOWER);
        valuesToReturn.put("HARVEST",ActionType.HARVEST);
        valuesToReturn.put("PRODUCTION",ActionType.PRODUCTION);
        valuesToReturn.put("PURPLE_TOWER",ActionType.PURPLE_TOWER);
        valuesToReturn.put("COUNCIL_PALACE", ActionType.COUNCIL_PALACE);
        valuesToReturn.put("MARKET", ActionType.MARKET);

        return valuesToReturn.get(actionType);
    }

    /**
     * Auxiliary method that receives a String as parameter and returns a particular GeneralColor enum property.
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
     * Auxiliary method that depending on Json file type that is currently parsing, returns the String name of a card.
     * @param card
     * @param jsonName
     * @return A String that describe a card's name.
     * @throws Exception Can be thrown by the call Callable's method.
     */
    private String getName(JsonObject card, String jsonName) throws Exception {
        Map<String, Callable<String>> commands = new HashMap<>();
        commands.put("DevelopmentCards.json", ()->getDevCardName(card));
        commands.put("LeaderCards.json", ()->getLeaderName(card));
        commands.put("ExcommunicationTiles.json", ()->getExcommunicationName(card));
        return commands.get(jsonName).call();
    }

    /**
     * This method returns a String name of a DevelopmentCard.
     * @param card
     * @return
     */
    private String getDevCardName(JsonObject card) {
        String name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        return name;
    }

    /**
     * This method returns a String name of a LeaderCard.
     * @param card
     * @return
     */
    private String getLeaderName(JsonObject card) {
        String name = card.get("name").getAsString();
        return name;
    }

    /**
     * This method returns a String name of a ExcommunicationTile.
     * @param card
     * @return
     */
    private String getExcommunicationName(JsonObject card) {
        String name = card.get("excommunicationTileDetails").getAsJsonObject().get("ExcommunicationTileName").getAsString();
        return name;
    }

    /**
     * This method parses a List of Strings describing the type of AdditionalCardInfo associated to a certain field.
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
     * This method is used to add a new Key-Value association on a map, verifying that a certain parsed List of
     * AdditionalCardInfo is empty before adding.
     * @param name
     * @param parsedAddInfo
     * @param mapToModify
     */
    private void addToMap(String name, List<AdditionalCardInfo> parsedAddInfo,
                          Map<String, List<AdditionalCardInfo>> mapToModify) {
        if(!parsedAddInfo.isEmpty())
            mapToModify.put(name, parsedAddInfo);
    }

}
