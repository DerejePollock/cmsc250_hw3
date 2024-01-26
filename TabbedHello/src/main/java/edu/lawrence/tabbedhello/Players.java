package edu.lawrence.tabbedhello;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private String name;
    private boolean inActiveMatch = false;
    private List<Match> matches = new ArrayList<>();
    private int won;
    private int played;

    // Constructor
    public Players(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isInActiveMatch() {
        return inActiveMatch;
    }
    
    public void setInActiveMatch(boolean inActiveMatch) {
        this.inActiveMatch = inActiveMatch;
    }
    
    public List<Match> getMatches() {
        return matches;
    }

    public void addMatch(Players opponent) {
        Match newMatch = new Match(this, opponent);
        matches.add(newMatch);
        opponent.matches.add(newMatch);
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

    // Getters and setters...
}
