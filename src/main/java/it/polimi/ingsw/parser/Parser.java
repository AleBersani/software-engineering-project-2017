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
     * Private method that sets during parsing, GeneralColor Enum attributes.
     * @param cardColor
     * @return
     */


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