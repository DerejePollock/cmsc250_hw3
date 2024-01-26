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

    @FXML
    private TextField nameText;
    @FXML
    private ListView<Match> matchesListView;
    @FXML
    private ListView<Players> leaderboardListView;

    @FXML

    public void generateMatches() {
        for (Match match : matches) {
            if (match.getStatus() == Match.MatchStatus.CREATED) {
                Players playerOne = match.getPlayerOne();
                Players playerTwo = match.getPlayerTwo();

                // Check if both players are not already in an active match
                if (!playerOne.isInActiveMatch() && !playerTwo.isInActiveMatch()) {
                    match.setStatus(Match.MatchStatus.ACTIVE);
                    playerOne.setInActiveMatch(true);
                    playerTwo.setInActiveMatch(true);
                }
            }
        }
        updateMatchesListView(); // Refresh the list view
    }

    public void registerPlayer() {
        String playerName = nameText.getText().trim();
        if (playerName.isEmpty()) {
            return;
        }

        Players newPlayer = new Players(playerName);
        players.add(newPlayer);

        for (Players existingPlayer : players) {
            if (!existingPlayer.equals(newPlayer)) {
                Match newMatch = new Match(newPlayer, existingPlayer);
                newMatch.setStatus(Match.MatchStatus.CREATED); // Initially set as CREATED
                matches.add(newMatch);
            }
        }

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

        // Set both players as not in an active match
        match.getPlayerOne().setInActiveMatch(false);
        match.getPlayerTwo().setInActiveMatch(false);

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
        matchesListView.getItems().clear();
        for (Match match : matches) {
            if (match.getStatus() == Match.MatchStatus.ACTIVE) {
                matchesListView.getItems().add(match);
            }
        }
    }
    
}
