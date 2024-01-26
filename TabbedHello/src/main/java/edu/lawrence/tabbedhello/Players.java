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
        this.won = 0;
        this.played = 0;
    }

    // Increment the count of won and played matches
    public void wonMatch() {
        won++;
        played++;
    }

    public void lostMatch() {
        played++;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getWon() {
        return won;
    }

    public int getPlayed() {
        return played;
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

    public void addMatch(Match match) {
        matches.add(match);
    }

    // Override toString if needed for ListView display
    @Override
    public String toString() {
        return name + " - Won: " + won + ", Played: " + played;
    }
}
