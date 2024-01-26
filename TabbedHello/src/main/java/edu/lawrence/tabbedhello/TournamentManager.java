
package edu.lawrence.tabbedhello;

import java.util.ArrayList;
import java.util.List;
import static java.util.regex.Pattern.matches;


public class TournamentManager {
    
    private List<Players> players;
    private List<Match> matches;

    public void registerPlayer(String playerName) {
    Players newPlayer = new Players(playerName);
    for (Players existingPlayer : players) {
        Match newMatch = new Match(newPlayer, existingPlayer);
        matches.add(newMatch);
    }
    players.add(newPlayer);
}

    
    public List<Match> getActiveMatches() {
        List<Match> activeMatches = new ArrayList<>();
        for (Match match : matches) {
            if (match.getStatus() == Match.MatchStatus.ACTIVE) {
                activeMatches.add(match);
            }
        }
        return activeMatches;
    }
    
    public void generateMatches() {
    for (Match match : matches) {
        if (match.getStatus() == Match.MatchStatus.CREATED &&
            !match.getPlayerOne().isInActiveMatch() &&
            !match.getPlayerTwo().isInActiveMatch()) {

            match.setStatus(Match.MatchStatus.ACTIVE);
            match.getPlayerOne().setInActiveMatch(true);
            match.getPlayerTwo().setInActiveMatch(true);
        }
    }
}

}
