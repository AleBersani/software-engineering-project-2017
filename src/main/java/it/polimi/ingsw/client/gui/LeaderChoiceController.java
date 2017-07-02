package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeaderChoiceController implements Initializable{
    private ArrayList<ImageView> leaderCards;
    private ArrayList<String> ultimateLeaders;

    @FXML
    private ImageView led1, led2, led3, led4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLeaderList();
        leaderCards = new ArrayList<>();
        ultimateLeaders = new ArrayList<>();
    }

    public void setLeaderList() {
        leaderCards.add(led1);
        leaderCards.add(led2);
        leaderCards.add(led3);
        leaderCards.add(led4);
    }

    public void setLeaderCards(ArrayList<String> availableLeaders) {
        ultimateLeaders.clear();
        ultimateLeaders = availableLeaders;
        for (int i = 0; i < leaderCards.size(); i++) {
            Image newLeader = new Image("client/leader/" + availableLeaders.get(i) + ".jpg");
            leaderCards.get(i).setImage(newLeader);
        }
    }

    public void clearLeaderList() {
        leaderCards.clear();
    }

    @FXML
    public String selectLeader1(){
        String leader1 = led1.getImage().toString();
        return leader1;
    }

    @FXML
    public String selectLeader2(){
        String leader2 = led2.getImage().toString();
        return leader2;
    }

    @FXML
    public String selectLeader3(){
        String leader3 = led3.getImage().toString();
        return leader3;
    }

    @FXML
    public String selectLeader4(){
        String leader4 = led4.getImage().toString();
        return leader4;
    }





    public String saveLeader1() {
        return ultimateLeaders.get(0);
    }

    public String saveLeader2() {
        return ultimateLeaders.get(1);
    }

    public String saveLeader3() {
        return ultimateLeaders.get(2);
    }

    public String saveLeader4() {
        return ultimateLeaders.get(3);
    }

}