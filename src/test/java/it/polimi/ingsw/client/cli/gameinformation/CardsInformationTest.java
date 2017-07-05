package it.polimi.ingsw.client.cli.gameinformation;

import it.polimi.ingsw.client.cli.model.DevelopmentCardsLight;
import it.polimi.ingsw.client.cli.model.ExcommunicationTileLight;
import it.polimi.ingsw.client.cli.model.LeaderCardLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardsInformationTest {
    @BeforeEach
    void setUp() {
        CardsInformation.initLists();
        DevelopmentCardsLight card1 = new DevelopmentCardsLight("Bosco", "4C",
                "3Se", "3Mp");
        DevelopmentCardsLight card2 = new DevelopmentCardsLight("Zecca","4C",
                "3Se", "3Mp");
        CardsInformation.getDevelopmentCardsLights().add(card1);
        CardsInformation.getDevelopmentCardsLights().add(card2);
        ExcommunicationTileLight excommunicationTile1 = new ExcommunicationTileLight("1.2",
                "4C, 3S");
        ExcommunicationTileLight excommunicationTile2 = new ExcommunicationTileLight("1.5",
                "4C, 3S");
        CardsInformation.getExcommunicationTileLights().add(excommunicationTile1);
        CardsInformation.getExcommunicationTileLights().add(excommunicationTile2);
        LeaderCardLight leaderCard1 = new LeaderCardLight("Lorenzo Il Magnifico",
                "4Se", "4Mp");
        LeaderCardLight leaderCard2 = new LeaderCardLight("Francesco Sforza",
                "4Se", "4Mp");
        CardsInformation.getLeaderCardsLights().add(leaderCard1);
        CardsInformation.getLeaderCardsLights().add(leaderCard2);
    }

    @Test
    void testSearchForDevelopmentCardLight() {
        Optional<DevelopmentCardsLight> result;
        DevelopmentCardsLight resultExpected = new DevelopmentCardsLight("Zecca","4C",
                "3Se", "3Mp");
        result = CardsInformation.searchForDevelopmentCardLight("Zecca");
        assertTrue(result.isPresent());
        result.ifPresent(developmentCardsLight -> assertEquals(resultExpected, developmentCardsLight));
    }

    @Test
    void testSearchForExcommunicationTileLight() {
        Optional<ExcommunicationTileLight> result;
        ExcommunicationTileLight resultExpected = new ExcommunicationTileLight("1.2",
                "4C, 3S");
        result = CardsInformation.searchForExcommunicationTileLight("1.2");
        assertTrue(result.isPresent());
        result.ifPresent(excommunicationTileLight -> assertEquals(resultExpected, excommunicationTileLight));
    }

    @Test
    void testSearchForLeaderCardLight() {
        Optional<LeaderCardLight> result;
        LeaderCardLight resultExpected = new LeaderCardLight("Lorenzo Il Magnifico",
                "4Se", "4Mp");
        result = CardsInformation.searchForLeaderCardLight("Lorenzo Il Magnifico");
        assertTrue(result.isPresent());
        result.ifPresent(leaderCardLight -> assertEquals(resultExpected, leaderCardLight));
    }

    @Test
    void testGetDevelopmentCardsLights() {
        DevelopmentCardsLight card1 = new DevelopmentCardsLight("Bosco", "4C",
                "1Se", "3Mp");
        DevelopmentCardsLight card2 = new DevelopmentCardsLight("Zecca","4C",
                "3Se", "2Mp");
        List<DevelopmentCardsLight> cardsLights = new ArrayList<>();
        cardsLights.add(card1);
        cardsLights.add(card2);
        CardsInformation.setDevelopmentCardsLights(cardsLights);
        assertEquals(cardsLights, CardsInformation.getDevelopmentCardsLights());
    }

    @Test
    void testGetExcommunicationTileLights() {
        ExcommunicationTileLight excommunicationTile1 = new ExcommunicationTileLight("1.2",
                "2C, 3S");
        ExcommunicationTileLight excommunicationTile2 = new ExcommunicationTileLight("1.5",
                "4C, 1S");
        List<ExcommunicationTileLight> excommunicationTileLights = new ArrayList<>();
        excommunicationTileLights.add(excommunicationTile1);
        excommunicationTileLights.add(excommunicationTile2);
        CardsInformation.setExcommunicationTileLights(excommunicationTileLights);
        assertEquals(excommunicationTileLights, CardsInformation.getExcommunicationTileLights());
    }

    @Test
    void testGetLeaderCardsLights() {
        LeaderCardLight leaderCard1 = new LeaderCardLight("Lorenzo Il Magnifico",
                "4Se", "4Vp");
        LeaderCardLight leaderCard2 = new LeaderCardLight("Francesco Sforza",
                "4S", "4Mp");
        List<LeaderCardLight> leaderCardLights = new ArrayList<>();
        leaderCardLights.add(leaderCard1);
        leaderCardLights.add(leaderCard2);
        CardsInformation.setLeaderCardsLights(leaderCardLights);
        assertEquals(leaderCardLights, CardsInformation.getLeaderCardsLights());
    }
}