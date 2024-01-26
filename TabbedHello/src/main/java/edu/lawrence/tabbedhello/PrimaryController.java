package edu.lawrence.tabbedhello;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController {
    private List<Players> players = new ArrayList<>();
    @FXML TextField nameText;
    @FXML ListView<String> namesListView;
    

    public void initialize(URL url, ResourceBundle rb) {
        loadNamesFromFile();
    }
    
    public void registerPlayer() {
        String playerName = nameText.getText().trim();
        if (playerName.isEmpty()) {
            // Handle the case where the player name is empty, e.g., show an error message
            return;
        }

        Players newPlayer = new Players(playerName);
        players.add(newPlayer);

        // Update the ListView with the new player's name
        namesListView.getItems().add(playerName);

        // Save all players to the file
        saveAllPlayersToFile();

        // Clear the text field after registering the player
        nameText.clear();
    }
    
    private void saveAllPlayersToFile() {
        try (PrintWriter output = new PrintWriter(new File("players.txt"))) {
            for (Players player : players) {
                output.println(player.getName()); // Assuming Players class has a getName() method
            }
        } catch (Exception ex) {
            System.out.println("Error writing data to text file: " + ex.getMessage());
        }
    }
    
    public void loadNamesFromFile() {
        try {
            List<String> names = Files.readAllLines(Paths.get("players.txt"));
            namesListView.getItems().setAll(names);
        } catch (IOException e) {
            e.printStackTrace(); // Or handle the exception as you see fit
        }
    }
   
//     /*   


//        Players newPlayer = new Players(playerName);
//        for (Players existingPlayer : players) {
//            // Create a match between the new player and each existing player
//            Match newMatch = new Match(newPlayer, existingPlayer);
//            existingPlayer.getMatches().add(newMatch);
//            newPlayer.getMatches().add(newMatch);
//        }
//        players.add(newPlayer);
//      //  updateActiveMatchesDisplay();
//
//        // Clear the text field after registering the player
//        nameText.clear();
//    }

//    private void updateActiveMatchesDisplay() {
//        List<Match> activeMatches = new ArrayList<>();
//        for (Players player : players) {
//            activeMatches.addAll(player.getActiveMatches());
//        }
//        matchesID.getItems().setAll(activeMatches);
//    }


    
//     @FXML
//    private void saveAll(ActionEvent event) {
//        try (PrintWriter output = new PrintWriter(new File("players.txt"))) {
//            for (Players player : players) {
//                output.println(player.toString());
//                for (Match match : player.getMatches()) {
//                    output.println("  " + match.toString());
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Error writing data to text file: " + ex.getMessage());
//        }
//    }
  
}


