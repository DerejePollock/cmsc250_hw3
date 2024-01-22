package edu.lawrence.tabbedhello;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class PrimaryController {

    
    
    private List<Players> players = new ArrayList<>();
    private List<Pair<Players, Players>> matches = new ArrayList<>();

   
    @FXML TextField nameText;
    @FXML Label greeting;
    @FXML ListView matchesID;
    private Players model;
    @FXML
    private void enterName(ActionEvent event) {
        String name = nameText.getText();
        String message = "Hello, "+name+". Welcome to JavaFX!";
        greeting.setText(message);
    }
    
    public void registerPlayer(String playerName) {
        players.add(new Players(playerName));
    }

    
     @FXML
    private void saveAll(ActionEvent event) {
        PrintWriter output = null;
        try { 
            output = new PrintWriter(new File("reservations.txt"));
            model.writeTo(output);
            output.close();
        } catch(Exception ex) {
            System.out.println("Error writing data to text file");
        }
    }
    
    

  
}


