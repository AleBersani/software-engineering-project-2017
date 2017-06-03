package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.*;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.gamelogic.cards.development.*;
import it.polimi.ingsw.gamelogic.cards.development.Character;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
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
    private BufferedReader br = null;
    private FileReader fr = null;
    private static String PATH;

    public Parser() throws IOException {
        PATH = System.getProperty("user.dir") + "/src/json/";
    }


    /**
     * Method that parses Territory Development Cards from Json.
     * @throws IOException
     */
    public void parseTerritory() throws IOException {
        String path = PATH + "DevelopmentCards.json";
        open(path);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonObject devCards = object.get("DevelopmentCards").getAsJsonObject();
        JsonArray cards = devCards.get("territory").getAsJsonArray();

        List<Territory> territories = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            DevelopmentCard developmentCard = parseDevelopmentCard(card);

            /*CardInformation cardInfo = parseCardInformation(card);
            List<Goods> costs = parseCosts(card);
            ExchangingGoods instantExchangingGoods = parseExchangingGoods(card);*/

            int harvestActionValueRequired = card.get("harvestActionValueRequired").getAsInt();
            ExchangingGoods harvestResult = parseExchangingGoods(card);
            territories.add(new Territory(developmentCard, harvestActionValueRequired, harvestResult));

        }
        territories.forEach((Territory s) -> System.out.print(s.toString() + "\n"));
        close();
    }

    /**
     * Method that parses Building Development Cards from Json
     * @throws IOException
     */
    public void parseBuilding() throws IOException {
        String path = PATH + "DevelopmentCards.json";
        open(path);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("building").getAsJsonArray();
        List<Building> buildings = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            DevelopmentCard developmentCard = parseDevelopmentCard(card);
            buildings.add(new Building(developmentCard));
        }
        buildings.forEach((Building s) -> System.out.println(s.toString() + "\n"));
        close();
    }

    /**
     * Method that parses Character Development Cards from Json
     * @throws IOException
     */
    public void parseCharacter() throws IOException {
        String path = PATH + "DevelopmentCards.json";
        open(path);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("character").getAsJsonArray();
        List<Character> characters = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++) {
            JsonObject card = cards.get(index).getAsJsonObject();
            DevelopmentCard developmentCard = parseDevelopmentCard(card);
            characters.add(new Character(developmentCard));
        }
        characters.forEach((Character s) -> System.out.println(s.toString() + "\n"));
    }

    /**
     * Method that parses Venture Development Cards from Json
     * @throws IOException
     */
    public void parseVenture() throws IOException {
        String path = PATH + "DevelopmentCards.json";
        open(path);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("venture").getAsJsonArray();
        List<Venture> ventures = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++) {
            JsonObject card = cards.get(index).getAsJsonObject();
            DevelopmentCard developmentCard = parseDevelopmentCard(card);
            Goods endGameRewards = parseEndGameRewards(card);
            ventures.add(new Venture(developmentCard, endGameRewards));
        }
    }

    /**
     * TODO: Javadoc
     * @return
     * @throws IOException
     */
    public List<ExcommunicationTile> parseExcommunicationTiles() throws IOException {
        String path = PATH + "ExcommunicationTiles.json";
        open(path);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("excommunicationTiles").getAsJsonArray();
        List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
        JsonObject card;
        JsonArray excommunicationTile;
        for(int index=0; index<cards.size(); index++){
            card = cards.get(index).getAsJsonObject();
            excommunicationTile = card.get("excommunicationDetails").getAsJsonArray();
            ExcommunicationTile parsedExcommunicationTile = gson.fromJson(excommunicationTile,
                                                                            ExcommunicationTile.class);
            excommunicationTiles.add(parsedExcommunicationTile);
        }
        close();
        return excommunicationTiles;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    public List<LeaderCard> parseLeaderCards() throws IOException {
        String path = PATH + "leadercards.json";
        open(path);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray leaders = object.get("leaderCards").getAsJsonArray();

        List<LeaderCard> parsedLeaderCards = new ArrayList<>();
        List<LeaderCost> parsedLeaderCostList;
        LeaderInformation cardInfo;
        JsonArray leaderCosts;
        for(int index=0; index<leaders.size(); index++){
            cardInfo = parseLeaderInformation(leaders.get(index).getAsJsonObject());
            leaderCosts = leaders.get(index).getAsJsonObject().get("requirements").getAsJsonArray();
            parsedLeaderCostList = parseLeaderCosts(leaderCosts);
            //parsedLeaderCards.add(new LeaderCard(cardInfo, parsedLeaderCostList));
        }
        return parsedLeaderCards;
    }

    public Map<String, List<AdditionalCardInfo>> parseAdditionalInfoMap() throws IOException {
        Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap = new HashMap<>();
        parseAdditionalInfoDevCards(parsedAdditionalInfoMap);
        parseAdditionalInfoLeaders(parsedAdditionalInfoMap);
        parseAdditionalInfoExcommunications(parsedAdditionalInfoMap);
        return parsedAdditionalInfoMap;
    }

    private void parseAdditionalInfoExcommunications(Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap) {

    }

    private void parseAdditionalInfoLeaders(Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap) {

    }

    private void parseAdditionalInfoDevCards(Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap)
                                                                                                throws IOException {
        String path = PATH + "DevelopmentCards.json";
        open(path);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        parseTerritoryAddInfo(parsedAdditionalInfoMap, object.get("territory").getAsJsonArray());

    }

    private void parseTerritoryAddInfo(Map<String, List<AdditionalCardInfo>> parsedAdditionalInfoMap, JsonArray territory) {
        JsonObject card;
        String name;
        for(int index=0; index<territory.size(); index++){
            card = territory.get(index).getAsJsonObject();
            name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
            List<AdditionalCardInfo> addInfoList = new ArrayList<>();
            parseListAddInfo(addInfoList, card);
        }
    }

    private void parseListAddInfo(List<AdditionalCardInfo> addInfoList, JsonObject card) {
        JsonArray addInfoTypes = card.get("additionalCardInfoType").getAsJsonArray();
        for(int index=0; index<addInfoList.size(); index++){
            String type = addInfoTypes.get(index).getAsString();
            switch (type) {
                case "cardFlashAction": parseCardFlashAction(addInfoList, card); break;
                case "conditionalProduction": parseConditionalProduction(addInfoList, card); break;
                case "minRequiredOnCost": parseMinRequiredOnCost(addInfoList, card); break;
                case "multipleProduction": parseMultipleProduction(addInfoList, card); break;
                case "requirementsOnCard": parseRequirementsOnCard(addInfoList, card); break;
                case "rewardsOnCard": parseRewardsOnCard(addInfoList, card); break;
                default: parseCardFlashExchangingGoods(addInfoList, card); break;
            }
        }

    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseCardFlashAction(List<AdditionalCardInfo> addInfoList, JsonObject card) {

    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseConditionalProduction(List<AdditionalCardInfo> addInfoList, JsonObject card) {

    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseMinRequiredOnCost(List<AdditionalCardInfo> addInfoList, JsonObject card) {

    }

    /**
     * TODO all
     * @param addInfoList
     * @param card
     */
    private void parseMultipleProduction(List<AdditionalCardInfo> addInfoList, JsonObject card) {

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
        ExchangingGoods parsedExchangingGoods = parseExchangingGoods(card);
        addInfoList.add(new CardFlashExchangingGoods(name, parsedExchangingGoods));
    }


    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private LeaderInformation parseLeaderInformation(JsonObject card) {
        String name = card.get("name").getAsString();
        return new LeaderInformation(name, "", parseLeaderCategory(card));
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private List<LeaderCost> parseLeaderCosts(JsonArray leaderCosts) {
        List<LeaderCost> parsedLeaderCosts = new ArrayList<>();
        JsonObject cardCost;
        List<CardsRequired> parsedCardsRequired = new ArrayList<>();
        Goods requiredGoods;
        for(int index =0; index<leaderCosts.size(); index++){
            cardCost = leaderCosts.get(index).getAsJsonObject();
            parsedCardsRequired = parseCardsRequiredList(cardCost.get("developmentCards").
                                                                                getAsJsonObject());
            requiredGoods = parseGoods(cardCost);
            parsedLeaderCosts.add(new LeaderCost(requiredGoods, parsedCardsRequired));
        }
        return parsedLeaderCosts;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private LeaderCategory parseLeaderCategory(JsonObject card) {
        if(card.get("abilityType").getAsString().equals("permanent"))
            return LeaderCategory.PERMANENT;
        return LeaderCategory.CONSUMABLE;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private List<CardsRequired> parseCardsRequiredList(JsonObject cardsRequired) {
        List<CardsRequired> parsedCardsRequired = new ArrayList<>();
        if (cardsRequired.get("territories").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("territories").getAsInt(), GeneralColor.GREEN));
        else if (cardsRequired.get("characters").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("characters").getAsInt(), GeneralColor.BLUE));
        else if (cardsRequired.get("buildings").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("buildings").getAsInt(), GeneralColor.YELLOW));
        else if (cardsRequired.get("ventures").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("ventures").getAsInt(), GeneralColor.PURPLE));

        return parsedCardsRequired;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private Goods parseGoods(JsonObject leaderCost) {
        JsonObject resources = leaderCost.get("resources").getAsJsonObject();
        JsonObject points = leaderCost.get("points").getAsJsonObject();
        Gson gson = new Gson();
        return new Goods(gson.fromJson(resources, Resources.class), gson.fromJson(points, Points.class));
    }

    /**
     * Private method used for Venture Development Cards parsing,
     * that parses Points from JsonObject and returns a Goods Object.
     * @param card JsonObject from which method gets a specific value.
     * @return Goods Object containing endGamePoints set in Json file.
     */
    private Goods parseEndGameRewards(JsonObject card) {
        Gson gson = new Gson();
        JsonArray costs = card.get("endGamePoints").getAsJsonArray();
        Points endGamePoints = gson.fromJson(costs, Points.class);
        return new Goods(new Resources(), endGamePoints);
    }

    /**
     * Private method used for Development Cards parsing,
     * that parses a DevelopmentCard Object from a JsonObject
     * @param card JsonObject from which method gets a specific value.
     * @return DevelopmentCard Object containing cardInformation, cost and instantEffect set in Json file.
     */
    private DevelopmentCard parseDevelopmentCard(JsonObject card) {
        CardInformation cardInfo = parseCardInformation(card);
        List<Goods> costs = parseCosts(card);
        ExchangingGoods instantExchangingGoods = parseExchangingGoods(card);

        return new DevelopmentCard(cardInfo, costs, instantExchangingGoods);
    }

    /**
     * Private method used by parseDevelopmentCard,
     * that parses a CardInformation Object from a JsonObject.
     * @param card JsonObject from which method gets a specific value.
     * @return CardInformation Object containing cardInformation set in Json file.
     */
    private CardInformation parseCardInformation(JsonObject card) {
        JsonArray cardInfos = card.get("cardInformations").getAsJsonArray();
        JsonObject cardInfo = cardInfos.get(0).getAsJsonObject();
        String name = cardInfo.get("name").getAsString();
        int number = cardInfo.get("number").getAsInt();
        int period = cardInfo.get("period").getAsInt();
        String cardColor = cardInfo.get("cardColor").getAsString();
        PeriodNumber periodNumber = decidePeriodEnum(period);
        GeneralColor cardColorEnum = decideColorEnum(cardColor);
        return new CardInformation(number, name, periodNumber, cardColorEnum);
    }

    /**
     * Private method that sets during parsing, GeneralColor Enum attributes.
     * @param cardColor
     * @return
     */
    private GeneralColor decideColorEnum(String cardColor) {
        GeneralColor cardColorOfficial = null;
        switch(cardColor) {
            case "blue": cardColorOfficial = GeneralColor.BLUE; break;
            case "yellow": cardColorOfficial = GeneralColor.YELLOW; break;
            case "green": cardColorOfficial = GeneralColor.GREEN; break;
            case "purple": cardColorOfficial = GeneralColor.PURPLE; break;
            default:
                System.out.println("Invalid String");
        }
        return cardColorOfficial;
    }

    /**
     * Private method that sets during parsing, PeriodNumber Enum attributes.
     * @param period Int variable that is translated in Enum attribute.
     * @return PeriodNumberEnum Object.
     */
    private PeriodNumber decidePeriodEnum(int period) {
        PeriodNumber periodNumber = null;
        switch(period) {
            case 1: periodNumber = PeriodNumber.FIRST; break;
            case 2: periodNumber = PeriodNumber.SECOND; break;
            case 3: periodNumber = PeriodNumber.THIRD; break;
            default:
                System.out.println("Incorrect Value"); break;
        }
        return periodNumber;
    }

    /**
     * Private method that parses cost in a List of Goods Objects, from a JsonArray.
     * @param card JsonObject from which method gets a specific field.
     * @return List of Goods containing one or more costs set in Json file.
     */
    private List<Goods> parseCosts(JsonObject card) {
        Gson gson = new Gson();
        JsonArray costs = card.get("cost").getAsJsonArray();
        List<Goods> parsedCosts = new ArrayList<Goods>();
        for(int index = 0; index<costs.size(); index++) {
            JsonObject goodObject = costs.get(index).getAsJsonObject();
            Goods cost = gson.fromJson(goodObject, Goods.class);
            parsedCosts.add(cost);
        }
        return parsedCosts;
    }

    /**
     * Private method that parses instantEffect in an ExchangingGoods Object, from Json.
     * @param card JsonObject from which method gets a specific field.
     * @return ExchangingGoods Object containing instantEffect set in Json file.
     */
    private ExchangingGoods parseExchangingGoods(JsonObject card) {
        Gson gson = new Gson();
        JsonArray instantEffect = card.get("instantEffect").getAsJsonArray();
        ExchangingGoods parsedInstantEffect = gson.fromJson(instantEffect.get(0).getAsJsonObject(),
                                                            ExchangingGoods.class);

        return parsedInstantEffect;
    }

    private void open(String path) throws IOException{
        fr = new FileReader(path);
        br = new BufferedReader(fr);
    }
    private void close() throws IOException {
        br.close();
        fr.close();
    }
}