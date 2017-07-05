package it.polimi.ingsw.server.gameelements;

import it.polimi.ingsw.server.parser.ParserAdditionalInfo;
import it.polimi.ingsw.server.parser.ParserBoardInformation;
import it.polimi.ingsw.server.parser.ParserCards;
import it.polimi.ingsw.server.parser.ParserConfigurations;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetGameElements implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(SetGameElements.class.getName());

    private ParserCards parserCards;
    private ParserAdditionalInfo parserAdditionalInfo;
    private ParserConfigurations parserConfigurations;
    private ParserBoardInformation parserBoardInformation;

    public SetGameElements() {
        parserCards = new ParserCards();
        parserAdditionalInfo = new ParserAdditionalInfo();
        parserConfigurations = new ParserConfigurations();
        parserBoardInformation = new ParserBoardInformation();
    }

    @Override
    public void run() {
        try {
            setupElements();
            LOGGER.info("Game elements loaded");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: cannot run set game elements thread", e);
        }
    }

    /**
     * Public method used to set static attributes of game elements' classes, through Parser classes.
     * @throws Exception Can be thrown from dependent methods invoked for parsing elements.
     */
    private void setupElements() throws Exception {
        setupCards();
        setupAdditionalInfoMaps();
        setupBasicConfigurations();
        setupBoardConfiguration();
    }


    private void setupCards() throws IOException {
        Cards.setTerritories(parserCards.completeParseTerritory());
        Cards.setBuildings(parserCards.completeParseBuilding());
        Cards.setCharacters(parserCards.completeParseCharacter());
        Cards.setVentures(parserCards.completeParseVenture());
        Cards.setExcommunicationTiles(parserCards.completeParseExcommunicationTiles());
        Cards.setLeaderCards(parserCards.completeParseLeaderCards());
    }

    private void setupAdditionalInfoMaps() throws Exception {
        AdditionalInfoMaps.initializeMaps();
        parserAdditionalInfo.parseMapsAdditionalInfo(AdditionalInfoMaps.getFlashEffectsOnChoice(),
                                                     AdditionalInfoMaps.getFlashEffectsNotSelectable(),
                                                     AdditionalInfoMaps.getPermanentEffectsOnChoice(),
                                                     AdditionalInfoMaps.getPermanentEffectsNotSelectable());
        AdditionalInfoMaps.setThirdPeriodExcommunicationModifiers(
                                                parserAdditionalInfo.parseThirdPeriodExcommunicationsMap());
    }

    private void setupBasicConfigurations() throws IOException {
        parserConfigurations.parseBoardConfiguration();
        parserConfigurations.parseGameConfiguration();
        parserConfigurations.parseCouncilPrivilege();
        parserConfigurations.parsePlayerConfiguration();
    }

    private void setupBoardConfiguration() throws IOException {
        BoardInformation.initializeBoardInformationMaps();
        parserBoardInformation.parseTowerMaps();
        BoardInformation.setCouncilPalace(parserBoardInformation.parseCouncilPalace());
        parserBoardInformation.parseFaithPointsToAvoidExcommunicationMap(
                BoardInformation.getFaithPointsToAvoidExcommunication());
        parserBoardInformation.parseFaithToVictoryPointsMap(BoardInformation.getFaithToVictoryPoints());
        parserBoardInformation.parseBoardActionAreas();
        parserBoardInformation.parseBonusTiles();
    }


}
