package edu.lawrence.tabbedhello;

public class Match {
    private Players playerOne;
    private Players playerTwo;
    private MatchStatus status;
    private Players winner; 

    public Match(Players playerOne, Players playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.status = MatchStatus.CREATED;
        this.winner = null; // Initialize winner as null
    }


    public enum MatchStatus {
        CREATED, ACTIVE, COMPLETED
    }
    
    public void setWinner(Players winner) {
        if (winner.equals(playerOne) || winner.equals(playerTwo)) {
            this.winner = winner;
            setStatus(MatchStatus.COMPLETED);
        }
    }
    
    public Players getWinner() {
        return winner;
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

    
    @Override
    public String toString() {
        return playerOne.getName() + " ~vs~ " + playerTwo.getName() + " - Status: " + status;
    }
}
