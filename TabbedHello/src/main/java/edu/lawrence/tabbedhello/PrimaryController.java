package edu.lawrence.tabbedhello;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;


public class PrimaryController {
    private List<Players> players = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    @FXML private TextField nameText;
    @FXML private ListView<Match> matchesListView; // Assuming you have this in your FXML for active matches
    @FXML private ListView<Players> leaderboardListView; // Assuming you have this in your FXML for leaderboard

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

    public void updateLeaderboard() {
        players.sort((p1, p2) -> p2.getWon() - p1.getWon());
        leaderboardListView.getItems().setAll(players);
    }

    // Add other methods as needed for handling match results and updating player statuses
}