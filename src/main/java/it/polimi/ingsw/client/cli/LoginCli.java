package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.connection.ConnectionStarter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCli implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(LoginCli.class.getName());
    private Scanner input;
    private List<String> connectionCommands;
    private List<String> loginRegisterCommands;
    private boolean connectionChosen;

    private ConnectionStarter connectionStarter;
    private AtomicBoolean flagToObserve;

    public LoginCli() {
        input = new Scanner(System.in);
        connectionCommands = new ArrayList<>();
        loginRegisterCommands = new ArrayList<>();
        connectionChosen = false;
    }

    @Override
    public void run() {
        init();
    }

    private void init() {
        ClientInformation.initClientInformation();
        connectionCommands = setConnectionAndLoginRegisterCommands();
        loginRegisterCommands = setConnectionAndLoginRegisterCommands();
        chooseConnection();
    }

    private List<String> setConnectionAndLoginRegisterCommands() {
        List<String> commands = new ArrayList<>();
        commands.add("A");
        commands.add("B");
        return commands;
    }

    private void chooseConnection() {
        String s;
        do {
            System.out.printf("Choose your connection: \n" +
                    "\'A\': RMI Connection\n" +
                    "\'B\': Socket Connection\n");
            s = input.next();
            s = s.trim().toUpperCase();
            if (choiceIsCorrect(s, connectionCommands)) {
                connectionChosen = true;
                switch (s) {
                    case "A":
                        connectionStarter = new ConnectionStarter("RMI");
                        break;
                    case "B":
                        connectionStarter = new ConnectionStarter("Socket_".concat(chooseIp()));
                }
                connectionStarter.startConnection();
                login();
            } else {
                System.out.printf("Your choice is incorrect! \n");
            }
        } while(!connectionChosen);
        CliMain cliMain = new CliMain();
        cliMain.init();
    }

    private boolean choiceIsCorrect(String s, List<String> commands) {
        Optional<String> command = commands.stream().filter(str -> str.equals(s)).findFirst();
        return command.isPresent();
    }

    private String chooseIp() {
        String ipAddress;
        System.out.println("Choose your Ip Address: (Default: 127.0.0.1)");
        ipAddress = input.nextLine();
        input.nextLine();
        if ("".equals(ipAddress.trim())){
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    private void login() {
        String nickname, password;
        boolean newClient;
        do {
            newClient = chooseNewClient();
            System.out.println("Enter your NickName: ");
            nickname = input.next();
            System.out.println("Enter your Password: ");
            password = input.next();
            connectionStarter.authenticate(nickname, password, newClient);
            try {
                Thread.sleep(500, 0);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "An Exception was thrown for waiting Thread ", e);
            }
        } while (!ClientInformation.isLoginSuccessful());
    }

    private boolean chooseNewClient() {
        boolean newClient = false;
        boolean chosen = false;
        String loginRegister;
        do {
            System.out.printf("Choose to: \n" +
                    "A: Login\n" +
                    "B: Register\n");
            loginRegister = input.next();
            loginRegister = loginRegister.trim().toUpperCase();
            if (choiceIsCorrect(loginRegister, loginRegisterCommands)) {
                newClient = (loginRegister.equals("A")) ? false : true;
                chosen = true;
            } else
                System.out.printf("Your choice is incorrect!\n");
        } while (!chosen);
        return newClient;
    }
}
