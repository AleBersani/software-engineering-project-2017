package it.polimi.ingsw.client.cli.gameinformation;

import it.polimi.ingsw.client.cli.model.DevelopmentCardsLight;
import it.polimi.ingsw.client.cli.model.ExcommunicationTileLight;
import it.polimi.ingsw.client.cli.model.LeaderCardLight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardsInformation {
    private static List<DevelopmentCardsLight> developmentCardsLights;
    private static List<ExcommunicationTileLight> excommunicationTileLights;
    private static List<LeaderCardLight> leaderCardsLights;

    public static void initLists() {
        developmentCardsLights = new ArrayList<>();
        excommunicationTileLights = new ArrayList<>();
        leaderCardsLights = new ArrayList<>();
    }

    /**
     * Method that searches for a Development Card Light in a static List through stream, filtering by
     * card name. The first result with same name as "cardName", is returned.
     * @param cardName Card identifier
     * @return The DevelopmentCardLight object to be printed.
     */
    public static Optional<DevelopmentCardsLight> searchForDevelopmentCardLight(String cardName) {
        return developmentCardsLights.stream()
                                     .filter(T ->  cardName.equals(T.getName()))
                                     .findFirst();
    }

    /**
     * Method that searches for a Excommunication Tile Light in a static List through stream, filtering by
     * card name. The first result with same name as "cardName", is returned.
     * @param cardName Card identifier
     * @return The ExcommunicationTileLight object to be printed.
     */
    public static Optional<ExcommunicationTileLight> searchForExcommunicationTileLight(String cardName) {
        return excommunicationTileLights.stream()
                                        .filter(T ->  cardName.equals(T.getName()))
                                        .findFirst();
    }

    /**
     * Method that searches for a Leader Card Light in a static List through stream, filtering by
     * card name. The first result with same name as "cardName", is returned.
     * @param cardName Card identifier
     * @return The LeaderCardLight object to be printed.
     */
    public static Optional<LeaderCardLight> searchForLeaderCardLight(String cardName) {
        return leaderCardsLights.stream()
                                .filter(T ->  cardName.equals(T.getName()))
                                .findFirst();
    }

    public static List<DevelopmentCardsLight> getDevelopmentCardsLights() {
        return developmentCardsLights;
    }

    public static void setDevelopmentCardsLights(List<DevelopmentCardsLight> developmentCardsLights) {
        CardsInformation.developmentCardsLights = developmentCardsLights;
    }

    public static List<ExcommunicationTileLight> getExcommunicationTileLights() {
        return excommunicationTileLights;
    }

    public static void setExcommunicationTileLights(List<ExcommunicationTileLight> excommunicationTileLights) {
        CardsInformation.excommunicationTileLights = excommunicationTileLights;
    }

    public static List<LeaderCardLight> getLeaderCardsLights() {
        return leaderCardsLights;
    }

    public static void setLeaderCardsLights(List<LeaderCardLight> leaderCardsLights) {
        CardsInformation.leaderCardsLights = leaderCardsLights;
    }
}
