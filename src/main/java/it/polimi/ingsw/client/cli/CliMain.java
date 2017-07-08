package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.cli.gameinformation.BoardOwnerInformation;
import it.polimi.ingsw.client.cli.gameinformation.CardsInformation;
import it.polimi.ingsw.client.cli.model.BoardSpaceDescriptionLight;
import it.polimi.ingsw.client.cli.model.DevelopmentCardsLight;
import it.polimi.ingsw.client.cli.model.ExcommunicationTileLight;
import it.polimi.ingsw.client.cli.model.LeaderCardLight;
import it.polimi.ingsw.client.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CliMain {
//    private boolean disconnect = false;
//    private ClientGameInformation gameInformation;
//
//    public void showMainMenu() {
//        //MAPPA DI METODI DA INVOCARE
//        Scanner input = new Scanner(System.in);
//        do {
//            System.out.println("Main Menu:\n" +
//                                "Z: Exit\n" +
//                                "A: Show Board\n" +
//                                "B: Show your player board\n" +
//                                "C: Show other players' information\n" +
//                                "D: Perform an action\n" +
//                                "E: Pass the turn\n" +
//                                "F: Suggest game's interruption\n");
//            String s = input.next();
//            //se s è presente nella mappa, invoco, altrimenti:
//            System.out.println("Sorry, your choice \""+ s +"\" is not valid\n");
//        }while (!disconnect);
//    }
//
//    public void showBoard() {
//        BoardLight board = gameInformation.getBoardLight();
//        String boardToPrint = board.toString();
//        //DA RIVEDERE
//        System.out.println(boardToPrint);
//    }
//
//    public void showPlayerBoard() {
//        PlayerLight playerInfo = gameInformation.getPlayerLight();
//        String playerInfoToPrint = playerInfo.toString();
//        System.out.println(playerInfoToPrint);
//    }
//
//    public void showOtherPlayers() {
//
//    }
//
//    public void showPerformActionMenu() {
//
//    }
//
//    public void passTurn() {
//
//    }
//
//    public void suggestGameInterruption() {
//
//    }

    private Scanner input;
    private List<String> mainMenu;
    private List<String> performActionMenu;
    //liste di comandi possibili

    private BoardLight boardLight;
    private Owner mainPlayer;
    private boolean disconnect = false;

    public void init(Scanner input) {
        this.input = input;
        mainMenu = setMainMenuCommands();
        boardLight = BoardLight.getInstance();
        mainPlayer = Owner.getInstance();

        showMainMenu();
    }

    private List<String> setMainMenuCommands() {
        List<String> commands = new ArrayList<>();
        commands.add("Z");
        commands.add("A");
        commands.add("B");
        commands.add("C");
        commands.add("D");
        commands.add("E");
        commands.add("F");
        commands.add("I");
        return commands;
    }

    private List<String> setPerformActionMenu() {
        List<String> commands = new ArrayList<>();
        return commands;
    }


    public void showMainMenu() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Main Menu:\n" +
                    "Z: Exit\n" +
                    "A: Show Board\n" +
                    "B: Show your player board\n" +
                    "C: Show other players' information\n" +
                    "D: Perform an action\n" +
                    "E: Pass the turn\n" +
                    "F: Suggest game's interruption\n" +
                    "I: Show Card Information\n");
            String s = input.next();
            s = s.trim().toUpperCase();
            if (isPresentCommand(mainMenu, s)) {
                switch (s) {
                    case "A": showBoard(); break;
                    case "B": showPlayerBoard(); break;
                    case "C": showOtherPlayers(); break;
                    case "D": showPerformActionMenu(); break;
                    case "E": passTurn(); break;
                    case "F": suggestGameInterruption(); break;
                    case "Z": disconnect = true; break;
                    case "I": showCardInformation(); break;
                    default: break;
                }
            }
            else
                System.out.println("Sorry, your choice \""+ s +"\" is not valid\n");
        }while (!disconnect);
    }

    private boolean isPresentCommand(List<String> menu, String s) {
        Optional<String> command = menu.stream().filter(str -> str.equals(s)).findFirst();
        return command.isPresent();
    }

    private void showBoard() {
        printTowers();
        printOtherSpaces();
        printCouncilPalace();
    }

    private void printTowers() {
        String greenTower = "Green Tower:\n" + getStringTower(boardLight.getGreenTower());
        String yellowTower = "Yellow Tower:\n" + getStringTower(boardLight.getYellowTower());
        String blueTower = "Blue Tower:\n" + getStringTower(boardLight.getBlueTower());
        String purpleTower = "Purple Tower:\n" + getStringTower(boardLight.getPurpleTower());
        String[] greenTowerStrings = greenTower.split("\n");
        String[] yellowTowerStrings = yellowTower.split("\n");
        String[] blueTowerStrings = blueTower.split("\n");
        String[] purpleTowerStrings = purpleTower.split("\n");
        for (int i = 0; i < greenTowerStrings.length && i < yellowTowerStrings.length &&
                i < blueTowerStrings.length && i < purpleTowerStrings.length; i++) {
            System.out.printf("|| %-40s ||   ", greenTowerStrings[i]);
            System.out.printf("|| %-40s ||   ", yellowTowerStrings[i]);
            System.out.printf("|| %-40s ||   ", blueTowerStrings[i]);
            System.out.printf("|| %-40s ||\n", purpleTowerStrings[i]);
        }
        for (int i = 0; i < 193; i++)
            System.out.printf("_");
        System.out.printf("\n");
    }

    private String getStringTower(List<TowerSlotLight> tower) {
        StringBuilder towerToString = new StringBuilder();
        for (TowerSlotLight towerSlotLight : tower)
            towerToString.append(towerSlotLight.toString());
        return towerToString.toString();
    }

    private void printOtherSpaces() {
        String market = "Market: \n"+ getStringActionSpaces(boardLight.getMarket());
        String harvest = "Harvest Area: \n" + getStringActionSpaces(boardLight.getHarvest());
        String production = "Production Area: \n" + getStringActionSpaces(boardLight.getProduction());
        String[] marketStrings = market.split("\n");
        String[] harvestStrings = harvest.split("\n");
        String[] productionStrings = production.split("\n");
        for (int i = 0; i < marketStrings.length && i < harvestStrings.length &&
                i < productionStrings.length; i++) {
            System.out.printf("|  %-56s  |   ", marketStrings[i]);
            System.out.printf("|  %-56s  |   ", harvestStrings[i]);
            System.out.printf("|  %-56s  |\n", productionStrings[i]);
        }
    }

    private String getStringActionSpaces(List<SlotLight> slotLightList) {
        StringBuilder toString = new StringBuilder();
        Optional<BoardSpaceDescriptionLight> slotLightOptional;
        for (SlotLight slot :slotLightList) {
            toString.append(slot.toString());
            if (slot.getPawnLight() == null)
                toString.append(" \n");
            slotLightOptional = BoardOwnerInformation.searchForBoardSpaceLight(slot.getBoardIdentifier());
            if (slotLightOptional.isPresent()){
                toString.append("Bonus: ").append(slotLightOptional.get().getBonus()).append("\n");
                toString.append("Malus: ").append(slotLightOptional.get().getMalus()).append("\n");
            }
        }
        return toString.toString();
    }

    private void printCouncilPalace() {
        System.out.println(boardLight.getCouncilPalaceLight().toString());
    }

    private void showPlayerBoard() {
        System.out.printf("%s", mainPlayer.printPlayerLight());
        System.out.printf("%s", mainPlayer.printOwnResourcesPoints());
        System.out.printf("%s\n", mainPlayer.printBonusTiles());
        printDevelopmentCards();
        System.out.printf("%s\n", mainPlayer.printLeaders());
    }

    public void printDevelopmentCards() {
        String[] territoriesCards = getCardsName(mainPlayer.getTerritories());
        String[] buildingCards = getCardsName(mainPlayer.getBuildings());
        String[] characterCards = getCardsName(mainPlayer.getCharacters());
        String[] ventureCards = getCardsName(mainPlayer.getVentures());
        int maxSize = Integer.max(
                Integer.max(territoriesCards.length, buildingCards.length),
                Integer.max(characterCards.length, ventureCards.length));
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("| %-35s |   ", territoriesCards[i]);
            System.out.printf("| %-35s |   ", buildingCards[i]);
            System.out.printf("| %-35s |   ", characterCards[i]);
            System.out.printf("| %-35s |\n", ventureCards[i]);
        }
    }

    private String[] getCardsName(List<Card> cards) {
        String[] names = new String[6];
        for (int i = 0; i < names.length; i++){
            if (i < cards.size())
                names[i] = cards.get(i).getName();
            else
                names[i] = " ";
        }
        return names;
    }

    private void showOtherPlayers() {
        List<String[]> totalPlayersInfo = getTotalPlayersInfo();
        for (int i = 0; i < totalPlayersInfo.get(0).length; i++) {
            for (int j = 0; j < totalPlayersInfo.size(); j++) {
                if (totalPlayersInfo.size() == 3){
                    System.out.printf("  %-62s", totalPlayersInfo.get(j)[i]);
                }
                else {
                    System.out.printf("    %-85s", totalPlayersInfo.get(j)[i]);
                }
            }
            System.out.printf("\n");
        }
    }

    private List<String[]> getTotalPlayersInfo() {
        StringBuilder eachPlayerInfo;
        List<String[]> totalPlayersInfo = new ArrayList<>();
        for (PlayerLight player : boardLight.getPlayerLights()) {
            eachPlayerInfo = new StringBuilder().append(player.printPlayerLightInfo());
            eachPlayerInfo.append(player.printPlayerPoints());
            totalPlayersInfo.add(eachPlayerInfo.toString().split("\n"));
        }
        return totalPlayersInfo;
    }

    private void showPerformActionMenu() {

    }

    private void passTurn() {

    }

    private void suggestGameInterruption() {

    }

    private void showCardInformation() {
        Scanner input = new Scanner(System.in);
        Optional<DevelopmentCardsLight> developmentCardsLight;
        Optional<LeaderCardLight> leaderCardLight;
        Optional<ExcommunicationTileLight> excommunicationTileLight;
        boolean found = false, exit = false;
        do {
            System.out.println("Insert a Card name or \'E\' to return to previous Menu\n");
            String s = input.next();
            s = s.replaceAll("^\\s+|\\s+$", "");
            if ((developmentCardsLight = CardsInformation.searchForDevelopmentCardLight(s)).isPresent()) {
                System.out.println(developmentCardsLight.get().toString());
            } else if ((leaderCardLight = CardsInformation.searchForLeaderCardLight(s)).isPresent()) {
                System.out.println(leaderCardLight.get().toString());
            } else if ((excommunicationTileLight = CardsInformation.searchForExcommunicationTileLight(s)).isPresent()) {
                System.out.println(excommunicationTileLight.get().toString());
            }

        }while(!found || !exit);
    }
}
