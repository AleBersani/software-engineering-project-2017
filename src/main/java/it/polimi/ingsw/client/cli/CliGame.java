package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.cli.gameinformation.BoardOwnerInformation;
import it.polimi.ingsw.client.cli.gameinformation.CardsInformation;
import it.polimi.ingsw.client.cli.model.*;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.support.GameStartType;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CliGame {
    private static final Logger LOGGER = Logger.getLogger(LoginCli.class.getName());

    private Scanner input;
    private List<String> mainMenu;
    private List<String> performActionMenu;
    //liste di comandi possibili

    private BoardLight boardLight;
    private Owner owner;
    private boolean disconnect = false;
    private ClientSender clientSender;
    private RequestsGeneratorCli requestsGeneratorCli;
    private AtomicBoolean flagToObserve;

    public CliGame() {
        this.input = new Scanner(System.in);
        mainMenu = setMainMenuCommands();
        performActionMenu = setPerformActionMenu();
        boardLight = BoardLight.getInstance();
        owner = Owner.getInstance();
        clientSender = new ClientSenderHandler();
        requestsGeneratorCli = new RequestsGeneratorCli();
    }

    public void init() {
        chooseGameType();
    }

    private void chooseGameType() {
        String choice;
        boolean requestSent = false;
        //flagToObserve = new AtomicBoolean(ClientCliInformation.isGameStarted());
        do {
            System.out.printf("A: New Game\n" +
                              "B: Resume Game\n");
            choice = input.next();
            System.out.println("Choice is "+ choice);
            if ("A".equals(choice.trim().toUpperCase())){
                clientSender.choseGameType(GameStartType.NEW);
                Thread threadObserver = new Thread(new ObserveThisForCli(flagToObserve));
                threadObserver.run();
                try {
                    threadObserver.join();
                } catch (InterruptedException e) {
                    System.out.println("An Error Occurred!");
                }
                System.out.println("Request Sent! ");
            } else if ("B".equals(choice.trim().toUpperCase()))
                System.out.printf("Work in progress...\n");
            //System.out.println(ClientCliInformation.isGameStarted() + " :Game is started?");
        } while (!flagToObserve.get());
        chooseLeaderCards();
    }

    private void chooseLeaderCards() {
        List<Card> leadersToShowUp;
        List<String[]> leadersToShowUpInformation;
        int maxNumOfLines;
        String leaderChosen;
        do {
            leadersToShowUp = ClientCliInformation.getCurrentLeadersToChoice();
            leadersToShowUpInformation = getLeadersToShowUpString(leadersToShowUp);
            maxNumOfLines = getMaxNumOfLines(leadersToShowUpInformation);
            System.out.println("Choose your Leaders: ");
            for (int i = 0; i < maxNumOfLines; i++) {
                for (int j = 0; j < leadersToShowUpInformation.size(); j++) {
                    if (i > leadersToShowUpInformation.get(j).length)
                        System.out.printf("| %-40s |", " ");
                    else {
                        System.out.printf("| %-40s |", leadersToShowUpInformation.get(j)[i]);
                    }
                }
                System.out.printf("\n");
            }
            leaderChosen = input.nextLine();
            input.nextLine();
            clientSender.sendToServer(requestsGeneratorCli.generateLeaderChoice(leaderChosen.trim()));
            try {
                Thread.sleep(500, 0);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "An Exception was thrown for waiting Thread ", e);
            }
       } while (!true);
        chooseBonusTile();
    }

    private int getMaxNumOfLines(List<String[]> leadersToShowUpInformation) {
        int max = 0;
        for (int i = 0; i < leadersToShowUpInformation.size(); i++) {
            if (leadersToShowUpInformation.get(i).length > max)
                max = leadersToShowUpInformation.get(i).length;
        }
        return max;
    }

    private List<String[]> getLeadersToShowUpString(List<Card> leadersToShowUp) {
        Card card;
        List<String[]> listOfInformation = new ArrayList<>();
        Optional<LeaderCardLight> leaderCardLightOptional;
        for (int i = 0; i < leadersToShowUp.size(); i++) {
            StringBuilder leadersInformation = new StringBuilder();
            card = leadersToShowUp.get(i);
            leaderCardLightOptional = CardsInformation.searchForLeaderCardLight(card.getName());
            if (leaderCardLightOptional.isPresent()) {
                leadersInformation.append(card.getName()).append("\n");
                leadersInformation.append(leaderCardLightOptional.get().getEffectDescription());
                listOfInformation.add(leadersInformation.toString().split("\n"));
            }
        }
        return listOfInformation;
    }

    private void chooseBonusTile() {
        List<String> bonusTilesToChoose;
        List<String[]> bonusTilesInformation;
        String choice;
        do {
            bonusTilesToChoose = ClientCliInformation.getCurrentBonusTilesToChoice();
            if (bonusTilesToChoose.size() < 0) {
                System.out.printf("Waiting for other players to choose\n");
            } else {
                System.out.printf("Choose your Bonus Tile!\n");
                bonusTilesInformation = getBonusTilesInformation(bonusTilesToChoose);
                for (int i = 0; i < bonusTilesInformation.get(0).length; i++) {
                    for (int j = 0; j < bonusTilesInformation.size(); j++) {
                        System.out.printf("| %-25s |", bonusTilesInformation.get(j)[i]);
                    }
                    System.out.printf("\n");
                }
                choice = input.nextLine();
                input.nextLine();
                if (BoardOwnerInformation.searchForBonusTileLight(choice.trim()).isPresent()) {
                    clientSender.sendToServer(requestsGeneratorCli.generateChosenBonusTile(choice));
                } else {
                    System.out.printf("Incorrect choice! \n");
                }
            }
        } while(!true);
        showMainMenu();
    }

    private List<String[]> getBonusTilesInformation(List<String> bonusTilesToChoose) {
        Optional<BonusTileDescriptionLight> bonusTileDescriptionLight;
        List<String[]> bonusTileToPrint = new ArrayList<>();
        for (int i = 0; i < bonusTilesToChoose.size(); i++) {
            StringBuilder description = new StringBuilder();
            bonusTileDescriptionLight = BoardOwnerInformation.searchForBonusTileLight(bonusTilesToChoose.get(i));
            if (bonusTileDescriptionLight.isPresent()) {
                description.append(bonusTileDescriptionLight.get().getBonusTileIdentifier()).append("\n");
                description.append(bonusTileDescriptionLight.get().getDescription()).append("\n");
                bonusTileToPrint.add(description.toString().split("\n"));
            }
        }
        return bonusTileToPrint;
    }

    public void showMainMenu() {
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
                    case "Z": disconnect = true; //Disconnessione?
                        break;
                    case "I": showCardInformation(); break;
                    default: break;
                }
            }
            else
                System.out.println("Sorry, your choice \""+ s +"\" is not valid\n");
        }while (!disconnect);
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

    private void showBoard() {
        printTowers();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                                                                                diceLight.getValue()));
        System.out.println("\n");
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
        System.out.printf("%s", owner.printPlayerLight());
        System.out.printf("%s", owner.printOwnResourcesPoints());
        System.out.printf("%s\n", owner.printBonusTiles());
        printDevelopmentCards();
        System.out.printf("%s\n", owner.printLeaders());
    }

    public void printDevelopmentCards() {
        String[] territoriesCards = getCardsName(owner.getTerritories());
        String[] buildingCards = getCardsName(owner.getBuildings());
        String[] characterCards = getCardsName(owner.getCharacters());
        String[] ventureCards = getCardsName(owner.getVentures());
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
        boolean back = false;
        boolean actionPerformed = false;
        String choice;
        do {
            System.out.printf("Choose an action to perform: \n" +
                              "A: Get a card from Tower\n" +
                              "B: Place on Market\n" +
                              "C: Place on Council Palace\n" +
                              "D: Place on Harvest area\n" +
                              "E: Place on Production area\n" +
                              "F: Activate a Leader card\n" +
                              "G: Placement of Leader\n" +
                              "H: Discard a Leader\n" +
                              "Z: Go back\n");
            choice = input.next();
            if (isPresentCommand(performActionMenu, choice.trim().toUpperCase())) {
                switch (choice) {
                    case "A":
                        placeOnTowers();
                        actionPerformed = true;
                        break;
                    case "B":
                        placeOnMarket();
                        actionPerformed = true;
                        break;
                    case "C":
                        placeOnCouncilPalace();
                        actionPerformed = true;
                        break;
                    case "D":
                        placeOnHarvestArea();
                        actionPerformed = true;
                        break;
                    case "E":
                        placeOnProductionArea();
                        actionPerformed = true;
                        break;
                    case "F":
                        activateALeaderCard();
                        actionPerformed = true;
                        break;
                    case "G":
                        placeALeaderCard();
                        actionPerformed = true;
                        break;
                    case "H":
                        discardALeaderCard();
                        actionPerformed = true;
                        break;
                    case "Z":
                        back = true;
                        break;
                }
            }
        } while (!back && !actionPerformed);

    }

    private void placeOnTowers() {
        printTowers();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                diceLight.getValue()));
        placeOnBoardSpace();
    }

    private void placeOnMarket() {
        printOtherSpaces();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                diceLight.getValue()));
        placeOnBoardSpace();

    }

    private void placeOnCouncilPalace() {
        printCouncilPalace();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                diceLight.getValue()));
        placeOnBoardSpace();
    }

    private void placeOnHarvestArea() {
        printOtherSpaces();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                diceLight.getValue()));
        placeOnBoardSpace();
    }

    private void placeOnProductionArea() {
        printOtherSpaces();
        boardLight.getDiceLightList().forEach(diceLight -> System.out.printf("{%s -> %d}     ", diceLight.getDiceColor().toString(),
                diceLight.getValue()));
        placeOnBoardSpace();
    }

    private void placeOnBoardSpace() {
        boolean sent = false;
        boolean incorrect;
        String boardIdentifier, pawnColor;
        int numberOfServants = 0;
        do {
            System.out.printf("Insert an Action, specifying: \n" +
                              "- identifier of the space: \n");
            incorrect = false;
            boardIdentifier = input.nextLine();
            input.nextLine();
            if (isBoardIdentifier(boardIdentifier)) {
                System.out.printf("- color of the pawn \n");
                pawnColor = input.nextLine();
                input.nextLine();
                if (isPawnColor(pawnColor.trim())) {
                    System.out.printf("- number of servants you want to sacrifice for grow pawn value\n");
                    if (input.hasNextInt()){
                        numberOfServants = input.nextInt();
                        clientSender.sendToServer(requestsGeneratorCli.generatePawnPlacement(boardIdentifier, pawnColor,
                                numberOfServants));
                        sent = true;
                    } else
                        incorrect = true;
                } else
                    incorrect = true;
            } else
                incorrect = true;
            if (incorrect) {
                System.out.println("Invalid input! \n");
            }
        } while (!sent);
    }

    private boolean isPawnColor(String trim) {
        Optional<PawnColor> pawnColorOptional =
                Arrays.stream(PawnColor.values()).filter(F -> F.equals(PawnColor.valueOf(trim))).findFirst();
        return pawnColorOptional.isPresent();
    }

    private boolean isBoardIdentifier(String boardIdentifier) {
        Optional<BoardIdentifier> boardIdentifierOptional =
                Arrays.stream(BoardIdentifier.values())
                        .filter(F -> F.equals(BoardIdentifier.valueOf(boardIdentifier))).findFirst();

        return boardIdentifierOptional.isPresent();
    }

    private void activateALeaderCard() {
        showPlayerBoard();
        String leaderCardName;
        Optional<Card> leaderCardInOwner;
        boolean sent = false;
        do {
            System.out.println("Enter a name of a Leader Card to activate: ");
            leaderCardName = input.nextLine();
            Optional<Card> found = Optional.empty();
            for (Card card : owner.getPlayerLight().getActivatedLeaders()) {
                if (card.getName().equals(leaderCardName.trim())) {
                    found = Optional.of(card);
                    break;
                }
            }
            leaderCardInOwner = found;
            if (CardsInformation.searchForLeaderCardLight(leaderCardName.trim()).isPresent() &&
                    !leaderCardInOwner.isPresent()) {
                //clientSender.sendToServer(requestsGeneratorCli.generateLeaderAction(ActionType.LEADER_ACTIVATION, leaderCardName));
                sent = true;
            } else
                System.out.println("Invalid Leader Card name!");
        } while (!sent);
    }

    private void placeALeaderCard() {
        showPlayerBoard();
        String leaderCardName;
        boolean sent = false;
        do {
            System.out.println("Enter a name of a Leader Card to place: ");
            leaderCardName = input.nextLine();
            if (CardsInformation.searchForLeaderCardLight(leaderCardName.trim()).isPresent()) {
               // clientSender.sendToServer(requestsGeneratorCli.generateLeaderAction(ActionType.LEADER_PLACEMENT, leaderCardName));
                sent = true;
            } else
                System.out.println("Invalid Leader Card name!");
        } while (!sent);
    }

    private void discardALeaderCard() {
        showPlayerBoard();
        String leaderCardName;
        Optional<Card> leaderCardInOwner;
        boolean sent = false;
        Owner owner = Owner.getInstance();
        do {
            System.out.println("Enter a name of a Leader Card to discard: ");
            leaderCardName = input.nextLine();
            Optional<Card> found = Optional.empty();
            for (Card card : owner.getPlayerLight().getActivatedLeaders()) {
                if (card.getName().equals(leaderCardName.trim())) {
                    found = Optional.of(card);
                    break;
                }
            }
            leaderCardInOwner = found;
            if (CardsInformation.searchForLeaderCardLight(leaderCardName.trim()).isPresent() &&
                    !leaderCardInOwner.isPresent()) {
              //  clientSender.sendToServer(requestsGeneratorCli.generateLeaderAction(ActionType.LEARD_DISCARD, leaderCardName));
                sent = true;
            } else
                System.out.println("Invalid Leader Card name!");
        } while (!sent);
    }

    private void passTurn() {

    }

    private void suggestGameInterruption() {
        System.out.println("WORK IN PROGRESS...");
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
                found = true;
            } else if ((leaderCardLight = CardsInformation.searchForLeaderCardLight(s)).isPresent()) {
                System.out.println(leaderCardLight.get().toString());
                found = true;
            } else if ((excommunicationTileLight = CardsInformation.searchForExcommunicationTileLight(s)).isPresent()) {
                System.out.println(excommunicationTileLight.get().toString());
                found = true;
            } else if ("E".equals(s)) {
                exit = true;
            }
        }while(!found && !exit);
    }

    private List<String> setPerformActionMenu() {
        List<String> commands = new ArrayList<>();
        commands.add("A");
        commands.add("B");
        commands.add("C");
        commands.add("D");
        commands.add("E");
        commands.add("F");
        commands.add("Z");
        return commands;
    }

    private boolean isPresentCommand(List<String> menu, String s) {
        Optional<String> command = menu.stream().filter(str -> str.equals(s)).findFirst();
        return command.isPresent();
    }
}
