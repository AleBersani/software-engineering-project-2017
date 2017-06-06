package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.*;
import it.polimi.ingsw.gamelogic.cards.development.*;
import it.polimi.ingsw.gamelogic.cards.development.Character;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserCards {
    private ParserSettings settings;

    public ParserCards(ParserSettings settings) {
        this.settings = settings;
    }

    /**
     * Method that parses Territory Development Cards from Json.
     * @throws IOException
     */
    public List<Territory> completeParseTerritory() throws IOException {
        JsonObject devCards = settings.extractJsonObject("DevelopmentCards.json");
        JsonArray cards = devCards.get("territory").getAsJsonArray();

        List<Territory> territories = parseTerritories(cards);
        //territories.forEach((Territory s) -> System.out.print(s.toString() + "\n"));
        return territories;
    }

    /**
     * Method that parses Building Development Cards from Json
     * @throws IOException
     */
    public List<Building> completeParseBuilding() throws IOException {
        JsonObject devCards = settings.extractJsonObject("DevelopmentCards.json");
        JsonArray cards = devCards.get("building").getAsJsonArray();
        List<Building> buildings = parseBuildings(cards);
        //buildings.forEach((Building s) -> System.out.println(s.toString() + "\n"));
        return buildings;
    }

    /**
     * Method that parses Character Development Cards from Json
     * @throws IOException
     */
    public List<Character> completeParseCharacter() throws IOException {
        JsonObject devCards = settings.extractJsonObject("DevelopmentCards.json");
        JsonArray cards = devCards.get("character").getAsJsonArray();
        List<Character> characters = parseCharacters(cards);
        return characters;
    }

    /**
     * Method that parses Venture Development Cards from Json
     * @throws IOException
     */
    public List<Venture> completeParseVenture() throws IOException {
        JsonObject devCards = settings.extractJsonObject("DevelopmentCards.json");
        JsonArray cards = devCards.get("venture").getAsJsonArray();
        List<Venture> ventures = parseVentures(cards);
        return ventures;
    }

    /**
     * TODO: Javadoc
     * @return
     * @throws IOException
     */
    public List<ExcommunicationTile> completeParseExcommunicationTiles() throws IOException {

        JsonObject excommunications = settings.extractJsonObject("ExcommunicationTiles.json");
        JsonArray cards = excommunications.get("excommunicationTiles").getAsJsonArray();
        List<ExcommunicationTile> excommunicationTiles = parseExcommunicationTiles(cards);
        return excommunicationTiles;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    public List<LeaderCard> completeParseLeaderCards() throws IOException {
        JsonObject object = settings.extractJsonObject("LeaderCards.json");
        JsonArray leaders = object.get("leaderCards").getAsJsonArray();
        List<LeaderCard> parsedLeaderCards = parseLeaderCards(leaders);
        return parsedLeaderCards;
    }


    /**PRIVATE METHODS**/

    /**
     * TODO JAVADOC
     * @param cards
     * @return
     */
    private List<Territory> parseTerritories(JsonArray cards) {
        List<Territory> territories = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);

            /*CardInformation cardInfo = parseCardInformation(card);
            List<Goods> costs = parseListGoods(card);
            ExchangingGoods instantExchangingGoods = parseExchangingGoods(card);*/

            int harvestActionValueRequired = card.get("harvestActionValueRequired").getAsInt();
            ExchangingGoods harvestResult = parseExchangingGoods(card, "harvestResult");
            territories.add(new Territory(developmentCard, harvestActionValueRequired, harvestResult));

        }
        return territories;
    }

    /**
     * TODO JAVADOC
     * @param cards
     * @return
     */
    private List<Building> parseBuildings(JsonArray cards) {
        List<Building> buildings = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            int productionActionValueRequired = card.get("productionActionValueRequired").getAsInt();
            ExchangingGoods productionResult = parseExchangingGoods(card, "productionResult");
            buildings.add(new Building(developmentCard, productionActionValueRequired, productionResult));
        }
        return buildings;
    }

    /**
     * TODO JAVADOC
     * @param cards
     * @return
     */
    private List<Character> parseCharacters(JsonArray cards) {
        List<Character> characters = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++) {
            JsonObject card = cards.get(index).getAsJsonObject();
            BasicDevelopmentCard developmentCard = parseDevelopmentCard(card);
            characters.add(new Character(developmentCard));
        }
        return characters;
    }

    /**
     * TODO JAVADOC
     * @param cards
     * @return
     */
    private List<Venture> parseVentures(JsonArray cards) {
        List<Venture> ventures = new ArrayList<>();
        Gson gson = new Gson();
        for(int index = 0; index<cards.size(); index++) {
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
     * that parses a DevelopmentCard Object from a JsonObject
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

    /*
     * Private method that parses cost in a List of Goods Objects, from a JsonArray.
     * @param costs JsonArray from which method gets JsonObject to parse.
     * @return List of Goods containing one or more costs set in Json file.

    private List<Goods> parseListGoods(JsonArray costs) {
        Gson gson = new Gson();
        List<Goods> parsedCosts = new ArrayList<>();
        for(int index = 0; index<costs.size(); index++) {
            JsonObject goodObject = costs.get(index).getAsJsonObject();
            Goods cost = gson.fromJson(goodObject, Goods.class);
            parsedCosts.add(cost);
        }
        return parsedCosts;
    }*/

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
        GeneralColor cardColorEnum = decideGeneralColorEnum(cardColor);
        return new CardInformation(number, name, periodNumber, cardColorEnum);
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
     * TODO JAVADOC
     * @param cards
     * @return
     */
    private List<ExcommunicationTile> parseExcommunicationTiles(JsonArray cards) {
        List<ExcommunicationTile> excommunicationTiles = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject card;
        JsonObject excommunicationTile;
        for(int index=0; index<cards.size(); index++){
            card = cards.get(index).getAsJsonObject();
            excommunicationTile = card.get("excommunicationDetails").getAsJsonObject();
            ExcommunicationTile parsedExcommunicationTile = parseSingleExcommunicationTile(excommunicationTile);
            excommunicationTiles.add(parsedExcommunicationTile);
        }
        return excommunicationTiles;
    }

    /**
     * TODO JAVADOC
     * @param excommunicationTile
     * @return
     */
    private ExcommunicationTile parseSingleExcommunicationTile(JsonObject excommunicationTile) {
        String name = excommunicationTile.get("ExcommunicationTileName").getAsString();
        PeriodNumber period = decidePeriodEnum(excommunicationTile.get("period").getAsInt());
        return new ExcommunicationTile(name, period);
    }

    /**
     * TODO JAVADOC
     * @param leaders
     * @return
     */
    private List<LeaderCard> parseLeaderCards(JsonArray leaders) {
        List<LeaderCard> parsedLeaderCards = new ArrayList<>();
        for(int index=0; index<leaders.size(); index++){
            LeaderInformation cardInfo = parseLeaderInformation(leaders.get(index).getAsJsonObject());
            JsonArray leaderCosts = leaders.get(index).getAsJsonObject().get("requirements").getAsJsonArray();
            List<LeaderCost> parsedLeaderCostList = parseLeaderCosts(leaderCosts);
            parsedLeaderCards.add(new LeaderCard(cardInfo, parsedLeaderCostList));
        }
        return parsedLeaderCards;
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     */
    private LeaderInformation parseLeaderInformation(JsonObject card) {
        String name = card.get("name").getAsString();
        return new LeaderInformation(name, parseLeaderCategory(card.get("abilityType").getAsString()));
    }

    /**
     * TODO Javadoc
     * @return
     * @throws IOException
     * @param category
     */
    private LeaderCategory parseLeaderCategory(String category) {
        Map<String, LeaderCategory> categoryToReturn = new HashMap<>();
        categoryToReturn.put("permanent", LeaderCategory.PERMANENT);
        categoryToReturn.put("consumable", LeaderCategory.CONSUMABLE);
        return categoryToReturn.get(category);
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

}
