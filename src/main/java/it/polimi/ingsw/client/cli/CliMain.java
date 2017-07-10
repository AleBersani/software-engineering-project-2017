package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.parser.ParserLightModel;

import java.io.IOException;

public class CliMain {
    public static void main(String args[]) {
        System.out.println("ciao!");
        ParserLightModel parserLightModel = new ParserLightModel();
        System.out.println("SONO QUI");
        try {
            parserLightModel.completeParseCLI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("arrivo qui");
        LoginCli loginCli = new LoginCli();
        loginCli.run();
    }
}
