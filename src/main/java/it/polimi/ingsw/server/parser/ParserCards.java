package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.gamelogic.basics.*;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.server.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.server.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that contains methods for parsing every type of Cards.
 */
public class ParserCards {
    private static final String DEVELOPMENT_CARDS_JSON = "DevelopmentCards.json";

    private ParserSettings settings;

    public ParserCards() {
        settings = new ParserSettings();
    }

    /**
     * Method that invokes an auxiliary method that parses Territory Development Cards from Json.
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<Territory> completeParseTerritory() throws IOException {
        JsonObject devCards = settings.extractJsonObject(DEVELOPMENT_CARDS_JSON);
        JsonArray cards = devCards.get("territory").getAsJsonArray();
        return parseTerritories(cards);
    }

    /**
     * Method that invokes an auxiliary method that parses Building Development Cards from Json.
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<Building> completeParseBuilding() throws IOException {
        JsonObject devCards = settings.extractJsonObject(DEVELOPMENT_CARDS_JSON);
        JsonArray cards = devCards.get("building").getAsJsonArray();
        return parseBuildings(cards);
    }

    /**
     * Method that invokes an auxiliary method that parses Character Development Cards from Json.
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<Character> completeParseCharacter() throws IOException {
        JsonObject devCards = settings.extractJsonObject(DEVELOPMENT_CARDS_JSON);
        JsonArray cards = devCards.get("character").getAsJsonArray();
        return parseCharacters(cards);
    }

    /**
     * Method that parses Venture Development Cards from Json.
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<Venture> completeParseVenture() throws IOException {
        JsonObject devCards = settings.extractJsonObject(DEVELOPMENT_CARDS_JSON);
        JsonArray cards = devCards.get("venture").getAsJsonArray();
        return parseVentures(cards);
    }

    /**
     * Method that invokes an auxiliary method that parses Excommunication Tile from Json.
     * @return //
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<ExcommunicationTile> completeParseExcommunicationTiles() throws IOException {
        JsonObject excommunications = settings.extractJsonObject("ExcommunicationTiles.json");
        JsonArray cards = excommunications.get("ExcommunicationTiles").getAsJsonArray();
        return parseExcommunicationTiles(cards);
    }

    /**
     * Method that invokes an auxiliary method that parses Leader Cards from Json.
     * @return //
     * @throws IOException Can be thrown by settings.extractJsonObject method.
     */
    public List<LeaderCard> completeParseLeaderCards() throws IOException {
        JsonObject object = settings.extractJsonObject("LeaderCards.json");
        JsonArray leaders = object.get("LeaderCards").getAsJsonArray();
        return parseLeaderCards(leaders);
    }

    /**
     * Auxiliary method that parses Territory Development Cards and returns a list of Territory cards.
     * @param cards JsonArray containing all cards to parse
     * @return A List of Territory Cards.
     */
    private List<Territory> parseTerritories(JsonArray cards) {
        List<Territory> territories = new ArrayList<>();
        for (int index = 0; index < cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            int harvestActionValueRequired = card.get("harvestActionValueRequired").getAsInt();
            ExchangingGoods harvestResult = parseExchangingGoods(card, "harvestResult");
            territories.add(new Territory(developmentCard, harvestActionValueRequired, harvestResult));
        }
        return territories;
    }

    /**
     * Auxiliary method that parses Building Development Cards and returns a list of Building cards.
     * @param cards JsonArray containing all cards to parse
     * @return A List of Building Cards.
     */
    private List<Building> parseBuildings(JsonArray cards) {
        List<Building> buildings = new ArrayList<>();
        for (int index = 0; index < cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            int productionActionValueRequired = card.get("productionActionValueRequired").getAsInt();
            ExchangingGoods productionResult = parseExchangingGoods(card, "productionResult");
            buildings.add(new Building(developmentCard, productionActionValueRequired, productionResult));
        }
        return buildings;
    }

    /**
     * Auxiliary method that parses Character Development Cards and returns a list of Character cards.
     * @param cards JsonArray containing all cards to parse
     * @return A List of Character Cards.
     */
    private List<Character> parseCharacters(JsonArray cards) {
        List<Character> characters = new ArrayList<>();
        for(int index = 0; index < cards.size(); index++) {
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            characters.add(new Character(developmentCard));
        }
        return characters;
    }

    /**
     * Auxiliary method that parses Venture Development Cards and returns a list of Venture cards.
     * @param cards JsonArray containing all cards to parse
     * @return A List of Venture Cards.
     */
    private List<Venture> parseVentures(JsonArray cards) {
        List<Venture> ventures = new ArrayList<>();
        Gson gson = new Gson();
        for (int index = 0; index < cards.size(); index++) {
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            Goods endGameRewards = parseEndGameRewards(card);
            List<Goods> requirementsOnCosts = gson.fromJson(card.get("requirementsOnCost").getAsJsonArray(),
                                                            new TypeToken<ArrayList<Goods>>(){}.getType());
            ventures.add(new Venture(developmentCard, endGameRewards, requirementsOnCosts));
        }
        return ventures;
    }

    /**
     * Private method used for Development Cards parsing,
     * that parses a BasicDevelopmentCard Object from a JsonObject.
     * @param card JsonObject from which method gets a specific value.
     * @return DevelopmentCard Object containing cardInformation, cost and instantEffect set in Json file.
     */
    private BasicDevelopmentCard parseDevelopmentCard(JsonObject card) {
        Gson gson = new Gson();
        CardInformation cardInfo = parseCardInformation(card);
        JsonArray cost = card.get("cost").getAsJsonArray();
        List<Goods> costs = gson.fromJson(cost, new TypeToken<ArrayList<Goods>>(){}.getType());
        return new BasicDevelopmentCard(cardInfo, costs);
    }

    /**
     * Private method that parses instantEffect in an ExchangingGoods Object, from Json.
     * @param card JsonObject from which method gets a specific field.
     * @return ExchangingGoods Object containing instantEffect set in Json file.
     */
    private ExchangingGoods parseExchangingGoods(JsonObject card, String fieldName) {
        Gson gson = new Gson();
        JsonObject exchangingGoods = card.get(fieldName).getAsJsonArray().get(0).getAsJsonObject();
        Resources resources = gson.fromJson(exchangingGoods.get("resources").getAsJsonObject(), Resources.class);
        Points points = gson.fromJson(exchangingGoods.get("points").getAsJsonObject(), Points.class);
        int councilPrivilege = exchangingGoods.get("councilePrivilege").getAsInt();
        return new ExchangingGoods(resources, points, councilPrivilege);
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
        return new CardInformation(number, name, decidePeriodEnum(period), decideGeneralColorEnum(cardColor));
    }

    /**
     * Private method that sets during parsing, PeriodNumber Enum attributes.
     * @param period Int variable that is translated in Enum attribute.
     * @return PeriodNumberEnum Object.
     */
    private PeriodNumber decidePeriodEnum(int period) {
        Map<Integer, PeriodNumber> periodToReturn = new HashMap<>();
        periodToReturn.put(1, PeriodNumber.FIRST);
        periodToReturn.put(2, PeriodNumber.SECOND);
        periodToReturn.put(3, PeriodNumber.THIRD);

        return periodToReturn.get(period);
    }

    /**
     * Private method that sets during parsing, GeneralColor Enum attributes.
     * @param cardColor //
     * @return //
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
     * Private method used for Venture Development Cards parsing,
     * that parses Points from JsonObject and returns a Goods Object.
     * @param card JsonObject from which method gets a specific value.
     * @return Goods Object containing endGamePoints set in Json file.
     */
    private Goods parseEndGameRewards(JsonObject card) {
        Gson gson = new Gson();
        JsonObject costs = card.get("endGamePoints").getAsJsonArray().get(0).getAsJsonObject();
        Points endGamePoints = gson.fromJson(costs, Points.class);
        return new Goods(new Resources(), endGamePoints);
    }

    /**
     * Auxiliary method that parses Excommunication Tiles and returns a list of Excommunication Tiles.
     * @param cards //
     * @return //
     */
    private List<ExcommunicationTile> parseExcommunicationTiles(JsonArray cards) {
        List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
        JsonObject card;
        JsonObject excommunicationTile;
        for(int index = 0; index < cards.size(); index++){
            card = cards.get(index).getAsJsonObject();
            excommunicationTile = card.get("excommunicationTileDetails").getAsJsonObject();
            ExcommunicationTile parsedExcommunicationTile = parseSingleExcommunicationTile(excommunicationTile);
            excommunicationTiles.add(parsedExcommunicationTile);
        }
        return excommunicationTiles;
    }

    /**
     * Auxiliary method used by the previous one that parses a single Excommunication Tile.
     * @param excommunicationTileDetails JsonObject representing a single Excommunication Tile's information.
     * @return A single ExcommunicationTile object.
     */
    private ExcommunicationTile parseSingleExcommunicationTile(JsonObject excommunicationTileDetails) {
        String name = excommunicationTileDetails.get("ExcommunicationTileName").getAsString();
        PeriodNumber period = decidePeriodEnum(excommunicationTileDetails.get("period").getAsInt());
        return new ExcommunicationTile(name, period);
    }

    /**
     * Auxiliary method that parses Leader Cards and returns a list of Leader Cards.
     * @param leaders JsonArray containing all LeaderCards' information.
     * @return A List of LeaderCards
     */
    private List<LeaderCard> parseLeaderCards(JsonArray leaders) {
        List<LeaderCard> parsedLeaderCards = new ArrayList<>();
        LeaderInformation cardInfo;
        JsonArray leaderCosts;
        List<LeaderCost> parsedLeaderCostList;
        for (int index = 0; index < leaders.size(); index++){
            cardInfo = parseLeaderInformation(leaders.get(index).getAsJsonObject());
            leaderCosts = leaders.get(index).getAsJsonObject().get("requirements").getAsJsonArray();
            parsedLeaderCostList = parseLeaderCosts(leaderCosts);
            parsedLeaderCards.add(new LeaderCard(cardInfo, parsedLeaderCostList));
        }
        return parsedLeaderCards;
    }

    /**
     * Auxiliary method that parses a Leader Information object necessary to the instantiation of
     * a LeaderCard Object.
     * @param card //
     * @return //
     */
    private LeaderInformation parseLeaderInformation(JsonObject card) {
        String name = card.get("name").getAsString();
        return new LeaderInformation(name, parseLeaderCategory(card.get("abilityType").getAsString()));
    }

    /**
     * Auxiliary method that parses a Leader Category enum property necessary to the instantiation
     * of a LeaderCard Object.
     * @param category //
     * @return //
     */
    private LeaderCategory parseLeaderCategory(String category) {
        Map<String, LeaderCategory> categoryToReturn = new HashMap<>();
        categoryToReturn.put("permanent", LeaderCategory.PERMANENT);
        categoryToReturn.put("consumable", LeaderCategory.CONSUMABLE);
        return categoryToReturn.get(category);
    }

    /**
     * Auxiliary method that parses a List of LeaderCost necessary to the instantiation of a LeaderCard Object.
     * @param leaderCosts //
     * @return //
     */
    private List<LeaderCost> parseLeaderCosts(JsonArray leaderCosts) {
        List<LeaderCost> parsedLeaderCosts = new ArrayList<>();
        JsonObject cardCost;
        List<CardsRequired> parsedCardsRequired;
        Goods requiredGoods;
        for (int index = 0; index < leaderCosts.size(); index++){
            cardCost = leaderCosts.get(index).getAsJsonObject();
            parsedCardsRequired = parseCardsRequiredList(cardCost.get("developmentCards").
                    getAsJsonObject());
            requiredGoods = parseGoods(cardCost);
            parsedLeaderCosts.add(new LeaderCost(requiredGoods, parsedCardsRequired));
        }
        return parsedLeaderCosts;
    }

    /**
     * Auxiliary method that parses a List of CardsRequired necessary to the instantiation of a LeaderCost Object.
     * @param cardsRequired //
     * @return //
     */
    private List<CardsRequired> parseCardsRequiredList(JsonObject cardsRequired) {
        List<CardsRequired> parsedCardsRequired = new ArrayList<>();
        if (cardsRequired.get("territories").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("territories").getAsInt(), GeneralColor.GREEN));
        if (cardsRequired.get("buildings").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("buildings").getAsInt(), GeneralColor.YELLOW));
        if (cardsRequired.get("characters").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("characters").getAsInt(), GeneralColor.BLUE));
        if (cardsRequired.get("ventures").getAsInt() != 0)
            parsedCardsRequired.add(new CardsRequired(cardsRequired.get("ventures").getAsInt(), GeneralColor.PURPLE));

        return parsedCardsRequired;
    }

    /**
     * Auxiliary method that parses a Goods object.
     * @param leaderCost //
     * @return //
     */
    private Goods parseGoods(JsonObject leaderCost) {
        JsonObject resources = leaderCost.get("resources").getAsJsonObject();
        JsonObject points = leaderCost.get("points").getAsJsonObject();
        Gson gson = new Gson();
        return new Goods(gson.fromJson(resources, Resources.class), gson.fromJson(points, Points.class));
    }
}
