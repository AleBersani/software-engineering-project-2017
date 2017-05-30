package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.development.Building;
import it.polimi.ingsw.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.gamelogic.cards.development.Territory;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private BufferedReader br = null;
    private FileReader fr = null;
    private final static String PATH = System.getProperty("user.dir") + "/src/json/DevelopmentCards.json";
    public Parser() throws IOException {

    }

    public void parseTerritory() throws IOException {
        open();

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("territory").getAsJsonArray();

        List<Territory> territories = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();

            CardInformation cardInfo = parseCardInformation(card);
            List<Goods> costs = parseCosts(card);
            // FlashEffect instantEffect = parseFlashEffect(card);
            int harvestActionValueRequired = card.get("harvestActionValueRequired").getAsInt();
            ExchangingGoods harvestResult = parseHarvestResult(card);
            territories.add(new Territory(new DevelopmentCard(cardInfo, costs, instantEffect), harvestActionValueRequired, harvestResult));

        }
        territories.forEach((Territory s) -> System.out.print(s.toString() + "\n"));
        close();
    }

    public void parseBuilding() throws IOException {
        open();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("building").getAsJsonArray();
        List<Building> buildings = new ArrayList<>();
        for(int index = 0; index<cards.size(); index++){
            JsonObject card = cards.get(index).getAsJsonObject();
            CardInformation cardInfo = parseCardInformation(card);
            List<Goods> costs = parseCosts(card);


        }

    }




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

    /*private FlashEffect parseFlashEffect(JsonObject card) {
        Gson gson = new Gson();
        JsonArray instantEffect = card.get("instantEffect").getAsJsonArray();
        ExchangingGoods parsedInstantEffect = gson.fromJson(instantEffect.get(0).getAsJsonObject(),
                ExchangingGoods.class);

        return new FlashEffect (parsedInstantEffect);
    }*/

    private ExchangingGoods parseHarvestResult(JsonObject card) {
        Gson gson = new Gson();
        JsonArray harvestResult = card.get("harvestResult").getAsJsonArray();
        ExchangingGoods parsedHarvestResult = gson.fromJson(harvestResult.get(0).getAsJsonObject(),
                ExchangingGoods.class);
        return parsedHarvestResult;
    }


    /*public void parseFile2() throws IOException{
        open();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(br).getAsJsonObject();
        JsonArray cards = object.get("cards").getAsJsonArray();
        for(int index = 0; index<cards.size(); index++){
            JsonObject cardObject = cards.get(index).getAsJsonObject();
            Card card = gson.fromJson(cardObject, Card.class);

            System.out.println(card.getName() + "-" + card.getPeriod() + "-" + card.getColor());
        }
        close();
    }*/

    private void open() throws IOException{
        fr = new FileReader(PATH);
        br = new BufferedReader(fr);
    }
    private void close() throws IOException{
        br.close();
        fr.close();
    }




}
