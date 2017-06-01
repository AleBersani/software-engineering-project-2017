package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.cards.development.*;
import it.polimi.ingsw.gamelogic.cards.development.Character;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
        JsonArray cards = object.get("territory").getAsJsonArray();

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
    private void close() throws IOException{
        br.close();
        fr.close();
    }
}