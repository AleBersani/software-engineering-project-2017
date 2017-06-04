package it.polimi.ingsw.gamelogic.modifiers.endgamerewards;

import it.polimi.ingsw.gamelogic.basics.Points;

/**
 * TODO: JavaDoc
 */
public class BasicEndGameRewards {
    private Points pointsForCharacterCards;
    private Points pointsForVentureCards;
    private Points pointsForTerritoryCards;
    private Points onePointLessForEveryFiveVictoryPoints;
    private Points onePointLessForEveryMilitaryPoints;
    private Points onePointLessForEveryWoodAndStoneOnBuilding;
    private Points onePointLessForEveryResource;

    public BasicEndGameRewards(Points pointsForCharacterCards, Points pointsForVentureCards, Points pointsForTerritoryCards) {
        this.pointsForCharacterCards = pointsForCharacterCards;
        this.pointsForVentureCards = pointsForVentureCards;
        this.pointsForTerritoryCards = pointsForTerritoryCards;
        onePointLessForEveryFiveVictoryPoints = new Points();
        onePointLessForEveryMilitaryPoints = new Points();
        onePointLessForEveryWoodAndStoneOnBuilding = new Points();
        onePointLessForEveryResource = new Points();
    }

    public Points calculateFinalEndGameRewards() {
        Points finalPoints = new Points();
        finalPoints.add(pointsForCharacterCards);
        finalPoints.add(pointsForVentureCards);
        finalPoints.add(pointsForTerritoryCards);
        finalPoints.subtract(onePointLessForEveryFiveVictoryPoints);
        finalPoints.subtract(onePointLessForEveryMilitaryPoints);
        finalPoints.subtract(onePointLessForEveryWoodAndStoneOnBuilding);
        finalPoints.subtract(onePointLessForEveryResource);
        return finalPoints;
    }

    public Points getPointsForCharacterCards() {
        return pointsForCharacterCards;
    }

    public void setPointsForCharacterCards(Points pointsForCharacterCards) {
        this.pointsForCharacterCards = pointsForCharacterCards;
    }

    public Points getPointsForVentureCards() {
        return pointsForVentureCards;
    }

    public void setPointsForVentureCards(Points pointsForVentureCards) {
        this.pointsForVentureCards = pointsForVentureCards;
    }

    public Points getPointsForTerritoryCards() {
        return pointsForTerritoryCards;
    }

    public void setPointsForTerritoryCards(Points pointsForTerritoryCards) {
        this.pointsForTerritoryCards = pointsForTerritoryCards;
    }

    public Points getOnePointLessForEveryFiveVictoryPoints() {
        return onePointLessForEveryFiveVictoryPoints;
    }

    public void setOnePointLessForEveryFiveVictoryPoints(Points onePointLessForEveryFiveVictoryPoints) {
        this.onePointLessForEveryFiveVictoryPoints = onePointLessForEveryFiveVictoryPoints;
    }

    public Points getOnePointLessForEveryMilitaryPoints() {
        return onePointLessForEveryMilitaryPoints;
    }

    public void setOnePointLessForEveryMilitaryPoints(Points onePointLessForEveryMilitaryPoints) {
        this.onePointLessForEveryMilitaryPoints = onePointLessForEveryMilitaryPoints;
    }

    public Points getOnePointLessForEveryWoodAndStoneOnBuilding() {
        return onePointLessForEveryWoodAndStoneOnBuilding;
    }

    public void setOnePointLessForEveryWoodAndStoneOnBuilding(Points onePointLessForEveryWoodAndStoneOnBuilding) {
        this.onePointLessForEveryWoodAndStoneOnBuilding = onePointLessForEveryWoodAndStoneOnBuilding;
    }

    public Points getOnePointLessForEveryResource() {
        return onePointLessForEveryResource;
    }

    public void setOnePointLessForEveryResource(Points onePointLessForEveryResource) {
        this.onePointLessForEveryResource = onePointLessForEveryResource;
    }
}
