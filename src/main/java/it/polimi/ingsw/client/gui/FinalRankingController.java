package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FinalRankingController implements Initializable {
    @FXML
    private GridPane table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<List<String>> rows = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("#");
        list.add("PlayerName");
        list.add("255Vp");
        list.add("4");
        rows.add(list);
        moreRows(rows);
    }

    public void moreRows(List<List<String>> list) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(30.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(Priority.NEVER);
        for (int j = 0; j < list.size(); j++) {
            table.getRowConstraints().add(rowConstraints);
            for (int i = 0; i < list.get(j).size(); i++) {
                table.add(new Label(list.get(j).get(i)), i, j+1);
            }
        }
    }
}