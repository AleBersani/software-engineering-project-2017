package it.polimi.ingsw.server.gamecontroller.gameelements;

import it.polimi.ingsw.server.parser.ParserAdditionalInfo;
import it.polimi.ingsw.server.parser.ParserBoardInformation;
import it.polimi.ingsw.server.parser.ParserCards;
import it.polimi.ingsw.server.parser.ParserConfigurations;

import java.io.IOException;

public class SetGameElements implements Runnable {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Public method used to set static attributes of game elements' classes, through Parser classes.
     * @throws Exception Can be thrown from dependent methods invoked for parsing elements.
     */
    public void setupElements() throws Exception {
        setupCards();
        setupAdditionalInfoMaps();
        setupBasicConfigurations();
        setupBoardConfiguration();
    }


    private void setupCards() throws IOException {
        Cards.territories = parserCards.completeParseTerritory();
        Cards.buildings = parserCards.completeParseBuilding();
        Cards.characters = parserCards.completeParseCharacter();
        Cards.ventures = parserCards.completeParseVenture();
        Cards.excommunicationTiles = parserCards.completeParseExcommunicationTiles();
        Cards.leaderCards = parserCards.completeParseLeaderCards();
    }

    private void setupAdditionalInfoMaps() throws Exception {
        AdditionalInfoMaps.initializeMaps();
        parserAdditionalInfo.parseMapsAdditionalInfo(AdditionalInfoMaps.flashEffectsOnChoice,
                                                     AdditionalInfoMaps.flashEffectsNotSelectable,
                                                     AdditionalInfoMaps.permanentEffectsOnChoice,
                                                     AdditionalInfoMaps.permanentEffectsNotSelectable);
    }

    private void setupBasicConfigurations() throws IOException {
        parserConfigurations.parseBoardConfiguration();
        parserConfigurations.parseGameConfiguration();
        parserConfigurations.parseCouncilePrivilege();
    }

    private void setupBoardConfiguration() throws IOException {
        BoardInformation.initializeBoardInformationMaps();
        parserBoardInformation.parseTowerMaps();
        BoardInformation.councilPalace = parserBoardInformation.parseCouncilPalace();
        parserBoardInformation.parseBoardActionAreas();
    }


}
