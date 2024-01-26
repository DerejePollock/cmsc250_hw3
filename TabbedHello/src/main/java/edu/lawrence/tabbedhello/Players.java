package edu.lawrence.tabbedhello;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private String name;
    private boolean inActiveMatch = false;
    private List<Match> matches = new ArrayList<>();
    private int won;
    private int played;

    public Players(String name) {
        this.name = name;
        this.won = 0;
        this.played = 0;
    }

    public void incrementWon() {
    this.won++;
    }

    public void incrementPlayed() {
        this.played++;
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
    
    public void removeMatch(Match match) {
        matches.remove(match);
    }

    
    @Override
    public String toString() {
        return name + " - Won: " + won + ", Played: " + played;
    }
}
