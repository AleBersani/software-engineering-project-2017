package it.polimi.ingsw.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabrizio on 05/06/2017.
 */
public class ParserAdditionalInfo {
    private ParserSettings settings;

    public ParserAdditionalInfo(ParserSettings settings) {
        this.settings = settings;
    }

    public void parseAdditionalInfoDevCards(Map<String, List<AdditionalCardInfo>> parsedAddInfo) {

    }










   /* public List<AdditionalCardInfo> parseSingleListAddInfoDevCards(String[] addInfoToInstantiate, JsonObject card) {
        int actionTypeIndex=0;
        for(int index=0; index<addInfoToInstantiate.length; index++){
            String type = addInfoToInstantiate[index];
            switch (type) {
                case "cardFlashAction": parseCardFlashAction(card, actionTypeIndex);
                    actionTypeIndex++; break;
                case "conditionalProduction": parseConditionalProduction(card); break;
                case "multipleProduction": parseMultipleProduction(card); break;
                case "requirementsOnCard": parseRequirementsOnCard(card); break;
                case "rewardsOnCard": parseRewardsOnCard(card); break;
                default: parseCardFlashExchangingGoods(card); break;
            }
        }
    }*/

    public String getDevCardName(JsonObject card) {
        String name = card.get("cardInformations").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        return name;
    }

    /*public String[] getAddInfoToInstantiateFromField(JsonObject addInfoList, String field){
        String[] addInfoToInstantiate = new String[];
        JsonArray fieldArray = addInfoList.get(field).getAsJsonArray();
        for(int index=0; index<fieldArray.size(); index++){
            addInfoToInstantiate[index] = fieldArray.get(index).getAsString();
        }
        return addInfoToInstantiate;
    }*/

    public boolean isEmpty(JsonArray addInfoList) {
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
