package edu.lawrence.tabbedhello;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class PrimaryController {
    private List<Players> players = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    @FXML private TextField nameText;
    @FXML private ListView<Match> matchesListView; // Assuming you have this in your FXML for active matches
    @FXML private ListView<Players> leaderboardListView; // Assuming you have this in your FXML for leaderboard
    @FXML
    
    private void generateMatches() {
    // Method implementation
    }
    

    public void registerPlayer() {
        String playerName = nameText.getText().trim();
        if (playerName.isEmpty()) {
            // Handle the case where the player name is empty
            return;
        }

        Players newPlayer = new Players(playerName);
    players.add(newPlayer);

    for (Players existingPlayer : players) {
        if (!existingPlayer.equals(newPlayer)) {
            Match newMatch = new Match(newPlayer, existingPlayer);
            newMatch.setStatus(Match.MatchStatus.ACTIVE); // Set the match status to ACTIVE
            matches.add(newMatch);
            existingPlayer.addMatch(newMatch);
            newPlayer.addMatch(newMatch);
        }
    }

    updateActiveMatchesDisplay();
    nameText.clear();
}

    public void updateActiveMatchesDisplay() {
        List<Match> activeMatches = new ArrayList<>();
        for (Match match : matches) {
            if (match.getStatus() == Match.MatchStatus.ACTIVE) {
                activeMatches.add(match);
            }
        }
        matchesListView.getItems().setAll(activeMatches);
    }
    
    public void initialize() {
        matchesListView.setOnMouseClicked(event -> {
            Match selectedMatch = matchesListView.getSelectionModel().getSelectedItem();
            if (selectedMatch != null && selectedMatch.getStatus() == Match.MatchStatus.ACTIVE) {
                showWinnerSelectionDialog(selectedMatch);
            }
        });
    }
    
    
    private void showWinnerSelectionDialog(Match match) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select Winner");
        alert.setHeaderText("Choose the winner for: " + match);

        ButtonType playerOneButton = new ButtonType(match.getPlayerOne().getName());
        ButtonType playerTwoButton = new ButtonType(match.getPlayerTwo().getName());
        alert.getButtonTypes().setAll(playerOneButton, playerTwoButton, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == playerOneButton) {
                processMatchResult(match, match.getPlayerOne());
            } else if (response == playerTwoButton) {
                processMatchResult(match, match.getPlayerTwo());
            }
        });
    }
    
    
    private void processMatchResult(Match match, Players winner) {
    // Set the match status to COMPLETED
    match.setStatus(Match.MatchStatus.COMPLETED);

    // Increment the winner's win count and both players' played count
    winner.incrementWon();
    match.getPlayerOne().incrementPlayed();
    match.getPlayerTwo().incrementPlayed();

    // Remove the match from the active matches list
    matches.removeIf(m -> m.getStatus() == Match.MatchStatus.COMPLETED);

    // Update the ListView and leaderboard
    updateMatchesListView();
    updateLeaderboard();
}
    public void updateLeaderboard() {
    // Sort players by number of wins (descending order)
    players.sort((p1, p2) -> Integer.compare(p2.getWon(), p1.getWon()));

    // Update the leaderboard list view
    leaderboardListView.getItems().setAll(players);
}
    public void updateMatchesListView() {
    // Clear the current list
    matchesListView.getItems().clear();

    // Add matches to the list view based on certain criteria
    for (Match match : matches) {
        // For example, you might want to display only ACTIVE matches
        if (match.getStatus() == Match.MatchStatus.ACTIVE) {
            matchesListView.getItems().add(match);
        }
    }
}


    // Add other methods as needed for handling match results and updating player statuses
}

