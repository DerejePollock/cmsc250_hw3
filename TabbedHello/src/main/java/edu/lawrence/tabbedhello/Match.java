package edu.lawrence.tabbedhello;

public class Match {
    private Players playerOne;
    private Players playerTwo;
    private MatchStatus status;

    public Match(Players playerOne, Players playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.status = MatchStatus.CREATED;
    }

    public enum MatchStatus {
        CREATED, ACTIVE, COMPLETED
    }

    // Getters and Setters
    public Players getPlayerOne() {
        return playerOne;
    }

    public Players getPlayerTwo() {
        return playerTwo;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    // Override toString if needed for ListView display
    @Override
    public String toString() {
        return playerOne.getName() + " ~vs~ " + playerTwo.getName() + " - Status: " + status;
    }
}
