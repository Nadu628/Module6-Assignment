package org.example.module6;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WelcomeToThePartyController {
    @FXML
    private Label welcomeUserLabel;
    @FXML
    private AnchorPane welcomeToThePartyPane;


    public void welcomeMessage(String name){
        welcomeUserLabel.setText("Welcome to the party " + name + "!");
    }
}
