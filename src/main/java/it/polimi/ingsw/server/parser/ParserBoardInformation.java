package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.server.gamecontroller.gameelements.BoardInformation;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.board.CouncilPalace;
import it.polimi.ingsw.server.gamelogic.board.MarketSpace;
import it.polimi.ingsw.server.gamelogic.board.ProductionHarvestSpace;
import it.polimi.ingsw.server.gamelogic.board.Space;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserBoardInformation {
    private ParserSettings parserSettings;

    public ParserBoardInformation() {
        this.parserSettings = new ParserSettings();
    }

    public void parseTowerMaps() throws IOException {
        JsonObject board = parserSettings.extractJsonObject("Board.json");
        BoardInformation.setGreenTower(parseSingleTowerMap(board.get("towers")
                .getAsJsonObject()
                .get("greenTower")
                .getAsJsonArray()));
        BoardInformation.setYellowTower(parseSingleTowerMap(board.get("towers")
                .getAsJsonObject()
                .get("yellowTower")
                .getAsJsonArray()));
        BoardInformation.setBlueTower(parseSingleTowerMap(board.get("towers")
                .getAsJsonObject()
                .get("blueTower")
                .getAsJsonArray()));
        BoardInformation.setPurpleTower(parseSingleTowerMap(board.get("towers")
                .getAsJsonObject()
                .get("purpleTower")
                .getAsJsonArray()));
    }

    private Map<Space, Goods> parseSingleTowerMap(JsonArray towerJson) {
        JsonObject object;
        Space space;
        Gson gson = new Gson();
        Goods bonus;
        Map<Space, Goods> towerInformation = new HashMap<>();
        for (int index = 0; index < towerJson.size(); index++) {
            object = towerJson.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            bonus = gson.fromJson(object.get("instantGoods").getAsJsonObject(), Goods.class);
            towerInformation.put(space, bonus);
        }
        return towerInformation;
    }

    private Space parseSpace(JsonObject space) {
        int requestedValue;
        requestedValue = space.get("requestedValue").getAsInt();
        return new Space(decideBoardId(space), requestedValue);
    }

    private BoardIdentifier decideBoardId(JsonObject space) {
        return BoardIdentifier.valueOf(space.get("boardIdentifier").getAsString());
    }

    public CouncilPalace parseCouncilPalace() throws IOException {
        JsonObject board, councilPalace;
        board = parserSettings.extractJsonObject("Board.json");
        councilPalace = board.get("councilPalace").getAsJsonObject();
        return getParsedCouncilPalace(councilPalace);
    }

    private CouncilPalace getParsedCouncilPalace(JsonObject councilPalace) {
        Gson gson = new Gson();
        ExchangingGoods bonusCouncil;
        Resources resources;
        Points points;
        int requestedValue;
        resources = gson.fromJson(councilPalace.get("instantGoods").getAsJsonObject()
                                    .get("resources").getAsJsonObject(), Resources.class);
        points = gson.fromJson(councilPalace.get("instantGoods").getAsJsonObject()
                                .get("points").getAsJsonObject(), Points.class);
        bonusCouncil = new ExchangingGoods(resources, points, councilPalace.get("instantGoods").getAsJsonObject()
                                                                .get("councilPrivilege").getAsInt() );
        requestedValue = councilPalace.get("requestedValue").getAsInt();
        return new CouncilPalace(bonusCouncil, requestedValue);
    }

    public void parseBoardActionAreas() throws IOException {
        JsonObject board, bonusActionSpaces;
        board = parserSettings.extractJsonObject("Board.json");
        bonusActionSpaces = board.get("boardActionSpaces").getAsJsonObject();
        parseProductionHarvestLists(BoardInformation.getHarvestArea(), bonusActionSpaces.get("harvestArea").getAsJsonArray());
        parseProductionHarvestLists(BoardInformation.getProductionArea(), bonusActionSpaces.get("productionArea").getAsJsonArray());
        parseMarket(BoardInformation.getMarketArea(), bonusActionSpaces.get("marketArea").getAsJsonArray());
    }

    private void parseProductionHarvestLists(List<ProductionHarvestSpace> productionHarvestArea, JsonArray area) {
        JsonObject object;
        Space space;
        int malusValue;
        for (int index = 0; index < area.size(); index++){
            object = area.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            malusValue = object.get("malusValue").getAsInt();
            productionHarvestArea.add(new ProductionHarvestSpace(space, malusValue));
        }
    }

    public void parseMarket(List<MarketSpace> marketArea, JsonArray market) {
        Gson gson = new Gson();
        JsonObject object;
        Space space;
        ExchangingGoods exchangingGoods;
        Resources resources;
        Points points;
        for (int index=0; index < market.size(); index++) {
            object = market.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            resources = gson.fromJson(object.get("instantGoods").getAsJsonObject().get("resources").getAsJsonObject(),
                                                                    Resources.class);
            points = gson.fromJson(object.get("instantGoods").getAsJsonObject().get("points").getAsJsonObject(),
                                                                            Points.class);
            exchangingGoods = new ExchangingGoods(resources, points, object.get("instantGoods").
                                                    getAsJsonObject().get("councilPrivilege").getAsInt());
            marketArea.add(new MarketSpace(space, exchangingGoods));
        }
    }

    public void parseBonusTiles() throws IOException {
        JsonObject boardInformation;
        JsonArray bonusTiles;
        boardInformation = parserSettings.extractJsonObject("Board.json");
        bonusTiles = boardInformation.get("bonusTiles").getAsJsonArray();
        //getParsedBonusTiles(BoardInformation.bonusTiles, bonusTiles);
    }

    private void getParsedBonusTiles(Map<String, List<ExchangingGoods>> parsedBonusTiles, JsonArray bonusTiles) {
        JsonObject object;
        String name;
        ExchangingGoods production, harvest;
        List<ExchangingGoods> exchangingGoods;
        for (int i=0; i < bonusTiles.size(); i++) {
            object = bonusTiles.get(i).getAsJsonObject();
            name = object.get("name").getAsString();
            production = parseExchangingGoods(object.get("bonusProduction").getAsJsonObject());
            harvest = parseExchangingGoods(object.get("bonusHarvest").getAsJsonObject());
            exchangingGoods = new ArrayList<>();
            exchangingGoods.add(production);
            exchangingGoods.add(harvest);
            parsedBonusTiles.put(name, exchangingGoods);
        }
    }

    private ExchangingGoods parseExchangingGoods(JsonObject instantGoods) {
        Gson gson = new Gson();
        Resources resources;
        Points points;
        resources = gson.fromJson(instantGoods.get("resources").getAsJsonObject(), Resources.class);
        points = gson.fromJson(instantGoods.get("points").getAsJsonObject(), Points.class);
        return new ExchangingGoods(resources, points, instantGoods.get("councilPrivilege").getAsInt());
    }
}
